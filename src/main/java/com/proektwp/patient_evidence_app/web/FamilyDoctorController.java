package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.service.impl.FamilyDoctorService;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = "familyDoctor")
public class FamilyDoctorController {
    private FamilyDoctorService familyDoctorService;

    public FamilyDoctorController(FamilyDoctorService familyDoctorService) {
        this.familyDoctorService = familyDoctorService;
    }


    @RequestMapping(value = "{doctor_id}", method = RequestMethod.GET)
    public String findFamilyDoctor(@PathVariable(value = "doctor_id") String doctor_id) {
        return "familyDoctorSuccess "+ doctor_id;
    }


}