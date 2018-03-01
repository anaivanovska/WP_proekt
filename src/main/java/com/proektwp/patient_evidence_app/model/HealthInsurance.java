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

    @OneToOne(mappedBy = "healthInsurance")
    public Patient patient;

    public HealthInsurance(){}


}
