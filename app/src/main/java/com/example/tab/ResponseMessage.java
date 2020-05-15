package com.example.tab;

public class ResponseMessage {
    String textMesage;
    boolean isSender;
    String date;

    public ResponseMessage(String textMesage, boolean isSender, String date) {
        this.textMesage = textMesage;
        this.isSender = isSender;
        this.date = date;
    }

    public String getTextMesage() {
        return textMesage;
    }

    public void setTextMesage(String textMesage) {
        this.textMesage = textMesage;
    }

    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }
}
