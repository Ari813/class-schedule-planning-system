package entities;

import java.io.Serializable;

public class Faculty implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int facultyNum;
	private String faculty;
	public int getFacultyNum() {
		return facultyNum;
	}
public Faculty() {
		
	}

	public void setFacultyNum(int facultyNum) {
		this.facultyNum = facultyNum;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = new String(faculty);
	}
	
	

}
