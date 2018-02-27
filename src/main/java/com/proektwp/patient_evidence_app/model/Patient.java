package com.proektwp.patient_evidence_app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    public String healthRecordNumber;

    @Column
    @NotNull
    public String firstName;

    @Column
    @NotNull
    public String lastName;


    @Column
    @Temporal(TemporalType.DATE)
    public Date dateOfBirth;

    @Column
    public String gender;

    @Column
    @NotNull
    public String embg;

    @Column
    @NotNull
    public String address;

    @Column
    public String proffesion;

    @Column
    public String marrigeState;

    @Column
    public String phoneNumber;

    @Column
    public String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "patient")
    public HealthInsurance healthInsurance;

    public Patient(){};

    public Patient(String healthRecordNumber, String firstName, String lastName, Date dateOfBirth, String gender, String embg, String address, String proffesion, String marrigeState, String phoneNumber, String email, HealthInsurance healthInsurance) {
        this.healthRecordNumber = healthRecordNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.embg = embg;
        this.address = address;
        this.proffesion = proffesion;
        this.marrigeState = marrigeState;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.healthInsurance = healthInsurance;
    }
}
