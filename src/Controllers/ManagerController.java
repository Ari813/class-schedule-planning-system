package Controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;




import GUI.Edit_Course;
import GUI.Main_Frame;
import GUI.Main_Menu;

public class ManagerController {
	final public static int EXIT = 1; 
	final public static int MAIN = 2; 
	 
	
	public Edit_Course ed ;
	private Main_Menu main;
	public Main_Frame manegerMainFrm;
	
	public ManagerController(Main_Frame mainFrm){
		
		manegerMainFrm=mainFrm;
		main=new Main_Menu();
		manegerMainFrm.add(main.PNL_Main);
		
		
		
		
		
	}	
}

