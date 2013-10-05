package MsgPackage;

import java.util.ArrayList;

import MsgPackage.MessagePack.OpType;
import entities.*;

public class GetStudyAidsPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<StudyAids> allStudyAids;

	public GetStudyAidsPack() {
		super();
		this.op = OpType.GetStudyAidsInfo;
	}

	/**
	 * @return the allStudyAids
	 */
	public ArrayList<StudyAids> getAllStudyAids() {
		return allStudyAids;
	}

	/**
	 * @param allStudyAids
	 *            the allStudyAids to set
	 */
	public void setAllStudyAids(ArrayList<StudyAids> allStudyAids) {
		this.allStudyAids = new ArrayList<StudyAids>(allStudyAids);
	}

}
