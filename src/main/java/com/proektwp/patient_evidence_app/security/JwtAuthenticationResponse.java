package com.proektwp.patient_evidence_app.security;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private final String token;

    public JwtAuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
