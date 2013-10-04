package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Class implements Serializable {
	

	public ArrayList<StudyAids> studyAids;
	public int classID;
	public String building;
	
	public int capcity;
	public int available;
	public int campus;
	


	public Class() {
		
	}
	


	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	public ArrayList<StudyAids> getStudyAids() {
		return studyAids;
	}

	public void setStudyAids(ArrayList<StudyAids> sA) {
		studyAids = sA;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
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

}
