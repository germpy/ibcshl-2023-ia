import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class EditScreen extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField AmountField;
	private JTextField NotesField;
	private ArrayList<Medicine> myLocalList;
	private Medicine myMed;
	private JCheckBox MondayCheckbox, TuesdayCheckbox, WednesdayCheckbox, ThursdayCheckbox, FridayCheckbox, SaturdayCheckbox, SundayCheckbox, DailyCheckbox;
	private JCheckBox AMCheckbox;
	private JCheckBox PMCheckbox;


	public EditScreen(ArrayList<Medicine> list, Medicine med) {
		myLocalList = list;
		myMed = med;
		setText();
		
		setDayCheckboxes();
	
		
		setButtons();
		setParameters();	
		
	}

	private void setText() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 333);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{68, 62, 98, 123};
		gbl_contentPane.rowHeights = new int[]{35, 7, 6, 0, 0, 30, 30, 24, 30, 27, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel TitleText = new JLabel("Edit");
		TitleText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_TitleText = new GridBagConstraints();
		gbc_TitleText.gridwidth = 2;
		gbc_TitleText.fill = GridBagConstraints.VERTICAL;
		gbc_TitleText.insets = new Insets(0, 0, 5, 5);
		gbc_TitleText.gridx = 1;
		gbc_TitleText.gridy = 0;
		contentPane.add(TitleText, gbc_TitleText);
	}

	private void setParameters() {
		
		JButton searchButton = new JButton("Search for existing entry...");
		searchButton.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditSubscreen window = new EditSubscreen(myLocalList);
				window.setVisible(true);
				setVisible(false);
			}
		});
		searchButton.setBorder(null);
		searchButton.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.fill = GridBagConstraints.BOTH;
		gbc_searchButton.gridwidth = 2;
		gbc_searchButton.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton.gridx = 1;
		gbc_searchButton.gridy = 1;
		contentPane.add(searchButton, gbc_searchButton);
		
		JLabel DayText = new JLabel("Day:");
		DayText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_DayText = new GridBagConstraints();
		gbc_DayText.anchor = GridBagConstraints.EAST;
		gbc_DayText.insets = new Insets(0, 0, 5, 5);
		gbc_DayText.gridx = 0;
		gbc_DayText.gridy = 2;
		contentPane.add(DayText, gbc_DayText);
		
		JLabel TimeText = new JLabel("Time:");
		TimeText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_TimeText = new GridBagConstraints();
		gbc_TimeText.insets = new Insets(0, 0, 5, 5);
		gbc_TimeText.anchor = GridBagConstraints.EAST;
		gbc_TimeText.gridx = 0;
		gbc_TimeText.gridy = 5;
		contentPane.add(TimeText, gbc_TimeText);
		
		//
		// JComboBox timeDropdown = new JComboBox();
		// timeDropdown.setBorder(null);
		// timeDropdown.setBackground(new Color(176, 196, 222));
		//
		// GridBagConstraints gbc_timeDropdown = new GridBagConstraints();
		// gbc_timeDropdown.insets = new Insets(0, 0, 5, 5);
		// gbc_timeDropdown.fill = GridBagConstraints.HORIZONTAL;
		// gbc_timeDropdown.gridx = 1;
		// gbc_timeDropdown.gridy = 5;
		// contentPane.add(timeDropdown, gbc_timeDropdown);
		//
		AMCheckbox = new JCheckBox("AM");
		AMCheckbox.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_AMCheckbox = new GridBagConstraints();
		gbc_AMCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_AMCheckbox.gridx = 1;
		gbc_AMCheckbox.gridy = 5;
		contentPane.add(AMCheckbox, gbc_AMCheckbox);

		 PMCheckbox = new JCheckBox("PM");
		 PMCheckbox.setBackground(new Color(176, 196, 222));
		 GridBagConstraints gbc_PM_Checkbox = new GridBagConstraints();
		 gbc_PM_Checkbox.insets = new Insets(0, 0, 5, 5);
		 gbc_PM_Checkbox.gridx = 2;
		 gbc_PM_Checkbox.gridy = 5;
		 contentPane.add(PMCheckbox, gbc_PM_Checkbox);
		JLabel NameText = new JLabel("Name:");
		NameText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_NameText = new GridBagConstraints();
		gbc_NameText.anchor = GridBagConstraints.EAST;
		gbc_NameText.insets = new Insets(0, 0, 5, 5);
		gbc_NameText.gridx = 0;
		gbc_NameText.gridy = 6;
		contentPane.add(NameText, gbc_NameText);
		
		if (myMed.getAmount()[0] != 0) {
			AMCheckbox.setSelected(true);
		}
		if (myMed.getAmount()[1] != 0) {
			PMCheckbox.setSelected(true);
		}
		
		NameField = new JTextField();
		NameField.setText(myMed.getName());
		GridBagConstraints gbc_NameField = new GridBagConstraints();
		gbc_NameField.gridwidth = 3;
		gbc_NameField.insets = new Insets(0, 0, 5, 0);
		gbc_NameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_NameField.gridx = 1;
		gbc_NameField.gridy = 6;
		contentPane.add(NameField, gbc_NameField);
		NameField.setColumns(10);
		
		JLabel AmountText = new JLabel("Amount:");
		AmountText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_AmountText = new GridBagConstraints();
		gbc_AmountText.insets = new Insets(0, 0, 5, 5);
		gbc_AmountText.anchor = GridBagConstraints.EAST;
		gbc_AmountText.gridx = 0;
		gbc_AmountText.gridy = 7;
		contentPane.add(AmountText, gbc_AmountText);
		
		AmountField = new JTextField();
		AmountField.setText(Integer.toString(Math.max(myMed.getAmount()[1], myMed.getAmount()[0])));
		AmountField.setColumns(10);
		GridBagConstraints gbc_AmountField = new GridBagConstraints();
		gbc_AmountField.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_AmountField.insets = new Insets(0, 0, 5, 5);
		gbc_AmountField.fill = GridBagConstraints.HORIZONTAL;
		gbc_AmountField.gridx = 1;
		gbc_AmountField.gridy = 7;
		contentPane.add(AmountField, gbc_AmountField);
		
		JLabel NotesText = new JLabel("Notes:");
		NotesText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_NotesText = new GridBagConstraints();
		gbc_NotesText.insets = new Insets(0, 0, 5, 5);
		gbc_NotesText.anchor = GridBagConstraints.EAST;
		gbc_NotesText.gridx = 2;
		gbc_NotesText.gridy = 7;
		contentPane.add(NotesText, gbc_NotesText);
		
		NotesField = new JTextField();
		NotesField.setText(myMed.getNotes());
		NotesField.setColumns(10);
		GridBagConstraints gbc_NotesField = new GridBagConstraints();
		gbc_NotesField.insets = new Insets(0, 0, 5, 0);
		gbc_NotesField.gridheight = 2;
		gbc_NotesField.fill = GridBagConstraints.BOTH;
		gbc_NotesField.gridx = 3;
		gbc_NotesField.gridy = 7;
		contentPane.add(NotesField, gbc_NotesField);

	}

	private void setButtons() {
		configureSave();
		
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
				if (AMCheckbox.isSelected() || PMCheckbox.isSelected()
						|| MondayCheckbox.isSelected() || TuesdayCheckbox.isSelected() || WednesdayCheckbox.isSelected() || ThursdayCheckbox.isSelected() || FridayCheckbox.isSelected() || SaturdayCheckbox.isSelected() || SundayCheckbox.isSelected() || DailyCheckbox.isSelected()
						|| !NameField.getText().isEmpty() || !AmountField.getText().isEmpty()) {
					Object[] options = {"CONFIRM",
		                    "BACK"};
					int n = JOptionPane.showOptionDialog(contentPane,
						    "Would you like to exit without saving",
						    "CONFIRMATION",
						    JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    options,
						    options[1]);
					if (n == 1)
						return;
				}
				HomeScreen window = new HomeScreen(myLocalList);
				window.setVisible(true);
				setVisible(false);
			}
		});
	}

	private void configureSave() {
		JButton SaveButton = new JButton("SAVE");
		SaveButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		SaveButton.setBorder(null);
		SaveButton.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_SaveButton = new GridBagConstraints();
		gbc_SaveButton.insets = new Insets(0, 0, 0, 5);
		gbc_SaveButton.fill = GridBagConstraints.BOTH;
		gbc_SaveButton.gridx = 0;
		gbc_SaveButton.gridy = 9;
		contentPane.add(SaveButton, gbc_SaveButton);
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((!AMCheckbox.isSelected() && !PMCheckbox.isSelected() )
						||(!MondayCheckbox.isSelected() && !TuesdayCheckbox.isSelected() && !WednesdayCheckbox.isSelected() && !ThursdayCheckbox.isSelected() && !FridayCheckbox.isSelected() && !SaturdayCheckbox.isSelected() && !SundayCheckbox.isSelected() && !DailyCheckbox.isSelected())
							|| (NameField.getText().isEmpty()) || (AmountField.getText().isEmpty()))
					{
						//System.out.println("MGSONDS");
						throw new Exception("");
					}
					int index = myLocalList.indexOf(myMed);
					myLocalList.set(index,
							new Medicine(NameField.getText(), 
							Integer.parseInt(AmountField.getText()), 
							AMCheckbox.isSelected(), PMCheckbox.isSelected(), 
							new boolean[] {MondayCheckbox.isSelected(), TuesdayCheckbox.isSelected(), WednesdayCheckbox.isSelected(), ThursdayCheckbox.isSelected(), FridayCheckbox.isSelected(), SaturdayCheckbox.isSelected(), SundayCheckbox.isSelected()}, 
							DailyCheckbox.isSelected(),
							(NotesField.getText().isEmpty()) ? " " : NotesField.getText()));
					NameField.setText("");
					AmountField.setText("");
					NotesField.setText("");
					AMCheckbox.setSelected(false); PMCheckbox.setSelected(false);
					MondayCheckbox.setSelected(false); TuesdayCheckbox.setSelected(false); WednesdayCheckbox.setSelected(false); ThursdayCheckbox.setSelected(false);
					FridayCheckbox.setSelected(false); SaturdayCheckbox.setSelected(false); SundayCheckbox.setSelected(false); 
					DailyCheckbox.setSelected(false);
					HomeScreen.writeToFile(myLocalList);
				} catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(null, "Incorrect Input! Check fields and try again; " + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	
	private void setDayCheckboxes() {
		MondayCheckbox = new JCheckBox("Monday");
		MondayCheckbox.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		MondayCheckbox.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_MondayCheckbox = new GridBagConstraints();
		gbc_MondayCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_MondayCheckbox.gridx = 1;
		gbc_MondayCheckbox.gridy = 2;
		contentPane.add(MondayCheckbox, gbc_MondayCheckbox);
		
		ThursdayCheckbox = new JCheckBox("Thursday");
		ThursdayCheckbox.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		ThursdayCheckbox.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_ThursdayCheckbox = new GridBagConstraints();
		gbc_ThursdayCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_ThursdayCheckbox.gridx = 2;
		gbc_ThursdayCheckbox.gridy = 2;
		contentPane.add(ThursdayCheckbox, gbc_ThursdayCheckbox);
		
		SundayCheckbox = new JCheckBox("Sunday");
		ThursdayCheckbox.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		SundayCheckbox.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_SundayCheckbox = new GridBagConstraints();
		gbc_SundayCheckbox.insets = new Insets(0, 0, 5, 0);
		gbc_SundayCheckbox.gridx = 3;
		gbc_SundayCheckbox.gridy = 2;
		contentPane.add(SundayCheckbox, gbc_SundayCheckbox);
		
		TuesdayCheckbox = new JCheckBox("Tuesday");
		TuesdayCheckbox.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		TuesdayCheckbox.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_TuesdayCheckbox = new GridBagConstraints();
		gbc_TuesdayCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_TuesdayCheckbox.gridx = 1;
		gbc_TuesdayCheckbox.gridy = 3;
		contentPane.add(TuesdayCheckbox, gbc_TuesdayCheckbox);
		
		FridayCheckbox = new JCheckBox("Friday");
		FridayCheckbox.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		FridayCheckbox.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_FridayCheckbox = new GridBagConstraints();
		gbc_FridayCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_FridayCheckbox.gridx = 2;
		gbc_FridayCheckbox.gridy = 3;
		contentPane.add(FridayCheckbox, gbc_FridayCheckbox);
		
		DailyCheckbox = new JCheckBox("Daily");
		
		FridayCheckbox.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		DailyCheckbox.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_DailyCheckbox = new GridBagConstraints();
		gbc_DailyCheckbox.insets = new Insets(0, 0, 5, 0);
		gbc_DailyCheckbox.gridx = 3;
		gbc_DailyCheckbox.gridy = 3;
		contentPane.add(DailyCheckbox, gbc_DailyCheckbox);
		
		WednesdayCheckbox = new JCheckBox("Wednesday");
		WednesdayCheckbox.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_WednesdayCheckbox = new GridBagConstraints();
		gbc_WednesdayCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_WednesdayCheckbox.gridx = 1;
		gbc_WednesdayCheckbox.gridy = 4;
		contentPane.add(WednesdayCheckbox, gbc_WednesdayCheckbox);
		
		SaturdayCheckbox = new JCheckBox("Saturday");
		SaturdayCheckbox.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_SaturdayCheckbox = new GridBagConstraints();
		gbc_SaturdayCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_SaturdayCheckbox.gridx = 2;
		gbc_SaturdayCheckbox.gridy = 4;
		contentPane.add(SaturdayCheckbox, gbc_SaturdayCheckbox);
		JCheckBox[] checkboxes = new JCheckBox[] {MondayCheckbox, TuesdayCheckbox, WednesdayCheckbox, ThursdayCheckbox, FridayCheckbox, SaturdayCheckbox, SundayCheckbox};

		if (myMed.isDaily())
			DailyCheckbox.setSelected(true);
		for (int i = 0; i < 7; i++)
		{
			if (myMed.isDaily())
			{
				checkboxes[i].setEnabled(false);
			}
			else if (myMed.getSchedule()[i])
			{
				checkboxes[i].setSelected(true);
			}
		}
		DailyCheckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkbox : checkboxes)
				{
					checkbox.setEnabled(!DailyCheckbox.isSelected());
					checkbox.setSelected(false);
				}
			}
		});
	}

}
