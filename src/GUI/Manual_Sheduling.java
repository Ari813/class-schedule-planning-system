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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.print.DocFlavor.STRING;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument.BranchElement;

import entities.Class;
import entities.Course;
import entities.Faculty;
import entities.Lecturer;
import Algorithm.Database;
import Algorithm.GeneticAlgorithmRun;
import Algorithm.Individual;
import Controllers.ManagerController;

import javax.swing.JTree;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import common.Settings;

import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.Label;
import java.awt.List;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JPopupMenu;

public class Manual_Sheduling extends JPanel implements ActionListener, ListSelectionListener, KeyListener
{

	private static final long serialVersionUID = 1L;
	private JTextField txtLecturerPreferences;
	private JTable tablemanual;

	private JComboBox cmbxFaculty;
	private JComboBox cmbBxCourse;
	private JComboBox cmbBxClass;
	private JComboBox cmbBxLecturer;
	private JButton btnBackToMainMenu;
	private JButton btnSet;
	private JButton btnClear;
	private JLabel COURSE;
	private JLabel lblType;
	private JLabel lblLecturer;
	private JLabel lblFaculty;
	private JLabel lblSemester;
	private JButton start;
	public JPanel PNL_Main;
	private ManagerController manager;

	// public IDclass[][][] ShedulingTable;
	// public Arrays[][][]arrays=new arr;

	private ArrayList<Class> arrayClasses;
	private ArrayList<Faculty> ManualArrayFaculty;

	// -----------------------
	// public Map<Integer, ArrayList<Course>> CourseMap2;
	public Map<Integer, Map<Integer, Map<Integer, Course>>> CourseMapInTable;
	
	public Map<Integer, Map<Integer, ArrayList<Course>>> CourseMap;
	public Map<Integer, ArrayList<Faculty>> facMap;
	// public Map<Integer, ArrayList<Lecturer>> LecturerMap;

	// -------------------------

	public IDclass id_calsss;
	public ArrayList<IDclass> array_id_calsss;
	// public Map<Integer, ArrayList<IDclass>> semesterMap;
	public Map<Integer, Map<Integer, Map<Integer, IDclass>>> FacultyMap;

	public int semid;
	public int facid;

	static Color[] colors = { Color.BLUE, Color.GRAY, Color.RED };
	static String[] strings = { "Test1", "Test2", "Test3" };

	// private ArrayList<Course> arraycourse;
	// private ArrayList<Lecturer> arrayLecturers;

