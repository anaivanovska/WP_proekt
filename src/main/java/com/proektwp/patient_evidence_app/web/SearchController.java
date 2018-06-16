package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.service.impl.HibernateSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/search")
public class SearchController {

    private HibernateSearchService hibernateSearchService;

    @Autowired
    public SearchController(HibernateSearchService hibernateSearchService) {
        this.hibernateSearchService = hibernateSearchService;
    }



    @CrossOrigin
    @GetMapping("/patients/{keywords}/{doctorID}")
    public List<Patient> searchPatientsByPhrase(@PathVariable String keywords, @PathVariable String doctorID){
        return this.hibernateSearchService.getAllPatients(keywords, doctorID);
    }
}
