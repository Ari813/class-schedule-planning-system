package Controllers;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entities.Building;
import entities.Campus;
import entities.Class;
import entities.ClassesAids;
import entities.Course;
import entities.Faculty;
import entities.Lecturer;
import entities.StudyAids;
import Client.ChatClient;
import GUI.*;
import MsgPackage.*;
import MsgPackage.GetAllLecturersPack.getInformation;

public class ManagerController {
	final public static int EXIT = 11;
	final public static int MAIN = 2;
	final public static int AUTO = 3;
	final public static int MAMUAL = 4;
	final public static int LECTURERPREFER = 5;
	final public static int COURSSETTING = 6;
	final public static int EDITCOURESES = 7;
	final public static int EDITCLASS = 8;
	final public static int EDITLECTURER = 9;
	final public static int SETTING = 10;
	final public static int MAGI = 0;
	final public static int NOT_MAGI = 1;

	final public static int EDITCLASSGUI = 20;
	final public static int EDITCOURSGUI = 21;

	private Lecturer_Preferences LP;
	private Automatic_Sheduling AS;
	private Course_Settings CS;
	private Edit_Class ECLSS;
	private Edit_Course ECRS;
	private Edit_Lecturer EL;
	private Manual_Sheduling MS;
	public static int fix=0;
	public Edit_Course ed;
	private Main_Menu main;
	public Main_Frame manegerMainFrm;
	public LecturerController lecturer_Ctrl;

	private ChatClient client;
	private GetAllLecturersPack LecMsg;
	private GetAllClassesPack ClassMsg;
	private GetAllCoursePack CourseMsg;
	private GetAllFacultyPack FacultyMsg;

	public ManagerController(Main_Frame mainFrm, ChatClient client) {
		this.client = client;
		manegerMainFrm = mainFrm;
		main = new Main_Menu(NOT_MAGI, this);
		manegerMainFrm.add(main.PNL_Main);
	}

	/*
	 * public void handleManagerGUI(int operation) { switch (operation) { case
	 * EXIT:// logout
	 * 
	 * break;
	 * 
	 * } }
	 */
	public void BacktoMainMenu(JPanel Panel2Close) {
		manegerMainFrm.remove(Panel2Close);
		manegerMainFrm.add(main.PNL_Main);
		manegerMainFrm.repaint();
	}

	public void Load_Lecturer_Preferences(JPanel Panel2Close) {
		manegerMainFrm.remove(Panel2Close);
		LP = new Lecturer_Preferences(null, this);
		LP.setLecturers(getAvailableLecturers(getInformation.schedual));
		manegerMainFrm.add(LP.PNL_Main);
		// lecturer_Ctrl = new LecturerController(this);
		manegerMainFrm.repaint();
	}

	public void Load_Automatic_Sheduling(JPanel Panel2Close) {
		
		
		
		manegerMainFrm.remove(Panel2Close);
		AS = new Automatic_Sheduling(this);
		manegerMainFrm.add(AS.PNL_Main);
		AS.addActions();
		manegerMainFrm.repaint();
		
	}

	// //////////////
	public void Load_Edit_Course(JPanel Panel2Close) {
		fix =0;
		manegerMainFrm.remove(Panel2Close);
		ECRS = new Edit_Course(this);
		ECRS.setCourses(getCourse());
		ECRS.setFaculty(getFaculty());
		ECRS.setAvailableLecturers(getAvailableLecturers(getInformation.nothing));
		ECRS.setStudyAids(GetClassAids());
		ECRS.setdefault();
		
		ECRS.addActions();
		manegerMainFrm.add(ECRS.PNL_Main);
		manegerMainFrm.repaint();
	}


	public void Load_Course_Settings(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		// /load all we need

		CS = new Course_Settings(this);
		CS.setFaculty(getFaculty());
		
		CS.setCourse(getCourse());
		manegerMainFrm.add(CS.PNL_Main);
		manegerMainFrm.repaint();
		CS.addActions();
	}

	public void Load_Edit_Lecturer(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		EL = new Edit_Lecturer(this);
		EL.setLec(getAvailableLecturers(getInformation.courses));
		EL.setcourse(getCourse());
		manegerMainFrm.add(EL.PNL_Main);
		manegerMainFrm.repaint();
		EL.addActions();
	}

	public void Load_Edit_Class(JPanel Panel2Close) {
		manegerMainFrm.remove(Panel2Close);
		ECLSS = new Edit_Class(this);

		ECLSS.setClasses(GetClasses());

		ECLSS.setClassStudyAids(GetClassAids());
		ECLSS.setCampus(getCampuses());
		ECLSS.setBuilding(getBuildings());
		// ECLSS.setAidsForExistingClasses(GetAidsForExistingClasses());
		manegerMainFrm.add(ECLSS.PNL_Main);
		ECLSS.addActions();
		manegerMainFrm.repaint();
	}

	public void Load_Manual_Sheduling(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		MS = new Manual_Sheduling(this);
		MS.setFaculty(getFaculty());
		MS.setClasses(GetClasses());
		MS.setCourse(getCourse());
		MS.setLec(getAvailableLecturers(getInformation.all));
		manegerMainFrm.add(MS.PNL_Main);
		MS.addActions();
		manegerMainFrm.repaint();
	}
	
	
	
	
	
	
	
	
	private ArrayList<Lecturer> getAvailableLecturers(
			getInformation additionalInfo) {
		GetAllLecturersPack AvailableLecturers = new GetAllLecturersPack();
		AvailableLecturers.setAdditionalInfo(additionalInfo);
		client.handleMessageFromClientUI(AvailableLecturers);
		AvailableLecturers = (GetAllLecturersPack) client.getMessage();

		return (AvailableLecturers.getAllLecturers());

	}

