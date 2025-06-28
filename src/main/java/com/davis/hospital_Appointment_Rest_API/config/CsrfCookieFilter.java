package com.davis.hospital_Appointment_Rest_API.config;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom CSRF token filter that ensures the CSRF token is properly processed for each request.
 * <p>
 * This filter extends {@link OncePerRequestFilter} to guarantee single execution per request.
 * It retrieves the CSRF token from the request attributes and makes it available for
 * subsequent processing in the filter chain.
 * </p>
 * 
 * <p><b>Key Responsibilities:</b></p>
 * <ul>
 *   <li>Retrieves the CSRF token from request attributes</li>
 *   <li>Ensures the token is processed before the request continues</li>
 *   <li>Works in conjunction with Spring Security's CSRF protection</li>
 * </ul>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-20
 * @see OncePerRequestFilter
 * @see CsrfToken
 */
public class CsrfCookieFilter extends OncePerRequestFilter {

    /**
     * Processes each request to ensure CSRF token handling.
     * <p>
     * This implementation retrieves the CSRF token from the request attributes,
     * which triggers token processing if needed, then continues the filter chain.
     * </p>
     * 
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @param filterChain the filter chain to continue processing
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs during request processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Retrieve CSRF token from request attributes
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        
        // Access the token to ensure it's processed (even if not used directly)
        if (csrfToken != null) {
            csrfToken.getToken();
        }
        
        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}