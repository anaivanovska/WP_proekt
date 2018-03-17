package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {


    public Patient findPatientByID(String patientID);
    public HealthInsurance findHealthInsuranceForPatient(String insuranceID);
    public Page<HealthExamination> findHealthExaminationsForPatient(String patientID);
    public List<Vaccine>  findVaccinesForPatient(String patientID);
    public FamilyDoctor findFamilyDoctor(String familyDoctorID);
    public Patient addNewPatient(String patientID, String firstName, String lastName, String password);
    public Patient deletePatient(String patientID);
    public Patient updatePatient(String patientID);

}
