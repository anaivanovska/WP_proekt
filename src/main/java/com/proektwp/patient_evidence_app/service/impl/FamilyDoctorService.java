package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.model.exceptions.DoctorExsistsException;
import com.proektwp.patient_evidence_app.model.exceptions.DoctorNotFoundException;
import com.proektwp.patient_evidence_app.persistence.FamilyDoctorRepository;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
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
public class FamilyDoctorService implements com.proektwp.patient_evidence_app.service.FamilyDoctorService {


    private FamilyDoctorRepository familyDoctorRepository;
    private PatientRepository patientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public FamilyDoctorService(FamilyDoctorRepository familyDoctorRepository, PatientRepository patientRepository){
        this.familyDoctorRepository =  familyDoctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public FamilyDoctor findFamilyDoctorById(String doctorID) {
        FamilyDoctor familyDoctor = this.familyDoctorRepository.findOne(doctorID);
        if(familyDoctor == null)
        {
            throw new DoctorNotFoundException(doctorID);
        }
        return familyDoctor;
    }

    @Override
    @Transactional(readOnly = true)
    public FamilyDoctor findDeputyFamilyDoctorById(String deputy_doctorID) {
        return this.findFamilyDoctorById(deputy_doctorID);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Patient> findAllPatients(String doctorID) {
        FamilyDoctor familyDoctor = this.findFamilyDoctorById(doctorID);
        Pageable pageable= new PageRequest(0,10, Sort.Direction.ASC, "lastName");
        return this.patientRepository.findAllByFamilyDoctor(familyDoctor, pageable);
    }

    @Override
    @Transactional
    public FamilyDoctor addNewFamilyDoctor(String doctorID, String firstName, String lastName, String password) {
        FamilyDoctor familyDoctor = this.familyDoctorRepository.findOne(doctorID);
        if(familyDoctor != null){
            throw new DoctorExsistsException(doctorID);
        }
        String encodedPass = this.bCryptPasswordEncoder.encode(password);
        familyDoctor = new FamilyDoctor(doctorID, firstName,lastName,encodedPass);
        familyDoctorRepository.save(familyDoctor);
        return familyDoctor;
    }

    @Override
    @Transactional
    public FamilyDoctor deleteFamilyDoctor(String doctorID) {
        FamilyDoctor familyDoctor = this.findFamilyDoctorById(doctorID);
        if( familyDoctor == null){
            throw new DoctorNotFoundException(doctorID);
        }
        this.familyDoctorRepository.delete(familyDoctor);
        return familyDoctor;
     }

    @Override
    @Transactional
    public FamilyDoctor updateFamilyDoctor(String doctorID) {
        return null;
    }

}
