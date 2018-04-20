package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.model.Vaccine;
import com.proektwp.patient_evidence_app.model.VaccineID;
import com.proektwp.patient_evidence_app.model.exceptions.VaccineNotFoundException;
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

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }


    @Override
    public Vaccine findVaccineByID(VaccineID vaccineID) {
        Vaccine vaccine = this.vaccineRepository.findOne(vaccineID);
        if(vaccine == null){
            throw new VaccineNotFoundException(vaccineID.getName(), vaccineID.getUserId());
        }
        return vaccine;
    }

    @Override
    @Transactional
    public Vaccine addNewVaccine(Vaccine newVaccine) {

        Vaccine vaccine = this.vaccineRepository.findOne(newVaccine.vaccineID);
        if(vaccine != null){

        }
        return this.vaccineRepository.save(newVaccine);
    }

    @Override
    @Transactional
    public Vaccine updateVaccine(Vaccine vaccine){
        Vaccine updatedVaccine = this.findVaccineByID(vaccine.vaccineID);
        updatedVaccine.dateOfReceipt = vaccine.dateOfReceipt;
        return this.vaccineRepository.save(updatedVaccine);
    }

    @Override
    public Vaccine deleteVaccine(VaccineID vaccineID) {
        Vaccine vaccine = this.findVaccineByID(vaccineID);
        this.vaccineRepository.delete(vaccineID);
        return vaccine;
    }


}