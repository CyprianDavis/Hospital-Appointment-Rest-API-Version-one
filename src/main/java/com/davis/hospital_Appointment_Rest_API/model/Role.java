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

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    // Many-to-Many relationship with Users
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    // Many-to-Many relationship with Authorities
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "RoleAuthority",
        joinColumns = @JoinColumn(name = "roleId"),
        inverseJoinColumns = @JoinColumn(name = "authorityId")
    )
    private Set<Authority> authorities = new HashSet<>();

    // Constructors
    public Role() {
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * @return the authorities
	 */
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
    
}
