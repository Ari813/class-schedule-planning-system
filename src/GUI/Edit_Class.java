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
import java.util.ArrayList;
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

import Controllers.LecturerController;
import Controllers.ManagerController;

public class Edit_Class extends JPanel implements ActionListener,
ListSelectionListener, KeyListener {

	//private JFrame frmLec;
	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;
	private JTextField txtClassEditor;
	private JComboBox cmbxEditClass ;
	private JComboBox cmbxcampus;
	private JList<String> lstClassAids;
	private JTextPane txtpnCodeNumber;
	private JComboBox cmbBxBlding;	
	private JCheckBox chckbxAvailable;
	
	public JPanel PNL_Main ;
	
	private JButton btnAdd ;
	private JButton btnRemove;
	private JButton btnNewClass;
	private JButton btnSaveChanges;
	private JButton btnDiscard;
	private JLabel lblCode ;
	private JLabel lblDescription ;
	private JLabel lblCampus;
	private JLabel lblBilding;
	private JLabel lblCapacity;
	
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut;
	private JLabel lblClassAids;
	
	private JList lstClassaids2 ;
	/**
	 * Launch the application.
	 */
	
	/*/JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
	lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
	lblTimeTableSchedualing.setLocation(new Point(50, 0));
	lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
	lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
	lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

	/*/
	private LecturerController lec;
	private ManagerController manager;
	private ArrayList<String> aids;
	

	/**
	 * Create the application.
	 */
	public Edit_Class (ManagerController mng) {

		super();
		this.manager=mng;
		initialize();}
	
	
		private void initialize() {
	
	
	
		pnl();
					
		PNL_Main.add(GETcmbxEditClass());
		PNL_Main.add(GETcmbxcampus());
		PNL_Main.add(GETcmbBxBlding());
		
		PNL_Main.add(GETlblCapacity());
		PNL_Main.add(GETlblBilding());
		PNL_Main.add(GETlblCampus());
		PNL_Main.add(GETlblDescription());
		PNL_Main.add(GETlblCode());
		PNL_Main.add(GETlblClassAids());
		
		PNL_Main.add(GETtxtpnCodeNumber());
		PNL_Main.add(GETtxtCapacityNumber());
		PNL_Main.add(GETtxtDescriptionText());
		PNL_Main.add(GETtxtClassEditor());
		
		PNL_Main.add(GETlstClassAids());
		
		
		PNL_Main.add(GETchckbxAvailable());
				
		PNL_Main.add(GETbtnAdd());
		PNL_Main.add(GETbtnRemove());
		PNL_Main.add(GETbtnNewClass());
	    PNL_Main.add(GETbtnSaveChanges());
	    PNL_Main.add(GETbtnDiscard());
	    
		PNL_Main.add(GETlstClassaids2());
		
		sethorizontalStrut();
		
	}
private void pnl() {
		
	PNL_Main = new JPanel();
	PNL_Main.setToolTipText("Edit class list");
	PNL_Main.setMinimumSize(new Dimension(774, 474));
	PNL_Main.setMaximumSize(new Dimension(774, 474));
	PNL_Main.setBounds(10, 85, 774, 474);
	PNL_Main.setLayout(null);
			
		}
	private JTextField GETtxtClassEditor() {
		txtClassEditor = new JTextField();
		txtClassEditor.setEditable(false);
		txtClassEditor.setText("Class editor");
		txtClassEditor.setHorizontalAlignment(SwingConstants.CENTER);
		txtClassEditor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtClassEditor.setColumns(10);
		txtClassEditor.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtClassEditor.setBackground(new Color(176, 224, 230));
		txtClassEditor.setBounds(0, 4, 774, 40);
		return txtClassEditor;
	}

	private JButton GETbtnDiscard() {
		btnDiscard = new JButton("Discard");
		btnDiscard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDiscard.addActionListener(this);
		btnDiscard.setBounds(539, 434, 160, 29);
		return btnDiscard;
	}

	private JButton GETbtnSaveChanges() {
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveChanges.setBounds(306, 434, 160, 29);
		btnSaveChanges.addActionListener(this);
		return btnSaveChanges;
	}

	private JButton GETbtnNewClass() {
		 btnNewClass = new JButton("New class");
			btnNewClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewClass.setBounds(73, 434, 160, 29);
			btnNewClass.addActionListener(this);
		return btnNewClass;
	}

	private JList GETlstClassaids2() {
		lstClassaids2 = new JList();
		lstClassaids2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstClassaids2.setBackground(new Color(169, 169, 169));
		lstClassaids2.setForeground(new Color(255, 255, 255));
		lstClassaids2.setBounds(586, 130, 138, 220);
		return lstClassaids2;
	}

	private JButton GETbtnRemove() {
		 btnRemove = new JButton("<--");
			btnRemove.setToolTipText("Remove item from class");
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnRemove.setBounds(502, 216, 65, 38);
			btnRemove.addActionListener(this);
		return btnRemove;
	}

	private JButton GETbtnAdd() {
		btnAdd = new JButton(" -->");
		btnAdd.setToolTipText("Add item to class");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.addActionListener(this);
		btnAdd.setBounds(502, 163, 65, 38);
		return btnAdd;
	}

	private JCheckBox GETchckbxAvailable() {
		chckbxAvailable = new JCheckBox("Available");
		chckbxAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxAvailable.setBounds(10, 80, 135, 23);
		return chckbxAvailable;
	}

	private JList GETlstClassAids() {
		lstClassAids = new JList<String>();
		lstClassAids.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstClassAids.setForeground(new Color(0, 0, 0));
		lstClassAids.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstClassAids.setValueIsAdjusting(true);
		lstClassAids.setSelectedIndices(new int[] {2});
		lstClassAids.setBackground(new Color(169, 169, 169));
		lstClassAids.setModel(new AbstractListModel() {
			String[] values = new String[] {"omri", "amit", "iris"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lstClassAids.setBounds(332, 130, 138, 220);
		return lstClassAids;
	}

	private JLabel GETlblClassAids() {
		lblClassAids = new JLabel("Class aids:");
		lblClassAids.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClassAids.setBounds(332, 100, 100, 14);
		return lblClassAids;
	}

	private JTextField GETtxtCapacityNumber() {
		txtCapacityNumber = new JTextField();
		txtCapacityNumber.setEnabled(false);
		txtCapacityNumber.setText("Capacity number");
		txtCapacityNumber.setBounds(172, 144, 100, 20);
		return txtCapacityNumber;
	}

	private void sethorizontalStrut() {
		
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

	private JLabel GETlblCapacity() {
		lblCapacity = new JLabel("Capacity:");
		lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCapacity.setVerticalAlignment(SwingConstants.TOP);
		lblCapacity.setBounds(172, 114, 76, 22);
		return lblCapacity;
	}

	private JComboBox GETcmbBxBlding() {
		cmbBxBlding = new JComboBox();
		cmbBxBlding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbBxBlding.setBounds(172, 292, 130, 20);
		return cmbBxBlding;
	}

	private JLabel GETlblBilding() {
		lblBilding = new JLabel("Building:");
		lblBilding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBilding.setBounds(172, 267, 82, 22);
		return lblBilding;
	}

	private JLabel GETlblCampus() {
		lblCampus = new JLabel("Campus:");
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCampus.setBounds(10, 267, 90, 14);
		return lblCampus;
	}

	private JComboBox GETcmbxcampus() {
		cmbxcampus = new JComboBox();
		cmbxcampus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbxcampus.setToolTipText("Campus");
		cmbxcampus.setBounds(10, 292, 130, 20);
		return cmbxcampus;
	}

	private JTextField GETtxtDescriptionText() {
		txtDescriptionText = new JTextField();
		txtDescriptionText.setEnabled(false);
		txtDescriptionText.setBackground(Color.WHITE);
		txtDescriptionText.setText("description text");
		txtDescriptionText.setBounds(10, 207, 100, 20);
		return txtDescriptionText;
	}

	private JLabel GETlblDescription() {
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescription.setBounds(10, 179, 123, 22);
		return lblDescription;
	}

	private JLabel GETlblCode() {
		lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCode.setBounds(10, 114, 90, 21);
		return lblCode;
	}

	private JTextPane GETtxtpnCodeNumber() {
		if (txtpnCodeNumber==null){
		txtpnCodeNumber = new JTextPane();
		txtpnCodeNumber.setEnabled(false);
		//txtpnCodeNumber.setDropMode(DropMode.ON);
		txtpnCodeNumber.setBackground(Color.WHITE);
		txtpnCodeNumber.setText("Code Number");
		txtpnCodeNumber.setBounds(10, 144, 100, 20);}
		return txtpnCodeNumber;
	}

	private JComboBox GETcmbxEditClass() {
		if (cmbxEditClass==null){
		cmbxEditClass = new JComboBox();
		cmbxEditClass.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
		cmbxEditClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbxEditClass.setToolTipText("Edit class list");
		cmbxEditClass.setBounds(10, 53, 754, 20);
		cmbxEditClass.setMaximumRowCount(52);}
		return cmbxEditClass;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSaveChanges) {}	
		if (e.getSource() == btnAdd) {}
		if (e.getSource() == btnRemove) {}	
		if (e.getSource() == btnNewClass) {}
		if (e.getSource() == btnDiscard) {
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


	public void addClassAids(ArrayList<String> arry) {
		// TODO Auto-generated method stub
		this.aids=new ArrayList<String>();
		lstClassAids.removeAll();
		this.aids.addAll(0, arry);
		for (int i=0 ; i < (arry.size()); i++){
			//lstClassAids.add(arry.get(i),i );
			
		
	}
	}
	
}
