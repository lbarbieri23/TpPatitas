package com.dds.services.whatsapp.ChatApiImpl;

public class ChatApiResponse {

    private Boolean sent;
    private String message;

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
