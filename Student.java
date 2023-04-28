package OOP_Project;

import java.util.ArrayList;

class Student extends User{
	
	String phone_Number;
	Item itemHeld;
	ArrayList<Item> items = new ArrayList<Item>();

	public Student(String id, String n, String p, String phone_Number) { //calls on user constructor and adds phone num to it
		super(id, n, p);
		this.phone_Number = phone_Number;
	}

	public Student(String id, String n, String p, String phone_Number, ArrayList<Item> BorrowedItems) { //for when u have items to add
		super(id, n, p);
		this.phone_Number = phone_Number;
		this.items = BorrowedItems;
	}
}
