package com.example.drivinglicense.model;

public class TipCategory {
    private int type; //Dạng mẹo
    private int category; //Thể loại mẹo
    private String name; //tên thể loại mẹo

    public TipCategory() {
    }

    public TipCategory(int type, int category, String name) {
        this.type = type;
        this.category = category;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
