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
 * Represents an authority/role in the system.
 * <p>
 * This entity maps to the 'Authorities' table and maintains a many-to-many
 * relationship with users through the UserAuthority join entity.
 * @author CYPRIAN DAVIS
 * </p>
 */
@Entity
@Table(name = "Authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 40, nullable = false, unique = true)
    private String name;

    /**
     * The roles associated with this authority.
     * Mapped by the 'authority' field in the RoleAuthority entity.
     */
    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleAuthority> roles = new HashSet<>();

    // Constructors, getters, setters
    public Authority() {}

    public Authority(String name) {
        this.name = name;
    }

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
	 * @return the roles
	 */
	public Set<RoleAuthority> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<RoleAuthority> roles) {
		this.roles = roles;
	}


    

}