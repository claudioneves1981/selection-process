package com.claudioneves.aulajparepository.dto;

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
    public double getFullSalary() {
        return this.salary + this.comission;
    }

    public double getFullSalary(double extra) {
        return getFullSalary() + extra;
    }

    public double getComission() {
        return comission;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }


}
