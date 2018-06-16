package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.Vaccine;
import com.proektwp.patient_evidence_app.model.VaccineID;
import com.proektwp.patient_evidence_app.model.exceptions.VaccineNotFoundException;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
import com.proektwp.patient_evidence_app.persistence.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VaccineService implements com.proektwp.patient_evidence_app.service.VaccineService {

    private VaccineRepository vaccineRepository;
    private PatientRepository patientRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository, PatientRepository patientRepository) {
        this.vaccineRepository = vaccineRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public Vaccine findVaccineByID(VaccineID vaccineID) {
        Vaccine vaccine = this.vaccineRepository.findOne(vaccineID);
        if(vaccine == null){
            throw new VaccineNotFoundException(vaccineID.getName(), vaccineID.getUserId());
        }
        return vaccine;
    }

    @Transactional
    @Override
    public Vaccine addNewVaccine(String userId, String name, String dateOfReceipt) throws ParseException {
        VaccineID vaccineID = new VaccineID();
        vaccineID.setName(name);
        vaccineID.setUserId(userId);
        Vaccine vaccine = this.vaccineRepository.findOne(vaccineID);
        if(vaccine != null){
            return null;
        }

        if(vaccineID == null){
            System.out.println("VACCINE IS NULL");
        }

        vaccine = new Vaccine();
        vaccine.vaccineID = vaccineID;
        vaccine.dateOfReceipt = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfReceipt);
        vaccine.patient = this.patientRepository.findOne(userId);
        return this.vaccineRepository.save(vaccine);
    }



    @Transactional
    @Override
    public Vaccine updateVaccine(String userId, String name, String dateOfReceipt) throws ParseException {
        VaccineID vaccineID = new VaccineID();
        vaccineID.setName(name);
        vaccineID.setUserId(userId);
        Vaccine vaccine = this.findVaccineByID(vaccineID);
        vaccine.dateOfReceipt = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfReceipt);
        return vaccine;
    }


    @Override
    public Vaccine deleteVaccine(VaccineID vaccineID) {
        Vaccine vaccine = this.findVaccineByID(vaccineID);
        this.vaccineRepository.delete(vaccineID);
        return vaccine;
    }


}