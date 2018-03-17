package com.proektwp.patient_evidence_app.model.exceptions;

public class PatientExistsException extends RuntimeException {
    public PatientExistsException(String patientID) {
        super("Patient with ID: "+ patientID +" already exists");
    }
}
