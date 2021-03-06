package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Indexed
@Entity
@Table(name="patient")
public class Patient extends User{

    @Temporal(TemporalType.DATE)
    public Date dateOfBirth;

    @NotNull
    public Gender gender;

    @NotNull
    public String embg;

    @NotNull
    public String profession;

    @NotNull
    public String marriageState;


    @ManyToOne
    @JsonIgnoreProperties("deputyFamilyDoctor")
    public FamilyDoctor familyDoctor;

    @OneToOne(cascade = CascadeType.ALL)
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


    public Patient(String userId, String firstName, String lastName, String password, String email, String phoneNumber, String address, Date dateOfBirth, String gender, String embg, String profession, String marriageState, HealthInsurance healthInsurance, FamilyDoctor familyDoctor) {
        super(userId, firstName, lastName, Role.ROLE_PATIENT, password, email, phoneNumber, address);
        this.dateOfBirth = dateOfBirth;
        this.gender = Gender.valueOf(gender);
        this.embg = embg;
        this.profession = profession;
        this.marriageState = marriageState;
        this.healthInsurance = healthInsurance;
        this.familyDoctor = familyDoctor;
        this.healthExaminations = new ArrayList<>();
        this.vaccines = new ArrayList<>();
    }
}