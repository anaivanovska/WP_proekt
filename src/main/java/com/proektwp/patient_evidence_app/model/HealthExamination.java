package com.proektwp.patient_evidence_app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "health_examination")
public class HealthExamination {

    @Id
    @Temporal(TemporalType.DATE)
    public Date dateOfExamination;

    @Column
    public String diagnosisAtMKB;

    @Column
    public Boolean laboratory_Finding;

    @Column
    public Boolean RTC_Finding;

    @Column
    public Boolean EHO_Finding;

    @Column
    public Boolean computed_Tomography;

    @Column
    public Boolean magnetic_Resonance;

    @Column
    public Boolean sent_To_A_specialist;

    @Column
    public Boolean illness;

    @Column
    public Boolean bandage;

    @Column
    public Boolean antibody_Prophylaxis;

    @OneToOne(cascade = CascadeType.REMOVE)
    public Therapy therapy;

    @ManyToOne
    public Patient patient;

    public HealthExamination(){}


}