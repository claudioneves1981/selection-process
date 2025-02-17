package com.claudioneves.selectionprocess.dto;

import java.text.DecimalFormat;

public non-sealed class Manager extends SelectedCandidate{

    private double comission;

    public Manager() {
    }

    public Manager(String vacancyCode, String candidate, String message, ContactAttempt contactAttempt, Double salary, double comission) {
        super(vacancyCode, candidate, message, contactAttempt, salary);
        this.comission = comission;
    }

    @Override
    public String getVacancyCode(){
        return "MN" + super.getVacancyCode();
    }

    @Override
    public String getFullSalary() {
        DecimalFormat df = new DecimalFormat("R$ #.00");
        return df.format(this.salary + this.comission);
    }

    public double getComission() {
        return comission;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

}
