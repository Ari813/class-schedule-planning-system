package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Main_Frame {

	private JFrame mainFrm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame window = new Main_Frame();
					window.mainFrm.setVisible(true);
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
		mainFrm = new JFrame();
		mainFrm.getContentPane().setBackground(SystemColor.menu);
		mainFrm.setBackground(SystemColor.inactiveCaptionBorder);
		mainFrm.setForeground(SystemColor.controlLtHighlight);
		mainFrm.setLocale(new Locale("en", "IL"));
		mainFrm.setType(Type.UTILITY);
		mainFrm.setResizable(false);
		mainFrm.setAlwaysOnTop(true);
		mainFrm.setPreferredSize(new Dimension(800, 600));
		mainFrm.setName("Main_Panel");
		mainFrm.setTitle("Time table schedualing system");
		mainFrm.setMinimumSize(new Dimension(800, 600));
		mainFrm.setMaximumSize(new Dimension(800, 600));
		mainFrm.setBounds(100, 100, 450, 300);
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrm.getContentPane().setLayout(null);
		
		JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
		lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeTableSchedualing.setLocation(new Point(50, 0));
		lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
		lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		mainFrm.getContentPane().add(lblTimeTableSchedualing);
		/*Main_Menu mnn = new Main_Menu(mainFrm);
		pnl_mainFrm.setContentPane(mnn);
		
		
		
		/*
		JPanel panel = new JPanel();
		panel.setBounds(10, 86, 774, 474);
		mainFrm.getContentPane().add(panel);
		
		JPanel PNL_Main = new JPanel();
		PNL_Main.setToolTipText("");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 86, 774, 474);
		PNL_Main.setLayout(null);
		Lecturer_Preferences PNL =new Lecturer_Preferences();
			frmLec.add( PNL.PNL_Main);
		/*/
		
		/*Main_Menu PNL=new Main_Menu(mainFrm);
		mainFrm.add(PNL);*/
		
	}
}
