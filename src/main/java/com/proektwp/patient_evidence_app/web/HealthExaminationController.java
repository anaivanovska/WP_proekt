package com.proektwp.patient_evidence_app.web;


import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.service.impl.HealthExaminationService;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value =  "/examination", produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthExaminationController {

    private HealthExaminationService healthExaminationService;
    private PatientService patientService;

    @Autowired
    public HealthExaminationController(HealthExaminationService healthExaminationService, PatientService patientService) {
        this.healthExaminationService = healthExaminationService;
        this.patientService = patientService;
    }

    @CrossOrigin
    @PostMapping(value = "/addNewExamination")
    public HealthExamination addNewExamination(@RequestParam(required = true) Date dateOfExamination,
                                               @RequestParam String diagnosisAtMKB,
                                               @RequestParam Boolean laboratory_Finding,
                                               @RequestParam Boolean RTC_Finding,
                                               @RequestParam Boolean EHO_Finding,
                                               @RequestParam Boolean computed_Tomography,
                                               @RequestParam Boolean magnetic_Resonance,
                                               @RequestParam Boolean sent_To_A_specialist,
                                               @RequestParam Boolean bandage,
                                               @RequestParam Boolean illness,
                                               @RequestParam Boolean antibody_Prophylaxis,
                                               @RequestParam String typeOfTherapy,
                                               @RequestParam(required = true) String patientID) {

        Patient patient = this.patientService.findPatientByID(patientID);
        HealthExamination healthExamination = new HealthExamination(dateOfExamination,
                diagnosisAtMKB,
                laboratory_Finding,
                RTC_Finding,
                EHO_Finding,
                computed_Tomography,
                magnetic_Resonance,
                sent_To_A_specialist,
                bandage, illness,
                antibody_Prophylaxis,
                typeOfTherapy,
                patient);
        return this.healthExaminationService.addNewExamination(healthExamination);
    }

    @CrossOrigin
    @PostMapping(value = "/{dateOfExamination}/{patientID}/update")
    public HealthExamination updateExamination(@PathVariable Date dateOfExamination,
                                               @RequestParam String diagnosisAtMKB,
                                               @RequestParam Boolean laboratory_Finding,
                                               @RequestParam Boolean RTC_Finding,
                                               @RequestParam Boolean EHO_Finding,
                                               @RequestParam Boolean computed_Tomography,
                                               @RequestParam Boolean magnetic_Resonance,
                                               @RequestParam Boolean sent_To_A_specialist,
                                               @RequestParam Boolean bandage,
                                               @RequestParam Boolean illness,
                                               @RequestParam Boolean antibody_Prophylaxis,
                                               @RequestParam String typeOfTherapy,
                                               @PathVariable String patientID) {

        return new HealthExamination();

    }

}