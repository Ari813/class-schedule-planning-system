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


public class Manual_Sheduling extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtLecturerPreferences;
	private JTable tablemanual;
	private JButton btnSaveChanges;
	private JButton btnBackToMainMenu;
	private JComboBox cmbxFaculty;
	private JComboBox cmbBxCourse;
	private JComboBox cmbBxType;
	private JComboBox cmbBxLecturer;
	private JComboBox cmbBxClass;
	private JButton btnSet;
	private JButton btnClear;
	private JButton btnDelete;
	private JComboBox cmbBxSemster;
	
	public JPanel PNL_Main;
	static Color[] colors = {Color.BLUE, Color.GRAY, Color.RED};
	static String[] strings = {"Test1", "Test2", "Test3"};
	
	/**
	 * Create the panel.
	 */
	public Manual_Sheduling() {
		JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
		lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeTableSchedualing.setLocation(new Point(50, 0));
		lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
		lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	
		
		PNL_Main = new JPanel();
		PNL_Main.setBorder(new EmptyBorder(0, 0, 0, 0));
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
	;
		PNL_Main.setLayout(null);
		
		
		
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setBounds(205, 434, 74, 29);
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PNL_Main.add(btnSaveChanges);
		
		btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setBounds(484, 434, 85, 29);
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBackToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PNL_Main.add(btnBackToMainMenu);
		
		txtLecturerPreferences = new JTextField();
		txtLecturerPreferences.setBounds(0, 4, 774, 31);
		txtLecturerPreferences.setText("Manual sheduling");
		txtLecturerPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerPreferences.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerPreferences.setColumns(10);
		txtLecturerPreferences.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerPreferences.setBackground(new Color(176, 224, 230));
		PNL_Main.add(txtLecturerPreferences);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 80, 774, 12);
		PNL_Main.add(horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(5, 424, 759, 5);
		horizontalStrut_2.setBackground(Color.BLACK);
		PNL_Main.add(horizontalStrut_2);
		
		Object data[][]={};
		String columns[]={};
	
		tablemanual = new JTable(data,columns);
		tablemanual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablemanual.setToolTipText("Manual sheduling table ");
		tablemanual.setFillsViewportHeight(true);
		tablemanual.setSurrendersFocusOnKeystroke(true);
		tablemanual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablemanual.setBackground(SystemColor.activeCaptionText);
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
	
	
		
	/*////////
		 JComboBox cmb = new JComboBox();
		 cmb.setSelectedIndex(0);
		 cmb.setBackground(Color.BLUE);
		 cmb.setEditable(true);
		 cmb.setSize(-107, -347);
		 cmb.setLocation(132, 397);
		 ComboBoxRenderer renderer = new ComboBoxRenderer(cmb);
		 renderer.setColors(colors);
	     renderer.setStrings(strings);
	     cmb.setRenderer(renderer);
	     cmb.getEditor().getEditorComponent().setBackground(Color.BLACK);
		/*////// 
		
        
        
        //////////////////////////
				
		
		
	 // PNL_Main.add(cmb);
		
	
		
			cmbxFaculty = new JComboBox();
		  	cmbxFaculty.setBounds(63, 55, 461, 20);
		  	cmbxFaculty.setModel(new DefaultComboBoxModel(new String[] {"choose Faculty"}));
		  	cmbxFaculty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		  	cmbxFaculty.setToolTipText("Faculty list");
		  	cmbxFaculty.setMaximumRowCount(52);
		  
		 PNL_Main.add(cmbxFaculty);
		
		  	
		  	 JScrollPane scroll = new JScrollPane(tablemanual);
		  scroll.setEnabled(false);
		  scroll.setBounds(10, 100, 511, 249);
		  
		  PNL_Main.add(scroll);
		  
		  JLabel COURSE = new JLabel("Course:");
		  COURSE.setFont(new Font("Tahoma", Font.BOLD, 12));
		  COURSE.setBounds(540, 120, 65, 15);
		  PNL_Main.add(COURSE);
		  
		  cmbBxCourse = new JComboBox();
		  cmbBxCourse.setForeground(SystemColor.info);
		  cmbBxCourse.setToolTipText("cmbBxCourse");
		  cmbBxCourse.setBounds(600, 120, 120, 20);
		  PNL_Main.add(cmbBxCourse);
		  
		  JLabel lblType = new JLabel("Type: ");
		  lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblType.setBounds(540, 160, 45, 15);
		  PNL_Main.add(lblType);
		  
		  cmbBxType = new JComboBox();
		  cmbBxType.setBounds(600, 160, 120, 22);
		  PNL_Main.add(cmbBxType);
		  
		  JLabel lblLecturer = new JLabel("Lecturer:");
		  lblLecturer.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblLecturer.setBounds(540, 200, 66, 14);
		  PNL_Main.add(lblLecturer);
		  
		 cmbBxLecturer = new JComboBox();
		  cmbBxLecturer.setToolTipText("cmbBxLecturer");
		  cmbBxLecturer.setBounds(600, 200, 120, 22);
		  PNL_Main.add(cmbBxLecturer);

		  cmbBxClass = new JComboBox();
		  cmbBxClass.setToolTipText("cmbBxLecturer");
		  cmbBxClass.setBounds(600, 240, 120, 22);
		  PNL_Main.add(cmbBxClass);
		  
		  JLabel lblClass = new JLabel("Class");
		  lblClass.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblClass.setBounds(540, 240, 46, 14);
		  PNL_Main.add(lblClass);
		  
		  btnSet = new JButton("Set");
		  btnSet.setBounds(600, 270, 80, 23);
		  PNL_Main.add(btnSet);
		  
		   btnClear = new JButton("Clear");
		  btnClear.setBounds(600, 300, 80, 23);
		  PNL_Main.add(btnClear);
		  
		   btnDelete = new JButton("delete");
		  btnDelete.setBounds(600, 330, 80, 23);
		  PNL_Main.add(btnDelete);
		  
		  JPanel panel = new JPanel();
		  panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		  panel.setBackground(UIManager.getColor("inactiveCaptionText"));
		  panel.setBounds(531, 103, 203, 260);
		  PNL_Main.add(panel);
		  
		  JLabel lblFaculty = new JLabel("Faculty:");
		  lblFaculty.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblFaculty.setBounds(10, 55, 65, 14);
		  PNL_Main.add(lblFaculty);
		  
		  JLabel lblSemester = new JLabel("semester:");
		  lblSemester.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblSemester.setBounds(534, 55, 71, 14);
		  PNL_Main.add(lblSemester);
		
		  cmbBxSemster = new JComboBox();
		  cmbBxSemster.setToolTipText("cmbBxSemster");
		  cmbBxSemster.setBounds(601, 55, 46, 20);
		  PNL_Main.add(cmbBxSemster);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {}
	
	
	}

