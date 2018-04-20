package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.FamilyDoctor;
import com.proektwp.patient_evidence_app.persistence.FamilyDoctorRepository;
import com.proektwp.patient_evidence_app.persistence.PatientRepository;
import com.proektwp.patient_evidence_app.service.impl.FamilyDoctorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class FamilyDoctorServiceTest {

    @Mock
    private FamilyDoctorRepository familyDoctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @InjectMocks
    private FamilyDoctorService familyDoctorService;



    @Test
    public void testAddNewFamilyDoctor(){
        FamilyDoctor deputy = this.familyDoctorRepository.findOne("2");

        FamilyDoctor aMockFamilyDoctor = new FamilyDoctor("6", "Mateja", "Trpcevska",  "mateja","mateja_T@gmail.com", "071/345-567"  ,"bulevar Vasko Karangelevski br. 11 - Bitola", true, "ginikologija", "Ponedelnik - Petok od 09:00 - 17:00", deputy);


        when(this.familyDoctorRepository.save(any(FamilyDoctor.class))).thenReturn(aMockFamilyDoctor);

        //Save
        FamilyDoctor familyDoctor = this.familyDoctorService.addNewFamilyDoctor("6", "Mateja", "Trpcevska",  "mateja","mateja_T@gmail.com", "071/345-567"  ,"bulevar Vasko Karangelevski br. 11 - Bitola", true, "ginikologija", "Ponedelnik - Petok od 09:00 - 17:00", "2");

        assertEquals(aMockFamilyDoctor.firstName, familyDoctor.firstName);
        assertNotNull(familyDoctor);


    }


}