package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.model.Patient;
import com.proektwp.patient_evidence_app.persistence.FamilyDoctorRepository;
import com.proektwp.patient_evidence_app.persistence.HealthExaminationRepository;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
import com.proektwp.patient_evidence_app.persistence.VaccineRepository;
import com.proektwp.patient_evidence_app.service.impl.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PatientServiceTest {

    @Autowired
    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private FamilyDoctorRepository familyDoctorRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private HealthExaminationRepository healthExaminationRepository;

    @Mock
    private VaccineRepository vaccineRepository;


    @Test
    public void testAddPatient(){
        FamilyDoctor familyDoctor = this.familyDoctorRepository.findOne("2");
        Patient aMockPatient = new Patient("patientID19", "Mihaela", "Janeva","eci", "marija97@gmail.com",  "071/213-498", "ul. Bezbroj br. 11", new Date(), "Female", "920842024","student", "unmarried",familyDoctor);

        when(patientRepository.save(any(Patient.class))).thenReturn(aMockPatient);
        //Save
        Patient patient = this.patientService.addNewPatient("patientID19", "Mihaela", "Janeva","eci", "marija97@gmail.com",  "071/312-498", "ul. Bezbroj br. 11", new Date(), "Female", "920842024","student", "unmarried","2");

        assertEquals(aMockPatient.userId, patient.userId);
        assertNotNull(patient);
    }

}
