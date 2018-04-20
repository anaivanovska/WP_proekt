package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patient")
public class Patient extends User{

    @Temporal(TemporalType.DATE)
    public Date dateOfBirth;

    @NotNull
    public String gender;

    @NotNull
    public String embg;

    @NotNull
    public String proffesion;

    @NotNull
    public String marrigeState;

    @ManyToOne
    @JsonIgnoreProperties("deputyFamilyDoctor")
    public FamilyDoctor familyDoctor;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL)
    public HealthInsurance healthInsurance;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    @JsonIgnore
    public List<HealthExamination> healthExaminations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    @JsonIgnore
    public List<Vaccine> vaccines;

    public Patient(){
        this.role = Role.ROLE_PATIENT;
    };


    public Patient(String userId, String firstName, String lastName, String password, String email, String phoneNumber, String address, Date dateOfBirth, String gender, String embg, String proffesion, String marrigeState, FamilyDoctor familyDoctor) {
        super(userId, firstName, lastName, Role.ROLE_PATIENT, password, email, phoneNumber, address);
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.embg = embg;
        this.proffesion = proffesion;
        this.marrigeState = marrigeState;
        this.familyDoctor = familyDoctor;
        this.healthInsurance = null;
        this.healthExaminations = null;
        this.vaccines = null;
    }
}