package com.proektwp.patient_evidence_app.web.validators.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return isValid(email);
    }

    private boolean isValid(String email){
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
