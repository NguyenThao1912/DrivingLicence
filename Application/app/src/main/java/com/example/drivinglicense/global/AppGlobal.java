package com.example.drivinglicense.global;

import android.app.Application;
import android.content.Intent;
import android.os.CountDownTimer;

import com.example.drivinglicense.fragment.QuestionFragment;
import com.example.drivinglicense.model.CurrentQuestion;
import com.example.drivinglicense.model.Licence;
import com.example.drivinglicense.model.Question;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class AppGlobal extends Application {

    // TODO cần lưu lại biến này vào bộ nhớ máy khi mở ứng dụng lên thì đọc lại
    public static final int TOTAL_TIME = 20 * 60 * 1000;
    public static final String KEY_BACK_FROM_RESULT = "BACK FROM RESULT";
    public static Licence licence;
    public static int currentTestId = 0;
    public static List<Question> questionList = new ArrayList<>();
    public static List<CurrentQuestion> answerSheetList = new ArrayList<>();
    public static List<CurrentQuestion> answerSheetListFiltered = new ArrayList<>();
    public static CountDownTimer countDownTimer;
    public static ArrayList<QuestionFragment> fragmentList = new ArrayList<>();
    public static TreeSet<String> selected_values = new TreeSet<>();
    public static int right_answer_count;
    public static int wrong_answer_count;
    public static int timer = 0;
    public static int no_answer_count = 0;
    public static StringBuilder data_question = new StringBuilder();

    public enum ANSWER_TYPE {
        NO_ANSWER,
        WRONG_ANSWER,
        RIGHT_ANSWER
    }
}
