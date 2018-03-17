package com.proektwp.patient_evidence_app.model.exceptions;


public class HealthInsuranceNotFoundException extends RuntimeException {
    public HealthInsuranceNotFoundException(String insuranceID){
        super("HealthInsurance with ID: "+insuranceID+" NOT FOUND");
    }
}
