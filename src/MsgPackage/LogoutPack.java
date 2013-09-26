package MsgPackage;

/**
 * @author Omri Barda		039725890 
 * @author Amit Joseph		034608547
 
 * @version June 2014
 */

import entities.Login;

public class LogoutPack extends MessagePack {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Login usr;

	/**
	 * constructor
	 * 
	 * @param usr
	 */
	public LogoutPack(Login usr) {
		this.usr = usr;
		this.op = OpType.Logout;
	}

	/**
	 * 
	 * @return the user
	 */
	public Login getUsr() {
		return usr;
	}

}
