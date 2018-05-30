package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.DTO.PatientDTO;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.*;

public interface PatientService {


    public Patient findPatientByID(String patientID);
    public Page<HealthExamination> findHealthExaminationsForPatient(String patientID);
    public List<Vaccine>  findVaccinesForPatient(String patientID);
    public Patient addNewPatient(PatientDTO patientDTO) throws ParseException;
    public Patient deletePatient(String patientID);
    public Patient updatePatient(PatientDTO patientDTO) throws ParseException;
}
