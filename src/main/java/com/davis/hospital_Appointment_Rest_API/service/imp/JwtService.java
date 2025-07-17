package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Service class for JWT (JSON Web Token) creation, validation, and processing.
 * Handles both access tokens and refresh tokens generation and verification.
 * @author CYPRIAN DAVIS
 */
@Service
public class JwtService {
    
    /** The secret key used for signing JWTs */
    private final SecretKey signingKey;
    
    /** Expiration time for access tokens in milliseconds */
    private final long accessTokenExpiration;
    
    /** Expiration time for refresh tokens in milliseconds (typically double the access token expiration) */
    private final long refreshTokenExpiration;
    
  
    /**
     * Constructs a new JwtService with the provided secret key and token expiration.
     * 
     * @param secretKey The secret key used for signing JWTs (injected from properties)
     * @param accessTokenExpiration The expiration time for access tokens in milliseconds
     */
    public JwtService(@Value("${jwt.secret}") String secretKey, 
            @Value("${jwt.expiration}") long accessTokenExpiration
           ) {
        // Convert the secret key string into a cryptographic key
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiration = accessTokenExpiration;
        // Refresh tokens last twice as long as access tokens
        this.refreshTokenExpiration = accessTokenExpiration * 2;
      
    }
    
    /**
     * Generates a JWT access token for the authenticated user.
     * 
     * @param authentication The authentication object containing user details
     * @return A signed JWT access token as a String
     */
    public String generateJwtToken(Authentication authentication) {
        Instant now = Instant.now();
        
        return Jwts.builder()
                .issuer("Davis Hospital")  // Identifies the issuer of the token
                .subject("JWT Token")      // Subject of the token
                // Include username in claims
                .claim("UserName", authentication.getName())
                // Include comma-separated list of authorities
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .issuedAt(Date.from(now))  // Token creation time
                .expiration(Date.from(now.plusMillis(accessTokenExpiration)))  // Token expiration time
                .signWith(signingKey)        // Sign with our secret key
                .compact();                 // Build and serialize to compact string
    }
    
    /**
     * Extracts the username (subject) from the JWT token.
     * 
     * @param token The JWT token to parse
     * @return The username extracted from the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    /**
     * Extracts the expiration date from the JWT token.
     * 
     * @param token The JWT token to parse
     * @return The expiration date of the token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    /**
     * Extracts authorities from the JWT token claims.
     * 
     * @param token The JWT token to parse
     * @return List of granted authorities
     */
    public List<GrantedAuthority> extractAuthorities(String token) {
        Claims claims = extractAllClaims(token);
        String authoritiesStr = claims.get("authorities", String.class);
        
        if (authoritiesStr == null || authoritiesStr.isEmpty()) {
            return Collections.emptyList();
        }
        
        return Arrays.stream(authoritiesStr.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    
    
    /**
     * Validates whether a JWT token is valid for the given user details.
     * 
     * @param token The JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean isTokenValid(String token) {
        // Check if username matches and token isn't expired
        return  !isTokenExpired(token);
    }
    
    /**
     * Checks if a token has expired.
     * 
     * @param token The JWT token to check
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    /**
     * Generic method to extract a specific claim from the token.
     * 
     * @param <T> The type of the claim to extract
     * @param token The JWT token to parse
     * @param claimsResolver Function to extract the specific claim
     * @return The extracted claim value
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * Generates a refresh token with extended expiration time.
     * 
     * @param authentication The authentication object containing user details
     * @return A signed JWT refresh token as a String
     */
    public String generateRefreshToken(Authentication authentication) {
        Instant now = Instant.now();
        
        return Jwts.builder()
                .issuer("Davis Hospital")
                .subject(authentication.getName())  // Subject is the username
                .claim("username", authentication.getName())
                .claim("token_type", "refresh")    // Explicitly mark as refresh token
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(refreshTokenExpiration)))  // Longer expiration
                .signWith(signingKey)
                .compact();
    }
    
    /**
     * Extracts all claims from the JWT token.
     * 
     * @param token The JWT token to parse
     * @return All claims contained in the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)       // Verify using our secret key
                .build()
                .parseSignedClaims(token)      // Parse and verify signature
                .getPayload();                // Extract the claims body
    }
}