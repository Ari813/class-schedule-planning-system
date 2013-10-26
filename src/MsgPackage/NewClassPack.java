package MsgPackage;

public class NewClassPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private entities.Class newClass;

	public entities.Class getAllclass() {
		return newClass;
	}

	public void setNewClass(entities.Class newClass) {
		this.newClass = newClass;
	}

	public NewClassPack() {
		super();
		this.op = OpType.NewClass;

	}
}
