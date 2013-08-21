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

public class Edit_Class2 {

	//private JFrame frmLec;
	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;

	/**
	 * Launch the application.
	 */
	/*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit_Class window = new Edit_Class();
					window.frmLec.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Edit_Class2() {
	
	
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
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setToolTipText("");
		layeredPane.setBackground(new Color(169, 169, 169));
		layeredPane.setBounds(5, 95, 309, 145);
		PNL_Main.add(layeredPane);
		
		JLabel lblEditClass = new JLabel("Edit Class:");
		lblEditClass.setBounds(5, 5, 82, 22);
		lblEditClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PNL_Main.add(lblEditClass);
		
		JComboBox cmbxEditClass = new JComboBox();
		cmbxEditClass.setToolTipText("Edit class list");
		cmbxEditClass.setBounds(83, 38, 650, 20);
		cmbxEditClass.setMaximumRowCount(52);
		PNL_Main.add(cmbxEditClass);
		
		JTextPane txtpnCodeNumber = new JTextPane();
		txtpnCodeNumber.setEnabled(false);
		txtpnCodeNumber.setDropMode(DropMode.ON);
		txtpnCodeNumber.setBackground(Color.WHITE);
		txtpnCodeNumber.setText("Code Number");
		txtpnCodeNumber.setBounds(35, 130, 100, 20);
		PNL_Main.add(txtpnCodeNumber);
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCode.setBounds(25, 100, 90, 21);
		PNL_Main.add(lblCode);
		
		JLabel lblDescription = new JLabel("description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescription.setBounds(25, 160, 123, 22);
		PNL_Main.add(lblDescription);
		
		txtDescriptionText = new JTextField();
		txtDescriptionText.setEnabled(false);
		txtDescriptionText.setBackground(Color.WHITE);
		txtDescriptionText.setText("description text");
		txtDescriptionText.setBounds(35, 190, 100, 20);
		PNL_Main.add(txtDescriptionText);
		txtDescriptionText.setColumns(10);
		
		JComboBox cmbxcampus = new JComboBox();
		cmbxcampus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbxcampus.setToolTipText("Campus");
		cmbxcampus.setBounds(40, 296, 130, 20);
		PNL_Main.add(cmbxcampus);
		
		JLabel lblCampus = new JLabel("campus:");
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCampus.setBounds(25, 267, 90, 14);
		PNL_Main.add(lblCampus);
		
		JLabel lblBilding = new JLabel("Bilding:");
		lblBilding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBilding.setBounds(25, 327, 82, 22);
		PNL_Main.add(lblBilding);
		
		JComboBox cmbBxBlding = new JComboBox();
		cmbBxBlding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbBxBlding.setBounds(40, 360, 130, 20);
		PNL_Main.add(cmbBxBlding);
		
		JLabel lblCapacity = new JLabel("capacity:");
		lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCapacity.setVerticalAlignment(SwingConstants.TOP);
		lblCapacity.setBounds(170, 100, 76, 22);
		PNL_Main.add(lblCapacity);
		
		txtCapacityNumber = new JTextField();
		txtCapacityNumber.setEnabled(false);
		txtCapacityNumber.setText("Capacity number");
		txtCapacityNumber.setBounds(180, 130, 100, 20);
		PNL_Main.add(txtCapacityNumber);
		txtCapacityNumber.setColumns(10);
		
		JLabel lblClassAids = new JLabel("Class aids:");
		lblClassAids.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClassAids.setBounds(352, 100, 100, 14);
		PNL_Main.add(lblClassAids);
		
		JList lstClassAids = new JList();
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
		lstClassAids.setBounds(370, 130, 100, 220);
		PNL_Main.add(lstClassAids);
		
		JCheckBox chckbxAvailable = new JCheckBox("Available");
		chckbxAvailable.setBounds(13, 65, 135, 23);
		PNL_Main.add(chckbxAvailable);
		
		JButton btnAdd = new JButton("Add -->");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds(476, 163, 100, 23);
		PNL_Main.add(btnAdd);
		
		JButton btnRemove = new JButton("remove  <--");
		btnRemove.setBounds(480, 206, 100, 23);
		PNL_Main.add(btnRemove);
		
		JList lstClassaids2 = new JList();
		lstClassaids2.setBackground(new Color(169, 169, 169));
		lstClassaids2.setForeground(new Color(255, 255, 255));
		lstClassaids2.setBounds(586, 130, 100, 220);
		PNL_Main.add(lstClassaids2);
		
		JButton btnNewClass = new JButton("New class");
		btnNewClass.setBounds(25, 423, 110, 23);
		PNL_Main.add(btnNewClass);
		
		JButton btnSaveChanges = new JButton("Save changes");
		btnSaveChanges.setBounds(172, 423, 110, 23);
		PNL_Main.add(btnSaveChanges);
		
		JButton btnBackToMainMenu = new JButton("Back to Main menu");
		btnBackToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBackToMainMenu.setBounds(629, 440, 135, 23);
		PNL_Main.add(btnBackToMainMenu);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(172, 188, 90, 25);
		PNL_Main.add(btnEdit);
	}
}
