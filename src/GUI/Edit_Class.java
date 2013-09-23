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
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;

public class Edit_Class {

	//private JFrame frmLec;
	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;
	private JTextField txtClassEditor;
	private JComboBox cmbxEditClass ;
	private JComboBox cmbxcampus;
	private JList lstClassAids;
	private JTextPane txtpnCodeNumber;
	private JComboBox cmbBxBlding;	
	private JCheckBox chckbxAvailable;
	private JButton btnAdd ;
	private JButton btnRemove;
	private JButton btnNewClass;
	private JButton btnSaveChanges;
	private JButton btnDiscard;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Edit_Class() {
	
	
		JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
		lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeTableSchedualing.setLocation(new Point(50, 0));
		lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
		lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	
		
		JPanel PNL_Main = new JPanel();
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
	;
		PNL_Main.setLayout(null);
		
		cmbxEditClass = new JComboBox();
		cmbxEditClass.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
		cmbxEditClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbxEditClass.setToolTipText("Edit class list");
		cmbxEditClass.setBounds(10, 53, 754, 20);
		cmbxEditClass.setMaximumRowCount(52);
		PNL_Main.add(cmbxEditClass);
		
		txtpnCodeNumber = new JTextPane();
		txtpnCodeNumber.setEnabled(false);
		txtpnCodeNumber.setDropMode(DropMode.ON);
		txtpnCodeNumber.setBackground(Color.WHITE);
		txtpnCodeNumber.setText("Code Number");
		txtpnCodeNumber.setBounds(10, 144, 100, 20);
		PNL_Main.add(txtpnCodeNumber);
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCode.setBounds(10, 114, 90, 21);
		PNL_Main.add(lblCode);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescription.setBounds(10, 179, 123, 22);
		PNL_Main.add(lblDescription);
		
		txtDescriptionText = new JTextField();
		txtDescriptionText.setEnabled(false);
		txtDescriptionText.setBackground(Color.WHITE);
		txtDescriptionText.setText("description text");
		txtDescriptionText.setBounds(10, 207, 100, 20);
		PNL_Main.add(txtDescriptionText);
		txtDescriptionText.setColumns(10);
		
		cmbxcampus = new JComboBox();
		cmbxcampus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbxcampus.setToolTipText("Campus");
		cmbxcampus.setBounds(10, 292, 130, 20);
		PNL_Main.add(cmbxcampus);
		
		JLabel lblCampus = new JLabel("Campus:");
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCampus.setBounds(10, 267, 90, 14);
		PNL_Main.add(lblCampus);
		
		JLabel lblBilding = new JLabel("Building:");
		lblBilding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBilding.setBounds(172, 267, 82, 22);
		PNL_Main.add(lblBilding);
		
		cmbBxBlding = new JComboBox();
		cmbBxBlding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbBxBlding.setBounds(172, 292, 130, 20);
		PNL_Main.add(cmbBxBlding);
		
		JLabel lblCapacity = new JLabel("Capacity:");
		lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCapacity.setVerticalAlignment(SwingConstants.TOP);
		lblCapacity.setBounds(172, 114, 76, 22);
		PNL_Main.add(lblCapacity);
		
		txtCapacityNumber = new JTextField();
		txtCapacityNumber.setEnabled(false);
		txtCapacityNumber.setText("Capacity number");
		txtCapacityNumber.setBounds(172, 144, 100, 20);
		PNL_Main.add(txtCapacityNumber);
		txtCapacityNumber.setColumns(10);
		
		JLabel lblClassAids = new JLabel("Class aids:");
		lblClassAids.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClassAids.setBounds(332, 100, 100, 14);
		PNL_Main.add(lblClassAids);
		
		lstClassAids = new JList();
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
		PNL_Main.add(lstClassAids);
		
		chckbxAvailable = new JCheckBox("Available");
		chckbxAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxAvailable.setBounds(10, 80, 135, 23);
		PNL_Main.add(chckbxAvailable);
		
		btnAdd = new JButton(" -->");
		btnAdd.setToolTipText("Add item to class");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds(502, 163, 61, 38);
		PNL_Main.add(btnAdd);
		
		 btnRemove = new JButton("<--");
		btnRemove.setToolTipText("Remove item from class");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(502, 216, 61, 38);
		PNL_Main.add(btnRemove);
		
		JList lstClassaids2 = new JList();
		lstClassaids2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstClassaids2.setBackground(new Color(169, 169, 169));
		lstClassaids2.setForeground(new Color(255, 255, 255));
		lstClassaids2.setBounds(586, 130, 138, 220);
		PNL_Main.add(lstClassaids2);
		
		
		
		 btnNewClass = new JButton("New class");
		btnNewClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewClass.setBounds(111, 440, 140, 23);
		PNL_Main.add(btnNewClass);
	
		
		
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveChanges.setBounds(332, 440, 140, 23);
		PNL_Main.add(btnSaveChanges);
		
		

		
		 btnDiscard = new JButton("Discard");
		btnDiscard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDiscard.setBounds(553, 440, 140, 23);
		PNL_Main.add(btnDiscard);
		
		txtClassEditor = new JTextField();
		txtClassEditor.setText("Class editor");
		txtClassEditor.setHorizontalAlignment(SwingConstants.CENTER);
		txtClassEditor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtClassEditor.setColumns(10);
		txtClassEditor.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtClassEditor.setBackground(new Color(176, 224, 230));
		txtClassEditor.setBounds(10, 11, 754, 31);
		PNL_Main.add(txtClassEditor);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(332, 114, 392, 14);
		PNL_Main.add(horizontalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(5, 424, 759, 5);
		PNL_Main.add(horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBackground(Color.BLACK);
		horizontalStrut_2.setBounds(0, 75, 774, 5);
		PNL_Main.add(horizontalStrut_2);
	}
	
	public void actionPerformed(ActionEvent e) {}
	
}
