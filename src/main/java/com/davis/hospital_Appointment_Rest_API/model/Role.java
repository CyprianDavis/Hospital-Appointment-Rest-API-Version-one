package com.davis.hospital_Appointment_Rest_API.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity class representing a Role in the system.
 * <p>
 * Roles are used to group authorities/permissions and assign them to users.
 * This class maps to the "Roles" table in the database.
 * </p>
 */
@Entity
@Table(name = "Roles")
public class Role {

    /**
     * Unique identifier for the role
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the role (must be unique)
     */
    @Column(name = "name", length = 40, nullable = false, unique = true)
    private String name;

    /**
     * Description of the role's purpose
     */
    @Column(name = "description", length = 200)
    private String description;

    /**
     * The authorities associated with this role.
     * Mapped by the 'role' field in the RoleAuthority entity.
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleAuthority> authorities = new HashSet<>();
    
   

    /**
     * Default constructor
     */
    public Role() {
    }

    /**
     * Constructor with name and description
     * @param name The name of the role
     * @param description The description of the role
     */
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the role ID
     * @return The role ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the role ID
     * @param id The ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the role name
     * @return The role name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the role name
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the role description
     * @return The role description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the role description
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

	/**
	 * @return the authorities
	 */
	public Set<RoleAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<RoleAuthority> authorities) {
		this.authorities = authorities;
	}

   
}