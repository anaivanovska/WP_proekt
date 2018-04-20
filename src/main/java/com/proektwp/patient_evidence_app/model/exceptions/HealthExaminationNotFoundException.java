package com.proektwp.patient_evidence_app.model.exceptions;

import java.util.*;

public class HealthExaminationNotFoundException extends  RuntimeException{
    public HealthExaminationNotFoundException(String userId, Date dateOfExamination){
        super("Health examination on "+ dateOfExamination + " for user with ID: "+ userId +" not found");
    }
}
