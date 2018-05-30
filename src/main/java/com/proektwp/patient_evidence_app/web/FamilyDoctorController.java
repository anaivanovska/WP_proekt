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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/api/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class FamilyDoctorController {

    @Value("${jwt.header}")
    private String tokenHeader;
    private JwtTokenUtil jwtTokenUtil;
    private FamilyDoctorService familyDoctorService;
    private PatientService patientService;

    @Autowired
    public FamilyDoctorController(JwtTokenUtil jwtTokenUtil, FamilyDoctorService familyDoctorService, PatientService patientService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.familyDoctorService = familyDoctorService;
        this.patientService = patientService;
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
        return this.familyDoctorService.addNewFamilyDoctor(familyDoctorDTO);
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


    @CrossOrigin
    @GetMapping(value = "/patient/{patientId}")
    public Patient getPatient(@PathVariable String patientId){
        return this.patientService.findPatientByID(patientId);
    }
}
