package OOP_Project;

class Student extends User{
	
	String phone_Number;

	public Student(String n, String u, String p, String phone_Number) { //calls on user constructor and adds phone num to it
		super(n, u, p);
		this.phone_Number = phone_Number;
	}

}
