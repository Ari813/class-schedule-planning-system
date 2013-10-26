package MsgPackage;

import java.util.ArrayList;

import entities.Course;

public class GetAllCoursePack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Course> allcourses;
	private boolean bringLecturers;

	public ArrayList<Course> getAllclass() {
		return allcourses;
	}

	public void setAllclass(ArrayList<Course> allcourses) {
		this.allcourses = new ArrayList<Course>(allcourses);
	}

	public GetAllCoursePack() {
		super();
		this.op = OpType.GetAllCourses;

	}
}
