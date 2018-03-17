package com.proektwp.patient_evidence_app.service.impl;

import com.proektwp.patient_evidence_app.model.CustomUserDetails;
import com.proektwp.patient_evidence_app.model.User;
import com.proektwp.patient_evidence_app.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){

        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUserId(userId);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException(userId));

        return optionalUser.map(CustomUserDetails::new).get();
    }
}
