package GUI;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import Server.*;

import javax.swing.SwingConstants;

import java.awt.Font;

public class ServerGUI extends JFrame implements ActionListener, KeyListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnStart;
	private JButton btnLogout;
	private JButton btnExit;
	private JLabel lblPort;
	private JLabel lblTitle;
	private JLabel lblSqlServerIp;
	private JLabel lblSqlServerPort;
	private JLabel lblSqlServerDb;
	private JLabel lblSqlServerPswd;
	private JLabel lblServerPort;
	private JTextField txtPort;
	private JTextField txtIp;
	private JTextField txtUser;
	private JTextField txtDBName;
	private JPasswordField passwordField;
	private JTextField txtServerPort;
	private EchoServer es;
	public static JFrame mainframe;

	public ServerGUI()
	{
		super();
		// setForeground(SystemColor.activeCaption);
		mainframe = this;
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();

	}

	private void initialize()
	{
		setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 350, 382);
		getContentPane().setLayout(null);
		getContentPane().add(GETtxtPort());
		getContentPane().add(GETtxtIp());
		getContentPane().add(gettxtUser());
		getContentPane().add(getlblPort());
		getContentPane().add(getlblTitle());
		getContentPane().add(getlblSqlServerIp());
		getContentPane().add(getlblSqlServerPort());
		getContentPane().add(getlblSqlServerDb());
		getContentPane().add(getlblSqlServerPswd());
		getContentPane().add(gettxtDBName());
		getContentPane().add(getpasswordField());
		getContentPane().add(getbtnStart());
		getContentPane().add(getbtnLogout());
		getContentPane().add(getbtnExit());
		getContentPane().add(getlblServerPort());
		getContentPane().add(gettxtServerPort());
		mainframe.setUndecorated(true);
		this.setVisible(true);
	}

	private JLabel getlblServerPort()
	{
		if (lblServerPort == null)
		{
			lblServerPort = new JLabel("server port:");
			lblServerPort.setBounds(21, 262, 164, 14);

		}
		return lblServerPort;
	}

	private JLabel getlblTitle()
	{
		if (lblTitle == null)
		{
			lblTitle = new JLabel("Server Login");
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setBounds(21, 11, 290, 40);

		}
		return lblTitle;
	}

	private JButton getbtnStart()
	{
		if (btnStart == null)
		{
			btnStart = new JButton("Start");
			btnStart.setBounds(21, 310, 89, 23);
			btnStart.addActionListener(this);
		}
		return btnStart;

	}

	private JButton getbtnLogout()
	{
		if (btnLogout == null)
		{
			btnLogout = new JButton("Logout");
			btnLogout.setBounds(120, 310, 89, 23);
			btnLogout.addActionListener(this);
			btnLogout.setEnabled(false);
		}
		return btnLogout;

	}

	private JButton getbtnExit()
	{
		if (btnExit == null)
		{
			btnExit = new JButton("Exit");
			btnExit.setBounds(222, 310, 89, 23);
			btnExit.addActionListener(this);
		}
		return btnExit;

	}

	private JTextField gettxtServerPort()
	{
		if (txtServerPort == null)
		{
			txtServerPort = new JTextField();
			txtServerPort.setText("5555");
			txtServerPort.setBounds(225, 262, 86, 20);
			txtServerPort.setColumns(10);
			txtServerPort.addKeyListener(this);
		}
		return txtServerPort;
	}

	private JPasswordField getpasswordField()
	{
		if (passwordField == null)
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(225, 209, 86, 20);
			passwordField.setText("Password");
			passwordField.addKeyListener(this);
		}
		return passwordField;
	}

	private JTextField gettxtDBName()
	{
		if (txtDBName == null)
		{

			txtDBName = new JTextField();
			txtDBName.setText("csps-db");
			txtDBName.setBounds(225, 124, 86, 20);
			txtDBName.setColumns(10);
			txtDBName.addKeyListener(this);
		}
		return txtDBName;
	}

	private JLabel getlblSqlServerPswd()
	{
		if (lblSqlServerPswd == null)
		{
			lblSqlServerPswd = new JLabel("SQL server password:");
			lblSqlServerPswd.setBounds(21, 209, 164, 14);
		}
		return lblSqlServerPswd;
	}

	private JLabel getlblSqlServerDb()
	{
		if (lblSqlServerDb == null)
		{
			lblSqlServerDb = new JLabel("SQL server DB NAME:");
			lblSqlServerDb.setBounds(21, 124, 164, 14);
		}
		return lblSqlServerDb;
	}

	private JTextField GETtxtPort()
	{
		if (txtPort == null)
		{
			txtPort = new JTextField();
			txtPort.setText("3306");
			txtPort.setBounds(225, 93, 86, 20);
			txtPort.setColumns(10);
			txtPort.addKeyListener(this);
		}
		return txtPort;
	}

	private JTextField GETtxtIp()
	{
		if (txtIp == null)
		{
			txtIp = new JTextField();
			txtIp.setText("localhost");
			txtIp.setBounds(225, 62, 86, 20);
			txtIp.setColumns(10);
			txtIp.addKeyListener(this);
		}
		return txtIp;
	}

	private JTextField gettxtUser()
	{
		if (txtUser == null)
		{
			txtUser = new JTextField();
			txtUser.setText("root");
			txtUser.setBounds(225, 181, 86, 20);
			txtUser.setColumns(10);
			txtUser.addKeyListener(this);
		}
		return txtUser;
	}

	private JLabel getlblPort()
	{
		if (lblPort == null)
		{

			lblPort = new JLabel("SQL server port:");
			lblPort.setBounds(21, 93, 164, 14);

		}
		return lblPort;
	}

	private JLabel getlblSqlServerIp()
	{
		if (lblSqlServerIp == null)
		{
			lblSqlServerIp = new JLabel("SQL server user:");
			lblSqlServerIp.setBounds(21, 180, 164, 17);
		}
		return lblSqlServerIp;
	}

	private JLabel getlblSqlServerPort()
	{
		if (lblSqlServerPort == null)
		{
			lblSqlServerPort = new JLabel("SQL server ip:");
			lblSqlServerPort.setBounds(21, 62, 164, 14);

		}
		return lblSqlServerPort;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == btnStart)
		{
			es = new EchoServer(Integer.parseInt(txtServerPort.getText()), txtIp.getText(), Integer.parseInt(txtPort.getText()), txtUser.getText(), txtDBName.getText(), new String(
					passwordField.getPassword()));
			try
			{
				es.listen(); // Start listening for connections
				btnStart.setEnabled(false);
				txtDBName.setEnabled(false);
				txtIp.setEnabled(false);
				txtPort.setEnabled(false);
				txtServerPort.setEnabled(false);
				txtUser.setEnabled(false);
				passwordField.setEnabled(false);
				btnLogout.setEnabled(true);
			} catch (Exception ex)
			{
				// Perror.sError("Could not listen for clients!");

			}
		}

		if (e.getSource() == btnLogout)
		{
			btnStart.setEnabled(true);
			txtDBName.setEnabled(true);
			txtIp.setEnabled(true);
			txtPort.setEnabled(true);
			txtServerPort.setEnabled(true);
			txtUser.setEnabled(true);
			passwordField.setEnabled(true);
			btnLogout.setEnabled(false);
			if (es.isListening())
			{
				try
				{

					es.close();
				} catch (Exception ex)
				{
					// Perror.sError("Could not close connection.");

				}
			}

		}

		if (e.getSource() == btnExit)
		{
			btnLogout.doClick();
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{

		ServerGUI turnOnSrv = new ServerGUI();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER)
		{
			if (e.getSource() == txtPort || e.getSource() == passwordField || e.getSource() == txtIp || e.getSource() == txtUser || e.getSource() == txtDBName || e.getSource() == txtServerPort)
				btnStart.doClick();
		}
		if (key == KeyEvent.VK_ESCAPE)
			btnExit.doClick();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}
}
