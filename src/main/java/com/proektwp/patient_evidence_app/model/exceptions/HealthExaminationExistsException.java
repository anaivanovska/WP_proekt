package com.proektwp.patient_evidence_app.model.exceptions;
import java.util.Date;

public class HealthExaminationExistsException  extends RuntimeException{
    public HealthExaminationExistsException(Date dateOfExamination, String userId) {
        super("Examination on date "+ dateOfExamination.toString() +" for patient "+userId + " already exists");
    }
}
