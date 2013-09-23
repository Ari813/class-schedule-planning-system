package GUI;


import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Component;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.util.Locale;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.Box;


public class Edit_Course extends JPanel {

	

	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;
	private JTextField txtIdNumber;
	private JTextField txtCoursName;
	private JTextField txtCourseEditor;
	private JLabel lblMainAcademicHours;
	
	private JComboBox cmbBxEditCouse;
	private JComboBox cmBxFaculty;
	private JSpinner spinner;
	private JList lstlecturers;
	private JButton btnAdd ;
	private JButton btnRemove;
	private JList list;
	private JTabbedPane tabbedPane;
	private JCheckBox checkBox;
	private JSpinner spinner_2;
	private JSpinner spinner_3;
	private JList list_2 ;
	private JCheckBox checkBox_2;
	private JSpinner spinner_1;
	private JSpinner spinner_6;
	private JLabel label_8;
	private JList list_1;
	private JCheckBox checkBox_1 ;
	private JSpinner spinner_4;
	private JSpinner spinner_5 ;
	private JList list_3;
	private JButton btnNewCourse;
	private JButton btnSave;
	private JButton btnDiscard;
	
	/**
	 * Create the panel.
	 */
	public Edit_Course() {
		JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
		lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeTableSchedualing.setLocation(new Point(50, 0));
		lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
		lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	
		JPanel PNL_Main = new JPanel();
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
	
		PNL_Main.setLayout(null);
		
		cmbBxEditCouse = new JComboBox();
		cmbBxEditCouse.setBounds(10, 53, 754, 20);
		PNL_Main.add(cmbBxEditCouse);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(10, 114, 50, 20);
		PNL_Main.add(lblId);
		
		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaculty.setBounds(10, 244, 88, 22);
		PNL_Main.add(lblFaculty);
		
		JLabel lblCoursName = new JLabel("Cours Name:");
		lblCoursName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCoursName.setBounds(10, 179, 125, 20);
		PNL_Main.add(lblCoursName);
		
		txtIdNumber = new JTextField();
		txtIdNumber.setEnabled(false);
		txtIdNumber.setEditable(false);
		txtIdNumber.setText("ID Number");
		txtIdNumber.setBounds(10, 144, 105, 20);
		PNL_Main.add(txtIdNumber);
		txtIdNumber.setColumns(10);
		
		txtCoursName = new JTextField();
		txtCoursName.setEnabled(false);
		txtCoursName.setText("cours name");
		txtCoursName.setBounds(10, 207, 105, 20);
		PNL_Main.add(txtCoursName);
		txtCoursName.setColumns(10);
		
		
		 cmBxFaculty = new JComboBox();
		cmBxFaculty.setBounds(10, 272, 105, 21);
		PNL_Main.add(cmBxFaculty);
		
		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSemester.setBounds(10, 354, 105, 29);
		PNL_Main.add(lblSemester);
		
		
		 spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		spinner.setBounds(20, 393, 50, 20);
		PNL_Main.add(spinner);
		
		JLabel lblAcademicHours;
		lblMainAcademicHours = new JLabel("Academic  hours:");
		lblMainAcademicHours.setHorizontalAlignment(SwingConstants.LEFT);
		lblMainAcademicHours.setVerticalAlignment(SwingConstants.TOP);
		lblMainAcademicHours.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMainAcademicHours.setBounds(151, 357, 154, 29);
		PNL_Main.add(lblMainAcademicHours);
		
		JLabel lblAvailableLecturers = new JLabel("Available lecturers:");
		lblAvailableLecturers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAvailableLecturers.setBounds(151, 105, 154, 14);
		PNL_Main.add(lblAvailableLecturers);
		
		 lstlecturers = new JList();
		lstlecturers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstlecturers.setBounds(145, 137, 115, 209);
		PNL_Main.add(lstlecturers);
		
		 btnAdd = new JButton("-->");
		btnAdd.setToolTipText("Add lecturer to course");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(292, 171, 61, 38);
		PNL_Main.add(btnAdd);
		
		 
		 btnRemove = new JButton("<--");
		btnRemove.setToolTipText("Remove lecturer from course");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(292, 237, 61, 38);
		PNL_Main.add(btnRemove);
		
		
		 list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 16));
		list.setBounds(377, 137, 115, 209);
		PNL_Main.add(list);
		
		 tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.setBounds(557, 102, 207, 311);
		PNL_Main.add(tabbedPane);
		
		JPanel panel_Lecture = new JPanel();
		tabbedPane.addTab("Lecture", null, panel_Lecture, null);
		panel_Lecture.setLayout(null);
		
		checkBox = new JCheckBox("Enable");
		checkBox.setFont(new Font("Dialog", Font.PLAIN, 16));
		checkBox.setBounds(10, 7, 170, 23);
		panel_Lecture.add(checkBox);
		
		JLabel label = new JLabel("Academic hours:");
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(10, 33, 170, 23);
		panel_Lecture.add(label);
		
		
		 spinner_2 = new JSpinner();
		spinner_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		spinner_2.setBounds(123, 59, 57, 23);
		panel_Lecture.add(spinner_2);
		
		JLabel label_1 = new JLabel("Max student per class:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_1.setBounds(10, 85, 170, 23);
		panel_Lecture.add(label_1);
		
		
		 spinner_3 = new JSpinner();
		spinner_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		spinner_3.setBounds(123, 111, 57, 23);
		panel_Lecture.add(spinner_3);
		
		JLabel label_2 = new JLabel("study aids:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_2.setBounds(10, 137, 170, 23);
		panel_Lecture.add(label_2);
		
		 list_2 = new JList();
		list_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		list_2.setBounds(10, 163, 178, 89);
		panel_Lecture.add(list_2);
		
		JPanel panel_Practice = new JPanel();
		tabbedPane.addTab("Practice", null, panel_Practice, null);
		panel_Practice.setLayout(null);
		
		 checkBox_2 = new JCheckBox("Enable");
		checkBox_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		checkBox_2.setBounds(10, 7, 170, 23);
		panel_Practice.add(checkBox_2);
		
		JLabel label_6 = new JLabel("Academic hours:");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_6.setBounds(10, 33, 170, 23);
		panel_Practice.add(label_6);
		
		 spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		spinner_1.setBounds(123, 59, 57, 23);
		panel_Practice.add(spinner_1);
		
		JLabel label_7 = new JLabel("Max student per class:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(10, 85, 170, 23);
		panel_Practice.add(label_7);
		
		
		spinner_6 = new JSpinner();
		spinner_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		spinner_6.setBounds(123, 111, 57, 23);
		panel_Practice.add(spinner_6);
		
		 label_8 = new JLabel("study aids:");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_8.setBounds(10, 137, 170, 23);
		panel_Practice.add(label_8);
		
		
		list_1 = new JList();
		list_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		list_1.setBounds(10, 163, 178, 89);
		panel_Practice.add(list_1);
		
		JPanel panel_Lab = new JPanel();
		tabbedPane.addTab("Lab", null, panel_Lab, null);
		panel_Lab.setLayout(null);
		
		checkBox_1 = new JCheckBox("Enable");
		checkBox_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		checkBox_1.setBounds(10, 7, 170, 23);
		panel_Lab.add(checkBox_1);
		
		JLabel label_3 = new JLabel("Academic hours:");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_3.setBounds(10, 33, 170, 23);
		panel_Lab.add(label_3);
		
		 spinner_4 = new JSpinner();
		spinner_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		spinner_4.setBounds(123, 59, 57, 23);
		panel_Lab.add(spinner_4);
		
		JLabel label_4 = new JLabel("Max student per class:");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_4.setBounds(10, 85, 170, 23);
		panel_Lab.add(label_4);
		
		 spinner_5 = new JSpinner();
		spinner_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		spinner_5.setBounds(123, 111, 57, 23);
		panel_Lab.add(spinner_5);
		
		JLabel label_5 = new JLabel("study aids:");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_5.setBounds(10, 137, 170, 23);
		panel_Lab.add(label_5);
		
		 list_3 = new JList();
		list_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		list_3.setBounds(10, 163, 178, 89);
		panel_Lab.add(list_3);
		
		btnNewCourse = new JButton("New Course");
		btnNewCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewCourse.setBounds(111, 440, 140, 23);
		PNL_Main.add(btnNewCourse);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSave.setBounds(304, 440, 140, 23);
		PNL_Main.add(btnSave);
		
		txtCourseEditor = new JTextField();
		txtCourseEditor.setText("Course editor");
		txtCourseEditor.setHorizontalAlignment(SwingConstants.CENTER);
		txtCourseEditor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCourseEditor.setColumns(10);
		txtCourseEditor.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtCourseEditor.setBackground(new Color(176, 224, 230));
		txtCourseEditor.setBounds(10, 11, 754, 31);
		PNL_Main.add(txtCourseEditor);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBackground(Color.BLACK);
		horizontalStrut.setBounds(0, 75, 774, 5);
		PNL_Main.add(horizontalStrut);
		
		 btnDiscard = new JButton("Discard");
		btnDiscard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDiscard.setBounds(497, 440, 140, 23);
		PNL_Main.add(btnDiscard);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(5, 424, 759, 5);
		PNL_Main.add(horizontalStrut_1);
		
		JTextPane textAcademicHours = new JTextPane();
		textAcademicHours.setEditable(false);
		textAcademicHours.setBackground(Color.LIGHT_GRAY);
		textAcademicHours.setText("0");
		textAcademicHours.setBounds(161, 393, 50, 20);
		PNL_Main.add(textAcademicHours);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(145, 122, 347, 14);
		PNL_Main.add(horizontalStrut_2);
		
	}
	public void actionPerformed(ActionEvent e) {}
	
}
