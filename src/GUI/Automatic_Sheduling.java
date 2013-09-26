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
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.LecturerController;
import Controllers.ManagerController;


public class Automatic_Sheduling extends JPanel implements ActionListener,
ListSelectionListener, KeyListener {

	private Component horizontalStrut_2;
	private Component horizontalStrut_1;
	private static final long serialVersionUID = 1L;
	private JTextField txtLecturerPreferences;
	private JTable tableAutonatic;
	public JPanel PNL_Main;
	private JLabel lblSemester;
	private JScrollPane scroll;

	private JComboBox cmbxFaculty;
	private JComboBox cmbBxSemster;
	
	private JList lstTimeTable ;
	private JLabel lblOptionalTimeTables;
	private JButton btnSaveChanges;
	private JButton btnBackToMainMenu ;
	private JButton btnPreview;
	private JPanel panel;
	private JLabel lblTimeTableSchedualing;
	private JLabel lblFaculty;
	private LecturerController lec;
	private ManagerController manger;
	static Color[] colors = {Color.BLUE, Color.GRAY, Color.RED};
	static String[] strings = {"Test1", "Test2", "Test3"};
	
	/**
	 * Create the panel.
	 */
	
	/*/	
	lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
	lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
	lblTimeTableSchedualing.setLocation(new Point(50, 0));
	lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
	lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
	lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

/*/	
	public Automatic_Sheduling(ManagerController mng) {
	
		super();
		this.manager=mng;
		initialize();}
	
	
		private void initialize() {
		// TODO Auto-generated method stub
		
		pnl();
		PNL_Main.add(GETbtnSaveChanges());
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtLecturerPreferences());
			
		PNL_Main.add(horizontalStrut1());
		PNL_Main.add(horizontalStrut2());
		PNL_Main.add(GETcmbxFaculty());
		PNL_Main.add(GETscroll());
		PNL_Main.add(GETlblFaculty());
		PNL_Main.add(GETlblSemester());
		PNL_Main.add(GETcmbBxSemster());
		PNL_Main.add(GETlblOptionalTimeTables());
		PNL_Main.add(GETlstTimeTable());
		PNL_Main.add(GETbtnPreview());
		PNL_Main.add(GETpanel());
		
		
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
	
	
	private JPanel GETpanel() {
		if (panel == null){
		 panel = new JPanel();
		  panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		  panel.setBackground(new Color(135, 206, 235));
		  panel.setBounds(540, 103, 201, 249);}
		return panel;
	}



	private JButton GETbtnPreview() {
		if (btnPreview == null){
			btnPreview = new JButton("Preview");
			btnPreview.addActionListener(this);
			btnPreview.setBounds(560, 313, 89, 23);}
		return btnPreview;
	}



	private JLabel GETlblOptionalTimeTables() {
		if (lblOptionalTimeTables == null){
			lblOptionalTimeTables = new JLabel("Optional time tables :");
			 lblOptionalTimeTables.setFont(new Font("Tahoma", Font.BOLD, 12));
			 lblOptionalTimeTables.setBounds(560, 123, 170, 14);
		  }
		return lblOptionalTimeTables;
	}



	private JList GETlstTimeTable() {
		if (lstTimeTable == null){
			lstTimeTable = new JList();
		  lstTimeTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		  lstTimeTable.setBackground(new Color(175, 238, 238));
		  lstTimeTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lstTimeTable.setBounds(559, 149, 102, 142);
		  }
		return lstTimeTable;
	}



	private JComboBox GETcmbBxSemster() {
		
		if (lblSemester == null){
			cmbBxSemster = new JComboBox();
		
		  cmbBxSemster.setToolTipText("cmbBxSemster");
		  cmbBxSemster.setBounds(601, 55, 46, 20);}
		return cmbBxSemster;
	}



	private Component GETlblSemester() {
		if (lblSemester == null){
		lblSemester = new JLabel("semester:");
		  lblSemester.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblSemester.setBounds(534, 55, 71, 14);
		  }
		return lblSemester;
	}



	private JLabel GETlblFaculty() {
		
		if (lblFaculty == null){
			lblFaculty = new JLabel("Faculty:");
			lblFaculty.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblFaculty.setBounds(10, 55, 65, 14);}
		return lblFaculty;
	}



	private JComboBox GETcmbxFaculty() {
		if (cmbxFaculty == null){
		cmbxFaculty = new JComboBox();
	  	cmbxFaculty.setBounds(63, 55, 461, 20);
	  	cmbxFaculty.setModel(new DefaultComboBoxModel(new String[] {"choose Faculty"}));
	  	cmbxFaculty.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	cmbxFaculty.setToolTipText("Faculty list");
	  	cmbxFaculty.setMaximumRowCount(52);}
		return cmbxFaculty;
	}



	private JScrollPane GETscroll() {
		if (scroll == null){
			scroll = new JScrollPane(GETtableAutonatic());
			scroll.setEnabled(false);
			scroll.setBounds(10, 100, 511, 249);}
		return scroll;
	}

	private JTable GETtableAutonatic() {
		if (tableAutonatic==null){
		tableAutonatic = new JTable();
		tableAutonatic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableAutonatic.setToolTipText("Manual auto sheduling table ");
		tableAutonatic.setFillsViewportHeight(true);
		tableAutonatic.setSurrendersFocusOnKeystroke(true);
		tableAutonatic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAutonatic.setBackground(SystemColor.activeCaptionText);
		tableAutonatic.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableAutonatic.setModel(new DefaultTableModel(
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableAutonatic.getColumnModel().getColumn(0).setPreferredWidth(65);
		tableAutonatic.getColumnModel().getColumn(1).setPreferredWidth(65);
		tableAutonatic.getColumnModel().getColumn(1).setMinWidth(25);
		tableAutonatic.getColumnModel().getColumn(2).setPreferredWidth(65);
		tableAutonatic.getColumnModel().getColumn(2).setMinWidth(25);
		tableAutonatic.getColumnModel().getColumn(3).setPreferredWidth(65);
		tableAutonatic.getColumnModel().getColumn(3).setMinWidth(25);
		tableAutonatic.getColumnModel().getColumn(4).setResizable(false);
		tableAutonatic.getColumnModel().getColumn(4).setPreferredWidth(65);
		tableAutonatic.getColumnModel().getColumn(4).setMinWidth(25);
		tableAutonatic.getColumnModel().getColumn(5).setResizable(false);
		tableAutonatic.getColumnModel().getColumn(5).setPreferredWidth(65);
		tableAutonatic.getColumnModel().getColumn(5).setMinWidth(25);
		tableAutonatic.getColumnModel().getColumn(6).setResizable(false);
		tableAutonatic.getColumnModel().getColumn(6).setPreferredWidth(65);
		tableAutonatic.getColumnModel().getColumn(6).setMinWidth(25);
		
	

		tableAutonatic.setColumnSelectionAllowed(true);
		tableAutonatic.setCellSelectionEnabled(true);
		tableAutonatic.setBounds(35, 127, 600, 224);
		}
		return tableAutonatic;
	}
///////////////////all class////	
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

///////////////////////////////////

	private JTextField GETtxtLecturerPreferences() {
		if (txtLecturerPreferences == null) {
		txtLecturerPreferences = new JTextField();
		txtLecturerPreferences.setBounds(0, 4, 774, 31);
		txtLecturerPreferences.setText("Automatic Sheduling is running");
		txtLecturerPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerPreferences.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerPreferences.setColumns(10);
		txtLecturerPreferences.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerPreferences.setBackground(new Color(176, 224, 230));
		}
		return txtLecturerPreferences;
	}


	private JButton GETbtnBackToMainMenu() {
		if (btnBackToMainMenu == null) {
			btnBackToMainMenu = new JButton("Discard");
			btnBackToMainMenu.setBounds(484, 434, 85, 29);
			btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnBackToMainMenu.addActionListener(this) ;
		}
		
		return btnBackToMainMenu;
	}
	


	private JButton GETbtnSaveChanges() {
		// TODO Auto-generated method stub
		if (btnSaveChanges == null) {
			btnSaveChanges = new JButton("Save");
			btnSaveChanges.setBounds(205, 434, 74, 29);
			btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnSaveChanges.addActionListener(this);
		}
		return btnSaveChanges;
	}


	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSaveChanges) {}	
		if (e.getSource() == btnBackToMainMenu) {}
		if (e.getSource() == btnPreview) {}
		
		
	
		
		
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
	
	}


