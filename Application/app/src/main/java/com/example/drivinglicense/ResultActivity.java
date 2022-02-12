package com.example.drivinglicense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.drivinglicense.adapter.GridResultAdapter;
import com.example.drivinglicense.global.AppGlobal;
import com.example.drivinglicense.views.MainActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.concurrent.TimeUnit;

public class ResultActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txt_timer, txt_right_answer, txt_result;
    Button btn_filter_total, btn_right_answer, btn_wrong_answer, btn_filter_no_answer;
    RecyclerView rv_result;
    GridResultAdapter adapter, filtered_adapter;

    BroadcastReceiver backToQuestion = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().toString().equals(AppGlobal.KEY_BACK_FROM_RESULT)) {
                int question = intent.getIntExtra(AppGlobal.KEY_BACK_FROM_RESULT, -1);
                goBackActivityWithQuestion(question);
            }
        }
    };

    private void goBackActivityWithQuestion(int question) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(AppGlobal.KEY_BACK_FROM_RESULT, question);
        setResult(Activity.RESULT_OK, returnIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(backToQuestion, new IntentFilter(AppGlobal.KEY_BACK_FROM_RESULT));

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//       toolbar.setTitle("RESULT");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txt_result = findViewById(R.id.txt_result);
        txt_timer = findViewById(R.id.txt_time);
        txt_right_answer = findViewById(R.id.txt_right_answer);

        btn_filter_total = findViewById(R.id.btn_filter_total);
        btn_right_answer = findViewById(R.id.btn_right_answer);
        btn_wrong_answer = findViewById(R.id.btn_wrong_answer);
        btn_filter_no_answer = findViewById(R.id.btn_filter_no_answer);

        rv_result = findViewById(R.id.rv_result);
        rv_result.setHasFixedSize(true);
        rv_result.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new GridResultAdapter(this, AppGlobal.answerSheetList);
        rv_result.setAdapter(adapter);

        txt_timer.setText(String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(AppGlobal.timer),
                TimeUnit.MILLISECONDS.toSeconds(AppGlobal.timer) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(AppGlobal.timer))));

        txt_right_answer.setText(new StringBuilder("").append(AppGlobal.right_answer_count).append("/").append(AppGlobal.questionList.size()));

        btn_filter_total.setText(new StringBuilder("").append(AppGlobal.questionList.size()));
        btn_right_answer.setText(new StringBuilder("").append(AppGlobal.right_answer_count));
        btn_wrong_answer.setText(new StringBuilder("").append(AppGlobal.wrong_answer_count));
        btn_filter_no_answer.setText(new StringBuilder("").append(AppGlobal.no_answer_count));

        int percent = (AppGlobal.right_answer_count*100/AppGlobal.questionList.size());
        if (percent > 90)
            txt_result.setText("EXCELLENT");
        else if (percent > 80)
            txt_result.setText("GOOD");
        else if (percent > 70)
            txt_result.setText("FAIR");
        else if (percent > 60)
            txt_result.setText("POOR");
        else if (percent > 50)
            txt_result.setText("BAD");
        else
            txt_result.setText("FAILED");

        btn_filter_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter == null) {
                    adapter = new GridResultAdapter(ResultActivity.this, AppGlobal.answerSheetList);
                    rv_result.setAdapter(adapter);
                } else {
                    rv_result.setAdapter(adapter);
                }
            }
        });

        btn_filter_no_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppGlobal.answerSheetListFiltered.clear();
                for (int i=0; i<AppGlobal.answerSheetList.size(); i++) {
                    if (AppGlobal.answerSheetList.get(i).getType() == AppGlobal.ANSWER_TYPE.NO_ANSWER) {
                        AppGlobal.answerSheetListFiltered.add(AppGlobal.answerSheetList.get(i));
                    }
                }
                filtered_adapter = new GridResultAdapter(ResultActivity.this, AppGlobal.answerSheetListFiltered);
                rv_result.setAdapter(filtered_adapter);
            }
        });

        btn_right_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppGlobal.answerSheetListFiltered.clear();
                for (int i=0; i<AppGlobal.answerSheetList.size(); i++) {
                    if (AppGlobal.answerSheetList.get(i).getType() == AppGlobal.ANSWER_TYPE.RIGHT_ANSWER) {
                        AppGlobal.answerSheetListFiltered.add(AppGlobal.answerSheetList.get(i));
                    }
                }
                filtered_adapter = new GridResultAdapter(ResultActivity.this, AppGlobal.answerSheetListFiltered);
                rv_result.setAdapter(filtered_adapter);
            }
        });

        btn_wrong_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppGlobal.answerSheetListFiltered.clear();
                for (int i=0; i<AppGlobal.answerSheetList.size(); i++) {
                    if (AppGlobal.answerSheetList.get(i).getType() == AppGlobal.ANSWER_TYPE.WRONG_ANSWER) {
                        AppGlobal.answerSheetListFiltered.add(AppGlobal.answerSheetList.get(i));
                    }
                }
                filtered_adapter = new GridResultAdapter(ResultActivity.this, AppGlobal.answerSheetListFiltered);
                rv_result.setAdapter(filtered_adapter);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.result_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_view_answer:
                viewTestAnswer();
                break;
            case R.id.menu_do_again:
                doTestAgain();
                break;
            case R.id.menu_home_scene:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void viewTestAnswer() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("action", "viewtestanswer");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void doTestAgain() {
        new MaterialStyledDialog.Builder(ResultActivity.this)
                .setTitle("Finish!")
                .setDescription("Do you want to do test again?")
                .setNegativeText("No")
                .onNegative((dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveText("Yes")
                .onPositive((dialog, which) -> {
                    dialog.dismiss();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("action", "doitagain");
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }).show();
    }
}