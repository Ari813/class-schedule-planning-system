package MsgPackage;

import entities.Lecturer;

public class UpdateLecturerPack extends MessagePack {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Lecturer newLecturer;

	public Lecturer getNewLecturer() {
		return newLecturer;
	}

	public void setNewLecturer(Lecturer newClass) {
		this.newLecturer = newClass;
	}

	public UpdateLecturerPack() {
		super();
		this.op = OpType.updateLectuer;

	}
}
