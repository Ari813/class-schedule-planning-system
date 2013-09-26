// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
/**
 * Deprecated
 */


package Client;

import java.io.IOException;

import common.ChatIF;

import ocsf.client.AbstractClient;


/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 * 
 * @author Omri Barda 039725890
 * @author Amit Joseph 034608547
 * @author Gilad Shpigel 300162393
 * @author Elad Elbaz 040539959
 * @version May 2012
 */

public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;
	Object msg;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 * 
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * @param clientUI
	 *            The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI)
			throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server and wakes
	 * getMessage() that waits for the server response.
	 * 
	 * @param msg
	 *            The message from the server.
	 */
	synchronized public void handleMessageFromServer(Object msg) {
		this.msg = msg;
		notifyAll();
	}

	/**
	 * This method handles all data coming from the UI
	 * 
	 * @param message
	 *            The message from the UI.
	 */
	public void handleMessageFromClientUI(Object message) {

		try {
			sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
			//Perror.pError("Could not send message to server.  Terminating client.");
			quit();
		}
		msg = null;
	}

	/**
	 * This method waits to message from the server
	 * 
	 * @return the message Package
	 */
	synchronized public Object getMessage() {
		while (msg == null) {
			try {
				wait();

			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		return msg;
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
// End of ChatClient class
