package MsgPackage;

import MsgPackage.MessagePack.OpType;
import entities.Course;

public class UpdateCoursePack extends MessagePack {

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

	public UpdateCoursePack() {
		super();
		this.op = OpType.updateCourse;

	}
}