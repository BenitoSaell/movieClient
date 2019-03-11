package com.benitosaell.client.controller;

import java.util.ArrayList;
import java.util.Collections;
/*
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;*
import org.springframework.stereotype.Component;
*/
//@Component
public class CustomAuthenticationProvider
/*implements AuthenticationProvider*/{
 
    /*@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
        String password = auth.getCredentials()
            .toString();
 
        if ("externaluser".equals(username) && "pass".equals(password)) {
            return new UsernamePasswordAuthenticationToken
              (username, password, Collections.emptyList());
        } else {
            throw new
              BadCredentialsException("External system authentication failed");
        }
	}*/
}
