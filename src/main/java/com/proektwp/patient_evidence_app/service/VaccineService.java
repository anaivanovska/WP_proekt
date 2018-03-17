package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.model.Vaccine;

import java.util.Date;
import java.util.List;

public interface VaccineService {
    public List<Vaccine> findAllVaccinesForPatient(String patientID);
    public Vaccine addNewVaccine(String name, Date date, String patientID );
    public Vaccine updateVaccine(String name, Date dateOfReceipt, String patientID);


}
