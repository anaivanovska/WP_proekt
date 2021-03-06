package com.proektwp.patient_evidence_app.web;

import com.proektwp.patient_evidence_app.model.DTO.PasswordDTO;
import com.proektwp.patient_evidence_app.model.User;
import com.proektwp.patient_evidence_app.security.*;
import com.proektwp.patient_evidence_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;
    private CustomUserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;


    @Autowired
    public UserController(CustomUserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
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
    public User getUser(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        CustomUserDetails userDetails  = (CustomUserDetails) this.userDetailsService.loadUserByUsername(username);
        return (User)userDetails;
    }


    @CrossOrigin
    @PutMapping(value = "/{userId}/changePassword", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public User changeUserPassword(@RequestBody PasswordDTO passwordDTO, @PathVariable String userId){
        return this.userService.changeUserPassword(passwordDTO, userId);

    }
}


