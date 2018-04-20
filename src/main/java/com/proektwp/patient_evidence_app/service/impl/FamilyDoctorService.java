package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.model.exceptions.DoctorExsistsException;
import com.proektwp.patient_evidence_app.model.exceptions.DoctorNotFoundException;
import com.proektwp.patient_evidence_app.model.exceptions.PatientNotFoundException;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public FamilyDoctorService(FamilyDoctorRepository familyDoctorRepository, PatientRepository patientRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.familyDoctorRepository =  familyDoctorRepository;
        this.patientRepository = patientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public FamilyDoctor findFamilyDoctorById(String doctorID) {
        FamilyDoctor familyDoctor = this.familyDoctorRepository.findOne(doctorID);
        if(familyDoctor== null){
            throw new PatientNotFoundException(doctorID);
        }
        return familyDoctor;
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
    public FamilyDoctor addNewFamilyDoctor(String doctorID, String firstName, String lastName, String password, String email, String phoneNumber, String address, Boolean agreement_with_FZO, String speciality, String workTime, String deputyFamilyDoctorID){
        FamilyDoctor familyDoctor = this.familyDoctorRepository.findOne(doctorID);
        if(familyDoctor != null){
            throw new DoctorExsistsException(doctorID);
        }
        FamilyDoctor deputyFamilyDoctor = this.findFamilyDoctorById(deputyFamilyDoctorID);
        System.out.println("deputy ID: "+ deputyFamilyDoctor.userId);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(password);
        familyDoctor = new FamilyDoctor(doctorID, firstName, lastName, encryptedPassword, email, phoneNumber, address,agreement_with_FZO, speciality, workTime, deputyFamilyDoctor);
        return familyDoctorRepository.save(familyDoctor);
    }

    @Override
    @Transactional
    public FamilyDoctor deleteFamilyDoctor(String doctorID) {
        FamilyDoctor familyDoctor = this.findFamilyDoctorById(doctorID);
        FamilyDoctor deputyFamilyDoctor = familyDoctor.deputyFamilyDoctor;
        //updatePatients(familyDoctor, deputyFamilyDoctor);
        System.out.println("before delete");
        this.familyDoctorRepository.delete(familyDoctor);
        System.out.println("after delete");
        //updateWhereDoctorISDeputy(familyDoctor, deputyFamilyDoctor);
        return familyDoctor;
     }

    @Override
    @Transactional
    public FamilyDoctor updateFamilyDoctor(String doctorID, String firstName, String lastName, String password, String email, String phoneNumber, String address, Boolean agreement_with_FZO, String speciality, String workTime, String deputyFamilyDoctorID) {
        FamilyDoctor deputyFamilyDoctor = this.findFamilyDoctorById(deputyFamilyDoctorID);
        FamilyDoctor updatedFamilyDoctor = this.findFamilyDoctorById(doctorID);
            updatedFamilyDoctor.firstName = firstName;
            updatedFamilyDoctor.lastName = lastName;
            updatedFamilyDoctor.password = this.bCryptPasswordEncoder.encode(password);
            updatedFamilyDoctor.email = email;
            updatedFamilyDoctor.phoneNumber = phoneNumber;
            updatedFamilyDoctor.address=address;
            updatedFamilyDoctor.agreement_with_FZO=agreement_with_FZO;
            updatedFamilyDoctor.speciality=speciality;
            updatedFamilyDoctor.workTime = workTime;
            updatedFamilyDoctor.deputyFamilyDoctor = deputyFamilyDoctor;
        return this.familyDoctorRepository.save(updatedFamilyDoctor);

    }

    private void updatePatients(FamilyDoctor familyDoctor, FamilyDoctor deputyFamilyDoctor){
        List<Patient> patients = this.patientRepository.findAllByFamilyDoctor(familyDoctor);
        for(Patient patient : patients){
            //patient.familyDoctor = deputyFamilyDoctor;
            //this.patientRepository.save(patient);
            System.out.println("PatientID: "+patient.userId +"FD ID: "+ patient.familyDoctor.userId);
        }
    }

    private void updateWhereDoctorISDeputy(FamilyDoctor familyDoctor, FamilyDoctor deputyFamilyDoctor){
        List<FamilyDoctor> doctors = this.familyDoctorRepository.findAllByDeputyFamilyDoctor(familyDoctor);
        /*for(FamilyDoctor doctor: doctors){
            System.out.println("Doctor id: "+ doctor.userId +" DFD: "+doctor.deputyFamilyDoctor.userId);
        }*/
    }
}

