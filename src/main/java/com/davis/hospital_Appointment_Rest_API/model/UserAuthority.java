package com.davis.hospital_Appointment_Rest_API.model;

import jakarta.persistence.*;

/**
 * Represents the join table between Users and Authorities.
 * <p>
 * This entity maps to the 'UserAuthority' table and establishes
 * a many-to-many relationship between users and authorities.
 * </p>
 * @author CYPRIAN DAVIS
 */
@Entity
@Table(name = "UserAuthority")
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorityId", referencedColumnName = "id", nullable = false)
    private Authority authority;

    // Constructors, getters, setters
    public UserAuthority() {}

    public UserAuthority(User user, Authority authority) {
        this.user = user;
        this.authority = authority;
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the authority
	 */
	public Authority getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

  
}