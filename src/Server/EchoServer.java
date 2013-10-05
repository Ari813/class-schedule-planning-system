// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import GUI.*;
import MsgPackage.*;
import entities.*;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 * 
 * @author Omri Barda 039725890
 * @author Amit Joseph 034608547
 * @version May 2014
 */
public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	Database db;
	HashMap<String, ArrayList<ConnectionToClient>> studentsInExam;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 * 
	 * @param port
	 *            The port number to connect on.
	 */
	public EchoServer(int port, String sqlIp, int sqlPort, String sqlUser,
			String sqlDBName, String sqlPassword) {
		super(port);
		try {
			String hostName = new String("JDBC:mysql://" + sqlIp + ":"
					+ sqlPort + "/" + sqlDBName);
			db = new Database(hostName, sqlUser, sqlPassword);
			studentsInExam = new HashMap<String, ArrayList<ConnectionToClient>>();
		} catch (Exception e) {
			// Perror.sFatalError("Could not listen for clients!");
		}

	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 * 
	 * @param msg
	 *            The query received from the client.
	 * @param client
	 *            The connection from which the message originated.
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		MessagePack msgpck = (MessagePack) msg;

		switch (msgpck.getOp()) {
		/*
		 * case exp to handle client request.
		 */
		case Login:
			login(msgpck, client);
			break;
		case Logout:
			break;
		case GetBuildingsInfo:
			AllBuildings(msgpck, client);
			break;
		case GetCampusInfo:
			AllCampus(msgpck, client);
			break;
		case GetClassAids:
			break;
		case GetStudyAidsInfo:
			break;

		}
	}

	private void AllCampus(MessagePack msg, ConnectionToClient client) {

		GetCampusPack cmps = (GetCampusPack) msg;

		try {
			cmps.setAllCampuses(db.getAllCampuses());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {
			client.sendToClient(cmps);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void AllBuildings(MessagePack msg, ConnectionToClient client) {
		GetBuildingsPack bldng = (GetBuildingsPack) msg;
		try {
			bldng.setAllBuildings(db.getAllBuildings());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {
			client.sendToClient(bldng);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private synchronized void login(MessagePack msg, ConnectionToClient client) {
		LoginPack lgn = (LoginPack) msg;
		int dblgn = Login.ERROR;
		try {
			dblgn = db.getUser(lgn.getUsr().getUserID(), lgn.getUsr()
					.getUserPass());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		lgn.getUsr().setLoginPermissionLevel(dblgn);
		try {

			client.sendToClient(lgn);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		JOptionPane.showMessageDialog(ServerGUI.mainframe,
				"Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * stops listening for connections.
	 */
	protected void serverStopped() {
		JOptionPane.showMessageDialog(ServerGUI.mainframe,
				"Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there
	 * is no UI in this phase).
	 * 
	 * @param args
	 *            [0] The port number to listen on. Defaults to 5555 if no
	 *            argument is entered.
	 */

}
// End of EchoServer class
