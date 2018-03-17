package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.exceptions.HealthInsuranceNotFoundException;
import com.proektwp.patient_evidence_app.model.exceptions.PatientExistsException;
import com.proektwp.patient_evidence_app.model.exceptions.PatientNotFoundException;
import com.proektwp.patient_evidence_app.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService implements com.proektwp.patient_evidence_app.service.PatientService {

    private FamilyDoctorRepository familyDoctorRepository;
    private PatientRepository patientRepository;
    private HealthInsuranceRepository healthInsuranceRepository;
    private HealthExaminationRepository healthExaminationRepository;
    private VaccineRepository vaccineRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientService(FamilyDoctorRepository familyDoctorRepository, PatientRepository patientRepository, HealthInsuranceRepository healthInsuranceRepository, HealthExaminationRepository healthExaminationRepository, VaccineRepository vaccineRepository) {
        this.patientRepository = patientRepository;
        this.healthInsuranceRepository = healthInsuranceRepository;
        this.healthExaminationRepository = healthExaminationRepository;
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Patient findPatientByID(String patientID) {
        Patient patient = this.patientRepository.findOne(patientID);
        if(patient == null){
            throw new PatientNotFoundException(patientID);
        }
        return patient;
    }

    @Override
    @Transactional(readOnly = true)
    public HealthInsurance findHealthInsuranceForPatient(String insuranceID) {
        HealthInsurance healthInsurance = this.healthInsuranceRepository.findOne(insuranceID);
        if(healthInsurance == null){
            throw new HealthInsuranceNotFoundException(insuranceID);
        }
        return healthInsurance;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HealthExamination> findHealthExaminationsForPatient(String patientID) {
        Patient patient = this.findPatientByID(patientID);
        Pageable pageable= new PageRequest(0,10, Sort.Direction.DESC, "dateOfExamination");
        return this.healthExaminationRepository.findByPatient(patient,  pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vaccine> findVaccinesForPatient(String patientID) {
        Patient patient = this.findPatientByID(patientID);
        return this.vaccineRepository.findByPatient(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public FamilyDoctor findFamilyDoctor(String familyDoctorID) {
        return this.familyDoctorRepository.findOne(familyDoctorID);
    }

    @Override
    @Transactional
    public Patient addNewPatient(String patientID, String firstName, String lastName, String password) throws PatientExistsException {
        Patient patient = this.patientRepository.findOne(patientID);
        if(patient  != null){
            throw new PatientExistsException(patientID);
        }
        String encodedPass = this.bCryptPasswordEncoder.encode(password);
        patient = new Patient(patientID, firstName, lastName, encodedPass);
        return this.patientRepository.save(patient);
    }

    @Override
    @Transactional
    public Patient deletePatient(String patientID) {
        Patient patient = this.findPatientByID(patientID);
        this.patientRepository.delete(patientID);
        return patient;
    }

    @Override
    @Transactional
    public Patient updatePatient(String patientID) {
        return null;
    }

}
