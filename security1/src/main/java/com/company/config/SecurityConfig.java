package com.company.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.company.handler.CustomAccessDeniedHandler;
import com.company.handler.CustomLoginSuccessHandler;
import com.company.service.CustomUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	//암호화 객체
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Customer Service 객체 생성
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public AccessDeniedHandler getAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public PersistentTokenRepository persistentToken() {
		JdbcTokenRepositoryImpl resp = new JdbcTokenRepositoryImpl();
		resp.setDataSource(dataSource);
		return resp;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling(ex -> ex.accessDeniedHandler(getAccessDeniedHandler()));
		
		//<security:form-login
		http.formLogin()
			.loginPage("/login")
			.failureUrl("/login-error")
			.successHandler(getAuthenticationSuccessHandler());
		
		//<security:logout
		http.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
			.deleteCookies("remember-me","JSESSION_ID")
			.logoutSuccessUrl("/");
		
		//<security:intercept-url
		http.authorizeRequests().antMatchers("/user-page").access("hasRole('ROLE_USER')")
								.antMatchers("/admin-page").access("hasRole('ROLE_ADMIN')")
								.antMatchers("/login").permitAll();
		
		//<security:remember-me
		http.rememberMe()
			.tokenRepository(persistentToken())
			.tokenValiditySeconds(604800);
	}
	
	//<security:authentication-manager>
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getUserDetailsService())
			.passwordEncoder(getPasswordEncoder());
	}
}
