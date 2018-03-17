package com.proektwp.patient_evidence_app.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VaccineID implements Serializable{
    String name;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    String userId;

    public void setName(String name) {
        this.name = name;
    }
}
