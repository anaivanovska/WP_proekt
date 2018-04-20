package com.proektwp.patient_evidence_app.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VaccineID implements Serializable{
    String name;
    String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }
     public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
