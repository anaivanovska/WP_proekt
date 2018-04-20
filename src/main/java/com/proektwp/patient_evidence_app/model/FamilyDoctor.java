package com.proektwp.patient_evidence_app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "family_doctor")
public class FamilyDoctor extends User{

    @NotNull
    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean agreement_with_FZO;

    @NotNull
    public String speciality;

    @NotNull
    public String workTime;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("deputyFamilyDoctor")
    public FamilyDoctor deputyFamilyDoctor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "familyDoctor")
    @JsonIgnore
    public List<Patient> patients;
    // so brishenje na doktorot da mu se naznaci drug doktor

    public FamilyDoctor(){
        this.role = Role.ROLE_DOCTOR;
    }


    public FamilyDoctor(String userId, String firstName, String lastName, String password, String email, String phoneNumber, String address, Boolean agreement_with_FZO, String speciality, String workTime, FamilyDoctor deputyFamilyDoctor) {
        super(userId, firstName, lastName, Role.ROLE_DOCTOR, password, email, phoneNumber, address);
        this.agreement_with_FZO = agreement_with_FZO;
        this.speciality = speciality;
        this.workTime = workTime;
        this.deputyFamilyDoctor = deputyFamilyDoctor;
    }
}