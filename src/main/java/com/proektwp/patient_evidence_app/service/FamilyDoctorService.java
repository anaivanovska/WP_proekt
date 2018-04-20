package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.Patient;
import org.springframework.data.domain.Page;

public interface FamilyDoctorService {

    public FamilyDoctor findFamilyDoctorById(String doctorID);
    public Page<Patient> findAllPatients(String doctorID);
    public FamilyDoctor addNewFamilyDoctor(String doctorID, String firstName, String lastName, String password, String email, String phoneNumber, String address, Boolean agreement_with_FZO, String speciality, String workTime, String deputyFamilyDoctorID);
    public FamilyDoctor deleteFamilyDoctor(String doctorID);
    public FamilyDoctor updateFamilyDoctor(String doctorID, String firstName, String lastName, String password, String email, String phoneNumber, String address, Boolean agreement_with_FZO, String speciality, String workTime, String deputyFamilyDoctorID);
}
