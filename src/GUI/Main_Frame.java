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

public class Main_Frame {

	private JFrame frmLec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame window = new Main_Frame();
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
	public Main_Frame() {
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
		PNL_Main.setToolTipText("");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 86, 774, 474);
		frmLec.getContentPane().add(PNL_Main);
		PNL_Main.setLayout(null);
	}
}
