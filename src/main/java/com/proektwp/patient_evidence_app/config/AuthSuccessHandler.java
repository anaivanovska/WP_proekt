package com.proektwp.patient_evidence_app.config;

import com.proektwp.patient_evidence_app.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication)
            throws IOException, ServletException {

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        String url;
        User user =(User) authentication.getPrincipal();
        if(user.role.equals("ROLE_ADMIN")){
            url = "/familyDoctor/"+user.userId;
        }else{
            url = "/patient/"+user.userId;
        }
        redirectStrategy.sendRedirect(arg0,arg1, url);

    }

}
