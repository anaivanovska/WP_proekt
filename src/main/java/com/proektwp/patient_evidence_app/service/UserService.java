package com.proektwp.patient_evidence_app.service;

import com.proektwp.patient_evidence_app.model.DTO.PasswordDTO;
import com.proektwp.patient_evidence_app.model.User;

public interface UserService {

    public User changeUserPassword(PasswordDTO passwordDTO, String userId);
}
