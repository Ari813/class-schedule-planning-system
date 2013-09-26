package Controllers;

import GUI.Lecturer_Preferences;



public class LecturerController {

	ManagerController managerController;
	private Lecturer_Preferences lec_Pref_panel;	
	public LecturerController(ManagerController managerController) {
	
		this.managerController = managerController;
		lec_Pref_panel = new Lecturer_Preferences(null, managerController);
		managerController.manegerMainFrm.add(lec_Pref_panel.PNL_Main);
	}

}
