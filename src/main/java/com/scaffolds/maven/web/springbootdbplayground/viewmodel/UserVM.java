/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author 1
 */
public class UserVM implements Serializable {
    
    private String username;
    private String password;
    private String email;
    private String role;

    public UserVM(@JsonProperty("username") String username, 
                  @JsonProperty("password") String password, 
                  @JsonProperty("email") String email, 
                  @JsonProperty("role") String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getRole() {
        return role;
    }
    
    public String getEncodedPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }
    
    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password, getAuthorities());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> role);
    }
}
