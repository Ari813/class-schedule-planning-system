package GUI;


import java.awt.EventQueue;
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
import java.util.Locale;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
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
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;


public class Edit_Course extends JPanel {

	

	private JTextField txtDescriptionText;
	private JTextField txtCapacityNumber;
	private JTextField txtIdNumber;
	private JTextField txtCoursName;
	/**
	 * Create the panel.
	 */
	public Edit_Course() {
		JLabel lblTimeTableSchedualing = new JLabel("Time Table Schedualing System");
		lblTimeTableSchedualing.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeTableSchedualing.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeTableSchedualing.setLocation(new Point(50, 0));
		lblTimeTableSchedualing.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTimeTableSchedualing.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTimeTableSchedualing.setBounds(new Rectangle(10, 11, 774, 64));
		lblTimeTableSchedualing.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	
		JPanel PNL_Main = new JPanel();
		PNL_Main.setToolTipText("Edit class list");
		PNL_Main.setMinimumSize(new Dimension(774, 474));
		PNL_Main.setMaximumSize(new Dimension(774, 474));
		PNL_Main.setBounds(10, 85, 774, 474);
	
		PNL_Main.setLayout(null);
		
		JLabel lblEditCourse = new JLabel("Edit course:");
		lblEditCourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEditCourse.setBounds(10, 11, 115, 14);
		PNL_Main.add(lblEditCourse);
		
		JComboBox cmbBxEditCouse = new JComboBox();
		cmbBxEditCouse.setBounds(58, 36, 506, 20);
		PNL_Main.add(cmbBxEditCouse);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(10, 81, 46, 14);
		PNL_Main.add(lblId);
		
		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaculty.setBounds(10, 196, 88, 22);
		PNL_Main.add(lblFaculty);
		
		JLabel lblCoursName = new JLabel("Cours Name:");
		lblCoursName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCoursName.setBounds(10, 131, 125, 20);
		PNL_Main.add(lblCoursName);
		
		txtIdNumber = new JTextField();
		txtIdNumber.setEnabled(false);
		txtIdNumber.setEditable(false);
		txtIdNumber.setText("ID Number");
		txtIdNumber.setBounds(20, 103, 86, 20);
		PNL_Main.add(txtIdNumber);
		txtIdNumber.setColumns(10);
		
		txtCoursName = new JTextField();
		txtCoursName.setEnabled(false);
		txtCoursName.setText("cours name");
		txtCoursName.setBounds(20, 164, 86, 20);
		PNL_Main.add(txtCoursName);
		txtCoursName.setColumns(10);
		
		JComboBox cmBxFaculty = new JComboBox();
		cmBxFaculty.setBounds(14, 225, 193, 21);
		PNL_Main.add(cmBxFaculty);
		
	}
}
