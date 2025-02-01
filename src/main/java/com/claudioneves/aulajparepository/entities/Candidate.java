package com.claudioneves.aulajparepository.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Double salary;
    private String phone;

    public Candidate() {
    }

    public Candidate(Long id, String name, String email, Double salary, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
