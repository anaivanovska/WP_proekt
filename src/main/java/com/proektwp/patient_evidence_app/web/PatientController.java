package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.service.impl.FamilyDoctorService;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
//@Secured({"ROLE_PATIENT", "ROLE_DOCTOR"})
@RequestMapping(value = "patient",produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    private PatientService patientService;
    private FamilyDoctorService familyDoctorService;

    @Autowired
    public PatientController(PatientService patientService, FamilyDoctorService familyDoctorService){
        this.patientService = patientService;
        this.familyDoctorService = familyDoctorService;
    }


    @CrossOrigin
    @PostMapping(value="/addNewPatient")
    public Patient addNewPatient(@RequestParam(required = true) String patientID, @RequestParam(required = true) String firstName,
                                 @RequestParam(required = true) String lastName, @RequestParam(required = true) String password,
                                 @RequestParam String email, @RequestParam String phoneNumber,
                                 @RequestParam String address, @RequestParam String dateOfBirth,
                                 @RequestParam String gender,@RequestParam String embg,
                                 @RequestParam String proffesion, @RequestParam String marrigeState,
                                 @RequestParam String doctorID, HttpServletResponse response
    ) throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return this.patientService.addNewPatient(patientID, firstName,
                                                 lastName, password,
                                                 email, phoneNumber,
                                                 address, sdf.parse(dateOfBirth),
                                                 gender, embg,
                                                 proffesion, marrigeState, doctorID);
    }

    @CrossOrigin
    @GetMapping(value="/{patientID}")
    public Patient findPatientWithID(@PathVariable String patientID){
        return this.patientService.findPatientByID(patientID);
    }

    @CrossOrigin
    @GetMapping(value = "/{patientID}/examinationList")
    public Page<HealthExamination> findHealthExaminationsForPatient(@PathVariable String patientID){
        return this.patientService.findHealthExaminationsForPatient(patientID);
    }


    @CrossOrigin
    @GetMapping(value = "/{patientID}/vaccines")
    public List<Vaccine> findVaccinesForPatient(@PathVariable String patientID){
        return this.patientService.findVaccinesForPatient(patientID);
    }





    @CrossOrigin
    @PostMapping(value = "/{patientID}/update")
    public Patient updatePatient(@PathVariable String patientID, @RequestParam(required = true) String firstName,
                                 @RequestParam(required = true) String lastName, @RequestParam(required = true) String password,
                                 @RequestParam String email, @RequestParam String phoneNumber,
                                 @RequestParam String address, @RequestParam Date dateOfBirth,
                                 @RequestParam String gender, @RequestParam String embg,
                                 @RequestParam String proffesion, @RequestParam String marrigeState,
                                 @RequestParam String doctorID, HttpServletResponse response
    ) {
        return this.patientService.updatePatient(patientID, firstName, lastName, password, email, phoneNumber, address, dateOfBirth, embg, gender,proffesion, marrigeState,doctorID);
    }

    @CrossOrigin
    @DeleteMapping(value="/{patientID}/delete")
    public Patient deletePatient(@PathVariable String patientID){
        return this.patientService.deletePatient(patientID);
    }

}
