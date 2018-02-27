package com.proektwp.patient_evidence_app.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.*;

@Entity
@Table(name = "family_doctor")
public class FamilyDoctor {
    @Id
    @NotNull
    public String number_OfStamp;

    @Column
    @NotNull
    public String firstName;

    @Column
    @NotNull
    public String lastName;

    @Column
    public Boolean agreement_with_FZO;

    @Column
    @NotNull
    public String speciality;

    @Column
    @NotNull
    public String address;

    @Column
    @NotNull
    public String phoneNumber;

    @Column
    public String email;

    @Column
    public String workTime;

    @OneToOne(cascade = CascadeType.REMOVE)
    public FamilyDoctor deputyFamilyDoctor;

    public FamilyDoctor(){}

}
