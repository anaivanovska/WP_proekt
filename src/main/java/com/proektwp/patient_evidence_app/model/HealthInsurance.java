package com.proektwp.patient_evidence_app.model;

import javax.persistence.*;

@Entity
@Table(name= "health_insurance")
public class HealthInsurance {

    @Id
    @Column(name="insurance_id")
    public String healthLegitimationNumber;

    @Column
    public String registrationNumber;

    @Column
    public String activityID;

    @Column
    public String typeOfHealthProtection;

    @OneToOne
    public Patient patient;


    public HealthInsurance(){}

    public HealthInsurance(String healthLegitimationNumber, String registrationNumber, String activityID, String typeOfHealthProtection) {
        this.healthLegitimationNumber = healthLegitimationNumber;
        this.registrationNumber = registrationNumber;
        this.activityID = activityID;
        this.typeOfHealthProtection = typeOfHealthProtection;
    }
}
