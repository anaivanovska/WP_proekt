package com.proektwp.patient_evidence_app.persistence;

import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.model.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, String>{
    public List<Medicine> findByHealthExamination(HealthExamination healthExamination);

}
