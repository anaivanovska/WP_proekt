package com.proektwp.patient_evidence_app.web.validators.Password;

import com.proektwp.patient_evidence_app.model.DTO.PasswordDTO;
import com.proektwp.patient_evidence_app.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {


    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

            PasswordDTO passwordDto = (PasswordDTO) value;
            return passwordDto.getPassword().equals(passwordDto.getMatchingPassword());

    }
}
