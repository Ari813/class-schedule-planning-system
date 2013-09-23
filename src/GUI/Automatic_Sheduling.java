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
import javax.swing.JList;
import javax.swing.border.EtchedBorder;


public class Automatic_Sheduling extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtLecturerPreferences;
	private JTable tableAutonatic;
	public JPanel PNL_Main;
	private JButton btnSaveChanges;
	private JButton btnBackToMainMenu ;
	private JButton btnPreview;
	private JScrollPane scroll;
	private JComboBox cmbxFaculty;
	private JComboBox cmbBxSemster;
	private JList lstTimeTable ;
	
	
	
	static Color[] colors = {Color.BLUE, Color.GRAY, Color.RED};
	static String[] strings = {"Test1", "Test2", "Test3"};
	
	/**
	 * Create the panel.
	 */
	public Automatic_Sheduling() {
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
		txtLecturerPreferences.setText("Automatic Sheduling is running");
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
		
		//tablemanual.getColumnModel().getColumn(1).setPreferredWidth(78);

		tableAutonatic.setColumnSelectionAllowed(true);
		tableAutonatic.setCellSelectionEnabled(true);
		tableAutonatic.setBounds(35, 127, 600, 224);
	
	
		
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
		
		  	
		  scroll = new JScrollPane(tableAutonatic);
		  scroll.setEnabled(false);
		  scroll.setBounds(10, 100, 511, 249);
		  
		  PNL_Main.add(scroll);
		  
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
		  
		  JLabel lblOptionalTimeTables = new JLabel("Optional time tables :");
		  lblOptionalTimeTables.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lblOptionalTimeTables.setBounds(560, 123, 170, 14);
		  PNL_Main.add(lblOptionalTimeTables);
		  
		  lstTimeTable = new JList();
		  lstTimeTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		  lstTimeTable.setBackground(new Color(175, 238, 238));
		  lstTimeTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		  lstTimeTable.setBounds(559, 149, 102, 142);
		  PNL_Main.add(lstTimeTable);
		  
		  btnPreview = new JButton("Preview");
		  btnPreview.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  	}
		  });
		  btnPreview.setBounds(560, 313, 89, 23);
		  PNL_Main.add(btnPreview);
		  
		  JPanel panel = new JPanel();
		  panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		  panel.setBackground(new Color(135, 206, 235));
		  panel.setBounds(540, 103, 201, 249);
		  PNL_Main.add(panel);
		
		
	}
	}


