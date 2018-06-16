package com.proektwp.patient_evidence_app.model.exceptions;

import javax.persistence.Embeddable;

public class VaccineNotFoundException extends RuntimeException {
    public VaccineNotFoundException(String name, String userId) {
        super("Vaccine "+ name + " for user with ID: "+userId+ " not found");
    }

    @Embeddable
    public static class MedicineID {


    }
}
