package entities;

import java.io.Serializable;

public class ClassesAids implements Serializable {
	private int classBuildingid;
	private int clssid;
	private int aidsid;
	
	
	public ClassesAids() {
		// TODO Auto-generated constructor stub
	}

	
	


	public ClassesAids(int classBuildingid, int clssid, int aidsid) {
		super();
		this.classBuildingid = classBuildingid;
		this.clssid = clssid;
		this.aidsid = aidsid;
	}


	public void setClassBuildingid(int classBuildingid) {
		this.classBuildingid = classBuildingid;
	}


	public int getClssid() {
		return clssid;
	}


	public void setClssid(int clssid) {
		this.clssid = clssid;
	}


	public int getAidsid() {
		return aidsid;
	}


	public void setAidsid(int aidsid) {
		this.aidsid = aidsid;
	}


}
