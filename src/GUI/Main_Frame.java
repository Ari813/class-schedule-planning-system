package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import common.ChatIF;
import Client.*;
import entities.*;
import Controllers.LecturerController;
import Controllers.ManagerController;
import MsgPackage.*;

public class Main_Frame extends JFrame implements ActionListener, ChatIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final public static int setX = 505, setY = 525;
	public static Main_Frame window;
	public static LoginGUI loginUserGUI;
	// public JFrame mainFrm;
	private ManagerController manger;
	 private LecturerController lecturer;
	 private Login lgnUsr;
	 private ChatClient client;
	// private MagiController magi;

	private int admin;
	final public static int MANAGER = 1; //
	final public static int LECTURER = 2; //
	final public static int MAGI = 3; //

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Main_Frame(1);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Frame(int admin) {
		this.admin = admin;
		start();
		
	}

	public void start() {
		loginUserGUI = new LoginGUI(this);
		setType(Type.UTILITY);
		setBounds(100, 100, 550, 550);
		getContentPane().add(loginUserGUI);

	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void ChooseAdmin() {
		if (admin == MANAGER) {
			manger = new ManagerController(this,client);
		} else if (admin == LECTURER) {
			 lecturer=new LecturerController(this,client);
		
		}
	}

	private void initialize() {

		// mainFrm = new JFrame();
		getContentPane().setBackground(SystemColor.menu);
		setBackground(SystemColor.inactiveCaptionBorder);
		setForeground(SystemColor.controlLtHighlight);
		setLocale(new Locale("en", "IL"));		
		setResizable(false);
		setAlwaysOnTop(true);
		setPreferredSize(new Dimension(800, 600));
		setName("Main_Panel");
		setTitle("Time table schedualing system");
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblTimeTableSchedualing = new JLabel(
				"Time Table Schedualing System");
		lblTimeTableSchedualing
				.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeTableSchedualing.setLocation(new Point(50, 0));
		lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
		lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED,
				null, null, null, null));
		getContentPane().add(lblTimeTableSchedualing);
		ChooseAdmin();
		getContentPane().repaint();

		/*
		 * Main_Menu mnn = new Main_Menu(mainFrm);
		 * pnl_mainFrm.setContentPane(mnn);
		 * 
		 * 
		 * 
		 * /* JPanel panel = new JPanel(); panel.setBounds(10, 86, 774, 474);
		 * mainFrm.getContentPane().add(panel);
		 * 
		 * JPanel PNL_Main = new JPanel(); PNL_Main.setToolTipText("");
		 * PNL_Main.setMinimumSize(new Dimension(774, 474));
		 * PNL_Main.setMaximumSize(new Dimension(774, 474));
		 * PNL_Main.setBounds(10, 86, 774, 474); PNL_Main.setLayout(null);
		 * Lecturer_Preferences PNL =new Lecturer_Preferences(); frmLec.add(
		 * PNL.PNL_Main); /
		 */

		/*
		 * Main_Menu PNL=new Main_Menu(mainFrm); mainFrm.add(PNL);
		 */

	}
	public void handleLoginGUI() {

		lgnUsr = new Login(loginUserGUI.getHost(), loginUserGUI.getPort(),
				loginUserGUI.getId(), loginUserGUI.getPass());
		LoginPack lgnmsg = new LoginPack(lgnUsr);
		try {
			client = new ChatClient(lgnUsr.getHost(), lgnUsr.getPort(), this);
			client.handleMessageFromClientUI(lgnmsg);
			lgnmsg = (LoginPack) client.getMessage();
			lgnUsr = lgnmsg.getUsr();
			admin=lgnUsr.getLoginPermissionLevel();
			handleLogin();
		} catch (IOException exception) {
			//Perror.pError("Can't setup connection!");
		}

	}

	public void handleLogoutGUI() {
		LogoutPack lgotmsg = new LogoutPack(lgnUsr);
		client.handleMessageFromClientUI(lgotmsg);
		getContentPane().removeAll();
		setBounds(100, 100, 550, 550);
		setPreferredSize(new Dimension(550,550));
		getContentPane().add(loginUserGUI);
		getContentPane().repaint();
		
	}

	private void handleLogin() {
		getContentPane().remove(loginUserGUI);
		initialize();
		
		
		}
	@Override
	public void display(Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
