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
import javax.swing.DefaultListModel;
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

import entities.*;
import Controllers.ManagerController;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;
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
	private JTextField txtCourseName;
	private JTextField txtCourseEditor;
	private JLabel lblMainAcademicHours;

	private JComboBox cmbBxEditCouse;
	private JComboBox CB_Faculty;
	private JSpinner Course_Semester;
	private JList lstAvailableLecturers;
	private JList lstChoosenLecturers;
	private JButton btnNewCourse;
	private JButton btnSave;
	private JButton btnDiscard;
	private JButton btnAdd;
	private JButton btnRemove;
	public JPanel PNL_Main;
	private JSpinner Practice_AcademicHours;
	private JSpinner Practice_MaxStdntPerClass;
	private JList<?> Practice_StudyAids;

	private JLabel lblStudyAids;
	private ManagerController manager;

	/*		Lecturers Data		*/
	private Map<Integer, Integer> ArrayAvailableLecturers;
	private Map<Integer, Integer> arraySelectedLecturers;
	private Map<Integer, Lecturer> ArrayLecturers;
	private DefaultListModel lstCLecturersModel;
	private DefaultListModel lstSelectedLecturersModel;
	
	
	/*		general StudyAids	*/
	private Map<Integer, StudyAids> LecStudyAids;
	/*		Lecture	StudyAids	*/
	private Map<Integer, Integer> LecAvailableStudyAids;
	private Map<Integer, Integer> LecSelectedStudyAids;
	private DefaultListModel lstLecturersClassAidsModel;
	private DefaultListModel lstLecturersSelectedClassAidsModel;
	
	
	
	private ArrayList<Faculty> arrayFaculty;
	private ArrayList<Course> arrayCourse;
	
	

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
		initialize();
		Practice_AcademicHours = new JSpinner();
		Practice_AcademicHours.setBounds(265, 400, 79, 20);
		PNL_Main.add(Practice_AcademicHours);
		Practice_AcademicHours.setFont(new Font("Dialog", Font.PLAIN, 16));
		JLabel label_7 = new JLabel("Max student per class:");
		label_7.setBounds(519, 373, 243, 23);
		PNL_Main.add(label_7);
		label_7.setFont(new Font("Dialog", Font.PLAIN, 18));
		Practice_MaxStdntPerClass = new JSpinner();
		Practice_MaxStdntPerClass.setBounds(519, 400, 79, 20);
		PNL_Main.add(Practice_MaxStdntPerClass);
		Practice_MaxStdntPerClass.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblStudyAids = new JLabel("Study aids:");
		lblStudyAids.setBounds(506, 102, 170, 23);
		PNL_Main.add(lblStudyAids);
		lblStudyAids.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Practice_StudyAids = new JList<Object>();
		Practice_StudyAids.setToolTipText("Selected study aids");
		Practice_StudyAids.setBounds(510, 268, 243, 90);
		PNL_Main.add(Practice_StudyAids);
		Practice_StudyAids.setBackground(SystemColor.inactiveCaption);
		Practice_StudyAids.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JList<Object> list = new JList<Object>();
		list.setToolTipText("Available study aids");
		list.setFont(new Font("Dialog", Font.PLAIN, 16));
		list.setBackground(SystemColor.inactiveCaption);
		list.setBounds(510, 142, 243, 85);
		PNL_Main.add(list);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(506, 122, 258, 14);
		PNL_Main.add(horizontalStrut);
		
		JButton btnAddStudyAids = new JButton("Add");
		btnAddStudyAids.setToolTipText("Add Study aid to course");
		btnAddStudyAids.setBounds(520, 237, 94, 23);
		PNL_Main.add(btnAddStudyAids);
		
		JButton btnRemveStudyAids = new JButton("Remove");
		btnRemveStudyAids.setToolTipText("Remove Study aid to course");
		btnRemveStudyAids.setBounds(651, 237, 89, 23);
		PNL_Main.add(btnRemveStudyAids);
		
		}
	
	
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

		PNL_Main.add(getlblAvailableLecturers());

		PNL_Main.add(getlstAvailableLecturers());

		PNL_Main.add(getbtnAdd());

		PNL_Main.add(getbtnRemove());

		PNL_Main.add(getlstChoosenLecturers());

		PNL_Main.add(getbtnNewCourse());

		PNL_Main.add(getbtnSave());

		PNL_Main.add(gettxtCourseEditor());

		PNL_Main.add(gethorizontalStrut());

		PNL_Main.add(getbtnDiscard());

		PNL_Main.add(gethorizontalStrut_1());

		PNL_Main.add(gethorizontalStrut_2());
		JLabel label_6 = new JLabel("Academic hours:");
		label_6.setBounds(265, 373, 243, 23);
		PNL_Main.add(label_6);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 18));
		

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
		horizontalStrut_2.setBounds(145, 122, 327, 14);
		return horizontalStrut_2;
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

	
	private JList getlstChoosenLecturers() {
		lstChoosenLecturers = new JList<Object>();
		lstChoosenLecturers.setToolTipText("Selectedlecturers");
		lstChoosenLecturers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstChoosenLecturers.setBounds(345, 141, 115, 217);
		return lstChoosenLecturers;
	}

	private JButton getbtnRemove() {
		btnRemove = new JButton("<--");
		btnRemove.setToolTipText("Remove lecturer from course");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(270, 237, 65, 38);
		return btnRemove;
	}

	private JButton getbtnAdd() {
		btnAdd = new JButton("-->");
		btnAdd.setToolTipText("Add lecturer to course");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(270, 171, 65, 38);
		return btnAdd;
	}

	private JList getlstAvailableLecturers() {
		lstAvailableLecturers = new JList<Object>();
		lstAvailableLecturers.setToolTipText("Available lecturers");
		lstAvailableLecturers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstAvailableLecturers.setBounds(145, 137, 115, 221);
		return lstAvailableLecturers;
	}

	private JLabel getlblAvailableLecturers() {
		JLabel lblAvailableLecturers = new JLabel("Lecturers:");
		lblAvailableLecturers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAvailableLecturers.setBounds(145, 105, 154, 14);
		return lblAvailableLecturers;
	}

	

	private JSpinner getCourse_Semester() {
		Course_Semester = new JSpinner();
		Course_Semester.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		Course_Semester.setBounds(11, 402, 79, 20);
		return Course_Semester;
	}

	private JLabel getlblSemester() {
		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSemester.setBounds(11, 369, 243, 29);
		return lblSemester;
	}

	private JComboBox getCB_Faculty() {
		CB_Faculty = new JComboBox();
		CB_Faculty.setBounds(10, 272, 105, 21);
		return CB_Faculty;
	}

	private JTextField gettxtCoursName() {
		txtCourseName = new JTextField();
		txtCourseName.setEnabled(false);
		txtCourseName.setText("course name");
		txtCourseName.setBounds(10, 207, 105, 20);
		txtCourseName.setColumns(10);
		return txtCourseName;
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
		JLabel lblCoursName = new JLabel("Course Name:");
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
		cmbBxEditCouse = new JComboBox();
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


	public void setCourses(ArrayList<Course> ArrayList) {
		arrayCourse =ArrayList;
		cmbBxEditCouse.removeAll();
		for (int i = 0; i < arrayCourse.size(); i++) {
			cmbBxEditCouse.addItem(arrayCourse.get(i).getCourseID() + ":"
					+ arrayCourse.get(i).getDescription());
		}
		
	}


	public void setFaculty(ArrayList<Faculty> ArrayList) {
		arrayFaculty =ArrayList;
		cmbBxEditCouse.removeAll();
		for (int i = 0; i < arrayFaculty.size(); i++) {
			CB_Faculty.addItem(arrayFaculty.get(i).getFacultyNum() + ":"
					+ arrayFaculty.get(i).getFaculty());
		}
		
	}


	public void setAvailableLecturers(ArrayList<Lecturer> arrayList) {
		ArrayLecturers = new HashMap<Integer,Lecturer>();
		ArrayAvailableLecturers= new HashMap<Integer, Integer>();
		arraySelectedLecturers = new HashMap<Integer, Integer>();
					
		lstCLecturersModel.removeAllElements();
		lstSelectedLecturersModel.removeAllElements();
		for (int i = 0; i < arrayList.size(); i++) {
			ArrayLecturers.put(arrayList.get(i).getID(), arrayList.get(i));
			lstCLecturersModel.addElement(arrayList.get(i).getID() + ":"
					+ arrayList.get(i).getFirstName()+ ":" + arrayList.get(i).getSurName());
			ArrayAvailableLecturers.put(arrayList.get(i).getID(), arrayList
					.get(i).getID());
		}
	}
		
		
	}

