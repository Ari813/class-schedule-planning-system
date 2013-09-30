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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import Controllers.LecturerController;
import Controllers.ManagerController;

public class Edit_Lecturer extends JPanel implements ActionListener,
ListSelectionListener, KeyListener {

	/**
	 * Create the panel.
	 */
	
	private	JTextField txLecturerNameText;
	private JTextField txtLecturerEditor;
	private JComboBox cmbxLecturerEditor;
	private JList lstAvailableCourses ;
	private JList lstAvailableCourses2;
	private JTextPane txtpnIDNumber;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnNewLecturer;
	private JButton btnSaveChanges;
	private JButton btnBackToMainMenu;
	public JPanel PNL_Main;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private JLabel lblID;
	private JLabel lblLecturer_Name;
	private JLabel lblAvailableCourses;
	LecturerController lec;
	private ManagerController manager;
	/*/
	JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
	lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
	lblTimeTableSchedualing.setLocation(new Point(50, 0));
	lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
	lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
	lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
/*/
	public Edit_Lecturer(ManagerController mng) {

		super();
		this.manager=mng;
		initialize();}
	
	
		private void initialize() {
		// TODO Auto-generated method stub
		pnl();
		
		PNL_Main.add(GETcmbxLecturerEditor());
		PNL_Main.add(GETtxtpnIDNumber());
		PNL_Main.add(GETlblID());
		PNL_Main.add(GETlblLecturer_Name());
		PNL_Main.add(GETtxLecturerNameText());
		PNL_Main.add(GETlblAvailableCourses());
		PNL_Main.add(GETlstAvailableCourses());
		PNL_Main.add(GETbtnAdd());
		PNL_Main.add(GETbtnRemove());
		PNL_Main.add(GETlstAvailableCourses2());
		PNL_Main.add(GETbtnNewLecturer());
		PNL_Main.add(GETbtnSaveChanges());
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtLecturerEditor());
		
		horizontalStrut();
	}
	private Component GETbtnRemove() {
			btnRemove = new JButton("<--");
			btnRemove.setToolTipText("Remove item from class");
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnRemove.setBounds(502, 216, 65, 38);
			btnRemove.addActionListener(this);
		return btnRemove;
	}
	private JList GETlstAvailableCourses2() {
		lstAvailableCourses2 = new JList();
		lstAvailableCourses2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstAvailableCourses2.setBackground(new Color(169, 169, 169));
		lstAvailableCourses2.setForeground(new Color(255, 255, 255));
		lstAvailableCourses2.setBounds(586, 130, 138, 283);
		return lstAvailableCourses2;
	}
	private JButton GETbtnNewLecturer() {
		 btnNewLecturer = new JButton("New Lecturer");
			btnNewLecturer.addActionListener(this);
			btnNewLecturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewLecturer.setBounds(73, 434, 160, 29);
			btnNewLecturer.addActionListener(this);
		return btnNewLecturer;
	}
	private JButton GETbtnSaveChanges() {
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveChanges.setBounds(306, 434, 160, 29);
		btnSaveChanges.addActionListener(this);
		return btnSaveChanges;
	}
	private Component GETbtnBackToMainMenu() {
		 btnBackToMainMenu = new JButton("Discard");
			btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnBackToMainMenu.addActionListener(this);
			btnBackToMainMenu.setBounds(539, 434, 160, 29);
			btnBackToMainMenu.addActionListener(this);
		return btnBackToMainMenu;
	}
	private JButton GETbtnAdd() {
		 btnAdd = new JButton(" -->");
			btnAdd.setToolTipText("Add item to class");
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnAdd.addActionListener(this);
			btnAdd.setBounds(502, 163, 65, 38);
		return btnAdd;
	}
	private JList GETlstAvailableCourses() {
		 lstAvailableCourses = new JList();
			lstAvailableCourses.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lstAvailableCourses.setForeground(new Color(0, 0, 0));
			lstAvailableCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lstAvailableCourses.setValueIsAdjusting(true);
			lstAvailableCourses.setSelectedIndices(new int[] {2});
			lstAvailableCourses.setBackground(new Color(169, 169, 169));
			lstAvailableCourses.setModel(new AbstractListModel() {
				String[] values = new String[] {"omri", "amit", "iris"};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			lstAvailableCourses.setBounds(332, 130, 138, 283);
		return lstAvailableCourses;
	}
	private JTextField GETtxtLecturerEditor() {
		txtLecturerEditor = new JTextField();
		txtLecturerEditor.setEditable(false);
		txtLecturerEditor.setText("Leturer editor");
		txtLecturerEditor.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerEditor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerEditor.setColumns(10);
		txtLecturerEditor.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerEditor.setBackground(new Color(176, 224, 230));
		txtLecturerEditor.setBounds(0, 4, 774, 40);
		return txtLecturerEditor;
	}
	private JLabel GETlblAvailableCourses() {
		lblAvailableCourses = new JLabel("Available courses");
		lblAvailableCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAvailableCourses.setBounds(332, 100, 157, 14);
		return lblAvailableCourses;
	}
	private JTextField GETtxLecturerNameText() {
		txLecturerNameText = new JTextField();
		txLecturerNameText.setEnabled(false);
		txLecturerNameText.setBackground(Color.WHITE);
		txLecturerNameText.setText("Lecturer Name text");
		txLecturerNameText.setBounds(10, 207, 100, 20);
		txLecturerNameText.setColumns(10);
		return txLecturerNameText;
	}
	private JLabel GETlblLecturer_Name() {
		lblLecturer_Name = new JLabel("Lecturer Name:");
		lblLecturer_Name.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLecturer_Name.setBounds(10, 179, 123, 22);
		return lblLecturer_Name;
	}
	private JLabel GETlblID() {
		lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblID.setBounds(10, 114, 90, 21);
		return lblID;
	}
	private void horizontalStrut() {
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(332, 114, 392, 14);
		PNL_Main.add(horizontalStrut);
	
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(5, 424, 759, 5);
		PNL_Main.add(horizontalStrut_1);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBackground(Color.BLACK);
		horizontalStrut_2.setBounds(0, 75, 774, 5);
		PNL_Main.add(horizontalStrut_2);
		
	}
	private JTextPane GETtxtpnIDNumber() {
		txtpnIDNumber= new JTextPane();
		txtpnIDNumber.setEnabled(false);
		//txtpnIDNumber.setDropMode(DropMode.ON);
		txtpnIDNumber.setBackground(Color.WHITE);
		txtpnIDNumber.setText("ID Number");
		txtpnIDNumber.setBounds(10, 144, 100, 20);
		return txtpnIDNumber;
	}
	private JComboBox GETcmbxLecturerEditor() {
		 cmbxLecturerEditor = new JComboBox();
			cmbxLecturerEditor.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
			cmbxLecturerEditor.setFont(new Font("Tahoma", Font.PLAIN, 16));
			cmbxLecturerEditor.setToolTipText("Edit class list");
			cmbxLecturerEditor.setBounds(10, 53, 754, 20);
			cmbxLecturerEditor.setMaximumRowCount(52);
		return cmbxLecturerEditor;
	}
	private void pnl() {
		PNL_Main= new JPanel();
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
	
		PNL_Main.setLayout(null);
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {}	
		if (e.getSource() == btnRemove) {}
		if (e.getSource() == btnNewLecturer) {}	
		if (e.getSource() == btnSaveChanges) {}
		if (e.getSource() == btnBackToMainMenu) {
			manager.BacktoMainMenu(this.PNL_Main);
		}
		
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
