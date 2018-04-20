package com.proektwp.patient_evidence_app.model.exceptions;

public class VaccineNotFoundException extends RuntimeException {
    public VaccineNotFoundException(String name, String userId) {
        super("Vaccine "+ name + " for user with ID: "+userId+ " not found");
    }
}
