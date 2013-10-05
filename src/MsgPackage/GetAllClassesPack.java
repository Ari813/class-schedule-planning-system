package MsgPackage;

import java.util.ArrayList;

public class GetAllClassesPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<entities.Class> allclass;

	public ArrayList<entities.Class> getAllclass() {
		return allclass;
	}

	public void setAllclass(ArrayList<entities.Class> allclass) {
		this.allclass = allclass;
	}

	public GetAllClassesPack() {
		super();
		this.op = OpType.GetAllClasses;

	}

}
