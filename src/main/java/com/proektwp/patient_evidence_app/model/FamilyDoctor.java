package com.proektwp.patient_evidence_app.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "family_doctor")
public class FamilyDoctor {
    @Id
    public String number_OfStamp;

    @Column
    public String firstName;

    @Column
    public String lastName;

    @Column
    public Boolean agreement_with_FZO;

    @Column
    public String speciality;

    @Column
    public String address;

    @Column
    public String phoneNumber;

    @Column
    public String email;

    @Column
    public String workTime;

    @OneToOne(cascade = CascadeType.REMOVE)
    public FamilyDoctor deputyFamilyDoctor;

    @OneToMany(mappedBy = "familyDoctor")
    public List<Patient> patients;
    // so brishenje na doktorot da mu se naznaci drug doktor

    public FamilyDoctor(){}


}
