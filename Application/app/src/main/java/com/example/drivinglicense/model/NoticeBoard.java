package com.example.drivinglicense.model;

public class NoticeBoard {
    private int typeId;
    private String name;
    private String detail;
    private String Icon;

    public NoticeBoard(int typeId, String name, String detail, String icon) {
        this.typeId = typeId;
        this.name = name;
        this.detail = detail;
        Icon = icon;
    }

    public NoticeBoard() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

}
