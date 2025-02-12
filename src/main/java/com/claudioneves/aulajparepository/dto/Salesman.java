package com.claudioneves.aulajparepository.dto;

public non-sealed class Salesman extends SelectedCandidate{

    private double percentPerSold;

    private double soldAmount;

    public Salesman(String vacancyCode, String candidate, String message, ContactAttempt contactAttempt, Double salary, double percentPerSold, double soldAmount) {
        super(vacancyCode, candidate, message, contactAttempt, salary);
        this.percentPerSold = percentPerSold;
        this.soldAmount = soldAmount;
    }


    public Salesman() {
    }

    @Override
    public String getVacancyCode(){
        return "SL" + super.getVacancyCode();
    }

    @Override
    public double getFullSalary() {
        return this.salary +(soldAmount * percentPerSold) / 100;
    }


    public double getPercentPerSold() {
        return percentPerSold;
    }

    public void setPercentPerSold(double percentPerSold) {
        this.percentPerSold = percentPerSold;
    }

    public double getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(double soldAmount) {
        this.soldAmount = soldAmount;
    }
}