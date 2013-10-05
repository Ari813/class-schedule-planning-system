package MsgPackage;

import java.util.ArrayList;

import MsgPackage.MessagePack.OpType;
import entities.Campus;

public class GetCampusPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Campus> allCampuses;

	public GetCampusPack() {
		super();
		this.op = OpType.GetCampusInfo;
	}

	/**
	 * @return the allCampuses
	 */
	public ArrayList<Campus> getAllCampuses() {
		return allCampuses;
	}

	/**
	 * @param allCampuses
	 *            the allCampuses to set
	 */
	public void setAllCampuses(ArrayList<Campus> allCampuses) {
		this.allCampuses = allCampuses;
	}

}
