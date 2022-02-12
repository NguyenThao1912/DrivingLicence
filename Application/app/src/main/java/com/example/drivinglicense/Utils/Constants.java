package com.example.drivinglicense.Utils;

public class Constants {
    public static String TABLE_QUESTION = "ZQUESTION";
    public static String TABLE_LICENSE = "ZLICENSE";
    public static String TABLE_QUESTION_TYPE = "ZQUESTIONTYPE";
    public static String TABLE_SIGN = "ZSIGN";
    public static String TABLE_SIGN_CATEGORY = "ZSIGNCATEGORY";
    public static String TABLE_TEST = "ZTEST";
    public static String TABLE_TEST_QUEST = "ZTESTQUEST";
    public static String TABLE_TIP = "ZTIP";
    public static String TABLE_TIP_CATEGORY = "ZTIPCATEGORY";
    public static String TABLE_TIP_TYPE = "ZTIPTYPE";
    public static String TABLE_NOTICE_BOARD = "TABLE_NOTICE_BOARD";
    public static String TABLE_NOTICE_BOARDTYPE = "TABLE_NOTICE_BOARD_TYPE";
    public static String TABLE_NUMBER_QUESTION_PER_TYPE = "ZNUMBERQUESTIONPERTYPE";
    public static String[] COLUMN_QUESTION = {"Z_PK",
            "ZQUESTIONCONTENT",
            "ZIMAGE",
            "ZOPTION1",
            "ZOPTION2",
            "ZOPTION3",
            "ZOPTION4",
            "ZANSWERDESC",
            "ZANSWERS",
            "ZQUESTIONTYPE",
            "ZLEARNED",
            "ZMARKED",
            "ZWRONG",
            "ZINCLUDEA1", "ZINCLUDEA2", "ZINCLUDEA34", "ZINCLUDEB1", "ZINCLUDEB2", "ZINCLUDEC", "ZINCLUDEDEF", "ZQUESTIONDIE", "ZAWSA1", "ZENT"};
    public static String[] COLUMN_LICENCE = {
            "Z_PK",
            "Z_ENT",
            "Z_OPT",
            "ZNUMBEROFCORRECTQUESTION",
            "ZNUMBEROFQUESTION",
            "ZNUMBEROFTEST",
            "ZDURATION",
            "ZCONTENT",
            "ZDESC",
            "ZNAME"
    };
}