	private ArrayList<Faculty> getFaculty() {

		FacultyMsg = new GetAllFacultyPack();
		client.handleMessageFromClientUI(FacultyMsg);
		FacultyMsg = (GetAllFacultyPack) client.getMessage();
		return (FacultyMsg.getAllFaculty());
	}

	private ArrayList<Course> getCourse() {

		CourseMsg = new GetAllCoursePack();
		CourseMsg.setAdditionalInfo();
		client.handleMessageFromClientUI(CourseMsg);
		CourseMsg = (GetAllCoursePack) client.getMessage();
		return (CourseMsg.getAllclass());
	}

	
	private ArrayList<ClassesAids> GetAidsForExistingClasses() {
		GetClassAidsPack ClassAidsMessage = new GetClassAidsPack();
		client.handleMessageFromClientUI(ClassAidsMessage);
		ClassAidsMessage = (GetClassAidsPack) client.getMessage();

		return (ClassAidsMessage.getAllclassAids());
	}

	// ////
	private ArrayList<StudyAids> GetClassAids() {
		GetStudyAidsPack studyAidsMessage = new GetStudyAidsPack();
		client.handleMessageFromClientUI(studyAidsMessage);
		studyAidsMessage = (GetStudyAidsPack) client.getMessage();
		return (studyAidsMessage.getAllStudyAids());
	}

	public ArrayList<Class> GetClasses() {
		ClassMsg = new GetAllClassesPack();
		client.handleMessageFromClientUI(ClassMsg);
		ClassMsg = (GetAllClassesPack) client.getMessage();
		return (ClassMsg.getAllclass());
	}

	private ArrayList<Campus> getCampuses() {
		GetCampusPack CampusMsg = new GetCampusPack();
		client.handleMessageFromClientUI(CampusMsg);
		CampusMsg = (GetCampusPack) client.getMessage();
		return (CampusMsg.getAllCampuses());
	}

	private ArrayList<Building> getBuildings() {
		GetBuildingsPack BuildingMsg = new GetBuildingsPack();
		client.handleMessageFromClientUI(BuildingMsg);
		BuildingMsg = (GetBuildingsPack) client.getMessage();
		return (BuildingMsg.getAllBuildings());
	}

	public Course CreateNewCourse(Course newCourse) {

		NewCoursePack NewCourseMsg = new NewCoursePack();
		NewCourseMsg.setNewCourse(newCourse);
		client.handleMessageFromClientUI(NewCourseMsg);
		NewCourseMsg = (NewCoursePack) client.getMessage();

		return NewCourseMsg.getNewCourse();
	}

	public Course UpdateNewCourse(Course newCourse) {

		UpdateCoursePack updateCourseMsg = new UpdateCoursePack();
		updateCourseMsg.setNewCourse(newCourse);
		client.handleMessageFromClientUI(updateCourseMsg);
		updateCourseMsg = (UpdateCoursePack) client.getMessage();

		return updateCourseMsg.getNewCourse();
	}



	public void logout() {
		// TODO Auto-generated method stub
		manegerMainFrm.handleLogoutGUI();

	}

	public boolean saveCoureSet(Map<Integer, ArrayList<Course>> coursePerFuculty) {
		// UpdateEstimatedStudentsNumPerClassPack

		UpdateEstimatedStudentsNumPerClassPack updateMsg = new UpdateEstimatedStudentsNumPerClassPack();
		updateMsg.setCoursePerFucultyMap(coursePerFuculty);
		client.handleMessageFromClientUI(updateMsg);

		updateMsg = (UpdateEstimatedStudentsNumPerClassPack) client
				.getMessage();

		return (updateMsg.isSucceed());

	}

	public Lecturer CreateNewLecturer(Lecturer newLecturer) {
		NewLecturerPack NewLecturerMsg = new NewLecturerPack();
		NewLecturerMsg.setNewLecturer(newLecturer);
		client.handleMessageFromClientUI(NewLecturerMsg);
		NewLecturerMsg = (NewLecturerPack) client.getMessage();

		return NewLecturerMsg.getNewLecturer();
	}

	public Lecturer UpdateNewLecturer(Lecturer newLecturer) {
		UpdateLecturerPack NewLecturerMsg = new UpdateLecturerPack();
		NewLecturerMsg.setNewLecturer(newLecturer);
		client.handleMessageFromClientUI(NewLecturerMsg);
		NewLecturerMsg = (UpdateLecturerPack) client.getMessage();

		return NewLecturerMsg.getNewLecturer();
	}

	public Class CreateNewClass(Class newClass) {
		NewClassPack newClassMsg = new NewClassPack();
		newClassMsg.setNewClass(newClass);
		client.handleMessageFromClientUI(newClassMsg);
		newClassMsg = (NewClassPack) client.getMessage();

		return newClassMsg.getNewClass();
	}

	public Class UpdateNewClass(Class newClass) {
		UpdateClassPack newClassMsg = new UpdateClassPack();
		newClassMsg.setNewClass(newClass);
		client.handleMessageFromClientUI(newClassMsg);
		newClassMsg = (UpdateClassPack) client.getMessage();

		return newClassMsg.getNewClass();
	}

	public boolean UpdateTable(ArrayList<Lecturer> arrayLecturer) {
		
		UpdateLecturersPreferencesPack NewLecturerscScheduleMsg = new UpdateLecturersPreferencesPack();
		NewLecturerscScheduleMsg.setAllLecturers(arrayLecturer);
		client.handleMessageFromClientUI(NewLecturerscScheduleMsg);
		NewLecturerscScheduleMsg = (UpdateLecturersPreferencesPack) client.getMessage();

		return NewLecturerscScheduleMsg.isSucceed();	
		
	}

}
