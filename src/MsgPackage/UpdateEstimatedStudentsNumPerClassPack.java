package MsgPackage;

import java.util.ArrayList;
import java.util.Map;

import entities.Course;

public class UpdateEstimatedStudentsNumPerClassPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<Integer, ArrayList<Course>> CoursePerFuculty ;
	private Boolean Success; 

	public UpdateEstimatedStudentsNumPerClassPack() {
		super();
		Success=false;
		this.op = OpType.UpdateEstimatedStudentsNumPerClass;

	}

	/**
	 * @return the coursePerFuculty
	 */
	public Map<Integer, ArrayList<Course>> getCoursePerFucultyMap() {
		return CoursePerFuculty;
	}

	/**
	 * @param coursePerFuculty the coursePerFuculty to set
	 */
	public void setCoursePerFucultyMap(Map<Integer, ArrayList<Course>> coursePerFuculty) {
		CoursePerFuculty = coursePerFuculty;
	}

	/**
	 * @return the success
	 */
	public boolean isSucceed() {
		return Success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSucceed() {
		Success = new Boolean (true);
	}
	public void setFailed() {
		Success = new Boolean (false);
	}
}
