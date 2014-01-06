package GUI;

import java.awt.EventQueue;
import java.util.Iterator;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.UIManager;

import java.awt.FlowLayout;

import javax.swing.CellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
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
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLayeredPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JScrollBar;

import net.miginfocom.swing.MigLayout;

import javax.swing.ScrollPaneConstants;

import org.omg.CosNaming.IstringHelper;

import com.jgoodies.forms.layout.ColumnSpec;

import entities.Course;
import entities.Faculty;
import entities.Lecturer;
import Controllers.LecturerController;
import Controllers.ManagerController;

import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/*/JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
 lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
 lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
 lblTimeTableSchedualing.setLocation(new Point(50, 0));
 lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
 lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
 lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
 lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
 /*/

public class Course_Settings extends JPanel implements ActionListener, TableModelListener, ListSelectionListener, FocusListener, KeyListener
{
	private JTextField txtCourseSttings;
	private JTable table;
	private JButton btnSaveChanges;
	private JButton btnBackToMainMenu;
	private JComboBox cmbxFaculty;
	private JScrollPane scroll;
	private Component horizontalStrut_2;
	private Component horizontalStrut_1;
	public JPanel PNL_Main;
	private LecturerController lec;
	private ManagerController manager;
	private Object[][] tableData = { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, };
	private TableModel lstModel;
	private JTableHeader head;
	private ArrayList<Faculty> arrayFaculty;
	private ArrayList<Faculty> arrayCourseFaculty;
	// private Map<Integer, Course> CoursePerFuculty ;
	private ArrayList<Course> arrayCourse;
	private Map<Integer, ArrayList<Course>> CoursePerFuculty2;
	private String columnNames[] = { "# of student", "course ID", "course description" };
	private ArrayList<Course> courseSET;
	private TableRowSorter sorter;
	private int FacultyIndex;
	private boolean clickflag;

	public static int maxrow = 1;

	public Course_Settings(ManagerController mng)
	{

		super();
		this.manager = mng;
		initialize();

	}

	private void initialize()
	{

		pnl();

		PNL_Main.add(GETbtnSaveChanges());
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtCourseSttings());
		PNL_Main.add(horizontalStrut2());
		PNL_Main.add(horizontalStrut1());
		PNL_Main.add(GETscroll());
		PNL_Main.add(GETcmbxFaculty());

