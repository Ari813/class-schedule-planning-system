package MsgPackage;

import java.util.ArrayList;


import entities.Course;

public class GetAllCoursesForSchedualingPack extends MessagePack
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Course> allcourses;
	private boolean additionalInfo;

	public ArrayList<Course> getAllclass()
	{
		return allcourses;
	}

	public void setAllclass(ArrayList<Course> allcourses)
	{
		this.allcourses = new ArrayList<Course>(allcourses);
	}

	public GetAllCoursesForSchedualingPack()
	{
		super();
		this.op = OpType.GetAllCoursesForSchedualing;

	}

	/**
	 * @return the bringLecturers
	 */
	public boolean isBringAdditionalInfo()
	{
		return additionalInfo;
	}

	/**
	 * @param bringLecturers
	 *            the bringLecturers to set
	 */
	public void setAdditionalInfo()
	{
		this.additionalInfo = true;
	}

	public void clrAdditionalInfo()
	{
		this.additionalInfo = false;
	}
}
