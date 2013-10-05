package Server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import entities.Building;
import entities.Campus;
import entities.Class;
import entities.Login;
import entities.StudyAids;

/**
 * Constructs a Database.
 */

public class Database {

	private final Connection conn;
	private static final String DEFAULT_database = "JDBC:mysql://localhost:3306/csps-db";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String DEFAULT_username = "root";
	private static final String DEFAULT_password = "Password";

	private String database;
	private String username;
	private String password;
	private Statement st;

	/**
	 * the class handles all references to the database
	 * 
	 * @param database
	 * @param username
	 *            - the user name
	 * @param password
	 *            - the uses password
	 * @throws Exception
	 *             -SQL Exception
	 */
	public Database(String database, String username, String password)
			throws Exception {
		try {
			java.lang.Class.forName(driver).newInstance();
		} catch (Exception ex) {
			throw ex;
		}

		try {
			this.database = new String(database);
			this.username = new String(username);
			this.password = new String(password);
			conn = DriverManager.getConnection(this.database, this.username,
					this.password);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			throw ex;
		}
	}

	/**
	 * the class handles all references to the database
	 * 
	 * @throws Exception
	 *             -SQL Exception
	 */
	public Database() throws Exception {
		try {
			java.lang.Class.forName(driver).newInstance();
		} catch (Exception ex) {
			throw ex;
		}

		try {
			conn = DriverManager.getConnection(DEFAULT_database,
					DEFAULT_username, DEFAULT_password);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			throw ex;
		}
	}

	/**
	 * check if the the user can login to the system
	 * 
	 * @param userID
	 *            -the user id
	 * @param userPass
	 *            -the user Password
	 * @return if success to login
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public int getUser(int userID, String userPass)
			throws NumberFormatException, SQLException {
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `csps-db`.`Login` L WHERE L.UserID = " + userID
						+ " AND L.Password = '" + userPass + "' ;");

		st = conn.createStatement();
		int ans = Login.ERROR;
		qrs = st.executeQuery(query);
		if (qrs.next()) {
			if (qrs.getString(4).equals("0")) {
				query = new String(
						"UPDATE `csps-db`.`Login` L SET `LoggedIn` = 1 WHERE L.UserID = "
								+ userID + ";");
				st = conn.createStatement();
				st.executeUpdate(query);
				ans = Integer.parseInt(qrs.getString(3));

			} else
				ans = Login.UserAlreadyLogged;
		}
		qrs.close();
		return ans;

	}

	/**
	 * exit from the system
	 * 
	 * @param userID
	 *            - the user id
	 * @throws SQLException
	 */
	public void exitUser(int userID) throws SQLException {
		String query = new String(
				"UPDATE `csps-db`.`Login` L SET `LoggedIn` = 0 WHERE L.UserID = "
						+ userID + ";");
		st = conn.createStatement();
		st.executeUpdate(query);
	}

	public ArrayList<Building> getAllBuildings() throws SQLException {
		ResultSet qrs = null;
		ArrayList<Building> buildingsArray = new ArrayList<Building>();
		Building bld;

		String query = new String("SELECT * FROM `csps-db`.building;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		if (qrs.next()) {
			bld = new Building();
			bld.setBuildingID(Integer.parseInt(qrs.getString(1)));
			bld.setBuildingName(qrs.getString(2));
			buildingsArray.add(bld);
		}
		qrs.close();

		return buildingsArray;
	}

	public ArrayList<StudyAids> getAllStudyAids() throws SQLException {
		ResultSet qrs = null;
		ArrayList<StudyAids> studyAidsArray = new ArrayList<StudyAids>();
		StudyAids sAid;

		String query = new String("SELECT * FROM `csps-db`.Aidsinfo;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		if (qrs.next()) {
			sAid = new StudyAids();
			sAid.setAidsID(Integer.parseInt(qrs.getString(1)));
			sAid.setAidsName(qrs.getString(2));
			studyAidsArray.add(sAid);
		}
		qrs.close();

		return studyAidsArray;
	}

	public ArrayList<Campus> getAllCampuses() throws SQLException {
		ResultSet qrs = null;
		ArrayList<Campus> campusessArray = new ArrayList<Campus>();
		Campus cmps;

		String query = new String("SELECT * FROM `csps-db`.campus;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		if (qrs.next()) {
			cmps = new Campus();
			cmps.setCampusId(Integer.parseInt(qrs.getString(1)));
			cmps.setCampusName(qrs.getString(2));
			campusessArray.add(cmps);
		}
		qrs.close();

		return campusessArray;
	}

}