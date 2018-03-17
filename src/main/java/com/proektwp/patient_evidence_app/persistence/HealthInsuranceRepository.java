package com.proektwp.patient_evidence_app.persistence;

import com.proektwp.patient_evidence_app.model.HealthInsurance;
import com.proektwp.patient_evidence_app.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthInsuranceRepository extends CrudRepository<HealthInsurance, String> {
    public HealthInsurance findByPatient(Patient patient);
}
