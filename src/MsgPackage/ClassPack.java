package MsgPackage;

import java.util.ArrayList;

import MsgPackage.MessagePack.OpType;


public class ClassPack {

	private OpType op;
	
	private entities.Class allclass;
	
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

	public entities.Class getAllclass() {
		return allclass;
	}

	public void setAllclass(entities.Class allclass) {
		this.allclass = allclass;
	}


	

	



}
