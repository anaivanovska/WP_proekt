package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patient")
public class Patient extends User{

    @Temporal(TemporalType.DATE)
    public Date dateOfBirth;

    public String gender;

    public String embg;

    public String address;

    public String proffesion;

    public String marrigeState;

    public String phoneNumber;

    public String email;


    @ManyToOne(fetch =  FetchType.LAZY)
    public FamilyDoctor familyDoctor;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public HealthInsurance healthInsurance;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    public List<HealthExamination> healthExaminations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    public List<Vaccine> vaccines;

    //@OneToOne(cascade = CascadeType.REMOVE)
    //public FamilyHistory familyHistory;

    public Patient(){};

    public Patient(String userId, String firstName, String lastName,  String password) {
        super(userId, firstName, lastName, "ROLE_USER", password);
    }

    public Patient(String userId, String firstName, String lastName, String password, Date dateOfBirth, String gender, String embg, String address, String proffesion, String marrigeState, String phoneNumber, String email, FamilyDoctor familyDoctor, HealthInsurance healthInsurance, List<HealthExamination> healthExaminations, List<Vaccine> vaccines) {
        super(userId, firstName, lastName, "ROLE_USER", password);
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.embg = embg;
        this.address = address;
        this.proffesion = proffesion;
        this.marrigeState = marrigeState;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.familyDoctor = familyDoctor;
        this.healthInsurance = healthInsurance;
        this.healthExaminations = healthExaminations;
        this.vaccines = vaccines;
    }
}