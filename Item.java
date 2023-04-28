package OOP_Project;

public class Item {
	
	protected String name, model;
	protected int value, quantity;
	boolean Consumable;
	
	public Item(String n, String m, int v, int q, boolean c) {
		this.name = n;
		this.model = m;
		this.value = v;
		this.quantity = q;
		this.Consumable = c;
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
