package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FamilyDoctorService {

    public FamilyDoctor findFamilyDoctorById(String doctorID);
    public FamilyDoctor findDeputyFamilyDoctorById(String deputy_doctorID);
    public Page<Patient> findAllPatients(String doctorID);
    public FamilyDoctor addNewFamilyDoctor(String doctorID, String firstName, String lastName, String password);
    public FamilyDoctor deleteFamilyDoctor(String doctorID);
    public FamilyDoctor updateFamilyDoctor(String doctorID);
}
