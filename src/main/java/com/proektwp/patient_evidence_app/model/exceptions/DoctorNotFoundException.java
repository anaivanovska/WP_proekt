package com.proektwp.patient_evidence_app.model.exceptions;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(String doctorID){
        super("Doctor with id "+doctorID+" not found");
    }
}
