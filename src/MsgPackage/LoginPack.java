package MsgPackage;

/**
 * @author Omri Barda		039725890 
 * @author Amit Joseph		034608547
 * @author Gilad Shpigel  	300162393
 * @author Elad Elbaz		040539959
 * @version June 2012
 */

import entities.Login;

public class LoginPack extends MessagePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Login usr;

	/**
	 * constructor
	 * 
	 * @return
	 */
	public LoginPack(Login usr) {
		this.usr = usr;
		this.op = OpType.Login;
	}

	/**
	 * 
	 * @return the user
	 */
	public Login getUsr() {
		return usr;
	}

}
