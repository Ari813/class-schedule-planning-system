package entities;

public class StudyAids {
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
