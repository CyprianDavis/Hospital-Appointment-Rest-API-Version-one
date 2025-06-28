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



@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
    	http.securityContext(ContextConfig -> ContextConfig.requireExplicitSave(false))
    		.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
    		.csrf(csrfConfig -> csrfConfig
    			    .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler) // Use custom handler
    			    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // Store CSRF token in cookies
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)

           .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register","/error","/api/userRoles/role").permitAll()  // Allow public registration
                .anyRequest().permitAll()                        // Secure everything else
            )
            .httpBasic(withDefaults()); // Basic auth for simplicity (for now)

        // Comment this out if you're building a pure API with no form login
        // http.formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
