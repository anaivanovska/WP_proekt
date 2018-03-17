package com.proektwp.patient_evidence_app.persistence;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyDoctorRepository extends CrudRepository<FamilyDoctor, String> {
}
