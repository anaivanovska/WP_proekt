package com.proektwp.patient_evidence_app.model;

import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class HealthExaminationID implements Serializable{

    @Temporal(TemporalType.DATE)
    Date dateOfExamination;
    @Column
    String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDateOfExamination(Date dateOfExamination) {
        this.dateOfExamination = dateOfExamination;
    }

    public Date getDateOfExamination() {
        return dateOfExamination;
    }

    public String getUserId() {
        return userId;
    }
}
