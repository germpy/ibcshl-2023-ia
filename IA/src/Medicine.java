import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Medicine {
	private String myName;
	private ArrayList<Medicine> myLocalList;
	private Integer[] myAmount = new Integer[2]; //also am pm
	private String myNotes; 
	private boolean isDaily; //[0] is AM [1] is PM
	private boolean[] mySchedule = new boolean[7]; //[0] is Mon, [1] is Tues, etc, [6] is Sun
	
	public Medicine(String name, Integer amount, boolean am, boolean pm, boolean[] schedule, boolean daily, String notes)
	{
		myName = name;
		if (isInt(amount.toString()))
		{
			myAmount[0] = (am == true)? amount : 0;
			myAmount[1] = (pm == true)? amount : 0;
		} else
		{
			myAmount[0] = myAmount[1] = 0;
		}
		isDaily = daily;
		mySchedule = schedule;
		myNotes = notes;
	}
	
	public Medicine() //Default constructor, if no parameters are given
	{
		myName = "";
		myAmount[0] = myAmount[1] = 0;
		isDaily = false;
		myNotes = "";
		Arrays.fill(mySchedule, false);
	}
	
	
	// Get functions
	public String getName()
	{
		return myName;
	}
	
	public Integer[] getAmount() //[0] is AM [1] is PM
	{
		return myAmount;
	}
	
	public String getNotes()
	{
		return myNotes;
	}
	
	public boolean isDaily()
	{
		return isDaily;
	}
	
	public boolean[] getSchedule()
	{
		return mySchedule;
	}
	
	// Set functions
	public void setName(String name)
	{
		myName = name;
	}
	
	public void setAmount(Integer amount, boolean am, boolean pm)
	{
		myAmount[0] = (am == true)? amount : 0;
		myAmount[1] = (pm == true)? amount : 0;
	}
	
	public void setNotes(String notes)
	{
		myNotes = notes;
	}
	
	public void setDaily(boolean daily)
	{
		isDaily = daily;
	}
	
	public void setSchedule(boolean[] schedule)
	{
		mySchedule = schedule;
	}
	
	public void setSchedule(int day, boolean bool)
	{
		mySchedule[day] = bool;
	}
	
	
	//MISC Functions
	public ArrayList<Medicine> split() //Splits a Medicine object by day (so each copy will only be taken one day), and returns an ArrayList of these split Medicines.
	{
		ArrayList<Medicine> tempList = new ArrayList<Medicine>();
		for (int i = 0; i < 7; i++)
		{
			if (isDaily() || mySchedule[i])
			{
				Medicine temp = new Medicine(myName, Math.max(myAmount[0], myAmount[1]), (myAmount[0] > 0)? true : false, (myAmount[1] > 0)? true : false, new boolean[7], false, myNotes);
				temp.setSchedule(i, true);
				tempList.add(temp);
			}
		}
		if (tempList.isEmpty())
			tempList.add(this);
		return tempList;
	}
	
	public int firstDayTaken() //Used for split Medicine objects and Medicine that is only taken one day
	{
		int i = 0;
		while (i < 7 && !isDaily && !mySchedule[i])
			i++;
		return i;
	}
	
	
	public int compareTo(Medicine other) { //1 means obj is higher; -1 means other is higher
		for (int i = 0; i < 7; i++)
		{

			if (mySchedule[i] ^ other.getSchedule()[i])
			{
				return (mySchedule[i])? 1 : -1;
			}
		}
		for (int y = 0; y < 2; y++)
		{
			if (myAmount[y] > 0 ^ other.getAmount()[y] > 0)
			{
				return (myAmount[y] > 0)? 1 : -1;
			}
		}
		if (getName().compareTo(other.getName()) != 0)
		{
			return getName().compareTo(other.getName());
		}
		return getNotes().compareTo(other.getNotes());
	}
	

	
	//other
	public boolean isInt(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, "Incorrect Amount! Input", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
