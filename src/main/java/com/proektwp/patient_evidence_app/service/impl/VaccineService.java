package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.model.Vaccine;
import com.proektwp.patient_evidence_app.model.VaccineID;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
import com.proektwp.patient_evidence_app.persistence.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Vaccine> findAllVaccinesForPatient(String patientID) {
        Patient patient = this.patientRepository.findOne(patientID);

        return this.vaccineRepository.findByPatient(patient);
    }

    @Override
    @Transactional
    public Vaccine addNewVaccine(String name, Date date, String patientID) {
        VaccineID vaccineID = new VaccineID();
        vaccineID.setName(name);
        Patient patient= this.patientRepository.findOne(patientID);
        Vaccine vaccine = new Vaccine(vaccineID, date, patient);

        return this.vaccineRepository.save(vaccine);
    }

    @Override
    @Transactional
    public Vaccine updateVaccine(String name, Date dateOfReceipt, String patientID) {
        VaccineID vaccineID = new VaccineID();
        vaccineID.setName(name);
        vaccineID.setUserId(patientID);
        Vaccine vaccine = this.vaccineRepository.findOne(vaccineID);
        vaccine.dateOfReceipt = dateOfReceipt;
        //uedjh
        return this.vaccineRepository.save(vaccine);
    }
}