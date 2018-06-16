package com.proektwp.patient_evidence_app.model.DTO;

import com.proektwp.patient_evidence_app.web.validators.Password.PasswordMatches;

import java.io.Serializable;

@PasswordMatches
public class PasswordDTO implements Serializable{

     String oldPassword;
     String password;
     String matchingPassword;


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}