package MsgPackage;

/**
 * @author Omri Barda		039725890 
 * @author Amit Joseph		034608547
 * @version June 2014
 */

import java.io.Serializable;

/* the way we send messages */

public class MessagePack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected OpType op;

	/**
	 * the operations enums sent between the client and the server
	 */
	public enum OpType {

		Login, 
		Logout, 
		GetClassAids, 
		GetCampusInfo
	};

	/**
	 * 
	 * @return the type of the operation
	 */
	public OpType getOp() {
		return op;
	}

}
