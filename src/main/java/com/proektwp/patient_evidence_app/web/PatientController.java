package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.DTO.PatientDTO;
import com.proektwp.patient_evidence_app.security.CustomUserDetails;
import com.proektwp.patient_evidence_app.security.JwtTokenUtil;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
//@Secured({"ROLE_PATIENT", "ROLE_DOCTOR"})
@RequestMapping(value = "/api/patient",produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    @Value("${jwt.header}")
    private String tokenHeader;
    private PatientService patientService;
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public PatientController(PatientService patientService, JwtTokenUtil jwtTokenUtil) {
        this.patientService = patientService;
        this.jwtTokenUtil = jwtTokenUtil;
    }




    @CrossOrigin
    @PostMapping(value="/addNewPatient")
    public Patient addNewPatient(@ModelAttribute("patientDTO") PatientDTO patientDTO, HttpServletResponse response
    ) throws ParseException, IOException {
        return this.patientService.addNewPatient(patientDTO);
    }

    @CrossOrigin
    @GetMapping(value="/getPatient")
    public Patient getPatient(HttpServletRequest request){
        String authToken = request.getHeader(tokenHeader).substring(7);
        String username = this.jwtTokenUtil.getUsernameFromToken(authToken);
        return this.patientService.findPatientByID(username);

    }


    @CrossOrigin
    @GetMapping(value = "/examinations")
    public Page<HealthExamination> findHealthExaminationsForPatient(HttpServletRequest request){
       CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.patientService.findHealthExaminationsForPatient(userDetails.userId);
    }


    @CrossOrigin
    @GetMapping(value = "/vaccines")
    public List<Vaccine> findVaccinesForPatient(HttpServletRequest request){
        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.patientService.findVaccinesForPatient(userDetails.userId);
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

}
