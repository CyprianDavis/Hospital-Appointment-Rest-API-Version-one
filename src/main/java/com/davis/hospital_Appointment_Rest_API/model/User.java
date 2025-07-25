package com.davis.hospital_Appointment_Rest_API.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
/**
 * Abstract base class representing a user in the Hospital Appointment System.
 * <p>
 * This entity serves as the parent class in a JOINED inheritance strategy,
 * with concrete implementations for different user types (Patient, Doctor, Admin).
 * The discriminator column "user_type" identifies the specific user subclass.
 * </p>
 * 
 * <p><b>Inheritance Strategy:</b> JOINED (separate tables for parent and subclasses)</p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Patient
 * @see Doctor
 * @see Admin
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
public abstract class User {
    
    /** Unique identifier for the user */
	@Id
    private String userId;
    
    /** Unique username for authentication */
    private String userName;
    
    /** Encrypted password (should be hashed in production) */
   
    private String passWord;
    
    /** Contact phone number in international format (e.g., +256...) */
    private String contact;
    
    /** Unique email address for communication */
    private String email;
    /**Role assigned to this user for authorization and access control.*/
    @OneToOne
    @JoinColumn(name = "roleId")
    private Role role;
    
    
    /** District/Locality of the user's address */
    
   // @JsonIgnore
    private String district;
    
    /** Street address component */
   // @JsonIgnore
    private String street;
    
    /** Postal code for physical mail */
    @Column(name = "postal_code")
    //@JsonIgnore
    private String postalCode;
    
    /**
     * Represents the current status of the user account.
     * Possible values might include: "ACTIVE", "INACTIVE", "SUSPENDED", "PENDING_VERIFICATION", etc.
     * The exact values depend on the business logic of the application.
     */
    private String status;
    /**
     * Timestamp recording when the user account was initially created in the system.
     * <p>
     * This field is automatically set to the current time when the user entity is first persisted.
     * Only stores the time component (hours, minutes, seconds) of the creation timestamp.
     * </p>
     * 
     * @see CreationTimestamp    */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdOn;

   


    /**
     * Timestamp recording the most recent update to the user's profile information.
     * <p>
     * This field should be automatically updated whenever any user profile data is modified.
     * Only stores the time component (hours, minutes, seconds) of the update timestamp.
     * </p>
     * 
     * @see @UpdateTimestamp
     */
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    /**
     * Gender identification of the user.
     * <p>
     * Expected values:
     * <ul>
     *   <li>"M" for Male</li>
     *   <li>"F" for Female</li>
     *   <li>"O" for Other/Non-binary</li>
     *   <li>null if unspecified</li>
     * </ul>
     * The field is optional and nullable to respect user privacy preferences.
     * </p>
     */
    private String gender;

    /**
     * Collection of notifications associated with this user.
     * <p>
     * Represents a one-to-many relationship where the User is the parent entity
     * and Notification is the child entity. The relationship is mapped by the
     * "user" field in the Notification entity.
     * </p>
     * 
     * <p><b>Relationship Characteristics:</b></p>
     * <ul>
     *   <li>Cascade operations are not enabled by default</li>
     *   <li>Notifications are loaded lazily when accessed</li>
     *   <li>Uses HashSet for O(1) complexity on basic operations</li>
     * </ul>
     * 
     * @see Notification
     */
    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications = new HashSet<>();

    /**
     * Collection of direct authorities (permissions) assigned to this user.
     * <p>
     * Represents a many-to-many relationship between User and Authority entities,
     * maintained through the UserAuthority join table. This allows granting specific
     * permissions to users in addition to those they inherit through their roles.
     * </p>
     *
     * <p><b>Relationship Characteristics:</b></p>
     * <ul>
     *   <li><b>Join Table:</b> "UserAuthority"</li>
     *   <li><b>Join Column:</b> "userId" (references User.userId)</li>
     *   <li><b>Inverse Join Column:</b> "authorityId" (references Authority.id)</li>
     *   <li><b>Default Fetch Type:</b> LAZY (authorities are loaded when accessed)</li>
     *   <li><b>Cascade:</b> None (default) - authorities exist independently</li>
     * </ul>
     *
     * <p><b>Usage Notes:</b></p>
     * <ul>
     *   <li>Use this for granting special permissions outside of role assignments</li>
     *   <li>Combined with role-based authorities for complete permission set</li>
     *   <li>Changes to this collection directly affect the join table</li>
     *   <li>Prefer role-based assignments for standard permission patterns</li>
     * </ul>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * // Grant authority directly
     * user.getAuthorities().add(permissionService.findByName("SPECIAL_ACCESS"));
     * </pre>
     *
     * @see Authority
     * @see jakarta.persistence.ManyToMany
     * @see jakarta.persistence.JoinTable
     * @see #roles
     */
   
    /**
     * Constructs a new User with basic information.
     * 
     * @param userName Unique username for authentication
     * @param passWord Encrypted password
     * @param contact Phone number in international format
     * @param district User's district/locality
     * @param street Street address component
     * @param postalCode Postal code for mail
     */
    public User(String userName, String passWord, String contact, 
               String district, String street, String postalCode) {
        this.userName = userName;
        this.passWord = passWord;
        this.contact = contact;
        this.district = district;
        this.street = street;
        this.postalCode = postalCode;
    }
    public User() {
    	
    }

    /**
     * Gets the unique user identifier
     * @return The user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the unique user identifier
     * @param userId The ID to set (typically auto-generated)
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the authentication username
     * @return The username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the authentication username
     * @param userName The username to set
     */
    public void setUserName(String userName) {
        
        this.userName = userName;
    }

    /**
     * Gets the encrypted password
     * @return The password hash
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets the encrypted password
     * @param passWord The password hash to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Gets the contact phone number
     * @return Phone number in international format
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the contact phone number
     * @param contact Phone number in international format (e.g., +256...)
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets the district/locality
     * @return The district name
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the district/locality
     * @param district The district name
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Gets the street address
     * @return The street name
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address
     * @param street The street name
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the postal code
     * @return The postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code
     * @param postalCode The postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the email address
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address
     * @param email The email to set (must be valid format)
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Gets the account creation timestamp
     * @return Date when user was created
     */
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the account creation timestamp
     * @param createdOn Creation date (typically auto-set)
     */
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets the last update timestamp
     * @return Date of last profile update
     */
    public LocalDateTime getLastUpdated() {
        return updatedOn;
    }

    /**
     * Sets the last update timestamp
     * @param lastUpdated Date of last update
     */
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.updatedOn = lastUpdated;
    }

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the notifications
	 */
	public Set<Notification> getNotifications() {
		return notifications;
	}

	/**
	 * @param notifications the notifications to set
	 */
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
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
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}