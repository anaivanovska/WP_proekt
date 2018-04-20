package com.proektwp.patient_evidence_app.controller;

import com.proektwp.patient_evidence_app.web.FamilyDoctorController;
import com.sun.xml.internal.ws.client.ResponseContext;
import com.sun.xml.internal.ws.client.ResponseContextReceiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.servlet.http.HttpServletResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FamilyDoctorControllerTest {

    @Autowired
    private FamilyDoctorController familyDoctorController;


    @Test
    public void testDeleteFamilyDoctor() {

        //Delete
        familyDoctorController.deleteFamilyDoctor("2");
    }
}
