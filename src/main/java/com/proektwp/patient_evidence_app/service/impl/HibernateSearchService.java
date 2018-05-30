package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.persistence.hibernateSearch.HibernateSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HibernateSearchService implements com.proektwp.patient_evidence_app.service.HibernateSearchService {

    private final HibernateSearchRepository searchRepository;

    @Autowired
    public HibernateSearchService(HibernateSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }


    @Override
    public List<Patient> getAllMatchingResults(String keyword) {
        return this.searchRepository.searchKeyword(Patient.class, keyword,  "userId", "firstName", "lastName");
    }
}
