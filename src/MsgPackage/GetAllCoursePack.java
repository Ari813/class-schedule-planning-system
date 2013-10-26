package MsgPackage;

import java.util.ArrayList;

import entities.Course;

public class GetAllCoursePack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Course> allcourses;
	private boolean additionalInfo;

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

	/**
	 * @return the bringLecturers
	 */
	public boolean isBringAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * @param bringLecturers the bringLecturers to set
	 */
	public void setAdditionalInfo(boolean additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}
