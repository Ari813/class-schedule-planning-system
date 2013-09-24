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
import javax.swing.DefaultCellEditor;
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

public class Lecturer_Preferences extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtLecturerPreferences;
	private JTable tableLecturermanu;
	private  JButton btnSaveChanges;
	private JButton btnBackToMainMenu;
  	private JComboBox cmbxlecturer;
	public JPanel PNL_Main;
	static Color[] colors = {Color.BLUE, Color.GRAY, Color.RED};
	static String[] strings = {"Test1", "Test2", "Test3"};
	
	/**
	 * Create the panel.
	 */
	public Lecturer_Preferences() {
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
		txtLecturerPreferences.setText("Lecturer Preferences");
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
	
		tableLecturermanu = new JTable(data,columns);
		tableLecturermanu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tableLecturermanu.setToolTipText("table ");
		tableLecturermanu.setFillsViewportHeight(true);
		tableLecturermanu.setSurrendersFocusOnKeystroke(true);
		tableLecturermanu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLecturermanu.setBackground(SystemColor.inactiveCaption);
		tableLecturermanu.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableLecturermanu.setModel(new DefaultTableModel(
			new Object[][] {
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
			},
			new String[] {
				"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableLecturermanu.getColumnModel().getColumn(0).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(1).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(2).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(3).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(4).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(5).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(6).setResizable(false);
		
		//tableLecturermanu.getColumnModel().getColumn(1).setPreferredWidth(78);

		tableLecturermanu.setColumnSelectionAllowed(true);
		tableLecturermanu.setCellSelectionEnabled(true);
		tableLecturermanu.setBounds(35, 127, 600, 224);
	
	
		javax.swing.JComboBox select = new javax.swing.JComboBox();
		select.addItem("Avoid");
		select.addItem("prefr");
		select.addItem("N\\A");
		select.setEnabled(true);
		select.setVisible(true);
		select.getEditor().getEditorComponent().setBackground(Color.yellow);
		select.getEditor().getEditorComponent().setForeground(Color.PINK);
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
		tableLecturermanu.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(select));
        
        
        //////////////////////////
				
		
		
	 // PNL_Main.add(cmb);
		
	
		
		
        	cmbxlecturer = new JComboBox();
		  	cmbxlecturer.setBounds(10, 53, 724, 20);
		  	cmbxlecturer.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
		  	cmbxlecturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		  	cmbxlecturer.setToolTipText("Edit class list");
		  	cmbxlecturer.setMaximumRowCount(52);
		  
		 PNL_Main.add(cmbxlecturer);
		
		  	
		  	 JScrollPane scroll = new JScrollPane(tableLecturermanu);
		  scroll.setEnabled(false);
		  scroll.setBounds(10, 103, 724, 252);
		  
		  PNL_Main.add(scroll);
		
		
	}
	public void actionPerformed(ActionEvent e) {
			
		if (e.getSource() == btnSaveChanges) {}
		if (e.getSource() == btnBackToMainMenu) {}
	}
	
}
