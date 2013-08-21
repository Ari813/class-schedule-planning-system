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


public class Edit_Course extends JPanel {

	

	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;
	private JTextField txtIdNumber;
	private JTextField txtCoursName;
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
		
		JLabel lblEditCourse = new JLabel("Edit course:");
		lblEditCourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEditCourse.setBounds(10, 11, 115, 14);
		PNL_Main.add(lblEditCourse);
		
		JComboBox cmbBxEditCouse = new JComboBox();
		cmbBxEditCouse.setBounds(58, 36, 506, 20);
		PNL_Main.add(cmbBxEditCouse);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(10, 80, 50, 20);
		PNL_Main.add(lblId);
		
		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaculty.setBounds(10, 192, 88, 22);
		PNL_Main.add(lblFaculty);
		
		JLabel lblCoursName = new JLabel("Cours Name:");
		lblCoursName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCoursName.setBounds(10, 130, 125, 20);
		PNL_Main.add(lblCoursName);
		
		txtIdNumber = new JTextField();
		txtIdNumber.setEnabled(false);
		txtIdNumber.setEditable(false);
		txtIdNumber.setText("ID Number");
		txtIdNumber.setBounds(20, 105, 105, 20);
		PNL_Main.add(txtIdNumber);
		txtIdNumber.setColumns(10);
		
		txtCoursName = new JTextField();
		txtCoursName.setEnabled(false);
		txtCoursName.setText("cours name");
		txtCoursName.setBounds(20, 155, 105, 20);
		PNL_Main.add(txtCoursName);
		txtCoursName.setColumns(10);
		
		JComboBox cmBxFaculty = new JComboBox();
		cmBxFaculty.setBounds(20, 225, 105, 21);
		PNL_Main.add(cmBxFaculty);
		
		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSemester.setBounds(10, 275, 105, 20);
		PNL_Main.add(lblSemester);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		spinner.setBounds(30, 301, 29, 20);
		PNL_Main.add(spinner);
		
		JLabel lblAcademicHaurs = new JLabel("Academic  haurs:");
		lblAcademicHaurs.setVerticalAlignment(SwingConstants.TOP);
		lblAcademicHaurs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAcademicHaurs.setBounds(10, 332, 154, 29);
		PNL_Main.add(lblAcademicHaurs);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 8, 1));
		spinner_1.setBounds(30, 363, 29, 20);
		PNL_Main.add(spinner_1);
		
		JLabel lblAvailableLecturers = new JLabel("Available lecturers:");
		lblAvailableLecturers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAvailableLecturers.setBounds(151, 80, 154, 14);
		PNL_Main.add(lblAvailableLecturers);
		
		JList lstlecturers = new JList();
		lstlecturers.setBounds(146, 114, 115, 209);
		PNL_Main.add(lstlecturers);
		
		JButton btnAdd = new JButton("Add -->");
		btnAdd.setBounds(271, 148, 105, 23);
		PNL_Main.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove <--");
		btnRemove.setBounds(271, 189, 105, 23);
		PNL_Main.add(btnRemove);
		
		JList list = new JList();
		list.setBounds(378, 114, 115, 209);
		PNL_Main.add(list);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(557, 102, 207, 229);
		PNL_Main.add(tabbedPane);
		
		JPanel panel_Lecturer = new JPanel();
		tabbedPane.addTab("Lecturer", null, panel_Lecturer, null);
		panel_Lecturer.setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("Enable");
		checkBox.setBounds(6, 7, 57, 23);
		panel_Lecturer.add(checkBox);
		
		JLabel label = new JLabel("Academic hours:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(6, 33, 93, 14);
		panel_Lecturer.add(label);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(147, 33, 29, 14);
		panel_Lecturer.add(spinner_2);
		
		JLabel label_1 = new JLabel("Max student per class:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(6, 58, 142, 14);
		panel_Lecturer.add(label_1);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(147, 58, 29, 14);
		panel_Lecturer.add(spinner_3);
		
		JLabel label_2 = new JLabel("study aids:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(6, 83, 75, 14);
		panel_Lecturer.add(label_2);
		
		JList list_2 = new JList();
		list_2.setBounds(6, 108, 150, 69);
		panel_Lecturer.add(list_2);
		
		JPanel panel_Practice = new JPanel();
		tabbedPane.addTab("Practice", null, panel_Practice, null);
		panel_Practice.setLayout(null);
		
		JCheckBox chckbxEnable = new JCheckBox("Enable");
		chckbxEnable.setBounds(6, 7, 57, 23);
		panel_Practice.add(chckbxEnable);
		
		JLabel lblAcademicHours = new JLabel("Academic hours:");
		lblAcademicHours.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAcademicHours.setBounds(6, 33, 93, 14);
		panel_Practice.add(lblAcademicHours);
		
		JSpinner spinner_AH = new JSpinner();
		spinner_AH.setBounds(147, 33, 29, 14);
		panel_Practice.add(spinner_AH);
		
		JLabel lblMaxStudentPer = new JLabel("Max student per class:");
		lblMaxStudentPer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaxStudentPer.setBounds(6, 58, 142, 14);
		panel_Practice.add(lblMaxStudentPer);
		
		JSpinner spinner_max_st = new JSpinner();
		spinner_max_st.setBounds(147, 58, 29, 14);
		panel_Practice.add(spinner_max_st);
		
		JLabel lblStudyAids = new JLabel("study aids:");
		lblStudyAids.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStudyAids.setBounds(6, 83, 75, 14);
		panel_Practice.add(lblStudyAids);
		
		JList list_1 = new JList();
		list_1.setBounds(6, 108, 150, 69);
		panel_Practice.add(list_1);
		
		JPanel panel_Lab = new JPanel();
		tabbedPane.addTab("Lab", null, panel_Lab, null);
		panel_Lab.setLayout(null);
		
		JCheckBox checkBox_1 = new JCheckBox("Enable");
		checkBox_1.setBounds(6, 7, 57, 23);
		panel_Lab.add(checkBox_1);
		
		JLabel label_3 = new JLabel("Academic hours:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(6, 33, 93, 14);
		panel_Lab.add(label_3);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBounds(147, 33, 29, 14);
		panel_Lab.add(spinner_4);
		
		JLabel label_4 = new JLabel("Max student per class:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(6, 58, 142, 14);
		panel_Lab.add(label_4);
		
		JSpinner spinner_5 = new JSpinner();
		spinner_5.setBounds(147, 58, 29, 14);
		panel_Lab.add(spinner_5);
		
		JLabel label_5 = new JLabel("study aids:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(6, 83, 75, 14);
		panel_Lab.add(label_5);
		
		JList list_3 = new JList();
		list_3.setBounds(6, 108, 150, 69);
		panel_Lab.add(list_3);
		
		JButton btnNewCourse = new JButton("New Course");
		btnNewCourse.setBounds(237, 413, 115, 23);
		PNL_Main.add(btnNewCourse);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSave.setBounds(369, 413, 89, 23);
		PNL_Main.add(btnSave);
		
	}
}
