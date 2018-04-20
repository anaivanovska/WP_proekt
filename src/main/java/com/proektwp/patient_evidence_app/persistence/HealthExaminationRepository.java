package com.proektwp.patient_evidence_app.persistence;

import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.model.HealthExaminationID;
import com.proektwp.patient_evidence_app.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthExaminationRepository extends JpaRepository<HealthExamination, HealthExaminationID> {
    public Page<HealthExamination> findByPatient(Patient patient, Pageable pageable);

}
