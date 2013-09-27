package Controllers;

import GUI.Lecturer_Preferences;
import GUI.Main_Frame;



public class LecturerController {

	//ManagerController managerController;
	private Lecturer_Preferences lec_Pref_panel;	
	public LecturerController(Main_Frame mainFrm) {
	
		//this.LecturerController = managerController;
		lec_Pref_panel = new Lecturer_Preferences(this, null);
		mainFrm.add(lec_Pref_panel.PNL_Main);
	}

}
