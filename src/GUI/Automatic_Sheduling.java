package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Algorithm.Database;
import Algorithm.GeneticAlgorithmRun;
import Algorithm.Individual;
import Controllers.LecturerController;
import Controllers.ManagerController;

import javax.swing.JProgressBar;

public class Automatic_Sheduling extends JPanel implements ActionListener, ListSelectionListener, KeyListener
{
	private Component horizontalStrut_2;
	private Component horizontalStrut_1;
	private static final long serialVersionUID = 1L;
	private JTextField txtLecturerPreferences;
	public JPanel PNL_Main;
	private JComboBox cmbBxSemster;
	private JButton btnOpenFolder;
	private JButton btnBackToMainMenu;
	private JLabel lblTimeTableSchedualing;
	private JProgressBar progressBar;
	private LecturerController lec;
	private ManagerController manager;
	static Color[] colors = { Color.BLUE, Color.GRAY, Color.RED };
	static String[] strings = { "Test1", "Test2", "Test3" };

	private Database allDatabase;
	private JLabel lblPopulationCounter;
	private JTextField txtLoopCounter;
	private JTextField txtRunTime;
	private JLabel lblRunTime;
	private JLabel panel_pic;

	/**
	 * Create the panel.
	 */

	/*
	 * / lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
	 * lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
	 * lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
	 * lblTimeTableSchedualing.setLocation(new Point(50, 0));
	 * lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
	 * lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
	 * lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
	 * lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED,
	 * null, null, null, null));
	 * 
	 * /
	 */
	public Automatic_Sheduling(ManagerController mng)
	{

		super();
		this.manager = mng;
		initialize();

		// manager.loadOptionalTimeTables();
		// manager.startalgo();

	}

	public void setData(Database database)
	{
		allDatabase = database;

	}

	private void initialize()
	{
		pnl();
		PNL_Main.add(GETbtnSaveChanges());
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtLecturerPreferences());

		PNL_Main.add(horizontalStrut1());
		PNL_Main.add(horizontalStrut2());

		PNL_Main.add(getprogressBar());

		PNL_Main.add(gettxtLoopCounter());
		txtLoopCounter.setColumns(10);

		PNL_Main.add(getlblPopulationCounter());

		PNL_Main.add(gettxtRunTime());

		PNL_Main.add(getlblRunTime());
		
		panel_pic = new JLabel();
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("C:\\Application\\ICON.JPG"));
			panel_pic = new JLabel(new ImageIcon(myPicture));
			//add(panel_pic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		panel_pic.setBounds(79, 121, 660, 252);
		PNL_Main.add(panel_pic);
		// PNL_Main.add(GETcmbBxSemster());

	}

	private Component getlblRunTime()
	{
		lblRunTime = new JLabel("Run time");
		lblRunTime.setBounds(643, 103, 121, 14);
		return lblRunTime;
	}

	private Component gettxtRunTime()
	{
		txtRunTime = new JTextField();
		txtRunTime.setEditable(false);
		txtRunTime.setColumns(10);
		txtRunTime.setBounds(643, 121, 121, 20);
		return txtRunTime;
	}

	private Component getlblPopulationCounter()
	{
		lblPopulationCounter = new JLabel("Population Counter");
		lblPopulationCounter.setBounds(10, 103, 121, 14);
		return lblPopulationCounter;
	}

	private Component gettxtLoopCounter()
	{
		txtLoopCounter = new JTextField();
		txtLoopCounter.setEditable(false);
		txtLoopCounter.setBounds(10, 121, 121, 20);
		txtLoopCounter.setColumns(10);
		return txtLoopCounter;
	}

	private Component getprogressBar()
	{
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 384, 754, 29);
		return progressBar;
	}

	private void pnl()
	{

		PNL_Main = new JPanel();
		PNL_Main.setBorder(new EmptyBorder(0, 0, 0, 0));
		PNL_Main.setToolTipText("Running...");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
		PNL_Main.setLayout(null);

	}

	// /////////////////all class////
	private Component horizontalStrut1()
	{
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 80, 774, 12);
		return horizontalStrut_1;
	}

	private Component horizontalStrut2()
	{
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(5, 424, 759, 5);
		horizontalStrut_2.setBackground(Color.BLACK);
		return horizontalStrut_2;
	}

	// /////////////////////////////////

	private JTextField GETtxtLecturerPreferences()
	{
		if (txtLecturerPreferences == null)
		{
			txtLecturerPreferences = new JTextField();
			txtLecturerPreferences.setEditable(false);
			txtLecturerPreferences.setBounds(0, 4, 774, 40);
			txtLecturerPreferences.setText("Automatic Scheduling is running");
			txtLecturerPreferences.setHorizontalAlignment(SwingConstants.CENTER);
			txtLecturerPreferences.setFont(new Font("Tahoma", Font.BOLD, 20));
			txtLecturerPreferences.setColumns(10);
			txtLecturerPreferences.setBorder(UIManager.getBorder("DesktopIcon.border"));
			txtLecturerPreferences.setBackground(new Color(176, 224, 230));
		}
		return txtLecturerPreferences;
	}

	private JButton GETbtnBackToMainMenu()
	{
		if (btnBackToMainMenu == null)
		{
			btnBackToMainMenu = new JButton("Discard");
			btnBackToMainMenu.setBounds(475, 434, 121, 29);
			btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));

		}

		return btnBackToMainMenu;
	}

	private JButton GETbtnSaveChanges()
	{
		if (btnOpenFolder == null)
		{
			btnOpenFolder = new JButton("Open");
			btnOpenFolder.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				}
			});
			btnOpenFolder.setBounds(177, 434, 121, 29);
			btnOpenFolder.setFont(new Font("Tahoma", Font.PLAIN, 16));

		}
		return btnOpenFolder;
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnOpenFolder)
		{
			File foler = new File("C:\\scheduling"); // path to the directory to be opened
			Desktop desktop = null;
			if (Desktop.isDesktopSupported())
			{
				desktop = Desktop.getDesktop();
			}

			try
			{
				desktop.open(foler);
			} catch (IOException e1)
			{
			
			}

		}
		if (e.getSource() == btnBackToMainMenu)
		{
			// manger.stopalgo();
			manager.stopAutoSchedualing();
			manager.BacktoMainMenu(this.PNL_Main);
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{
	
	}

	public void addActions()
	{
		btnBackToMainMenu.addActionListener(this);
		btnOpenFolder.addActionListener(this);
		manager.startAutoSchedualing();
		manager.startTimer();
	}

	public void updateProgressBar(double fitness)
	{
		progressBar.setValue((int) Math.round(fitness * 100));
	}

	public void updatePopCounter(Integer counter)
	{
		txtLoopCounter.setText(counter.toString());
	}

	public void updaterunTime(String time)
	{
		txtRunTime.setText(time);
	}
}
