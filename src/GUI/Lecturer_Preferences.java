package GUI;

import javax.swing.JPanel;
import java.awt.EventQueue;

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
import java.util.Locale;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.UIManager;

import java.awt.FlowLayout;

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

import javax.swing.JLayeredPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;


import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;

import net.miginfocom.swing.MigLayout;
import javax.swing.ScrollPaneConstants;

public class Lecturer_Preferences extends JPanel {
	private JTextField txtLecturerPreferences;
	private JTable tableLecturermanu;

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
	
		
		JPanel PNL_Main = new JPanel();
		PNL_Main.setBorder(new EmptyBorder(0, 0, 0, 0));
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
	;
		PNL_Main.setLayout(null);
		
		JButton btnSaveChanges = new JButton("Save");
		btnSaveChanges.setBounds(226, 434, 74, 29);
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PNL_Main.add(btnSaveChanges);
		
		JButton btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setBounds(384, 434, 85, 29);
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
		horizontalStrut_1.setBounds(0, 80, 764, 12);
		PNL_Main.add(horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(0, 416, 764, 12);
		horizontalStrut_2.setBackground(Color.BLACK);
		PNL_Main.add(horizontalStrut_2);
		
		Object data[][]={{new Integer(10),new Integer(10),false},{new Integer(10),new Integer(10),"barda"}
		,{new Integer(10),new Integer(10),"barda"}};
		String columns[]={"# of student","course ID","course description"};
	
		tableLecturermanu = new JTable(data,columns);
		tableLecturermanu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLecturermanu.setBackground(SystemColor.inactiveCaptionText);
		tableLecturermanu.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableLecturermanu.setModel(new DefaultTableModel(
			new Object[][] {
				{"8:00-9:00", "10", null, null, null, null, null},
				{"9:00-10:00", "", null, null, null, null, null},
				{"10:00-11:00", new Integer(10), null, null, null, null, null},
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
				"time", "sunday", "monday", "tuesday", "wednesday", "thursday", "friday"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableLecturermanu.getColumnModel().getColumn(0).setPreferredWidth(78);
		tableLecturermanu.getColumnModel().getColumn(1).setResizable(false);
		tableLecturermanu.setSurrendersFocusOnKeystroke(true);
		
		tableLecturermanu.setToolTipText("");
		tableLecturermanu.setColumnSelectionAllowed(true);
		tableLecturermanu.setCellSelectionEnabled(true);
		tableLecturermanu.setBounds(25, 110, 600, 300);
	
		  	
		  	JComboBox cmbxlecturer = new JComboBox();
		  	cmbxlecturer.setBounds(10, 46, 651, 26);
		  	cmbxlecturer.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
		  	cmbxlecturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		  	cmbxlecturer.setToolTipText("Edit class list");
		  	cmbxlecturer.setMaximumRowCount(52);
		  	PNL_Main.add(cmbxlecturer);
		  JScrollPane scroll = new JScrollPane(tableLecturermanu);
		  scroll.setEnabled(false);
		  scroll.setBounds(45, 99, 616, 247);
		  
		  PNL_Main.add(scroll);
		  
		
	}

}
