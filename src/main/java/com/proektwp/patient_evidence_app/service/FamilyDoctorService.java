package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.DTO.FamilyDoctorDTO;
import com.proektwp.patient_evidence_app.model.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FamilyDoctorService {

    public FamilyDoctor findFamilyDoctorById(String doctorID);
    public List<String> findAllFamilyDoctorsID();
    public Page<Patient> findAllPatients(String doctorID);
    public FamilyDoctor addNewFamilyDoctor(FamilyDoctorDTO familyDoctorDTO);
    public FamilyDoctor deleteFamilyDoctor(String doctorID);
    public FamilyDoctor updateFamilyDoctor(FamilyDoctorDTO familyDoctorDTO);
}
