package Controllers;

import java.util.ArrayList;

import javax.swing.JPanel;

import Client.ChatClient;
import GUI.Automatic_Sheduling;
import GUI.Course_Settings;
import GUI.Edit_Class;
import GUI.Edit_Course;
import GUI.Edit_Lecturer;
import GUI.Lecturer_Preferences;
import GUI.Main_Frame;
import GUI.Main_Menu;
import GUI.Manual_Sheduling;
import MsgPackage.ClassPack;

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

	public Edit_Course ed;
	private Main_Menu main;
	public Main_Frame manegerMainFrm;
	public LecturerController lecturer_Ctrl;
	private ArrayList<entities.Class> arrayClass;

	private ChatClient client;

	private ClassPack ClassMsg;

	public ManagerController(Main_Frame mainFrm, ChatClient client) {
		this.client = client;
		manegerMainFrm = mainFrm;
		main = new Main_Menu(NOT_MAGI, this);
		manegerMainFrm.add(main.PNL_Main);
	}

	public void handleManagerGUI(int operation) {
		switch (operation) {
		case EXIT:// logout

			break;

		}
	}

	public void BacktoMainMenu(JPanel Panel2Close) {
		manegerMainFrm.remove(Panel2Close);
		manegerMainFrm.add(main.PNL_Main);
		manegerMainFrm.repaint();
	}

	public void Load_Lecturer_Preferences(JPanel Panel2Close) {
		manegerMainFrm.remove(Panel2Close);
		LP = new Lecturer_Preferences(null, this);
		manegerMainFrm.add(LP.PNL_Main);
		// lecturer_Ctrl = new LecturerController(this);
		manegerMainFrm.repaint();
	}

	public void Load_Automatic_Sheduling(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		AS = new Automatic_Sheduling(this);
		manegerMainFrm.add(AS.PNL_Main);
		manegerMainFrm.repaint();
	}

	public void GetClasses() {

		 arrayClass = new ArrayList<entities.Class>();
		ClassMsg = new ClassPack();
		client.handleMessageFromClientUI(ClassMsg);
		ClassMsg = (ClassPack) client.getMessage();
		arrayClass=ClassMsg.getAllclass();
	}

	public void Load_Edit_Class(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		ECLSS = new Edit_Class(this);
	//	GetClasses();
		//ECLSS.setClassStudyAids(arrayClass);
		
		
		
		manegerMainFrm.add(ECLSS.PNL_Main);
		manegerMainFrm.repaint();
	}

	public void Load_Edit_Course(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		ECRS = new Edit_Course(this);

		manegerMainFrm.add(ECRS.PNL_Main);
		manegerMainFrm.repaint();
	}

	public void Load_Course_Settings(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		// /load all we need

		CS = new Course_Settings(this);
		
		manegerMainFrm.add(CS.PNL_Main);
		manegerMainFrm.repaint();
	}

	public void Load_Edit_Lecturer(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		EL = new Edit_Lecturer(this);
		manegerMainFrm.add(EL.PNL_Main);
		manegerMainFrm.repaint();
	}

	public void Load_Manual_Sheduling(JPanel Panel2Close) {

		manegerMainFrm.remove(Panel2Close);
		MS = new Manual_Sheduling(this);
		manegerMainFrm.add(MS.PNL_Main);
		manegerMainFrm.repaint();
	}

}
