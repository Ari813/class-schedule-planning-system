package entities;

/**
 * @author Omri Barda		039725890 
 * @author Amit Joseph		034608547
 * @version Oct 2013
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String position;
	private String assignmentYear;
	private boolean hasCoursesInfo;
	private ArrayList<Course> LecturerCourses;
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
	public Lecturer(int lecturerID, String password, String LecturerName,
			String surName, String authorizationLevel, String position,
			String assignmentYear) {
		super(lecturerID, password, LecturerName, authorizationLevel);
		this.position = position;
		this.assignmentYear = assignmentYear;
		LecturerCourses= new ArrayList<Course>();
		hasCoursesInfo = false;
	}

	/**
	 * empty constructor
	 */
	public Lecturer() {
		LecturerCourses= new ArrayList<Course>();
		hasCoursesInfo = false;
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

	/**
	 * @return the lecturerCourses
	 */
	public ArrayList<Course> getLecturerCourses() {
		return LecturerCourses;
	}

	/**
	 * @param lecturerCourses the lecturerCourses to set
	 */
	public void setLecturerCourses(ArrayList<Course> lecturerCourses) {
		LecturerCourses = lecturerCourses;
		hasCoursesInfo = !LecturerCourses.isEmpty();
	}
	
	public void addCourse(Course Crs) {
		LecturerCourses.add(Crs);
		hasCoursesInfo = true;
	}

	/**
	 * @return the hasCoursesInfo
	 */
	public Boolean getHasCoursesInfo() {
		return hasCoursesInfo;
	}

	/**
	 * @param hasCoursesInfo the hasCoursesInfo to set
	 */
	public void setHasCoursesInfo( ) {
		this.hasCoursesInfo = true;
	}
	public void clrHasCoursesInfo( ) {
		this.hasCoursesInfo = false;
	}
	
	
}
