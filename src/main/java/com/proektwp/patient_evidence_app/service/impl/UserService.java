package com.proektwp.patient_evidence_app.service.impl;


import com.proektwp.patient_evidence_app.model.DTO.PasswordDTO;
import com.proektwp.patient_evidence_app.model.User;
import com.proektwp.patient_evidence_app.security.CustomUserDetails;
import com.proektwp.patient_evidence_app.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements com.proektwp.patient_evidence_app.service.UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User changeUserPassword(PasswordDTO passwordDTO, String userId) {
        Optional<User> optionalUser = this.userRepository.findUserByUserId(userId);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException(userId));
        User user = optionalUser.map(CustomUserDetails::new).get();
        if(passwordEncoder.matches(passwordDTO.getOldPassword(), user.password)){
            String encodedPassword = this.passwordEncoder.encode(passwordDTO.getPassword());
            this.userRepository.updatePassword(encodedPassword, userId);
            return user;
        }

        return null;

    }
}
