package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "health_examination")
public class HealthExamination {

    @EmbeddedId
    public HealthExaminationID examinationID;

    public String diagnosisAtMKB;

    public Boolean laboratory_Finding;

    public Boolean RTC_Finding;

    public Boolean EHO_Finding;

    public Boolean computed_Tomography;

    public Boolean magnetic_Resonance;

    public Boolean sent_To_A_specialist;

    public Boolean illness;

    public Boolean bandage;

    public Boolean antibody_Prophylaxis;

    public String typeOfTherapy;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "healthExamination")
    public List<Medicine> medicines;

    @MapsId("userId")
    @ManyToOne
    @JsonIgnore
    public Patient patient;

    public HealthExamination(){}

    public HealthExamination(HealthExaminationID healthExaminationID, String diagnosisAtMKB, Boolean laboratory_Finding, Boolean RTC_Finding, Boolean EHO_Finding, Boolean computed_Tomography, Boolean magnetic_Resonance, Boolean sent_To_A_specialist, Boolean illness, Boolean bandage, Boolean antibody_Prophylaxis, String  typeOfTherapy, Patient patient) {
        this.examinationID = healthExaminationID;
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