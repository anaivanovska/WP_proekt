package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.Patient;

import java.util.List;

public interface HibernateSearchService {

    public List<Patient> getAllPatients(String keyword, String doctorID);
}
