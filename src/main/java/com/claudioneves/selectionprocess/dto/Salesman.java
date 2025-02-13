package com.claudioneves.selectionprocess.dto;

public non-sealed class Salesman extends SelectedCandidate {

    private double percentPayablePerSold;


    public Salesman(String vacancyCode, String candidate, String message, ContactAttempt contactAttempt, Double salary, double percentPayablePerSold) {
        super(vacancyCode, candidate, message, contactAttempt, salary);
        this.percentPayablePerSold = percentPayablePerSold;
    }


    public Salesman() {
    }

    @Override
    public String getVacancyCode(){
        return "SL" + super.getVacancyCode();
    }

    @Override
    public double getFullSalary() {
        return this.salary;
    }


    public double getPercentPayablePerSold() {
        return percentPayablePerSold;
    }

    public void setPercentPayablePerSold(double percentPayablePerSold) {
        this.percentPayablePerSold = percentPayablePerSold;
    }

}