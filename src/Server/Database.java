package Server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

import entities.Building;
import entities.Campus;
import entities.Class;
import entities.ClassesAids;
import entities.Course;
import entities.Faculty;
import entities.Lecturer;
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
			if (qrs.getInt("LoggedIn") == 0) {
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
		while (qrs.next()) {
			bld = new Building();
			bld.setBuildingID(qrs.getInt("BuildingID"));
			bld.setBuildingName(qrs.getString("BuildingName"));
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
		while (qrs.next()) {
			sAid = new StudyAids();
			sAid.setAidsID(qrs.getInt("ClassAidID"));
			sAid.setAidsName(qrs.getString("ClassAidName"));
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
		while (qrs.next()) {
			cmps = new Campus();
			cmps.setCampusId(qrs.getInt("CampusID"));
			cmps.setCampusName(qrs.getString("CampusName"));
			campusessArray.add(cmps);
		}
		qrs.close();

		return campusessArray;
	}

	public ArrayList<Class> getAllClasses() throws SQLException {
		ResultSet qrs = null;
		ResultSet classAidsQrs = null;
		ArrayList<Class> ClassesArray = new ArrayList<Class>();
		StudyAids stdyAds;
		Class cls;

		String query = new String("SELECT * FROM `csps-db`.class;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			cls = new Class();
			cls.setBuilding(qrs.getInt("ClassBuilding"));
			cls.setClassID(qrs.getInt("ClassID"));
			cls.setCapcity(qrs.getInt("Capacity"));
			cls.setCampus(qrs.getInt("Campus"));
			cls.setAvailable(qrs.getBoolean("Available"));
			cls.setDescription(qrs.getString("Description"));
			query = new String(
					"SELECT * FROM `csps-db`.classaids ca where ca.ClassBuilding = "
							+ cls.getBuilding() + " AND ca.ClassID = "
							+ cls.getClassID() + ";");
			st = conn.createStatement();
			classAidsQrs = st.executeQuery(query);
			while (classAidsQrs.next()) {
				stdyAds = new StudyAids();
				stdyAds.setAidsID(classAidsQrs.getInt("ClassAidID"));
				cls.addStudyAid(stdyAds);
			}
			ClassesArray.add(cls);
			classAidsQrs.close();
		}
		qrs.close();
		return ClassesArray;
	}

	public ArrayList<Course> getAllCourses(boolean additionalInfo)
			throws SQLException {
		ResultSet qrs = null;
		ResultSet AidsQrs = null;
		ResultSet LecturersQrs = null;

		ArrayList<Course> CourseArray = new ArrayList<Course>();
		Lecturer lec;
		StudyAids stdyAds;
		Course crs;

		String query = new String("SELECT * FROM `csps-db`.course;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			crs = new Course(qrs.getInt("Capacity"), qrs.getInt("CourseID"),
					qrs.getString("Description"), qrs.getInt("Faculty"),
					qrs.getInt("Semester"));
			if (additionalInfo) {
				/* Add study aids */
				query = new String(
						"SELECT * FROM `csps-db`.CourseAids ca where ca.CourseID = "
								+ crs.getCourseID() + ";");
				st = conn.createStatement();
				AidsQrs = st.executeQuery(query);
				while (AidsQrs.next()) {
					stdyAds = new StudyAids();
					stdyAds.setAidsID(AidsQrs.getInt("ClassAidID"));
					crs.addStudyAids(stdyAds);
				}

				/* Add Lecturer */

				query = new String(
						"SELECT * FROM `csps-db`.courselecturers cl where cl.CourseID = "
								+ crs.getCourseID() + ";");
				st = conn.createStatement();

				LecturersQrs = st.executeQuery(query);
				while (LecturersQrs.next()) {
					lec = new Lecturer();
					lec.setID(LecturersQrs.getInt("LecturerID"));
					crs.addLecturer(lec);
				}
				LecturersQrs.close();
				AidsQrs.close();

				crs.setHasadditionalInfo();
			}

			CourseArray.add(crs);

		}
		qrs.close();
		return CourseArray;

	}

	public ArrayList<Faculty> getAllFaculty() throws SQLException {
		ResultSet qrs = null;
		ArrayList<Faculty> FacultyArray = new ArrayList<Faculty>();
		Faculty fclty;

		String query = new String("SELECT * FROM `csps-db`.Faculty;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			fclty = new Faculty();
			fclty.setFacultyNum(qrs.getInt("FacultyID"));
			fclty.setFaculty(qrs.getString("FacultyName"));
			FacultyArray.add(fclty);
		}
		qrs.close();

		return FacultyArray;
	}

	public ArrayList<Lecturer> getAllLecturers(boolean additionalInfo)
			throws SQLException {
		ResultSet qrs = null;
		ResultSet CoursesQrs = null;
		ArrayList<Lecturer> LecturerArray = new ArrayList<Lecturer>();
		Lecturer lec;
		Course crs;

		String query = new String("SELECT * FROM `csps-db`.lecturer;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			lec = new Lecturer();
			lec.setID(qrs.getInt("LecturerID"));
			lec.setName(qrs.getString("LecturerName"));

			if (additionalInfo) {
				query = new String(
						"SELECT * FROM `csps-db`.courselecturers cl where cl.LecturerID = "
								+ lec.getID() + ";");
				st = conn.createStatement();

				CoursesQrs = st.executeQuery(query);
				while (CoursesQrs.next()) {
					crs = new Course();
					crs.setCourseID(CoursesQrs.getInt("CourseID"));
					lec.addCourse(crs);
					lec.setHasCoursesInfo();
				}
			}

			LecturerArray.add(lec);
		}

		qrs.close();

		return LecturerArray;
	}
}
