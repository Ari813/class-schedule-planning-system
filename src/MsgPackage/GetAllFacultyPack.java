package MsgPackage;

import java.util.ArrayList;

import MsgPackage.MessagePack.OpType;
import entities.Course;
import entities.Faculty;

public class GetAllFacultyPack  extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private ArrayList<Faculty> allFaculty;
	
	public ArrayList<Faculty> getAllFaculty() {
		return allFaculty;
	}

	public void setAllFaculty(ArrayList<Faculty> allFaculty) {
		this.allFaculty = allFaculty;
	}

	public GetAllFacultyPack() {
		super();
		this.op = OpType.GetAllFaculty;
	}

}
