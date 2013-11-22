package MsgPackage;

import entities.Course;

public class NewCoursePack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Course newCourse;

	public Course getNewCourse() {
		return newCourse;
	}

	public void setNewCourse(Course newClass) {
		this.newCourse = newClass;
	}

	public NewCoursePack() {
		super();
		this.op = OpType.newCourse;

	}
}
