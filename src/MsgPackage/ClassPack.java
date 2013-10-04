package MsgPackage;

import java.util.ArrayList;

import MsgPackage.MessagePack.OpType;


public class ClassPack {

	private OpType op;
	
	private ArrayList<entities.Class> allclass;
	
	public ArrayList<entities.Class> getAllclass() {
		return allclass;
	}

	public void setAllclass(ArrayList<entities.Class> allclass) {
		this.allclass = allclass;
	}

	public ClassPack() {
		super();
		this.op = OpType.GetClassAids;
		
		
	}

	public OpType getOp() {
		return op;
	}

	public void setOp(OpType op) {
		this.op = op;
	}

	


	

	



}
