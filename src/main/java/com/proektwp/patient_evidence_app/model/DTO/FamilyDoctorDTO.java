package com.proektwp.patient_evidence_app.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FamilyDoctorDTO implements Serializable{

    String userId;
    String firstName;
    String lastName;
    String password;
    String email;
    String phoneNumber;
    String address;
    String dateOfBirth;
    Boolean agreement_with_FZO;
    String speciality;
    String workTime;
    String deputyFamilyDoctorID;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getAgreement_with_FZO() {
        return agreement_with_FZO;
    }

    public void setAgreement_with_FZO(Boolean agreement_with_FZO) {
        this.agreement_with_FZO = agreement_with_FZO;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getDeputyFamilyDoctorID() {
        return deputyFamilyDoctorID;
    }

    public void setDeputyFamilyDoctorID(String deputyFamilyDoctorID) {
        this.deputyFamilyDoctorID = deputyFamilyDoctorID;
    }

}
