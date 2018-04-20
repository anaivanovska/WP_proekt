package com.proektwp.patient_evidence_app.model.exceptions;

public class HealhInsuranceExistsException extends RuntimeException {
    public HealhInsuranceExistsException(String healthLegitimationNumber) {
        super("HealthInsurance with legitimationNumber: "+ healthLegitimationNumber + " exists");
    }
}
