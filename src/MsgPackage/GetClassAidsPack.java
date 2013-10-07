package MsgPackage;

import java.util.ArrayList;

import entities.Campus;
import entities.ClassesAids;
import MsgPackage.MessagePack.OpType;

public class GetClassAidsPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<ClassesAids> allclassAids;

	

	
	public GetClassAidsPack() {
		super();
		this.op = OpType.GetClassAids;

	}


	public ArrayList<ClassesAids> getAllclassAids() {
		return allclassAids;
	}

	public void setAllclassAids(ArrayList<ClassesAids> allclassAids) {
		this.allclassAids =new ArrayList<ClassesAids>(allclassAids) ;
	}

}
