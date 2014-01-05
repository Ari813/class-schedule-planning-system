package entities;

import java.io.Serializable;

public class Building implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int buildingID;
  private String buildingName;
  private Campus umlCampus;
	public Building() {
		// TODO Auto-generated constructor stub
	}

	public Building(int buildingID, String buildingName) {
		super();
		this.buildingID = buildingID;
		this.buildingName = buildingName;
	}

	public int getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

}
