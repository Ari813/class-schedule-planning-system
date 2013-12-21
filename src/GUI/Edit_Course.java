package GUI;

import java.awt.Color;
import java.util.Iterator;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.*;
import Controllers.ManagerController;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;

public class Edit_Course extends JPanel implements ActionListener,
		ListSelectionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isNewCourse;
	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;
	private JTextField txtIdNumber;
	private JTextField txtCourseName;
	private JTextField txtCourseEditor;
	private JLabel lblMainAcademicHours;
	private JLabel lblAcademichours;
	private JComboBox cmbBxEditCourse;
	private JComboBox CB_Faculty;
	private JSpinner Course_Semester;
	private JList lstAvailableLecturers;
	private JList lstChoosenLecturers;
	private JButton btnNewCourse;
	private JButton btnSave;
	private JButton btnDiscard;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnRemveStudyAids;
	public JPanel PNL_Main;
	private JSpinner AcademicHours;
	private JSpinner MaxStdntPerClass;
	private JList<?> SelectedStudyAids;
	private JButton btnAddStudyAids;
	private JLabel lblStudyAids;
	private JLabel lblMaxsdntClassJ; // Max student per class
	private ManagerController manager;
	private JList AvailableStudyAids;
	private JComboBox mainCourscomboBox;
	/* Lecturers Data */
	private Map<Integer, Lecturer> ArrayLecturers; // arrayStudyAids;
	private Map<Integer, Integer> ArrayAvailableLecturers; // arrayAvailableStudyAids;
	private Map<Integer, Integer> arraySelectedLecturers; // arraySelectedStudyAids;
	private DefaultListModel lstCLecturersModel;// lstClassAidsModel;
	private DefaultListModel lstSelectedLecturersModel; // lstSelectedClassAidsModel;
	private JCheckBox chckbxMainCourse;
	/* general StudyAids */
	private Map<Integer, StudyAids> crsStudyAids;
	/* Lecture StudyAids */

	private Map<Integer, Integer> CrsAvailableStudyAids;
	private Map<Integer, Integer> CrsSelectedStudyAids;
	private DefaultListModel lstLecturersClassAidsModel;
	private DefaultListModel lstLecturersSelectedClassAidsModel;

	private ArrayList<Faculty> arrayFaculty;
	private ArrayList<Course> arrayCourse;

	private Map<Integer, Integer> indexcourse;
	/**
	 * Create the panel.
	 */

	public Edit_Course(ManagerController mng) {

		super();
		this.manager = mng;

		initialize();

	}

	private JButton getBtnRemveStudyAids() {
		btnRemveStudyAids = new JButton("Remove");
		btnRemveStudyAids.setToolTipText("Remove Study aid to course");
		btnRemveStudyAids.setBounds(651, 237, 89, 23);
		
		return btnRemveStudyAids;
	}

	private JButton getBtnAddStudyAids() {
		btnAddStudyAids = new JButton("Add");
		btnAddStudyAids.setToolTipText("Add Study aid to course");
		btnAddStudyAids.setBounds(520, 237, 94, 23);
		
		return btnAddStudyAids;
	}

	private Component getHorizontalStrut() {
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(506, 122, 258, 14);
		return horizontalStrut;
	}

	private JList getAvailableStudyAids() {
		AvailableStudyAids = new JList();
		lstLecturersClassAidsModel = new DefaultListModel();
		AvailableStudyAids.setModel(lstLecturersClassAidsModel);
		AvailableStudyAids.setToolTipText("Available study aids");
		AvailableStudyAids.setFont(new Font("Dialog", Font.PLAIN, 16));
		AvailableStudyAids.setBackground(SystemColor.inactiveCaption);
		AvailableStudyAids.setBounds(510, 142, 243, 85);
		return AvailableStudyAids;
	}

	private JList getSelectedStudyAids() {
		SelectedStudyAids = new JList();
		lstLecturersSelectedClassAidsModel = new DefaultListModel();
		SelectedStudyAids.setModel(lstLecturersSelectedClassAidsModel);
		SelectedStudyAids.setToolTipText("Selected study aids");
		SelectedStudyAids.setBounds(510, 268, 243, 90);
		SelectedStudyAids.setBackground(SystemColor.inactiveCaption);
		SelectedStudyAids.setFont(new Font("Dialog", Font.PLAIN, 16));
		return SelectedStudyAids;
	}

	private JLabel getlblStudyAids() {
		lblStudyAids = new JLabel("Study aids:");
		lblStudyAids.setBounds(506, 102, 170, 23);
		lblStudyAids.setFont(new Font("Tahoma", Font.PLAIN, 18));
		return lblStudyAids;
	}

	private JSpinner getMaxStdntPerClass() {
		MaxStdntPerClass = new JSpinner();
		MaxStdntPerClass.setFont(new Font("Dialog", Font.PLAIN, 16));
		MaxStdntPerClass.setModel(new SpinnerNumberModel(0, 0, 120, 1));
		MaxStdntPerClass.setBounds(519, 400, 79, 20);
		return MaxStdntPerClass;
	}

	private Component getlblMaxsdntClassJ() {
		lblMaxsdntClassJ = new JLabel("Max student per class:");
		lblMaxsdntClassJ.setBounds(518, 369, 243, 29);
		lblMaxsdntClassJ.setFont(new Font("Dialog", Font.PLAIN, 18));
		return lblMaxsdntClassJ;
	}

	private JSpinner getAcademicHours() {
		AcademicHours = new JSpinner();
		AcademicHours.setModel(new SpinnerNumberModel(0, 0, 8, 1));
		AcademicHours.setBounds(265, 400, 79, 20);
		AcademicHours.setFont(new Font("Dialog", Font.PLAIN, 16));
		return AcademicHours;
	}

	private void initialize() {

		pnl();

		isNewCourse = false;

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

		PNL_Main.add(getlabel_6());

		PNL_Main.add(getAcademicHours());

		PNL_Main.add(getlblMaxsdntClassJ());

		PNL_Main.add(getMaxStdntPerClass());

		PNL_Main.add(getlblStudyAids());

		PNL_Main.add(getSelectedStudyAids());

		PNL_Main.add(getAvailableStudyAids());

		PNL_Main.add(getHorizontalStrut());

		PNL_Main.add(getBtnAddStudyAids());

		PNL_Main.add(getBtnRemveStudyAids());
		
		mainCourscomboBox = new JComboBox();
		mainCourscomboBox.setBounds(10, 137, 125, 20);
		mainCourscomboBox.setVisible(false);
		
		PNL_Main.add(mainCourscomboBox);
		
		chckbxMainCourse = new JCheckBox("have main course");
		chckbxMainCourse.setBounds(10, 104, 129, 23);
		
		PNL_Main.add(chckbxMainCourse);

		PNL_Main.repaint();
	}

	private JLabel getlabel_6() {
		lblAcademichours = new JLabel("Academic hours:");
		lblAcademichours.setBounds(264, 369, 243, 29);
		lblAcademichours.setFont(new Font("Dialog", Font.PLAIN, 18));
		return lblAcademichours;
	}

	private void pnl() {
		PNL_Main = new JPanel();
		PNL_Main.setToolTipText("");
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
		lstSelectedLecturersModel = new DefaultListModel();
		lstChoosenLecturers = new JList();
		lstChoosenLecturers.setModel(lstSelectedLecturersModel);
		lstChoosenLecturers.setToolTipText("Selected Lecturers");
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
		lstCLecturersModel = new DefaultListModel();

		lstAvailableLecturers = new JList<Object>();
		lstAvailableLecturers.setModel(lstCLecturersModel);
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
		SpinnerNumberModel snm = new SpinnerNumberModel(new Integer(0),
				new Integer(0), new Integer(100), new Integer(5));

		Course_Semester = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		Course_Semester.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		Course_Semester.setBounds(11, 400, 79, 20);
		return Course_Semester;

	}

	private JLabel getlblSemester() {
		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSemester.setBounds(10, 369, 243, 29);
		return lblSemester;
	}

	private JComboBox getCB_Faculty() {
		CB_Faculty = new JComboBox();
		CB_Faculty.setBounds(10, 329, 105, 21);
		return CB_Faculty;
	}

	private JTextField gettxtCoursName() {
		txtCourseName = new JTextField();
		txtCourseName.setEnabled(false);
		txtCourseName.setText("course name");
		txtCourseName.setBounds(10, 264, 105, 20);
		txtCourseName.setColumns(10);
		return txtCourseName;
	}

	private JTextField gettxtIdNumber() {

		txtIdNumber = new JTextField();
		txtIdNumber.setEnabled(false);
		txtIdNumber.setEditable(false);
		txtIdNumber.setText("ID Number");
		txtIdNumber.setBounds(10, 201, 105, 20);
		txtIdNumber.setColumns(10);
		txtIdNumber.addKeyListener(this);
		return txtIdNumber;
	}

	private JLabel getlblCoursName() {
		JLabel lblCoursName = new JLabel("Course Name:");
		lblCoursName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCoursName.setBounds(10, 236, 125, 20);
		return lblCoursName;
	}

	private JLabel getlblFaculty() {
		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaculty.setBounds(10, 301, 88, 22);
		return lblFaculty;
	}

	private JLabel getlblId() {
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(10, 171, 50, 20);
		return lblId;
	}

	private JComboBox getcmbBxEditCouse() {
		cmbBxEditCourse = new JComboBox();
		cmbBxEditCourse.setModel(new DefaultComboBoxModel(
				new String[] { "Empty" }));
		cmbBxEditCourse.setBounds(10, 53, 754, 20);
		
		return cmbBxEditCourse;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cmbBxEditCourse) {
			//mainCourscomboBox.setVisible(false);
			//chckbxMainCourse.setVisible(false);
			createNewCourse(false);
			setSelectedCourse();
		}
		if (e.getSource() == btnAddStudyAids) {
			int index;
			if ((crsStudyAids != null) && (!crsStudyAids.isEmpty())) {
				if (AvailableStudyAids.getSelectedIndex() >= 0) {
					index = Integer
							.parseInt(lstLecturersClassAidsModel
									.getElementAt(
											AvailableStudyAids
													.getSelectedIndex())
									.toString().split(":")[0]);
					addAids(index);
				}
			}
		}
		if (e.getSource() == btnRemveStudyAids) {
			int index;

			if ((CrsSelectedStudyAids != null)
					&& (!CrsSelectedStudyAids.isEmpty())) {
				if (SelectedStudyAids.getSelectedIndex() >= 0) {
					index = Integer.parseInt(lstLecturersSelectedClassAidsModel
							.getElementAt(SelectedStudyAids.getSelectedIndex())
							.toString().split(":")[0]);
					removeAids(index);
				}
			}
		}

		if (e.getSource() == btnNewCourse) {
			//mainCourscomboBox.setVisible(true);
		//	chckbxMainCourse.setVisible(true);
			isNewCourse = true;
			resetLists();
			resetListslec();
			createNewCourse(true);
			Iterator<StudyAids> aidsItr = crsStudyAids.values().iterator();
			while (aidsItr.hasNext()) {
				int tempID = aidsItr.next().getAidsID();
				CrsAvailableStudyAids.put(tempID, tempID);
				lstLecturersClassAidsModel.addElement(tempID + ":"
						+ crsStudyAids.get(tempID).getAidsName());
			}

			Iterator<Lecturer> LecItr = ArrayLecturers.values().iterator();
			while (LecItr.hasNext()) {
				int tempID = LecItr.next().getID();
				ArrayAvailableLecturers.put(tempID, tempID);
				lstCLecturersModel.addElement(tempID + ":"
						+ ArrayLecturers.get(tempID).getName());
			}
		}

		if (e.getSource() == btnAdd) {

			int index = 0;

			if ((ArrayLecturers != null) && (!ArrayLecturers.isEmpty())) {
				if (lstAvailableLecturers.getSelectedIndex() >= 0) {// lstAvailableLecturers
																	// <--lstClassAids
					index = Integer.parseInt(lstCLecturersModel
							.getElementAt(
									lstAvailableLecturers.getSelectedIndex())
							.toString().split(":")[0]);
					addLEC(index);

				}
			}
		}

		if (e.getSource() == btnRemove) {
			int index;

			if ((arraySelectedLecturers != null)
					&& (!arraySelectedLecturers.isEmpty())) {
				if (lstChoosenLecturers.getSelectedIndex() >= 0) {// lstChoosenLecturers
																	// <--
																	// lstSelectedClassaids
					index = Integer.parseInt(lstSelectedLecturersModel
							.getElementAt(
									lstChoosenLecturers.getSelectedIndex())
							.toString().split(":")[0]);
					removeLEC(index);
				}
			}
		}
		if (e.getSource() == mainCourscomboBox) {
			
		}
		if (e.getSource() == chckbxMainCourse) {
			if (chckbxMainCourse.isSelected())
				mainCourscomboBox.setVisible(true);
			
			else
				mainCourscomboBox.setVisible(false);
		}
		if (e.getSource() == btnSave) {
			//mainCourscomboBox.setVisible(false);
			//chckbxMainCourse.setVisible(false);
			chckbxMainCourse.setSelected(false);;
			Course newCourse = new Course();
			
			newCourse.setCourseID(Integer.parseInt(txtIdNumber.getText()));
			newCourse.setDescription(txtCourseName.getText());
			newCourse.setFaculty(arrayFaculty
					.get(CB_Faculty.getSelectedIndex()).getFacultyNum());
			newCourse.setSemester((int) Course_Semester.getValue());
			newCourse.setAcademicHours((int) AcademicHours.getValue());
			newCourse.setStudentNumber((int) MaxStdntPerClass.getValue());
			if (chckbxMainCourse.isSelected()){
				newCourse.setCourseRelativeKey(arrayCourse.get(indexcourse.get(mainCourscomboBox.getSelectedIndex())).getCourseID());
				
				}
			else
				newCourse.setCourseRelativeKey(-1);
			//newCourse
			Iterator<Integer> itr = arraySelectedLecturers.values().iterator();
			while (itr.hasNext())
				newCourse.addLecturer(ArrayLecturers.get(itr.next()));
				
			itr = CrsSelectedStudyAids.values().iterator();
			while (itr.hasNext())
				newCourse.addStudyAids(crsStudyAids.get(itr.next()));

			Course serverAnsCourse;
			if (isNewCourse) {
				serverAnsCourse = manager.CreateNewCourse(newCourse);
			} else {
				serverAnsCourse = manager.UpdateNewCourse(newCourse);
			
			}
			if (serverAnsCourse.getCourseID() == newCourse.getCourseID()){
				if(!isNewCourse)
				arrayCourse.set(cmbBxEditCourse.getSelectedIndex()-1, newCourse);
				else{
					arrayCourse.add(newCourse);
					setCourses(arrayCourse);
					
				
				}
				System.out.println("Success!!!");}
			else {
				System.out.println("Fail!!!!");
			}
			if (isNewCourse)
				setdefault();
			createNewCourse(false);
			//manager.BacktoMainMenu(this.PNL_Main);
		}
		if (e.getSource() == btnDiscard) {
			manager.BacktoMainMenu(this.PNL_Main);
		}
	}

	private void createNewCourse(boolean bool) {
		txtIdNumber.setEditable(bool);
		txtIdNumber.setEnabled(bool);
		txtIdNumber.setText("");
		txtCourseName.setEditable(bool);
		txtCourseName.setEnabled(bool);
		txtCourseName.setText("");

		//CB_Faculty.getModel().setSelectedItem(1);
		Course_Semester.setValue(1);
		AcademicHours.setValue(1);
		MaxStdntPerClass.setValue(1);

		isNewCourse = bool;
	}

	private void removeLEC(int index) {
		ArrayAvailableLecturers.put(arraySelectedLecturers.get(index),
				arraySelectedLecturers.get(index));
		arraySelectedLecturers.remove(index);
		lstCLecturersModel.addElement(lstSelectedLecturersModel
				.getElementAt(lstChoosenLecturers.getSelectedIndex()));
		lstSelectedLecturersModel
				.remove(lstChoosenLecturers.getSelectedIndex());

	}

	private void addLEC(int index) {
		arraySelectedLecturers.put(ArrayAvailableLecturers.get(index),
				ArrayAvailableLecturers.get(index));
		ArrayAvailableLecturers.remove(index);
		lstSelectedLecturersModel.addElement(lstCLecturersModel
				.getElementAt(lstAvailableLecturers.getSelectedIndex()));
		lstCLecturersModel.remove(lstAvailableLecturers.getSelectedIndex());

	}

	private void setSelectedCourse() {
		int i = 0;
		CB_Faculty.setVisible(true);

		int index = cmbBxEditCourse.getSelectedIndex()-ManagerController.fix ;

		if ((arrayCourse != null) && (!arrayCourse.isEmpty()) && (index >= 0)) {
			for (i = 0; i < arrayFaculty.size(); i++) {
				if (arrayCourse.get(index).getFaculty() == arrayFaculty.get(i)
						.getFacultyNum())
					break;
			}

			CB_Faculty.setSelectedIndex(i);
			txtIdNumber.setText(Integer.toString(arrayCourse.get(index)
					.getCourseID()));
			txtCourseName.setText((arrayCourse.get(index).getDescription()));
			Course_Semester.setValue(arrayCourse.get(index).getSemester());
			AcademicHours.setValue(arrayCourse.get(index).getAcademicHours());
			MaxStdntPerClass
					.setValue(arrayCourse.get(index).getStudentNumber());
			resetLists();
			setCouseAids(index);
			setCoursLec(index);
			btnSave.setEnabled(true);

		}
		if (index < 0)
			setdefault();

	}

	public void setdefault() {
		txtIdNumber.setText("ID Number");
		txtCourseName.setText("Course name");
		//CB_Faculty.setVisible(false);
		Course_Semester.setValue(0);
		AcademicHours.setValue(0);
		MaxStdntPerClass.setValue(0);
		btnSave.setEnabled(false);
	}

	private void setCoursLec(int index) {
		{
			resetListslec();

			for (int i = 0; i < arrayCourse.get(index).getCourseLecturers()
					.size(); i++) {
				arraySelectedLecturers.put(arrayCourse.get(index)
						.getCourseLecturers().get(i).getID(),
						arrayCourse.get(index).getCourseLecturers().get(i)
								.getID());
				lstSelectedLecturersModel.addElement(arrayCourse.get(index)
						.getCourseLecturers().get(i).getID()
						+ ":"
						+ ArrayLecturers.get(
								arrayCourse.get(index).getCourseLecturers()
										.get(i).getID()).getName());
			}
			Iterator<Lecturer> itr = ArrayLecturers.values().iterator();
			while (itr.hasNext()) {
				int tempID = itr.next().getID();
				if (!arraySelectedLecturers.containsKey(tempID)) {
					ArrayAvailableLecturers.put(tempID, tempID);
					lstCLecturersModel.addElement(tempID + ":"
							+ ArrayLecturers.get(tempID).getName());
				}

			}
		}

	}

	private void resetListslec() {
		arraySelectedLecturers.clear();
		ArrayAvailableLecturers.clear();
		lstCLecturersModel.removeAllElements();
		lstSelectedLecturersModel.removeAllElements();

	}

	private void setCouseAids(int index) {
		// resetLists();
		for (int i = 0; i < arrayCourse.get(index).getStudyAids().size(); i++) {
			CrsSelectedStudyAids.put(
					arrayCourse.get(index).getStudyAids().get(i).getAidsID(),
					arrayCourse.get(index).getStudyAids().get(i).getAidsID());
			lstLecturersSelectedClassAidsModel.addElement(arrayCourse
					.get(index).getStudyAids().get(i).getAidsID()
					+ ":"
					+ crsStudyAids.get(
							arrayCourse.get(index).getStudyAids().get(i)
									.getAidsID()).getAidsName());
		}
		Iterator<StudyAids> itr = crsStudyAids.values().iterator();
		while (itr.hasNext()) {
			int tempID = itr.next().getAidsID();
			if (!CrsSelectedStudyAids.containsKey(tempID)) {
				CrsAvailableStudyAids.put(tempID, tempID);
				lstLecturersClassAidsModel.addElement(tempID + ":"
						+ crsStudyAids.get(tempID).getAidsName());
			}
		}
	}

	private void resetLists() {
		// TODO Auto-generated method stub
		CrsSelectedStudyAids.clear();
		CrsAvailableStudyAids.clear();
		lstLecturersClassAidsModel.removeAllElements();
		lstLecturersSelectedClassAidsModel.removeAllElements();
	}

	private void removeAids(int index) {
		CrsAvailableStudyAids.put(CrsSelectedStudyAids.get(index),
				CrsSelectedStudyAids.get(index));
		CrsSelectedStudyAids.remove(index);
		lstLecturersClassAidsModel
				.addElement(lstLecturersSelectedClassAidsModel
						.getElementAt(SelectedStudyAids.getSelectedIndex()));
		lstLecturersSelectedClassAidsModel.remove(SelectedStudyAids
				.getSelectedIndex());

	}

	private void addAids(int index) {
		CrsSelectedStudyAids.put(CrsAvailableStudyAids.get(index),
				CrsAvailableStudyAids.get(index));
		CrsAvailableStudyAids.remove(index);
		lstLecturersSelectedClassAidsModel
				.addElement(lstLecturersClassAidsModel
						.getElementAt(AvailableStudyAids.getSelectedIndex()));
		lstLecturersClassAidsModel
				.remove(AvailableStudyAids.getSelectedIndex());

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar())) {
			if (e.getSource() == txtIdNumber) {
				txtIdNumber.setText("");
			}
		} else {
			btnSave.setEnabled(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setCourses(ArrayList<Course> arrayList) {
		arrayCourse = arrayList;
		indexcourse=new HashMap<>();
		indexcourse.clear();
		cmbBxEditCourse.removeAllItems();
		int key=0;
		for (int i = 0; i < arrayCourse.size(); i++) {
			cmbBxEditCourse.addItem(arrayCourse.get(i).getCourseID() + ":"
					+ arrayCourse.get(i).getDescription());
			if (arrayCourse.get(i).getCourseRelativeKey()==-1){
				
				mainCourscomboBox.addItem(arrayCourse.get(i).getCourseID() + ":"
					+ arrayCourse.get(i).getDescription());
				indexcourse.put(key, i);
				key++;
			
			}
			
		}

	}

	public void setFaculty(ArrayList<Faculty> arrayList) {
		arrayFaculty = arrayList;
		CB_Faculty.removeAllItems();
		for (int i = 0; i < arrayFaculty.size(); i++) {
			CB_Faculty.addItem(arrayFaculty.get(i).getFacultyNum() + ":"
					+ arrayFaculty.get(i).getFaculty());
		}

	}

	public void setAvailableLecturers(ArrayList<Lecturer> arrayList) {
		ArrayLecturers = new HashMap<Integer, Lecturer>();
		ArrayAvailableLecturers = new HashMap<Integer, Integer>();
		arraySelectedLecturers = new HashMap<Integer, Integer>();

		lstCLecturersModel.removeAllElements();
		lstSelectedLecturersModel.removeAllElements();
		for (int i = 0; i < arrayList.size(); i++) {
			ArrayLecturers.put(arrayList.get(i).getID(), arrayList.get(i));
			lstCLecturersModel.addElement(arrayList.get(i).getID() + ":"
					+ arrayList.get(i).getName());
			ArrayAvailableLecturers.put(arrayList.get(i).getID(), arrayList
					.get(i).getID());
		}
	}

	public void setStudyAids(ArrayList<StudyAids> arrayList) {
		int i;
		crsStudyAids = new HashMap<Integer, StudyAids>();
		CrsAvailableStudyAids = new HashMap<Integer, Integer>();
		CrsSelectedStudyAids = new HashMap<Integer, Integer>();
		lstLecturersClassAidsModel.removeAllElements();
		lstLecturersSelectedClassAidsModel.removeAllElements();
		for (i = 0; i < arrayList.size(); i++) {
			crsStudyAids.put(arrayList.get(i).getAidsID(), arrayList.get(i));
			lstLecturersClassAidsModel.addElement(arrayList.get(i).getAidsID()
					+ ":" + arrayList.get(i).getAidsName());
			CrsAvailableStudyAids.put(arrayList.get(i).getAidsID(), arrayList
					.get(i).getAidsID());
		}

	}

	public void addActions() {
		btnRemveStudyAids.addActionListener(this);
		btnAddStudyAids.addActionListener(this);
		mainCourscomboBox.addActionListener(this);
		btnDiscard.addActionListener(this);
		chckbxMainCourse.addActionListener(this);
		btnSave.addActionListener(this);
		btnNewCourse.addActionListener(this);
		btnRemove.addActionListener(this);
		btnAdd.addActionListener(this);
		cmbBxEditCourse.addActionListener(this);
	}
}
