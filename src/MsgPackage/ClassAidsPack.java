package MsgPackage;

import java.util.ArrayList;

import MsgPackage.MessagePack.OpType;


public class ClassAidsPack {

	private OpType op;
	
	private ArrayList<String> aids;
	
	public ClassAidsPack() {
		super();
		this.op = OpType.GetClassAids;
		
		
	}
	public OpType getOp() {
		return op;
	}

	public void setOp(OpType op) {
		this.op = op;
	}

	public ArrayList<String> getAids() {
		return aids;
	}

	public void setAids(ArrayList<String> aids) {
		this.aids = aids;
	}

	

	



}
