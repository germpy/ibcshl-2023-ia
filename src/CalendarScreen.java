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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class CalendarScreen extends JFrame {

	private JPanel contentPane;
	private ArrayList<Medicine> myLocalList;
	private ArrayList<Medicine> myModelList;
	private JTable table;
	private DefaultTableModel model;
	private String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};;

	public CalendarScreen(ArrayList<Medicine> list) {

		myLocalList = list; 
		myModelList = new ArrayList<Medicine>();
		setText(list);
		setTable();

	}
	
	public void setText(ArrayList<Medicine> list) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 383);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{68, 62, 98, 123};
		gbl_contentPane.rowHeights = new int[]{21, 20, 28, 0, 0, 30, 30, 24, 30, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel TitleText = new JLabel("Calendar");
		TitleText.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		GridBagConstraints gbc_TitleText = new GridBagConstraints();
		gbc_TitleText.gridwidth = 2;
		gbc_TitleText.fill = GridBagConstraints.VERTICAL;
		gbc_TitleText.insets = new Insets(0, 0, 5, 5);
		gbc_TitleText.gridx = 1;
		gbc_TitleText.gridy = 0;
		contentPane.add(TitleText, gbc_TitleText);
		
		JButton HomeButton = new JButton("HOME");
		HomeButton.setBorder(null);
		HomeButton.setBackground(new Color(176, 196, 222));
		HomeButton.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		GridBagConstraints gbc_HomeButton = new GridBagConstraints();
		gbc_HomeButton.insets = new Insets(0, 0, 0, 5);
		gbc_HomeButton.fill = GridBagConstraints.BOTH;
		gbc_HomeButton.gridx = 0;
		gbc_HomeButton.gridy = 8;
		contentPane.add(HomeButton, gbc_HomeButton);
		HomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeScreen window = new HomeScreen(list);
				window.setVisible(true);
				setVisible(false);
			}	
		});
	}
	
	public void setTable() {
		
		model = new DefaultTableModel(new String[]{ "Day", "Name", "Amount", "AM / PM", "Notes"}, 0);
		//model.addRow(new String[]{ "Day", "Name", "Amount", "AM / PM", "Notes"});		
		table = new JTable(model);
		table.setEnabled(false);
		table.setAutoCreateColumnsFromModel(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 7;
		gbc_table.gridwidth = 4;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		contentPane.add(table, gbc_table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(true);
		scrollPane.setViewportView(table);
		scrollPane.setWheelScrollingEnabled(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0,0,5,5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane,gbc_scrollPane);
		
		ArrayList<Medicine> tempList = new ArrayList<Medicine>();
		for (int i = 0; i < myLocalList.size(); i++)
		{
			Medicine temp = myLocalList.get(i);
			tempList.addAll(temp.split());
		}
		myLocalList = tempList;
		for (Medicine med: myLocalList)
		{
			try {
				myModelList.add(indexNum(med), med);
				model.insertRow(indexNum(med), new String[]{ 
						days[med.firstDayTaken()],
						med.getName(),
						String.valueOf(Math.max(med.getAmount()[0], med.getAmount()[1])),
						(med.getAmount()[0] != 0 && med.getAmount()[1] != 0) ? "AM & PM" : (med.getAmount()[0] != 0)? "AM" : "PM", 
						med.getNotes()});
			} catch (Exception e)
			{
				System.out.println(med.getName() + " " + e.getMessage());
			}
		}
	}
	
	public int indexNum(Medicine med) {
		int i = 0;
		
		try {
			while (i < myModelList.size() && med.compareTo(myModelList.get(i)) < 0) {
				i++;
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return i;
	}
	
	public String getColumnName(int column) {
        return new String[]{ "Day", "Name", "Amount", "AM / PM", "Notes"}[column];
    }

}
