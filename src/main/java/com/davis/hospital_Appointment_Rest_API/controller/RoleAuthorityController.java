package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.davis.hospital_Appointment_Rest_API.model.Authority;
import com.davis.hospital_Appointment_Rest_API.model.Role;
import com.davis.hospital_Appointment_Rest_API.model.RoleAuthority;
import com.davis.hospital_Appointment_Rest_API.service.imp.AuthorityServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.RoleAuthorityServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.RoleServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * REST Controller for managing role-authority relationships.
 * <p>
 * Provides endpoints for assigning authorities to roles in the system.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-29
 */
@RestController
@RequestMapping("/api/roleAuthorities")
public class RoleAuthorityController {

    private final RoleAuthorityServiceImp roleAuthorityServiceImp;
    private final RoleServiceImp roleServiceImp;
    private final AuthorityServiceImp authorityServiceImp;

    /**
     * Constructs a new RoleAuthorityController with required services.
     * 
     * @param roleAuthorityServiceImp service for role-authority relationship operations
     * @param roleServiceImp service for role operations
     * @param authorityServiceImp service for authority operations
     */
    public RoleAuthorityController(RoleAuthorityServiceImp roleAuthorityServiceImp,
                                 RoleServiceImp roleServiceImp,
                                 AuthorityServiceImp authorityServiceImp) {
        this.roleAuthorityServiceImp = roleAuthorityServiceImp;
        this.roleServiceImp = roleServiceImp;
        this.authorityServiceImp = authorityServiceImp;
    }

    /**
     * Assigns an authority to a role.
     * <p>
     * Creates a relationship between the specified role and authority.
     * </p>
     * 
     * @param roleId the ID of the role to which the authority will be assigned
     * @param authorityId the ID of the authority to be assigned to the role
     * @return ResponseEntity containing either:
     *         - Success response with created RoleAuthority (HTTP 201) or
     *         - Error response if role/authority not found (HTTP 404) or
     *         - Error response for server errors (HTTP 500)
     * @throws IllegalArgumentException if either ID parameter is null
     */
    @PostMapping("/{roleId}/{authorityId}")
    public ResponseEntity<?> addRoleAuthority(
            @PathVariable Long roleId, 
            @PathVariable Long authorityId) {
        try {
            // Validate IDs are not null
            if (roleId == null || authorityId == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Both roleId and authorityId are required"));
            }

            Optional<Role> role = roleServiceImp.findById(roleId);  // Fixed: was using authorityId instead of roleId
            Optional<Authority> authority = authorityServiceImp.findById(authorityId);

            if (role.isEmpty() || authority.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Role or Authority not found"));
            }

            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRole(role.get());
            roleAuthority.setAuthority(authority.get());

            RoleAuthority savedRoleAuthority = roleAuthorityServiceImp.addRoleAuthority(roleAuthority);
           if(savedRoleAuthority != null) {
        	   return ResponseEntity.status(HttpStatus.CREATED)
                       .body(new ApiResponse<>(true, "Successfully assigned authority to role", savedRoleAuthority));
           }
           else{
        	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
   	                .body(new ApiResponse<>(false, "Failed to assign authority to role"));
           }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
        }
    }
}