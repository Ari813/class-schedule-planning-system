package MsgPackage;

public class UpdateClassPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private entities.Class updateClass;

	public entities.Class getNewClass() {
		return updateClass;
	}

	public void setNewClass(entities.Class newClass) {
		this.updateClass = newClass;
	}

	public UpdateClassPack() {
		super();
		this.op = OpType.UpadteClass;

	}
}
