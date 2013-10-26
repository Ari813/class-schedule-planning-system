package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.Lecturer;
import entities.StudyAids;
import Controllers.ManagerController;

import java.awt.SystemColor;
import java.util.Map;

public class Edit_Course extends JPanel implements ActionListener,
ListSelectionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;
	private JTextField txtIdNumber;
	private JTextField txtCoursName;
	private JTextField txtCourseEditor;
	private JLabel lblMainAcademicHours;

	private JComboBox<?> cmbBxEditCouse;
	private JComboBox<?> CB_Faculty;
	private JSpinner Course_Semester;
	private JList<?> lstAvailableLecturers;
	private JList<?> lstChoosenLecturers;
	private JTabbedPane tabbedPane;
	private JButton btnNewCourse;
	private JButton btnSave;
	private JButton btnDiscard;
	private JButton btnAdd;
	private JButton btnRemove;
	public JPanel PNL_Main;

	private JCheckBox ChkEnableLecture;
	private JSpinner Lec_AcademicHours;
	private JSpinner Lec_MaxStdntPerClass;
	private JList<?> Lec_StdyAids;

	private JCheckBox ChkEnablePractice;
	private JSpinner Practice_AcademicHours;
	private JSpinner Practice_MaxStdntPerClass;
	private JList<?> Practice_StudyAids;

	private JLabel label_8;

	private JCheckBox ChkEnableLab;
	private JSpinner Lab_AcademicHours;
	private JSpinner Lab_MaxStdntPerClass;
	private JList<?> Lab_StudyAids;
	private ManagerController manager;

	/*		Lecturers Data		*/
	private Map<Integer, Lecturer> ArrayAvailableLecturers;
	private Map<Integer, Integer> arraySelectedLecturers;
	private Map<Integer, StudyAids> ArrayLecturers;
	
	/*		general StudyAids	*/
	private Map<Integer, StudyAids> LecStudyAids;
	/*		Lecture	StudyAids	*/
	private Map<Integer, Integer> LecAvailableStudyAids;
	private Map<Integer, Integer> LecSelectedStudyAids;
	
	/*		Practice StudyAids	*/
	private Map<Integer, Integer> PracticeAvailableStudyAids;
	private Map<Integer, Integer> PracticeSelectedStudyAids;
	
	/*		Lab StudyAids		*/
	private Map<Integer, Integer> LabAvailableStudyAids;
	private Map<Integer, Integer> LabSelectedStudyAids;
	
	
	

	/**
	 * Create the panel.
	 */
	/*/JLabel lblTimeTableSchedualing = new JLabel(
	"Time Table Schedualing System");
lblTimeTableSchedualing
	.setHorizontalTextPosition(SwingConstants.CENTER);
lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
lblTimeTableSchedualing.setLocation(new Point(50, 0));
lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED,
	null, null, null, null));
/*/
	public Edit_Course(ManagerController mng) {
	
		super();
		this.manager=mng;
		initialize();}
	
	
		private void initialize() {
	
		
	
		pnl();

		PNL_Main.add(getcmbBxEditCouse());

		PNL_Main.add(getlblId());

		PNL_Main.add(getlblFaculty());

		PNL_Main.add(getlblCoursName());

		PNL_Main.add(gettxtIdNumber());

		PNL_Main.add(gettxtCoursName());

		PNL_Main.add(getCB_Faculty());

		PNL_Main.add(getlblSemester());

		PNL_Main.add(getCourse_Semester());

		PNL_Main.add(getlblMainAcademicHours());

		PNL_Main.add(getlblAvailableLecturers());

		PNL_Main.add(getlstAvailableLecturers());

		PNL_Main.add(getbtnAdd());

		PNL_Main.add(getbtnRemove());

		PNL_Main.add(getlstChoosenLecturers());

		PNL_Main.add(gettabbedPane());

		PNL_Main.add(getbtnNewCourse());

		PNL_Main.add(getbtnSave());

		PNL_Main.add(gettxtCourseEditor());

		PNL_Main.add(gethorizontalStrut());

		PNL_Main.add(getbtnDiscard());

		PNL_Main.add(gethorizontalStrut_1());

		PNL_Main.add(gettextAcademicHours());

		PNL_Main.add(gethorizontalStrut_2());

	}

	private void pnl() {
		PNL_Main = new JPanel();
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);

		PNL_Main.setLayout(null);
			
		}


	private Component gethorizontalStrut_2() {
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(145, 122, 347, 14);
		return horizontalStrut_2;
	}

	private JTextPane gettextAcademicHours() {
		JTextPane textAcademicHours = new JTextPane();
		textAcademicHours.setEditable(false);
		textAcademicHours.setBackground(Color.LIGHT_GRAY);
		textAcademicHours.setText("0");
		textAcademicHours.setBounds(161, 393, 50, 20);
		return textAcademicHours;
	}

	private Component gethorizontalStrut_1() {
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(5, 424, 759, 5);
		return horizontalStrut_1;
	}

	private JButton getbtnDiscard() {
		btnDiscard = new JButton("Discard");
		btnDiscard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDiscard.setBounds(539, 440, 160, 29);
		btnDiscard.addActionListener(this);
		return btnDiscard;
	}

	private Component gethorizontalStrut() {
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBackground(Color.BLACK);
		horizontalStrut.setBounds(0, 75, 774, 5);
		return horizontalStrut;
	}

	private JTextField gettxtCourseEditor() {
		txtCourseEditor = new JTextField();
		txtCourseEditor.setEditable(false);
		txtCourseEditor.setText("Course editor");
		txtCourseEditor.setHorizontalAlignment(SwingConstants.CENTER);
		txtCourseEditor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCourseEditor.setColumns(10);
		txtCourseEditor.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtCourseEditor.setBackground(new Color(176, 224, 230));
		txtCourseEditor.setBounds(0, 4, 774, 40);
		return txtCourseEditor;
	}

	private JButton getbtnSave() {
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(306, 440, 160, 29);
		return btnSave;
	}

	private JButton getbtnNewCourse() {
		btnNewCourse = new JButton("New Course");
		btnNewCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewCourse.setBounds(73, 440, 160, 29);
		return btnNewCourse;
	}

	private JList getLab_StudyAids() {
		Lab_StudyAids = new JList<Object>();
		Lab_StudyAids.setBackground(SystemColor.inactiveCaption);
		Lab_StudyAids.setFont(new Font("Dialog", Font.PLAIN, 16));
		Lab_StudyAids.setBounds(10, 163, 178, 89);
		return Lab_StudyAids;
	}

	private JLabel getlabel_5() {
		JLabel label_5 = new JLabel("study aids:");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_5.setBounds(10, 137, 170, 23);
		return label_5;
	}

	private JSpinner getLab_MaxStdntPerClass() {
		Lab_MaxStdntPerClass = new JSpinner();
		Lab_MaxStdntPerClass.setFont(new Font("Dialog", Font.PLAIN, 16));
		Lab_MaxStdntPerClass.setBounds(123, 111, 57, 23);
		return Lab_MaxStdntPerClass;
	}

	private JLabel getlabel_4() {	
		JLabel label_4 = new JLabel("Max student per class:");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_4.setBounds(10, 85, 170, 23);
		return label_4;
	}

	private JSpinner getLab_AcademicHours() {
		Lab_AcademicHours = new JSpinner();
		Lab_AcademicHours.setFont(new Font("Dialog", Font.PLAIN, 16));
		Lab_AcademicHours.setBounds(123, 59, 57, 23);
		return Lab_AcademicHours;
	}

	private JLabel getlabel_3() {
		JLabel label_3 = new JLabel("Academic hours:");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_3.setBounds(10, 33, 170, 23);
		return label_3;
	}

	private JCheckBox getChkEnableLab() {
		ChkEnableLab = new JCheckBox("Enable");
		ChkEnableLab.setBackground(SystemColor.scrollbar);
		ChkEnableLab.setFont(new Font("Dialog", Font.PLAIN, 16));
		ChkEnableLab.setBounds(10, 7, 170, 23);
		return ChkEnableLab;
	}

	private JList getPractice_StudyAids() {
		Practice_StudyAids = new JList<Object>();
		Practice_StudyAids.setBackground(SystemColor.inactiveCaption);
		Practice_StudyAids.setFont(new Font("Dialog", Font.PLAIN, 16));
		Practice_StudyAids.setBounds(10, 163, 178, 89);
		return Practice_StudyAids;
	}

	private JLabel getlabel_8() {
		label_8 = new JLabel("study aids:");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_8.setBounds(10, 137, 170, 23);
		return label_8;
	}

	private JSpinner getPractice_MaxStdntPerClass() {
		Practice_MaxStdntPerClass = new JSpinner();
		Practice_MaxStdntPerClass.setFont(new Font("Dialog", Font.PLAIN, 16));
		Practice_MaxStdntPerClass.setBounds(123, 111, 57, 23);
		return Practice_MaxStdntPerClass;
	}

	private JLabel getlabel_7() {
		JLabel label_7 = new JLabel("Max student per class:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(10, 85, 170, 23);
		return label_7;
	}

	private JSpinner getPractice_AcademicHours() {
		Practice_AcademicHours = new JSpinner();
		Practice_AcademicHours.setFont(new Font("Dialog", Font.PLAIN, 16));
		Practice_AcademicHours.setBounds(123, 59, 57, 23);
		return Practice_AcademicHours;
	}

	private JLabel getlabel_6() {
		JLabel label_6 = new JLabel("Academic hours:");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_6.setBounds(10, 33, 170, 23);
		return label_6;
	}

	private JCheckBox getChkEnablePractice() {
		ChkEnablePractice = new JCheckBox("Enable");
		ChkEnablePractice.setBackground(SystemColor.scrollbar);
		ChkEnablePractice.setFont(new Font("Dialog", Font.PLAIN, 16));
		ChkEnablePractice.setBounds(10, 7, 170, 23);
		return ChkEnablePractice;
	}

	private JList getLec_StdyAids() {
		Lec_StdyAids = new JList<Object>();
		Lec_StdyAids.setBackground(SystemColor.inactiveCaption);
		Lec_StdyAids.setFont(new Font("Dialog", Font.PLAIN, 16));
		Lec_StdyAids.setBounds(10, 163, 178, 89);
		return Lec_StdyAids;
	}

	private JLabel getlabel_2() {
		JLabel label_2 = new JLabel("study aids:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_2.setBounds(10, 137, 170, 23);
		return label_2;
	}

	private JSpinner getLec_MaxStdntPerClass() {
		Lec_MaxStdntPerClass = new JSpinner();
		Lec_MaxStdntPerClass.setFont(new Font("Dialog", Font.PLAIN, 16));
		Lec_MaxStdntPerClass.setBounds(123, 111, 57, 23);
		return Lec_MaxStdntPerClass;
	}

	private JLabel getlabel_1() {
		JLabel label_1 = new JLabel("Max student per class:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_1.setBounds(10, 85, 170, 23);
		return label_1;
	}

	private JSpinner getLec_AcademicHours() {
		Lec_AcademicHours = new JSpinner();
		Lec_AcademicHours.setFont(new Font("Dialog", Font.PLAIN, 16));
		Lec_AcademicHours.setBounds(123, 59, 57, 23);
		return Lec_AcademicHours;
	}

	private JLabel getlabel() {
		JLabel label = new JLabel("Academic hours:");
		label.setBackground(SystemColor.scrollbar);
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(10, 33, 170, 23);
		return label;
	}

	private JCheckBox getChkEnableLecture() {
		ChkEnableLecture = new JCheckBox("Enable");
		ChkEnableLecture.setBackground(SystemColor.scrollbar);
		ChkEnableLecture.setFont(new Font("Dialog", Font.PLAIN, 16));
		ChkEnableLecture.setBounds(10, 7, 170, 23);
		return ChkEnableLecture;
	}

	private JTabbedPane gettabbedPane() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		tabbedPane.setBounds(557, 102, 207, 311);

		JPanel panel_Practice = new JPanel();
		panel_Practice.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("Practice", null, panel_Practice, null);
		panel_Practice.setLayout(null);

		panel_Practice.add(getChkEnablePractice());

		panel_Practice.add(getlabel_6());

		panel_Practice.add(getPractice_AcademicHours());

		panel_Practice.add(getlabel_7());

		panel_Practice.add(getPractice_MaxStdntPerClass());

		panel_Practice.add(getlabel_8());

		panel_Practice.add(getPractice_StudyAids());

		JPanel panel_Lecture = new JPanel();
		panel_Lecture.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("Lecture", null, panel_Lecture, null);
		panel_Lecture.setLayout(null);

		panel_Lecture.add(getChkEnableLecture());

		panel_Lecture.add(getlabel());

		panel_Lecture.add(getLec_AcademicHours());

		panel_Lecture.add(getlabel_1());

		panel_Lecture.add(getLec_MaxStdntPerClass());

		panel_Lecture.add(getlabel_2());

		panel_Lecture.add(getLec_StdyAids());

		JPanel panel_Lab = new JPanel();
		panel_Lab.setBackground(SystemColor.scrollbar);
		tabbedPane.addTab("Lab", null, panel_Lab, null);
		panel_Lab.setLayout(null);

		panel_Lab.add(getChkEnableLab());

		panel_Lab.add(getlabel_3());

		panel_Lab.add(getLab_AcademicHours());

		panel_Lab.add(getlabel_4());

		panel_Lab.add(getLab_MaxStdntPerClass());

		panel_Lab.add(getlabel_5());

		panel_Lab.add(getLab_StudyAids());
		return tabbedPane;
	}

	private JList getlstChoosenLecturers() {
		lstChoosenLecturers = new JList<Object>();
		lstChoosenLecturers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstChoosenLecturers.setBounds(377, 137, 115, 209);
		return lstChoosenLecturers;
	}

	private JButton getbtnRemove() {
		btnRemove = new JButton("<--");
		btnRemove.setToolTipText("Remove lecturer from course");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(292, 237, 65, 38);
		return btnRemove;
	}

	private JButton getbtnAdd() {
		btnAdd = new JButton("-->");
		btnAdd.setToolTipText("Add lecturer to course");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(292, 171, 65, 38);
		return btnAdd;
	}

	private JList getlstAvailableLecturers() {
		lstAvailableLecturers = new JList<Object>();
		lstAvailableLecturers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstAvailableLecturers.setBounds(145, 137, 115, 209);
		return lstAvailableLecturers;
	}

	private JLabel getlblAvailableLecturers() {
		JLabel lblAvailableLecturers = new JLabel("Available lecturers:");
		lblAvailableLecturers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAvailableLecturers.setBounds(151, 105, 154, 14);
		return lblAvailableLecturers;
	}

	private JLabel getlblMainAcademicHours() {
		JLabel lblMainAcademicHours;
		lblMainAcademicHours = new JLabel("Academic  hours:");
		lblMainAcademicHours.setHorizontalAlignment(SwingConstants.LEFT);
		lblMainAcademicHours.setVerticalAlignment(SwingConstants.TOP);
		lblMainAcademicHours.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMainAcademicHours.setBounds(151, 357, 154, 29);
		return lblMainAcademicHours;
	}

	private JSpinner getCourse_Semester() {
		Course_Semester = new JSpinner();
		Course_Semester.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		Course_Semester.setBounds(20, 393, 50, 20);
		return Course_Semester;
	}

	private JLabel getlblSemester() {
		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSemester.setBounds(10, 354, 105, 29);
		return lblSemester;
	}

	private JComboBox getCB_Faculty() {
		CB_Faculty = new JComboBox<Object>();
		CB_Faculty.setBounds(10, 272, 105, 21);
		return CB_Faculty;
	}

	private JTextField gettxtCoursName() {
		txtCoursName = new JTextField();
		txtCoursName.setEnabled(false);
		txtCoursName.setText("cours name");
		txtCoursName.setBounds(10, 207, 105, 20);
		txtCoursName.setColumns(10);
		return txtCoursName;
	}

	private JTextField gettxtIdNumber() {

		txtIdNumber = new JTextField();
		txtIdNumber.setEnabled(false);
		txtIdNumber.setEditable(false);
		txtIdNumber.setText("ID Number");
		txtIdNumber.setBounds(10, 144, 105, 20);
		txtIdNumber.setColumns(10);
		return txtIdNumber;
	}

	private JLabel getlblCoursName() {
		JLabel lblCoursName = new JLabel("Cours Name:");
		lblCoursName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCoursName.setBounds(10, 179, 125, 20);
		return lblCoursName;
	}

	private JLabel getlblFaculty() {
		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaculty.setBounds(10, 244, 88, 22);
		return lblFaculty;
	}

	private JLabel getlblId() {
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(10, 114, 50, 20);
		return lblId;
	}

	private JComboBox getcmbBxEditCouse() {
		cmbBxEditCouse = new JComboBox<Object>();
		cmbBxEditCouse.setBounds(10, 53, 754, 20);
		return cmbBxEditCouse;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnNewCourse) {
		}
		
		if (e.getSource() == btnAdd) {
			int index;
			
			
				}

		
		if (e.getSource() == btnRemove) {
		}
		if (e.getSource() == btnSave) {
		}
		if (e.getSource() == btnDiscard) {
			manager.BacktoMainMenu(this.PNL_Main);
		}

	}


	private void addAids(int index) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setCourse(Object course) {
		// TODO Auto-generated method stub
		
	}

}
