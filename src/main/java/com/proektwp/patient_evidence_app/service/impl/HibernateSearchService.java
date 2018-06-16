package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.persistence.hibernateSearch.HibernateSearchRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HibernateSearchService implements com.proektwp.patient_evidence_app.service.HibernateSearchService {

    private final HibernateSearchRepositoryImpl searchRepository;

    @Autowired
    public HibernateSearchService(HibernateSearchRepositoryImpl searchRepository) {
        this.searchRepository = searchRepository;
    }


    private List<Patient> getAllMatchingResults(String keyword) {
        return this.searchRepository.searchPhrase(Patient.class, keyword,  "userId", "firstName", "lastName");
    }

    @Override
    public List<Patient> getAllPatients(String keyword, String doctorID) {
        List<Patient> patients = this.getAllMatchingResults(keyword);

        return patients.stream()
                       .filter(patient -> patient.familyDoctor.userId.equals(doctorID))
                       .collect(Collectors.toList());
    }
}
