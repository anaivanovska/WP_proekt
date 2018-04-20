package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.exceptions.DoctorNotFoundException;
import com.proektwp.patient_evidence_app.model.exceptions.PatientExistsException;
import com.proektwp.patient_evidence_app.model.exceptions.PatientNotFoundException;
import com.proektwp.patient_evidence_app.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class PatientService implements com.proektwp.patient_evidence_app.service.PatientService {

    private FamilyDoctorRepository familyDoctorRepository;
    private PatientRepository patientRepository;
    private HealthExaminationRepository healthExaminationRepository;
    private VaccineRepository vaccineRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PatientService(PatientRepository patientRepository,FamilyDoctorRepository familyDoctorRepository, HealthExaminationRepository healthExaminationRepository, VaccineRepository vaccineRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.patientRepository = patientRepository;
        this.familyDoctorRepository = familyDoctorRepository;
        this.healthExaminationRepository = healthExaminationRepository;
        this.vaccineRepository = vaccineRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        return this.vaccineRepository.findByPatient(patient);
    }

    @Override
    @Transactional
    public Patient addNewPatient(String patientID, String firstName, String lastName, String password,String email , String phoneNumber, String address, Date dateOfBirth, String embg, String gender, String proffesion, String marrigeState, String doctorID) throws PatientExistsException {
        Patient patient = this.patientRepository.findOne(patientID);

        if(patient  != null){
            throw new PatientExistsException(patient.userId);
        }

        FamilyDoctor familyDoctor = this.familyDoctorRepository.findOne(doctorID);

        if(familyDoctor == null){
            throw new DoctorNotFoundException(doctorID);
        }

        String encryptedPassword = this.bCryptPasswordEncoder.encode(password);
        patient = new Patient(patientID, firstName, lastName, encryptedPassword, email, phoneNumber, address, dateOfBirth, embg, gender, proffesion, marrigeState, familyDoctor );
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
    public Patient updatePatient(String patientID, String firstName, String lastName, String password,String email , String phoneNumber, String address, Date dateOfBirth, String embg, String gender, String proffesion, String marrigeState, String doctorID){
        Patient updatedPatient = this.findPatientByID(patientID);

            updatedPatient.firstName=firstName;
            updatedPatient.lastName=lastName;
            updatedPatient.password = this.bCryptPasswordEncoder.encode(password);
            updatedPatient.email=email;
            updatedPatient.phoneNumber = phoneNumber;
            updatedPatient.address=address;
            updatedPatient.dateOfBirth = dateOfBirth;
            updatedPatient.gender = gender;
            updatedPatient.embg=embg;
            updatedPatient.proffesion = proffesion;
            updatedPatient.marrigeState = marrigeState;
            updatedPatient.familyDoctor = this.familyDoctorRepository.findOne(doctorID);
        return this.patientRepository.save(updatedPatient);
    }

}
