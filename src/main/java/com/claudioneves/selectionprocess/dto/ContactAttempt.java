package com.claudioneves.selectionprocess.dto;

public class ContactAttempt {

    private int attempts;
    private String message;
    private String phoneAttempted;


    public ContactAttempt() {
    }

    public ContactAttempt(int attempts, String message, String phoneAttempted ) {
        this.attempts = attempts;
        this.message = message;
        this.phoneAttempted = phoneAttempted;
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
}
