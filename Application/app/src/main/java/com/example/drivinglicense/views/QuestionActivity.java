package com.example.drivinglicense.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.drivinglicense.R;
import com.example.drivinglicense.ResultActivity;
import com.example.drivinglicense.adapter.AnswerSheetAdapter;
import com.example.drivinglicense.adapter.QuestionFragmentAdapter;
import com.example.drivinglicense.db.DBManager;
import com.example.drivinglicense.fragment.QuestionFragment;
import com.example.drivinglicense.global.AppGlobal;
import com.example.drivinglicense.model.CurrentQuestion;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

public class QuestionActivity extends AppCompatActivity {

    private static final int CODE_GET_RESULT = 9999;
    int time_play = AppGlobal.TOTAL_TIME;
    boolean isAnswerModeView = false;
    RecyclerView answer_sheet_view;
    AnswerSheetAdapter adapter;
    TextView txt_right_answer, txt_wrong_answer;
    TextView txt_timer;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onDestroy() {
        if (AppGlobal.countDownTimer != null)
            AppGlobal.countDownTimer.cancel();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        takeQuestion();

        txt_right_answer = findViewById(R.id.txt_question_right);
        txt_timer = findViewById(R.id.txt_timer);

        txt_timer.setVisibility(View.VISIBLE);
        txt_right_answer.setVisibility(View.VISIBLE);
        txt_right_answer.setText(new StringBuilder(String.format("%d", AppGlobal.right_answer_count)).append("/")
                .append(String.format("%d", AppGlobal.questionList.size())).toString());

        countTimer();

//
        answer_sheet_view = (RecyclerView) findViewById(R.id.grid_answer);
        answer_sheet_view.setHasFixedSize(true);
//        answer_sheet_view.setLayoutManager(new GridLayoutManager(this));
        answer_sheet_view.setLayoutManager(new GridLayoutManager(this, AppGlobal.questionList.size() / 2));
        adapter = new AnswerSheetAdapter(this, AppGlobal.answerSheetList);
        answer_sheet_view.setAdapter(adapter);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.sliding_tabs);

        genFragment();

        QuestionFragmentAdapter questionFragmentAdapter = new QuestionFragmentAdapter(getSupportFragmentManager(), this, AppGlobal.fragmentList);
        viewPager.setAdapter(questionFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int SCROLL_RIGHT = 0;
            int SCROLL_LEFT = 1;
            int SCROLL_UNDETERMINED = 2;

            int currentScrollDirection = 2;

            private void setCurrentScrollDirection(float positionOffset) {
                if (1 - positionOffset > 0.5) {
                    this.currentScrollDirection = SCROLL_RIGHT;
                } else if (1 - positionOffset < 0.5) {
                    this.currentScrollDirection = SCROLL_LEFT;
                }
            }

            private Boolean isScrollDirectionUndetermined() {
                return currentScrollDirection == SCROLL_UNDETERMINED;
            }


            private Boolean iScrollRight() {
                return currentScrollDirection == SCROLL_RIGHT;
            }

            private Boolean iScrollLeft() {
                return currentScrollDirection == SCROLL_LEFT;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (isScrollDirectionUndetermined()) {
                    setCurrentScrollDirection(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                QuestionFragment questionFragment;
                int pos = 0;
                if (position > 0) {
                    if (iScrollRight()) {
                        questionFragment = AppGlobal.fragmentList.get(position - 1);
                        pos = position - 1;
                    } else if (iScrollLeft()) {
                        if (position < AppGlobal.fragmentList.size()-1) {
                            questionFragment = AppGlobal.fragmentList.get(position + 1);
                            pos = position + 1;
                        }else
                        {
                            questionFragment = AppGlobal.fragmentList.get(position);
                            pos = position ;
                        }
                    } else {
                        questionFragment = AppGlobal.fragmentList.get(pos);
                    }
                } else {
                    questionFragment = AppGlobal.fragmentList.get(0);
                    pos = 0;
                }

                CurrentQuestion question_state = questionFragment.getSelectedAnswer();
                AppGlobal.answerSheetList.set(position, question_state);
                adapter.notifyDataSetChanged();

                countCorrectAnswer();

                txt_right_answer.setText(new StringBuilder(String.format("%d", AppGlobal.right_answer_count)).append("/")
                        .append(String.format("%d", AppGlobal.questionList.size())).toString());
                txt_wrong_answer.setText(String.valueOf(AppGlobal.wrong_answer_count));

                if (question_state.getType() == AppGlobal.ANSWER_TYPE.NO_ANSWER) {
                    questionFragment.showCorrectAnswer();
                    questionFragment.disableAnswer();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE)
                    this.currentScrollDirection = SCROLL_UNDETERMINED;
            }
        });

    }

    private void finishedGame() {
        int position = viewPager.getCurrentItem();
        QuestionFragment questionFragment = AppGlobal.fragmentList.get(position);
        CurrentQuestion question_state = questionFragment.getSelectedAnswer();
        AppGlobal.answerSheetList.set(position, question_state);
        adapter.notifyDataSetChanged();

        countCorrectAnswer();

        txt_right_answer.setText(new StringBuilder(String.format("%d", AppGlobal.right_answer_count)).append("/")
                .append(String.format("%d", AppGlobal.questionList.size())).toString());
        txt_wrong_answer.setText(String.valueOf(AppGlobal.wrong_answer_count));

        if (question_state.getType() == AppGlobal.ANSWER_TYPE.NO_ANSWER) {
            questionFragment.showCorrectAnswer();
            questionFragment.disableAnswer();
        }

        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        AppGlobal.timer = AppGlobal.TOTAL_TIME - time_play;
        AppGlobal.no_answer_count = AppGlobal.questionList.size() - AppGlobal.wrong_answer_count - AppGlobal.right_answer_count;
        AppGlobal.data_question = new StringBuilder(new Gson().toJson(AppGlobal.answerSheetList));

        startActivityForResult(intent, CODE_GET_RESULT);

    }

