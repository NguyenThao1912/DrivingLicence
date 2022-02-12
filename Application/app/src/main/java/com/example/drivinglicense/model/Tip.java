package com.example.drivinglicense.model;

public class Tip {
    private int category; //Thể loại mẹo
    private String content; //nội dung mẹo

    public Tip() {
    }

    public Tip(int category, String content) {
        this.category = category;
        this.content = content;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
