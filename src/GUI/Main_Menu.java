package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class Main_Menu extends JPanel {
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
//	private JButton ;
	
	/**
	 * Create the panel.
	 */
	public Main_Menu(JFrame frmLec) {
		mainfrm=frmLec;
		frmLec.add(this);
		setPreferredSize(new Dimension(774, 474));
		setName("PNL_MainMenu");
		setMaximumSize(new Dimension(774, 474));
		setMinimumSize(new Dimension(774, 474));
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		txtMainMenu = new JTextField();
		txtMainMenu.setBounds(10, 11, 754, 31);
		txtMainMenu.setBackground(new Color(176, 224, 230));
		txtMainMenu.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		txtMainMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtMainMenu.setText("Main Menu");
		add(txtMainMenu);
		txtMainMenu.setColumns(10);
		
		btnLecturerPreferences = new JButton("Lecturer Preferences");
		btnLecturerPreferences.setBounds(10, 99, 350, 31);
		btnLecturerPreferences.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(btnLecturerPreferences);
		
		btnCourseSettings = new JButton("Course Settings");
		btnCourseSettings.setBounds(10, 145, 350, 31);
		btnCourseSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(btnCourseSettings);
		
		btnSettings = new JButton("Settings");
		btnSettings.setBounds(10, 432, 350, 31);
		btnSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(btnSettings);
		
		btnStartSchdualing = new JButton("Start Schdualing");
		btnStartSchdualing.setBounds(10, 191, 350, 31);
		btnStartSchdualing.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(btnStartSchdualing);
		
		btnEditCourses = new JButton("Edit Courses");
		btnEditCourses.setBounds(414, 99, 350, 31);
		add(btnEditCourses);
		btnEditCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnEditClasses = new JButton("Edit Classes");
		btnEditClasses.setBounds(414, 145, 350, 31);
		add(btnEditClasses);
		btnEditClasses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnEditLecturers = new JButton("Edit Lecturers");
		btnEditLecturers.setBounds(414, 191, 350, 31);
		add(btnEditLecturers);
		btnEditLecturers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(10, 80, 350, 22);
		add(horizontalStrut);
		
		JLabel lblDatabaseManagment = new JLabel("Database Managment");
		lblDatabaseManagment.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseManagment.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDatabaseManagment.setBounds(414, 66, 350, 22);
		add(lblDatabaseManagment);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(414, 80, 350, 22);
		add(horizontalStrut_1);
		
		JLabel lblSemesterManagment = new JLabel("Semester Managment");
		lblSemesterManagment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemesterManagment.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSemesterManagment.setBounds(10, 70, 350, 14);
		add(lblSemesterManagment);
		
		btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuit.setBounds(593, 435, 171, 31);
		add(btnQuit);
		setVisible(true);
	
	}
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLecturerPreferences) {
				mainfrm.remove(this);
				
			}
			if (e.getSource() == btnQuit) {}
			if (e.getSource() == btnCourseSettings) {}
			if (e.getSource() == btnSettings) {}
			if (e.getSource() == btnStartSchdualing) {}
			
			
			if (e.getSource() == btnEditCourses) {}
			if (e.getSource() == btnEditClasses) {}
			if (e.getSource() == btnEditLecturers) {}
				
				
			
		}
		
		

	
}
