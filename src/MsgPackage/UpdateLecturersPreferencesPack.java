package MsgPackage;

import java.util.ArrayList;

import entities.Lecturer;

public class UpdateLecturersPreferencesPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean Success; 
	
	private ArrayList<Lecturer> allLecturers;

	public ArrayList<Lecturer> getAllLecturers() {
		return allLecturers;
	}

	public void setAllLecturers(ArrayList<Lecturer> allLecturers) {
		this.allLecturers = allLecturers;
	}

	public UpdateLecturersPreferencesPack() {
		super();
		this.op = OpType.UpdateLecturersPreferences;
	}
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

