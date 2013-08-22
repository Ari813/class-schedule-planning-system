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
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;


import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;

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
		//PNL_Main.add(scrollBar);
		
		JComboBox cmbxCourseSttings = new JComboBox();
		cmbxCourseSttings.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
		cmbxCourseSttings.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbxCourseSttings.setToolTipText("Edit class list");
		cmbxCourseSttings.setBounds(10, 53, 754, 20);
		cmbxCourseSttings.setMaximumRowCount(52);
		PNL_Main.add(cmbxCourseSttings);
		
		JButton btnNewClass = new JButton("New class");
		btnNewClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewClass.setBounds(111, 440, 110, 23);
		PNL_Main.add(btnNewClass);
		
		JButton btnSaveChanges = new JButton("Save");
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveChanges.setBounds(332, 440, 110, 23);
		PNL_Main.add(btnSaveChanges);
		
		JButton btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBackToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBackToMainMenu.setBounds(553, 440, 110, 23);
		PNL_Main.add(btnBackToMainMenu);
		
		txtCourseSttings = new JTextField();
		txtCourseSttings.setText("Course sttings");
		txtCourseSttings.setHorizontalAlignment(SwingConstants.CENTER);
		txtCourseSttings.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCourseSttings.setColumns(10);
		txtCourseSttings.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtCourseSttings.setBackground(new Color(176, 224, 230));
		txtCourseSttings.setBounds(10, 11, 754, 31);
		PNL_Main.add(txtCourseSttings);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(5, 424, 759, 5);
		PNL_Main.add(horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBackground(Color.BLACK);
		horizontalStrut_2.setBounds(0, 75, 774, 5);
		PNL_Main.add(horizontalStrut_2);
		
		Object data[][]={{new Integer(10),new Integer(10),false},{new Integer(10),new Integer(10),"barda"}
		,{new Integer(10),new Integer(10),"barda"}};
		String columns[]={"# of student","course ID","course description"};
		/*//
		DefaultTableModel model;
		model = new DefaultTableModel(columns,20);
		  table=new JTable(model){@Override
		        public boolean isCellEditable(int arg0, int arg1) {
		         
		            return true;
		        }};
			
		/*///
		table = new JTable(data,columns);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(SystemColor.info);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(10), new Integer(10), null, null},
				{new Integer(10), new Integer(10), null, null},
				{new Integer(10), new Integer(10), null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"# of student", "course ID", "course description", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.setSurrendersFocusOnKeystroke(true);
		
		table.setToolTipText("");
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(24, 112, 622, 304);
	
		PNL_Main.add(table);
	}
}
