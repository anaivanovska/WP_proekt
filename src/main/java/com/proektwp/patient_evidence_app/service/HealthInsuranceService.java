package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.HealthInsurance;

public interface HealthInsuranceService {

    public HealthInsurance findHealthInsuranceByID(String insurance_id);
    public HealthInsurance addHealthInsurance(String healthLegitimationNumber, String registrationNumber, String activityID, String typeOfHealthProtection, String patientID);
    public HealthInsurance updateHealthInsurance(String healthLegitimationNumber, String registrationNumber, String activityID, String typeOfHealthProtection, String patientID);

}
