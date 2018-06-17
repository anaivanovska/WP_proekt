package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.DTO.HealthExaminationDTO;
import com.proektwp.patient_evidence_app.model.DTO.PatientDTO;
import com.proektwp.patient_evidence_app.security.CustomUserDetails;
import com.proektwp.patient_evidence_app.security.JwtTokenUtil;
import com.proektwp.patient_evidence_app.service.impl.HealthExaminationService;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
import com.proektwp.patient_evidence_app.service.impl.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RestController
//@Secured({"ROLE_PATIENT", "ROLE_DOCTOR"})
@RequestMapping(value = "/api/patient",produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${from.email}")
    String fromMail;

    private PatientService patientService;
    private JwtTokenUtil jwtTokenUtil;
    private JavaMailSender mailSender;
    private VaccineService vaccineService;
    private HealthExaminationService examinationService;


    @Autowired
    public PatientController(PatientService patientService, JwtTokenUtil jwtTokenUtil, JavaMailSender mailSender, VaccineService vaccineService, HealthExaminationService examinationService) {
        this.patientService = patientService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.mailSender = mailSender;
        this.vaccineService = vaccineService;
        this.examinationService = examinationService;
    }




    @CrossOrigin
    @PostMapping(value="/addNewPatient")
    public Patient addNewPatient(@ModelAttribute("patientDTO") PatientDTO patientDTO, HttpServletResponse response
    ) throws ParseException, IOException {
        String password = UUID.randomUUID().toString();
        patientDTO.setPassword(password);
        Patient newPatient =  this.patientService.addNewPatient(patientDTO);
        String body = "Vashiot profil e kreiran! Najavete se so korisnicko ime: " + newPatient.userId + ", password: " + password + ". Vednash po najavata vnesete svoj pasword!";
        sendConfirmationEmail("Aktivacija na korisnicki profil ",body, newPatient.email);
        return newPatient;
    }

    @CrossOrigin
    @GetMapping(value="/getPatient")
    public Patient getPatient(HttpServletRequest request){
        String authToken = request.getHeader(tokenHeader).substring(7);
        String username = this.jwtTokenUtil.getUsernameFromToken(authToken);
        return this.patientService.findPatientByID(username);

    }


    @CrossOrigin
    @GetMapping(value = "{userId}/examinations")
    public Page<HealthExamination> findHealthExaminationsForPatient(@PathVariable String userId, HttpServletRequest request){
        return this.patientService.findHealthExaminationsForPatient(userId);
    }


    @CrossOrigin
    @GetMapping(value = "{userId}/vaccines")
    public List<Vaccine> findVaccinesForPatient(@PathVariable String userId, HttpServletRequest request){
        return this.patientService.findVaccinesForPatient(userId);
    }





    @CrossOrigin
    @PostMapping(value = "/update")
    public Patient updatePatient(@ModelAttribute PatientDTO patientDTO) throws ParseException {
        return this.patientService.updatePatient(patientDTO);
    }

    @CrossOrigin
    @DeleteMapping(value="/delete")
    public Patient deletePatient(@RequestParam String patientID){
        return this.patientService.deletePatient(patientID);
    }


    @CrossOrigin
    @GetMapping(value = "/{patientId}")
    public Patient getPatient(@PathVariable String patientId){
        return this.patientService.findPatientByID(patientId);
    }


    @CrossOrigin
    @PostMapping(value = "/{userId}/addVaccine")
    public Vaccine addNewVaccine(@PathVariable String userId, @RequestParam(required = true) String name, @RequestParam(required = true) String dateOfReceipt) throws ParseException {
            System.out.println(userId +" "+ name);
        return this.vaccineService.addNewVaccine(userId, name, dateOfReceipt);
    }

    @CrossOrigin
    @PostMapping(value = "/{userId}/addExamination", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HealthExamination addNewExamination(@PathVariable String userId, @RequestBody HealthExaminationDTO examinationDTO) throws ParseException {
        System.out.println("RTC" + examinationDTO.getRtc_Finding());
        //examinationDTO.getMedicines().forEach(medicine -> System.out.println(medicine.getName()));
        return this.examinationService.addNewExamination(examinationDTO, userId);
    }


    @CrossOrigin
    @GetMapping(value = "/{userId}/examination/{dateOfExamination}/medicines")
    public List<Medicine> getMedicinesForExamination(@PathVariable String userId, @PathVariable String dateOfExamination) throws ParseException {
        HealthExaminationID examinationID = new HealthExaminationID();
        examinationID.setUserId(userId);
        examinationID.setDateOfExamination(new SimpleDateFormat("yyyy-MM-dd").parse(dateOfExamination));
        return this.examinationService.findMedicinesForExamination(examinationID);
    }

    private void sendConfirmationEmail(String subject, String body, String empEmail) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(empEmail);
        email.setFrom(fromMail);
        mailSender.send(email);
    }
}
