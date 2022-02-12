package com.example.drivinglicense.model;

public class Licence {
    private int Z_PK;
    private int Z_ENT;
    private int Z_OPT;
    private int Z_NUMBER_OF_CORRECT_QUESTION;
    private int Z_NUMBER_OF_QUESTION;
    private int Z_NUMBER_OF_TEST;
    private float ZDURATION;
    private String ZCONTENT;
    private String ZDESC; // Mô tả
    private String ZNAME;// Tên bằng lái
    private int MS;

    public Licence() {
    }

    public Licence(String name, int ms) {
        ZNAME = name;
        MS = ms;
    }

    public Licence(int z_PK, int z_ENT, int z_OPT, int z_NUMBER_OF_CORRECT_QUESTION, int z_NUMBER_OF_QUESTION, int z_NUMBER_OF_TEST, float ZDURATION, String ZCONTENT, String ZDESC, String ZNAME) {
        Z_PK = z_PK;
        Z_ENT = z_ENT;
        Z_OPT = z_OPT;
        Z_NUMBER_OF_CORRECT_QUESTION = z_NUMBER_OF_CORRECT_QUESTION;
        Z_NUMBER_OF_QUESTION = z_NUMBER_OF_QUESTION;
        Z_NUMBER_OF_TEST = z_NUMBER_OF_TEST;
        this.ZDURATION = ZDURATION;
        this.ZCONTENT = ZCONTENT;
        this.ZDESC = ZDESC;
        this.ZNAME = ZNAME;
    }
//    public Licence(int z_ENT, int z_OPT, int z_NUMBER_OF_CORRECT_QUESTION, int z_NUMBER_OF_QUESTION, int z_NUMBER_OF_TEST, float ZDURATION, String ZCONTENT, String ZDESC, String ZNAME) {
//        Z_ENT = z_ENT;
//        Z_OPT = z_OPT;
//        Z_NUMBER_OF_CORRECT_QUESTION = z_NUMBER_OF_CORRECT_QUESTION;
//        Z_NUMBER_OF_QUESTION = z_NUMBER_OF_QUESTION;
//        Z_NUMBER_OF_TEST = z_NUMBER_OF_TEST;
//        this.ZDURATION = ZDURATION;
//        this.ZCONTENT = ZCONTENT;
//        this.ZDESC = ZDESC;
//        this.ZNAME = ZNAME;
//    }

    public int getZ_PK() {
        return Z_PK;
    }

    public void setZ_PK(int z_PK) {
        Z_PK = z_PK;
    }

    public int getZ_ENT() {
        return Z_ENT;
    }

    public void setZ_ENT(int z_ENT) {
        Z_ENT = z_ENT;
    }

    public int getZ_OPT() {
        return Z_OPT;
    }

    public void setZ_OPT(int z_OPT) {
        Z_OPT = z_OPT;
    }

    public int getZ_NUMBER_OF_CORRECT_QUESTION() {
        return Z_NUMBER_OF_CORRECT_QUESTION;
    }

    public void setZ_NUMBER_OF_CORRECT_QUESTION(int z_NUMBER_OF_CORRECT_QUESTION) {
        Z_NUMBER_OF_CORRECT_QUESTION = z_NUMBER_OF_CORRECT_QUESTION;
    }

    public int getZ_NUMBER_OF_QUESTION() {
        return Z_NUMBER_OF_QUESTION;
    }

    public void setZ_NUMBER_OF_QUESTION(int z_NUMBER_OF_QUESTION) {
        Z_NUMBER_OF_QUESTION = z_NUMBER_OF_QUESTION;
    }

    public int getZ_NUMBER_OF_TEST() {
        return Z_NUMBER_OF_TEST;
    }

    public void setZ_NUMBER_OF_TEST(int z_NUMBER_OF_TEST) {
        Z_NUMBER_OF_TEST = z_NUMBER_OF_TEST;
    }

    public float getZDURATION() {
        return ZDURATION;
    }

    public void setZDURATION(float ZDURATION) {
        this.ZDURATION = ZDURATION;
    }

    public String getZCONTENT() {
        return ZCONTENT;
    }

    public void setZCONTENT(String ZCONTENT) {
        this.ZCONTENT = ZCONTENT;
    }

    public String getZDESC() {
        return ZDESC;
    }

    public void setZDESC(String ZDESC) {
        this.ZDESC = ZDESC;
    }

    public String getZNAME() {
        return ZNAME;
    }

    public void setZNAME(String ZNAME) {
        this.ZNAME = ZNAME;
    }

    public int getMS() {
        return MS;
    }

    public void setMS(int MS) {
        this.MS = MS;
    }
}
