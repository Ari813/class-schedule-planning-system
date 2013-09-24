package Controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;




import GUI.Edit_Course;
import GUI.Main_Frame;
import GUI.Main_Menu;

public class ManagerController {
	final public static int EXIT = 1; 
	final public static int MAIN = 2; 
	 
	public JPanel PNL_Exhibit;
	public Edit_Course ed ;
	private Main_Menu main;
	public Main_Frame manegerMainFrm;
	
	public ManagerController(Main_Frame mainFrm){
		PNL_Exhibit = new JPanel();
		manegerMainFrm=mainFrm;
		 ed = new Edit_Course();
		main=new Main_Menu();
		PNL_Exhibit =main;
		manegerMainFrm.add(main.PNL_Main);
		
		
		
	}	
}

