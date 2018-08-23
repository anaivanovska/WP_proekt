package com.proektwp.patient_evidence_app.security;

import com.proektwp.patient_evidence_app.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class CustomUserDetails extends User implements org.springframework.security.core.userdetails.UserDetails {
    

    public CustomUserDetails(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("ROLE" + this.role);
      return  AuthorityUtils.createAuthorityList(String.valueOf(this.role));

    }


    @Override
    public String getPassword() {
        return super.password;
    }

    @Override
    public String getUsername() {
        return super.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
