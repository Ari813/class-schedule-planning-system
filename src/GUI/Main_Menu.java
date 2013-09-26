package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.LecturerController;
import Controllers.ManagerController;

public class Main_Menu extends JPanel implements ActionListener,
ListSelectionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMainMenu;
	private JButton btnLecturerPreferences;
	private JButton btnQuit;
	private JButton btnCourseSettings; 
	private JButton btnSettings;
	private JButton btnStartSchdualing;
	private JButton btnEditCourses;
	private JButton btnEditClasses;
	private JButton btnEditLecturers;
	private JFrame mainfrm;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private JLabel lblDatabaseManagment;
	private JLabel lblSemesterManagment;
	public JPanel PNL_Main ;
	private LecturerController lec;
	private ManagerController manager;
	
	/**
	 * Create the panel.
	 */
	public Main_Menu(LecturerController lec,ManagerController mng) {
		
	
		super();
		this.lec=lec;
		this.manager=mng;
		initialize();}
	
	
		private void initialize() {
		 pnl();
		
		
		PNL_Main.add(GETtxtMainMenu());
		PNL_Main.add(GETbtnLecturerPreferences());
		
		
		PNL_Main.add(GETbtnCourseSettings());
		
		PNL_Main.add(GETbtnEditCourses());
		PNL_Main.add(GETbtnEditClasses());
		
		PNL_Main.add(GETbtnEditLecturers());
		
		PNL_Main.add(GETlblDatabaseManagment());
		PNL_Main.add(GETlblSemesterManagment());
		
		
		PNL_Main.add(GETbtnQuit());
		
		horizontalStrut();
		if (manager!=null){
		PNL_Main.add(GETbtnSettings());
		PNL_Main.add(GETbtnStartSchdualing());
		}
		PNL_Main.setVisible(true);
	
	}
private JButton GETbtnQuit() {
	btnQuit = new JButton("Quit");
	btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnQuit.setBounds(593, 435, 171, 31);
	btnQuit.addActionListener(this);
		return btnQuit;
	}
private JLabel GETlblSemesterManagment() {
	lblSemesterManagment = new JLabel("Semester Managment");
	lblSemesterManagment.setHorizontalAlignment(SwingConstants.CENTER);
	lblSemesterManagment.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblSemesterManagment.setBounds(10, 70, 350, 14);
		return lblSemesterManagment;
	}
private JLabel GETlblDatabaseManagment() {
	lblDatabaseManagment = new JLabel("Database Managment");
	lblDatabaseManagment.setHorizontalAlignment(SwingConstants.CENTER);
	lblDatabaseManagment.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblDatabaseManagment.setBounds(414, 66, 350, 22);
		return lblDatabaseManagment;
	}
private void horizontalStrut() {
	horizontalStrut = Box.createHorizontalStrut(20);
	horizontalStrut.setBounds(10, 80, 350, 22);
	PNL_Main.add(horizontalStrut);
	horizontalStrut_1 = Box.createHorizontalStrut(20);
	horizontalStrut_1.setBounds(414, 80, 350, 22);
	PNL_Main.add(horizontalStrut_1);
		
	}
private JButton GETbtnEditLecturers() {
	btnEditLecturers = new JButton("Edit Lecturers");
	btnEditLecturers.setBounds(414, 191, 350, 31);
	btnEditLecturers.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnEditLecturers.addActionListener(this);
		return btnEditLecturers;
	}
private JButton GETbtnEditClasses() {
	btnEditClasses = new JButton("Edit Classes");
	btnEditClasses.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnEditClasses.setBounds(414, 145, 350, 31);
	btnEditClasses.addActionListener(this);
		return btnEditClasses;
	}
private JButton GETbtnEditCourses() {
	btnEditCourses = new JButton("Edit Courses");
	btnEditCourses.setBounds(414, 99, 350, 31);
	btnEditCourses.addActionListener(this);
	btnEditCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		return btnEditCourses;
	}
private JButton GETbtnStartSchdualing() {
	btnStartSchdualing = new JButton("Start Schdualing");
	btnStartSchdualing.setBounds(10, 191, 350, 31);
	btnStartSchdualing.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnStartSchdualing.addActionListener(this);
		return btnStartSchdualing;
	}
private JButton GETbtnSettings() {
	btnSettings = new JButton("Settings");
	btnSettings.setBounds(10, 432, 350, 31);
	btnSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnSettings.addActionListener(this);
		return btnSettings;
	}
private JButton GETbtnCourseSettings() {
	btnCourseSettings = new JButton("Course Settings");
	btnCourseSettings.setBounds(10, 145, 350, 31);
	btnCourseSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnCourseSettings.addActionListener(this);
		return btnCourseSettings;
	}
private JButton GETbtnLecturerPreferences() {
	btnLecturerPreferences = new JButton("Lecturer Preferences");
	btnLecturerPreferences.setBounds(10, 99, 350, 31);
	btnLecturerPreferences.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnLecturerPreferences.addActionListener(this);
		return btnLecturerPreferences;
	}
private JTextField GETtxtMainMenu() {
	txtMainMenu = new JTextField();
	txtMainMenu.setBounds(10, 11, 754, 31);
	txtMainMenu.setBackground(new Color(176, 224, 230));
	txtMainMenu.setBorder(UIManager.getBorder("DesktopIcon.border"));
	txtMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
	txtMainMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
	txtMainMenu.setText("Main Menu");
		return txtMainMenu;
	}
private void pnl() {
	PNL_Main = new JPanel();	
	PNL_Main.setPreferredSize(new Dimension(774, 474));
	PNL_Main.setName("PNL_MainMenu");
	PNL_Main.setMaximumSize(new Dimension(774, 474));
	PNL_Main.setMinimumSize(new Dimension(774, 474));
	PNL_Main.setBounds(10, 85, 774, 474);
	PNL_Main.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	PNL_Main.setLayout(null);
		
	}
	
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLecturerPreferences) {
				//mainfrm.remove(this);
				//lecturer
				
			}
			if (e.getSource() == btnQuit) {}
			if (e.getSource() == btnCourseSettings) {}
			if (e.getSource() == btnSettings) {}
			if (e.getSource() == btnStartSchdualing) {}
			
			
			if (e.getSource() == btnEditCourses) {}
			if (e.getSource() == btnEditClasses) {}
			if (e.getSource() == btnEditLecturers) {}
				
				
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		

	
}
