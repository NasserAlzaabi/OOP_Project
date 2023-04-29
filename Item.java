package OOP_Project;

public class Item {
	
	protected String name, model, date;
	protected int value, quantity; //date :ddmmyy:
	boolean consumable;
	
	public Item(String n, String m, int v, int q, boolean c, String d) {
		this.name = n;
		this.model = m;
		this.value = v;
		this.quantity = q;
		this.consumable = c;

		if (c)this.date = ""; //consumable no date "y"

		else this.date = d; // "n"
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
