package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.*;
import com.proektwp.patient_evidence_app.model.DTO.HealthExaminationDTO;

import java.text.ParseException;
import java.util.List;

public interface HealthExaminationService {
    public HealthExamination findHealthExamination(HealthExaminationID healthExaminationID);
    public List<Medicine> findMedicinesForExamination(HealthExaminationID healthExaminationID);
    public HealthExamination addNewExamination(HealthExaminationDTO examinationDTO, String userId) throws ParseException;
    public HealthExamination updateExamination(HealthExamination healthExamination);
    public HealthExamination deleteExamination(HealthExaminationID healthExaminationID);
}