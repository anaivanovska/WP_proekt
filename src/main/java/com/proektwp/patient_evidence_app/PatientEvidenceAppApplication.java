package com.proektwp.patient_evidence_app;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.DTO.FamilyDoctorDTO;
import com.proektwp.patient_evidence_app.persistence.FamilyDoctorRepository;
import com.proektwp.patient_evidence_app.persistence.HealthExaminationRepository;
import com.proektwp.patient_evidence_app.persistence.HealthInsuranceRepository;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
import com.proektwp.patient_evidence_app.service.impl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class PatientEvidenceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientEvidenceAppApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(BCryptPasswordEncoder passwordEncoder, FamilyDoctorService familyDoctorService, PatientService patientService, HealthExaminationService healthExaminationService, HealthInsuranceService healthInsuranceService, VaccineService vaccineService) {
		return args -> {
			SimpleDateFormat sdf = new SimpleDateFormat();
		};
	}
}

