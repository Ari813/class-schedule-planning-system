package GUI;

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

public class Course_Settings extends JPanel {
	private JTextField txtCourseSttings;
	private JTable table;

	public Course_Settings() {

	
	
	
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
		btnSaveChanges.setBounds(201, 434, 85, 23);
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PNL_Main.add(btnSaveChanges);
		
		JButton btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setBounds(487, 434, 85, 23);
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBackToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PNL_Main.add(btnBackToMainMenu);
		
		txtCourseSttings = new JTextField();
		txtCourseSttings.setBounds(10, 11, 754, 31);
		txtCourseSttings.setText("Course sttings");
		txtCourseSttings.setHorizontalAlignment(SwingConstants.CENTER);
		txtCourseSttings.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCourseSttings.setColumns(10);
		txtCourseSttings.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtCourseSttings.setBackground(new Color(176, 224, 230));
		PNL_Main.add(txtCourseSttings);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 80, 774, 12);
		PNL_Main.add(horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(5, 424, 759, 5);
		horizontalStrut_2.setBackground(Color.BLACK);
		PNL_Main.add(horizontalStrut_2);
		
		Object data[][]={{new Integer(10),new Integer(10),false},{new Integer(10),new Integer(10),"barda"}
		,{new Integer(10),new Integer(10),"barda"}};
		String columns[]={"# of student","course ID","course description"};
	
		table = new JTable(data,columns);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(SystemColor.info);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(10), new Integer(10), null},
				{new Integer(10), new Integer(10), null},
				{new Integer(10), new Integer(10), null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
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
				Object.class, Object.class, String.class
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
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(25, 110, 600, 300);
	
		  	
		  	JComboBox cmbxFaculty = new JComboBox();
		  	cmbxFaculty.setBounds(10, 53, 724, 20);
		  	cmbxFaculty.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
		  	cmbxFaculty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		  	cmbxFaculty.setToolTipText("Edit class list");
		  	cmbxFaculty.setMaximumRowCount(52);
		  	PNL_Main.add(cmbxFaculty);
		  JScrollPane scroll = new JScrollPane(table);
		  scroll.setBounds(10, 103, 724, 303);
		  
		  PNL_Main.add(scroll);
		  
		
		
		
	//	PNL_Main.add(table);
	}
}
