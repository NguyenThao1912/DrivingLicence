package com.example.drivinglicense.model;

public class Question {

    private int Z_PK;             //khóa chính
    private String ZQUESTIONCONTENT;// nội dung câu hỏi
    private String ZIMAGE;          //ảnh
    private String ZOPTION1;        //lựa chọn 1
    private String ZOPTION2;        //lựa chọn 2
    private String ZOPTION3;        //lựa chọn 3
    private String ZOPTION4;        //lựa chọn 4
    private String ZANSWERDESC;     //nội dung đáp án
    private int ZANSWERS;           //vị trí đáp án đáp án
    private int ZQUESTIONDIE;       //câu điểm liệt
    private int ZLEARNED;           //đã học
    private int ZMARKED;            //đã đánh dấu
    private int ZWRONG;             //sai ?

    /*
        private int ZQUESTIONTYPE;
        private int ZINCLUDEA1;
        private int ZINCLUDEA2;
        private int ZINCLUDEA34;
        private int ZINCLUDEB1;
        private int ZINCLUDEB2;
        private int ZINCLUDEC;
        private int ZINCLUDEDEF;
        private int Z_ENT;
    */
    public Question() {
    }

    public Question(int z_PK, String ZQUESTIONCONTENT, String ZIMAGE, String ZOPTION1, String ZOPTION2, String ZOPTION3, String ZOPTION4, String ZANSWERDESC, int ZANSWERS, int ZQUESTIONDIE, int ZLEARNED, int ZMARKED, int ZWRONG) {
        Z_PK = z_PK;
        this.ZQUESTIONCONTENT = ZQUESTIONCONTENT;
        this.ZIMAGE = ZIMAGE;
        this.ZOPTION1 = ZOPTION1;
        this.ZOPTION2 = ZOPTION2;
        this.ZOPTION3 = ZOPTION3;
        this.ZOPTION4 = ZOPTION4;
        this.ZANSWERDESC = ZANSWERDESC;
        this.ZANSWERS = ZANSWERS;
        this.ZQUESTIONDIE = ZQUESTIONDIE;
        this.ZLEARNED = ZLEARNED;
        this.ZMARKED = ZMARKED;
        this.ZWRONG = ZWRONG;
    }

    public String getZQUESTIONCONTENT() {
        return ZQUESTIONCONTENT;
    }

    public void setZQUESTIONCONTENT(String ZQUESTIONCONTENT) {
        this.ZQUESTIONCONTENT = ZQUESTIONCONTENT;
    }

    public String getZIMAGE() {
        return ZIMAGE;
    }

    public void setZIMAGE(String ZIMAGE) {
        this.ZIMAGE = ZIMAGE;
    }

    public String getZOPTION1() {
        return ZOPTION1;
    }

    public void setZOPTION1(String ZOPTION1) {
        this.ZOPTION1 = ZOPTION1;
    }

    public String getZOPTION2() {
        return ZOPTION2;
    }

    public void setZOPTION2(String ZOPTION2) {
        this.ZOPTION2 = ZOPTION2;
    }

    public String getZOPTION3() {
        return ZOPTION3;
    }

    public void setZOPTION3(String ZOPTION3) {
        this.ZOPTION3 = ZOPTION3;
    }

    public String getZOPTION4() {
        return ZOPTION4;
    }

    public void setZOPTION4(String ZOPTION4) {
        this.ZOPTION4 = ZOPTION4;
    }

    public String getZANSWERDESC() {
        return ZANSWERDESC;
    }

    public void setZANSWERDESC(String ZANSWERDESC) {
        this.ZANSWERDESC = ZANSWERDESC;
    }

    public int getZANSWERS() {
        return ZANSWERS;
    }

    public void setZANSWERS(int ZANSWERS) {
        this.ZANSWERS = ZANSWERS;
    }

    public int getZQUESTIONDIE() {
        return ZQUESTIONDIE;
    }

    public void setZQUESTIONDIE(int ZQUESTIONDIE) {
        this.ZQUESTIONDIE = ZQUESTIONDIE;
    }

    public int getZLEARNED() {
        return ZLEARNED;
    }

    public void setZLEARNED(int ZLEARNED) {
        this.ZLEARNED = ZLEARNED;
    }

    public int getZMARKED() {
        return ZMARKED;
    }

    public void setZMARKED(int ZMARKED) {
        this.ZMARKED = ZMARKED;
    }

    public int getZWRONG() {
        return ZWRONG;
    }

    public void setZWRONG(int ZWRONG) {
        this.ZWRONG = ZWRONG;
    }

    public int getZ_PK() {
        return Z_PK;
    }

    public void setZ_PK(int z_PK) {
        Z_PK = z_PK;
    }

}
