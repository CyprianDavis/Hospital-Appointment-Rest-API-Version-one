package com.davis.hospital_Appointment_Rest_API.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Custom authentication provider that handles username/password authentication.
 * Implements Spring Security's {@link AuthenticationProvider} interface to provide
 * custom authentication logic against a user database.
 * @author CYPRIAN DAVIS
 */
public class UserNamePwdAuthenticationProvider implements AuthenticationProvider {

    /**
     * Service for loading user-specific data during authentication.
     * Injected by Spring's dependency injection mechanism.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Password encoder used to verify submitted passwords against stored encoded passwords.
     * Typically configured as a BCryptPasswordEncoder in the security configuration.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Authenticates a user based on username and password credentials.
     *
     * @param authentication the authentication request object containing user credentials
     * @return a fully authenticated Authentication object containing user details and authorities
     * @throws AuthenticationException if authentication fails (invalid credentials, user not found, etc.)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Extract username from authentication request
        String userName = authentication.getName();
        
        // Extract raw password from authentication request
        String password = authentication.getCredentials().toString();
        
        // Load user details from the database using the username
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        
        // Verify the provided password matches the stored encoded password
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // If passwords match, create and return a fully authenticated token
            return new UsernamePasswordAuthenticationToken(
                userName, 
                password, // Note: In production, you might want to set this to null after authentication
                userDetails.getAuthorities() // Include the user's granted authorities/roles
            );
        } else {
            // Throw exception if password doesn't match
            throw new BadCredentialsException("Invalid Credentials");
        }
    }

    /**
     * Indicates whether this AuthenticationProvider supports the specified authentication type.
     *
     * @param authentication the authentication class to check
     * @return true if this provider supports the specified authentication class
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // This provider only supports UsernamePasswordAuthenticationToken authentication requests
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}