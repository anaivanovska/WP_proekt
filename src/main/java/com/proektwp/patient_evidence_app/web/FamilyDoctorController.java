package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.service.impl.FamilyDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.util.List;

@RestController
//@Secured("ROLE_DOCTOR")
@RequestMapping(value="familyDoctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class FamilyDoctorController {
    private FamilyDoctorService familyDoctorService;

    @Autowired
    public FamilyDoctorController(FamilyDoctorService familyDoctorService) {
        this.familyDoctorService = familyDoctorService;
    }

    @CrossOrigin
    @GetMapping(value = "/{doctorID}")
    public FamilyDoctor findFamilyDoctor(@PathVariable(value = "doctorID") String doctorID) {
        return this.familyDoctorService.findFamilyDoctorById(doctorID);
    }

    @CrossOrigin
    @GetMapping(value = "/{doctorID}/patients")
    public  Page<Patient> findPatientsForFamilyDoctor(@PathVariable String doctorID){
        return this.familyDoctorService.findAllPatients(doctorID);
    }

    @CrossOrigin
    @PostMapping(value = "/addNewFamilyDoctor")
    public FamilyDoctor addNewFamilyDoctor(@RequestParam(required = true) String doctorID,@RequestParam(required = true) String firstName,
                                                 @RequestParam(required = true) String lastName,@RequestParam(required = true)  String password,
                                                 @RequestParam(required = true) String email,@RequestParam(required = true) String phoneNumber,
                                                 @RequestParam(required = true) String address,@RequestParam(required = true) Boolean agreement_with_FZO,
                                                 @RequestParam(required = true) String speciality,@RequestParam(required = true) String workTime,
                                                 @RequestParam(required = true) String deputyDoctorID, HttpServletResponse response){
        return this.familyDoctorService.addNewFamilyDoctor(doctorID, firstName,
                                                            lastName, password,
                                                            email, phoneNumber,
                                                            address,agreement_with_FZO, speciality,
                                                            workTime, deputyDoctorID);
    }

    @CrossOrigin
    @PostMapping(value = "/{doctorID}/update")
    public FamilyDoctor updateFamilyDoctor(@PathVariable(required = true) String doctorID,@RequestParam String firstName,
                                           @RequestParam String lastName,@RequestParam  String password,
                                           @RequestParam String email,@RequestParam String phoneNumber,
                                           @RequestParam String address,@RequestParam Boolean agreement_with_FZO,
                                           @RequestParam String speciality,@RequestParam String workTime,
                                           @RequestParam String deputyDoctorID, HttpServletResponse response){
        return this.familyDoctorService.updateFamilyDoctor(doctorID, firstName, lastName, password, email, phoneNumber, address, agreement_with_FZO, speciality, workTime, deputyDoctorID);

    }

    @CrossOrigin
    @DeleteMapping(value = "/{doctorID}/delete")
    public FamilyDoctor deleteFamilyDoctor(@PathVariable String doctorID){
        return this.familyDoctorService.deleteFamilyDoctor(doctorID);
    }


}
