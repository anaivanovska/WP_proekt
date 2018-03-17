package com.proektwp.patient_evidence_app.model.exceptions;

public class DoctorExsistsException extends RuntimeException {
    public  DoctorExsistsException(String doctorID){
        super("Doctor with "+doctorID+" already exists.");
    }
}
