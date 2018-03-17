package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.service.impl.HealthExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured({"ROLE_DOCTOR", "ROLE_PATIENT"})
public class HealthExaminationController {

    private HealthExaminationService healthExaminationService;


    @Autowired
    public HealthExaminationController(HealthExaminationService healthExaminationService){
        this.healthExaminationService = healthExaminationService;
    }

}
