package common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import entities.Answer;
import entities.ComputerizedExam;
import entities.Course;
import entities.Exam;
import entities.ExtractedExam;
import entities.Lecturer;
import entities.Login;
import entities.Question;
import entities.QuestionPerExam;
import entities.Request;
import entities.Student;
import entities.Subject;
import files.MyFile;

/**
 * @author Omri Barda		039725890 
 * @author Amit Joseph		034608547
 * @author Gilad Shpigel  	300162393
 * @author Elad Elbaz		040539959
 * @version May 2012
 */

/**
 * Constructs a Database.
 */


public class Database {

	private final Connection conn;
	private static final String DEFAULT_database = "JDBC:mysql://localhost:3309/aes";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String DEFAULT_username = "root";
	private static final String DEFAULT_password = "123123";

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
			Class.forName(driver).newInstance();
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
			Class.forName(driver).newInstance();
		} catch (Exception ex) {
			throw ex;
		}

		try {
			conn = DriverManager.getConnection(DEFAULT_database,
					DEFAULT_username, DEFAULT_password);
		} catch (SQLException ex) {
			Perror.pError("SQLException: " + ex.getMessage());
			Perror.pError("SQLState: " + ex.getSQLState());
			Perror.pError("VendorError: " + ex.getErrorCode());
			throw ex;
		}
	}

	/**
	 * get from the database the question
	 * 
	 * @param sNum
	 *            -the subject number
	 * @return qnum -the question number
	 * @throws SQLException
	 */
	public Integer getQuestionNumber(Integer sNum) throws SQLException {
		ResultSet qrs = null;
		Integer qNum = null;
		String query = new String(
				"SELECT MAX(QuestionID) FROM `aes`.`question` Q "
						+ "WHERE Q.SubjectID like " + sNum + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		try {
			if (qrs.next())
				qNum = new Integer(Integer.parseInt(qrs.getString(1)) + 1);
		} catch (Exception e) {
			qNum = new Integer(0);
		}
		qrs.close();
		return qNum;
	}

	// Adding question to the Database with all question data.
	/**
	 * the Duration Time Request
	 * 
	 * @param durestionTime
	 *            -the time the lecturer Want to add to the exam
	 * @throws SQLException
	 */
	public void addDurationTimeReuqest(Request durestionTime)
			throws SQLException {
		String query = new String(
				"INSERT INTO `aes`.`Duration time request` (`ExamCode`, `CreatorID`, `Extantion Duration Time`, `Reasons`, `Approved`) VALUES ('"
						+ durestionTime.getExamCode()
						+ "', "
						+ durestionTime.getCreatorId()
						+ ", "
						+ durestionTime.getExtensionDurationTime()
						+ ", '"
						+ durestionTime.getReasons()
						+ "', "
						+ Request.WAITING
						+ ");");
		st = conn.createStatement();
		st.executeUpdate(query);
	}

	/**
	 * 
	 * 
	 * @param creatorID
	 *            - the exam creator ID
	 * @return the active Exams
	 * @throws SQLException
	 */
	public ArrayList<ExtractedExam> getActiveExams(int creatorID)
			throws SQLException {

		ArrayList<ExtractedExam> activeExams = new ArrayList<ExtractedExam>();
		ExtractedExam activeExm;
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		ResultSet qrs = null;
		// getting all exams taking place at the moment
		String query = new String(
				"SELECT * FROM `aes`.`extracted exam` EE WHERE EE.creatorID = "
						+ creatorID + " and EE.ExamExtractDate < "
						+ currentTime + " and " + currentTime
						+ " < (EE.ExamExtractDate + EE.ExamDurationTime);");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			activeExm = new ExtractedExam();
			activeExm.setExamCode(qrs.getString(1));
			activeExm.setSubjectID(Integer.parseInt(qrs.getString(2)));
			activeExm.setCourseID(Integer.parseInt(qrs.getString(3)));
			activeExm.setExamID(Integer.parseInt(qrs.getString(4)));
			activeExm.setExamExtractDate(new Date(Long.parseLong(qrs
					.getString(6))));
			activeExm.setExamDurationTime(new Date(Long.parseLong(qrs
					.getString(7))));
			activeExams.add(activeExm);
		}
		qrs.close();
		// getting exams that already have requests:
		String examCode;
		query = new String(
				"SELECT DISTINCT(EE.ExamCode) FROM `aes`.`extracted exam` EE , `aes`.`Duration time request` DTR where EE.ExamCode = DTR.ExamCode ;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			examCode = new String(qrs.getString(1));
			for (int i = 0; i < activeExams.size(); i++)
				if (examCode.equals(activeExams.get(i).getExamCode()))
					activeExams.remove(i);
		}
		return activeExams;
	}

	/**
	 * 
	 * @param q
	 *            - the Question to add to the Database
	 * @return Question Answer
	 * @throws SQLException
	 */
	public Question addQuestion(Question q) throws SQLException {
		String query;
		q.setqID(getQuestionNumber(q.getSubjectID()));
		query = new String(
				"INSERT INTO `aes`.`question` (`QuestionID`, `QuestionText`, `QuestionDate`, `QuestionCreator`, `QuestionInstructions`, `CorrectAnswer`, `AnswerA`, `AnswerB`, `AnswerC`, `SubjectID`) VALUES ("
						+ q.getqID()
						+ ", '"
						+ q.getqText()
						+ "', "
						+ q.getqDate().getTime()
						+ ", "
						+ q.getqCreator()
						+ ", '"
						+ q.getqInstructions()
						+ "', '"
						+ q.getCorrectAns()
						+ "', '"
						+ q.getAnswerA()
						+ "', '"
						+ q.getAnswerB()
						+ "', '"
						+ q.getAnswerC()
						+ "', "
						+ q.getSubjectID() + ");");
		st = conn.createStatement();
		st.executeUpdate(query);
		return q;
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
				"SELECT * FROM `aes`.`Login` L WHERE L.UserID = " + userID
						+ " AND L.Password = '" + userPass + "' ;");
		st = conn.createStatement();
		int ans = Login.ERROR;
		qrs = st.executeQuery(query);
		if (qrs.next()) {
			if (qrs.getString(4).equals("0")) {
				query = new String(
						"UPDATE `aes`.`Login` L SET `LoggedIn` = 1 WHERE L.UserID = "
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
				"UPDATE `aes`.`Login` L SET `LoggedIn` = 0 WHERE L.UserID = "
						+ userID + ";");
		st = conn.createStatement();
		st.executeUpdate(query);
	}

	/**
	 * get from database the Lecturer Courses
	 * 
	 * @param LecturerID
	 * @return the Lecturer Courses
	 * @throws SQLException
	 */
	public ArrayList<Course> getLecturerCourses(int LecturerID)
			throws SQLException {

		ArrayList<Course> courses = new ArrayList<Course>();
		Course crs;
		ResultSet qrs = null;
		String query = new String(
				"SELECT C.ID,C.CourseName FROM `aes`.`courses per lecturer` CPL,`aes`.`course` C WHERE CPL.LecturerID = "
						+ LecturerID + " and C.ID=CPL.CourseID;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);

		while (qrs.next()) {
			crs = new Course();
			crs.setCourseID(Integer.parseInt(qrs.getString(1)));
			crs.setCourseName(qrs.getString(2));
			courses.add(crs);
		}
		qrs.close();
		return courses;
	}

	/**
	 * add exam to DB
	 * 
	 * @param exm
	 *            - the exam to add the database
	 * @return
	 * @throws SQLException
	 */
	public Exam addExam(Exam exm) throws SQLException {

		String query;
		int numberOfQuestions = 0;
		exm.setExamID(getExamNumber(exm.getSubjectID(), exm.getCourseID()));
		// adding exam:
		query = new String(
				"INSERT INTO `aes`.`exam` (`SubjectID`, `CourseID`, `ExamID`, `ExamCreationDate`, `ExamDurationTime`, `ExamCreatorID`, `ExamStudentInstructions`, `ExamLecturerInstructions`) VALUES ("
						+ exm.getSubjectID()
						+ ", "
						+ exm.getCourseID()
						+ ", "
						+ exm.getExamID()
						+ ", '"
						+ exm.getExamCreationDate().getTime()
						+ "', '"
						+ exm.getExamDurationTime().getTime()
						+ "', "
						+ exm.getExamLecturerID()
						+ ", '"
						+ exm.getExamStudentInstructions()
						+ "', '"
						+ exm.getExamLecturerInstructions() + "');");
		st = conn.createStatement();
		st.executeUpdate(query);
		// adding question per exam:
		numberOfQuestions = exm.getNumOfQuestions();
		for (int i = 0; i < numberOfQuestions; i++) {

			query = new String(
					"INSERT INTO `aes`.`questions per exam` (`SubjectID`, `CourseID`, `ExamID`, `QuestionID`, `QuestionPoints`) VALUES ("
							+ exm.getSubjectID()
							+ " , "
							+ exm.getCourseID()
							+ ", "
							+ exm.getExamID()
							+ ", "
							+ exm.getQuestion(i).getQuestion().getqID()
							+ ", "
							+ exm.getQuestion(i).getQuestionGrade() + ");");

			st = conn.createStatement();
			st.executeUpdate(query);
		}
		return exm;
	}

	/**
	 * get Exam Number from DB
	 * 
	 * @param sNum
	 *            -the subject number
	 * @param cNum
	 *            -the course number
	 * @return the Exam Number
	 * @throws SQLException
	 */
	public int getExamNumber(Integer sNum, Integer cNum) throws SQLException {
		ResultSet qrs = null;
		Integer eNum = null;
		String query = new String("SELECT MAX(ExamID) FROM `aes`.`exam` E "
				+ "WHERE E.SubjectID LIKE " + sNum + " and E.CourseID LIKE "
				+ cNum + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		try {
			if (qrs.next())
				eNum = new Integer(Integer.parseInt(qrs.getString(1)) + 1);

		} catch (Exception e) {
			eNum = new Integer(0);
		}
		qrs.close();
		return eNum;
	}

	/**
	 * 
	 * insert the extracted Exam to the DB
	 * 
	 * @param exm
	 *            the extracted Exam
	 * @throws SQLException
	 */
	public void extractExam(ExtractedExam exm) throws SQLException {
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		String query = new String(
				"INSERT INTO `aes`.`extracted exam`(`ExamCode`,`SubjectID`,`CourseID`,`ExamID`,`LecturerID`,`ExamExtractDate`,`ExamDurationTime`,`ExtensionDurationTime`,`Locked`,`CreatorID`)VALUES("
						+ " '"
						+ exm.getExamCode()
						+ "'"
						+ ", "
						+ exm.getSubjectID()
						+ ", "
						+ exm.getCourseID()
						+ ", "
						+ exm.getExamID()
						+ ", "
						+ exm.getLecturerID()
						+ ", "
						+ currentTime
						+ ", "
						+ exm.getExamDurationTime().getTime()
						+ ", "
						+ 0
						+ ", "
						+ exm.getLockedExam()
						+ ", "
						+ exm.getCreatorID()
						+ ");");
		st = conn.createStatement();
		st.executeUpdate(query);
	}

	/**
	 * 
	 * @param LecturerID
	 *            the Lecturer ID
	 * @return the Exams To Extract
	 * @throws SQLException
	 */
	public ArrayList<ExtractedExam> getExamsToExtract(int LecturerID)
			throws SQLException {
		ArrayList<ExtractedExam> exams = new ArrayList<ExtractedExam>();
		ExtractedExam exm;
		ResultSet qrs = null;
		String query = new String("SELECT * FROM `aes`.`exam` E WHERE EXISTS "
				+ " (SELECT * FROM `aes`.`courses per lecturer` CPL"
				+ " WHERE CPL.lecturerID= " + LecturerID
				+ " AND CPL.courseID=E.courseID);");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			exm = new ExtractedExam();
			exm.setExamID(Integer.parseInt(qrs.getString(3)));
			exm.setSubjectID(Integer.parseInt(qrs.getString(1)));
			exm.setCourseID(Integer.parseInt(qrs.getString(2)));
			exm.setExamDurationTime(new Date(Long.parseLong(qrs.getString(5))));
			exm.setLecturerID(Integer.parseInt(qrs.getString(6)));
			exm.unlockExam();
			exm.setCreatorID(LecturerID);
			exm.setExamLecturerInstructions(qrs.getString(8));
			exams.add(exm);
		}
		qrs.close();
		return exams;
	}

	/**
	 * 
	 * @param LecturerID
	 *            the Lecturer ID
	 * @return the subjects of the Lecturer
	 * @throws SQLException
	 */
	public ArrayList<Subject> getLecturerSubject(int LecturerID)
			throws SQLException {

		ArrayList<Subject> subjects = new ArrayList<Subject>();
		Subject sbj;
		ResultSet qrs = null;
		String query = new String(
				"SELECT distinct S.SubjectID,S.SubjectName FROM `aes`.`courses per lecturer` CPL,`aes`.`subject` S,`aes`.`courses per subject` CPS WHERE CPL.LecturerID = "
						+ LecturerID
						+ " and CPL.CourseID=CPS.CourseID and CPS.SubjectID=S.SubjectID; ");
		st = conn.createStatement();
		qrs = st.executeQuery(query);

		while (qrs.next()) {
			sbj = new Subject();
			sbj.setSubjectID(Integer.parseInt(qrs.getString(1)));
			sbj.setSubjectName(qrs.getString(2));
			subjects.add(sbj);
		}
		qrs.close();
		return subjects;
	}

	/**
	 * 
	 * @param subjectID
	 *            - the subject ID
	 * @return the Courses of the Lecturer
	 * @throws SQLException
	 */
	public ArrayList<Course> getSubjectCourses(Integer subjectID)
			throws SQLException {

		ArrayList<Course> courses = new ArrayList<Course>();
		Course crs;
		ResultSet qrs = null;
		String query = new String(
				"SELECT C.ID,C.CourseName FROM `aes`.`courses per subject` CPS,`aes`.`course` C WHERE CPS.SubjectID = "
						+ subjectID + " and C.ID=CPS.CourseID;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);

		while (qrs.next()) {
			crs = new Course();
			crs.setCourseID(Integer.parseInt(qrs.getString(1)));
			crs.setCourseName(qrs.getString(2));
			courses.add(crs);
		}
		qrs.close();
		return courses;
	}

	/**
	 * 
	 * @param subjectID
	 *            -the subject ID
	 * @return the Subject Questions
	 * @throws NumberFormatException
	 * @throws SQLException
	 */

	public ArrayList<Question> getSubjectQuestions(Integer subjectID)
			throws NumberFormatException, SQLException {
		ArrayList<Question> questions = new ArrayList<Question>();
		Question qstn;
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `aes`.`question` Q WHERE Q.SubjectID = "
						+ subjectID + " ;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			qstn = new Question();
			qstn.setqID(Integer.parseInt(qrs.getString(1)));
			qstn.setqText(qrs.getString(2));
			qstn.setqDate(new Date(Long.parseLong(qrs.getString(3))));
			qstn.setqCreator(Integer.parseInt(qrs.getString(4)));
			qstn.setqInstructions(qrs.getString(5));
			qstn.setCorrectAns(qrs.getString(6));
			qstn.setAnswerA(qrs.getString(7));
			qstn.setAnswerB(qrs.getString(8));
			qstn.setAnswerC(qrs.getString(9));
			qstn.setSubjectID(Integer.parseInt(qrs.getString(10)));
			questions.add(qstn);
		}
		qrs.close();
		return questions;
	}

	/**
	 * 
	 * @param lctrr
	 *            -the login Lecturer
	 * @return all Lecturer Extracted Exams
	 * @throws SQLException
	 */
	public ArrayList<ExtractedExam> getReports(Lecturer lctrr)
			throws SQLException {
		ArrayList<ExtractedExam> rprt = new ArrayList<ExtractedExam>();
		ExtractedExam exm;
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `aes`.`extracted exam` E WHERE E.lecturerID = "
						+ lctrr.getID() + " and E.locked >0 ;");

		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			exm = new ExtractedExam();
			exm.setExamCode(qrs.getString(1));
			exm.setExamID(Integer.parseInt(qrs.getString(4)));
			exm.setSubjectID(Integer.parseInt(qrs.getString(2)));
			exm.setCourseID(Integer.parseInt(qrs.getString(3)));
			exm.setExamDurationTime(new Date(Long.parseLong(qrs.getString(6))));
			exm.setExamExtractDate(null);
			exm.setLecturerID(lctrr.getID());
			exm.unlockExam();
			exm.setCreatorID(Integer.parseInt(qrs.getString(10)));

			rprt.add(exm);
		}

		qrs.close();
		return rprt;
	}

	/**
	 * 
	 * @param examcode
	 *            the exam code
	 * @return all the grades of the exam whit the exam code
	 * @throws SQLException
	 */
	public ArrayList<Integer> getReportFile(String examcode)
			throws SQLException {
		ArrayList<Integer> grdLst = new ArrayList<Integer>();
		Integer temp;
		ResultSet qrs = null;

		String query = new String("SELECT CE.grade "
				+ "from `aes`.`computerized exam` CE WHERE CE.examcode= '"
				+ examcode + "' and CE.Approved=1" + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			temp = new Integer(Integer.parseInt(qrs.getString(1)));
			grdLst.add(temp);// grades
		}

		return grdLst;

	}

	/**
	 * 
	 * @param examCode
	 *            - the exam Code
	 * @return computerized Exams wait to approve
	 * @throws SQLException
	 */
	public ArrayList<ComputerizedExam> getExamsToApprove(String examCode)
			throws SQLException {
		ArrayList<ComputerizedExam> computerizedExams = new ArrayList<ComputerizedExam>();
		ComputerizedExam cmptExam;
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `aes`.`computerized exam` CE WHERE CE.examCode = '"
						+ examCode
						+ "' and CE.approved = 0 and CE.finishStatus>0;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			cmptExam = new ComputerizedExam();
			cmptExam.setExamCode(qrs.getString(1));
			cmptExam.setStudentID(Integer.parseInt(qrs.getString(2)));
			cmptExam.setExamBeginingHour(new Date(Long.parseLong(qrs
					.getString(3))));
			cmptExam.setExamEndingHour(new Date(
					Long.parseLong(qrs.getString(4))));
			cmptExam.setExamGrade(Integer.parseInt(qrs.getString(5)));
			cmptExam.setExamFinishStatus(Integer.parseInt(qrs.getString(6)));
			cmptExam.setExamSuspectedCopy(Integer.parseInt(qrs.getString(9)));
			computerizedExams.add(cmptExam);
		}
		qrs.close();
		return computerizedExams;
	}

	/**
	 * 
	 * @param examID
	 *            - the exam ID
	 * @param courseID
	 *            -the course ID
	 * @param subjectID
	 *            -the subject ID
	 * @return list of questions Per Exam
	 * @throws SQLException
	 */
	public ArrayList<QuestionPerExam> getQuestionsPerExam(int examID,
			int courseID, int subjectID) throws SQLException {
		ResultSet qrs;
		ArrayList<QuestionPerExam> qstnsPrExm = new ArrayList<QuestionPerExam>();
		String query = new String(
				"SELECT * FROM `aes`.`questions per exam` QPE WHERE QPE.SubjectID = "
						+ subjectID + " and QPE.CourseID = " + courseID
						+ " and QPE.ExamID = " + examID + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			QuestionPerExam qstnPrExm = new QuestionPerExam();
			qstnPrExm.setQuestionGrade(Integer.parseInt(qrs.getString(5)));
			qstnPrExm.setQuestion(getQuestion(
					Integer.parseInt(qrs.getString(4)), subjectID));
			qstnsPrExm.add(qstnPrExm);
		}
		qrs.close();
		return qstnsPrExm;
	}

	/**
	 * 
	 * @param questionID
	 * @param subjectID
	 * @return the question from the DB
	 * @throws SQLException
	 */
	public Question getQuestion(int questionID, int subjectID)
			throws SQLException {
		ResultSet qrs;
		Question qstn = new Question();
		String query = new String(
				"SELECT * FROM `aes`.`question` Q WHERE Q.questionID = "
						+ questionID + " and Q.subjectID = " + subjectID + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		if (qrs.next()) {
			qstn.setqID(Integer.parseInt(qrs.getString(1)));
			qstn.setqText(qrs.getString(2));
			qstn.setqDate(new Date(Long.parseLong(qrs.getString(3))));
			qstn.setqCreator(Integer.parseInt(qrs.getString(4)));
			qstn.setqInstructions(qrs.getString(5));
			qstn.setCorrectAns(qrs.getString(6));
			qstn.setAnswerA(qrs.getString(7));
			qstn.setAnswerB(qrs.getString(8));
			qstn.setAnswerC(qrs.getString(9));
			qstn.setSubjectID(Integer.parseInt(qrs.getString(10)));
		}
		qrs.close();
		return qstn;
	}

	/**
	 * 
	 * @param examCode
	 * @return this function returns a single extracted exam from the db so that
	 *         the student would be able to be examined
	 * @throws SQLException
	 */
	public ExtractedExam getONEextractedExam(String examCode)
			throws SQLException {
		ExtractedExam exm = null;
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `aes`.`extracted exam` E WHERE  "
						+ " E.ExamCode = '" + examCode + "';");

		st = conn.createStatement();
		qrs = st.executeQuery(query);
		// now save the answers:
		exm = new ExtractedExam();
		if (qrs.next()) {
			exm.setExamCode(examCode);
			exm.setSubjectID(Integer.parseInt(qrs.getString(2)));
			exm.setCourseID(Integer.parseInt(qrs.getString(3)));
			exm.setExamID(Integer.parseInt(qrs.getString(4)));
			exm.setLecturerID(Integer.parseInt(qrs.getString(5)));
			exm.setExamExtractDate(new Date(Long.parseLong(qrs.getString(6))));
			exm.setExamDurationTime(new Date(Long.parseLong(qrs.getString(7))));
			exm.setExamExtantionDuration(new Date(Long.parseLong(qrs.getString(8))));
			if (Integer.parseInt(qrs.getString(9)) == 0)
				exm.unlockExam();
			else
				exm.lockExam();
			exm.setCreatorID(Integer.parseInt(qrs.getString(10)));
			qrs.close();
		} else {
			exm.setExamCode(ExtractedExam.ERROR);
			return exm;
		}

		// now get the instructions from the
		query = new String("SELECT * FROM `aes`.`exam` QPE "
				+ "WHERE QPE.SubjectID = " + exm.getSubjectID()
				+ " AND QPE.courseID =" + exm.getCourseID()
				+ " And QPE.examID = " + exm.getExamID() + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			exm.setExamLecturerInstructions(qrs.getString(8));
			exm.setExamStudentInstructions(qrs.getString(7));
		}
		qrs.close();

		return exm;
	}

	/**
	 * if the student took the exam already
	 * 
	 * @param examCode
	 *            -the exam Code
	 * @param studentID
	 *            -the student ID
	 * @return the exam
	 * @throws SQLException
	 */
	public ExtractedExam checkIfStudentTookExam(String examCode, int studentID)
			throws SQLException {
		ExtractedExam exm = null;
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `aes`.`computerized exam` CE WHERE CE.ExamCode = '"
						+ examCode + "' and CE.studentID = " + studentID + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		// now save the answers:
		exm = new ExtractedExam();
		if (qrs.next())
			exm.setExamCode(ExtractedExam.ERROR_EXAM_ALREADY_TOOK_PLACE);
		else
			exm.setExamCode(examCode);
		return exm;
	}

	/**
	 * this function gets all question numbers (and their score) needed for a
	 * student to take an exam and returns it as an array.
	 * 
	 * @param exm
	 * @throws SQLException
	 */
	public ArrayList<QuestionPerExam> getQuestionToGetExamined(ExtractedExam exm)
			throws SQLException {
		ArrayList<QuestionPerExam> qpeArr = new ArrayList<QuestionPerExam>();
		ResultSet qrs = null;
		QuestionPerExam qpe;

		// get from 'questions per exam': all question numbers and question points of
		// an exam with a certain examID, couseID and SubjectID:
		String query = new String(
				"SELECT * FROM `aes`.`questions per exam` QPE "
						+ "WHERE QPE.SubjectID = " + exm.getSubjectID()
						+ " AND QPE.courseID =" + exm.getCourseID()
						+ " And QPE.examID = " + exm.getExamID() + ";");

		st = conn.createStatement();

		qrs = st.executeQuery(query);

		while (qrs.next()) {
			qpe = new QuestionPerExam();
			qpe.SetQuestionNum(Integer.parseInt(qrs.getString(4)));
			qpe.setQuestionGrade(Integer.parseInt(qrs.getString(5)));
			qpeArr.add(qpe); // now add to the
		}
		qrs.close();
		return qpeArr;
	}

	/**
	 * 
	 * @param examCode
	 *            -the exam Code
	 * @param studentID
	 *            - the student ID
	 * @return the Answers for the exam
	 * @throws SQLException
	 */
	public ArrayList<Answer> getAnswers(String examCode, int studentID)
			throws SQLException {
		ArrayList<Answer> ansLst = new ArrayList<Answer>();
		Answer ans = null;
		ResultSet qrs;
		String query = new String(
				"SELECT * FROM `aes`.`answer` A WHERE A.examCode = '"
						+ examCode + "' and A.studentID = " + studentID + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			ans = new Answer();
			ans.setSubjectID(Integer.parseInt(qrs.getString(2)));
			ans.setQuestionID(Integer.parseInt(qrs.getString(3)));
			ans.setSelectedAnswer(Integer.parseInt(qrs.getString(5)));
			ansLst.add(ans);
		}
		qrs.close();
		return ansLst;
	}

	/**
	 * 
	 * @param creatorID
	 *            - the lecturer Which create the exam
	 * @return the extracted Exams
	 * @throws SQLException
	 */
	public ArrayList<ExtractedExam> getUnapprovedExams(int creatorID)
			throws SQLException {
		ArrayList<ExtractedExam> extractedExams = new ArrayList<ExtractedExam>();
		ExtractedExam exrtExam;
		ResultSet qrs = null;
		String query = new String(
				"SELECT DISTINCT(EE.ExamCode), EE.SubjectID, EE.CourseID, EE.ExamID, EE.ExamExtractDate FROM `aes`.`computerized exam` CE , `aes`.`extracted exam` EE WHERE EE.CreatorID = "
						+ creatorID
						+ " and CE.ExamCode = EE.ExamCode and CE.approved = 0 and CE.finishStatus>0 ;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			exrtExam = new ExtractedExam();
			exrtExam.setExamCode(qrs.getString(1));
			exrtExam.setSubjectID(Integer.parseInt(qrs.getString(2)));
			exrtExam.setCourseID(Integer.parseInt(qrs.getString(3)));
			exrtExam.setExamID(Integer.parseInt(qrs.getString(4)));
			exrtExam.setExamExtractDate(new Date(Long.parseLong(qrs
					.getString(5))));
			extractedExams.add(exrtExam);
		}
		qrs.close();
		return extractedExams;
	}

	/**
	 * 
	 * @param examCode
	 *            -the exam Code
	 * @return the file Name
	 * @throws SQLException
	 */
	public String getFileName(String examCode) throws SQLException {
		ResultSet qrs = null;

		String fileName = new String();
		String query = new String(
				"SELECT EF.fileName FROM  `aes`.`exam files` EF WHERE "
						+ "EF.examCode = '" + examCode + "';");

		st = conn.createStatement();
		qrs = st.executeQuery(query);
		if (qrs.next())
			fileName = qrs.getString(1);
		System.out.println(fileName);
		qrs.close();
		return fileName;
	}

	/**
	 * 
	 * @param creatorID
	 *            - the creator ID
	 * @return the extracted Exams
	 * @throws SQLException
	 */
	public ArrayList<ExtractedExam> getUnlockedExams(int creatorID)
			throws SQLException {
		ArrayList<ExtractedExam> extractedExams = new ArrayList<ExtractedExam>();
		ExtractedExam exrtExam;
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `aes`.`extracted exam` EE WHERE EE.CreatorID = "
						+ creatorID + " and EE.locked = 0 ;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			exrtExam = new ExtractedExam();
			exrtExam.setExamCode(qrs.getString(1));
			exrtExam.setSubjectID(Integer.parseInt(qrs.getString(2)));
			exrtExam.setCourseID(Integer.parseInt(qrs.getString(3)));
			exrtExam.setExamID(Integer.parseInt(qrs.getString(4)));
			exrtExam.setExamExtractDate(new Date(Long.parseLong(qrs
					.getString(6))));
			extractedExams.add(exrtExam);
		}
		qrs.close();
		return extractedExams;

	}

	/**
	 * check if the exam is locked
	 * 
	 * @param extExm
	 * @throws SQLException
	 */
	public ExtractedExam lockExam(ExtractedExam extExm) throws SQLException {

		ResultSet qrs = null;
		String query = new String(
				"SELECT EE.locked FROM `aes`.`extracted exam` EE WHERE EE.examCode = '"
						+ extExm.getExamCode() + "' ;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		if (qrs.next()) {
			if (qrs.getString(1).equals("2"))
				extExm.timedLockExam();
			else {
				query = new String(
						"UPDATE `aes`.`extracted exam` EE SET `locked` = 1 WHERE EE.examCode = '"
								+ extExm.getExamCode() + "' ;");
				st = conn.createStatement();
				st.executeUpdate(query);
				extExm.lockExam();
			}
		}
		return extExm;
	}

	/**
	 * 
	 * @param cmptExam
	 *            the Computerized Exam
	 * @return the approve Exam
	 */
	public ComputerizedExam approveExam(ComputerizedExam cmptExam) {
		String query = new String(
				"UPDATE `aes`.`computerized exam` SET `grade`="+ cmptExam.getExamGrade() +",`comments`='"
						+ cmptExam.getExamComments()
						+ "', `approved`=1 WHERE `ExamCode`='"
						+ cmptExam.getExamCode() + "' and`StudentID`= "
						+ cmptExam.getStudentID() + ";");
		try {
			st = conn.createStatement();
			st.executeUpdate(query);

		} catch (SQLException e) {
			cmptExam.setExamApproved(false);
		}

		return cmptExam;
	}

	/**
	 * upload file to the system
	 * 
	 * @param sid
	 *            -the StudentID
	 * @param myfile
	 *            - the File Name
	 * @param examCode
	 *            -the Exam Code
	 * @throws SQLException
	 */
	public void uploadFile(int sid, MyFile myfile, String examCode)
			throws SQLException {
		String query = new String(
				"INSERT INTO `aes`.`student files` (`StudentID`, `ExamCode`, `FileName`) VALUES ("
						+ sid
						+ ", '"
						+ examCode
						+ "', '"
						+ myfile.getName()
						+ "');");
		st = conn.createStatement();
		st.executeUpdate(query);

	}

	/**
	 * 
	 * @param student
	 *            -the login student
	 * @return the student computerizedExams
	 * @throws SQLException
	 */
	public ArrayList<ComputerizedExam> getExamsPerStudent(Student student)
			throws SQLException {
		ArrayList<ComputerizedExam> computerizedExams = new ArrayList<ComputerizedExam>();
		ComputerizedExam cmptExam;
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `aes`.`computerized exam` CE WHERE CE.StudentID = "
						+ student.getID() + ";");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			cmptExam = new ComputerizedExam();
			cmptExam.setExamCode(qrs.getString(1));
			cmptExam.setStudentID(Integer.parseInt(qrs.getString(2)));
			cmptExam.setExamBeginingHour(new Date(Long.parseLong(qrs
					.getString(3))));
			cmptExam.setExamEndingHour(new Date(
					Long.parseLong(qrs.getString(4))));
			cmptExam.setExamGrade(Integer.parseInt(qrs.getString(5)));
			cmptExam.setExamFinishStatus(Integer.parseInt(qrs.getString(6)));
			cmptExam.setExamComments(qrs.getString(7));
			cmptExam.setExamApproved(Integer.parseInt(qrs.getString(8)) == 1);
			cmptExam.setExamSuspectedCopy(Integer.parseInt(qrs.getString(9)));
			computerizedExams.add(cmptExam);
		}
		qrs.close();
		return computerizedExams;
	}

	/**
	 * 
	 * @param examCode
	 *            -the exam Code
	 * @return all the Question of the Exam
	 * @throws SQLException
	 */
	public ArrayList<QuestionPerExam> getQuestionsPerExam(String examCode)
			throws SQLException {
		ResultSet qrs;
		ArrayList<QuestionPerExam> qstnsPrExm = new ArrayList<QuestionPerExam>();
		String query = new String(
				"SELECT QPE.QuestionPoints, QPE.SubjectID, QPE.QuestionID FROM `aes`.`questions per exam` QPE, `aes`.`extracted exam` EE WHERE QPE.SubjectID = EE.SubjectID AND QPE.CourseID = EE.CourseID AND QPE.ExamID = EE.ExamID AND EE.ExamCode = '"
						+ examCode + "';");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			QuestionPerExam qstnPrExm = new QuestionPerExam();
			qstnPrExm.setQuestionGrade(Integer.parseInt(qrs.getString(1)));
			qstnPrExm.setQuestion(getQuestion(
					Integer.parseInt(qrs.getString(3)),
					Integer.parseInt(qrs.getString(2))));
			qstnsPrExm.add(qstnPrExm);
		}
		qrs.close();
		return qstnsPrExm;
	}

	/**
	 * 
	 * @param examCode
	 * @return the student computerized Exams
	 * @throws SQLException
	 */
	public ArrayList<ComputerizedExam> getStudentsPerComputerized(
			String examCode) throws SQLException {
		ArrayList<ComputerizedExam> computerizedExams = new ArrayList<ComputerizedExam>();
		ComputerizedExam cmptExam;
		ResultSet qrs = null;
		String query = new String(
				"SELECT * FROM `aes`.`computerized exam` CE WHERE CE.examCode = '"
						+ examCode
						+ "' and CE.approved = 0 and CE.finishStatus>0;");
		st = conn.createStatement();
		qrs = st.executeQuery(query);
		while (qrs.next()) {
			cmptExam = new ComputerizedExam();
			cmptExam.setExamCode(qrs.getString(1));
			cmptExam.setStudentID(Integer.parseInt(qrs.getString(2)));
			cmptExam.setExamBeginingHour(new Date(Long.parseLong(qrs
					.getString(3))));
			cmptExam.setExamEndingHour(new Date(
					Long.parseLong(qrs.getString(4))));
			cmptExam.setExamGrade(Integer.parseInt(qrs.getString(5)));
			cmptExam.setExamFinishStatus(Integer.parseInt(qrs.getString(6)));
			cmptExam.setExamSuspectedCopy(Integer.parseInt(qrs.getString(9)));
			computerizedExams.add(cmptExam);
		}
		qrs.close();
		return computerizedExams;
	}

	/**
	 * 
	 * @param cmpExm
	 *            -the Computerized Exam to add th DB
	 * @return if successes
	 */
	public ComputerizedExam addComputerizedExam(ComputerizedExam cmpExm) {
		String query = new String(
				"INSERT INTO `aes`.`computerized exam` (`ExamCode`, `StudentID`, `beginingHour`, `endingHour`, `grade`, `finishStatus`, `approved`, `suspectedCopy`) VALUES ('"
						+ cmpExm.getExamCode()
						+ "',"
						+ cmpExm.getStudentID()
						+ ", "
						+ cmpExm.getExamBeginingHour().getTime()
						+ ", "
						+ cmpExm.getExamEndingHour().getTime()
						+ ", "
						+ cmpExm.getExamGrade()
						+ ", "
						+ cmpExm.getExamFinishStatus()
						+ ", 0, "
						+ cmpExm.getExamSuspectedCopy() + ");");
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			cmpExm.setExamCode(ComputerizedExam.Error);
			e.printStackTrace();
		}
		return cmpExm;
	}

	/**
	 * add Computerized Exam answers to DB
	 * 
	 * @param cmpExm
	 *            the Computerized Exam
	 */
	public void addAnswers(ComputerizedExam cmpExm) {
		String query = null;
		for (int i = 0; i < cmpExm.getAnswers().size(); i++) {
			query = ("INSERT INTO `aes`.`answer` (`examCode`, `subjectID`, `questionID`, `studentID`, `selectedAnswer`) VALUES ('"
					+ cmpExm.getExamCode()
					+ "', "
					+ cmpExm.getAnswer(i).getSubjectID()
					+ ", "
					+ cmpExm.getAnswer(i).getQuestionID()
					+ ", "
					+ cmpExm.getStudentID()
					+ ", "
					+ cmpExm.getAnswer(i).getSelectedAnswer() + ");");
			try {
				st = conn.createStatement();
				st.executeUpdate(query);
			} catch (SQLException e) {
				cmpExm.getAnswer(i).setQuestionID(Question.ERROR);
			}
		}
	}

	public void updateCheatSuspection(ComputerizedExam exmSource,
			ComputerizedExam exm) throws SQLException {
		//when student a copied from student b:
		String query = new String(
				"UPDATE `aes`.`computerized exam` SET `suspectedCopy`="
						+ exmSource.getStudentID()
						+ " WHERE `ExamCode`='"
						+ exm.getExamCode() + "' and `StudentID`= "
						+ exm.getStudentID() + ";");
			st = conn.createStatement();
			st.executeUpdate(query);
			//================================================
			//when student b copied from  student a:
			query = new String(
					"UPDATE `aes`.`computerized exam` SET `suspectedCopy`="
							+ exm.getStudentID()
							+ " WHERE `ExamCode`='"
							+ exm.getExamCode() + "' and `StudentID`= "
							+ exmSource.getStudentID() + ";");
				st = conn.createStatement();
				st.executeUpdate(query);
			 
	}

}