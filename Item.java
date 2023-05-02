package OOP_Project;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Item {
	
	protected String name, model, date;
	protected int value, quantity, borrowedQuantity = 0; //date :ddmmyy:
	boolean consumable;

	//sets current date in dd-mm-yyyy format
	private LocalDate currentDateObj = LocalDate.now();
	protected DateTimeFormatter currentDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	protected String currentDate = currentDateObj.format(currentDateFormat);

	public Item(String n, String m, int v, int q, boolean c, String d) {
		this.name = n;
		this.model = m;
		this.value = v;
		this.quantity = q;
		this.consumable = c;

		if (c)this.date = "0"; //consumable no date "y"

		else this.date = d; // "n"
	}

	public int getBorrowedQuantity() {
		return borrowedQuantity;
	}

	public void setBorrowedQuantity(int borrowedQuantity) {
		this.borrowedQuantity = borrowedQuantity;
	}

	public Item(){
		this.name = "";
		this.model = "";
		this.value = 0;
		this.quantity = 0;
		this.consumable = false;
		this.date = "";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrentDate(){ //get current date
		return currentDate;
	}

	public boolean isConsumable() {
		return consumable;
	}

	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}

	public boolean getConsumable(){ 
		return consumable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
