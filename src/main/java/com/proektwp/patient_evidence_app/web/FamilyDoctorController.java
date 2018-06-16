package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.DTO.FamilyDoctorDTO;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.security.CustomUserDetails;
import com.proektwp.patient_evidence_app.security.JwtTokenUtil;
import com.proektwp.patient_evidence_app.service.impl.FamilyDoctorService;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/api/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class FamilyDoctorController {

    @Value("${jwt.header}")
    private String tokenHeader;


    @Value("${from.email}")
    private String fromMail;

    private JavaMailSender mailSender;
    private JwtTokenUtil jwtTokenUtil;
    private FamilyDoctorService familyDoctorService;
    private PatientService patientService;

    @Autowired
    public FamilyDoctorController(JwtTokenUtil jwtTokenUtil, FamilyDoctorService familyDoctorService, PatientService patientService, JavaMailSender mailSender) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.familyDoctorService = familyDoctorService;
        this.patientService = patientService;
        this.mailSender = mailSender;
    }



    @CrossOrigin
    @GetMapping(value = "/getAll")
    public List<String> findAllFamilyDoctorsID(){
        return this.familyDoctorService.findAllFamilyDoctorsID();
    }
    @CrossOrigin
    @GetMapping(value = "/get")
    public FamilyDoctor findFamilyDoctor(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader).substring(7);
        String username = this.jwtTokenUtil.getUsernameFromToken(authToken);
        return this.familyDoctorService.findFamilyDoctorById(username);
    }


    @CrossOrigin
    @GetMapping(value = "/patients")
    public  Page<Patient> findPatientsForFamilyDoctor(){
        CustomUserDetails authenticatedUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.familyDoctorService.findAllPatients(authenticatedUserDetails.userId);
    }

    @CrossOrigin
    @PostMapping(value = "/addNewFamilyDoctor")
    public FamilyDoctor addNewFamilyDoctor(@ModelAttribute FamilyDoctorDTO familyDoctorDTO, HttpServletResponse response){
        String password = UUID.randomUUID().toString();
        familyDoctorDTO.setPassword(password);
        FamilyDoctor newFamilyDoctor =  this.familyDoctorService.addNewFamilyDoctor(familyDoctorDTO);
        String body = "Vashiot profil e kreiran! Najavete se so korisnicko ime: " + newFamilyDoctor.userId + ", password: " + password + ". Vednash po najavata vnesete svoj pasword!";
        sendConfirmationEmail("Aktivacija na korisnicki profil ",body, newFamilyDoctor.email);
        return newFamilyDoctor;
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    public FamilyDoctor updateFamilyDoctor(@ModelAttribute FamilyDoctorDTO familyDoctorDTO){
        System.out.println(familyDoctorDTO.getUserId() + " FirstName: " + familyDoctorDTO.getFirstName());
        return this.familyDoctorService.updateFamilyDoctor(familyDoctorDTO);

    }

    @CrossOrigin
    @DeleteMapping(value = "/delete")
    public FamilyDoctor deleteFamilyDoctor(@PathVariable String doctorID){
        return this.familyDoctorService.deleteFamilyDoctor(doctorID);
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
