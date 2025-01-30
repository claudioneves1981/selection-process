package com.claudioneves.aulajparepository.dto;

import java.util.List;

public class SelectedCandidate {

    private String candidate;
    private String message;
    private ContactAttempt contactAttempt;


    public SelectedCandidate() {
    }

    public SelectedCandidate(String candidate, String message, ContactAttempt contactAttempt) {
        this.candidate = candidate;
        this.message = message;
        this.contactAttempt = contactAttempt;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactAttempt getContactAttempt() {
        return contactAttempt;
    }

    public void setContactAttempt(ContactAttempt contactAttempt) {
        this.contactAttempt = contactAttempt;
    }
}
