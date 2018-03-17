package com.proektwp.patient_evidence_app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "family_doctor")
public class FamilyDoctor extends User{

    public Boolean agreement_with_FZO;

    public String speciality;

    public String address;

    public String phoneNumber;

    public String email;

    public String workTime;

    @OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JsonIgnore
    public FamilyDoctor deputyFamilyDoctor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "familyDoctor")
    @JsonIgnore
    public List<Patient> patients;
    // so brishenje na doktorot da mu se naznaci drug doktor

    public FamilyDoctor(){}

    public FamilyDoctor(String userId, String firstName, String lastName, String password) {
        super(userId, firstName, lastName, "ROLE_ADMIN", password);
    }

    public FamilyDoctor(String userId, String firstName, String lastName, String password, Boolean agreement_with_FZO, String speciality, String address, String phoneNumber, String email, String workTime, FamilyDoctor deputyFamilyDoctor, List<Patient> patients) {
        super(userId, firstName, lastName, "ROLE_ADMIN", password);
        this.agreement_with_FZO = agreement_with_FZO;
        this.speciality = speciality;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workTime = workTime;
        this.deputyFamilyDoctor = deputyFamilyDoctor;
        this.patients = patients;
    }
}