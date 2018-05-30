package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.User;
import com.proektwp.patient_evidence_app.security.*;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;
    private CustomUserDetailsService userDetailsService;
    private Environment environment;
    private JavaMailSender mailSender;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public UserController(CustomUserDetailsService userDetailsService, Environment environment, JavaMailSender mailSender, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.environment = environment;
        this.mailSender = mailSender;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest loginRequest){
        final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails user =(CustomUserDetails) this.userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String authToken = this.jwtTokenUtil.generateToken(user);
        System.out.println("TOKEN : " + authToken);
        return ResponseEntity.ok(new JwtAuthenticationResponse(authToken));

    }


    @CrossOrigin
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserRole(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        CustomUserDetails userDetails  = (CustomUserDetails) this.userDetailsService.loadUserByUsername(username);
        return (User)userDetails;
    }
}