	private TableModel lstModel;
	private Object[][] tableData = { { "8:00-9:00", null, null, null, null, null, null }, { "9:00-10:00", null, null, null, null, null, null }, { "10:00-11:00", null, null, null, null, null, null },
			{ "11:00-12:00", null, null, null, null, null, null }, { "12:00-13:00", null, null, null, null, null, null }, { "13:00-14:00", null, null, null, null, null, null },
			{ "14:00-15:00", null, null, null, null, null, null }, { "15:00-16:00", null, null, null, null, null, null }, { "16:00-17:00", null, null, null, null, null, null },
			{ "17:00-18:00", null, null, null, null, null, null }, { "18:00-19:00", null, null, null, null, null, null }, { "19:00-20:00", null, null, null, null, null, null },
			{ "20:00-21:00", null, null, null, null, null, null }, { "21:00-22:00", null, null, null, null, null, null }, };
	private String columnNames[] = { "Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
	private JComboBox semesterSpinner;

	private JFormattedTextField courseCapacityHours;
	private int courseHours = 0;
	private JFormattedTextField classCapacity;
	private int classCapacityValue = 0;
	private ArrayList<Course> Allcourses;
	private Database allData;
	private ArrayList<Lecturer> arrayLecturers;
	private Individual firstIndividual;
	private Individual IndividualToAlgo;

	/**
	 * Create the panel.
	 */
	public Manual_Sheduling(ManagerController mng)
	{

		super();

		this.manager = mng;
		initialize();
	}

	private void initialize()
	{
		pnl();
		PNL_Main.add(GETbtnBackToMainMenu());
		PNL_Main.add(GETtxtLecturerPreferences());
		PNL_Main.add(GETcmbxFaculty());
		PNL_Main.add(GETscroll());
		PNL_Main.add(GETCOURSELBL());
		PNL_Main.add(GETcmbBxCourse());
		PNL_Main.add(GETlblType());
		PNL_Main.add(GETcmbBxType());
		PNL_Main.add(GETlblLecturer());
		PNL_Main.add(GETcmbBxLecturer());
		PNL_Main.add(GETbtnSet());
		PNL_Main.add(GETbtnClear());
		PNL_Main.add(GETlblFaculty());
		PNL_Main.add(GETlblSemester());

		horizontalStrut();

	}

	private JLabel GETlblFaculty()
	{
		lblFaculty = new JLabel("Faculty:");
		lblFaculty.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFaculty.setBounds(10, 55, 65, 14);
		return lblFaculty;
	}

	private JLabel GETlblSemester()
	{
		lblSemester = new JLabel("semester:");
		lblSemester.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSemester.setBounds(534, 55, 71, 14);
		return lblSemester;
	}

	private JButton GETbtnClear()
	{
		btnClear = new JButton("Clear");

		btnClear.setBounds(672, 261, 65, 23);

		return btnClear;
	}

	private JButton GETbtnSet()
	{
		btnSet = new JButton("Set");

		btnSet.setBounds(567, 261, 82, 23);

		return btnSet;
	}

	private JComboBox GETcmbBxLecturer()
	{
		cmbBxLecturer = new JComboBox();
		cmbBxLecturer.setToolTipText("cmbBxLecturer");
		cmbBxLecturer.setBounds(600, 160, 120, 22);
		return cmbBxLecturer;
	}

	private JLabel GETlblLecturer()
	{
		lblLecturer = new JLabel("Lecturer:");
		lblLecturer.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLecturer.setBounds(540, 160, 66, 14);
		return lblLecturer;
	}

	private JComboBox GETcmbBxType()
	{
		cmbBxClass = new JComboBox();
		cmbBxClass.setBounds(600, 200, 120, 22);

		return cmbBxClass;
	}

	private JLabel GETlblType()
	{
		lblType = new JLabel("class");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblType.setBounds(540, 200, 45, 15);
		return lblType;
	}

	private JComboBox GETcmbBxCourse()
	{
		cmbBxCourse = new JComboBox();
		cmbBxCourse.setForeground(new Color(0, 0, 0));
		cmbBxCourse.setToolTipText("cmbBxCourse");
		cmbBxCourse.setBounds(600, 120, 120, 20);
		return cmbBxCourse;
	}

	private JLabel GETCOURSELBL()
	{
		COURSE = new JLabel("Course:");
		COURSE.setFont(new Font("Tahoma", Font.BOLD, 12));
		COURSE.setBounds(540, 120, 65, 15);
		return COURSE;
	}

	private JScrollPane GETscroll()
	{
		JScrollPane scroll = new JScrollPane(GETtablemanual());
		scroll.setEnabled(false);
		scroll.setBounds(10, 100, 511, 249);
		return scroll;
	}

	private JTable GETtablemanual()
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
				return false;

			}

