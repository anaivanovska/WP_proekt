package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Secured("ROLE_USER")
@RequestMapping(value = "patient")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }


    @GetMapping(value="{patientID}")
    public String findPatientWihtID(@PathVariable String patientID){
        return "patient "+ patientID;
    }


}
