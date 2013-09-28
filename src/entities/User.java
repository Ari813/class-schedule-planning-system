package entities;

/**
 * @author Omri Barda		039725890 
 * @author Amit Joseph		034608547
 * @author Gilad Shpigel  	300162393
 * @author Elad Elbaz		040539959
 * @version June 2012
 */

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer ID;
	private String password;
	private String firstName;
	private String surName;
	private String authorizationLevel;
	

	/**
	 * constructor
	 * 
	 * @param ID
	 * @param password
	 * @param firstName
	 * @param surName
	 * @param authorizationLevel
	 * @param address
	 * @param dateOfBirth
	 * @param phoneNumber
	 */
	public User(Integer ID, String password, String firstName, String surName,
			String authorizationLevel) {
		super();
		this.ID = new Integer(ID);
		this.password = password;
		this.firstName = firstName;
		this.surName = surName;
		this.authorizationLevel = authorizationLevel;
		
	}

	/**
	 * empty constructor
	 */
	public User() {

	}

	// ------------------
	// Setters & Getters
	// ------------------
	/**
	 * 
	 * @return user ID
	 */
	public Integer getID() {
		return ID;
	}

	/**
	 * 
	 * @param ID
	 *            of user
	 */
	public void setID(int ID) {
		this.ID = new Integer(ID);
	}

	/**
	 * 
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 *            of user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return user first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            of user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return user sur name
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * 
	 * @param surName
	 *            of user
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * 
	 * @return user authorization level
	 */
	public String getAuthorizationLevel() {
		return authorizationLevel;
	}

	/**
	 * 
	 * @param authorizationLevel
	 *            of user
	 */
	public void setAuthorizationLevel(String authorizationLevel) {
		this.authorizationLevel = authorizationLevel;
	}

	

}
