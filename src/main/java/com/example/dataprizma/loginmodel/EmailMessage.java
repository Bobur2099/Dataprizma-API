package com.example.dataprizma.loginmodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


public class EmailMessage {

    private String to;
    private String message;

    public EmailMessage() {
    }

    public EmailMessage(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
