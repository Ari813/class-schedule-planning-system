package MsgPackage;

import java.util.ArrayList;

import MsgPackage.MessagePack.OpType;
import entities.Lecturer;
import entities.StudyAids;

public class GetAvailableLecturersPack extends MessagePack {
	private static final long serialVersionUID = 1L;
	private ArrayList<Lecturer> allLecturer;
	
	public GetAvailableLecturersPack() {
		super();
		this.op = OpType.GetLecturersInfo;
	}

	public ArrayList<Lecturer> getAllLecturer() {
		return allLecturer;
	}

	public void setAllLecturer(ArrayList<Lecturer> allLecturer) {
		this.allLecturer = allLecturer;
	}

}
