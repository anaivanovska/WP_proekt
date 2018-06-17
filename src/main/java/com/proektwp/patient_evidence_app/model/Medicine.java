package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table( name = "medicine")
public class Medicine {

    @JsonIgnoreProperties("examinationID")
    @EmbeddedId
    public MedicineID medicineID;

    public int quantity;

    public String typeOfReception;


    @JsonIgnore
    @MapsId("examinationID")
    @ManyToOne
    public HealthExamination healthExamination;

    public Medicine(){}

    public Medicine(String name, int quantity, String typeOfReception, HealthExamination healthExamination) {
        this.medicineID = new MedicineID();
        this.medicineID.setName(name);
        this.medicineID.setExaminationID(healthExamination.examinationID);
        this.quantity = quantity;
        this.typeOfReception = typeOfReception;
        this.healthExamination = healthExamination;
    }
}