			public void setValueAt(Object value, int row, int col)
			{
				tableData[row][col] = value;
				fireTableCellUpdated(row, col);
			}

		};

		tablemanual = new JTable(tableData, columnNames);
		tablemanual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablemanual.setToolTipText("Manual sheduling table ");
		tablemanual.setFillsViewportHeight(true);
		tablemanual.setSurrendersFocusOnKeystroke(true);
		tablemanual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablemanual.setBackground(SystemColor.inactiveCaption);
		tablemanual.setBorder(new LineBorder(new Color(0, 0, 0)));

		tablemanual.setModel(lstModel);
		tablemanual.getColumnModel().getColumn(0).setPreferredWidth(90);
		tablemanual.getColumnModel().getColumn(1).setResizable(false);
		tablemanual.getColumnModel().getColumn(1).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(1).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(2).setResizable(false);
		tablemanual.getColumnModel().getColumn(2).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(2).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(3).setResizable(false);
		tablemanual.getColumnModel().getColumn(3).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(3).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(4).setResizable(false);
		tablemanual.getColumnModel().getColumn(4).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(4).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(5).setResizable(false);
		tablemanual.getColumnModel().getColumn(5).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(5).setMinWidth(25);
		tablemanual.getColumnModel().getColumn(6).setResizable(false);
		tablemanual.getColumnModel().getColumn(6).setPreferredWidth(65);
		tablemanual.getColumnModel().getColumn(6).setMinWidth(25);

		// tablemanual.getColumnModel().getColumn(1).setPreferredWidth(78);

		tablemanual.setColumnSelectionAllowed(true);
		tablemanual.setCellSelectionEnabled(true);
		tablemanual.setBounds(35, 127, 600, 224);

		return tablemanual;
	}

	private void horizontalStrut()
	{
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 80, 774, 12);
		PNL_Main.add(horizontalStrut_1);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(5, 424, 759, 5);
		horizontalStrut_2.setBackground(Color.BLACK);
		PNL_Main.add(horizontalStrut_2);

		start = new JButton("start scheduling");

		start.setBackground(new Color(0, 255, 204));
		start.setFont(new Font("Trajan Pro", Font.BOLD, 11));
		start.setBounds(73, 434, 285, 28);
		PNL_Main.add(start);

		semesterSpinner = new JComboBox();
		semesterSpinner.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
		semesterSpinner.setBounds(600, 54, 49, 20);
		PNL_Main.add(semesterSpinner);
		courseCapacityHours = new JFormattedTextField();
		courseCapacityHours.setFont(new Font("Tahoma", Font.BOLD, 11));
		courseCapacityHours.setBackground(SystemColor.window);
		courseCapacityHours.setEnabled(false);
		courseCapacityHours.setEditable(false);
		courseCapacityHours.setBounds(730, 120, 35, 15);
		PNL_Main.add(courseCapacityHours);

		courseCapacityHours.setValue(0);

		classCapacity = new JFormattedTextField();
		classCapacity.setBackground(SystemColor.window);
		classCapacity.setEditable(false);
		classCapacity.setEnabled(false);
		classCapacity.setBounds(730, 200, 35, 15);
		PNL_Main.add(classCapacity);

	}

	private JTextField GETtxtLecturerPreferences()
	{
		txtLecturerPreferences = new JTextField();
		txtLecturerPreferences.setDisabledTextColor(SystemColor.desktop);
		txtLecturerPreferences.setEnabled(false);
		txtLecturerPreferences.setBounds(0, 4, 774, 39);
		txtLecturerPreferences.setText("Manual scheduling");
		txtLecturerPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		txtLecturerPreferences.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLecturerPreferences.setColumns(10);
		txtLecturerPreferences.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtLecturerPreferences.setBackground(new Color(176, 224, 230));
		return txtLecturerPreferences;
	}

	private JButton GETbtnBackToMainMenu()
	{
		btnBackToMainMenu = new JButton("Discard");
		btnBackToMainMenu.setBounds(453, 434, 169, 29);
		btnBackToMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));

		return btnBackToMainMenu;
	}

	private void pnl()
	{
		PNL_Main = new JPanel();
		PNL_Main.setBorder(new EmptyBorder(0, 0, 0, 0));
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
		PNL_Main.setLayout(null);

	}

	private JComboBox GETcmbxFaculty()
	{
		cmbxFaculty = new JComboBox();
		cmbxFaculty.setBounds(63, 55, 461, 20);
		cmbxFaculty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbxFaculty.setToolTipText("Faculty list");
		cmbxFaculty.setMaximumRowCount(52);
		return cmbxFaculty;
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == cmbBxClass)
		{
			classCapacityValue = arrayClasses.get(cmbBxClass.getSelectedIndex()).getCapcity();
			classCapacity.setValue(classCapacityValue);

		}
		if (e.getSource() == start)
		{
	 	
		
			
			manager.Load_Automatic_Sheduling(this.PNL_Main, allData,firstIndividual);

		}

		if (e.getSource() == btnBackToMainMenu)
		{
			manager.BacktoMainMenu(this.PNL_Main);
		}

		if (e.getSource() == semesterSpinner || e.getSource() == cmbxFaculty)
		{
			SetTable(ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFacultyNum(), semesterSpinner.getSelectedIndex()+1 );
			insert_to_corse_combo();
			if (cmbBxCourse.getItemCount() > 0)
				insert_to_lec_combo();
			else
			{
				cmbBxLecturer.removeAllItems();
			}

		}
		if (e.getSource() == cmbBxLecturer)
		{

		}
		if (e.getSource() == cmbBxCourse)
		{
			// CourseMap.get(selectedIndex).get(semesterIndex)
			// texthours.setText("");
			courseHours = 0;
			//courseCapacityHours.setValue(0);
			if (cmbBxCourse.getItemCount() > 0)
			{
				int facultySelectedIndex = ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFacultyNum();
				int semesterIndex = semesterSpinner.getSelectedIndex()+1 ;
				courseHours = CourseMap.get(facultySelectedIndex).get(semesterIndex).get(cmbBxCourse.getSelectedIndex()).getAcademicHours();
				courseCapacityHours.setValue(courseHours);
				insert_to_lec_combo();
			} else
			{
				cmbBxLecturer.removeAllItems();
			}
		}

		if (e.getSource() == btnSet)
		{
			int tmpRow = 0;

			if ((cmbBxCourse.getItemCount() != 0) && (cmbBxLecturer.getItemCount() != 0)&&(cmbBxCourse.getSelectedIndex() != -1)&& (cmbBxLecturer.getSelectedIndex() != -1) &&((int)courseCapacityHours.getValue()!=0))
			{

				int Row = tablemanual.getSelectedRow();
				int Column = tablemanual.getSelectedColumn();
				int ColumnRow = Row + (Column - 1) * Settings.dailyHours;
				boolean haveplaceflag = true;
				int fac = ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFacultyNum();

				int semester = semesterSpinner.getSelectedIndex()+1;
				int courseid = CourseMap.get(fac).get(semester).get(cmbBxCourse.getSelectedIndex()).getCourseID();
				String courseDescription = CourseMap.get(fac).get(semester).get(cmbBxCourse.getSelectedIndex()).getDescription();
				int classRoom = arrayClasses.get(cmbBxClass.getSelectedIndex()).getClassID();
				String classRoomDescription = arrayClasses.get(cmbBxClass.getSelectedIndex()).getDescription();
				int lecid = CourseMap.get(fac).get(semester).get(cmbBxCourse.getSelectedIndex()).getCourseLecturers().get(cmbBxLecturer.getSelectedIndex()).getID();
				String lecname = CourseMap.get(fac).get(semester).get(cmbBxCourse.getSelectedIndex()).getCourseLecturers().get(cmbBxLecturer.getSelectedIndex()).getName();

				int cap = CourseMap.get(fac).get(semester).get(cmbBxCourse.getSelectedIndex()).getStudentNumber();
				System.out.println(cap);
				if (classCapacityValue < cap)
					JOptionPane.showMessageDialog(manager.manegerMainFrm, "class dont have enough capacity for this course");
				else
				{

					if ((Column >= 1) && (haveplaceflag))
					{
						id_calsss = new IDclass(courseid, lecid, classRoom, ColumnRow, 0, courseDescription, lecname, classRoomDescription);

						if (FacultyMap == null)
						{
							FacultyMap = new HashMap<Integer, Map<Integer, Map<Integer, IDclass>>>();
						}

						boolean flag = true;
						for (int j = 0; j < courseHours; j++)
						{

							tmpRow = Row + j;
							if (tmpRow >= Settings.dailyHours)
							{
								System.out.print("overlap");
								flag = false;
								JOptionPane.showMessageDialog(manager.manegerMainFrm, "The course needs more consecutive hours ");
								break;
							}

							if (flag)
							{
								for (int fac_index = 0; fac_index < cmbxFaculty.getItemCount(); fac_index++)
								{
									if (FacultyMap.get(fac_index) != null)
									{
										for (int semester_index = 1; semester_index <= 8; semester_index++)
										{
											if (FacultyMap.get(fac_index).get(semester_index) != null)
											{
												if (FacultyMap.get(fac_index).get(semester_index).get(ColumnRow) != null)
												{// .getClassid()==classRoom){
													int room = FacultyMap.get(fac_index).get(semester_index).get(ColumnRow).getClassid();
													int lec = FacultyMap.get(fac_index).get(semester_index).get(ColumnRow).getLecid();
													if (room == classRoom)
													{
														JOptionPane.showMessageDialog(manager.manegerMainFrm, "room is occupied");
														flag = false;
														break;
													}
													if (lec == lecid)
													{
														JOptionPane.showMessageDialog(manager.manegerMainFrm, "lecturer teaches  in these hours");
														flag = false;
														break;
													}
												}
											}
										}
									}

								}
							}
							ColumnRow++;
						}
						ColumnRow = Row + (Column - 1) * Settings.dailyHours;

						if (flag)
						{
							
							for (int j = 0; j < courseHours; j++)
							{
								id_calsss = new IDclass(courseid, lecid, classRoom, ColumnRow, courseHours, courseDescription, lecname, classRoomDescription);
								id_calsss.setId(j);
								firstIndividual.setGeneByID(Column, Row+j, lecid, classRoom, courseid,j,false);
								
								// tablemanual.getModel().setValueAt(id_calsss.getClassid()
								// + ":" +id_calsss.getCousreid() + ":"
								// +id_calsss.getLecid() ,Row+j,Column );

								if (FacultyMap.containsKey(fac))
								{
									if (FacultyMap.get(fac).containsKey(semester))
									{
										if (FacultyMap.get(fac).get(semester).containsKey(ColumnRow))
										{

											JOptionPane.showMessageDialog(manager.manegerMainFrm, "course overlap");
											flag = false;
											break;
											// FacultyMap.get(fac).get(semester).get(ColumnRow).setall(id_calsss);
											// Map<Integer, Map<Integer,
											// ArrayList<Course>>>
											// CourseMapInTable;
											// System.out.print("overlap");//Only
											// switch
										} else
										{
											FacultyMap.get(fac).get(semester).put(ColumnRow, id_calsss);
										}

									} else
									{
										Map<Integer, IDclass> ColumnRowMap = new HashMap<Integer, IDclass>();
										ColumnRowMap.put(ColumnRow, id_calsss);
										FacultyMap.get(fac).put(semester, ColumnRowMap);

									}
								} else
								{

									Map<Integer, IDclass> ColumnRowMap = new HashMap<Integer, IDclass>();
									//

									ColumnRowMap.put(ColumnRow, id_calsss);
									Map<Integer, Map<Integer, IDclass>> semesterMap = new HashMap<Integer, Map<Integer, IDclass>>();
									semesterMap.put(semester, ColumnRowMap);
									FacultyMap.put(fac, semesterMap);
								}
								ColumnRow++;
							}
							setIndividual(courseid,true);

							if (flag)
							{
								for (int j = 0; j < courseHours; j++)
									tablemanual.getModel().setValueAt(id_calsss.getClassid() + ":" + id_calsss.getCousreid() + ":" + id_calsss.getLecid(), Row + j, Column);

								Course courseToMove = new Course();
								courseToMove = CourseMap.get(fac).get(semester).get(cmbBxCourse.getSelectedIndex());

								if (CourseMapInTable.containsKey(fac))
								{
									if (CourseMapInTable.get(fac).containsKey(semester))
									{
										CourseMapInTable.get(fac).get(semester).put(courseid, courseToMove);
									} else
									{
										Map<Integer, Course> tmpcourse = new HashMap<Integer, Course>();
										tmpcourse.put(courseid, courseToMove);
										CourseMapInTable.get(fac).put(semester, tmpcourse);

									}
								} else
								{
									Map<Integer, Course> tmpcourse = new HashMap<Integer, Course>();
									tmpcourse.put(courseid, courseToMove);
									Map<Integer, Map<Integer, Course>> facMap = new HashMap<Integer, Map<Integer, Course>>();
									facMap.put(semester, tmpcourse);
									CourseMapInTable.put(fac, facMap);

								}

								CourseMap.get(fac).get(semester).remove(cmbBxCourse.getSelectedIndex());

								insert_to_corse_combo();
								if (cmbBxCourse.getItemCount() > 0)
									insert_to_lec_combo();
								else
								{
									cmbBxLecturer.removeAllItems();
								}
							}
						}
					}
				}
			}

		}
		if (e.getSource() == btnClear)
		{

			if (tablemanual.getSelectedColumn() >= 1)
			{

				int fac = ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFacultyNum();
				int semester = (int) semesterSpinner.getSelectedIndex()+1 ;
				int Row = tablemanual.getSelectedRow();
				int Column = tablemanual.getSelectedColumn();
				int ColumnRow = Row + (Column - 1) * Settings.dailyHours;

				if (FacultyMap != null)
				{
					if (FacultyMap.get(fac).get(semester).containsKey(ColumnRow))
					{
						int ClassID = FacultyMap.get(fac).get(semester).get(ColumnRow).getClassid();
						int LecturerID = FacultyMap.get(fac).get(semester).get(ColumnRow).getLecid();
						int CourseID = FacultyMap.get(fac).get(semester).get(ColumnRow).getCousreid();
						
						int size = FacultyMap.get(fac).get(semester).get(ColumnRow).getSize();
						int QA1 = FacultyMap.get(fac).get(semester).get(ColumnRow).getId();
						int QA2 = size - QA1;

						// ss
						Course courseToMoveBack = new Course();
						int courseid = FacultyMap.get(fac).get(semester).get(ColumnRow).getCousreid();
						courseToMoveBack = CourseMapInTable.get(fac).get(semester).get(courseid);
						CourseMap.get(fac).get(semester).add(courseToMoveBack);

						CourseMapInTable.get(fac).get(semester).remove(courseid);

						for (int j = 0; j < QA2; j++)
						{
							firstIndividual.clrGeneByID(Column, Row+j, LecturerID, ClassID, CourseID,true);
							tablemanual.getModel().setValueAt("", tablemanual.getSelectedRow() + j, tablemanual.getSelectedColumn());
							FacultyMap.get(fac).get(semester).remove(ColumnRow + j);
						}
						for (int j = 1; j <= QA1; j++)
						{
							firstIndividual.clrGeneByID(Column, Row-j, LecturerID, ClassID, CourseID,true);
							FacultyMap.get(fac).get(semester).remove(ColumnRow - j);
							tablemanual.getModel().setValueAt("", tablemanual.getSelectedRow() - j, tablemanual.getSelectedColumn());
						}
						setIndividual(CourseID,false);
					}

					insert_to_corse_combo();
					if (cmbBxCourse.getItemCount() > 0)
						insert_to_lec_combo();
					else
					{
						cmbBxLecturer.removeAllItems();
					}

				}

			}

		}
	}

	private void setIndividual(int courseid,boolean flag) {
		
		int courseIndex = allData.getMapping().getCourseIndex(courseid);
		
		for (int Hours = 0; Hours < firstIndividual.weeklyHours; Hours++)
		{
			for (int LecturerIndex = 0; LecturerIndex < arrayLecturers.size(); LecturerIndex++)
			{
				for (int ClassesIndex = 0; ClassesIndex < arrayClasses.size(); ClassesIndex++)
				{
					 if (flag){
						 
					
					
					firstIndividual.getGeneByIndex(Hours, LecturerIndex, ClassesIndex, courseIndex).setUnEditable();;
				
					 }else
					 {
					
						 firstIndividual.getGeneByIndex(Hours, LecturerIndex, ClassesIndex, courseIndex).setEditable();
					 }
				}
			}
		}
	}


	
	public void setfirstIndividual()
	{
	
	//	int size=Allcourses.size();
			//int fac=0;
			//int sem=0;
		//	int courseid;
	/*
			for(int j=0;j<size;j++){
				fac=Allcourses.get(j).getFaculty();
				sem=Allcourses.get(j).getSemester();
				courseid=Allcourses.get(j).getCourseID();
				if (CourseMapInTable.containsKey(fac))
					if (CourseMapInTable.get(fac).containsKey(sem))
						if (CourseMapInTable.get(fac).get(sem).containsKey(courseid))
							Allcourses.remove(j);
			}
	*/
		firstIndividual = new Individual(arrayLecturers.size(), arrayClasses.size(), Allcourses.size());
		allData = new Database(Allcourses, arrayLecturers, arrayClasses);
		manager.setDataBase(allData);
		//System.out.println(firstIndividual.getSelection());
	}

	private void SetTable(int faculty, int semester)
	{
		
		if (FacultyMap != null)
		{
			for (int columnIndex = 1; columnIndex <= Settings.workingDays; columnIndex++)
				for (int rowIndex = 0; rowIndex < Settings.dailyHours; rowIndex++)
				{
					tablemanual.getModel().setValueAt("", rowIndex, columnIndex);
				}
			for (int columnIndex = 1; columnIndex <= Settings.workingDays; columnIndex++)
				for (int rowIndex = 0; rowIndex <= Settings.dailyHours; rowIndex++)
				{
					int ColumnRow = rowIndex + (columnIndex - 1) * Settings.dailyHours;
					if (FacultyMap.containsKey(faculty))
						if (FacultyMap.get(faculty).containsKey(semester))
							if (FacultyMap.get(faculty).get(semester).containsKey(ColumnRow))
							{
								tablemanual.getModel().setValueAt(
										FacultyMap.get(faculty).get(semester).get(ColumnRow).getCousreid() + ":" + FacultyMap.get(faculty).get(semester).get(ColumnRow).getClassid() + ":"
												+ FacultyMap.get(faculty).get(semester).get(ColumnRow).getLecid(), rowIndex, columnIndex);

							}
				}
		}
	}

	public void setFaculty(ArrayList<Faculty> faculty)
	{
		ManualArrayFaculty = faculty;

		cmbxFaculty.removeAll();
		for (int i = 0; i < ManualArrayFaculty.size(); i++)
		{
			cmbxFaculty.addItem(ManualArrayFaculty.get(i).getFacultyNum() + ":" + ManualArrayFaculty.get(i).getFaculty());

		}
	}

	public void setClasses(ArrayList<Class> getClasses)
	{
		
		arrayClasses = getClasses;

		cmbBxClass.removeAll();
		for (int i = 0; i < arrayClasses.size(); i++)
		{
			cmbBxClass.addItem(arrayClasses.get(i).getClassID() + ":" + arrayClasses.get(i).getDescription());
		}
	}

	// --------------------------------

	public void setMapCourse(ArrayList<Course> course)
	{

		Allcourses = course;
		CourseMap = new HashMap<Integer, Map<Integer, ArrayList<Course>>>();

		CourseMapInTable = new HashMap<Integer, Map<Integer, Map<Integer, Course>>>();
		for (int i = 0; i < course.size(); i++)
		{
			int facultyID = course.get(i).getFaculty();
			int Semester = course.get(i).getSemester();
			if (CourseMap.containsKey(facultyID))
			{
				if (CourseMap.get(facultyID).containsKey(Semester))
				{
					CourseMap.get(facultyID).get(Semester).add(course.get(i));
				} else
				{
					ArrayList<Course> newcourse = new ArrayList<Course>();
					newcourse.add(course.get(i));
					CourseMap.get(facultyID).put(Semester, newcourse);
				}

			} else
			{
				ArrayList<Course> newcourse = new ArrayList<Course>();
				newcourse.add(course.get(i));

				Map<Integer, ArrayList<Course>> courseinMap = new HashMap<Integer, ArrayList<Course>>();

				courseinMap.put(Semester, newcourse);
				CourseMap.put(facultyID, courseinMap);
			}

		}

		insert_to_corse_combo();
		if (cmbBxCourse.getItemCount() > 0)
			insert_to_lec_combo();
		else
		{
			cmbBxLecturer.removeAllItems();
		}
		
	}

	private void insert_to_corse_combo()
	{

		cmbBxCourse.removeAllItems();
		int selectedIndex = ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFacultyNum();
		int semesterIndex = semesterSpinner.getSelectedIndex() +1;

		if (CourseMap != null && CourseMap.get(selectedIndex) != null && CourseMap.get(selectedIndex).get(semesterIndex) != null)
		{
			int size = CourseMap.get(selectedIndex).get(semesterIndex).size();
			for (int i = 0; i < size;)
			{
				cmbBxCourse.addItem(CourseMap.get(selectedIndex).get(semesterIndex).get(i).getCourseID() + ":" + CourseMap.get(selectedIndex).get(semesterIndex).get(i).getDescription());
				i++;
				// System.out.print("sss");

			}
		}

	}

	public void insert_to_lec_combo()
	{
		cmbBxLecturer.removeAllItems();
		int selectedIndex = ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFacultyNum();
		int semesterIndex = semesterSpinner.getSelectedIndex()+1 ;
		int courseindex = cmbBxCourse.getSelectedIndex();
		ArrayList<Lecturer> lecturerPerCourse = new ArrayList<Lecturer>();
		if (CourseMap != null && CourseMap.get(selectedIndex) != null && CourseMap.get(selectedIndex).get(semesterIndex) != null)
		{
			{
				lecturerPerCourse = CourseMap.get(selectedIndex).get(semesterIndex).get(courseindex).getCourseLecturers();
				if (lecturerPerCourse != null)
				{
					int size = CourseMap.get(selectedIndex).get(semesterIndex).get(cmbBxCourse.getSelectedIndex()).getCourseLecturers().size();
					for (int i = 0; i < size; i++)
					{
						cmbBxLecturer.addItem(lecturerPerCourse.get(i).getID() + ":" + lecturerPerCourse.get(i).getName());

						// course=Integer.parseInt(cmbBxCourse.getModel()
						// .getElementAt(cmbBxCourse.getSelectedIndex())
						// .toString().split(":")[0]);

					}
				}

			}
		}
	}

	/*
	 * / public void setMapLec(ArrayList<Lecturer> availableLecturers) {
	 * 
	 * //availableLecturers.get(1).getLecturerCourses(); LecturerMap =new
	 * HashMap<Integer, ArrayList<Lecturer>>(); for (int lec_index = 0;
	 * lec_index < availableLecturers.size(); lec_index++) { ArrayList<Course>
	 * LecturerCourses = availableLecturers.get(lec_index).getLecturerCourses();
	 * for (int Course_index = 0; Course_index < LecturerCourses.size();
	 * Course_index++) { int
	 * CourseID=LecturerCourses.get(Course_index).getCourseID(); if
	 * (LecturerMap.containsKey(CourseID)){
	 * (LecturerMap.get(CourseID)).add(availableLecturers.get(lec_index));
	 * 
	 * } else{ ArrayList<Lecturer> insert_lec =new ArrayList<Lecturer>();
	 * insert_lec.add(availableLecturers.get(lec_index));
	 * LecturerMap.put(CourseID, insert_lec); } } } //��� �� ������
	 * ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFacultyNum();
	 * //cmbBxCourse.getSelectedIndex() if (cmbBxCourse.getItemCount()>0)
	 * insert_to_lec_combo(); else{ cmbBxLecturer.removeAllItems(); }
	 * 
	 * 
	 * }
	 * 
	 * /
	 */
	/*
	 * / private void insert_to_lec_combo2() {
	 * 
	 * cmbBxLecturer.removeAllItems(); int
	 * semesterIndex=semesterSpinner.getSelectedIndex()+1; int
	 * selectedIndex=ManualArrayFaculty
	 * .get(cmbxFaculty.getSelectedIndex()).getFacultyNum();
	 * 
	 * if ( CourseMap!=null && CourseMap.get(selectedIndex)!=null &&
	 * CourseMap.get(selectedIndex).get(semesterIndex)!=null ){ //int
	 * cotseindex=
	 * CourseMap.get(selectedIndex).get(semesterIndex).get(cmbBxCourse
	 * .getSelectedIndex()).getAcademicHours(); int
	 * courseID=CourseMap.get(ManualArrayFaculty
	 * .get(cmbxFaculty.getSelectedIndex
	 * ()).getFacultyNum()).get(semesterIndex).get
	 * (cmbBxCourse.getSelectedIndex()).getCourseID(); if (
	 * LecturerMap.get(courseID)!=null && LecturerMap!=null){ int size=
	 * LecturerMap.get(courseID).size(); for (int i = 0; i < size; i++) {
	 * cmbBxLecturer.addItem(LecturerMap.get(courseID).get(i).getID() + ":" +
	 * LecturerMap.get(courseID).get(i).getName()); } } } }
	 * 
	 * /
	 */
	public void addActions()
	{

		btnBackToMainMenu.addActionListener(this);
		start.addActionListener(this);
		btnSet.addActionListener(this);
		// tablemanual.addAncestorListener();
		btnClear.addActionListener(this);

		cmbxFaculty.addActionListener(this);
		cmbBxCourse.addActionListener(this);
		cmbBxClass.addActionListener(this);
		semesterSpinner.addActionListener(this);
		cmbBxLecturer.addActionListener(this);

		tablemanual.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				int row = tablemanual.getSelectedRow();
				int column = tablemanual.getSelectedColumn();
				int fac = ManualArrayFaculty.get(cmbxFaculty.getSelectedIndex()).getFacultyNum();
				int ColumnRow = row + (column - 1) * Settings.dailyHours;
				int semester = semesterSpinner.getSelectedIndex() +1;
				if (e.getClickCount() == 2)
				{
					if (FacultyMap != null)
					{
						if (FacultyMap.containsKey(fac))
						{
							if (FacultyMap.get(fac).containsKey(semester))
							{
								if (FacultyMap.get(fac).get(semester).containsKey(ColumnRow))
									JOptionPane.showMessageDialog(manager.manegerMainFrm, FacultyMap.get(fac).get(semester).get(ColumnRow).ToString());

							}
						}
					}
				}
				if (e.getClickCount() == 1)
				{
					// JTable target = (JTable)e.getSource();

					// do some action if appropriate column
					if (column > 0)
					{
						tablemanual.setToolTipText("Manual sheduling table ");

						if (FacultyMap != null)
						{
							if (FacultyMap.containsKey(fac))
							{
								if (FacultyMap.get(fac).containsKey(semester))
								{
									if (FacultyMap.get(fac).get(semester).containsKey(ColumnRow))
										tablemanual.setToolTipText(FacultyMap.get(fac).get(semester).get(ColumnRow).ToString());
								}
							}
						}
					}
				}
			}
		});
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

	// public void setCourse(ArrayList<Course> course) {

	/*
	 * / arraycourse = course; for (int i = 0; i < arraycourse.size(); i++) {
	 * cmbBxCourse.addItem(arraycourse.get(i).getCourseID() + ":" +
	 * arraycourse.get(i).getDescription()); } /
	 */
	// setMapCourse(course);
	// }

	public void setLec(ArrayList<Lecturer> availableLecturers)
	{

		arrayLecturers = availableLecturers;
		/*
		 * /cmbBxLecturer.removeAllItems(); for (int i = 0; i <
		 * arrayLecturers.size(); i++) {
		 * cmbBxLecturer.addItem(arrayLecturers.get(i).getID() + ":" +
		 * arrayLecturers.get(i).getName());
		 * 
		 * } /
		 */
		// setMapLec(availableLecturers);
		//
	}

	private static void addPopup(Component component, final JPopupMenu popup)
	{
		component.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e)
			{
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
