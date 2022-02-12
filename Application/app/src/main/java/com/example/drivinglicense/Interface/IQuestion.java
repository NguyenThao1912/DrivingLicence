package com.example.drivinglicense.Interface;

import com.example.drivinglicense.model.CurrentQuestion;

public interface IQuestion {
    CurrentQuestion getSelectedAnswer();
    void showCorrectAnswer();
    void disableAnswer();
    void resetQuestion();
}
