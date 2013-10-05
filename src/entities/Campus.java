package entities;

public class Campus {
	private int campusId;
	private String campusName;
	public Campus() {
		// TODO Auto-generated constructor stub
	}
	
	public Campus(int campusId, String campusName) {
		super();
		this.campusId = campusId;
		this.campusName = campusName;
	}
	
	public int getCampusId() {
		return campusId;
	}
	
	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

}
