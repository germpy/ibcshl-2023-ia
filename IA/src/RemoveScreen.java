import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

public class RemoveScreen extends JFrame {

	private JPanel contentPane;
	private ArrayList<Medicine> myLocalList;
	private ArrayList <Medicine> medList = new ArrayList<Medicine>();
	ArrayList<String> days = new ArrayList<String>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
	JComboBox comboBox = new JComboBox<>(days.toArray());
	DefaultTableModel model;
	private JTable table;

	public RemoveScreen(ArrayList<Medicine> list) {
		myLocalList = list;
		setText();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setText(comboBox.getSelectedIndex());
			}
		});
		
		model = new DefaultTableModel(0,4);
		model.addRow(new String[]{ "Day", "Name", "Amount", "AM / PM", "Notes"});	
		table = new JTable(model);
		table.setDefaultEditor(Object.class, null);
		//table.setEnabled(false);
		table.setRowSelectionAllowed(true);
		setText(comboBox.getSelectedIndex());
		
		JButton RemoveButton = new JButton("REMOVE");
		RemoveButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		RemoveButton.setBorder(null);
		RemoveButton.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_RemoveButton = new GridBagConstraints();
		gbc_RemoveButton.insets = new Insets(0, 0, 0, 5);
		gbc_RemoveButton.fill = GridBagConstraints.BOTH;
		gbc_RemoveButton.gridx = 0;
		gbc_RemoveButton.gridy = 9;
		contentPane.add(RemoveButton, gbc_RemoveButton);
		RemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() == 0)
						throw new Exception("You cannot select the first row");
					Medicine med = medList.get(table.getSelectedRow() - 1);
					myLocalList.remove(med);
					HomeScreen.writeToFile(myLocalList);
					setText(comboBox.getSelectedIndex());
				} catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Some issue with removal! " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);;
				}
			}
		});
		
		JButton HomeButton = new JButton("HOME");
		HomeButton.setBorder(null);
		HomeButton.setBackground(new Color(176, 196, 222));
		HomeButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		GridBagConstraints gbc_HomeButton = new GridBagConstraints();
		gbc_HomeButton.fill = GridBagConstraints.BOTH;
		gbc_HomeButton.gridx = 3;
		gbc_HomeButton.gridy = 9;
		contentPane.add(HomeButton, gbc_HomeButton);
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeScreen window = new HomeScreen(myLocalList);
				window.setVisible(true);
				setVisible(false);
			}
		});
		
		
		
		contentPane.add(table);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 7;
		gbc_table.gridwidth = 4;
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		contentPane.add(table, gbc_table);
	}

	private void setText() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 324);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{68, 62, 98, 123};
		gbl_contentPane.rowHeights = new int[]{35, 7, 6, 0, 0, 30, 30, 24, 30, 27, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel TitleText = new JLabel("Remove");
		TitleText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_TitleText = new GridBagConstraints();
		gbc_TitleText.gridwidth = 2;
		gbc_TitleText.fill = GridBagConstraints.VERTICAL;
		gbc_TitleText.insets = new Insets(0, 0, 5, 5);
		gbc_TitleText.gridx = 1;
		gbc_TitleText.gridy = 0;
		contentPane.add(TitleText, gbc_TitleText);
		
		JLabel DayText = new JLabel("Day:");
		DayText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_DayText = new GridBagConstraints();
		gbc_DayText.anchor = GridBagConstraints.EAST;
		gbc_DayText.insets = new Insets(0, 0, 5, 5);
		gbc_DayText.gridx = 0;
		gbc_DayText.gridy = 1;
		contentPane.add(DayText, gbc_DayText);
		
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);
	}
	
	private void setText(int i) {
		int rowCount = model.getRowCount();
		medList.clear();
		//Remove rows one by one from the end of the table
		for (int x = rowCount - 1; x >= 1; x--) {
			model.removeRow(x);
		}
		for (Medicine med : myLocalList) // int x = 0; x < myLocalList.size(); x++ 
		{
			//Medicine med = myLocalList.get(x); 
			if (med.isDaily() || med.getSchedule()[i])
			{
				medList.add(med);
				//System.out.println(med.getName());
				model.addRow(new String[]{ 
						(med.isDaily()) ? "Daily" : days.get(comboBox.getSelectedIndex()),
						med.getName(),
						String.valueOf(Math.max(med.getAmount()[0], med.getAmount()[1])), 
						(med.getAmount()[0] != 0 && med.getAmount()[1] != 0) ? "AM & PM" : (med.getAmount()[0] != 0)? "AM" : "PM", 
						med.getNotes()});
			}
		}
	}
}
