package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.model.HealthExaminationID;
import com.proektwp.patient_evidence_app.model.Medicine;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.persistence.HealthExaminationRepository;
import com.proektwp.patient_evidence_app.persistence.MedicineRepository;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class HealthExaminationService implements com.proektwp.patient_evidence_app.service.HealthExaminationService{
    private HealthExaminationRepository healthExaminationRepository;
    private MedicineRepository medicineRepository;
    private PatientRepository patientRepository;

    @Autowired
    public HealthExaminationService(HealthExaminationRepository healthExaminationRepository, MedicineRepository medicineRepository, PatientRepository patientRepository) {
        this.healthExaminationRepository = healthExaminationRepository;
        this.medicineRepository = medicineRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public HealthExamination findHealthExamination(Date examinationID, String patientID) {
        HealthExaminationID healthExaminationID = new HealthExaminationID();
        healthExaminationID.setDateOfExamination(examinationID);
        healthExaminationID.setUserId(patientID);
        return this.healthExaminationRepository.findOne(healthExaminationID);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findMedicineForExamination(Date examinationID) {
        HealthExaminationID healthExaminationID= new HealthExaminationID();
        healthExaminationID.setDateOfExamination(examinationID);
        HealthExamination healthExamination = this.healthExaminationRepository.findOne(healthExaminationID);
        return this.medicineRepository.findByHealthExamination(healthExamination);
    }

    @Override
    @Transactional
    public HealthExamination addNewExamination(Date date, String diagnosisAtMKB, Boolean laboratory_Finding, Boolean RTC_Finding, Boolean EHO_finding, Boolean computed_Tomography, Boolean magnetic_Resonance, Boolean sent_To_A_specialist, Boolean illness, Boolean bandage, Boolean antibody_Prophylaxis,String typeOfTherapy, String patientID) {
        HealthExaminationID healthExaminationID = new HealthExaminationID();
        healthExaminationID.setDateOfExamination(date);
        healthExaminationID.setUserId(patientID);
        Patient patient = this.patientRepository.findOne(patientID);
        HealthExamination healthExamination =this.healthExaminationRepository.findOne(healthExaminationID);
        if(healthExamination != null){
            System.out.println("POSTOI");
            System.out.println("POSTOI");
        }else{
            System.out.println("NULL");
        }

        System.out.println("pred");
        healthExamination = new HealthExamination(healthExaminationID, diagnosisAtMKB, laboratory_Finding, RTC_Finding, EHO_finding,computed_Tomography, magnetic_Resonance, sent_To_A_specialist, illness, bandage, antibody_Prophylaxis, typeOfTherapy, patient);
        System.out.println("potoa");

        return this.healthExaminationRepository.save(healthExamination);
    }

    @Override
    @Transactional
    public HealthExamination updateExamination(Date date, String diagnosisAtMKB, Boolean laboratory_Finding, Boolean RTC_Finding, Boolean EHO_finding, Boolean computed_Tomography, Boolean magnetic_Resonance, Boolean sent_To_A_specialist, Boolean illness, Boolean bandage, Boolean antibody_Prophylaxis,String typeOfTherapy, String patientID) {
        HealthExaminationID healthExaminationID = new HealthExaminationID();
        healthExaminationID.setDateOfExamination(date);
        healthExaminationID.setUserId(patientID);
        HealthExamination healthExamination =this.healthExaminationRepository.findOne(healthExaminationID);
        if(healthExamination == null){
            System.out.println("ne postoi");
        }
        healthExamination.diagnosisAtMKB = diagnosisAtMKB;
        return this.healthExaminationRepository.save(healthExamination);
    }


}
