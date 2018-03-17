package com.proektwp.patient_evidence_app;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.model.HealthInsurance;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.persistence.FamilyDoctorRepository;
import com.proektwp.patient_evidence_app.persistence.HealthExaminationRepository;
import com.proektwp.patient_evidence_app.persistence.HealthInsuranceRepository;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
import com.proektwp.patient_evidence_app.service.impl.FamilyDoctorService;
import com.proektwp.patient_evidence_app.service.impl.HealthExaminationService;
import com.proektwp.patient_evidence_app.service.impl.HealthInsuranceService;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
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
	CommandLineRunner runner(FamilyDoctorService familyDoctorService, PatientService patientService, HealthExaminationService healthExaminationService, HealthInsuranceService healthInsuranceService) {
		return args -> {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			/*familyDoctorService.addNewFamilyDoctor("1", "Dr1", "Doktor1",  "dentist");
			familyDoctorService.addNewFamilyDoctor("2", "Dr2", "Doktor2", "dentist");

			patientService.addNewPatient("12", "Patient2_Name", "Patient2_Surname","pass");
			patientService.addNewPatient("11", "P1", "P1","pass");

			healthInsuranceService.addHealthInsurance("leg1", "regN", "actId", "typeOfProt", "11" );
			healthInsuranceService.addHealthInsurance("leg2", "regN", "actId", "typeOfProt", "12" );

			healthExaminationService.addNewExamination(sdf.parse("21/12/2017"), "diagnosis1", true, true, true,true, true, true,true, true, true, "typeOfTherapy", "11");

			healthExaminationService.addNewExamination(sdf.parse("21/02/2018"), "diagnosis2", true, false, true,true, true, true,true, true, true, "typeOfTherapy", "11");


			healthExaminationService.addNewExamination(sdf.parse("10/03/2018"), "diagnosis3", false, true, true,true, true, true,true, true, true, "typeOfTherapy", "11");

			healthExaminationService.addNewExamination(sdf.parse("10/04/2018"), "diagnosis1", true, true, true,true, true, true,true, true, true, "typeOfTherapy", "12"); */

			//healthExaminationService.addNewExamination(sdf.parse("11/03/2018"), "diagnosis1", true, true, true,true, true, true,true, true, true, "typeOfTherapy", "12");

			//healthExaminationService.updateExamination(sdf.parse("10/03/2018"), "diagnosis3_Changed", false, true, true,true, true, true,true, true, true, "typeOfTherapy", "11");

			//HealthExamination examination = healthExaminationService.findHealthExamination(sdf.parse("10/04/2018"), "11");
			//System.out.println(examination.patient.userId);
			};
	}
}
