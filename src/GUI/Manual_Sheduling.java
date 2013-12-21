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
import javax.swing.table.DefaultTableModel;

import Controllers.ManagerController;


public class Manual_Sheduling extends JPanel implements ActionListener,
ListSelectionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtLecturerPreferences;
	private JTable tablemanual;
	
	private JComboBox cmbxFaculty;
	private JComboBox cmbBxCourse;
	private JComboBox cmbBxType;
	private JComboBox cmbBxLecturer;
	private JComboBox cmbBxClass;
	private JComboBox cmbBxSemster;
	
	private JButton btnSaveChanges;
	private JButton btnBackToMainMenu;
	private JButton btnSet;
	private JButton btnClear;
	private JButton btnDelete;
	private JLabel COURSE;
	private JLabel lblType;
	private JLabel lblLecturer ;
	private JLabel lblClass;
	private JPanel panel;
	private JLabel lblFaculty;
	private   JLabel lblSemester;

	public JPanel PNL_Main;
	private ManagerController manager;
	
	
	static Color[] colors = {Color.BLUE, Color.GRAY, Color.RED};
	static String[] strings = {"Test1", "Test2", "Test3"};
	
	/**
	 * Create the panel.
	 */
	public Manual_Sheduling(ManagerController mng) {
		
	
		super();
		
		this.manager=mng;
		initialize();}
	
	
		private void initialize() {
pnl();
		
		
		PNL_Main.add(GETbtnSaveChanges());
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
		PNL_Main.add(GETcmbBxClass());
		PNL_Main.add(GETlblClass());
		PNL_Main.add(GETbtnSet());
		PNL_Main.add(GETbtnClear());
		PNL_Main.add(GETbtnDelete());
		PNL_Main.add(GETpanel());
		PNL_Main.add(GETlblFaculty());
		PNL_Main.add(GETlblSemester());
		PNL_Main.add(GETcmbBxSemster());
		
		horizontalStrut();
		
	}

	private JLabel GETlblFaculty() {
		 lblFaculty= new JLabel("Faculty:");
		  lblFaculty.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblFaculty.setBounds(10, 55, 65, 14);
		return lblFaculty;
	}

	private JComboBox GETcmbBxSemster() {
		 cmbBxSemster = new JComboBox();
		  cmbBxSemster.setToolTipText("cmbBxSemster");
		  cmbBxSemster.setBounds(601, 55, 46, 20);
		return cmbBxSemster;
	}

	private JLabel GETlblSemester() {
		 lblSemester= new JLabel("semester:");
		  lblSemester.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblSemester.setBounds(534, 55, 71, 14);
		return lblSemester;
	}

	private JPanel GETpanel() {
		  panel = new JPanel();
		  panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		  panel.setBackground(SystemColor.activeCaption);
		  panel.setBounds(531, 103, 203, 260);
		return panel;
	}

	private JButton GETbtnDelete() {
		btnDelete = new JButton("delete");
		  btnDelete.setBounds(600, 330, 80, 23);
		 
		return btnDelete;
	}

	private JButton GETbtnClear() {
		 btnClear = new JButton("Clear");
		  btnClear.setBounds(600, 300, 80, 23);
		 
		return btnClear;
	}

	private JButton GETbtnSet() {
		btnSet = new JButton("Set");
		  btnSet.setBounds(600, 270, 80, 23);
		 
		return btnSet;
	}

	private JLabel GETlblClass() {
		lblClass = new JLabel("Class");
		  lblClass.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblClass.setBounds(540, 240, 46, 14);
		return lblClass;
	}

	private JComboBox GETcmbBxClass() {
		cmbBxClass = new JComboBox();
		  cmbBxClass.setToolTipText("cmbBxLecturer");
		  cmbBxClass.setBounds(600, 240, 120, 22);
		return cmbBxClass;
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
		cmbBxType = new JComboBox();
		  cmbBxType.setBounds(600, 160, 120, 22);
		
		return cmbBxType;
	}

	private JLabel GETlblType() {
		lblType= new JLabel("Type: ");
		  lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblType.setBounds(540, 160, 45, 15);
		return lblType;
	}

	private JComboBox GETcmbBxCourse() {
		 cmbBxCourse = new JComboBox();
		  cmbBxCourse.setForeground(SystemColor.info);
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
		tablemanual = new JTable();
		tablemanual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablemanual.setToolTipText("Manual sheduling table ");
		tablemanual.setFillsViewportHeight(true);
		tablemanual.setSurrendersFocusOnKeystroke(true);
		tablemanual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablemanual.setBackground(SystemColor.inactiveCaption);
		tablemanual.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablemanual.setModel(new DefaultTableModel(
			new Object[][] {
				{"8:00", null, null, null, null, null, null},
				{"9:00", null, null, null, null, null, null},
				{"10:00", null, null, null, null, null, null},
				{"11:00", null, null, null, null, null, null},
				{"12:00", null, null, null, null, null, null},
				{"13:00", null, null, null, null, null, null},
				{"14:00", null, null, null, null, null, null},
				{"15:00", null, null, null, null, null, null},
				{"16:00", null, null, null, null, null, null},
				{"17:00", null, null, null, null, null, null},
				{"18:00", null, null, null, null, null, null},
				{"19:00", null, null, null, null, null, null},
				{"20:00", null, null, null, null, null, null},
				{"21:00", null, null, null, null, null, null},
			},
			new String[] {
				"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablemanual.getColumnModel().getColumn(0).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(1).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(1).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(2).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(2).setMinWidth(25);
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
		
	}

	private JTextField GETtxtLecturerPreferences() {
		txtLecturerPreferences = new JTextField();
		txtLecturerPreferences.setDisabledTextColor(SystemColor.desktop);
		txtLecturerPreferences.setEnabled(false);
		txtLecturerPreferences.setBounds(0, 4, 774, 39);
		txtLecturerPreferences.setText("Manual sheduling");
		txtLecturerPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerPreferences.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerPreferences.setColumns(10);
		txtLecturerPreferences.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerPreferences.setBackground(new Color(176, 224, 230));
		return txtLecturerPreferences;
	}

	private JButton GETbtnBackToMainMenu() {
		btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setBounds(462, 434, 160, 29);
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		return btnBackToMainMenu;
	}

	private JButton GETbtnSaveChanges() {
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setBounds(151, 434, 160, 29);
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		return btnSaveChanges;
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
	  	cmbxFaculty.setModel(new DefaultComboBoxModel(new String[] {"choose Faculty"}));
	  	cmbxFaculty.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	cmbxFaculty.setToolTipText("Faculty list");
	  	cmbxFaculty.setMaximumRowCount(52);
		return cmbxFaculty;
	}

	
	public void AddFaculty(ArrayList<String> FacultyList	){
		
	}
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSaveChanges) {}
		if (e.getSource() == btnBackToMainMenu) {
			manager.BacktoMainMenu(this.PNL_Main);
		}
		if (e.getSource() == btnSet) {}
		if (e.getSource() == btnClear) {}
		if (e.getSource() == btnDelete) {}
		if (e.getSource()== cmbxFaculty){}
		

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
		btnSaveChanges.addActionListener(this);
		 btnSet.addActionListener(this);		
		 btnDelete.addActionListener(this);
		 btnClear.addActionListener(this);
		
	}
	
	
	
	}