		// PNL_Main.add(table);
	}

	private void pnl()
	{

		PNL_Main = new JPanel();
		PNL_Main.setBorder(new EmptyBorder(0, 0, 0, 0));

		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
		PNL_Main.setLayout(null);

	}

	private JComboBox GETcmbxFaculty()
	{
		if (cmbxFaculty == null)
		{
			cmbxFaculty = new JComboBox();
			cmbxFaculty.setBounds(10, 53, 724, 20);
			cmbxFaculty.setFont(new Font("Tahoma", Font.PLAIN, 16));
			cmbxFaculty.setToolTipText("faculty");
			cmbxFaculty.setMaximumRowCount(10);

		}
		return cmbxFaculty;
	}

	private JScrollPane GETscroll()
	{
		if (scroll == null)
		{
			scroll = new JScrollPane(GETtable());
			scroll.setBounds(10, 103, 724, 168);
		}
		return scroll;
	}

	private JTable GETtable()
	{
		if (table == null)
		{

			lstModel = new AbstractTableModel()
			{
				public String getColumnName(int col)
				{
					return columnNames[col].toString();
				}

				public int getRowCount()
				{
					return tableData.length;
				}

				public int getColumnCount()
				{
					return columnNames.length;
				}

				public Object getValueAt(int row, int col)
				{
					return tableData[row][col];
				}

				public boolean isCellEditable(int row, int col)
				{
					if ((col >= 1))
						return false;
					if (row >= maxrow)
						return false;
					else
					{
						return true;

					}
				}

				public void setValueAt(Object value, int row, int col)
				{
					tableData[row][col] = value;
					fireTableCellUpdated(row, col);
				}

				@Override
				public Class getColumnClass(int col)
				{
					if (col == 1) // second column accepts only Integer values
						return Integer.class;
					else
						return Object.class; // other columns accept String
												// values
				}

			};

			lstModel.addTableModelListener(this);

			table = new JTable(tableData, columnNames);
			table.addFocusListener(new FocusAdapter()
			{
				@Override
				public void focusLost(FocusEvent e)
				{
					table.clearSelection();

				}
			});

			table.setCellSelectionEnabled(true);
			table.setColumnSelectionAllowed(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setBackground(SystemColor.inactiveCaption);
			table.setBorder(new LineBorder(new Color(0, 0, 0)));

			sorter = new TableRowSorter<TableModel>(lstModel);
			table.setModel(lstModel);
			// table.setAutoCreateRowSorter(true);
			table.setRowSorter(sorter);

			lstModel.setValueAt(null, 0, 0);
			lstModel.setValueAt(null, 0, 1);
			lstModel.setValueAt(null, 0, 2);
			table.getModel().addTableModelListener(this);

			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(2).setPreferredWidth(140);
			table.setSurrendersFocusOnKeystroke(true);

			table.setToolTipText("");
			table.setBounds(25, 110, 600, 300);
		}
		return table;
	}

	private JTextField GETtxtCourseSttings()
	{
		if (txtCourseSttings == null)
		{
			txtCourseSttings = new JTextField();
			txtCourseSttings.setEditable(false);
			txtCourseSttings.setBounds(0, 4, 774, 40);
			txtCourseSttings.setText("Course settings");
			txtCourseSttings.setHorizontalAlignment(SwingConstants.CENTER);
			txtCourseSttings.setFont(new Font("Tahoma", Font.BOLD, 20));
			txtCourseSttings.setColumns(10);
			txtCourseSttings.setBorder(UIManager.getBorder("DesktopIcon.border"));
			txtCourseSttings.setBackground(new Color(176, 224, 230));
		}
		return txtCourseSttings;
	}

	private JButton GETbtnBackToMainMenu()
	{
		if (btnBackToMainMenu == null)
		{
			btnBackToMainMenu = new JButton("Discard");
			btnBackToMainMenu.setBounds(462, 434, 160, 29);
			btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return btnBackToMainMenu;
	}

	private JButton GETbtnSaveChanges()
	{
		if (btnSaveChanges == null)
		{
			btnSaveChanges = new JButton("Save");
			btnSaveChanges.setBounds(151, 434, 160, 29);
			btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));

		}
		return btnSaveChanges;
	}

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

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnSaveChanges)
		{
			boolean tmp = manager.saveCoureSet(CoursePerFuculty2);
			if (tmp)
			{
				JOptionPane.showMessageDialog(manager.manegerMainFrm, "Succeeded update");
				manager.BacktoMainMenu(this.PNL_Main);
			} else
			{
				JOptionPane.showMessageDialog(manager.manegerMainFrm, "update failed please try again");
			}

			// manager.SaveCourse();
		}
		if (e.getSource() == btnBackToMainMenu)
		{
			manager.BacktoMainMenu(this.PNL_Main);
		}
		if (e.getSource() == cmbxFaculty)
		{
			clickflag = false;
			FacultyIndex = Integer.parseInt(cmbxFaculty.getModel().getElementAt(cmbxFaculty.getSelectedIndex()).toString().split(":")[0]);// cmbxFaculty.getSelectedIndex();
			if (table.isEditing())
			{
				table.getCellEditor().stopCellEditing();
			}
			GetCourseTable(FacultyIndex);
			// manager.LoadFacultyCourse(cmbxFaculty.getSelectedIndex());

			Integer.parseInt(cmbxFaculty.getModel().getElementAt(cmbxFaculty.getSelectedIndex()).toString().split(":")[0]);

		}
	}

	private void GetCourseTable(int index)
	{
		maxrow = 0;
		if (CoursePerFuculty2.get(index) != null)
		{
			if (CoursePerFuculty2.get(index) != null)
			{
				arrayCourse = CoursePerFuculty2.get(index);
				maxrow = arrayCourse.size();
				for (int i = 0; i < arrayCourse.size(); i++)
				{
					lstModel.setValueAt(arrayCourse.get(i).getCapacity(), i, 0);
					lstModel.setValueAt(arrayCourse.get(i).getCourseID(), i, 1);
					lstModel.setValueAt(arrayCourse.get(i).getDescription(), i, 2);
				}

				for (int i = arrayCourse.size(); i < lstModel.getRowCount(); i++)
				{
					lstModel.setValueAt(null, i, 0);
					lstModel.setValueAt(null, i, 1);
					lstModel.setValueAt(null, i, 2);
				}

			}
		} else
		{
			for (int i = 0; i < lstModel.getRowCount(); i++)
			{
				lstModel.setValueAt(null, i, 0);
				lstModel.setValueAt(null, i, 1);
				lstModel.setValueAt(null, i, 2);
			}

		}
		clickflag = true;
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		// TODO Auto-generated method stub

	}

	public void setFaculty(ArrayList<Faculty> arrayList)
	{
		arrayFaculty = arrayList;
		cmbxFaculty.removeAll();
		for (int i = 0; i < arrayFaculty.size(); i++)
		{
			cmbxFaculty.addItem(arrayFaculty.get(i).getFacultyNum() + ":" + arrayFaculty.get(i).getFaculty());
		}

	}

	public void setCourse(ArrayList<Course> course)
	{
		courseSET = course;
		ArrayList<Course> tmp = null;
		CoursePerFuculty2 = new HashMap<Integer, ArrayList<Course>>();
		for (int i = 0; i < course.size(); i++)
		{
			if (!(CoursePerFuculty2.containsKey(course.get(i).getFaculty())))
			{
				tmp = new ArrayList<Course>();

				CoursePerFuculty2.put(course.get(i).getFaculty(), tmp);
			}
			CoursePerFuculty2.get(course.get(i).getFaculty()).add(course.get(i));

		}
	}

	@Override
	public void tableChanged(TableModelEvent e)
	{
		try
		{

			if (clickflag)
			{
				ArrayList<Course> tmp2 = null;
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model = (TableModel) e.getSource();

				// String columnName = model.getColumnName(column);
				int data = Integer.parseInt(model.getValueAt(row, column).toString());
				if (data < 0)
				{
					throw new java.lang.NumberFormatException();
				}

				// tmp2=
				CoursePerFuculty2.get(FacultyIndex).get(row).setCapacity(data);
				// tmp2.get(row).setCapacity(data);
				// Do something with the data...
			}
		} catch (java.lang.NumberFormatException ell)
		{

			int row = e.getFirstRow();
			int column = e.getColumn();
			TableModel model = (TableModel) e.getSource();
			String columnName = model.getColumnName(column);
			model.setValueAt(CoursePerFuculty2.get(FacultyIndex).get(row).getCapacity(), row, column);
			CoursePerFuculty2.get(FacultyIndex).get(row).getCapacity();
			// CoursePerFuculty2.get(FacultyIndex).get(row).setCapacity(CoursePerFuculty2.get(FacultyIndex).get(row).getCapacity());

			JOptionPane.showMessageDialog(manager.manegerMainFrm, "enter a positive number");
			// JOptionPane.show
		}
	}

	@Override
	public void focusGained(FocusEvent e)
	{

	}

	@Override
	public void focusLost(FocusEvent e)
	{

	}

	public void addActions()
	{
		btnSaveChanges.addActionListener(this);
		btnBackToMainMenu.addActionListener(this);
		cmbxFaculty.addActionListener(this);
	}

}
