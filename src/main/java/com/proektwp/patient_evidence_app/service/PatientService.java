package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.*;
import org.springframework.data.domain.Page;

import java.util.*;

public interface PatientService {


    public Patient findPatientByID(String patientID);
    public Page<HealthExamination> findHealthExaminationsForPatient(String patientID);
    public List<Vaccine>  findVaccinesForPatient(String patientID);
    public Patient addNewPatient(String patientID, String firstName, String lastName, String password,String email , String phoneNumber, String address, Date dateOfBirth, String embg, String gender, String proffesion, String marrigeState, String doctorID);
    public Patient deletePatient(String patientID);
    public Patient updatePatient(String patientID, String firstName, String lastName, String password,String email , String phoneNumber, String address, Date dateOfBirth, String embg, String gender, String proffesion, String marrigeState, String doctorID);

}
