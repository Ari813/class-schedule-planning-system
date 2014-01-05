package entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Class implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<StudyAids> studyAids;
	private int classID;
	private int building;
	private String description;
	private Building uml;
	private int capcity;
	private Boolean available;
	private int campus;

	public Class() {
		studyAids = new ArrayList<StudyAids>();

	}

	public Boolean getAvailable() {
		return available;
	}
	
	public int getAvailableInt() {
		if (available)
			return 1;
		else 
			return 0;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	public void setAvailable(int available) {
		if (available==1)
			this.available = true;
		else 
			this.available = false;	
		
	}

	public ArrayList<StudyAids> getStudyAids() {
		return studyAids;
	}

	public void setStudyAids(ArrayList<StudyAids> studyAids) {
		this.studyAids = studyAids;
	}

	public void addStudyAid(StudyAids studyAid) {
		this.studyAids.add(studyAid);
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public int getBuilding() {
		return building;
	}

	public void setBuilding(int building) {
		this.building = building;
	}

	public int getCapcity() {
		return capcity;
	}

	public void setCapcity(int capcity) {
		this.capcity = capcity;
	}

	public int getCampus() {
		return campus;
	}

	public void setCampus(int campus) {
		this.campus = campus;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = new String(description);
	}

}
