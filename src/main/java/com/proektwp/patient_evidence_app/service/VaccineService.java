package com.proektwp.patient_evidence_app.service;
import com.proektwp.patient_evidence_app.model.Vaccine;
import com.proektwp.patient_evidence_app.model.VaccineID;

public interface VaccineService {
    public Vaccine findVaccineByID(VaccineID vaccineID);
    public Vaccine addNewVaccine(Vaccine vaccine);
    public Vaccine updateVaccine(Vaccine vaccine);
    public Vaccine deleteVaccine(VaccineID vaccineID);


}
