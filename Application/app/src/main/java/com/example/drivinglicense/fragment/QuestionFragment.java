package com.example.drivinglicense.fragment;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.drivinglicense.Interface.IQuestion;
import com.example.drivinglicense.R;
import com.example.drivinglicense.global.AppGlobal;
import com.example.drivinglicense.model.CurrentQuestion;
import com.example.drivinglicense.model.Question;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment implements IQuestion {
    private static final String TAG = "QuestionFragment";
    TextView txt_question_text;
    CheckBox ckbA, ckbB, ckbC, ckbD;
    FrameLayout layout_image;
    ProgressBar progressBar;
    Question question;
    int questionIndex = -1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_question, container, false);

        questionIndex = getArguments().getInt("index", -1);
        question = AppGlobal.questionList.get(questionIndex);

        if (question != null) {

            layout_image = itemView.findViewById(R.id.layout_image);
            progressBar = itemView.findViewById(R.id.progressBar);
            try {
                if (!question.getZIMAGE().equals("")) {
                    ImageView img = itemView.findViewById(R.id.img_question);
                    InputStream is = getContext().getAssets().open("imageapp_600/"+question.getZIMAGE());
                    Bitmap  bitmap = BitmapFactory.decodeStream(is);
                    img.setImageBitmap(bitmap);
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    layout_image.setVisibility(View.GONE);
                }
            }catch (Exception e)
            {
                Log.d(TAG, "onCreateView: " + e.getMessage());
            }

            txt_question_text = itemView.findViewById(R.id.txt_question_text);
            txt_question_text.setText(question.getZQUESTIONCONTENT());

            ckbA = itemView.findViewById(R.id.ckbA);
            ckbA.setText(question.getZOPTION1());
            ckbA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        AppGlobal.selected_values.add("1");
                    } else {
                        AppGlobal.selected_values.remove("1");
                    }
                }
            });

            ckbB = itemView.findViewById(R.id.ckbB);
            ckbB.setText(question.getZOPTION2());
            ckbB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        AppGlobal.selected_values.add("2");
                    } else {
                        AppGlobal.selected_values.remove("2");
                    }
                }
            });

            ckbC = itemView.findViewById(R.id.ckbC);
            if (question.getZOPTION3() == "") {
                ckbC.setVisibility(View.GONE);
            } else {
                ckbC.setText(question.getZOPTION3());
                ckbC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            AppGlobal.selected_values.add("3");
                        } else {
                            AppGlobal.selected_values.remove("3");
                        }
                    }
                });
            }


            ckbD = itemView.findViewById(R.id.ckbD);
            if (question.getZOPTION4() == null) {
                ckbD.setVisibility(View.GONE);
            } else {
                ckbD.setText(question.getZOPTION4());
                ckbD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            AppGlobal.selected_values.add("4");
                        } else {
                            AppGlobal.selected_values.remove("4");
                        }
                    }
                });
            }


        }
        return itemView;
    }

    @Override
    public CurrentQuestion getSelectedAnswer() {
        CurrentQuestion currentQuestion = new CurrentQuestion(questionIndex, AppGlobal.ANSWER_TYPE.NO_ANSWER);
        StringBuilder result = new StringBuilder();
        if (AppGlobal.selected_values.size() > 1) {
            Object[] arrayAnswer = AppGlobal.selected_values.toArray();
            for (int i = 0; i < arrayAnswer.length; i++) {
                if (i < arrayAnswer.length - 1)
                    result.append(new StringBuilder((String)arrayAnswer[i]).append(","));
                else
                    result.append(new StringBuilder((String)arrayAnswer[i]));
            }
        } else if (AppGlobal.selected_values.size() == 1) {
            Object[] arrayAnswer = AppGlobal.selected_values.toArray();
            result.append(new StringBuilder((String)arrayAnswer[0]));
        }

        if (question != null) {
            if (!TextUtils.isEmpty(result)) {
                if (result.toString().equals("" + question.getZANSWERS()))
                    currentQuestion.setType(AppGlobal.ANSWER_TYPE.RIGHT_ANSWER);
                else
                    currentQuestion.setType(AppGlobal.ANSWER_TYPE.WRONG_ANSWER);

            } else {
                currentQuestion.setType(AppGlobal.ANSWER_TYPE.NO_ANSWER);
            }
        } else {
            Toast.makeText(getContext(), "Can not get Answer", Toast.LENGTH_SHORT).show();
            currentQuestion.setType(AppGlobal.ANSWER_TYPE.NO_ANSWER);
        }
        AppGlobal.selected_values.clear();
        return currentQuestion;
    }

    @Override
    public void showCorrectAnswer() {
        int correctQuestion = question.getZANSWERS();

        if (correctQuestion == 1) {
            ckbA.setTypeface(null, Typeface.BOLD);
            ckbA.setTextColor(Color.RED);
        } else if (correctQuestion == 2) {
            ckbB.setTypeface(null, Typeface.BOLD);
            ckbB.setTextColor(Color.RED);
        } else if (correctQuestion == 3) {
            ckbC.setTypeface(null, Typeface.BOLD);
            ckbC.setTextColor(Color.RED);
        } else if (correctQuestion == 4) {
            ckbD.setTypeface(null, Typeface.BOLD);
            ckbD.setTextColor(Color.RED);
        }
    }

    @Override
    public void disableAnswer() {
        ckbA.setEnabled(false);
        ckbB.setEnabled(false);
        ckbC.setEnabled(false);
        ckbD.setEnabled(false);
    }

    @Override
    public void resetQuestion() {
        ckbA.setEnabled(true);
        ckbB.setEnabled(true);
        ckbC.setEnabled(true);
        ckbD.setEnabled(true);

        ckbA.setChecked(false);
        ckbB.setChecked(false);
        ckbC.setChecked(false);
        ckbD.setChecked(false);

        ckbA.setTypeface(null, Typeface.NORMAL);
        ckbA.setTextColor(Color.BLACK);
        ckbB.setTypeface(null, Typeface.NORMAL);
        ckbB.setTextColor(Color.BLACK);
        ckbC.setTypeface(null, Typeface.NORMAL);
        ckbC.setTextColor(Color.BLACK);
        ckbD.setTypeface(null, Typeface.NORMAL);
        ckbD.setTextColor(Color.BLACK);
    }
}