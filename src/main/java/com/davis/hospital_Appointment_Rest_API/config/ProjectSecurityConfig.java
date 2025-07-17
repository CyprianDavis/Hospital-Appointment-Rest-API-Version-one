package com.davis.hospital_Appointment_Rest_API.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import com.davis.hospital_Appointment_Rest_API.filter.CsrfCookieFilter;
import com.davis.hospital_Appointment_Rest_API.filter.JwtValidationFilter;
import com.davis.hospital_Appointment_Rest_API.service.imp.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Central security configuration class for the Hospital Appointment REST API.
 * 
 * <p>This class configures Spring Security with JWT authentication, CSRF protection,
 * and role-based authorization. Key features include:</p>
 * 
 * <ul>
 *   <li>Stateless JWT-based authentication</li>
 *   <li>Custom CSRF protection with cookie-based token repository</li>
 *   <li>Role-based endpoint access control</li>
 *   <li>Custom exception handling for security events</li>
 *   <li>Password encoding with multiple algorithm support</li>
 * </ul>
 *
 * <p>The configuration implements the following security workflow:</p>
 * <ol>
 *   <li>Public endpoints for authentication and registration</li>
 *   <li>JWT validation for authenticated requests</li>
 *   <li>CSRF protection for state-changing operations</li>
 *   <li>Custom error handling for security exceptions</li>
 * </ol>
 *
 * @author Cyprian Davis
 * @version 1.1
 * @since 2025-06-29
 * @see SecurityFilterChain
 * @see AuthenticationManager
 * @see PasswordEncoder
 */
@Configuration
public class ProjectSecurityConfig {
    
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;
   
    
    /**
     * Constructs a new ProjectSecurityConfig with required dependencies.
     *
     * @param authenticationEntryPoint Handles authentication failures (HTTP 401)
     * @param accessDeniedHandler Handles authorization failures (HTTP 403)
     * @param jwtService Service for JWT token operations (generation/validation)
     * @param objectMapper Jackson ObjectMapper for JSON processing
     */
    public ProjectSecurityConfig(CustomAuthenticationEntryPoint authenticationEntryPoint,
                                CustomAccessDeniedHandler accessDeniedHandler,
                                JwtService jwtService,
                                ObjectMapper objectMapper) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
        
    }

    /**
     * Configures the security filter chain with authentication, authorization,
     * and protection mechanisms.
     *
     * @param http the HttpSecurity builder to configure
     * @return the fully configured SecurityFilterChain
     * @throws Exception if configuration fails
     * 
     * @implSpec This implementation:
     * <ul>
     *   <li>Disables session management (stateless)</li>
     *   <li>Configures CSRF with cookie-based token storage</li>
     *   <li>Adds JWT validation and generation filters</li>
     *   <li>Sets up custom exception handlers</li>
     *   <li>Defines public and protected endpoints</li>
     * </ul>
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
    	
        http
            // Configure exception handling for authentication and authorization failures
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
            )
            
            // Configure security context management
            .securityContext(securityContext -> securityContext
                .requireExplicitSave(false)
            )
            
            // Configure session management
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // CSRF configuration
            .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
            		.ignoringRequestMatchers(
            		    "/api/users/patient/register",
            		    "/api/users/auth"
            		)
            		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .addFilterAfter(new CsrfCookieFilter(), UsernamePasswordAuthenticationFilter.class)
            
            // JWT filters
            .addFilterBefore(
                new JwtValidationFilter(jwtService, objectMapper),
                UsernamePasswordAuthenticationFilter.class
            )
				/*
				 * .addFilterAfter( new JwtGenerationFilter(jwtService),
				 * UsernamePasswordAuthenticationFilter.class )
				 */
            
            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/users/patient/register",
                    "/api/users/auth"
                ).permitAll()
                .anyRequest().authenticated()
            )
            
            // Basic auth fallback
            .httpBasic(withDefaults());
        
        return http.build();
    }
     
    /**
     * Creates a delegating password encoder that supports multiple encoding formats.
     * 
     * <p>The encoder will automatically detect the encoding algorithm from the password prefix
     * (e.g., {bcrypt}, {pbkdf2}, {sha256}) and use the appropriate encoder.</p>
     *
     * @return PasswordEncoder that supports multiple encoding schemes
     * 
     * @see PasswordEncoderFactories#createDelegatingPasswordEncoder()
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Configures the authentication manager with custom provider.
     * 
     * @param userDetailsService service for loading user details
     * @param passwordEncoder encoder for password verification
     * @return configured AuthenticationManager instance
     * 
     * @implNote The manager is configured to retain credentials after authentication
     *           to support subsequent authentication attempts if needed.
     */
    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        UserNamePwdAuthenticationProvider authenticationProvider =
                new UserNamePwdAuthenticationProvider(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }
}