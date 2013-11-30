package GUI;

import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

import javax.swing.UIManager;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import entities.Course;
import entities.Lecturer;
import Controllers.LecturerController;
import Controllers.ManagerController;

import java.util.Iterator;

public class Edit_Lecturer extends JPanel implements ActionListener,
		ListSelectionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */

	private JTextField txtLecturerNameText;
	private JTextField txtLecturerEditor;
	private JComboBox cmbxLecturerEditor;
	private JList lstAvailableCourses;// lstAvailableLecturers
	private JList lstSelectedCourses;// lstChoosenLecturers
	private JTextField txtpnIDNumber;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnNewLecturer;
	private JButton btnSaveChanges;
	private JButton btnBackToMainMenu;
	public JPanel PNL_Main;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private JLabel lblID;
	private JLabel lblLecturer_Name;
	private JLabel lblAvailableCourses;
	LecturerController lec;
	boolean isNewLecturer;

	private ArrayList<Lecturer> ArrayLecturer;

	private Map<Integer, Course> arrayCourse;// ArrayLecturers;
	private Map<Integer, Integer> ArrayAvailableCourse;// ArrayAvailableLecturers;
	private Map<Integer, Integer> arraySelectedCourse;// arraySelectedLecturers;
	private DefaultListModel lstCourseModel;// lstCLecturersModel;;
	private DefaultListModel lstSelectedCourseModel;// lstSelectedLecturersModel;

	private ManagerController manager;

	/*
	 * / JLabel lblTimeTableSchedualing = new
	 * JLabel("Time Table Schedualing System");
	 * lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
	 * lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
	 * lblTimeTableSchedualing.setLocation(new Point(50, 0));
	 * lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
	 * lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
	 * lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
	 * lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED,
	 * null, null, null, null)); /
	 */
	public Edit_Lecturer(ManagerController mng) {

		super();
		this.manager = mng;
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		pnl();

		PNL_Main.add(GETcmbxLecturerEditor());
		PNL_Main.add(GETtxtpnIDNumber());
		PNL_Main.add(GETlblID());
		PNL_Main.add(GETlblLecturer_Name());
		PNL_Main.add(GETtxLecturerNameText());
		PNL_Main.add(GETlblAvailableCourses());
		PNL_Main.add(GETlstAvailableCourses());
		PNL_Main.add(GETbtnAdd());
		PNL_Main.add(GETbtnRemove());
		PNL_Main.add(GETlstSelectedCourses());
		PNL_Main.add(GETbtnNewLecturer());
		PNL_Main.add(GETbtnSaveChanges());
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtLecturerEditor());

		horizontalStrut();
	}

	private Component GETbtnRemove() {
		btnRemove = new JButton("<--");
		btnRemove.setToolTipText("Remove item from class");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(502, 216, 65, 38);
		btnRemove.addActionListener(this);
		return btnRemove;
	}

	private JList GETlstSelectedCourses() {
		lstSelectedCourses = new JList();
		lstSelectedCourseModel = new DefaultListModel();
		lstSelectedCourses.setModel(lstSelectedCourseModel);
		lstSelectedCourses.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstSelectedCourses.setBackground(new Color(169, 169, 169));
		lstSelectedCourses.setForeground(new Color(255, 255, 255));
		lstSelectedCourses.setBounds(586, 130, 138, 283);
		return lstSelectedCourses;
	}

	private JButton GETbtnNewLecturer() {
		btnNewLecturer = new JButton("New Lecturer");
		btnNewLecturer.addActionListener(this);
		btnNewLecturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewLecturer.setBounds(73, 434, 160, 29);
		btnNewLecturer.addActionListener(this);
		return btnNewLecturer;
	}

	private JButton GETbtnSaveChanges() {
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveChanges.setBounds(306, 434, 160, 29);
		btnSaveChanges.addActionListener(this);
		btnSaveChanges.setEnabled(false);
		return btnSaveChanges;
	}

	private Component GETbtnBackToMainMenu() {
		btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBackToMainMenu.addActionListener(this);
		btnBackToMainMenu.setBounds(539, 434, 160, 29);
		btnBackToMainMenu.addActionListener(this);
		return btnBackToMainMenu;
	}

	private JButton GETbtnAdd() {
		btnAdd = new JButton(" -->");
		btnAdd.setToolTipText("Add item to class");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.addActionListener(this);
		btnAdd.setBounds(502, 163, 65, 38);
		return btnAdd;
	}

	private JList GETlstAvailableCourses() {
		lstAvailableCourses = new JList();
		lstCourseModel = new DefaultListModel();
		lstAvailableCourses.setModel(lstCourseModel);
		lstAvailableCourses.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstAvailableCourses.setForeground(new Color(0, 0, 0));
		lstAvailableCourses
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstAvailableCourses.setValueIsAdjusting(true);
		lstAvailableCourses.setSelectedIndices(new int[] { 2 });
		lstAvailableCourses.setBackground(new Color(169, 169, 169));

		lstAvailableCourses.setBounds(332, 130, 138, 283);
		return lstAvailableCourses;
	}

	private JTextField GETtxtLecturerEditor() {
		txtLecturerEditor = new JTextField();
		txtLecturerEditor.setEditable(false);
		txtLecturerEditor.setText("Leturer editor");
		txtLecturerEditor.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerEditor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerEditor.setColumns(10);
		txtLecturerEditor.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerEditor.setBackground(new Color(176, 224, 230));
		txtLecturerEditor.setBounds(0, 4, 774, 40);
		return txtLecturerEditor;
	}

	private JLabel GETlblAvailableCourses() {
		lblAvailableCourses = new JLabel("Available courses");
		lblAvailableCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAvailableCourses.setBounds(332, 100, 157, 14);
		return lblAvailableCourses;
	}

	private JTextField GETtxLecturerNameText() {
		txtLecturerNameText = new JTextField();
		txtLecturerNameText.setEnabled(false);
		txtLecturerNameText.setBackground(Color.WHITE);
		txtLecturerNameText.setText("Lecturer Name text");
		txtLecturerNameText.setBounds(10, 207, 100, 20);
		txtLecturerNameText.setColumns(10);
		return txtLecturerNameText;
	}

	private JLabel GETlblLecturer_Name() {
		lblLecturer_Name = new JLabel("Lecturer Name:");
		lblLecturer_Name.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLecturer_Name.setBounds(10, 179, 123, 22);
		return lblLecturer_Name;
	}

	private JLabel GETlblID() {
		lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblID.setBounds(10, 114, 90, 21);
		return lblID;
	}

	private void horizontalStrut() {
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(332, 114, 392, 14);
		PNL_Main.add(horizontalStrut);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(5, 424, 759, 5);
		PNL_Main.add(horizontalStrut_1);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBackground(Color.BLACK);
		horizontalStrut_2.setBounds(0, 75, 774, 5);
		PNL_Main.add(horizontalStrut_2);

	}

	private JTextField GETtxtpnIDNumber() {
		txtpnIDNumber = new JTextField();
		txtpnIDNumber.setEnabled(false);
		// txtpnIDNumber.setDropMode(DropMode.ON);
		txtpnIDNumber.setBackground(Color.WHITE);
		txtpnIDNumber.setText("ID Number");
		txtpnIDNumber.setBounds(10, 144, 100, 20);
		txtpnIDNumber.addKeyListener(this);
		return txtpnIDNumber;
	}

	private JComboBox GETcmbxLecturerEditor() {
		cmbxLecturerEditor = new JComboBox();
		cmbxLecturerEditor.setModel(new DefaultComboBoxModel(
				new String[] { "Empty" }));
		cmbxLecturerEditor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbxLecturerEditor.setToolTipText("Edit class list");
		cmbxLecturerEditor.setBounds(10, 53, 754, 20);
		cmbxLecturerEditor.setMaximumRowCount(52);
		cmbxLecturerEditor.addActionListener(this);
		return cmbxLecturerEditor;
	}

	private void pnl() {
		PNL_Main = new JPanel();
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);

		PNL_Main.setLayout(null);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			int index = 0;
			if ((arrayCourse != null) && (!arrayCourse.isEmpty())) {
				if (lstAvailableCourses.getSelectedIndex() >= 0) {
					index = Integer.parseInt(lstCourseModel
							.getElementAt(
									lstAvailableCourses.getSelectedIndex())
							.toString().split(":")[0]);
					addCourse(index);

				}
			}
		}
		if (e.getSource() == btnRemove) {
			int index = 0;

			if ((arraySelectedCourse != null)
					&& (!arraySelectedCourse.isEmpty())) {
				if (lstSelectedCourses.getSelectedIndex() >= 0) {// lstChoosenLecturers
																	// <--
																	// lstSelectedClassaids
					index = Integer
							.parseInt(lstSelectedCourseModel
									.getElementAt(
											lstSelectedCourses
													.getSelectedIndex())
									.toString().split(":")[0]);
					removeCourse(index);

				}
			}
		}
		if (e.getSource() == btnNewLecturer) {
			resetListslec();
			createNewLecturer(true);

			Iterator<Course> crsItr = arrayCourse.values().iterator();
			while (crsItr.hasNext()) {
				int tempID = crsItr.next().getCourseID();
				ArrayAvailableCourse.put(tempID, tempID);
				lstCourseModel.addElement(tempID + ":"
						+ arrayCourse.get(tempID).getDescription());
			}

		}
		if (e.getSource() == btnSaveChanges) {
			Lecturer newLecturer = new Lecturer();
			Lecturer serverAns;
			newLecturer.setID(Integer.parseInt(txtpnIDNumber.getText()));
			newLecturer.setName(txtLecturerNameText.getText());

			Iterator<Integer> crsItr = arraySelectedCourse.keySet().iterator();
			while (crsItr.hasNext()) {
				newLecturer
						.addCourse(arrayCourse.get(crsItr.next().intValue()));
			}

			if (isNewLecturer) {
				serverAns = manager.CreateNewLecturer(newLecturer);
			} else {
				serverAns = manager.UpdateNewLecturer(newLecturer);
			}

			if (serverAns.getID().equals(newLecturer.getID())){
				if(!isNewLecturer)
					ArrayLecturer.set(cmbxLecturerEditor.getSelectedIndex()-1, newLecturer);
					else{
						ArrayLecturer.add(newLecturer);
						setLec(ArrayLecturer);
																}
				System.out.println(" Success!!!");}
			else {
				System.out.println(" Fail!!!!");
			}
			createNewLecturer(false);
			setdefault();

		}
		if (e.getSource() == btnBackToMainMenu) {
			manager.BacktoMainMenu(this.PNL_Main);
		}
		if (e.getSource() == cmbxLecturerEditor) {
			createNewLecturer(false);

			setSelectedLec();
		}

	}

	private void setSelectedLec() {
		int index = cmbxLecturerEditor.getSelectedIndex() - 1;
		if ((ArrayLecturer != null) && (!ArrayLecturer.isEmpty())
				&& (index >= 0)) {
			txtpnIDNumber.setText(Integer.toString(ArrayLecturer.get(index)
					.getID()));
			txtLecturerNameText.setText(ArrayLecturer.get(index).getName());
			btnSaveChanges.setEnabled(true);
			setCoursLec(index);

		}
		if (index < 0)
			setdefault();

	}

	private void setdefault() {
		txtpnIDNumber.setText("ID Number");
		txtLecturerNameText.setText("lecturer name");
		btnSaveChanges.setEnabled(false);

	}

	private void setCoursLec(int index) {
		{
			resetListslec();

			for (int i = 0; i < ArrayLecturer.get(index).getLecturerCourses()
					.size(); i++) {
				arraySelectedCourse.put(ArrayLecturer.get(index)
						.getLecturerCourses().get(i).getCourseID(),
						ArrayLecturer.get(index).getLecturerCourses().get(i)
								.getCourseID());
				lstSelectedCourseModel
						.addElement(ArrayLecturer.get(index)
								.getLecturerCourses().get(i).getCourseID()
								+ ":"
								+ arrayCourse.get(
										ArrayLecturer.get(index)
												.getLecturerCourses().get(i)
												.getCourseID())
										.getDescription());
			}
			Iterator<Course> itr = arrayCourse.values().iterator();
			while (itr.hasNext()) {
				int tempID = itr.next().getCourseID();
				if (!arraySelectedCourse.containsKey(tempID)) {
					ArrayAvailableCourse.put(tempID, tempID);
					lstCourseModel.addElement(tempID + ":"
							+ arrayCourse.get(tempID).getDescription());
				}

			}
		}

	}

	private void createNewLecturer(boolean bool) {
		txtpnIDNumber.setEditable(bool);
		txtpnIDNumber.setEnabled(bool);
		txtpnIDNumber.setText("");
		txtLecturerNameText.setEditable(bool);
		txtLecturerNameText.setEnabled(bool);
		txtLecturerNameText.setText("");

		isNewLecturer = bool;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtpnIDNumber)
			if (!Character.isDigit(e.getKeyChar()))
				txtpnIDNumber.setText("");
			else
				btnSaveChanges.setEnabled(true);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	// d
	public void setLec(ArrayList<Lecturer> arrayList) {

		{
			ArrayLecturer = arrayList;
			cmbxLecturerEditor.removeAll();
			for (int i = 0; i < arrayList.size(); i++) {
				cmbxLecturerEditor.addItem(ArrayLecturer.get(i).getID() + ":"
						+ ArrayLecturer.get(i).getName());

			}

		}

	}

	private void addCourse(int index) {

		arraySelectedCourse.put(ArrayAvailableCourse.get(index),
				ArrayAvailableCourse.get(index));
		ArrayAvailableCourse.remove(index);
		lstSelectedCourseModel.addElement(lstCourseModel
				.getElementAt(lstAvailableCourses.getSelectedIndex()));
		lstCourseModel.remove(lstAvailableCourses.getSelectedIndex());

	}

	private void removeCourse(int index) {
		ArrayAvailableCourse.put(arraySelectedCourse.get(index),
				arraySelectedCourse.get(index));
		arraySelectedCourse.remove(index);
		lstCourseModel.addElement(lstSelectedCourseModel
				.getElementAt(lstSelectedCourses.getSelectedIndex()));
		lstSelectedCourseModel.remove(lstSelectedCourses.getSelectedIndex());

	}

	// arrayCourse
	public void setcourse(ArrayList<Course> arrayList) {

		arrayCourse = new HashMap<Integer, Course>();
		ArrayAvailableCourse = new HashMap<Integer, Integer>();
		arraySelectedCourse = new HashMap<Integer, Integer>();

		lstCourseModel.removeAllElements();
		lstSelectedCourseModel.removeAllElements();
		for (int i = 0; i < arrayList.size(); i++) {
			arrayCourse.put(arrayList.get(i).getCourseID(), arrayList.get(i));
			lstCourseModel.addElement(arrayList.get(i).getCourseID() + ":"
					+ arrayList.get(i).getDescription());
		
			ArrayAvailableCourse.put(arrayList.get(i).getCourseID(), arrayList
					.get(i).getCourseID());
		}
	}

	private void resetListslec() {
		arraySelectedCourse.clear();
		ArrayAvailableCourse.clear();
		lstCourseModel.removeAllElements();
		lstSelectedCourseModel.removeAllElements();

	}

}