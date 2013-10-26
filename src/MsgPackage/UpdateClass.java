package MsgPackage;

public class UpdateClass extends MessagePack {

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

	public UpdateClass() {
		super();
		this.op = OpType.UpadteClass;

	}
}
