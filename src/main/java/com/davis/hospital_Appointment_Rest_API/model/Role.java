package com.davis.hospital_Appointment_Rest_API.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
     * Authorities/permissions granted by this role
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "RoleAuthority",
        joinColumns = @JoinColumn(name = "roleId"),
        inverseJoinColumns = @JoinColumn(name = "authorityId")
    )
    private Set<Authority> authorities = new HashSet<>();

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
     * Gets the authorities granted by this role
     * @return Set of authorities
     */
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    /**
     * Sets the authorities granted by this role
     * @param authorities The set of authorities to grant
     */
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}