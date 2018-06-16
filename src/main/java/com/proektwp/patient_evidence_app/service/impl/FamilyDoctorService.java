package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.DTO.FamilyDoctorDTO;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.model.exceptions.DoctorExsistsException;
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
import java.util.stream.Collectors;

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
        System.out.println(familyDoctor.firstName);
        return familyDoctor;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllFamilyDoctorsID() {
        return this.familyDoctorRepository.findAll().stream()
                                                    .map(familyDoctor -> familyDoctor.userId)
                                                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Patient> findAllPatients(String doctorID) {
        FamilyDoctor familyDoctor = this.findFamilyDoctorById(doctorID);
        Pageable pageable= new PageRequest(0,5, Sort.Direction.ASC, "lastName");
        return this.patientRepository.findAllByFamilyDoctor(familyDoctor, pageable);
    }

    @Override
    @Transactional
    public FamilyDoctor addNewFamilyDoctor(FamilyDoctorDTO familyDoctorDTO){
        FamilyDoctor familyDoctor = this.familyDoctorRepository.findOne(familyDoctorDTO.getUserId());
        if(familyDoctor != null){
            throw new DoctorExsistsException(familyDoctorDTO.getUserId());
        }
        FamilyDoctor deputyFamilyDoctor = this.findFamilyDoctorById(familyDoctorDTO.getDeputyFamilyDoctorID());
        String encryptedPassword = this.bCryptPasswordEncoder.encode(familyDoctorDTO.getPassword());
        familyDoctor = new FamilyDoctor(familyDoctorDTO.getUserId(), familyDoctorDTO.getFirstName(), familyDoctorDTO.getLastName(), encryptedPassword, familyDoctorDTO.getEmail(), familyDoctorDTO.getPhoneNumber(), familyDoctorDTO.getAddress(),familyDoctorDTO.getAgreement_with_FZO(), familyDoctorDTO.getSpeciality(), familyDoctorDTO.getWorkTime(), deputyFamilyDoctor);
        return familyDoctorRepository.save(familyDoctor);
    }

    @Override
    @Transactional
    public FamilyDoctor deleteFamilyDoctor(String doctorID) {
        FamilyDoctor familyDoctor = this.findFamilyDoctorById(doctorID);
        FamilyDoctor deputyFamilyDoctor = familyDoctor.deputyFamilyDoctor;
        this.familyDoctorRepository.delete(familyDoctor);
        //updateWhereDoctorISDeputy(familyDoctor, deputyFamilyDoctor);
        return familyDoctor;
     }

    @Override
    @Transactional
    public FamilyDoctor updateFamilyDoctor(FamilyDoctorDTO familyDoctorDTO) {
        FamilyDoctor updatedFamilyDoctor = this.findFamilyDoctorById(familyDoctorDTO.getUserId());
         //FirstName
        if(!updatedFamilyDoctor.firstName.equals(familyDoctorDTO.getFirstName()) && familyDoctorDTO.getFirstName().trim().length() != 0){
            updatedFamilyDoctor.firstName = familyDoctorDTO.getFirstName();
        }
        //Lastname
        if(!updatedFamilyDoctor.lastName.equals(familyDoctorDTO.getLastName()) && familyDoctorDTO.getLastName().trim().length() != 0){
            updatedFamilyDoctor.lastName = familyDoctorDTO.getLastName();
        }
        //Email
        if(!updatedFamilyDoctor.email.equals(familyDoctorDTO.getEmail()) && familyDoctorDTO.getEmail().trim().length() != 0){
            updatedFamilyDoctor.email = familyDoctorDTO.getEmail();
        }
        //PhoneNumber
        if(!updatedFamilyDoctor.phoneNumber.equals(familyDoctorDTO.getPhoneNumber()) && familyDoctorDTO.getPhoneNumber().trim().length() != 0){
            updatedFamilyDoctor.phoneNumber = familyDoctorDTO.getPhoneNumber();
        }
        //Address
        if(!updatedFamilyDoctor.address.equals(familyDoctorDTO.getAddress()) && familyDoctorDTO.getAddress().trim().length() != 0){
            updatedFamilyDoctor.address = familyDoctorDTO.getAddress();
        }
       //Agreement with FZO
        if(!updatedFamilyDoctor.agreement_with_FZO.equals(familyDoctorDTO.getAgreement_with_FZO())){
            updatedFamilyDoctor.agreement_with_FZO = familyDoctorDTO.getAgreement_with_FZO();
        }
        //Specialty
        if(!updatedFamilyDoctor.speciality.equals(familyDoctorDTO.getSpeciality()) && familyDoctorDTO.getSpeciality().trim().length() != 0){
            updatedFamilyDoctor.speciality = familyDoctorDTO.getSpeciality();
        }
        //Work time
        if(!updatedFamilyDoctor.workTime.equals(familyDoctorDTO.getWorkTime()) && familyDoctorDTO.getWorkTime().trim().length() != 0){
            updatedFamilyDoctor.workTime = familyDoctorDTO.getWorkTime();
        }
        //Deputy
        if(!updatedFamilyDoctor.deputyFamilyDoctor.userId.equals(familyDoctorDTO.getDeputyFamilyDoctorID()) && familyDoctorDTO.getDeputyFamilyDoctorID().trim().length() != 0){
            FamilyDoctor newDeputyFamilyDoctor = this.findFamilyDoctorById(familyDoctorDTO.getDeputyFamilyDoctorID());
            updatedFamilyDoctor.deputyFamilyDoctor = newDeputyFamilyDoctor;
        }

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

