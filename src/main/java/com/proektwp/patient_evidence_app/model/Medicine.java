package com.proektwp.patient_evidence_app.model;

import javax.persistence.*;

@Entity
@Table( name = "medicine")
public class Medicine {

    @Id
    public String name;

    public int quantity;

    public String typeOfReception;

    @ManyToOne
    public HealthExamination healthExamination;

    public Medicine(){}

    public Medicine(String name, int quantity, String typeOfReception, HealthExamination healthExamination) {
        this.name = name;
        this.quantity = quantity;
        this.typeOfReception = typeOfReception;
        this.healthExamination = healthExamination;
    }
}