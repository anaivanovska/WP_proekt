package com.proektwp.patient_evidence_app.service;
import com.proektwp.patient_evidence_app.model.Vaccine;
import com.proektwp.patient_evidence_app.model.VaccineID;

import java.text.ParseException;

public interface VaccineService {
    public Vaccine findVaccineByID(VaccineID vaccineID);
    public Vaccine addNewVaccine(String userId, String name, String dateOfReceipt) throws ParseException;
    public Vaccine updateVaccine(String userId, String name, String dateOfReceipt) throws ParseException;
    public Vaccine deleteVaccine(VaccineID vaccineID);


}
