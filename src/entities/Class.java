package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Class implements Serializable {
	public ArrayList<StadyAids> stadyAids;
	public int classID;
	public String building;
	public Boolean Occupied;
	public int capcity;
	
	public Class() {
		
	}

	public ArrayList<StadyAids> getSA() {
		return stadyAids;
	}

	public void setSA(ArrayList<StadyAids> sA) {
		stadyAids = sA;
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

	public Boolean getOccupied() {
		return Occupied;
	}

	public void setOccupied(Boolean occupied) {
		Occupied = occupied;
	}

	public int getCapcity() {
		return capcity;
	}

	public void setCapcity(int capcity) {
		this.capcity = capcity;
	}

}
