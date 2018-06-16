package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "health_examination")
public class HealthExamination {

    @EmbeddedId
    public HealthExaminationID examinationID;

    @NotNull
    public String diagnosisAtMKB;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean laboratory_Finding;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean RTC_Finding;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean EHO_Finding;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean computed_Tomography;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean magnetic_Resonance;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean sent_To_A_specialist;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean illness;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean bandage;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean antibody_Prophylaxis;


    public String typeOfTherapy;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "healthExamination")
    public List<Medicine> medicines;

    @MapsId("userId")
    @ManyToOne
    @JsonIgnore
    public Patient patient;

    public HealthExamination(){}

    public HealthExamination(Date dateOfExamination, String diagnosisAtMKB, Boolean laboratory_Finding, Boolean RTC_Finding, Boolean EHO_Finding, Boolean computed_Tomography, Boolean magnetic_Resonance, Boolean sent_To_A_specialist, Boolean illness, Boolean bandage, Boolean antibody_Prophylaxis, String  typeOfTherapy, Patient patient) {
        this.examinationID = new HealthExaminationID();
        this.examinationID.setDateOfExamination(dateOfExamination);
        this.diagnosisAtMKB = diagnosisAtMKB;
        this.laboratory_Finding = laboratory_Finding;
        this.RTC_Finding = RTC_Finding;
        this.EHO_Finding = EHO_Finding;
        this.computed_Tomography = computed_Tomography;
        this.magnetic_Resonance = magnetic_Resonance;
        this.sent_To_A_specialist = sent_To_A_specialist;
        this.illness = illness;
        this.bandage = bandage;
        this.antibody_Prophylaxis = antibody_Prophylaxis;
        this.typeOfTherapy = typeOfTherapy;
        this.patient = patient;
    }
}