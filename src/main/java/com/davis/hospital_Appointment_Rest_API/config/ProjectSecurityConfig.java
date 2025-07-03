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
 * Configures Spring Security settings including authentication, authorization,
 * CSRF protection, and session management. This implementation provides:
 * </p>
 * <ul>
 *   <li>Basic authentication with password encoding</li>
 *   <li>Custom exception handling for authentication and access denied scenarios</li>
 *   <li>Configurable CSRF protection (enabled by default)</li>
 *   <li>Session management policies</li>
 *   <li>Role-based authorization rules</li>
 * </ul>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.1
 * @since 2025-06-29
 * @see SecurityFilterChain
 * @see PasswordEncoder
 * @see HttpSecurity
 */
@Configuration
public class ProjectSecurityConfig {
    
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    
    /**
     * Constructs a new ProjectSecurityConfig with required dependencies.
     *
     * @param authenticationEntryPoint Handles authentication exceptions
     * @param accessDeniedHandler Handles authorization exceptions
     */
    public ProjectSecurityConfig(CustomAuthenticationEntryPoint authenticationEntryPoint,
                               CustomAccessDeniedHandler accessDeniedHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    /**
     * Configures the security filter chain for the application.
     *
     * @param http the HttpSecurity to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configure exception handling for authentication and authorization failures
            .exceptionHandling(exceptionHandling -> exceptionHandling
                // Custom response for unauthenticated requests
                .authenticationEntryPoint(authenticationEntryPoint)
                // Custom response for unauthorized access attempts  
                .accessDeniedHandler(accessDeniedHandler)
            )
            
            // Configure security context management
            .securityContext(securityContext -> securityContext
                // Allow SecurityContext to be saved automatically
                .requireExplicitSave(false)
            )
            
            // Configure session management
            .sessionManagement(session -> session
                // Create session for every request (consider STATELESS for pure APIs)
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            )
            
            // Configure CSRF protection
            .csrf(csrf -> csrf
                // Custom CSRF token request handler
                .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                // Store CSRF token in cookie with HttpOnly=false for JS access
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                // Uncomment to disable CSRF for pure REST APIs:
                // .disable()
            )
            // Add CSRF cookie filter after basic authentication
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            
            // Configure authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/api/users/patient/register"  // Allow patient registration without auth
                ).permitAll()
                // All other endpoints require authentication
                .anyRequest().authenticated()
            )
            
            // Enable HTTP Basic authentication
            .httpBasic(withDefaults());
        
        return http.build();
    }
     
    /**
     * Creates a password encoder bean that supports multiple encoding formats.
     * Uses Spring Security's delegating password encoder which can handle
     * multiple password encoding algorithms with prefix identifiers (e.g., {bcrypt}).
     *
     * @return the configured PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        // Creates a delegating encoder that supports multiple encoding formats
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}