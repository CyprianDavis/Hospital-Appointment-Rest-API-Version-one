package com.davis.hospital_Appointment_Rest_API.filter;

import java.io.IOException;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.davis.hospital_Appointment_Rest_API.service.imp.JwtService;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT validation filter that intercepts incoming requests to validate JWT tokens.
 * This filter is responsible for:
 * <ul>
 *   <li>Extracting JWT tokens from Authorization headers</li>
 *   <li>Validating token authenticity and expiration</li>
 *   <li>Setting up Spring Security context for authenticated users</li>
 *   <li>Returning standardized error responses for invalid tokens</li>
 * </ul>
 * 
 * <p>This filter should be placed before authorization filters in the Spring Security filter chain.</p>
 * 
 * @author CYPRIAN DAVIS
 * 
 * @see OncePerRequestFilter
 * @see JwtService
 */
public class JwtValidationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new JwtValidationFilter with required dependencies.
     * 
     * @param jwtService The JWT service for token validation and parsing
     * @param objectMapper The JSON object mapper for response serialization
     */
    public JwtValidationFilter(JwtService jwtService, ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }

    /**
     * Processes each incoming request to validate JWT tokens.
     * 
     * <p>This method:
     * <ol>
     *   <li>Extracts JWT from Authorization header</li>
     *   <li>Validates token using JwtService</li>
     *   <li>Sets up Spring Security context if valid</li>
     *   <li>Returns appropriate error responses for invalid tokens</li>
     * </ol>
     * 
     * @param request The incoming HTTP request
     * @param response The HTTP response
     * @param filterChain The filter chain to continue processing
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   FilterChain filterChain) throws ServletException, IOException {
        
        try {
            String jwt = parseJwt(request);
            
            if (jwt != null) {
                if (jwtService.isTokenValid(jwt, null)) {
                    String username = jwtService.extractUsername(jwt);
                    
                    List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(jwt)
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                        .collect(Collectors.toList());
                    
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(
                            username, 
                            null, 
                            authorities);
                    
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    sendErrorResponse(response, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
                    return;
                }
            }
        } catch (ExpiredJwtException e) {
            sendErrorResponse(response, "JWT token has expired", HttpStatus.UNAUTHORIZED);
            return;
        } catch (JwtException | IllegalArgumentException e) {
            sendErrorResponse(response, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
            return;
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
            sendErrorResponse(response, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }
        
        filterChain.doFilter(request, response);
    }

    /**
     * Sends a standardized error response in JSON format.
     * 
     * @param response The HTTP response to modify
     * @param message The error message to include
     * @param status The HTTP status code to set
     * @throws IOException if an I/O error occurs during response writing
     */
    private void sendErrorResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        ApiResponse<Void> apiResponse = ApiResponse.error(message);
        objectMapper.writeValue(response.getWriter(), apiResponse);
    }

    /**
     * Extracts the JWT token from the Authorization header.
     * 
     * @param request The HTTP request containing the Authorization header
     * @return The extracted JWT token or null if not present/invalid format
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        return StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ") 
            ? headerAuth.substring(7) 
            : null;
    }

    /**
     * Determines which requests should bypass JWT validation.
     * 
     * @param request The incoming HTTP request
     * @return true if the request path matches excluded endpoints, false otherwise
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/auth/login") || 
               request.getServletPath().equals("/api/auth/refresh-token");
    }
}