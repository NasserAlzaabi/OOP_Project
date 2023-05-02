package OOP_Project;

import java.util.ArrayList;

public class Request {
	public Request(String name, String iD, String date, ArrayList<Item> items) {
		Name = name;
		ID = iD;
		Date = date;
		this.items = items;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	String Name, ID, Date;
	ArrayList<Item> items = new ArrayList<Item>();

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
	
