package com.proektwp.patient_evidence_app.init;


import com.proektwp.patient_evidence_app.model.Role;
import com.proektwp.patient_evidence_app.model.User;
import com.proektwp.patient_evidence_app.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AdminInit {

    private final Environment environment;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminInit(Environment environment, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.environment = environment;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if( userRepository.count() ==0 ) {
            User user = new User();
            String username = environment.getProperty("app.user.admin.username");
            String email = environment.getProperty("app.user.admin.email");
            String password = passwordEncoder.encode(environment.getProperty("app.user.admin.password"));

            user.userId = username;
            user.role = Role.ROLE_ADMIN;
            user.firstName = username;
            user.lastName = username;
            user.email = email;
            user.password = password;
            user.address = "Admin address";
            user.phoneNumber = "Admin phoneNumber";

            this.userRepository.save(user);
        }
    }


}
