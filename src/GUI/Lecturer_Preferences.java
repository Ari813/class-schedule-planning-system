package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.DefaultTableModel;

import Controllers.LecturerController;
import Controllers.ManagerController;

public class Lecturer_Preferences extends JPanel  implements ActionListener,
ListSelectionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox select;
	private JTextField txtLecturerPreferences;
	private JTable tableLecturermanu;
	private JButton btnSaveChanges;
	private JButton btnBackToMainMenu;
  	private JComboBox cmbxlecturer;
  	private JScrollPane scroll;
  	private Component horizontalStrut_2;
  	private Component horizontalStrut_1;
	public JPanel PNL_Main;
	private LecturerController lec;
	private ManagerController manager;
	static Color[] colors = {Color.BLUE, Color.GRAY, Color.RED};
	static String[] strings = {"Test1", "Test2", "Test3"};
	
	/**
	 * Create the panel.
	 */
	public Lecturer_Preferences(LecturerController lec,ManagerController manger) {
		
	
		super();
		this.lec=lec;
		this.manager=manger;
		initialize();}
	
	/*/	JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
	lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
	lblTimeTableSchedualing.setLocation(new Point(50, 0));
	lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
	lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
	lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
/*/
		private void initialize() {

		pnl();
		
		
		
		PNL_Main.add(GETbtnSaveChanges());
		
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtLecturerPreferences());
			
		PNL_Main.add(GETcmbxlecturer());
		PNL_Main.add(GETscroll());
		
		 sethorizontalStrut();
		PNL_Main.setVisible(true);
	}
	private void pnl() {
		
		PNL_Main = new JPanel();
		PNL_Main.setBorder(new EmptyBorder(0, 0, 0, 0));
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
	
		PNL_Main.setLayout(null);
		
	}
	private Component GETbtnSaveChanges() {
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setBounds(151, 434, 160, 29);
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveChanges.addActionListener(this);
		return btnSaveChanges;
	}
	private JButton GETbtnBackToMainMenu() {
		 btnBackToMainMenu = new JButton("Discard");
			btnBackToMainMenu.setBounds(462, 434, 160, 29);
			btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnBackToMainMenu.addActionListener(this);
		return btnBackToMainMenu;
	}
	private JTextField GETtxtLecturerPreferences() {
		txtLecturerPreferences = new JTextField();
		txtLecturerPreferences.setEditable(false);
		txtLecturerPreferences.setBounds(0, 4, 774, 40);
		txtLecturerPreferences.setText("Lecturer Preferences");
		txtLecturerPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerPreferences.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerPreferences.setColumns(10);
		txtLecturerPreferences.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerPreferences.setBackground(new Color(176, 224, 230));
		return txtLecturerPreferences;
	}
	private void sethorizontalStrut() {
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 80, 774, 12);
		PNL_Main.add(horizontalStrut_1);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(5, 424, 759, 5);
		horizontalStrut_2.setBackground(Color.BLACK);
		PNL_Main.add(horizontalStrut_2);
		
	}
	private JComboBox GETcmbxlecturer() {
		cmbxlecturer = new JComboBox();
	  	cmbxlecturer.setBounds(10, 53, 724, 20);
	  	cmbxlecturer.setModel(new DefaultComboBoxModel(new String[] {"Empty"}));
	  	cmbxlecturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	cmbxlecturer.setToolTipText("Edit class list");
	  	cmbxlecturer.setMaximumRowCount(52);
		return cmbxlecturer;
	}
	private Component GETscroll() {
			scroll = new JScrollPane(GETtableLecturermanu());
			scroll.setEnabled(false);
			scroll.setBounds(10, 103, 724, 252);
		return scroll;
	}
	private JTable GETtableLecturermanu() {
		tableLecturermanu = new JTable();
		tableLecturermanu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tableLecturermanu.setToolTipText("table ");
		tableLecturermanu.setFillsViewportHeight(true);
		tableLecturermanu.setSurrendersFocusOnKeystroke(true);
		tableLecturermanu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLecturermanu.setBackground(SystemColor.inactiveCaption);
		tableLecturermanu.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableLecturermanu.setModel(new DefaultTableModel(
			new Object[][] {
				{"8:00-9:00", null, null, null, null, null, null},
				{"9:00-10:00", null, null, null, null, null, null},
				{"10:00-11:00", null, null, null, null, null, null},
				{"11:00-12:00", null, null, null, null, null, null},
				{"12:00-13:00", null, null, null, null, null, null},
				{"13:00-14:00", null, null, null, null, null, null},
				{"14:00-15:00", null, null, null, null, null, null},
				{"15:00-16:00", null, null, null, null, null, null},
				{"16:00-17:00", null, null, null, null, null, null},
				{"17:00-18:00", null, null, null, null, null, null},
				{"18:00-19:00", null, null, null, null, null, null},
				{"19:00-20:00", null, null, null, null, null, null},
				{"20:00-21:00", null, null, null, null, null, null},
				{"21:00-22:00", null, null, null, null, null, null},
			},
			new String[] {
				"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableLecturermanu.getColumnModel().getColumn(0).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(1).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(2).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(3).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(4).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(5).setResizable(false);
		tableLecturermanu.getColumnModel().getColumn(6).setResizable(false);
		
		//tableLecturermanu.getColumnModel().getColumn(1).setPreferredWidth(78);

		tableLecturermanu.setColumnSelectionAllowed(true);
		tableLecturermanu.setCellSelectionEnabled(true);
		tableLecturermanu.setBounds(35, 127, 600, 224);
	
	
		select = new JComboBox();
		select.addItem("Avoid");
		select.addItem("prefr");
		select.addItem("N\\A");
		select.setEnabled(true);
		select.setVisible(true);
		select.getEditor().getEditorComponent().setBackground(Color.yellow);
		select.getEditor().getEditorComponent().setForeground(Color.PINK);
	
		tableLecturermanu.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(select));
        tableLecturermanu.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(select));
        
		return tableLecturermanu;
	}

	public void actionPerformed(ActionEvent e) {
			
		if (e.getSource() == btnSaveChanges) {
			
		}
		if (e.getSource() == btnBackToMainMenu) {
			if (lec==null){
				manager.BacktoMainMenu(this.PNL_Main);
			}
			else{
				System.exit(1);
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
