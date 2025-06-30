package com.davis.hospital_Appointment_Rest_API.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
/**
 * @author CYPRIAN DAVIS
 */
@Entity
public class RoleAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	@ManyToOne
	@JoinColumn(name = "authorityId")
	private Authority authority;
	/**
	 * @param role
	 * @param authority
	 */
	public RoleAuthority(Role role, Authority authority) {
		this.role = role;
		this.authority = authority;
	}
	public RoleAuthority() {
		
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
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
