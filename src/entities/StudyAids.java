package entities;

import java.io.Serializable;

public class StudyAids implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int aidsID;

	public StudyAids(int aidsID, String aidsName) {
		super();
		this.aidsID = aidsID;
		this.aidsName = aidsName;
	}

	private String aidsName;

	public int getAidsID() {
		return aidsID;
	}

	public void setAidsID(int aidsID) {
		this.aidsID = aidsID;
	}

	public String getAidsName() {
		return aidsName;
	}

	public void setAidsName(String aidsName) {
		this.aidsName = aidsName;
	}

	public StudyAids() {

	}

}
