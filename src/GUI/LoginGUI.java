package GUI;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;
import javax.swing.JButton;



import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Rectangle;
import java.awt.Dimension;

public class LoginGUI extends JPanel implements ActionListener, KeyListener,
		FocusListener, ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final public static Integer DEFAULT_PORT = 5555;
	final public static String DEFAULT_HOST = "localhost";
	final public static String Amit_HOST = "5.29.162.248";

	private JTextField txtId;
	private JPasswordField txtPassword;
	private JTextField txtHostName;
	private JTextField txtPort;
	private JLabel lblId;
	private JLabel lblPassword;
	private JLabel lblHostNameaddress;
	private JLabel lblPortNumber;
	private JLabel lblUser;
	private JLabel lblServer;
	private JLabel lblWelcome;
	private JButton btnLogin;
	private JButton btnExit;
	private JCheckBox chkBoxRememberMe;
	private JCheckBox chkBoxSaveSrvr;
	private Main_Frame mainGUI;

	private boolean rememberLogin;
	private boolean rememberServer;

	public LoginGUI(Main_Frame mainGUI) {
		super();
		setPreferredSize(new Dimension(800, 600));
		setSize(new Dimension(774, 600));
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		setBounds(new Rectangle(100, 100, 880, 600));
		
		this.mainGUI = mainGUI;
		rememberLogin = false;
		rememberServer = false;
		initialize();

	}

	private void initialize() {

		setBorder(new TitledBorder(null, "Login",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 774, 474);
		setLayout(null);

		add(getLblId());
		add(getLblPassword());
		add(getLblHostNameaddress());
		add(getLblPortNumber());
		add(getLblUser());
		add(getLblServer());
		add(getLblWelcome());

		add(getTxtId());
		add(getTxtPassword());
		add(getTxtHostName());
		add(getTxtPort());

		add(getBtnLogin());
		add(getBtnExit());

		add(getChkBoxRememberMe());
		add(getChkBoxSaveSrvr());

		enableButtons();
		this.setVisible(true);
	}

	private JCheckBox getChkBoxSaveSrvr() {
		if (chkBoxSaveSrvr == null) {
			chkBoxSaveSrvr = new JCheckBox("Save");
			chkBoxSaveSrvr.setFont(new Font("Dialog", Font.PLAIN, 16));
			chkBoxSaveSrvr.setToolTipText("Remember server connection details");
			chkBoxSaveSrvr.setBounds(464, 354, 170, 23);
			chkBoxSaveSrvr.addChangeListener(this);
		}
		return chkBoxSaveSrvr;
	}

	private JLabel getLblWelcome() {
		if (lblWelcome == null) {
			lblWelcome = new JLabel("WELCOME");
			lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
			lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblWelcome.setBackground(SystemColor.inactiveCaptionBorder);
			lblWelcome.setBounds(10, 35, 780, 89);
		}
		return lblWelcome;
	}

	private JCheckBox getChkBoxRememberMe() {
		if (chkBoxRememberMe == null) {
			chkBoxRememberMe = new JCheckBox("Remember me");
			chkBoxRememberMe.setFont(new Font("Dialog", Font.PLAIN, 16));
			chkBoxRememberMe.setToolTipText("Remember me");
			chkBoxRememberMe.setBounds(464, 242, 170, 23);
			chkBoxRememberMe.addChangeListener(this);
		}
		return chkBoxRememberMe;
	}

	// ------------------
	// Component Getters
	// ------------------
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID:");
			lblId.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblId.setBounds(165, 190, 145, 14);
		}
		return lblId;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblPassword.setBounds(165, 215, 145, 14);
		}
		return lblPassword;
	}

	private JLabel getLblHostNameaddress() {
		if (lblHostNameaddress == null) {
			lblHostNameaddress = new JLabel("Host Name/Address:");
			lblHostNameaddress.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblHostNameaddress.setBounds(165, 303, 227, 14);
		}
		return lblHostNameaddress;
	}

	private JLabel getLblPortNumber() {
		if (lblPortNumber == null) {
			lblPortNumber = new JLabel("Port Number:");
			lblPortNumber.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblPortNumber.setBounds(165, 328, 145, 14);
		}
		return lblPortNumber;
	}

	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("User:");
			lblUser.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblUser.setBounds(76, 163, 95, 14);
		}
		return lblUser;
	}

	private JLabel getLblServer() {
		if (lblServer == null) {
			lblServer = new JLabel("Server:");
			lblServer.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblServer.setBounds(76, 271, 95, 14);
		}
		return lblServer;
	}

	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setFont(new Font("Dialog", Font.PLAIN, 16));
			txtId.setText("ID");
			txtId.setBounds(464, 190, 170, 20);
			txtId.setColumns(10);
			txtId.addKeyListener(this);
			txtId.addFocusListener(this);
		}
		return txtId;
	}

	private JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JPasswordField();
			txtPassword.setFont(new Font("Dialog", Font.PLAIN, 16));
			txtPassword.setText("password");
			txtPassword.setBounds(464, 215, 170, 20);
			txtPassword.setColumns(10);
			txtPassword.addKeyListener(this);
			txtPassword.addFocusListener(this);
		}
		return txtPassword;
	}

	private JTextField getTxtHostName() {
		if (txtHostName == null) {
			txtHostName = new JTextField();
			txtHostName.setFont(new Font("Dialog", Font.PLAIN, 16));
			txtHostName.setText(DEFAULT_HOST);
			txtHostName.setBounds(464, 300, 170, 20);
			txtHostName.setColumns(10);
			txtHostName.addKeyListener(this);
		}
		return txtHostName;
	}

	private JTextField getTxtPort() {
		if (txtPort == null) {
			txtPort = new JTextField();
			txtPort.setFont(new Font("Dialog", Font.PLAIN, 16));
			txtPort.setText(DEFAULT_PORT.toString());
			txtPort.setBounds(464, 325, 170, 20);
			txtPort.setColumns(10);
			txtPort.addKeyListener(this);
		}
		return txtPort;
	}

	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Login");
			btnLogin.setBounds(new Rectangle(0, 0, 160, 29));
			btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnLogin.setBounds(165, 514, 160, 29);
			btnLogin.addActionListener(this);
		}
		return btnLogin;
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.setBounds(new Rectangle(0, 0, 160, 29));
			btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnExit.setBounds(485, 514, 160, 29);
			btnExit.addActionListener(this);
		}
		return btnExit;
	}

	// ------------------
	// Getters
	// ------------------
	public String getHost() {
		if (txtHostName.getText().equals("localhost1"))
			return new String(Amit_HOST);
		return new String(txtHostName.getText());
	}

	public int getPort() {
		try {
			return Integer.parseInt(txtPort.getText());
		} catch (Exception exception) {
		//	common.Perror.pError("Invalid port number!\nconnecting with default port.");
		}
		return DEFAULT_PORT;
	}

	public int getId() {
		try {
			return Integer.parseInt(txtId.getText());
		} catch (Exception exception) {
		//	common.Perror.pError("Invalid ID number!");
		}
		return -1;
	}

	public String getPass() {
		return new String(txtPassword.getPassword());
	}

	// ------------------
	// ------------------

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			int ID;
			if ((ID = getId()) != -1)
				mainGUI.handleLoginGUI();
		}
		if (e.getSource() == btnExit) {
			System.exit(0);
		}
	}

	public void enableButtons() {
		txtId.setEditable(true);
		txtPassword.setEditable(true);
		txtHostName.setEditable(true);
		txtPort.setEditable(true);
	}

	public void cleanLoginGUI() {

		txtId.setEditable(false);
		txtPassword.setEditable(false);
		txtHostName.setEditable(false);
		txtPort.setEditable(false);
		if (!rememberLogin) {
			txtId.setText("ID");
			txtPassword.setText("password");
		}
		if (!rememberServer) {
			txtHostName.setText(DEFAULT_HOST);
			txtPort.setText(DEFAULT_PORT.toString());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			if (e.getSource() == txtId || e.getSource() == txtPassword
					|| e.getSource() == txtHostName || e.getSource() == txtPort)
				btnLogin.doClick();
		}
		if (key == KeyEvent.VK_ESCAPE)
			btnExit.doClick();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == txtPassword && getPass().equals("password"))
			txtPassword.setText("");
		if (e.getSource() == txtId && txtId.getText().equals("ID"))
			txtId.setText("");
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == txtPassword && getPass().equals(""))
			txtPassword.setText("password");
		if (e.getSource() == txtId && txtId.getText().equals(""))
			txtId.setText("ID");
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == chkBoxRememberMe)
			if (chkBoxRememberMe.isSelected())
				rememberLogin = true;
			else
				rememberLogin = false;
			
		if (e.getSource() == chkBoxSaveSrvr)
			if (chkBoxRememberMe.isSelected())
				rememberServer = true;
			else
				rememberServer = false;


	}
}
