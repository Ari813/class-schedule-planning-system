package MsgPackage;

import java.util.ArrayList;

import MsgPackage.MessagePack.OpType;
import entities.*;

public class GetBuildingsPack extends MessagePack {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Building> allBuildings;

	public GetBuildingsPack() {
		this.op = OpType.GetBuildingsInfo;
	}

	/**
	 * @param op
	 *            the op to set
	 */
	public void setOp(OpType op) {
		this.op = op;
	}

	/**
	 * @return the allBuildings
	 */
	public ArrayList<Building> getAllBuildings() {
		return allBuildings;
	}

	/**
	 * @param allBuildings the allBuildings to set
	 */
	public void setAllBuildings(ArrayList<Building> allBuildings) {
		this.allBuildings = allBuildings;
	}

}
