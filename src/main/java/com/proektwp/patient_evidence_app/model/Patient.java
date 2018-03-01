package com.proektwp.patient_evidence_app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    public String healthRecordNumber;

    @Column
    public String firstName;

    @Column
    public String lastName;

    @Column
    @Temporal(TemporalType.DATE)
    public Date dateOfBirth;

    @Column
    public String gender;

    @Column
    public String embg;

    @Column
    public String address;

    @Column
    public String proffesion;

    @Column
    public String marrigeState;

    @Column
    public String phoneNumber;

    @Column
    public String email;

    @ManyToOne
    public FamilyDoctor familyDoctor;

    @OneToOne(cascade = CascadeType.ALL)
    public HealthInsurance healthInsurance;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    public List<HealthExamination> healthExaminations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    public List<Vaccine> vaccines;

    //@OneToOne(cascade = CascadeType.REMOVE)
    //public FamilyHistory familyHistory;

    public Patient(){};


}
