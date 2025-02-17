package com.claudioneves.selectionprocess.dto;

public class ContactAttempt {

    private int attempts;
    private String message;
    private String phoneAttempted;
    private Boolean awnser;


    public ContactAttempt() {
    }

    public ContactAttempt(int attempts, String message, String phoneAttempted, Boolean awnser) {
        this.attempts = attempts;
        this.message = message;
        this.phoneAttempted = phoneAttempted;
        this.awnser = awnser;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneAttempted() {
        return phoneAttempted;
    }

    public void setPhoneAttempted(String phoneAttempted) {
        this.phoneAttempted = phoneAttempted;
    }

    public Boolean getAwnser() {
        return awnser;
    }

    public void setAwnser(Boolean awnser) {
        this.awnser = awnser;
    }

}
