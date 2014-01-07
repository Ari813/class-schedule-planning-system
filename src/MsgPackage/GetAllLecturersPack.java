package MsgPackage;

import java.util.ArrayList;

import entities.Lecturer;

;

public class GetAllLecturersPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum getInformation {

		all, courses, schedual, nothing
	};
	private boolean getOnlyAvialable;

	private getInformation additionalInfo;
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
		additionalInfo = getInformation.nothing;
		getOnlyAvialable = false;
	}

	/**
	 * @return the bringCourses
	 */
	public getInformation isBringAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * @param bringLecturers
	 *            the bringLecturers to set
	 */
	public void setAdditionalInfo(getInformation additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void clrAdditionalInfo() {
		this.additionalInfo = getInformation.nothing;
	}

	/**
	 * @return the getOnlyAvialable
	 */
	public boolean getOnlyAvialable()
	{
		return getOnlyAvialable;
	}

	/**
	 * @param getOnlyAvialable the getOnlyAvialable to set
	 */
	public void setGetOnlyAvialable()
	{
		this.getOnlyAvialable = true;
	}
	
	public void clrGetOnlyAvialable()
	{
		this.getOnlyAvialable = false;
	}

}
