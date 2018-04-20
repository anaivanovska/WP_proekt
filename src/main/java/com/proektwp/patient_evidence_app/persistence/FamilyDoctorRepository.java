package com.proektwp.patient_evidence_app.persistence;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyDoctorRepository extends JpaRepository<FamilyDoctor, String> {

    public List<FamilyDoctor> findAllByDeputyFamilyDoctor(FamilyDoctor deputyFamilyDoctor);
}
