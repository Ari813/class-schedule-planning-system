package entities;

/**
 * @author Omri Barda		039725890 
 * @author Amit Joseph		034608547
 * @author Gilad Shpigel  	300162393
 * @author Elad Elbaz		040539959
 * @version June 2012
 */

import java.io.Serializable;

public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final public static int UserAlreadyLogged = -2;
	final public static int ERROR = -1;
	final public static int PRINCIPAL_PRMSSN = 1;
	final public static int LECTURER_PRMSSN = 2;
	final public static int STUDENT_PRMSSN = 3;

	private String host;
	private int port;
	private String loginTime;
	private int loginPermissionLevel;
	private int userID;
	private String userPass;

	/**
	 * empty constructor
	 */
	public Login() {
	}

	/**
	 * constructor
	 * 
	 * @param host
	 * @param port
	 * @param userID
	 * @param userPass
	 */
	public Login(String host, int port, int userID, String userPass) {
		super();
		this.host = new String(host);
		this.port = port;
		this.userID = userID;
		this.userPass = new String(userPass);
	}

	// ------------------
	// Setters & Getters
	// ------------------

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the loginTime
	 */
	public String getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime
	 *            the loginTime to set
	 */
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the loginPermissionLevel
	 */
	public int getLoginPermissionLevel() {
		return loginPermissionLevel;
	}

	/**
	 * @param loginPermissionLevel
	 *            the loginPermissionLevel to set
	 */
	public void setLoginPermissionLevel(int loginPermissionLevel) {
		this.loginPermissionLevel = loginPermissionLevel;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}

	/**
	 * @param userPass
	 *            the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
}
