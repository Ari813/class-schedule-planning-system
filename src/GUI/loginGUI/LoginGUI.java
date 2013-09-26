package GUI.loginGUI;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;
import javax.swing.JButton;


import GUI.Main_Frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class LoginGUI extends JPanel implements ActionListener, KeyListener,
		FocusListener, ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final public static Integer DEFAULT_PORT = 5555;
	final public static String DEFAULT_HOST = "localhost";
	final public static String Amit_HOST = "79.181.196.152";

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
		this.mainGUI = mainGUI;
		rememberLogin = false;
		rememberServer = false;
		initialize();

	}

	private void initialize() {

		setBorder(new TitledBorder(null, "Welcome Screen",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, mainGUI.setX, mainGUI.setY);
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
			chkBoxSaveSrvr.setToolTipText("Remember server connection details");
			chkBoxSaveSrvr.setBounds(272, 321, 170, 23);
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
			lblWelcome.setBounds(10, 35, 480, 89);
		}
		return lblWelcome;
	}

	private JCheckBox getChkBoxRememberMe() {
		if (chkBoxRememberMe == null) {
			chkBoxRememberMe = new JCheckBox("Remember me");
			chkBoxRememberMe.setToolTipText("Remember me");
			chkBoxRememberMe.setBounds(272, 212, 170, 23);
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
			lblId.setBounds(128, 162, 145, 14);
		}
		return lblId;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setBounds(128, 187, 145, 14);
		}
		return lblPassword;
	}

	private JLabel getLblHostNameaddress() {
		if (lblHostNameaddress == null) {
			lblHostNameaddress = new JLabel("Host Name/Address:");
			lblHostNameaddress.setBounds(128, 275, 145, 14);
		}
		return lblHostNameaddress;
	}

	private JLabel getLblPortNumber() {
		if (lblPortNumber == null) {
			lblPortNumber = new JLabel("Port Number:");
			lblPortNumber.setBounds(128, 300, 145, 14);
		}
		return lblPortNumber;
	}

	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("User:");
			lblUser.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			lblUser.setBounds(69, 135, 46, 14);
		}
		return lblUser;
	}

	private JLabel getLblServer() {
		if (lblServer == null) {
			lblServer = new JLabel("Server:");
			lblServer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			lblServer.setBounds(69, 243, 46, 14);
		}
		return lblServer;
	}

	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setText("ID");
			txtId.setBounds(272, 162, 170, 20);
			txtId.setColumns(10);
			txtId.addKeyListener(this);
			txtId.addFocusListener(this);
		}
		return txtId;
	}

	private JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JPasswordField();
			txtPassword.setText("password");
			txtPassword.setBounds(272, 187, 170, 20);
			txtPassword.setColumns(10);
			txtPassword.addKeyListener(this);
			txtPassword.addFocusListener(this);
		}
		return txtPassword;
	}

	private JTextField getTxtHostName() {
		if (txtHostName == null) {
			txtHostName = new JTextField();
			txtHostName.setText(DEFAULT_HOST);
			txtHostName.setBounds(272, 272, 170, 20);
			txtHostName.setColumns(10);
			txtHostName.addKeyListener(this);
		}
		return txtHostName;
	}

	private JTextField getTxtPort() {
		if (txtPort == null) {
			txtPort = new JTextField();
			txtPort.setText(DEFAULT_PORT.toString());
			txtPort.setBounds(272, 297, 170, 20);
			txtPort.setColumns(10);
			txtPort.addKeyListener(this);
		}
		return txtPort;
	}

	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Login");
			btnLogin.setBounds(10, 446, 89, 43);
			btnLogin.addActionListener(this);
		}
		return btnLogin;
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.setBounds(404, 446, 86, 43);
			btnExit.addActionListener(this);
		}
		return btnExit;
	}

	// ------------------
	// Getters
	// ------------------
	public String getHost() {
		if (txtHostName.getText().equals("aj"))
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
