package com.proektwp.patient_evidence_app.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class HealthExaminationID implements Serializable{

    Date dateOfExamination;
    String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDateOfExamination(Date dateOfExamination) {
        this.dateOfExamination = dateOfExamination;
    }
}
