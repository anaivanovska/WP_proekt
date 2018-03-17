package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.HealthExamination;
import com.proektwp.patient_evidence_app.model.Medicine;
import com.proektwp.patient_evidence_app.model.Patient;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;
import java.util.List;

public interface HealthExaminationService {
    public HealthExamination findHealthExamination(Date examinationID, String patientID);
    public List<Medicine> findMedicineForExamination(Date examinationID);
    public HealthExamination addNewExamination(Date date, String diagnosisAtMKB, Boolean laboratory_Finding, Boolean RTC_Finding, Boolean EHO_finding, Boolean computed_Tomography, Boolean magnetic_Resonance, Boolean sent_To_A_specialist, Boolean illness, Boolean bandage, Boolean antibody_Prophylaxis,String typeOfTherapy, String patientID);
    public HealthExamination updateExamination(Date date, String diagnosisAtMKB, Boolean laboratory_Finding, Boolean RTC_Finding, Boolean EHO_finding, Boolean computed_Tomography, Boolean magnetic_Resonance, Boolean sent_To_A_specialist, Boolean illness, Boolean bandage, Boolean antibody_Prophylaxis,String typeOfTherapy, String patientID);
    }