package GUI;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.Canvas;

public class Main_Menu extends JPanel {
	private JTextField txtMainMenu;

	/**
	 * Create the panel.
	 */
	public Main_Menu() {
		setPreferredSize(new Dimension(774, 474));
		setName("PNL_MainMenu");
		setMaximumSize(new Dimension(774, 474));
		setMinimumSize(new Dimension(774, 474));
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		txtMainMenu = new JTextField();
		txtMainMenu.setBackground(new Color(176, 224, 230));
		txtMainMenu.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		txtMainMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtMainMenu.setText("Main Menu");
		txtMainMenu.setBounds(10, 11, 754, 31);
		add(txtMainMenu);
		txtMainMenu.setColumns(10);
		
		JButton btnLecturerPreferences = new JButton("Lecturer Preferences");
		btnLecturerPreferences.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLecturerPreferences.setBounds(10, 80, 210, 40);
		add(btnLecturerPreferences);
		
		JButton btnCourseSettings = new JButton("Course Settings");
		btnCourseSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCourseSettings.setBounds(10, 140, 210, 40);
		add(btnCourseSettings);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSettings.setBounds(10, 372, 210, 40);
		add(btnSettings);
		
		JButton btnStartSchdualing = new JButton("Start Schdualing");
		btnStartSchdualing.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStartSchdualing.setBounds(10, 423, 210, 40);
		add(btnStartSchdualing);
		
		JButton btnEditLecturers = new JButton("Edit Lecturers");
		btnEditLecturers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEditLecturers.setBounds(554, 80, 210, 40);
		add(btnEditLecturers);
		
		JButton btnEditCourses = new JButton("Edit Courses");
		btnEditCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEditCourses.setBounds(554, 140, 210, 40);
		add(btnEditCourses);
		
		JButton btnEditClasses = new JButton("Edit Classes");
		btnEditClasses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEditClasses.setBounds(554, 200, 210, 40);
		add(btnEditClasses);

	}
}
