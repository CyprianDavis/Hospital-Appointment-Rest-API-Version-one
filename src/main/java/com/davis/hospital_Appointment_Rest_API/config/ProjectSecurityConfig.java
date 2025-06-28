package com.davis.hospital_Appointment_Rest_API.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

/**
 * Security configuration class for the Hospital Appointment REST API.
 * <p>
 * This class configures Spring Security settings including:
 * <ul>
 *   <li>CSRF protection with cookie-based token storage</li>
 *   <li>Session management policies</li>
 *   <li>Authorization rules for endpoints</li>
 *   <li>Password encoding strategy</li>
 *   <li>Basic authentication configuration</li>
 * </ul>
 * 
 * The configuration currently permits all requests for development purposes,
 * with explicit exceptions for specific public endpoints.
 * </p>
 * 
 * <p>
 * Security Features Implemented:
 * <ul>
 *   <li>CSRF protection with {@link CookieCsrfTokenRepository}</li>
 *   <li>Custom {@link CsrfTokenRequestAttributeHandler} for CSRF token handling</li>
 *   <li>Session creation policy set to ALWAYS</li>
 *   <li>Delegating password encoder for secure password storage</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-29
 * @see SecurityFilterChain
 * @see PasswordEncoder
 * @see HttpSecurity
 */
@Configuration
public class ProjectSecurityConfig {
	 private final CustomAuthenticationEntryPoint authenticationEntryPoint;
	 private final CustomAccessDeniedHandler accessDeniedHandler;
	 
	// Constructor injection
	    public ProjectSecurityConfig(CustomAuthenticationEntryPoint authenticationEntryPoint,
	                               CustomAccessDeniedHandler accessDeniedHandler) {
	        this.authenticationEntryPoint = authenticationEntryPoint;
	        this.accessDeniedHandler = accessDeniedHandler;
	    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
        // Configure exception handling
        .exceptionHandling(exceptionHandling -> exceptionHandling
            .authenticationEntryPoint(authenticationEntryPoint)  // For unauthenticated users
            .accessDeniedHandler(accessDeniedHandler))          // For unauthorized but authenticated users
        
       . securityContext(ContextConfig -> ContextConfig.requireExplicitSave(false))
            .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .csrf(csrfConfig -> csrfConfig
                    .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register","/error","/api/userRoles/role").permitAll()
                .anyRequest().permitAll()
            )
            .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}