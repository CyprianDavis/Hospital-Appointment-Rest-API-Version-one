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
 * A custom security filter for validating JWT tokens in incoming HTTP requests.
 *
 * <p>This filter performs the following tasks:</p>
 * <ul>
 *   <li>Extracts JWT tokens from the Authorization header</li>
 *   <li>Validates token authenticity, expiration, and structure</li>
 *   <li>Populates the Spring Security context with authenticated user details</li>
 *   <li>Generates consistent JSON error responses for invalid or expired tokens</li>
 * </ul>
 *
 * <p>This filter should be registered before any authorization filters in the Spring Security chain.</p>
 *
 * @author CYPRIAN DAVIS
 * @see OncePerRequestFilter
 * @see JwtService
 */
public class JwtValidationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;
  


    /**
     * Constructs a {@code JwtValidationFilter} with required dependencies.
     *
     * @param jwtService    the JWT service for token parsing and validation
     * @param objectMapper  the JSON object mapper used to serialize error responses
     */
    public JwtValidationFilter(JwtService jwtService, ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
        
    }

    /**
     * Validates JWT tokens for each HTTP request and sets up the Spring Security context.
     *
     * <p>Steps performed:</p>
     * <ol>
     *   <li>Extracts the JWT from the Authorization header</li>
     *   <li>Validates the token’s signature and expiration</li>
     *   <li>If valid, extracts the username and authorities and sets up authentication context</li>
     *   <li>Otherwise, sends an appropriate error response</li>
     * </ol>
     *
     * @param request      the incoming HTTP request
     * @param response     the HTTP response
     * @param filterChain  the remaining filter chain
     * @throws ServletException in case of servlet-related issues
     * @throws IOException      in case of I/O errors during processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);

            if (jwt != null) {
            		String username = jwtService.extractUsername(jwt);
            		 
                if (jwtService.isTokenValid(jwt)) {
                    

                    List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(jwt)
                        .stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                        .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);

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
            sendErrorResponse(response, "Malformed or invalid JWT token", HttpStatus.UNAUTHORIZED);
            return;
        } catch (Exception e) {
        		e.printStackTrace();
            sendErrorResponse(response, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Constructs and writes a standardized error response in JSON format.
     *
     * @param response the HTTP response to populate
     * @param message  the error message to include
     * @param status   the HTTP status to set on the response
     * @throws IOException if an error occurs while writing the response
     */
    private void sendErrorResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<Void> apiResponse = ApiResponse.error(message);
        objectMapper.writeValue(response.getWriter(), apiResponse);
    }

    /**
     * Extracts the JWT token from the request's Authorization header.
     *
     * @param request the incoming HTTP request
     * @return the JWT token string, or {@code null} if not found or improperly formatted
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        return (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer "))
            ? headerAuth.substring(7)
            : null;
    }

    /**
     * Determines whether this filter should be skipped for a specific request.
     *
     * <p>This can be used to exclude login or token refresh endpoints from JWT validation.</p>
     *
     * @param request the incoming HTTP request
     * @return {@code true} if the request should bypass this filter, {@code false} otherwise
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/auth");
        // Add more paths if needed: || request.getServletPath().equals("/api/auth/refresh-token")
    }
}
