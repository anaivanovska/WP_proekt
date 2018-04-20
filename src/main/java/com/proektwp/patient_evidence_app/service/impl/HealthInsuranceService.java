package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.model.HealthInsurance;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.model.exceptions.HealhInsuranceExistsException;
import com.proektwp.patient_evidence_app.model.exceptions.HealthInsuranceNotFoundException;
import com.proektwp.patient_evidence_app.model.exceptions.PatientNotFoundException;
import com.proektwp.patient_evidence_app.persistence.HealthInsuranceRepository;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
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
    public HealthInsurance findHealthInsuranceByID(String healthLegitimationNumber) {
        HealthInsurance healthInsurance = this.healthInsuranceRepository.findOne(healthLegitimationNumber);
        if(healthInsurance == null){
            throw  new HealthInsuranceNotFoundException(healthLegitimationNumber);
        }

        return healthInsurance;
    }

    @Override
    @Transactional
    public HealthInsurance addHealthInsurance(HealthInsurance newHealthInsurance){
        HealthInsurance healthInsurance = this.healthInsuranceRepository.findOne(newHealthInsurance.healthLegitimationNumber);
        if(healthInsurance != null){
            throw new HealhInsuranceExistsException(healthInsurance.healthLegitimationNumber);
        }
        return this.healthInsuranceRepository.save(newHealthInsurance);
    }

    @Override
    @Transactional
    public HealthInsurance updateHealthInsurance(HealthInsurance healthInsurance) {
        HealthInsurance updatedHealthInsurance= this.findHealthInsuranceByID(healthInsurance.healthLegitimationNumber);
            updatedHealthInsurance.activityID = healthInsurance.activityID;
            updatedHealthInsurance.registrationNumber = healthInsurance.registrationNumber;
            updatedHealthInsurance.typeOfHealthProtection = healthInsurance.typeOfHealthProtection;
        return this.healthInsuranceRepository.save(updatedHealthInsurance);

    }

    @Override
    public HealthInsurance deleteHealthInsurance(String healthLegitimationNumber) {
        HealthInsurance healthInsurance = this.findHealthInsuranceByID(healthLegitimationNumber);
        this.healthInsuranceRepository.delete(healthLegitimationNumber);
        return healthInsurance;
    }
}
