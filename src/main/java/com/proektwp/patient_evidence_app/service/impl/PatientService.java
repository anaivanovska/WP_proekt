package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.DTO.PatientDTO;
import com.proektwp.patient_evidence_app.model.exceptions.DoctorNotFoundException;
import com.proektwp.patient_evidence_app.model.exceptions.PatientExistsException;
import com.proektwp.patient_evidence_app.model.exceptions.PatientNotFoundException;
import com.proektwp.patient_evidence_app.persistence.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PatientService implements com.proektwp.patient_evidence_app.service.PatientService {

    private FamilyDoctorRepository familyDoctorRepository;
    private PatientRepository patientRepository;
    private HealthExaminationRepository healthExaminationRepository;
    private VaccineRepository vaccineRepository;
    private HealthInsuranceRepository healthInsuranceRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PatientService(PatientRepository patientRepository,FamilyDoctorRepository familyDoctorRepository, HealthExaminationRepository healthExaminationRepository, VaccineRepository vaccineRepository, HealthInsuranceRepository healthInsuranceRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.patientRepository = patientRepository;
        this.familyDoctorRepository = familyDoctorRepository;
        this.healthExaminationRepository = healthExaminationRepository;
        this.vaccineRepository = vaccineRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.healthInsuranceRepository = healthInsuranceRepository;
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
    public Page<HealthExamination> findHealthExaminationsForPatient(String patientID) {
        Patient patient = this.findPatientByID(patientID);
        Pageable pageable= new PageRequest(0,10);
        return this.healthExaminationRepository.findByPatient(patient,  pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vaccine> findVaccinesForPatient(String patientID) {
        Patient patient = this.findPatientByID(patientID);
        List<Vaccine> vaccines  = this.vaccineRepository.findByPatient(patient);
        return vaccines;

    }

    @Override
    @Transactional
    public Patient addNewPatient(PatientDTO patientDTO) throws PatientExistsException, ParseException {
        Patient patient = this.patientRepository.findOne(patientDTO.getUserId());

        if(patient  != null){
            throw new PatientExistsException(patient.userId);
        }

        FamilyDoctor familyDoctor = this.familyDoctorRepository.findOne(patientDTO.getFamilyDoctorID());

        if(familyDoctor == null){
            throw new DoctorNotFoundException(patientDTO.getFamilyDoctorID());
        }

        HealthInsurance patientInsurance = new HealthInsurance(patientDTO.getHealthLegitimationNumber(), patientDTO.getRegistrationNumber(), patientDTO.getActivityID(), patientDTO.getTypeOfHealthProtection());
        this.healthInsuranceRepository.save(patientInsurance);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(patientDTO.getPassword());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        patient = new Patient(patientDTO.getUserId(), patientDTO.getFirstName(), patientDTO.getLastName(), encryptedPassword, patientDTO.getEmail(), patientDTO.getPhoneNumber(), patientDTO.getAddress(), sdf.parse(patientDTO.getDateOfBirth()),  patientDTO.getGender(), patientDTO.getEmbg(), patientDTO.getProfession(), patientDTO.getMarriageState(),patientInsurance, familyDoctor );
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
    public Patient updatePatient(PatientDTO patientDTO) throws ParseException {
        Patient updatedPatient = this.findPatientByID(patientDTO.getUserId());

            updatedPatient.firstName=patientDTO.getFirstName();
            updatedPatient.lastName=patientDTO.getLastName();
            updatedPatient.email=patientDTO.getEmail();
            updatedPatient.phoneNumber = patientDTO.getPhoneNumber();
            updatedPatient.address=patientDTO.getAddress();
            updatedPatient.dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(patientDTO.getDateOfBirth());
            updatedPatient.profession = patientDTO.getProfession();
            updatedPatient.marriageState = patientDTO.getMarriageState();
        return this.patientRepository.save(updatedPatient);
    }


}
