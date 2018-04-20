package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.HealthInsurance;

public interface HealthInsuranceService {

    public HealthInsurance findHealthInsuranceByID(String healthLegitimationNumber);
    public HealthInsurance addHealthInsurance(HealthInsurance healthInsurance);
    public HealthInsurance updateHealthInsurance(HealthInsurance healthInsurance);
    public HealthInsurance deleteHealthInsurance(String healthLegitimationNumber);
}
