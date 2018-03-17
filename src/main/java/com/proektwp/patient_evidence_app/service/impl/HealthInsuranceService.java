package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.HealthInsurance;
import com.proektwp.patient_evidence_app.persistence.HealthInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HealthInsuranceService implements com.proektwp.patient_evidence_app.service.HealthInsuranceService{

    private HealthInsuranceRepository healthInsuranceRepository;

    @Autowired
    public HealthInsuranceService(HealthInsuranceRepository healthInsuranceRepository){
        this.healthInsuranceRepository = healthInsuranceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public HealthInsurance findHealthInsuranceByID(String insurance_id) {
        return healthInsuranceRepository.findOne(insurance_id);
    }

    @Override
    @Transactional
    public HealthInsurance addHealthInsurance(String healthLegitimationNumber, String registrationNumber, String activityID, String typeOfHealthProtection, String patientID) {
        return null;
    }

    @Override
    @Transactional
    public HealthInsurance updateHealthInsurance(String healthLegitimationNumber, String registrationNumber, String activityID, String typeOfHealthProtection, String patientID) {
        return null;
    }
}
