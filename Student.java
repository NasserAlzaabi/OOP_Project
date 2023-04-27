package OOP_Project;

class Student extends User{
	
	String phone_Number, itemHeld;

	public Student(String id, String n, String p, String phone_Number, String itemHeld) { //calls on user constructor and adds phone num to it
		super(id, n, p);
		this.phone_Number = phone_Number;
		this.itemHeld = itemHeld;
	}
}
