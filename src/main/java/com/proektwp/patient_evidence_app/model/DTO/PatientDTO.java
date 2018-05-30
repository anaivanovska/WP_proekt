package com.proektwp.patient_evidence_app.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class PatientDTO implements Serializable{
    String userId;
    String firstName;
    String lastName;
    String password;
    String email;
    String phoneNumber;
    String address;
    String dateOfBirth;
    String profession;
    String marriageState;
    String familyDoctorID;
    String embg;
    String gender;
    String healthLegitimationNumber;
    String registrationNumber;
    String activityID;
    String typeOfHealthProtection;




    public String getEmbg() {
        return embg;
    }

    public void setEmbg(String embg) {
        this.embg = embg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMarriageState() {
        return marriageState;
    }

    public void setMarriageState(String marriageState) {
        this.marriageState = marriageState;
    }

    public String getFamilyDoctorID() {
        return familyDoctorID;
    }

    public void setFamilyDoctorID(String familyDoctorID) {
        this.familyDoctorID = familyDoctorID;
    }

    public String getHealthLegitimationNumber() {
        return healthLegitimationNumber;
    }

    public void setHealthLegitimationNumber(String healthLegitimationNumber) {
        this.healthLegitimationNumber = healthLegitimationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getTypeOfHealthProtection() {
        return typeOfHealthProtection;
    }

    public void setTypeOfHealthProtection(String typeOfHealthProtection) {
        this.typeOfHealthProtection = typeOfHealthProtection;
    }
}
