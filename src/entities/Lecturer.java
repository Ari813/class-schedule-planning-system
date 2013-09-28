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

public class Lecturer extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String position;
	private String assignmentYear;

	/**
	 * constructor
	 * 
	 * @param lecturerID
	 * @param password
	 * @param firstName
	 * @param surName
	 * @param authorizationLevel
	
	
	 * @param position
	
	 */
	public Lecturer(int lecturerID, String password, String firstName,
			String surName, String authorizationLevel, String position,
			String assignmentYear) {
		super(lecturerID, password, firstName, surName, authorizationLevel);
		this.position = position;
		this.assignmentYear = assignmentYear;
	}

	/**
	 * empty constructor
	 */
	public Lecturer() {

	}

	

	// ------------------
	// Setters & Getters
	// ------------------

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the assignmentYear
	 */
	public String getAssignmentYear() {
		return assignmentYear;
	}

	/**
	 * @param assignmentYear
	 *            the assignmentYear to set
	 */
	public void setAssignmentYear(String assignmentYear) {
		this.assignmentYear = assignmentYear;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
