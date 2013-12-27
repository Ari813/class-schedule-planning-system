package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.print.DocFlavor.STRING;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import entities.Class;
import entities.Course;
import entities.Faculty;
import entities.Lecturer;
import Controllers.ManagerController;

import javax.swing.JTree;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import common.Settings;

import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.Label;
import java.awt.List;


public class Manual_Sheduling extends JPanel implements ActionListener,
ListSelectionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtLecturerPreferences;
	private JTable tablemanual;
	
	private JComboBox cmbxFaculty;
	private JComboBox cmbBxCourse;
	private JComboBox cmbBxClass;
	private JComboBox cmbBxLecturer;
	private JButton btnBackToMainMenu;
	private JButton btnSet;
	private JButton btnClear;
	private JLabel COURSE;
	private JLabel lblType;
	private JLabel lblLecturer ;
	private JLabel lblFaculty;
	private   JLabel lblSemester;
	private JButton start;
	public JPanel PNL_Main;
	private ManagerController manager;
	
	//public idcalsss[][][] ShedulingTable;
	//public Arrays[][][]arrays=new arr;
	
	
	private ArrayList<Class> arrayClasses;
	private ArrayList<Faculty> ManualArrayFaculty;
	
	
	public idcalsss id_calsss;
	public ArrayList<idcalsss> array_id_calsss;
	//public Map<Integer, ArrayList<idcalsss>> semesterMap;
	public Map<String, Map<Integer, Map<Integer, idcalsss>>> FacultyMap;
	public int semid;
	public int facid;
	
	
	static Color[] colors = {Color.BLUE, Color.GRAY, Color.RED};
	static String[] strings = {"Test1", "Test2", "Test3"};
	
	private ArrayList<Course> arraycourse;
	private ArrayList<Lecturer> arrayLecturers;
	
	private TableModel lstModel;
	private Object[][] tableData={
			{"8:00-9:00", null, null, null, null, null, null},
			{"9:00-10:00", null, null, null, null, null, null},
			{"10:00-11:00", null, null, null, null, null, null},
			{"11:00-12:00", null, null, null, null, null, null},
			{"12:00-13:00", null, null, null, null, null, null},
			{"13:00-14:00", null, null, null, null, null, null},
			{"14:00-15:00", null, null, null, null, null, null},
			{"15:00-16:00", null, null, null, null, null, null},
			{"16:00-17:00", null, null, null, null, null, null},
			{"17:00-18:00", null, null, null, null, null, null},
			{"18:00-19:00", null, null, null, null, null, null},
			{"19:00-20:00", null, null, null, null, null, null},
			{"20:00-21:00", null, null, null, null, null, null},
			{"21:00-22:00", null, null, null, null, null, null},
		};
	private	String columnNames[]={"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	private JSpinner semesterSpinner;
	
	
	
	/**
	 * Create the panel.
	 */
	public Manual_Sheduling(ManagerController mng) {
		
	
		super();
		
		this.manager=mng;
		initialize();}
	
	
		private void initialize() {
pnl();
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtLecturerPreferences());
	   	PNL_Main.add(GETcmbxFaculty());
		PNL_Main.add(GETscroll());
		PNL_Main.add(GETCOURSELBL());
		PNL_Main.add(GETcmbBxCourse());
		PNL_Main.add(GETlblType());
		PNL_Main.add(GETcmbBxType());
		PNL_Main.add(GETlblLecturer());
		PNL_Main.add(GETcmbBxLecturer());
		PNL_Main.add(GETbtnSet());
		PNL_Main.add(GETbtnClear());
		PNL_Main.add(GETlblFaculty());
		PNL_Main.add(GETlblSemester());
		
		horizontalStrut();
		
	}

	private JLabel GETlblFaculty() {
		 lblFaculty= new JLabel("Faculty:");
		  lblFaculty.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblFaculty.setBounds(10, 55, 65, 14);
		return lblFaculty;
	}



	private JLabel GETlblSemester() {
		 lblSemester= new JLabel("semester:");
		  lblSemester.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblSemester.setBounds(534, 55, 71, 14);
		return lblSemester;
	}

	

	

	private JButton GETbtnClear() {
		 btnClear = new JButton("Clear");
		  btnClear.setBounds(672, 261, 65, 23);
		 
		return btnClear;
	}

	private JButton GETbtnSet() {
		btnSet = new JButton("Set");
		  btnSet.setBounds(567, 261, 82, 23);
		 
		return btnSet;
	}



	private JComboBox GETcmbBxLecturer() {
		 cmbBxLecturer = new JComboBox();
		  cmbBxLecturer.setToolTipText("cmbBxLecturer");
		  cmbBxLecturer.setBounds(600, 200, 120, 22);
		return cmbBxLecturer;
	}

	private JLabel GETlblLecturer() {
		 lblLecturer = new JLabel("Lecturer:");
		  lblLecturer.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblLecturer.setBounds(540, 200, 66, 14);
		return lblLecturer;
	}

	private JComboBox GETcmbBxType() {
		cmbBxClass = new JComboBox();
		  cmbBxClass.setBounds(600, 160, 120, 22);
		
		return cmbBxClass;
	}

	private JLabel GETlblType() {
		lblType= new JLabel("class");
		  lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblType.setBounds(540, 160, 45, 15);
		return lblType;
	}

	private JComboBox GETcmbBxCourse() {
		 cmbBxCourse = new JComboBox();
		  cmbBxCourse.setForeground(new Color(0, 0, 0));
		  cmbBxCourse.setToolTipText("cmbBxCourse");
		  cmbBxCourse.setBounds(600, 120, 120, 20);
		return cmbBxCourse;
	}

	private JLabel GETCOURSELBL() {
		 COURSE = new JLabel("Course:");
		  COURSE.setFont(new Font("Tahoma", Font.BOLD, 12));
		  COURSE.setBounds(540, 120, 65, 15);
		return COURSE;
	}

	private JScrollPane GETscroll() {
		 JScrollPane scroll = new JScrollPane(GETtablemanual());
		  scroll.setEnabled(false);
		  scroll.setBounds(10, 100, 511, 249);
		return scroll;
	}

	private JTable GETtablemanual() {
		
		
		lstModel= new AbstractTableModel() {
			public String getColumnName(int col) {
		        return columnNames[col].toString();
		    }
		    public int getRowCount() { return tableData.length; }
		    public int getColumnCount() { return columnNames.length; }
		    public Object getValueAt(int row, int col) {
		        return tableData[row][col];
		    }
		    public boolean isCellEditable(int row, int col)
		        {             return false;
		                   		        
		         }
		    public void setValueAt(Object value, int row, int col) {
		    	tableData[row][col] = value;
		        fireTableCellUpdated(row, col);
		    }
		   
		    
		  
		};
		
		tablemanual = new JTable(tableData,columnNames);
		tablemanual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablemanual.setToolTipText("Manual sheduling table ");
		tablemanual.setFillsViewportHeight(true);
		tablemanual.setSurrendersFocusOnKeystroke(true);
		tablemanual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablemanual.setBackground(SystemColor.inactiveCaption);
		tablemanual.setBorder(new LineBorder(new Color(0, 0, 0)));
	
		
		
		tablemanual.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablemanual.getColumnModel().getColumn(1).setResizable(false);
		tablemanual.getColumnModel().getColumn(1).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(1).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(2).setResizable(false);
		tablemanual.getColumnModel().getColumn(2).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(2).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(3).setResizable(false);
		tablemanual.getColumnModel().getColumn(3).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(3).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(4).setResizable(false);
		tablemanual.getColumnModel().getColumn(4).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(4).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(5).setResizable(false);
		tablemanual.getColumnModel().getColumn(5).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(5).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(6).setResizable(false);
		tablemanual.getColumnModel().getColumn(6).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(6).setMinWidth(25);
		
		//tablemanual.getColumnModel().getColumn(1).setPreferredWidth(78);

		tablemanual.setColumnSelectionAllowed(true);
		tablemanual.setCellSelectionEnabled(true);
		tablemanual.setBounds(35, 127, 600, 224);
		tablemanual.setModel(lstModel);
		return tablemanual;
	}

	private void horizontalStrut() {
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 80, 774, 12);
		PNL_Main.add(horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(5, 424, 759, 5);
		horizontalStrut_2.setBackground(Color.BLACK);
		PNL_Main.add(horizontalStrut_2);
		
		start = new JButton("start scheduling");
		start.addActionListener(this); 
		
		start.setBackground(new Color(0, 255, 204));
		start.setFont(new Font("Trajan Pro", Font.BOLD, 11));
		start.setBounds(73, 434, 285, 28);
		PNL_Main.add(start);
		
		semesterSpinner = new JSpinner();
		semesterSpinner.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		semesterSpinner.setBounds(600, 54, 29, 20);
		PNL_Main.add(semesterSpinner);
		
		
		
	}

	private JTextField GETtxtLecturerPreferences() {
		txtLecturerPreferences = new JTextField();
		txtLecturerPreferences.setDisabledTextColor(SystemColor.desktop);
		txtLecturerPreferences.setEnabled(false);
		txtLecturerPreferences.setBounds(0, 4, 774, 39);
		txtLecturerPreferences.setText("Manual scheduling");
		txtLecturerPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerPreferences.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerPreferences.setColumns(10);
		txtLecturerPreferences.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerPreferences.setBackground(new Color(176, 224, 230));
		return txtLecturerPreferences;
	}

	private JButton GETbtnBackToMainMenu() {
		btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setBounds(453, 434, 169, 29);
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		return btnBackToMainMenu;
	}

	

	private void pnl() {
		PNL_Main = new JPanel();
		PNL_Main.setBorder(new EmptyBorder(0, 0, 0, 0));
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
		PNL_Main.setLayout(null);
		
	}

	private JComboBox GETcmbxFaculty() {
		cmbxFaculty = new JComboBox();
	  	cmbxFaculty.setBounds(63, 55, 461, 20);
	  	cmbxFaculty.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	cmbxFaculty.setToolTipText("Faculty list");
	  	cmbxFaculty.setMaximumRowCount(52);
		return cmbxFaculty;
	}

	
	public void AddFaculty(ArrayList<String> FacultyList	){
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start ){
			
			manager.Load_Automatic_Sheduling(this.PNL_Main);
		}
	
		if (e.getSource() == btnBackToMainMenu) {
			manager.BacktoMainMenu(this.PNL_Main);
		}
		if (e.getSource() == cmbxFaculty) {
			semesterSpinner.setValue(1);
		
		}
		
		if (e.getSource() == btnSet) {
			int Row=tablemanual.getSelectedRow();
			int Column=tablemanual.getSelectedColumn();
			int ColumnRow=Row+(Column-1)*Settings.dailyHours;
		
			if (Column>=1){
			id_calsss=new idcalsss(arrayLecturers.get(cmbBxLecturer.getSelectedIndex()).getID(),arraycourse.get(cmbBxCourse.getSelectedIndex()).getCourseID(),arrayClasses.get(cmbBxClass.getSelectedIndex()).getClassID()
					,ColumnRow);
			
			tablemanual.getModel().setValueAt(id_calsss.getClassid() + ":" +id_calsss.getCousreid() + ":" +id_calsss.getLecid() ,Row,Column );
			
			
		if (FacultyMap==null)
			FacultyMap= new HashMap<String,Map<Integer, Map<Integer, idcalsss>>>();
			String fac=ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFaculty();
			int	semester=(int)semesterSpinner.getValue();
			if (FacultyMap.containsKey(fac)){
				if (FacultyMap.get(fac).containsKey(semester)){
					if (FacultyMap.get(fac).get(semester).containsKey(ColumnRow)){
						FacultyMap.get(fac).get(semester).get(ColumnRow).setall(id_calsss);
						System.out.print("overlap");//Only switch
					}else{
						FacultyMap.get(fac).get(semester).put(ColumnRow, id_calsss);
					}				
					
				}else
					{
						Map<Integer, idcalsss> ColumnRowMap =new HashMap<Integer, idcalsss>();
						ColumnRowMap.put(ColumnRow,id_calsss);
						FacultyMap.get(fac).put(semester, ColumnRowMap);
											
					}
			}else{
							
				Map<Integer, idcalsss> ColumnRowMap =new HashMap<Integer, idcalsss>();
				ColumnRowMap.put(ColumnRow,id_calsss);
				Map<Integer, Map<Integer, idcalsss>> semesterMap =new HashMap<Integer, Map<Integer, idcalsss>>();
				semesterMap.put(semester, ColumnRowMap);
				FacultyMap.put(fac, semesterMap);
			}
			}
		}
		//	lstModel.getValueAt(arg0, arg1)
			//tablemanual.getSelectedRow();
		//	tablemanual.getColumnModel().getColumn(1).setCellEditor("111");
			//tablemanual.getModel().setValueAt(amount, table.getSelectedRow(), 4);		
			
			
		if (e.getSource() == btnClear) {
			if (tablemanual.getSelectedColumn()>=1){
				String fac=ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFaculty();
				int	semester=(int)semesterSpinner.getValue();
				int Row=tablemanual.getSelectedRow();
				int Column=tablemanual.getSelectedColumn();
				int ColumnRow=Row+(Column-1)*Settings.dailyHours;
				if (FacultyMap!=null){
					if (FacultyMap.get(fac).get(semester).containsKey(ColumnRow))
					FacultyMap.get(fac).get(semester).remove(ColumnRow);
							
				}
			
			tablemanual.getModel().setValueAt("",tablemanual.getSelectedRow(), tablemanual.getSelectedColumn());
			
			
			}
			
		}


	}

	


	private int dateToNum(String value) {
int i=0;
switch (value) {
case "sunday":
	i=1;
	break;
case "Monday":
	i=2;
	break;
case "Tuesday":
	i=3;
	break;
case "Wednesday":
	i=4;
	break;
case "Thursday":
	i=5;
	break;
case "Friday":
	i=6;
	break;
default:
	break;
	
}
return i;	
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


	public void addActions() {
		// TODO Auto-generated method stub
		btnBackToMainMenu.addActionListener(this);
		
		 btnSet.addActionListener(this);		
		
		 btnClear.addActionListener(this);
		 start.addActionListener(this);
		 cmbxFaculty.addActionListener(this);
		 cmbBxCourse.addActionListener(this);
		 cmbBxClass.addActionListener(this);
		 semesterSpinner.setVisible(false);
		 semesterSpinner.setVisible(true);
	}


	public void setFaculty(ArrayList<Faculty> faculty) {
		ManualArrayFaculty = faculty;
		cmbxFaculty.removeAll();
		for (int i = 0; i < ManualArrayFaculty.size(); i++) {
			cmbxFaculty.addItem(ManualArrayFaculty.get(i).getFacultyNum() + ":"
					+ ManualArrayFaculty.get(i).getFaculty());
		
	}}


	public void setClasses(ArrayList<Class> getClasses) {
		// TODO Auto-generated method stub
		arrayClasses = getClasses;
		cmbBxClass.removeAll();
		for (int i = 0; i < arrayClasses.size(); i++) {
			cmbBxClass.addItem(arrayClasses.get(i).getClassID() + ":"
					+ arrayClasses.get(i).getDescription());
		}
	}


	public void setCourse(ArrayList<Course> course) {
		// TODO Auto-generated method stub
		arraycourse = course;
		for (int i = 0; i < arraycourse.size(); i++) {
			cmbBxCourse.addItem(arraycourse.get(i).getCourseID() + ":"
					+ arraycourse.get(i).getDescription());}
	}


	public void setLec(ArrayList<Lecturer> availableLecturers) {
		// TODO Auto-generated method stub
		arrayLecturers = availableLecturers;
		cmbBxLecturer.removeAllItems();
		for (int i = 0; i < arrayLecturers.size(); i++) {
			cmbBxLecturer.addItem(arrayLecturers.get(i).getID() + ":"
					+ arrayLecturers.get(i).getName());
			
		}
	}
	}

