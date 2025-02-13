package com.claudioneves.selectionprocess.dto;


public sealed abstract class SelectedCandidate permits Manager, Salesman{

    protected String vacancyCode;
    protected String candidate;
    protected String message;
    protected ContactAttempt contactAttempt;
    protected Double salary;

    public SelectedCandidate(String vacancyCode, String candidate, String message, ContactAttempt contactAttempt, Double salary) {
        this.vacancyCode = vacancyCode;
        this.candidate = candidate;
        this.message = message;
        this.contactAttempt = contactAttempt;
        this.salary = salary;
    }

    public SelectedCandidate() {
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getVacancyCode() {
        return vacancyCode;
    }

    public void setVacancyCode(String vacancyCode) {
        this.vacancyCode = vacancyCode;
    }

    public abstract double getFullSalary();
}
