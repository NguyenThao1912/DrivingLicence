package com.example.drivinglicense.model;

import com.example.drivinglicense.global.AppGlobal;

public class CurrentQuestion {
    private int questionIndex;
    private AppGlobal.ANSWER_TYPE type;

    public CurrentQuestion(int questionIndex, AppGlobal.ANSWER_TYPE type) {
        this.questionIndex = questionIndex;
        this.type = type;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public AppGlobal.ANSWER_TYPE getType() {
        return type;
    }

    public void setType(AppGlobal.ANSWER_TYPE type) {
        this.type = type;
    }
}
