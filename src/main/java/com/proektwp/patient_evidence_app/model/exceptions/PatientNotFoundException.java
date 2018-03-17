package com.proektwp.patient_evidence_app.model.exceptions;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String patientID) {
        super("Patient with ID: "+patientID+" NOT FOUND");
    }
}
