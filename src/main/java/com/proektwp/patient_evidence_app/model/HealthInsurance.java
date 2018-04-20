package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name= "health_insurance")
public class HealthInsurance {

    @Id
    public String healthLegitimationNumber;

    public String registrationNumber;

    public String activityID;

    public String typeOfHealthProtection;

    @OneToOne
    @JsonIgnore
    public Patient patient;

    public HealthInsurance(){}

    public HealthInsurance(String healthLegitimationNumber, String registrationNumber, String activityID, String typeOfHealthProtection, Patient patient) {
        this.healthLegitimationNumber = healthLegitimationNumber;
        this.registrationNumber = registrationNumber;
        this.activityID = activityID;
        this.typeOfHealthProtection = typeOfHealthProtection;
        this.patient = patient;
    }
}