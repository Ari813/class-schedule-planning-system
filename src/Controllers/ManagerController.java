package Controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Edit_Course;
import GUI.Main_Frame;
import GUI.Main_Menu;

public class ManagerController {
	final public static int EXIT = 1;
	final public static int MAIN = 2;
	final public static int AUTO = 3;
	final public static int MAMUAL = 4;
	final public static int LECTURERPREFER = 5;
	final public static int COURSSETTING = 6;
	final public static int EDITCOURESES = 7;
	final public static int EDITCLASS = 8;
	final public static int EDITLECTURER = 9;
	final public static int SETTING = 10;

	public Edit_Course ed;
	private Main_Menu main;
	public Main_Frame manegerMainFrm;
	public LecturerController lecturer_Ctrl;
	
	public ManagerController(Main_Frame mainFrm) {

		manegerMainFrm = mainFrm;
		main = new Main_Menu(null, this);
		manegerMainFrm.add(main.PNL_Main);

	}

	public void handleManagerGUI(int operation) {
		switch (operation) {
		case EXIT:// logout

			break;

		}
	}
	
	public void BacktoMainMenu(JPanel Panel2Close)
	{
		manegerMainFrm.remove(Panel2Close);
		manegerMainFrm.add(main);
		manegerMainFrm.repaint();
	}

	public void Load_Lecturer_Preferences(JPanel Panel2Close) {
		manegerMainFrm.remove(Panel2Close);
		lecturer_Ctrl = new LecturerController(this);
		manegerMainFrm.repaint();		
	}
}
