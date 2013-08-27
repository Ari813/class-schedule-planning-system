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

import javax.swing.DefaultCellEditor;
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
import javax.swing.table.TableColumn;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;


import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;

import net.miginfocom.swing.MigLayout;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;
import javax.swing.JSlider;

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
		btnSaveChanges.setBounds(205, 434, 74, 23);
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PNL_Main.add(btnSaveChanges);
		
		JButton btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setBounds(484, 434, 85, 23);
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBackToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PNL_Main.add(btnBackToMainMenu);
		
		txtLecturerPreferences = new JTextField();
		txtLecturerPreferences.setBounds(10, 11, 754, 31);
		txtLecturerPreferences.setText("Lecturer Preferences");
		txtLecturerPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerPreferences.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerPreferences.setColumns(10);
		txtLecturerPreferences.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerPreferences.setBackground(new Color(176, 224, 230));
		PNL_Main.add(txtLecturerPreferences);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 75, 774, 5);
		PNL_Main.add(horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(5, 424, 759, 5);
		horizontalStrut_2.setBackground(Color.BLACK);
		PNL_Main.add(horizontalStrut_2);
		
		Object data[][]={};
		String columns[]={};
		
		tableLecturermanu = new JTable(data,columns);
		tableLecturermanu.setRowSelectionAllowed(false);
		tableLecturermanu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableLecturermanu.setToolTipText("weekly Time Table");
		tableLecturermanu.setFillsViewportHeight(true);
		tableLecturermanu.setSurrendersFocusOnKeystroke(true);
		tableLecturermanu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLecturermanu.setBackground(SystemColor.activeCaption);
		tableLecturermanu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
				"Time", "Sunday", "Monday", "Tuesday", "wednesday", "Thursday", "Friday"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
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
		tableLecturermanu.getColumnModel().getColumn(4).setMinWidth(19);
		tableLecturermanu.setCellSelectionEnabled(true);
		tableLecturermanu.setBounds(10, 103, 724, 303);
		
		TableColumn gradeColumn = tableLecturermanu.getColumnModel().getColumn(1);
		JComboBox CB_TimeTableOptions = new JComboBox ();
		CB_TimeTableOptions.removeAllItems();
		    try {
		    	CB_TimeTableOptions.addItem("Evoid");
		    	CB_TimeTableOptions.addItem("Prefered");
		    	CB_TimeTableOptions.addItem("N\\A");
		    } catch (NullPointerException e) {
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		gradeColumn.setCellEditor(new DefaultCellEditor(CB_TimeTableOptions));
		
		//////////////////////////
		//  PNL_Main.add(tableLecturermanu);
		
		// TableColumn includeColumn = tableLecturermanu.getColumnModel().getColumn(2);
        // includeColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
		
		  	
		  	JComboBox cmbxlecturer = new JComboBox();
		  	cmbxlecturer.setBounds(10, 53, 754, 20);
		  	cmbxlecturer.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
		  	cmbxlecturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		  	cmbxlecturer.setToolTipText("Edit class list");
		  	cmbxlecturer.setMaximumRowCount(52);
		  
		 PNL_Main.add(cmbxlecturer);
		
		  	
		  JScrollPane scroll = new JScrollPane(tableLecturermanu);
		  scroll.setEnabled(false);
		  scroll.setBounds(10, 103, 754, 253);
		  
		  PNL_Main.add(scroll);
		
		
	}
}
