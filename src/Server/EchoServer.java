package server;

// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import GUI.ServerGUI;
import MsgPackage.*;

import common.*;
import entities.*;
import files.Browse;
import files.MyFile;
import files.Save;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 * 
 * @author Omri Barda 039725890
 * @author Amit Joseph 034608547
 * @author Gilad Shpigel 300162393
 * @author Elad Elbaz 040539959
 * @version May 2012
 */
public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	Database db;
	HashMap<String,ArrayList<ConnectionToClient>> studentsInExam;

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
			Perror.sFatalError("Could not listen for clients!");
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
		case Login:
			login(msgpck, client);
			break;
		case Logout:
			logout(msgpck, client);
			break;
		case AddQuestion:
			addQuestion(msgpck, client);
			break;
		case AddExam:
			addExam(msgpck, client);
			break;
		case GetCoursesPerSubject:
			getCoursesPerSubject(msgpck, client);
			break;
		case GetQuestionsPerSubject:
			getQuestionsPerSubject(msgpck, client);
			break;
		case GetLecturerCourse:
			getLecturerCourse(msgpck, client);
			break;
		case GetLecturerSubject:
			GetLecturerSubject(msgpck, client);
			break;
		case GetExamNumber:
			getExamNumber(msgpck, client);
			break;
		case GetQuestionNumber:
			getQuestionNumber(msgpck, client);
			break;
		case GetUnlockedExams:
			getUnlockedExams(msgpck, client);
			break;
		case LockExam:
			lockExam(msgpck, client);
			break;
		case GetUnapprovedExams:
			getUnapprovedExams(msgpck, client);
			break;
		case GetActiveExams:
			getActiveExams(msgpck, client);
			break;
		case AddDurationTime:
			addDurationTimeRequest(msgpck, client);
			approveRequest(msgpck);
			break;
		case GetExamsToApprove:
			getExamsToApprove(msgpck, client);
			break;
		case GetExamsToExtract:
			getExamToExtract(msgpck, client);
			break;
		case ExtractExam:
			extractExam(msgpck, client);
			break;
		case LecturerReport:
			addReport(msgpck, client);
			break;
		case LecturerReportFile:
			getReportFile(msgpck, client);
			break;
		case getExamFile:
			getStudentExamFile(msgpck, client);
			break;
		case uploadExamFile:
			StudentUploadExamFile(msgpck, client);
			break;

		case ApproveGrade:
			approveGrade(msgpck, client);
			break;

		case GetExamsPerStudent:
			getExamsPerStudent(msgpck, client);
			break;

		case AddComputerzdExam:
			addComputerizedExam(msgpck, client);
			CheckForCheating(msgpck);
			break;

		case GetExtentionForExam:
			getExtentionForExam(msgpck, client);
			break;

		case GetExamined:
			getExaminedNow(msgpck, client);
			break;

		}
	}

	private void approveRequest(MessagePack msgpck) {
		ExtractedExam extExam = new ExtractedExam();
		AddDurationTimePack durRqst = (AddDurationTimePack) msgpck;
		ArrayList<ConnectionToClient> clientLst = new ArrayList<ConnectionToClient>(); 
		clientLst.addAll(studentsInExam.get(durRqst.getRqst().getExamCode()));
		studentsInExam.remove(durRqst.getRqst().getExamCode());
		
		extExam.setExamCode(durRqst.getRqst().getExamCode());
		extExam.setExamExtantionDuration(new Date(durRqst.getRqst().getExtensionDurationTime()));
		
		GetExtentionForExamPack examExtentionPck = new GetExtentionForExamPack(extExam);
		examExtentionPck.setApproved(true);
		
		for (int i = 0; i<clientLst.size();i++)
		{
			try {
				clientLst.get(i).sendToClient(examExtentionPck);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void getExtentionForExam(MessagePack msgpck,
			ConnectionToClient client) {
		ExtractedExam exm;
		GetExtentionForExamPack extForExam = (GetExtentionForExamPack)msgpck;
		exm = extForExam.getExtrctExam();
		if(!studentsInExam.containsKey(exm.getExamCode()))
			studentsInExam.put(exm.getExamCode(), new ArrayList<ConnectionToClient>());
		studentsInExam.get(exm.getExamCode()).add(client);	
	}

	synchronized private void CheckForCheating(MessagePack msgpck) {
		AddComputerizedExamPack cmpExmPck = (AddComputerizedExamPack) msgpck;
		ArrayList<ComputerizedExam> cmpExms;
		ComputerizedExam exmSource;
		ComputerizedExam exm;
		int qID, ans;
		int qIDsource, ansSource;
		int stdID,stdIDSrc;
		cmpExms = examsForCheckCheating(cmpExmPck.getComputerizedExam()
				.getExamCode());
		exm = cmpExmPck.getComputerizedExam();
		stdID = exm.getStudentID();
		for (int j = 0, suspected = 0; j < cmpExms.size(); j++) {
			// scanning for exams not including current exam
			exmSource = cmpExms.get(j);
			
			for (int k = 0; k < exm.getAnswers().size(); k++) {
				qID = exm.getAnswer(k).getQuestionID();
				ans = exm.getAnswer(k).getSelectedAnswer();
				for (int l = 0; l < exm.getAnswers().size(); l++) {
					stdIDSrc = exmSource.getStudentID();
					qIDsource = exmSource.getAnswer(k).getQuestionID();
					ansSource = exmSource.getAnswer(k).getSelectedAnswer();
					if (qID == qIDsource // if the same question
							&& ans == ansSource // if the same answer
							&& stdID != stdIDSrc
							&& ans != 1 && ans != -1) // if not the right answer
					{
						suspected++;
						break;
					}
				}
				if (suspected == (exm.getAnswers().size()/2)) {
					try {
						db.updateCheatSuspection(exmSource, exm);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
			}
			if (suspected == 2)
				break;
		}
	}

	private synchronized void addComputerizedExam(MessagePack msgpck,
			ConnectionToClient client) {
		//add computerized exam to DB
		AddComputerizedExamPack cmpExmPck = (AddComputerizedExamPack) msgpck;
		ComputerizedExam cmpExm = cmpExmPck.getComputerizedExam();
		cmpExm = db.addComputerizedExam(cmpExm);
		if (!cmpExm.equals(ComputerizedExam.Error))
			db.addAnswers(cmpExm);
		try {
			client.sendToClient(cmpExmPck);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		// if exam exam were finished because time is up lock exam in DB.
		if (cmpExm.getExamFinishStatus() == ComputerizedExam.STATUS_TIMESUP)
			{
				ExtractedExam extExm =new ExtractedExam();
				extExm.setExamCode(cmpExm.getExamCode());
				try {
					db.lockExam(extExm);
					studentsInExam.remove(cmpExm.getExamCode());
				} catch (SQLException e) {
				}
			}
		

	
	}

	private void getExaminedNow(MessagePack msg, ConnectionToClient client) {
		GetExaminedPack pck = (GetExaminedPack) msg;
		ArrayList<QuestionPerExam> qstnArr = null;
		ExtractedExam extrctd = null;
		Question qstn = null;
		QuestionPerExam qpe;
		try {
			extrctd = db.checkIfStudentTookExam(pck.getExamCode(), pck
					.GetStudent().getID());
			if (!extrctd.getExamCode().equals(
					ExtractedExam.ERROR_EXAM_ALREADY_TOOK_PLACE))
				extrctd = db.getONEextractedExam(pck.getExamCode());
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		pck.setExtractedExam(extrctd);
		if ((!extrctd.getExamCode().equals(ExtractedExam.ERROR))
				&& (!extrctd.getExamCode().equals(
						ExtractedExam.ERROR_EXAM_ALREADY_TOOK_PLACE))) {
			try {
				qstnArr = db.getQuestionToGetExamined(extrctd);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			} // get the question numbers and scores from DB
				// and the final phase: get the questions according to the
				// questions' ID.
			for (int i = 0; i < (qstnArr.size()); i++) {
				try {
					qpe = qstnArr.get(i);
					qstn = db.getQuestion(qpe.getQuestion().getqID(),
							extrctd.getSubjectID());
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				qstnArr.get(i).setQuestion(qstn);
			}
			pck.setQuestionsArray(qstnArr); // add to the package
		}
		try {
			client.sendToClient(pck);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void StudentUploadExamFile(MessagePack msg,
			ConnectionToClient client) {
		StudentUploadFilePack uploadFile = (StudentUploadFilePack) msg;
		uploadFile.setSuccess(true);
		Save save = new Save(uploadFile.getMyfile());
		try {
			db.uploadFile(uploadFile.getSID(), uploadFile.getMyfile(),
					uploadFile.getExamCode());
		} catch (SQLException e) {
			uploadFile.setSuccess(false);
		}
		try {
			client.sendToClient(uploadFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private synchronized void approveGrade(MessagePack msgpck,
			ConnectionToClient client) {
		ApproveGradePack appGradePck = (ApproveGradePack) msgpck;
		ComputerizedExam dbCompExm = null;
		dbCompExm = db.approveExam(appGradePck.getCmptExam());
		appGradePck.setCmptExam(dbCompExm);
		try {
			client.sendToClient(appGradePck);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	private void getUnlockedExams(MessagePack msg, ConnectionToClient client) {
		GetUnlockedExamsPack unlckdExms = (GetUnlockedExamsPack) msg;

		ArrayList<ExtractedExam> dbUlockedExams = null;

		try {

			dbUlockedExams = db.getUnlockedExams(unlckdExms.getLecturer()
					.getID());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		unlckdExms.addExams(dbUlockedExams);
		try {
			client.sendToClient(unlckdExms);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getExamsToApprove(MessagePack msg, ConnectionToClient client) {
		GetExamsToApprovePack exmsToApprv = (GetExamsToApprovePack) msg;
		ArrayList<ComputerizedExam> dbUnapprovedExams = null;
		ArrayList<QuestionPerExam> dbQstnPerExm = null;
		// Adding Exams
		try {
			dbUnapprovedExams = db.getExamsToApprove(exmsToApprv.getExrtExam()
					.getExamCode());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		exmsToApprv.addExams(dbUnapprovedExams);

		// Adding answers per exams
		for (int i = 0; i < exmsToApprv.ComputerizedExamSize(); i++) {
			try {
				exmsToApprv.insertAnswers(db.getAnswers(exmsToApprv
						.getExrtExam().getExamCode(), exmsToApprv
						.getComputerizedExam(i).getStudentID()), i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Adding the exam questions
		try {
			dbQstnPerExm = db.getQuestionsPerExam(exmsToApprv.getExrtExam()
					.getExamID(), exmsToApprv.getExrtExam().getCourseID(),
					exmsToApprv.getExrtExam().getSubjectID());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		exmsToApprv.addQuestionPerExam(dbQstnPerExm);

		// Sending to server
		try {
			client.sendToClient(exmsToApprv);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getUnapprovedExams(MessagePack msg, ConnectionToClient client) {
		GetUnapprovedExamsPack unprvExms = (GetUnapprovedExamsPack) msg;
		ArrayList<ExtractedExam> dbUnapprovedExams = null;

		try {
			dbUnapprovedExams = db.getUnapprovedExams(unprvExms.getLecturer()
					.getID());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		unprvExms.addExams(dbUnapprovedExams);
		try {
			client.sendToClient(unprvExms);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//
	private void getStudentExamFile(MessagePack msg, ConnectionToClient client) {
		StudentGetFilePack downlodFile = (StudentGetFilePack) msg;
		String dbFileName = new String();
		try {
			dbFileName = db.getFileName(downlodFile.getExamCode());

		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if ((!dbFileName.isEmpty())) {
			downlodFile.setGood(true);
			File f = new File("C://group21//" + dbFileName);
			Browse b = new Browse(f);
			MyFile myFile = b.getFile();

			downlodFile.setFileName(dbFileName);
			downlodFile.setMyfile(myFile);
		} else
			downlodFile.setGood(false);

		// downlodFile.AddReport(dbFileName);
		try {
			client.sendToClient(downlodFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getReportFile(MessagePack msg, ConnectionToClient client) {

		LecturerReportFilePack lecrfp = (LecturerReportFilePack) msg; //

		ArrayList<Integer> dbReport = null;
		try {
			dbReport = db.getReportFile(lecrfp.getExam().getExamCode());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		lecrfp.setGrades(dbReport);
		try {
			client.sendToClient(lecrfp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private synchronized void addReport(MessagePack msg,
			ConnectionToClient client) {
		LecturerReportPack lctrrexam = (LecturerReportPack) msg;
		ArrayList<ExtractedExam> dbReport = null;
		try {
			dbReport = db.getReports(lctrrexam.getLctrr());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		lctrrexam.AddReport(dbReport);
		try {
			client.sendToClient(lctrrexam);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void getQuestionsPerSubject(MessagePack msg,
			ConnectionToClient client) {

		GetQuestionsPerSubjectPack sbjctQstns = (GetQuestionsPerSubjectPack) msg;
		ArrayList<Question> dbQuestions = null;

		try {
			dbQuestions = db.getSubjectQuestions(sbjctQstns.getSbjct()
					.getSubjectID());
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		sbjctQstns.AddQuestions(dbQuestions);
		try {
			client.sendToClient(sbjctQstns);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void getExamNumber(MessagePack msg, ConnectionToClient client) {
		GetExamNumberPack exm = (GetExamNumberPack) msg;
		Integer exmNum = null;

		try {
			exmNum = new Integer(db.getExamNumber(exm.getSubjectNum(),
					exm.getCourseNum()));
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		exm.setNewExamNum(exmNum);
		try {
			client.sendToClient(exm);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void getCoursesPerSubject(MessagePack msg, ConnectionToClient client) {
		GetCoursesPerSubjectPack sbjctCrs = (GetCoursesPerSubjectPack) msg;
		ArrayList<Course> dbCourses = null;

		try {
			dbCourses = db
					.getSubjectCourses(sbjctCrs.getSbjct().getSubjectID());
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		sbjctCrs.AddCourses(dbCourses);
		try {
			client.sendToClient(sbjctCrs);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private synchronized void addExam(MessagePack msg, ConnectionToClient client) {
		AddExamPack exm = (AddExamPack) msg;

		try {
			exm.setExm(db.addExam(exm.getExm()));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			client.sendToClient(exm);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void GetLecturerSubject(MessagePack msg, ConnectionToClient client) {
		GetLecturerSubjectPack lctrrSbj = (GetLecturerSubjectPack) msg;
		ArrayList<Subject> dbSubjects = null;

		try {
			dbSubjects = db.getLecturerSubject((lctrrSbj.getLctrr().getID()));
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		lctrrSbj.AddSubjects(dbSubjects);
		try {
			client.sendToClient(lctrrSbj);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private synchronized void extractExam(MessagePack msg,
			ConnectionToClient client) {
		ExtractExamPack lctrrnewexam = (ExtractExamPack) msg;
		String answar = new String("exam code generated");
		try {

			db.extractExam(lctrrnewexam.GetExam());

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			answar = "Error: code already exists";
		}
		try {

			client.sendToClient(answar);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void getExamToExtract(MessagePack msg, ConnectionToClient client) {
		GetExamsToExtractPack lctrrexam = (GetExamsToExtractPack) msg;
		ArrayList<ExtractedExam> dbExam = null;
		try {
			dbExam = db.getExamsToExtract(lctrrexam.getLctrr().getID());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		lctrrexam.addExams(dbExam);
		try {
			client.sendToClient(lctrrexam);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	// lctrrexam.getLctrr().getID()
	private void getQuestionNumber(MessagePack msg, ConnectionToClient client) {
		GetQuestionNumberPack qstn = (GetQuestionNumberPack) msg;
		Integer qstnNum = null;

		try {
			qstnNum = new Integer(db.getQuestionNumber(qstn.getSubjectNum()));
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		qstn.setNewQuestionNum(qstnNum);
		try {
			client.sendToClient(qstn);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getActiveExams(MessagePack msg, ConnectionToClient client) {
		GetActiveExamsPack actvExams = (GetActiveExamsPack) msg;
		ArrayList<ExtractedExam> dbActiveExams = null;

		try {
			dbActiveExams = db.getActiveExams(actvExams.getLctrr().getID());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		actvExams.addExams(dbActiveExams);

		try {
			client.sendToClient(actvExams);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized void addDurationTimeRequest(MessagePack msg,
			ConnectionToClient client) {
		AddDurationTimePack drtnTime = (AddDurationTimePack) msg;
		try {
			db.addDurationTimeReuqest(drtnTime.getRqst());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		try {
			client.sendToClient(drtnTime);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getLecturerCourse(MessagePack msg, ConnectionToClient client) {
		GetLecturerCoursesPack lctrrCrs = (GetLecturerCoursesPack) msg;
		ArrayList<Course> dbCourses = null;

		try {
			dbCourses = db.getLecturerCourses(lctrrCrs.getLctrr().getID());
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		lctrrCrs.AddCourses(dbCourses);
		try {
			client.sendToClient(lctrrCrs);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	private synchronized void addQuestion(MessagePack msg,
			ConnectionToClient client) {
		AddQuestionPack qstn = (AddQuestionPack) msg;

		try {
			qstn.setQuestion(db.addQuestion(qstn.getQuestion()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			client.sendToClient(qstn);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized void logout(MessagePack msg, ConnectionToClient client) {
		LogoutPack lgt = (LogoutPack) msg;
		try {
			db.exitUser(lgt.getUsr().getUserID());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private synchronized void lockExam(MessagePack msg,
			ConnectionToClient client) {
		LockExamPack lckExm = (LockExamPack) msg;
		ExtractedExam dbLockExam = null;

		try {
			dbLockExam = db.lockExam(lckExm.getExtExm());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		lckExm.setExtExm(dbLockExam);
		try {
			client.sendToClient(lckExm);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (dbLockExam.getLockedExam() == 1)
			stopExam(dbLockExam.getExamCode());
	}

	private void stopExam(String examCode) {
		ExtractedExam extExam = new ExtractedExam();
		ArrayList<ConnectionToClient> clientLst = new ArrayList<ConnectionToClient>();
		clientLst.addAll(studentsInExam.get(examCode));
		studentsInExam.remove(examCode);
		extExam.setExamCode(examCode);
		
		StopExamPack stopExmPck = new StopExamPack(extExam);
		
		for (int i = 0; i<clientLst.size();i++)
		{
			if (clientLst.get(i).isAlive())
			try {
				clientLst.get(i).sendToClient(stopExmPck);
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	private void getExamsPerStudent(MessagePack msg, ConnectionToClient client) {
		GetExamsPerStudentPack exmsPerStdnt = (GetExamsPerStudentPack) msg;
		ArrayList<ComputerizedExam> dbStudentExams = null;
		ArrayList<QuestionPerExam> dbQstnPerExm = null;

		// Adding Exams
		try {

			dbStudentExams = db.getExamsPerStudent(exmsPerStdnt.getStudent());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		exmsPerStdnt.addExams(dbStudentExams);

		// Adding answers per exams
		for (int i = 0; i < exmsPerStdnt.ComputerizedExamSize(); i++) {
			try {
				exmsPerStdnt.insertAnswers(db.getAnswers(exmsPerStdnt
						.getComputerizedExam(i).getExamCode(), exmsPerStdnt
						.getComputerizedExam(i).getStudentID()), i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Adding the exam questions
			try {
				dbQstnPerExm = db.getQuestionsPerExam(exmsPerStdnt
						.getComputerizedExam(i).getExamCode());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			exmsPerStdnt.addQuestionPerExam(
					dbStudentExams.get(i).getExamCode(), dbQstnPerExm);
		}
		// Sending to server
		try {
			client.sendToClient(exmsPerStdnt);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<ComputerizedExam> examsForCheckCheating(String examCode) {
		ArrayList<ComputerizedExam> dbCheckCheating = null;
		// Adding Exams
		try {
			dbCheckCheating = db.getExamsToApprove(examCode);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Adding answers per exams
		for (int i = 0; i < dbCheckCheating.size(); i++) {
			try {
				dbCheckCheating.get(i).setAnswers(
						db.getAnswers(examCode, dbCheckCheating.get(i)
								.getStudentID()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dbCheckCheating;
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
