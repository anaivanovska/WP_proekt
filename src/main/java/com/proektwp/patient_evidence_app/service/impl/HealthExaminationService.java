package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.DTO.HealthExaminationDTO;
import com.proektwp.patient_evidence_app.model.DTO.MedicineDTO;
import com.proektwp.patient_evidence_app.model.exceptions.HealthExaminationExistsException;
import com.proektwp.patient_evidence_app.model.exceptions.HealthExaminationNotFoundException;
import com.proektwp.patient_evidence_app.persistence.HealthExaminationRepository;
import com.proektwp.patient_evidence_app.persistence.MedicineRepository;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public HealthExamination findHealthExamination(HealthExaminationID healthExaminationID){
        HealthExamination healthExamination = this.healthExaminationRepository.findOne(healthExaminationID);
        if(healthExamination == null){
            throw  new HealthExaminationNotFoundException(healthExaminationID. getUserId(), healthExaminationID.getDateOfExamination());
        }
        return healthExamination;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> findMedicinesForExamination(HealthExaminationID healthExaminationID) {
        HealthExamination healthExamination= this.findHealthExamination(healthExaminationID);
        return this.medicineRepository.findByHealthExamination(healthExamination);
    }

    @Transactional
    @Override
    public HealthExamination addNewExamination(HealthExaminationDTO examinationDTO, String userId) throws ParseException {
        HealthExaminationID examinationID = new HealthExaminationID();
        Date dateOfExamination = new SimpleDateFormat("yyyy-MM-dd").parse(examinationDTO.getDateOfExamination());

        examinationID.setUserId(userId);
        examinationID.setDateOfExamination(dateOfExamination);
        System.out.println("Examination set  ");

        HealthExamination examination = this.healthExaminationRepository.findOne(examinationID);
        if(examination != null){
            return null;
        }
            Patient patient = this.patientRepository.findOne(userId);
        System.out.println(examinationDTO.getRtc_Finding());

        examination = new HealthExamination(dateOfExamination,examinationDTO.getDiagnosisAtMKB(),
                                                examinationDTO.getLaboratory_Finding(), examinationDTO.getRtc_Finding(),
                                                examinationDTO.getEho_Finding(), examinationDTO.getComputed_Tomography(),
                                                examinationDTO.getMagnetic_Resonance(), examinationDTO.getSent_To_A_specialist(),
                                                examinationDTO.getIllness(), examinationDTO.getBandage(), examinationDTO.getAntibody_Prophylaxis(),
                                                examinationDTO.getTypeOfTherapy(), patient);



        examination = this.healthExaminationRepository.save(examination);


        List<Medicine> savedMedicine = this.saveTherapyForExamination(examination, examinationDTO.getMedicines());
                    if(savedMedicine.size() != examinationDTO.getMedicines().size()){
                        return null;
                    }

        return  examination;


    }



    private List<Medicine> saveTherapyForExamination(HealthExamination examination, List<MedicineDTO> medicines){
        List<Medicine> savedMedicines = new ArrayList<>();
        medicines.forEach(medicine -> {
            System.out.println("medicine: "+ medicine.getName());
            MedicineID medicineID = new MedicineID();
            medicineID.setName(medicine.getName());
            medicineID.setExaminationID(examination.examinationID);
            System.out.println("Find medicine -> next");

            Medicine newMedicine = this.medicineRepository.findOne(medicineID);
            if(newMedicine == null){
                System.out.println("medicine e null ");
                newMedicine = new Medicine();
                newMedicine.medicineID = medicineID;
                newMedicine.quantity = medicine.getQuantity();
                newMedicine.typeOfReception = medicine.getTypeOfReception();
                newMedicine.healthExamination = examination;
                newMedicine = this.medicineRepository.save(newMedicine);
                System.out.println("Saved medicine: "+ newMedicine.quantity );
                savedMedicines.add(newMedicine);
            }
        });

        return savedMedicines;
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
