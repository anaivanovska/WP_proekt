package com.proektwp.patient_evidence_app;

import com.proektwp.patient_evidence_app.model.*;
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
			String birthDate = "09.07.1987";
			FamilyDoctorDTO familyDoctorDTO = new FamilyDoctorDTO();
			familyDoctorDTO.setUserId("6");
			familyDoctorDTO.setFirstName("Zan");
			familyDoctorDTO.setLastName("Mitrev");
			familyDoctorDTO.setAddress("ul. Albert Ajnshtajn, Skopje");
			familyDoctorDTO.setEmail("zanMitrev@gmail.com");
			familyDoctorDTO.setPhoneNumber("071/361-780");
			familyDoctorDTO.setDateOfBirth(birthDate);
			familyDoctorDTO.setAgreement_with_FZO(true);
			familyDoctorDTO.setPassword(passwordEncoder.encode("zan"));
			familyDoctorDTO.setDeputyFamilyDoctorID("2");
			familyDoctorDTO.setWorkTime("Ponedelnik - Petok od 08h - 17h");
			familyDoctorDTO.setSpeciality("kardiolog");
			familyDoctorService.addNewFamilyDoctor(familyDoctorDTO);

			//FamilyDoctor familyDoctor = familyDoctorRepository.findOne("3");
			//System.out.println(familyDoctor.userId + " Name: "+ familyDoctor.firstName);
			//Patient patient = patientService.addNewPatient("patientID16", "Elena", "Janeva","eci", "marija97@gmail.com",  "071/312-498", "ul. Bezbroj br. 11", new Date(), "Female", "920842024","student", "unmarried","3");
			//FamilyDoctor deputy = familyDoctorRepository.findOne("2");
			//System.out.println(deputy.firstName +" "+ deputy.lastName);


		};
	}
}

