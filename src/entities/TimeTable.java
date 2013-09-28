package entities;

import java.io.Serializable;

import javax.swing.Spring;

public class TimeTable implements Serializable {
	private String  day;
	private int hour;
	private Faculty faculty;
	
	
	
	
	
	
	
public TimeTable(String day, int hour, Faculty faculty) {
		super();
		this.day = day;
		this.hour = hour;
		this.faculty = faculty;
	}
public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	


}
