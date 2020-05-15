package com.example.tab;

public class ResponseMessage {
    String textMesage;
    boolean isSender;

    public ResponseMessage(String textMesage, boolean isSender) {
        this.textMesage = textMesage;
        this.isSender = isSender;
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
}
