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

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class PatientEvidenceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientEvidenceAppApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(FamilyDoctorRepository familyDoctorRepository,FamilyDoctorService familyDoctorService, PatientService patientService, HealthExaminationService healthExaminationService, HealthInsuranceService healthInsuranceService, VaccineService vaccineService) {
		return args -> {

			//familyDoctorService.addNewFamilyDoctor("2", "Blaze", "Blazevski",  "blaze1235shf", "blaze@gmail.com", "071/567-890", "ul. Ruza Delceva - Bitola",  true, "ortodoncija", "Ponedelnik - Petok od 09:00 - 17:00", "5");
			//FamilyDoctor familyDoctor = familyDoctorRepository.findOne("3");
			//System.out.println(familyDoctor.userId + " Name: "+ familyDoctor.firstName);
			//Patient patient = patientService.addNewPatient("patientID16", "Elena", "Janeva","eci", "marija97@gmail.com",  "071/312-498", "ul. Bezbroj br. 11", new Date(), "Female", "920842024","student", "unmarried","3");
			//FamilyDoctor deputy = familyDoctorRepository.findOne("2");
			//System.out.println(deputy.firstName +" "+ deputy.lastName);


		};
	}
}

