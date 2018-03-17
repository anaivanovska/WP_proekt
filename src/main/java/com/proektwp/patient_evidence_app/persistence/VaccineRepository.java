package com.proektwp.patient_evidence_app.persistence;

import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.model.Vaccine;
import com.proektwp.patient_evidence_app.model.VaccineID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, VaccineID> {
    public List<Vaccine> findByPatient(Patient patient);
 }
