package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import common.Settings;

import MsgPackage.GetAllLecturersPack.getInformation;
import entities.Building;
import entities.Campus;
import entities.Class;
import entities.Course;
import entities.Faculty;
import entities.Lecturer;
import entities.Login;
import entities.StudyAids;

/**
 * Constructs a Database.
 */

public class Database
{

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

	public Database(String database, String username, String password) throws Exception
	{
		try
		{
			java.lang.Class.forName(driver).newInstance();
		} catch (Exception ex)
		{
			throw ex;
		}

		try
		{
			this.database = new String(database);
			this.username = new String(username);
			this.password = new String(password);
			conn = DriverManager.getConnection(this.database, this.username, this.password);
		} catch (SQLException ex)
		{
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
	public Database() throws Exception
	{
		try
		{
			java.lang.Class.forName(driver).newInstance();
		} catch (Exception ex)
		{
			throw ex;
		}

		try
		{
			conn = DriverManager.getConnection(DEFAULT_database, DEFAULT_username, DEFAULT_password);
		} catch (SQLException ex)
		{
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
	public int getUser(int userID, String userPass) throws NumberFormatException, SQLException
	{
		ResultSet qrs = null;
		String query = new String("SELECT * FROM `csps-db`.`Login` L WHERE L.UserID = " + userID + " AND L.Password = '" + userPass + "' ;");

		st = conn.createStatement();
		int ans = Login.ERROR;
		qrs = st.executeQuery(query);
		if (qrs.next())
		{
			if (qrs.getInt("LoggedIn") == 0)
			{
				query = new String("UPDATE `csps-db`.`Login` L SET `LoggedIn` = 1 WHERE L.UserID = " + userID + ";");
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
	public void exitUser(int userID) throws SQLException
	{
		String query = new String("UPDATE `csps-db`.`Login` L SET `LoggedIn` = 0 WHERE L.UserID = " + userID + ";");
		st = conn.createStatement();
		st.executeUpdate(query);
	}

	public ArrayList<Building> getAllBuildings() throws SQLException
	{
		ResultSet qrs = null;
		ArrayList<Building> buildingsArray = new ArrayList<Building>();
		Building bld;

		String query = new String("SELECT * FROM `csps-db`.building;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next())
		{
			bld = new Building();
			bld.setBuildingID(qrs.getInt("BuildingID"));
			bld.setBuildingName(qrs.getString("BuildingName"));
			buildingsArray.add(bld);
		}
		qrs.close();

		return buildingsArray;
	}

	public ArrayList<StudyAids> getAllStudyAids() throws SQLException
	{
		ResultSet qrs = null;
		ArrayList<StudyAids> studyAidsArray = new ArrayList<StudyAids>();
		StudyAids sAid;

		String query = new String("SELECT * FROM `csps-db`.Aidsinfo;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next())
		{
			sAid = new StudyAids();
			sAid.setAidsID(qrs.getInt("ClassAidID"));
			sAid.setAidsName(qrs.getString("ClassAidName"));
			studyAidsArray.add(sAid);
		}
		qrs.close();

		return studyAidsArray;
	}

	public ArrayList<Campus> getAllCampuses() throws SQLException
	{
		ResultSet qrs = null;
		ArrayList<Campus> campusessArray = new ArrayList<Campus>();
		Campus cmps;

		String query = new String("SELECT * FROM `csps-db`.campus;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next())
		{
			cmps = new Campus();
			cmps.setCampusId(qrs.getInt("CampusID"));
			cmps.setCampusName(qrs.getString("CampusName"));
			campusessArray.add(cmps);
		}
		qrs.close();

		return campusessArray;
	}

	public ArrayList<Class> getAllClasses() throws SQLException
	{
		ResultSet qrs = null;
		ResultSet classAidsQrs = null;
		ArrayList<Class> ClassesArray = new ArrayList<Class>();
		StudyAids stdyAds;
		Class cls;

		String query = new String("SELECT * FROM `csps-db`.class;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next())
		{
			cls = new Class();
			cls.setBuilding(qrs.getInt("ClassBuilding"));
			cls.setClassID(qrs.getInt("ClassID"));
			cls.setCapcity(qrs.getInt("Capacity"));
			cls.setCampus(qrs.getInt("Campus"));
			cls.setAvailable(qrs.getInt("Available"));
			cls.setDescription(qrs.getString("Description"));
			query = new String("SELECT * FROM `csps-db`.classaids ca where ca.ClassBuilding = " + cls.getBuilding() + " AND ca.ClassID = " + cls.getClassID() + ";");
			st = conn.createStatement();
			classAidsQrs = st.executeQuery(query);
			while (classAidsQrs.next())
			{
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

	public ArrayList<Course> getAllCourses(boolean additionalInfo) throws SQLException
	{
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
		while (qrs.next())
		{
			crs = new Course(qrs.getInt("Capacity"), qrs.getInt("CourseID"), qrs.getString("Description"), qrs.getInt("Faculty"), qrs.getInt("Semester"), qrs.getInt("AcademicHours"),
					qrs.getInt("EstimationOfStudentsNum"), qrs.getInt("CourseRelatedKey"));
			if (additionalInfo)
			{
				/* Add study aids */
				query = new String("SELECT * FROM `csps-db`.CourseAids ca where ca.CourseID = " + crs.getCourseID() + ";");
				st = conn.createStatement();
				AidsQrs = st.executeQuery(query);
				while (AidsQrs.next())
				{
					stdyAds = new StudyAids();
					stdyAds.setAidsID(AidsQrs.getInt("ClassAidID"));
					crs.addStudyAids(stdyAds);
				}

				/* Add Lecturer */

				query = new String(
						"SELECT cl.CourseID,cl.LecturerID,lec.LecturerName FROM `csps-db`.courselecturers cl, `csps-db`.lecturer lec where lec.LecturerID = cl.LecturerID AND cl.CourseID = "
								+ crs.getCourseID() + ";");
				st = conn.createStatement();

				LecturersQrs = st.executeQuery(query);
				while (LecturersQrs.next())
				{
					lec = new Lecturer();
					lec.setID(LecturersQrs.getInt("LecturerID"));
					lec.setName(LecturersQrs.getString("LecturerName"));
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

	public ArrayList<Faculty> getAllFaculty() throws SQLException
	{
		ResultSet qrs = null;
		ArrayList<Faculty> FacultyArray = new ArrayList<Faculty>();
		Faculty fclty;

		String query = new String("SELECT * FROM `csps-db`.Faculty;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next())
		{
			fclty = new Faculty();
			fclty.setFacultyNum(qrs.getInt("FacultyID"));
			fclty.setFaculty(qrs.getString("FacultyName"));
			FacultyArray.add(fclty);
		}
		qrs.close();

		return FacultyArray;
	}

	public ArrayList<Lecturer> getAllLecturers(getInformation additionalInfo) throws SQLException
	{
		ResultSet qrs = null;

		ArrayList<Lecturer> LecturerArray = new ArrayList<Lecturer>();
		Lecturer lec;

		String query = new String("SELECT * FROM `csps-db`.lecturer;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next())
		{
			lec = new Lecturer();
			lec.setID(qrs.getInt("LecturerID"));
			lec.setName(qrs.getString("LecturerName"));
			switch (additionalInfo)
			{
			case courses:
				lec = getLecturersCourses(lec);
				break;
			case schedual:
				lec = getLecturersSchedual(lec);
				break;
			case all:
				lec = getLecturersCourses(lec);
				lec = getLecturersSchedual(lec);
				break;
			case nothing:
				break;
			}

			LecturerArray.add(lec);
		}

		qrs.close();

		return LecturerArray;
	}

	private Lecturer getLecturersSchedual(Lecturer lec) throws SQLException
	{
		ResultSet SchedualQrs = null;
		int schedualArray[] = new int[Settings.weekHours];
		String query = new String("SELECT * FROM `csps-db`.LecturerPref where LecturerID=" + lec.getID() + ";");
		st = conn.createStatement();

		SchedualQrs = st.executeQuery(query);
		while (SchedualQrs.next())
			schedualArray[SchedualQrs.getInt("TimeArrayIndex")] = SchedualQrs.getInt("Pref");
		lec.setPreferedSchedualArray(schedualArray);
		lec.setHasSchedualInfo();
		return lec;
	}

	private Lecturer getLecturersCourses(Lecturer lec) throws SQLException
	{
		ResultSet CoursesQrs = null;
		Course crs;
		String query = new String("SELECT * FROM `csps-db`.courselecturers cl where cl.LecturerID = " + lec.getID() + ";");
		st = conn.createStatement();

		CoursesQrs = st.executeQuery(query);
		while (CoursesQrs.next())
		{
			crs = new Course();
			crs.setCourseID(CoursesQrs.getInt("CourseID"));
			lec.addCourse(crs);
			lec.setHasCoursesInfo();
		}
		return lec;
	}

	public Lecturer newLecturer(Lecturer newLecturer) throws SQLException
	{
		String query;
		query = new String("INSERT INTO `csps-db`.`lecturer` (`LecturerID`, `LecturerName`) VALUES ('" + newLecturer.getID() + "', '" + newLecturer.getName() + "');");
		st = conn.createStatement();
		st.executeUpdate(query);

		newLecturer = updateLecturer(newLecturer);
		newLecturer = createNewLecturerPreferences(newLecturer);

		return newLecturer;
	}

	private Lecturer createNewLecturerPreferences(Lecturer newLecturer) throws SQLException
	{
		String query;
		for (int i = 0; i < Settings.weekHours; i++)
		{
			query = new String("INSERT INTO `csps-db`.`LecturerPref` (`LecturerID`, `TimeArrayIndex`, `Pref`) VALUES ('" + newLecturer.getID() + "', '" + i + "', '0');");
			st = conn.createStatement();
			st.executeUpdate(query);
		}
		return newLecturer;
	}

	public Course updateCourse(Course newCourse) throws SQLException
	{
		String query;
		query = new String("UPDATE `csps-db`.`course` SET `Capacity`='" + newCourse.getStudentNumber() + "', `Faculty`='" + newCourse.getFaculty() + "', `Semester`='" + newCourse.getSemester()
				+ "', `Description`='" + newCourse.getDescription() + "', `AcademicHours`='" + newCourse.getAcademicHours() + "', `CourseRelatedKey`='" + newCourse.getCourseRelativeKey()
				+ "' WHERE `CourseID`='" + newCourse.getCourseID() + "';");
		st = conn.createStatement();
		st.executeUpdate(query);
		newCourse = updateCourseInfoCourse(newCourse);
		return newCourse;
	}

	public Course newCourse(Course newCourse) throws SQLException
	{
		String query;
		query = new String("INSERT INTO `csps-db`.`course` (`CourseID`, `Capacity`, `Faculty`, `Semester`, `Description`, `AcademicHours`, `CourseRelatedKey`) VALUES ('" + newCourse.getCourseID()
				+ "', '" + newCourse.getStudentNumber() + "', '" + newCourse.getFaculty() + "', '" + newCourse.getSemester() + "', '" + newCourse.getDescription() + "', '"
				+ newCourse.getAcademicHours() + "', '" + newCourse.getCourseRelativeKey() + "');");

		st = conn.createStatement();
		st.executeUpdate(query);

		newCourse = updateCourseInfoCourse(newCourse);
		return newCourse;
	}

	public Course updateCourseInfoCourse(Course newCourse) throws SQLException
	{
		String query;
		query = new String("DELETE FROM `csps-db`.`courseaids` WHERE `CourseID`='" + newCourse.getCourseID() + "';");
		st = conn.createStatement();
		st.executeUpdate(query);

		for (int i = 0; i < newCourse.getStudyAids().size(); i++)
		{
			query = new String("INSERT INTO `csps-db`.`courseaids` (`CourseID`, `ClassAidID`) VALUES ('" + newCourse.getCourseID() + "', '" + newCourse.getStudyAids().get(i).getAidsID() + "');");
			st = conn.createStatement();
			st.executeUpdate(query);

		}

		query = new String("DELETE FROM `csps-db`.`courselecturers` WHERE `CourseID`='" + newCourse.getCourseID() + "';");
		st = conn.createStatement();
		st.executeUpdate(query);

		for (int i = 0; i < newCourse.getCourseLecturers().size(); i++)
		{
			query = new String("INSERT INTO `csps-db`.`courselecturers` (`CourseID`, `LecturerID`) VALUES ('" + newCourse.getCourseID() + "', '" + newCourse.getCourseLecturers().get(i).getID()
					+ "');");
			st = conn.createStatement();
			st.executeUpdate(query);
		}

		return newCourse;
	}

	public Lecturer updateLecturer(Lecturer newLecturer) throws SQLException
	{
		String query;
		query = new String("DELETE FROM `csps-db`.`courselecturers` WHERE `LecturerID`='" + newLecturer.getID() + "';");

		st = conn.createStatement();
		st.executeUpdate(query);

		for (int i = 0; i < newLecturer.getLecturerCourses().size(); i++)
		{
			query = new String("INSERT INTO `csps-db`.`courselecturers` (`CourseID`, `LecturerID`) VALUES ('" + newLecturer.getLecturerCourses().get(i).getCourseID() + "', '" + newLecturer.getID()
					+ "');");

			st = conn.createStatement();
			st.executeUpdate(query);

		}
		return newLecturer;
	}

	public Class newClass(Class newClass) throws SQLException
	{
		String query;
		query = new String("INSERT INTO `csps-db`.`class` (`ClassBuilding`, `ClassID`, `Capacity`, `Campus`, `Description`, `Available`) VALUES ('" + newClass.getBuilding() + "', '"
				+ newClass.getClassID() + "', '" + newClass.getCapcity() + "', '" + newClass.getCampus() + "', '" + newClass.getDescription() + "', '" + newClass.getAvailableInt() + "');");

		st = conn.createStatement();
		st.executeUpdate(query);
		newClass = updateClassInfo(newClass);
		return newClass;
	}

	public Class updateClass(Class newClass) throws SQLException
	{
		String query;
		query = new String("UPDATE `csps-db`.`class` SET `Capacity`='" + newClass.getCapcity() + "', `Campus`='" + newClass.getCampus() + "', `Description`='" + newClass.getDescription()
				+ "', `Available`='" + newClass.getAvailableInt() + "' WHERE `ClassBuilding`='" + newClass.getBuilding() + "' and`ClassID`='" + newClass.getClassID() + "';");

		st = conn.createStatement();
		st.executeUpdate(query);
		newClass = updateClassInfo(newClass);
		return newClass;
	}

	public Class updateClassInfo(Class newClass) throws SQLException
	{
		String query;
		query = new String("DELETE FROM `csps-db`.`classaids` WHERE `ClassBuilding`='" + newClass.getBuilding() + "' and`ClassID`='" + newClass.getClassID() + "';");

		st = conn.createStatement();
		st.executeUpdate(query);

		for (int i = 0; i < newClass.getStudyAids().size(); i++)
		{
			query = new String("INSERT INTO `csps-db`.`classaids` (`ClassBuilding`, `ClassID`, `ClassAidID`) VALUES ('" + newClass.getBuilding() + "', '" + newClass.getClassID() + "', '"
					+ newClass.getStudyAids().get(i).getAidsID() + "');");

			st = conn.createStatement();
			st.executeUpdate(query);

		}
		return newClass;
	}

	public boolean UpdateEstimatedStudentsNumPerClass(Map<Integer, ArrayList<Course>> coursePerFucultyMap) throws SQLException
	{
		String query;
		Iterator<Integer> keyItr = coursePerFucultyMap.keySet().iterator();
		Iterator<Course> CourseArrItr;
		Course TmpCrs;

		while (keyItr.hasNext())
		{

			CourseArrItr = coursePerFucultyMap.get(keyItr.next()).iterator();
			while (CourseArrItr.hasNext())
			{
				TmpCrs = CourseArrItr.next();
				query = new String("UPDATE `csps-db`.`course` SET `EstimationOfStudentsNum`='" + TmpCrs.getCapacity() + "' WHERE `CourseID`='" + TmpCrs.getCourseID() + "';");
				st = conn.createStatement();
				st.executeUpdate(query);
			}
		}
		return true;
	}

	public boolean UpdateLecturersPreferences(ArrayList<Lecturer> allLecturers) throws SQLException
	{
		String query = null;
		Lecturer lecturer;
		for (int i = 0; i < allLecturers.size(); i++)
		{
			lecturer = allLecturers.get(i);
			for (int j = 0; j < lecturer.getPreferedSchedualArray().length; j++)
			{
				query = new String("UPDATE `csps-db`.`LecturerPref` SET `Pref`='" + lecturer.getPreferedSchedualArray()[j] + "' WHERE `LecturerID`='" + lecturer.getID() + "' and`TimeArrayIndex`='"
						+ j + "';");
				st = conn.createStatement();
				st.executeUpdate(query);
			}

		}
		return true;
	}

	public ArrayList<Course> getAllCoursesForSchedualing(boolean bringAdditionalInfo) throws SQLException
	{
		ResultSet qrs = null;
		ResultSet AidsQrs = null;
		ResultSet LecturersQrs = null;

		ArrayList<Course> CourseArray = new ArrayList<Course>();
		Lecturer lec;
		StudyAids stdyAds;
		Course crs = null;

		String query = new String("SELECT * FROM `csps-db`.course;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next())
		{
			int capacity = qrs.getInt("Capacity");
			int courseID = qrs.getInt("CourseID");
			String description = qrs.getString("Description");
			int faculty = qrs.getInt("Faculty");
			int semester = qrs.getInt("Semester");
			int academicHours = qrs.getInt("AcademicHours");
			int EstimationOfStudentsNum = qrs.getInt("EstimationOfStudentsNum");
			int relatedCourseKey = qrs.getInt("CourseRelatedKey");
			courseID = (courseID * 10);

			for (int i = EstimationOfStudentsNum; i > 0; i -= capacity)
			{
				crs = new Course(capacity, courseID, description, faculty, semester, academicHours, EstimationOfStudentsNum, relatedCourseKey);

				if (bringAdditionalInfo)
				{
					/* Add study aids */
					query = new String("SELECT * FROM `csps-db`.CourseAids ca where ca.CourseID = " + crs.getCourseID() + ";");
					st = conn.createStatement();
					AidsQrs = st.executeQuery(query);
					while (AidsQrs.next())
					{
						stdyAds = new StudyAids();
						stdyAds.setAidsID(AidsQrs.getInt("ClassAidID"));
						crs.addStudyAids(stdyAds);
					}

					/* Add Lecturer */

					query = new String(
							"SELECT cl.CourseID,cl.LecturerID,lec.LecturerName FROM `csps-db`.courselecturers cl, `csps-db`.lecturer lec where lec.LecturerID = cl.LecturerID AND cl.CourseID = "
									+ crs.getCourseID() + ";");
					st = conn.createStatement();

					LecturersQrs = st.executeQuery(query);
					while (LecturersQrs.next())
					{
						lec = new Lecturer();
						lec.setID(LecturersQrs.getInt("LecturerID"));
						lec.setName(LecturersQrs.getString("LecturerName"));
						crs.addLecturer(lec);
					}
					LecturersQrs.close();
					AidsQrs.close();

					crs.setHasadditionalInfo();
				}
				courseID++;
				CourseArray.add(crs);
			}
		}
		qrs.close();
		return CourseArray;

	}
}
