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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import entities.Lecturer;
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
	
	
	private DefaultCellEditor CmbTableModel;
	private ArrayList<Lecturer>  ArrayLecturer;
	private TableModel lstModel;
	private Object[][] tableData={
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
		};
	private	String columnNames[]={"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	
	
	
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
	private JScrollPane GETscroll() {
			scroll = new JScrollPane(GETtableLecturermanu());
			scroll.setEnabled(false);
			scroll.setBounds(10, 103, 724, 252);
		return scroll;
	}
	

		
		
		
		
	
	
	private JTable GETtableLecturermanu() {
		tableLecturermanu = new JTable(tableData,columnNames);
		tableLecturermanu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tableLecturermanu.setToolTipText("table ");
		tableLecturermanu.setFillsViewportHeight(true);
		tableLecturermanu.setSurrendersFocusOnKeystroke(true);
		tableLecturermanu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLecturermanu.setBackground(SystemColor.inactiveCaption);
		tableLecturermanu.setBorder(new LineBorder(new Color(0, 0, 0)));
	
		
		lstModel= new AbstractTableModel() {
			public String getColumnName(int col) {
		        return columnNames[col].toString();
		    }
		    public int getRowCount() { return tableData.length; }
		    public int getColumnCount() { return columnNames.length; }
		    public Object getValueAt(int row, int col) {
		        return tableData[row][col];
		    }
		    public boolean isCellEditable(int row, int col)
		        {  if ((col < 1 )) 
		            return false;
		              else {
		            return true;
		         		        
		        } }
		    public void setValueAt(Object value, int row, int col) {
		    	

		    	

		    	tableData[row][col] = value;
		        fireTableCellUpdated(row, col);
		    }
		    @Override  
		      public Class getColumnClass(int col) {  
		        if (col == 1)       //second column accepts only Integer values  
		            return String.class;  
		        else return Object.class;  //other columns accept String values  
		    } 
		    
		  
		};
		
		tableLecturermanu.setModel(lstModel);
		
	
		for (int x = 0;x<6;x++) {
		tableLecturermanu.getColumnModel().getColumn(x).setResizable(false);
		
		}
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
		select.setBackground(Color.RED);
		select.getEditor().getEditorComponent().setBackground(Color.yellow);
		select.getEditor().getEditorComponent().setForeground(Color.PINK);
		
		
		CmbTableModel=new DefaultCellEditor(select) ;
		
		for (int x = 1;x<6;x++) {
			//tableLecturermanu.getColumnModel().s
		tableLecturermanu.getColumnModel().getColumn(x).setCellEditor(CmbTableModel);
        		}
		return tableLecturermanu;
	}

	public void actionPerformed(ActionEvent e) {
			
		if (e.getSource() == btnSaveChanges) {
			int rows = tableLecturermanu.getRowCount();
			int columns = tableLecturermanu.getColumnCount();
			for(int i = 1 ; i < rows ; i++)
			{
			    for(int j = 1 ; j < columns ; j++)
			    {
			    	
			    	TableCellEditor tce = tableLecturermanu.getCellEditor(i, j);
			    	
			    	//tableLecturermanu.getSelectedRow(1);
			    	System.out.println("Default " + i + "-" + j + " Selection: " + tce.getCellEditorValue());
			    	System.out.println(tableLecturermanu.getModel().getValueAt(i, j));
			    }
			}
			
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

	public void setLecturers(ArrayList<Lecturer> arrayList) {
		ArrayLecturer=arrayList;
		
	//	ArrayLecturer=new HashMap<Integer, Lecturer>();
		for (int i = 0; i < arrayList.size(); i++) {
	//		ArrayLecturer.put(arrayList.get(i).getID(), arrayList.get(i));
			cmbxlecturer.addItem(arrayList.get(i).getID() + ":"+ arrayList.get(i).getName());
			for (int hour=0;hour<14;hour++){
				for (int day=1;day<7;day++){
					int tmp=(day-1)*14+hour;
					//tableLecturermanu.getModel().setValueAt(arrayList.get(i).getPreferedSchedualArray()[tmp], hour, day);
					tableLecturermanu.getModel().setValueAt("n/a" , hour, day);
				}
				
				
			}
		}
		
		
		
		
	}

	
	
	//int[]because every entry will store {cellX,cellY}	public void something(){
	
}
