package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.*;
import java.util.List;

public interface HealthExaminationService {
    public HealthExamination findHealthExamination(HealthExaminationID healthExaminationID);
    public List<Medicine> findMedicineForExamination(HealthExaminationID healthExaminationID);
    public HealthExamination addNewExamination(HealthExamination healthExamination);
    public HealthExamination updateExamination(HealthExamination healthExamination);
    public HealthExamination deleteExamination(HealthExaminationID healthExaminationID);
}