package com.proektwp.patient_evidence_app.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class MedicineID implements Serializable {

    @Column
    String name;

    @Embedded
    HealthExaminationID examinationID;


    public MedicineID(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HealthExaminationID getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(HealthExaminationID examinationID) {
        this.examinationID = examinationID;
    }
}
