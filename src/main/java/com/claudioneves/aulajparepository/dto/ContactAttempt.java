package com.claudioneves.aulajparepository.dto;

public class ContactAttempt {

    private int attempts;
    private String message;


    public ContactAttempt() {
    }

    public ContactAttempt(int attempts, String message) {
        this.attempts = attempts;
        this.message = message;
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
}
