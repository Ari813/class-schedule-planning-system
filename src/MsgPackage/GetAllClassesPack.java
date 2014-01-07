package MsgPackage;

import java.util.ArrayList;

public class GetAllClassesPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean onlyAvailable;
	private ArrayList<entities.Class> allclass;

	public ArrayList<entities.Class> getAllclass() {
		return allclass;
	}

	public void setAllclass(ArrayList<entities.Class> allclass) {
		this.allclass = new ArrayList<entities.Class>(allclass);
	}

	public GetAllClassesPack() {
		super();
		this.op = OpType.GetAllClasses;
		onlyAvailable = false;

	}

	/**
	 * @return the onlyAvailable
	 */
	public boolean getOnlyAvailable()
	{
		return onlyAvailable;
	}

	/**
	 * @param onlyAvailable the onlyAvailable to set
	 */
	public void setOnlyAvailable()
	{
		this.onlyAvailable = true;
	}
	
	public void clrOnlyAvailable()
	{
		this.onlyAvailable = false;
	}

}
