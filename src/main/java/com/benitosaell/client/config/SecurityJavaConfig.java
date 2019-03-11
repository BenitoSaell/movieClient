package com.benitosaell.client.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;/*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/

import com.benitosaell.client.controller.CustomAuthenticationProvider;

//@Configuration
//@EnableWebSecurity
public class SecurityJavaConfig/* extends WebSecurityConfigurerAdapter*/ {
	/*
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("javainuse")
		.password("{noop}javainuse").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/peliculas/**").authorizeRequests().anyRequest().hasRole("USER")
		.and().formLogin();
		
	}*/

}
