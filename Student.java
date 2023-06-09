package OOP_Project;

import java.util.ArrayList;

class Student extends User{
	
	String phone_Number;
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	boolean isApproved = false;
	ArrayList<Item> items = new ArrayList<Item>();
	boolean request = false; // new variable

	public Student(String id, String n, String p, String phone_Number) { //calls on user constructor and adds phone num to it
		super(id, n, p);
		this.phone_Number = phone_Number;
	}

	public Student(String id, String n, String p, String phone_Number, ArrayList<Item> BorrowedItems) { //for when u have items to add
		super(id, n, p);
		this.phone_Number = phone_Number;
		this.items = BorrowedItems;
	}

	public Student(Student c){
		super(c);
		this.phone_Number = c.phone_Number;
		this.items = c.items;
	}

	public String getPhone_Number() {
		return phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}

	public boolean isRequest() { //new
		return request;
	}

	public void setRequest(boolean request) { //new
		this.request = request;
	}

	public Student(){
		super();
		this.phone_Number = "";
	}

	public String getID(){return super.user_id;}
	public String getName(){return super.name;}
	public String getPhoneNumber(){return phone_Number;}


	public void addItem(Item item){ //new method 
		items.add(item);
	}

	public void removeItem(Item item){ //new method
		items.remove(item); 
	}

	public void setItems(ArrayList<Item> newItems){ //new method
		this.items = newItems;
	}

	public ArrayList<Item> getItems(){
		return this.items;
	}

	// public String sendItemRequest(){
	// 	
	// }
}
