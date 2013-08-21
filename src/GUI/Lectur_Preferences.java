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

public class Lectur_Preferences {

	private JFrame frmLec;
	private JTextField txtDescriptionText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lectur_Preferences window = new Lectur_Preferences();
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
	public Lectur_Preferences() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLec = new JFrame();
		frmLec.getContentPane().setBackground(SystemColor.menu);
		frmLec.setBackground(SystemColor.inactiveCaptionBorder);
		frmLec.setForeground(SystemColor.controlLtHighlight);
		frmLec.setLocale(new Locale("en", "IL"));
		frmLec.setType(Type.UTILITY);
		frmLec.setResizable(false);
		frmLec.setAlwaysOnTop(true);
		frmLec.setPreferredSize(new Dimension(800, 600));
		frmLec.setName("Main_Panel");
		frmLec.setTitle("Time table schedualing system");
		frmLec.setMinimumSize(new Dimension(800, 600));
		frmLec.setMaximumSize(new Dimension(800, 600));
		frmLec.setBounds(100, 100, 450, 300);
		frmLec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLec.getContentPane().setLayout(null);
		
		JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
		lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeTableSchedualing.setLocation(new Point(50, 0));
		lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
		lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmLec.getContentPane().add(lblTimeTableSchedualing);
		
		JPanel PNL_Main = new JPanel();
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 86, 774, 474);
		frmLec.getContentPane().add(PNL_Main);
		PNL_Main.setLayout(null);
		
		JLabel lblEditClass = new JLabel("Edit Class:");
		lblEditClass.setBounds(5, 5, 82, 22);
		lblEditClass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PNL_Main.add(lblEditClass);
		
		JComboBox cmbxEditClass = new JComboBox();
		cmbxEditClass.setToolTipText("Edit class list");
		cmbxEditClass.setBounds(83, 38, 650, 20);
		cmbxEditClass.setMaximumRowCount(52);
		PNL_Main.add(cmbxEditClass);
		
		JRadioButton rdbtnAvailable = new JRadioButton("Available");
		rdbtnAvailable.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnAvailable.setVerticalAlignment(SwingConstants.TOP);
		rdbtnAvailable.setForeground(Color.BLACK);
		rdbtnAvailable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAvailable.setBounds(6, 65, 109, 23);
		PNL_Main.add(rdbtnAvailable);
		
		JTextPane txtpnCodeText = new JTextPane();
		txtpnCodeText.setBackground(Color.WHITE);
		txtpnCodeText.setText("code text");
		txtpnCodeText.setBounds(35, 125, 135, 20);
		PNL_Main.add(txtpnCodeText);
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCode.setBounds(25, 100, 46, 21);
		PNL_Main.add(lblCode);
		
		JLabel lblDescription = new JLabel("description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescription.setBounds(25, 160, 123, 22);
		PNL_Main.add(lblDescription);
		
		txtDescriptionText = new JTextField();
		txtDescriptionText.setBackground(Color.WHITE);
		txtDescriptionText.setText("description text");
		txtDescriptionText.setBounds(37, 187, 135, 20);
		PNL_Main.add(txtDescriptionText);
		txtDescriptionText.setColumns(10);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(172, 224, 90, 25);
		PNL_Main.add(btnEdit);
		
		JComboBox cmbxcampus = new JComboBox();
		cmbxcampus.setToolTipText("Campus");
		cmbxcampus.setBounds(40, 296, 130, 20);
		PNL_Main.add(cmbxcampus);
	}
}
