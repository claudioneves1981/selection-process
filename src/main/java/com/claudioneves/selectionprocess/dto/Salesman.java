package com.claudioneves.selectionprocess.dto;

import java.text.DecimalFormat;

public non-sealed class Salesman extends SelectedCandidate {

    private double percentPayablePerSold;


    public Salesman(String vacancyCode, String candidate, String message, ContactAttempt contactAttempt, Double salary, double percentPayablePerSold) {
        super(vacancyCode, candidate, message, contactAttempt, salary);
        this.percentPayablePerSold = percentPayablePerSold;
    }


    public Salesman() {
    }

    @Override
    public String getFullSalary() {
        DecimalFormat df = new DecimalFormat("R$ #.00");
        return df.format(this.salary);
    }


    public double getPercentPayablePerSold() {
        return percentPayablePerSold;
    }

    public void setPercentPayablePerSold(double percentPayablePerSold) {
        this.percentPayablePerSold = percentPayablePerSold;
    }

    @Override
    public String getVacancyCode(){
        return "SL" + super.getVacancyCode();
    }


}