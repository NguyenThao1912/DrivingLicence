package com.example.drivinglicense.model;

public class NoticeBoardType {
    private int typeId;
    private String typeName;

    public NoticeBoardType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public NoticeBoardType() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