    private void countCorrectAnswer() {
        AppGlobal.right_answer_count = AppGlobal.wrong_answer_count = 0;
        for (CurrentQuestion q : AppGlobal.answerSheetList) {
            if (q.getType() == AppGlobal.ANSWER_TYPE.RIGHT_ANSWER)
                AppGlobal.right_answer_count += 1;
            else if (q.getType() == AppGlobal.ANSWER_TYPE.WRONG_ANSWER)
                AppGlobal.wrong_answer_count++;
        }
    }

    private void genFragment() {
        for (int i = 0; i < AppGlobal.questionList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            QuestionFragment questionFragment = new QuestionFragment();
            questionFragment.setArguments(bundle);

            AppGlobal.fragmentList.add(questionFragment);
        }
    }

    private void countTimer() {
        if (AppGlobal.countDownTimer == null) {
            AppGlobal.countDownTimer = new CountDownTimer(AppGlobal.TOTAL_TIME, 1000) {
                @Override
                public void onTick(long l) {
                    txt_timer.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(l),
                            TimeUnit.MILLISECONDS.toSeconds(l) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));
                    time_play -= 1000;
                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {
            AppGlobal.countDownTimer.cancel();
            AppGlobal.countDownTimer = new CountDownTimer(AppGlobal.TOTAL_TIME, 1000) {
                @SuppressLint("DefaultLocale")
                @Override
                public void onTick(long l) {
                    txt_timer.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(l),
                            TimeUnit.MILLISECONDS.toSeconds(l) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));
                    time_play -= 1000;
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    }

    private void takeQuestion() {
        AppGlobal.questionList = DBManager.getInstance(this).getListQuestion_By_TestIndex_AND_LicenceName(AppGlobal.currentTestId, AppGlobal.licence.getZNAME());

        if (AppGlobal.questionList.size() == 0) {
            new MaterialStyledDatePickerDialog.Builder(this)
                    .setTitle("Wrong");
        } else {
            AppGlobal.answerSheetList.clear();
            for (int i = 0; i < AppGlobal.questionList.size(); i++) {
                AppGlobal.answerSheetList.add(new CurrentQuestion(i, AppGlobal.ANSWER_TYPE.NO_ANSWER));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem mMenuItem = menu.findItem(R.id.menu_wrong_item);
        ConstraintLayout constraintLayout = (ConstraintLayout) mMenuItem.getActionView();
        txt_wrong_answer = (TextView)constraintLayout.findViewById(R.id.txt_wrong_count);
        txt_wrong_answer.setText(String.valueOf(0));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_finish) {
            if (!isAnswerModeView) {
                new MaterialStyledDialog.Builder(QuestionActivity.this)
                        .setTitle("Finish!")
                        .setDescription("Do you really want to finish?")
                        .setNegativeText("No")
                        .onNegative((dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setPositiveText("Yes")
                        .onPositive((dialog, which) -> {
                            dialog.dismiss();
                            finishedGame();
                        }).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_GET_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                String action = data.getStringExtra("action");
                if (action == null || TextUtils.isEmpty(action)) {
                    int questionNum = data.getIntExtra(AppGlobal.KEY_BACK_FROM_RESULT, -1);
                    viewPager.setCurrentItem(questionNum);

                    isAnswerModeView = true;
                    AppGlobal.countDownTimer.cancel();

                    txt_wrong_answer.setVisibility(View.GONE);
                    txt_right_answer.setVisibility(View.GONE);
                    txt_timer.setVisibility(View.GONE);
                } else {
                    if (action.equals("viewtestanswer")) {
                        viewPager.setCurrentItem(0);

                        isAnswerModeView = true;
                        AppGlobal.countDownTimer.cancel();

                        txt_wrong_answer.setVisibility(View.GONE);
                        txt_right_answer.setVisibility(View.GONE);
                        txt_timer.setVisibility(View.GONE);

                        for (int i=0; i<AppGlobal.fragmentList.size(); i++) {
                            AppGlobal.fragmentList.get(i).showCorrectAnswer();
                            AppGlobal.fragmentList.get(i).disableAnswer();
                        }
                    } else if (action.equals("doitagain")) {
                        viewPager.setCurrentItem(0);

                        isAnswerModeView = true;
                        countTimer();

                        txt_wrong_answer.setVisibility(View.VISIBLE);
                        txt_right_answer.setVisibility(View.VISIBLE);
                        txt_timer.setVisibility(View.VISIBLE);

                        for (CurrentQuestion item: AppGlobal.answerSheetList)
                            item.setType(AppGlobal.ANSWER_TYPE.NO_ANSWER);

                        adapter.notifyDataSetChanged();

                        for (int i=0; i<AppGlobal.fragmentList.size(); i++) {
                            AppGlobal.fragmentList.get(i).resetQuestion();
                        }
                    }
                }
            }
        }
    }
}