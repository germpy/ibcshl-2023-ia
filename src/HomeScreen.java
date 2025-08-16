import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;
import java.time.*;

@SuppressWarnings("serial")
public class HomeScreen extends JFrame {

	private JPanel contentPane;
	private ArrayList<Medicine> myLocalList;
	private Calendar cal;
	private int lastDate;
	private int streak;
	private int lastYear;
	private boolean wasEmpty;
	ArrayList<Medicine> AMlist;
	ArrayList<Medicine> PMlist;
	
	public static void main(String[] args) { // Runs at startup
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen frame = new HomeScreen(new ArrayList<Medicine>());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});                                    
	}

	public HomeScreen(ArrayList<Medicine> list) {
		cal = Calendar.getInstance();
		myLocalList = list;
		loadStreak();
		loadFile();

		configureFrame(); // configures frame

		configureText(); // sets various text: date time, good [x], etc

		configureAMPM(); // sets AM, PM texts
		
		configureStreak(); // initializes streak

		configureButtons(); // configures buttons
	}

	private void configureFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 149, 120, 119, 42 };
		gbl_contentPane.rowHeights = new int[] { 35, 7, 6, 30, 30, 24, 30, 0, 42, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
	}

	private void configureText() {
		JLabel TitleText = new JLabel();
		TitleText.setFont(new Font("Nirmala UI", Font.PLAIN, 23));
		TitleText.setText("Med Manager");
		GridBagConstraints gbc_TitleText = new GridBagConstraints();
		gbc_TitleText.anchor = GridBagConstraints.NORTH;
		gbc_TitleText.insets = new Insets(0, 0, 5, 5);
		gbc_TitleText.gridwidth = 2;
		gbc_TitleText.gridx = 0;
		gbc_TitleText.gridy = 0;
		contentPane.add(TitleText, gbc_TitleText);

		JTextArea GreetingText = new JTextArea();
		GreetingText.setEditable(false);
		GreetingText.setBackground(new Color(173, 216, 230));
		GreetingText.setForeground(new Color(0, 0, 0));
		GreetingText.setFont(new Font("Nirmala UI", Font.PLAIN, 16));

		int hour = cal.get(Calendar.HOUR_OF_DAY); //Checks time of day so correct message is displayed
		if (hour >= 18) { //6:00pm+
			GreetingText.setText("Good Night!");
		} else if (hour >= 12) { //12:00pm+
			GreetingText.setText("Good Afternoon!");
		} else { //12:00am --> 11:59am
			GreetingText.setText("Good Morning! ");
		}
		GridBagConstraints gbc_GreetingText = new GridBagConstraints();
		gbc_GreetingText.gridwidth = 2;
		gbc_GreetingText.anchor = GridBagConstraints.SOUTH;
		gbc_GreetingText.insets = new Insets(0, 0, 5, 5);
		gbc_GreetingText.gridx = 0;
		gbc_GreetingText.gridy = 1;
		contentPane.add(GreetingText, gbc_GreetingText);

		JTextArea DateText = new JTextArea(); //Displays current date and time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY hh:mm a");
		LocalDateTime now = LocalDateTime.now();
		DateText.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		DateText.setEditable(false);
		DateText.setText("TODAY: " + dtf.format(now));
		DateText.setForeground(new Color(0, 0, 0));
		DateText.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_DateText = new GridBagConstraints();
		gbc_DateText.gridwidth = 2;
		gbc_DateText.fill = GridBagConstraints.BOTH;
		gbc_DateText.insets = new Insets(0, 0, 5, 5);
		gbc_DateText.gridx = 0;
		gbc_DateText.gridy = 2;
		contentPane.add(DateText, gbc_DateText);
	}

	private void configureStreak() {
		JLabel StreakText = new JLabel("STREAK: " + streak);
		StreakText.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		GridBagConstraints gbc_StreakText = new GridBagConstraints();
		gbc_StreakText.gridwidth = 2;
		gbc_StreakText.anchor = GridBagConstraints.NORTH;
		gbc_StreakText.insets = new Insets(0, 0, 5, 5);
		gbc_StreakText.gridx = 2;
		gbc_StreakText.gridy = 1;
		contentPane.add(StreakText, gbc_StreakText);
		JButton StreakButton = new JButton("I took my meds today!");
		StreakButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		StreakButton.setBorder(null);
		StreakButton.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_StreakButton = new GridBagConstraints();
		gbc_StreakButton.fill = GridBagConstraints.BOTH;
		gbc_StreakButton.insets = new Insets(0, 0, 5, 5);
		gbc_StreakButton.gridwidth = 2;
		gbc_StreakButton.gridx = 2;
		gbc_StreakButton.gridy = 2;
		
		if (AMlist.isEmpty() && PMlist.isEmpty()) //if no medicines are scheduled for the day
		{
			StreakButton.setText("No meds today; Streak perserved! :)"); //streak is not punished
			StreakButton.setEnabled(false);  
			saveStreak();
		}
		else
		{
			StreakButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (wasEmpty == false && (cal.get(Calendar.YEAR) == lastYear && cal.get(Calendar.DAY_OF_YEAR) == lastDate)
							|| (lastYear - cal.get(Calendar.YEAR) == 1 && lastDate >= 365
									&& cal.get(Calendar.DAY_OF_YEAR) == 1)) // check if is new years
					{
						JOptionPane.showMessageDialog(null, "You already took meds today!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						;
					} else {
						wasEmpty = false; //checks if the user added medicine for today if it was previously empty
						lastDate = cal.get(Calendar.DAY_OF_YEAR);
						lastYear = cal.get(Calendar.YEAR);
						StreakText.setText("STREAK: " + ++streak);
						saveStreak();
					}
				}
			});
		}
		contentPane.add(StreakButton, gbc_StreakButton);
	}

	private void configureButtons() {
		JButton AddButton = new JButton("ADD");
		AddButton.setBorder(null);
		AddButton.setBackground(new Color(176, 196, 222));
		AddButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddScreen window = new AddScreen(myLocalList);
				window.setVisible(true);
				setVisible(false);
			}
		});

		JButton ScheduleButton = new JButton("My Schedule");
		ScheduleButton.setFont(new Font("Nirmala UI", Font.PLAIN, 17));
		ScheduleButton.setBackground(new Color(176, 196, 222));
		ScheduleButton.setBorder(null);
		ScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarScreen window = new CalendarScreen(myLocalList);
				window.setVisible(true);
				setVisible(false);
			}
		});

		GridBagConstraints gbc_ScheduleButton = new GridBagConstraints();
		gbc_ScheduleButton.gridheight = 2;
		gbc_ScheduleButton.fill = GridBagConstraints.BOTH;
		gbc_ScheduleButton.gridwidth = 3;
		gbc_ScheduleButton.insets = new Insets(0, 0, 5, 0);
		gbc_ScheduleButton.gridx = 2;
		gbc_ScheduleButton.gridy = 4;
		contentPane.add(ScheduleButton, gbc_ScheduleButton);

		GridBagConstraints gbc_AddButton = new GridBagConstraints();
		gbc_AddButton.gridheight = 2;
		gbc_AddButton.fill = GridBagConstraints.BOTH;
		gbc_AddButton.insets = new Insets(0, 0, 0, 5);
		gbc_AddButton.gridx = 0;
		gbc_AddButton.gridy = 7;
		contentPane.add(AddButton, gbc_AddButton);

		JButton RemoveButton = new JButton("REMOVE");
		RemoveButton.setIconTextGap(2);
		RemoveButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		RemoveButton.setBorder(null);
		RemoveButton.setBackground(new Color(176, 196, 222));
		RemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveScreen removeWindow = new RemoveScreen(myLocalList);
				removeWindow.setVisible(true);
				setVisible(false);
			}
		});

		GridBagConstraints gbc_RemoveButton = new GridBagConstraints();
		gbc_RemoveButton.gridheight = 2;
		gbc_RemoveButton.fill = GridBagConstraints.BOTH;
		gbc_RemoveButton.insets = new Insets(0, 0, 0, 5);
		gbc_RemoveButton.gridx = 1;
		gbc_RemoveButton.gridy = 7;
		contentPane.add(RemoveButton, gbc_RemoveButton);

		JButton EditButton = new JButton("EDIT");
		EditButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		EditButton.setBorder(null);
		EditButton.setBackground(new Color(176, 196, 222));
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditSubscreen editWindow = new EditSubscreen(myLocalList);
				editWindow.setVisible(true);
				setVisible(false);
			}
		});

		GridBagConstraints gbc_EditButton = new GridBagConstraints();
		gbc_EditButton.gridheight = 2;
		gbc_EditButton.insets = new Insets(0, 0, 0, 5);
		gbc_EditButton.fill = GridBagConstraints.BOTH;
		gbc_EditButton.gridx = 2;
		gbc_EditButton.gridy = 7;
		contentPane.add(EditButton, gbc_EditButton);
	}

	private void configureAMPM() {
		JScrollPane AMScroll = new JScrollPane();
		AMScroll.setWheelScrollingEnabled(true);
		AMScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		AMScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_AMScroll = new GridBagConstraints();
		gbc_AMScroll.gridheight = 2;
		gbc_AMScroll.gridwidth = 2;
		gbc_AMScroll.fill = GridBagConstraints.BOTH;
		gbc_AMScroll.insets = new Insets(0, 0, 5, 5);
		gbc_AMScroll.gridx = 0;
		gbc_AMScroll.gridy = 3;
		contentPane.add(AMScroll, gbc_AMScroll);
		JScrollPane PMScroll = new JScrollPane();
		PMScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		PMScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_PMScroll = new GridBagConstraints();
		gbc_PMScroll.gridheight = 2;
		gbc_PMScroll.gridwidth = 2;
		gbc_PMScroll.fill = GridBagConstraints.BOTH;
		gbc_PMScroll.insets = new Insets(0, 0, 5, 5);
		gbc_PMScroll.gridx = 0;
		gbc_PMScroll.gridy = 5;
		contentPane.add(PMScroll, gbc_PMScroll);
		DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
		int dayNum = dayOfWeek.getValue() - 1;
		AMlist = new ArrayList<>();
		PMlist = new ArrayList<>();
		for (Medicine med : myLocalList) {
			if (med.isDaily() || med.getSchedule()[dayNum]) {
				if (med.getAmount()[0] > 0)
					AMlist.add(med);
				if (med.getAmount()[1] > 0)
					PMlist.add(med);
			}
		}

		JTextArea AMText = new JTextArea();
		AMScroll.setViewportView(AMText);
		AMText.setText("AM: N/A");
		String am = new String("AM: ");
		if (!AMlist.isEmpty()) {
			for (Medicine med : AMlist) {
				am += "\n" + med.getName();
			}
			AMText.setText(am);
		}

		JTextArea PMText = new JTextArea();
		PMScroll.setViewportView(PMText);

		PMText.setWrapStyleWord(true);
		PMText.setText("PM: N/A");
		if (!PMlist.isEmpty()) {
			String pm = new String("PM: ");
			for (Medicine med : PMlist) {
				pm += "\n" + med.getName();
			}
			PMText.setText(pm);
		}
		
		AMText.setForeground(Color.BLACK);
		AMText.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		AMText.setEditable(false);
		AMText.setBackground(new Color(176, 196, 222));
		AMText.setBounds(10, 92, 150, 60);
		PMText.setEditable(false);
		PMText.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		PMText.setForeground(Color.BLACK);
		PMText.setBackground(new Color(176, 196, 222));
		PMText.setBounds(10, 163, 150, 60);
	}

	public static void writeToFile(ArrayList<Medicine> medList) {
		try {
			FileWriter writer  = new FileWriter(new File("medicine.txt"), false); 
			{
				for (Medicine med: medList) {
					writer.write("\n" + med.getName() + "\n");
					writer.write(Math.max(med.getAmount()[0], med.getAmount()[1]) + "\n"); //AM / PM
					writer.write((med.getAmount()[0] != 0) + "\n" + (med.getAmount()[1] != 0) + "\n");
					writer.write(med.getNotes() + "\n");
					writer.write(med.isDaily() + "\n");
					for (boolean bool : med.getSchedule())
					{
						writer.write(bool + " ");
					}
				}
			}
		writer.close();	
		}
		catch(Exception err) {
			JOptionPane.showMessageDialog(null, "Some issue with saving! " + err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);;
			
		}
	}

	
	
	private void saveStreak() {
		try {
			FileWriter writer = new FileWriter(new File("streak.txt"), false);
			{
				writer.write(AMlist.isEmpty() && PMlist.isEmpty()? "true " : "false "); //if there are no medicines to take, if the user later adds medicines for that day, they can still increase streak. 
				writer.write(cal.get(Calendar.DAY_OF_YEAR) + " " + cal.get(Calendar.YEAR) + " " + streak);
			}
			writer.close();
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, "Some issue with saving streak: " + err.getMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
			;
		}
	}

	private void loadStreak() {
		lastDate = 0;
		try {
			File fileName = new File("streak.txt");
			Scanner infile = new Scanner(fileName).useDelimiter(" ");
			if(!fileName.exists()){
				fileName.createNewFile();
			} else
			{
				wasEmpty = Boolean.parseBoolean(infile.next());
				lastDate = infile.nextInt();
				lastYear = infile.nextInt();
				// if more than a day has passed (lastYear = cal.get(Calendar.YEAR) && lastDate
				if ((lastYear == cal.get(Calendar.YEAR) && Math.abs(cal.get(Calendar.DAY_OF_YEAR) - lastDate) > 1) || // if sane year, diff b/w days >1
						(lastDate >= 365 && (cal.get(Calendar.DAY_OF_YEAR) != 366 || cal.get(Calendar.DAY_OF_YEAR) != 1)) // or it was new years eve, didn't log ny day
						|| lastYear != cal.get(Calendar.YEAR)) // or if different year
				{
					streak = 0;
				} else {
					streak = infile.nextInt();
					}
			}

			infile.close();
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}

	}

	public void loadFile() {
		try {
			File fileName = new File("medicine.txt");
			Scanner infile = new Scanner(fileName).useDelimiter("\n");
			if (!fileName.exists()) {
				fileName.createNewFile();
			} else {
				myLocalList.clear();
				while (infile.hasNextLine()) {
					String name = infile.next();
					int amount = infile.nextInt();
					boolean am = Boolean.parseBoolean(infile.next());
					boolean pm = Boolean.parseBoolean(infile.next());
					String notes = infile.next();
					boolean daily = infile.nextBoolean();
					String[] str = new String[7];
					str = infile.next().split(" ");
					boolean[] schedule = new boolean[7];
					for (int i = 0; i < 7; i++) {
						schedule[i] = Boolean.parseBoolean(str[i]);
					}
					Medicine med = new Medicine(name, amount, am, pm, schedule, daily, notes);
					myLocalList.add(med);
				}
				infile.close();
			}
		} catch (Exception err) {
			File fileName = new File("medicine.txt");
			try {
				fileName.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}
