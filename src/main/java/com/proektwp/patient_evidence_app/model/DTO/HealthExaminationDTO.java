package com.proektwp.patient_evidence_app.model.DTO;


import com.proektwp.patient_evidence_app.model.Medicine;
import org.hibernate.search.query.dsl.BooleanJunction;

import java.io.Serializable;
import java.util.List;

public class HealthExaminationDTO implements Serializable {

    String diagnosisAtMKB;
    Boolean laboratory_Finding;
    Boolean rtc_Finding;
    Boolean eho_Finding;
    Boolean computed_Tomography;
    Boolean magnetic_Resonance;
    Boolean sent_To_A_specialist;
    Boolean illness;
    Boolean bandage;
    Boolean antibody_Prophylaxis;
    String typeOfTherapy;
    String dateOfExamination;
    List<MedicineDTO> medicines;

    public HealthExaminationDTO() {
    }

    public String getDiagnosisAtMKB() {
        return diagnosisAtMKB;
    }

    public void setDiagnosisAtMKB(String diagnosisAtMKB) {
        this.diagnosisAtMKB = diagnosisAtMKB;
    }

    public Boolean getLaboratory_Finding() {
        return laboratory_Finding;
    }

    public void setLaboratory_Finding(Boolean laboratory_Finding) {
        this.laboratory_Finding = laboratory_Finding;
    }

    public Boolean getRtc_Finding() {
        return rtc_Finding;
    }

    public void setRtc_Finding(Boolean rtc_Finding) {
        this.rtc_Finding = rtc_Finding;
    }

    public Boolean getEho_Finding() {
        return eho_Finding;
    }

    public void setEho_Finding(Boolean eho_Finding) {
        this.eho_Finding = eho_Finding;
    }

    public Boolean getComputed_Tomography() {
        return computed_Tomography;
    }

    public void setComputed_Tomography(Boolean computed_Tomography) {
        this.computed_Tomography = computed_Tomography;
    }

    public Boolean getMagnetic_Resonance() {
        return magnetic_Resonance;
    }

    public void setMagnetic_Resonance(Boolean magnetic_Resonance) {
        this.magnetic_Resonance = magnetic_Resonance;
    }

    public Boolean getSent_To_A_specialist() {
        return sent_To_A_specialist;
    }

    public void setSent_To_A_specialist(Boolean sent_To_A_specialist) {
        this.sent_To_A_specialist = sent_To_A_specialist;
    }

    public Boolean getIllness() {
        return illness;
    }

    public void setIllness(Boolean illness) {
        this.illness = illness;
    }

    public Boolean getBandage() {
        return bandage;
    }

    public void setBandage(Boolean bandage) {
        this.bandage = bandage;
    }

    public Boolean getAntibody_Prophylaxis() {
        return antibody_Prophylaxis;
    }

    public void setAntibody_Prophylaxis(Boolean antibody_Prophylaxis) {
        this.antibody_Prophylaxis = antibody_Prophylaxis;
    }

    public String getTypeOfTherapy() {
        return typeOfTherapy;
    }

    public void setTypeOfTherapy(String typeOfTherapy) {
        this.typeOfTherapy = typeOfTherapy;
    }

    public String getDateOfExamination() {
        return dateOfExamination;
    }

    public void setDateOfExamination(String dateOfExamination) {
        this.dateOfExamination = dateOfExamination;
    }

    public List<MedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineDTO> medicines) {
        this.medicines = medicines;
    }
}
