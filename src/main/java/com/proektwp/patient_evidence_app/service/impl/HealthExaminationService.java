package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.model.HealthExaminationID;
import com.proektwp.patient_evidence_app.model.Medicine;
import com.proektwp.patient_evidence_app.model.exceptions.HealthExaminationExistsException;
import com.proektwp.patient_evidence_app.model.exceptions.HealthExaminationNotFoundException;
import com.proektwp.patient_evidence_app.persistence.HealthExaminationRepository;
import com.proektwp.patient_evidence_app.persistence.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;
import java.util.Date;
import java.util.List;

@Service
public class HealthExaminationService implements com.proektwp.patient_evidence_app.service.HealthExaminationService{
    private HealthExaminationRepository healthExaminationRepository;
    private MedicineRepository medicineRepository;

    @Autowired
    public HealthExaminationService(HealthExaminationRepository healthExaminationRepository, MedicineRepository medicineRepository) {
        this.healthExaminationRepository = healthExaminationRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public HealthExamination findHealthExamination(HealthExaminationID healthExaminationID){
        HealthExamination healthExamination = this.healthExaminationRepository.findOne(healthExaminationID);
        if(healthExamination == null){
            throw  new HealthExaminationNotFoundException(healthExaminationID. getUserId(), healthExaminationID.getDateOfExamination());
        }
        return healthExamination;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findMedicineForExamination(HealthExaminationID healthExaminationID) {
        HealthExamination healthExamination= this.findHealthExamination(healthExaminationID);
        return this.medicineRepository.findByHealthExamination(healthExamination);
    }

    @Override
    @Transactional
    public HealthExamination addNewExamination(HealthExamination newHealthExamination) {
        HealthExamination healthExamination = this.healthExaminationRepository.findOne(newHealthExamination.examinationID);
        if(healthExamination != null){
            throw new HealthExaminationExistsException(healthExamination.examinationID.getDateOfExamination(), healthExamination.examinationID.getUserId());
        }

        return this.healthExaminationRepository.save(newHealthExamination);
    }

    @Override
    @Transactional
    public HealthExamination updateExamination(HealthExamination healthExamination ) {
        HealthExamination updatedHealthExamination =this.findHealthExamination(healthExamination.examinationID);
            updatedHealthExamination.diagnosisAtMKB = healthExamination.diagnosisAtMKB;
            updatedHealthExamination.antibody_Prophylaxis = healthExamination.antibody_Prophylaxis;
            updatedHealthExamination.bandage = healthExamination.bandage;
            updatedHealthExamination.computed_Tomography = healthExamination.computed_Tomography;
            updatedHealthExamination.EHO_Finding = healthExamination.EHO_Finding;
            updatedHealthExamination.illness = healthExamination.illness;
            updatedHealthExamination.laboratory_Finding = healthExamination.laboratory_Finding;
            updatedHealthExamination.magnetic_Resonance = healthExamination.magnetic_Resonance;
            updatedHealthExamination.RTC_Finding = healthExamination.RTC_Finding;
            updatedHealthExamination.sent_To_A_specialist = healthExamination.sent_To_A_specialist;
            updatedHealthExamination.typeOfTherapy = healthExamination.typeOfTherapy;
        return this.healthExaminationRepository.save(updatedHealthExamination);
    }

    @Override
    @Transactional
    public HealthExamination deleteExamination(HealthExaminationID healthExaminationID) {
        HealthExamination healthExamination = this.findHealthExamination(healthExaminationID);
        this.healthExaminationRepository.delete(healthExaminationID);
        return healthExamination;
    }


}
