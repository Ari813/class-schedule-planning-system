package GUI;

import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;

import entities.Building;
import entities.Campus;
import entities.Class;
import entities.StudyAids;
import Controllers.LecturerController;
import Controllers.ManagerController;

import java.awt.SystemColor;

public class Edit_Class extends JPanel implements ActionListener, ListSelectionListener, KeyListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private JFrame frmLec;
	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;
	private JTextField txtClassEditor;
	private JComboBox cmbxEditClass;
	private JComboBox cmbxcampus;
	private JList<String> lstClassAids;
	private JList lstSelectedClassaids;

	private DefaultListModel lstClassAidsModel;
	private DefaultListModel lstSelectedClassAidsModel;

	private JTextField txtpnCodeNumber;
	private JComboBox cmbBxBlding;
	private JCheckBox chckbxAvailable;

	public JPanel PNL_Main;

	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnNewClass;
	private JButton btnSaveChanges;
	private JButton btnDiscard;
	private JLabel lblCode;
	private JLabel lblDescription;
	private JLabel lblCampus;
	private JLabel lblBilding;
	private JLabel lblCapacity;
	private JTextField txtClassSelectedCampus;
	private JTextField txtClassSelectedBuildig;

	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut;
	private JLabel lblClassAids;
	// private static int aidsIndex=0;

	private Map<Integer, StudyAids> arrayStudyAids;
	private Map<Integer, Integer> arrayAvailableStudyAids;
	private Map<Integer, Integer> arraySelectedStudyAids;

	private ArrayList<Campus> arrayCampus;
	private ArrayList<Building> arrayBuilding;
	private ArrayList<Class> arrayClasses;
	public String txtSelectedCampus;
	boolean isNewClass;

	/**
	 * Launch the application.
	 */

	private LecturerController lec;
	private ManagerController manager;
	private ArrayList<String> aids;

	/**
	 * Create the application.
	 */
	public Edit_Class(ManagerController mng)
	{

		super();
		this.manager = mng;
		initialize();
	}

	private void initialize()
	{

		pnl();

		PNL_Main.add(GETcmbxEditClass());
		PNL_Main.add(GETcmbxcampus());
		PNL_Main.add(GETcmbBxBlding());

		PNL_Main.add(GETlblCapacity());
		PNL_Main.add(GETlblBilding());
		PNL_Main.add(GETlblCampus());
		PNL_Main.add(GETlblDescription());
		PNL_Main.add(GETlblCode());
		PNL_Main.add(GETlblClassAids());

		PNL_Main.add(GETtxtpnCodeNumber());
		PNL_Main.add(GETtxtCapacityNumber());
		PNL_Main.add(GETtxtDescriptionText());
		PNL_Main.add(GETtxtClassEditor());
		//TODO  JScrollBar 
		PNL_Main.add(GETlstClassAids());

		PNL_Main.add(GETchckbxAvailable());

		PNL_Main.add(GETbtnAdd());
		PNL_Main.add(GETbtnRemove());
		PNL_Main.add(GETbtnNewClass());
		PNL_Main.add(GETbtnSaveChanges());
		PNL_Main.add(GETbtnDiscard());

		PNL_Main.add(GETSelectedClassAids());

		sethorizontalStrut();

	}

	private void pnl()
	{

		PNL_Main = new JPanel();
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
		PNL_Main.setLayout(null);

	}

	private JTextField GETtxtClassEditor()
	{
		txtClassEditor = new JTextField();
		txtClassEditor.setEditable(false);
		txtClassEditor.setText("Class editor");
		txtClassEditor.setHorizontalAlignment(SwingConstants.CENTER);
		txtClassEditor.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtClassEditor.setColumns(10);
		txtClassEditor.setBorder(UIManager.getBorder("DesktopIcon.border"));
		txtClassEditor.setBackground(new Color(176, 224, 230));
		txtClassEditor.setBounds(0, 4, 774, 40);
		return txtClassEditor;
	}

	private JButton GETbtnDiscard()
	{
		btnDiscard = new JButton("Discard");
		btnDiscard.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnDiscard.setBounds(539, 434, 160, 29);
		return btnDiscard;
	}

	private JButton GETbtnSaveChanges()
	{
		btnSaveChanges = new JButton("Save");
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveChanges.setBounds(306, 434, 160, 29);
		btnSaveChanges.setEnabled(false);

		return btnSaveChanges;
	}

	private JButton GETbtnNewClass()
	{
		btnNewClass = new JButton("New class");
		btnNewClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewClass.setBounds(73, 434, 160, 29);

		return btnNewClass;
	}

	private JButton GETbtnRemove()
	{
		btnRemove = new JButton("<--");
		btnRemove.setToolTipText("Remove item from class");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(502, 216, 65, 38);

		return btnRemove;
	}

	private JButton GETbtnAdd()
	{
		btnAdd = new JButton(" -->");
		btnAdd.setToolTipText("Add item to class");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnAdd.setBounds(502, 163, 65, 38);
		return btnAdd;
	}

	private JCheckBox GETchckbxAvailable()
	{
		chckbxAvailable = new JCheckBox("Available");
		chckbxAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxAvailable.setBounds(10, 80, 135, 23);
		return chckbxAvailable;
	}

	private JList GETSelectedClassAids()
	{
		lstSelectedClassAidsModel = new DefaultListModel();

		lstSelectedClassaids = new JList();
		lstSelectedClassaids.setModel(lstSelectedClassAidsModel);
		lstSelectedClassaids.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstSelectedClassaids.setBackground(new Color(169, 169, 169));
		lstSelectedClassaids.setForeground(new Color(255, 255, 255));
		lstSelectedClassaids.setBounds(586, 130, 138, 220);
		return lstSelectedClassaids;
	}

	private JList GETlstClassAids()
	{
		lstClassAids = new JList<>();
		lstClassAids.setToolTipText("class aids options ");
		lstClassAidsModel = new DefaultListModel();
		lstClassAids.setModel(lstClassAidsModel);
		lstClassAids.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lstClassAids.setForeground(new Color(0, 0, 0));
		lstClassAids.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstClassAids.setValueIsAdjusting(true);
		lstClassAids.setSelectedIndices(new int[] { 2 });
		lstClassAids.setBackground(new Color(169, 169, 169));
		lstClassAids.setBounds(332, 130, 138, 220);
		return lstClassAids;
	}

	private JLabel GETlblClassAids()
	{
		lblClassAids = new JLabel("Class aids:");
		lblClassAids.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClassAids.setBounds(332, 100, 100, 14);
		return lblClassAids;
	}

	private JTextField GETtxtCapacityNumber()
	{
		txtCapacityNumber = new JTextField();
		txtCapacityNumber.setEditable(true);
		txtCapacityNumber.setText("Capacity number");
		txtCapacityNumber.setBounds(172, 144, 100, 20);
		txtCapacityNumber.addKeyListener(this);
		return txtCapacityNumber;
	}

	private void sethorizontalStrut()
	{

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(332, 114, 392, 14);
		PNL_Main.add(horizontalStrut);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(5, 424, 759, 5);
		PNL_Main.add(horizontalStrut_1);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBackground(Color.BLACK);
		horizontalStrut_2.setBounds(0, 75, 774, 5);
		PNL_Main.add(horizontalStrut_2);

		txtClassSelectedCampus = new JTextField();
		txtClassSelectedCampus.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtSelectedCampus = "class selected campus";
		txtClassSelectedCampus.setText(txtSelectedCampus);
		txtClassSelectedCampus.setToolTipText("class campus");
		txtClassSelectedCampus.setBackground(SystemColor.info);
		txtClassSelectedCampus.setEnabled(false);
		txtClassSelectedCampus.setEditable(false);
		txtClassSelectedCampus.setBounds(10, 323, 130, 20);
		PNL_Main.add(txtClassSelectedCampus);

		txtClassSelectedBuildig = new JTextField();
		txtClassSelectedBuildig.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtClassSelectedBuildig.setEditable(false);
		txtClassSelectedBuildig.setToolTipText("class building");
		txtClassSelectedBuildig.setText("class selected buildig");
		txtClassSelectedBuildig.setEnabled(false);
		txtClassSelectedBuildig.setBackground(SystemColor.info);
		txtClassSelectedBuildig.setBounds(172, 323, 130, 20);
		PNL_Main.add(txtClassSelectedBuildig);

	}

	private JLabel GETlblCapacity()
	{
		lblCapacity = new JLabel("Capacity:");
		lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCapacity.setVerticalAlignment(SwingConstants.TOP);
		lblCapacity.setBounds(172, 114, 76, 22);
		return lblCapacity;
	}

	private JComboBox GETcmbBxBlding()
	{
		cmbBxBlding = new JComboBox();
		cmbBxBlding.setEnabled(false);
		cmbBxBlding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbBxBlding.setBounds(172, 292, 130, 20);
		return cmbBxBlding;
	}

	private JLabel GETlblBilding()
	{
		lblBilding = new JLabel("Building:");
		lblBilding.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBilding.setBounds(172, 267, 82, 22);
		return lblBilding;
	}

	private JLabel GETlblCampus()
	{
		lblCampus = new JLabel("Campus:");
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCampus.setBounds(10, 267, 90, 14);
		return lblCampus;
	}

	private JComboBox GETcmbxcampus()
	{
		cmbxcampus = new JComboBox();
		cmbxcampus.setEnabled(false);
		cmbxcampus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbxcampus.setToolTipText("Campus");
		cmbxcampus.setBounds(10, 292, 130, 20);
		return cmbxcampus;
	}

	private JTextField GETtxtDescriptionText()
	{
		txtDescriptionText = new JTextField();
		txtDescriptionText.setToolTipText("class description");
		txtDescriptionText.setEnabled(false);
		txtDescriptionText.setBackground(Color.WHITE);
		txtDescriptionText.setText("description text");
		txtDescriptionText.setBounds(10, 207, 100, 20);
		return txtDescriptionText;
	}

	private JLabel GETlblDescription()
	{
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescription.setBounds(10, 179, 123, 22);
		return lblDescription;
	}

	private JLabel GETlblCode()
	{
		lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCode.setBounds(10, 114, 90, 21);
		return lblCode;
	}

	private JTextField GETtxtpnCodeNumber()
	{
		if (txtpnCodeNumber == null)
		{
			txtpnCodeNumber = new JTextField();
			txtpnCodeNumber.setEnabled(false);
			txtpnCodeNumber.setEditable(false);
			// txtpnCodeNumber.setDropMode(DropMode.ON);
			txtpnCodeNumber.setBackground(Color.WHITE);
			txtpnCodeNumber.setText("Code Number");
			txtpnCodeNumber.setBounds(10, 144, 100, 20);
			txtpnCodeNumber.addKeyListener(this);
		}
		return txtpnCodeNumber;
	}

	private JComboBox GETcmbxEditClass()
	{
		if (cmbxEditClass == null)
		{
			cmbxEditClass = new JComboBox();
			cmbxEditClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
			cmbxEditClass.setToolTipText("Edit class list");
			cmbxEditClass.setBounds(10, 53, 754, 20);
			cmbxEditClass.setMaximumRowCount(10);

		}
		return cmbxEditClass;
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnSaveChanges)
		{
			btnSaveChanges.setEnabled(false);
			if (!txtpnCodeNumber.getText().equals(""))
			{

				Class newClass = new Class();
				Class serverAns;

				newClass.setClassID(Integer.parseInt(txtpnCodeNumber.getText()));
				newClass.setDescription(txtDescriptionText.getText());
				newClass.setBuilding(arrayBuilding.get(cmbBxBlding.getSelectedIndex()).getBuildingID());
				newClass.setCampus(arrayCampus.get(cmbxcampus.getSelectedIndex()).getCampusId());
				newClass.setCapcity(Integer.parseInt(txtCapacityNumber.getText()));
				newClass.setAvailable(chckbxAvailable.isSelected());

				Iterator<Integer> stdyAidsItr = arraySelectedStudyAids.keySet().iterator();
				while (stdyAidsItr.hasNext())
				{
					newClass.addStudyAid(arrayStudyAids.get(stdyAidsItr.next().intValue()));
				}

				if (isNewClass)
				{
					serverAns = manager.CreateNewClass(newClass);
				} else
				{
					serverAns = manager.UpdateNewClass(newClass);
				}

				if (serverAns.getClassID() == newClass.getClassID())
				{
					if (!isNewClass)
					{
						// /// TODO -1
						arrayClasses.set(cmbxEditClass.getSelectedIndex(), newClass);
					} else
					{
						arrayClasses.add(newClass);
						cmbxEditClass.removeAllItems();
						setClasses(arrayClasses);
					}
					JOptionPane.showMessageDialog(manager.manegerMainFrm, "Succeeded update");
					System.out.println(" Success!!!");
				} else
				{
					JOptionPane.showMessageDialog(manager.manegerMainFrm, " update Fail try again");
					System.out.println(" Fail!!!!");
				}
				if (isNewClass)
					setdefault();
				createNewClass(false);

			} else
				JOptionPane.showMessageDialog(manager.manegerMainFrm, "ClassID  not enter...");

		}

		if (e.getSource() == btnAdd)
		{
			int index;

			if (lstClassAids.getSelectedIndex() != -1)
				btnSaveChanges.setEnabled(true);
			if ((arrayStudyAids != null) && (!arrayStudyAids.isEmpty()))
			{
				if (lstClassAids.getSelectedIndex() >= 0)
				{
					index = Integer.parseInt(lstClassAidsModel.getElementAt(lstClassAids.getSelectedIndex()).toString().split(":")[0]);
					addAids(index);
				}
			}

		}
		if (e.getSource() == btnRemove)
		{
			if (lstSelectedClassaids.getSelectedIndex() != -1)
				btnSaveChanges.setEnabled(true);
			int index;

			if ((arraySelectedStudyAids != null) && (!arraySelectedStudyAids.isEmpty()))
			{
				if (lstSelectedClassaids.getSelectedIndex() >= 0)
				{
					index = Integer.parseInt(lstSelectedClassAidsModel.getElementAt(lstSelectedClassaids.getSelectedIndex()).toString().split(":")[0]);
					removeAids(index);
				}
			}
		}
		if (e.getSource() == btnNewClass)
		{
			resetLists();
			createNewClass(true);

			Iterator<StudyAids> stdyaidItr = arrayStudyAids.values().iterator();
			while (stdyaidItr.hasNext())
			{
				int tempID = stdyaidItr.next().getAidsID();
				arrayAvailableStudyAids.put(tempID, tempID);
				lstClassAidsModel.addElement(tempID + ":" + arrayStudyAids.get(tempID).getAidsName());
			}

		}
		if (e.getSource() == btnDiscard)
		{
			manager.BacktoMainMenu(this.PNL_Main);
		}
		if (e.getSource() == cmbxEditClass)
		{
			createNewClass(false);
			setSelectedClass();
		}

	}

	private void removeAids(int index)
	{

		arrayAvailableStudyAids.put(arraySelectedStudyAids.get(index), arraySelectedStudyAids.get(index));
		arraySelectedStudyAids.remove(index);
		lstClassAidsModel.addElement(lstSelectedClassAidsModel.getElementAt(lstSelectedClassaids.getSelectedIndex()));
		lstSelectedClassAidsModel.remove(lstSelectedClassaids.getSelectedIndex());
	}

	private void addAids(int index)
	{

		arraySelectedStudyAids.put(arrayAvailableStudyAids.get(index), arrayAvailableStudyAids.get(index));
		arrayAvailableStudyAids.remove(index);
		lstSelectedClassAidsModel.addElement(lstClassAidsModel.getElementAt(lstClassAids.getSelectedIndex()));
		lstClassAidsModel.remove(lstClassAids.getSelectedIndex());

	}

	private void setSelectedClass()
	{
		// /// TODO -1
		int index = cmbxEditClass.getSelectedIndex();
		if ((arrayClasses != null) && (!arrayClasses.isEmpty()) && (index >= 0))
		{
			cmbxcampus.setSelectedIndex(arrayClasses.get(index).getCampus());
			cmbBxBlding.setSelectedIndex(arrayClasses.get(index).getBuilding());
			txtClassSelectedCampus.setText(cmbxcampus.getSelectedItem().toString());
			txtClassSelectedBuildig.setText(cmbBxBlding.getSelectedItem().toString());
			txtCapacityNumber.setText(Integer.toString(arrayClasses.get(index).getCapcity()));
			txtDescriptionText.setText((arrayClasses.get(index).getDescription()));
			chckbxAvailable.setSelected(arrayClasses.get(index).getAvailable());
			setClassAids(index);
			txtpnCodeNumber.setText(Integer.toString(arrayClasses.get(index).getClassID()));
			btnSaveChanges.setEnabled(false);
		}

		if (index < 0)
			setdefault();

	}

	/**
	 * @see need to do: maybe make a set(or map) i didn't finish
	 */
	private void setClassAids(int clssIndex)
	{
		resetLists();

		for (int i = 0; i < arrayClasses.get(clssIndex).getStudyAids().size(); i++)
		{
			arraySelectedStudyAids.put(arrayClasses.get(clssIndex).getStudyAids().get(i).getAidsID(), arrayClasses.get(clssIndex).getStudyAids().get(i).getAidsID());
			lstSelectedClassAidsModel.addElement(arrayClasses.get(clssIndex).getStudyAids().get(i).getAidsID() + ":"
					+ arrayStudyAids.get(arrayClasses.get(clssIndex).getStudyAids().get(i).getAidsID()).getAidsName());
		}
		Iterator<StudyAids> itr = arrayStudyAids.values().iterator();
		while (itr.hasNext())
		{
			int tempID = itr.next().getAidsID();
			if (!arraySelectedStudyAids.containsKey(tempID))
			{
				arrayAvailableStudyAids.put(tempID, tempID);
				lstClassAidsModel.addElement(tempID + ":" + arrayStudyAids.get(tempID).getAidsName());
			}

		}
	}

	private void resetLists()
	{
		arraySelectedStudyAids.clear();
		arrayAvailableStudyAids.clear();
		lstClassAidsModel.removeAllElements();
		lstSelectedClassAidsModel.removeAllElements();

	}

	private void setdefault()
	{

		txtpnCodeNumber.setText("Code Number");
		txtCapacityNumber.setText("Capacity number");
		txtDescriptionText.setText("description text");
		txtClassSelectedCampus.setText(txtSelectedCampus);
		txtClassSelectedBuildig.setText("class selected buildig");
		chckbxAvailable.setSelected(true);
		btnSaveChanges.setEnabled(false);

	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e)
	{
		if ((e.getSource() == txtpnCodeNumber) || (e.getSource() == txtCapacityNumber))
			if (!Character.isDigit(e.getKeyChar()))
				((JTextField) e.getSource()).setText("0");
			else
				btnSaveChanges.setEnabled(true);
		if (txtpnCodeNumber.getText().equals("") || txtCapacityNumber.getText().equals(""))
			btnSaveChanges.setEnabled(false);

	}

	private void createNewClass(boolean bool)
	{
		txtpnCodeNumber.setEditable(bool);
		txtpnCodeNumber.setEnabled(bool);

		txtDescriptionText.setEditable(bool);
		txtDescriptionText.setEnabled(bool);

		cmbxcampus.setEnabled(bool);
		cmbxcampus.setEditable(bool);

		cmbBxBlding.setEnabled(bool);
		cmbBxBlding.setEditable(bool);

		if (bool)
		{
			txtDescriptionText.setText("");
			txtCapacityNumber.setText("0");
			cmbBxBlding.setSelectedIndex(0);
			txtpnCodeNumber.setText("");
			cmbxcampus.setSelectedIndex(0);
		}

		isNewClass = bool;
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{

	}

	public void setClassStudyAids(ArrayList<StudyAids> arrayList)
	{

		arrayStudyAids = new HashMap<Integer, StudyAids>();
		arrayAvailableStudyAids = new HashMap<Integer, Integer>();
		arraySelectedStudyAids = new HashMap<Integer, Integer>();
		lstClassAidsModel.removeAllElements();
		lstSelectedClassAidsModel.removeAllElements();
		for (int i = 0; i < arrayList.size(); i++)
		{
			arrayStudyAids.put(arrayList.get(i).getAidsID(), arrayList.get(i));
			lstClassAidsModel.addElement(arrayList.get(i).getAidsID() + ":" + arrayList.get(i).getAidsName());
			arrayAvailableStudyAids.put(arrayList.get(i).getAidsID(), arrayList.get(i).getAidsID());
		}
	}

	public void setCampus(ArrayList<Campus> arrayList)
	{

		arrayCampus = arrayList;
		for (int i = 0; i < arrayCampus.size(); i++)
		{
			cmbxcampus.addItem(arrayCampus.get(i).getCampusId() + ":" + arrayCampus.get(i).getCampusName());
		}
	}

	public void setBuilding(ArrayList<Building> arrayList)
	{
		arrayBuilding = arrayList;
		for (int i = 0; i < arrayBuilding.size(); i++)
		{
			cmbBxBlding.addItem(arrayBuilding.get(i).getBuildingID() + ":" + arrayBuilding.get(i).getBuildingName());
		}
	}

	public void setClasses(ArrayList<Class> arrayList)
	{
		arrayClasses = arrayList;
		cmbxEditClass.removeAll();
		for (int i = 0; i < arrayClasses.size(); i++)
		{
			cmbxEditClass.addItem(arrayClasses.get(i).getClassID() + ":" + arrayClasses.get(i).getDescription());
		}
	}

	public void addActions()
	{
		// TODO Auto-generated method stub
		btnDiscard.addActionListener(this);
		btnSaveChanges.addActionListener(this);
		btnNewClass.addActionListener(this);
		btnRemove.addActionListener(this);
		btnAdd.addActionListener(this);
		cmbxEditClass.addActionListener(this);
	}

}
