package MsgPackage;

import java.util.ArrayList;

import entities.Lecturer;;

public class GetAllLecturersPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private ArrayList<Lecturer> allLecturers;
	
	public ArrayList<Lecturer> getAllFaculty() {
		return allLecturers;
	}

	public void setAllLecturers(ArrayList<Lecturer> allLecturers) {
		this.allLecturers = allLecturers;
	}

	public GetAllLecturersPack() {
		super();
		this.op = OpType.GetLecturersInfo;
	}

}
