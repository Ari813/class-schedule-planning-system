package MsgPackage;

import java.util.ArrayList;

import entities.Lecturer;;

public class GetAllLecturersPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean additionalInfo;
	private ArrayList<Lecturer> allLecturers;
	
	public ArrayList<Lecturer> getAllLecturers() {
		return allLecturers;
	}

	public void setAllLecturers(ArrayList<Lecturer> allLecturers) {
		this.allLecturers = allLecturers;
	}

	public GetAllLecturersPack() {
		super();
		this.op = OpType.GetLecturersInfo;
	}

	/**
	 * @return the bringCourses
	 */
	public boolean isBringAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * @param bringLecturers the bringLecturers to set
	 */
	public void setAdditionalInfo() {
		this.additionalInfo = true;
	}
	
	public void clrAdditionalInfo() {
		this.additionalInfo = false;
	}

}
