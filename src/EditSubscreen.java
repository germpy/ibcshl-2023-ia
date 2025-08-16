import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Arrays;

public class EditSubscreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Medicine> myLocalList;
	private ArrayList <Medicine> medList = new ArrayList<Medicine>();
	ArrayList<String> days = new ArrayList<String>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
	JComboBox comboBox = new JComboBox<>(days.toArray());
	DefaultTableModel model;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSubscreen frame = new EditSubscreen(new ArrayList<Medicine>());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EditSubscreen(ArrayList<Medicine> list) {
		myLocalList = list;
		setText();
		
		setTable();
				
		setButtons();
	}

	private void setButtons() {
		JButton SelectButton = new JButton("SELECT");
		SelectButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		SelectButton.setBorder(null);
		SelectButton.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_SelectButton = new GridBagConstraints();
		gbc_SelectButton.insets = new Insets(0, 0, 0, 5);
		gbc_SelectButton.fill = GridBagConstraints.BOTH;
		gbc_SelectButton.gridx = 0;
		gbc_SelectButton.gridy = 9;
		contentPane.add(SelectButton, gbc_SelectButton);
		SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Please select a row!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						if (table.getSelectedRow() == 0)
							throw new Exception("You cannot select the first row!");
						Medicine med = medList.get(table.getSelectedRow() - 1);
						EditScreen window = new EditScreen(myLocalList, med);
						window.setVisible(true);
						setVisible(false);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Some issue with selection! " + ex.getLocalizedMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);						
						ex.printStackTrace();
					}
				}
				
			}
		});
		
		JButton BackButton = new JButton("BACK");
		BackButton.setBorder(null);
		BackButton.setBackground(new Color(176, 196, 222));
		BackButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		GridBagConstraints gbc_BackButton = new GridBagConstraints();
		gbc_BackButton.fill = GridBagConstraints.BOTH;
		gbc_BackButton.gridx = 3;
		gbc_BackButton.gridy = 9;
		contentPane.add(BackButton, gbc_BackButton);
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeScreen window = new HomeScreen(myLocalList);
				window.setVisible(true);
				setVisible(false);
			}
		});
		
	}

	private void setTable() {
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setText(comboBox.getSelectedIndex());
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);
		
		model = new DefaultTableModel(0,4);
		model.addRow(new String[]{ "Day", "Name", "Amount", "AM / PM", "Notes"});	
		table = new JTable(model);
		table.setDefaultEditor(Object.class, null);
		table.setRowSelectionAllowed(true);
		setText(comboBox.getSelectedIndex());
		
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
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel TitleText = new JLabel("Choose Existing Entry");
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
	}
	
	private void setText(int i) {
		int rowCount = model.getRowCount();
		medList.clear();
		//Remove rows one by one from the end of the table
		for (int x = rowCount - 1; x >= 1; x--) {
			model.removeRow(x);
		}
		for (Medicine med : myLocalList) 
		{
			if (med.isDaily() || med.getSchedule()[i])
			{
				medList.add(med);
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
