package com.davis.hospital_Appointment_Rest_API.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.davis.hospital_Appointment_Rest_API.service.imp.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtGenerationFilter extends OncePerRequestFilter{
	  private final JwtService jwtService;
	  
	  public JwtGenerationFilter(JwtService jwtService) {
		  this.jwtService =jwtService;
	  }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // Get the current authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Check if authentication is successful (not null and authenticated)
        if (authentication != null && authentication.isAuthenticated()) {
            // Generate JWT token
            String jwtToken = jwtService.generateJwtToken(authentication);
            
            // Add token to response header
            response.setHeader("Authorization", "Bearer " + jwtToken);
            
            // Optionally generate refresh token if needed
            String refreshToken = jwtService.generateRefreshToken(authentication);
            response.setHeader("Refresh-Token", refreshToken);
        }
        
        // Continue the filter chain
        filterChain.doFilter(request, response);
		
	}

}
