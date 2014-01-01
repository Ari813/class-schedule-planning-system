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
			logout(msgpck, client);
			break;
		case GetBuildingsInfo:
			AllBuildings(msgpck, client);
			break;
		case GetCampusInfo:
			AllCampus(msgpck, client);
			break;
		case GetAllClasses:
			allClasses(msgpck, client);
			break;
		case GetStudyAidsInfo:
			allAids(msgpck, client);
			break;
		case GetAllCourses:
			allCourses(msgpck, client);
			break;
		case GetFacultyInfo:
			AllFaculty(msgpck, client);
			break;
		case GetLecturersInfo:
			AllLecturers(msgpck, client);
			break;
		case NewClass:
			NewClass(msgpck, client);
			break;
		case UpadteClass:
			updateClass(msgpck, client);
			break;
		case newLectuer:
			newLecturer(msgpck, client);
			break;
		case updateLectuer:
			updateLecturer(msgpck, client);
			break;

		case updateCourse:
			updateCourse(msgpck, client);
			break;

		case newCourse:
			newCourse(msgpck, client);
			break;
		case UpdateEstimatedStudentsNumPerClass:
			updateEstimatedStudentsNumPerClass(msgpck, client);
			break;
			
		case UpdateLecturersPreferences:
			UpdateLecturersPreferences(msgpck, client);
			break;
			
		case GetAllCoursesForSchedualing:
			allCoursesForSchedualing(msgpck, client);
			break;
		default:
			break;

		}
	}

	private void allCoursesForSchedualing(MessagePack msg, ConnectionToClient client) {
		GetAllCoursePack crss = (GetAllCoursePack) msg;
		try {
			crss.setAllclass(db.getAllCoursesForSchedualing(crss.isBringAdditionalInfo()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {
			client.sendToClient(crss);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void UpdateLecturersPreferences(MessagePack msg,
			ConnectionToClient client) {
		UpdateLecturersPreferencesPack lecturersArr = (UpdateLecturersPreferencesPack) msg;

		try {
			if (db.UpdateLecturersPreferences(lecturersArr.getAllLecturers()))
				lecturersArr.setSucceed();
			else
				lecturersArr.setFailed();
		} catch (SQLException e1) {
			lecturersArr.setFailed();
		}

		try {
			client.sendToClient(lecturersArr);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void updateEstimatedStudentsNumPerClass(MessagePack msg,
			ConnectionToClient client) {
		UpdateEstimatedStudentsNumPerClassPack CoursePerFuculty = (UpdateEstimatedStudentsNumPerClassPack) msg;

		try {
			if (db.UpdateEstimatedStudentsNumPerClass(CoursePerFuculty
					.getCoursePerFucultyMap()))
				CoursePerFuculty.setSucceed();
			else
				CoursePerFuculty.setFailed();
		} catch (SQLException e1) {
			CoursePerFuculty.setFailed();
		}

		try {
			client.sendToClient(CoursePerFuculty);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void newCourse(MessagePack msg, ConnectionToClient client) {
		NewCoursePack newCourse = (NewCoursePack) msg;
		try {
			newCourse.setNewCourse((db.newCourse(newCourse.getNewCourse())));
		} catch (SQLException e1) {
			newCourse.getNewCourse().setCourseID(-1);
		}

		try {
			client.sendToClient(newCourse);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void updateCourse(MessagePack msg, ConnectionToClient client) {
		UpdateCoursePack newCourse = (UpdateCoursePack) msg;
		try {
			newCourse.setNewCourse((db.updateCourse(newCourse.getNewCourse())));
		} catch (SQLException e1) {
			newCourse.getNewCourse().setCourseID(-1);
		}

		try {
			client.sendToClient(newCourse);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void updateLecturer(MessagePack msg, ConnectionToClient client) {
		UpdateLecturerPack newLec = (UpdateLecturerPack) msg;
		try {
			newLec.setNewLecturer(db.updateLecturer(newLec.getNewLecturer()));
		} catch (SQLException e1) {
			newLec.getNewLecturer().setID(-1);
		}

		try {
			client.sendToClient(newLec);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void newLecturer(MessagePack msg, ConnectionToClient client) {
		NewLecturerPack newLec = (NewLecturerPack) msg;
		try {
			newLec.setNewLecturer(db.newLecturer(newLec.getNewLecturer()));
		} catch (SQLException e1) {
			newLec.getNewLecturer().setID(-1);
		}
		try {
			client.sendToClient(newLec);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void updateClass(MessagePack msg, ConnectionToClient client) {
		UpdateClassPack newcls = (UpdateClassPack) msg;
		try {
			newcls.setNewClass(db.updateClass(newcls.getNewClass()));
		} catch (SQLException e1) {
			newcls.getNewClass().setClassID(-1);
		}
		try {
			client.sendToClient(newcls);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void NewClass(MessagePack msg, ConnectionToClient client) {
		NewClassPack newcls = (NewClassPack) msg;
		try {
			newcls.setNewClass(db.newClass(newcls.getNewClass()));
		} catch (SQLException e1) {
			newcls.getNewClass().setClassID(-1);
		}
		try {
			client.sendToClient(newcls);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void AllLecturers(MessagePack msg, ConnectionToClient client) {
		GetAllLecturersPack lecpck = (GetAllLecturersPack) msg;

		try {
			lecpck.setAllLecturers(db.getAllLecturers(lecpck
					.isBringAdditionalInfo()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {
			client.sendToClient(lecpck);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void AllFaculty(MessagePack msg, ConnectionToClient client) {
		GetAllFacultyPack fclty = (GetAllFacultyPack) msg;

		try {
			fclty.setAllFaculty(db.getAllFaculty());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {

			client.sendToClient(fclty);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void allCourses(MessagePack msg, ConnectionToClient client) {
		GetAllCoursePack crss = (GetAllCoursePack) msg;
		try {
			crss.setAllclass(db.getAllCourses(crss.isBringAdditionalInfo()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {
			client.sendToClient(crss);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void logout(MessagePack msg, ConnectionToClient client) {
		LogoutPack lgt = (LogoutPack) msg;
		try {
			db.exitUser(lgt.getUsr().getUserID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void allAids(MessagePack msg, ConnectionToClient client) {
		GetStudyAidsPack aids = (GetStudyAidsPack) msg;

		try {

			aids.setAllStudyAids(db.getAllStudyAids());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {

			client.sendToClient(aids);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void allClasses(MessagePack msg, ConnectionToClient client) {

		GetAllClassesPack clss = (GetAllClassesPack) msg;

		try {

			clss.setAllclass(db.getAllClasses());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {

			client.sendToClient(clss);
		} catch (IOException e) {

			e.printStackTrace();
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
