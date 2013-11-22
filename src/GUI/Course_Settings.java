package GUI;

import java.awt.EventQueue;
import java.util.Iterator;
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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.UIManager;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLayeredPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;


import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollBar;

import net.miginfocom.swing.MigLayout;

import javax.swing.ScrollPaneConstants;

import entities.Course;
import entities.Faculty;
import entities.Lecturer;
import Controllers.LecturerController;
import Controllers.ManagerController;
/*/JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
lblTimeTableSchedualing.setLocation(new Point(50, 0));
lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
/*/



public class Course_Settings extends JPanel implements ActionListener,
ListSelectionListener, KeyListener {
	private JTextField txtCourseSttings;
	private JTable table;
	private JButton btnSaveChanges; 
	private JButton btnBackToMainMenu;
	private JComboBox cmbxFaculty ;
	private  JScrollPane scroll;
	private Component horizontalStrut_2;
	private Component horizontalStrut_1;
	public JPanel PNL_Main ;
	private LecturerController lec;
	private ManagerController manager;
	private Object[][] tableData;
	private TableModel lstModel;
	private ArrayList<Faculty> arrayFaculty;
	private ArrayList<Faculty> arrayCourseFaculty;
	private Map<Integer, Course> CoursePerFuculty ;
	
	private Map<Integer, ArrayList<Course>> CoursePerFuculty2 ;
	
	public Course_Settings(ManagerController mng) {

		super();
		this.manager=mng;
		initialize();
		
	
	}
	
	
		private void initialize() {
	
	
		
		pnl();
		
		
		PNL_Main.add(GETbtnSaveChanges());
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtCourseSttings());
		PNL_Main.add(horizontalStrut2());
		PNL_Main.add(horizontalStrut1());
		PNL_Main.add(GETscroll());
	  	PNL_Main.add(GETcmbxFaculty());
		
	//	PNL_Main.add(table);
	}
	
	private void pnl() {
		
		PNL_Main = new JPanel();
		PNL_Main.setBorder(new EmptyBorder(0, 0, 0, 0));
	
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
		PNL_Main.setLayout(null);
			
		}
	private JComboBox GETcmbxFaculty() {
		if (cmbxFaculty==null){
		cmbxFaculty = new JComboBox();
	  	cmbxFaculty.setBounds(10, 53, 724, 20);
	  	cmbxFaculty.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
	  	cmbxFaculty.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	cmbxFaculty.setToolTipText("Edit class list");
	  	cmbxFaculty.setMaximumRowCount(52);}
		return cmbxFaculty;
	}
	private JScrollPane GETscroll() {
		if (scroll==null){
		 scroll = new JScrollPane(GETtable());
		  scroll.setBounds(10, 103, 724, 168);}
		return scroll;
	}
	private JTable GETtable() {
		if (table==null){
			
			Object[][] tableData={
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null}};
			
			String columns[]={"# of student","course ID","course description"};
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(SystemColor.inactiveCaption);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		//table.setModel( lstModel);
		
		
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"# of student", "course ID", "course description"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.setSurrendersFocusOnKeystroke(true);
		
		table.setToolTipText("");
		table.setBounds(25, 110, 600, 300);
		}
		return table;
	}
	private JTextField GETtxtCourseSttings() {
		if (txtCourseSttings==null){
		txtCourseSttings = new JTextField();
		txtCourseSttings.setEditable(false);
		txtCourseSttings.setBounds(0, 4, 774, 40);
		txtCourseSttings.setText("Course sttings");
		txtCourseSttings.setHorizontalAlignment(SwingConstants.CENTER);
		txtCourseSttings.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCourseSttings.setColumns(10);
		txtCourseSttings.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtCourseSttings.setBackground(new Color(176, 224, 230));}
		return txtCourseSttings;
	}
	private JButton GETbtnBackToMainMenu() {
		if (btnBackToMainMenu==null){
		btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setBounds(462, 434, 160, 29);
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBackToMainMenu.addActionListener(this);}
		return btnBackToMainMenu;
	}
	private JButton GETbtnSaveChanges() {
		if (btnSaveChanges==null){
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setBounds(151, 434, 160, 29);
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveChanges.addActionListener(this);
		}
		return btnSaveChanges;
	}
	
	private Component horizontalStrut1() {
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 80, 774, 12);
		return horizontalStrut_1;
	}
	private Component horizontalStrut2() {
		 horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(5, 424, 759, 5);
		horizontalStrut_2.setBackground(Color.BLACK);
		return horizontalStrut_2;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSaveChanges) {
			
			//manager.SaveCourse();
		}	
		if (e.getSource() == btnBackToMainMenu) {
			manager.BacktoMainMenu(this.PNL_Main);
		}
		if (e.getSource() ==cmbxFaculty) {
			GetCourseTable(cmbxFaculty.getSelectedIndex());
			//manager.LoadFacultyCourse(cmbxFaculty.getSelectedIndex());
			
			
		}	
		
		
		
	}
	private void GetCourseTable(int index) {
		
		//for (int i=0;i<CoursePerFuculty.size();i++){
		
		Iterator<Course> itr = CoursePerFuculty.values().iterator();
		while (itr.hasNext()) {
			int tempID = itr.next().getFaculty();
			
			
		}
		
		
	}
/*
Iterator<Lecturer> itr = ArrayLecturers.values().iterator();
while (itr.hasNext()) {
	int tempID = itr.next().getID();
	if (!arraySelectedLecturers.containsKey(tempID)) {
		ArrayAvailableLecturers.put(tempID, tempID);
		lstCLecturersModel.addElement(tempID + ":"
				+ ArrayLecturers.get(tempID).getName());
	}
*/

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
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void setFaculty(ArrayList<Faculty> arrayList) {
		arrayFaculty = arrayList;
		cmbxFaculty.removeAll();
		for (int i = 0; i < arrayFaculty.size(); i++) {
			cmbxFaculty.addItem(arrayFaculty.get(i).getFacultyNum() + ":"
					+ arrayFaculty.get(i).getFaculty());
		}

}


	public void setCourse(ArrayList<Course> course) {
		for(int i=0;i<course.size();i++){
			CoursePerFuculty.put(course.get(i).getFaculty(), course.get(i));
			CoursePerFuculty2.put()
			
		}
	}}
