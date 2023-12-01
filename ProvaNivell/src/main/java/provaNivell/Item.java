package provaNivell;

import java.util.Objects;

public class Item {
	
	private String name;
	private String type;
	private double price;
	private int wear;
	private int id;
	private static int nextId = 0;
	
	
	public Item(String name, String type, double price, int wear) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.wear = wear;
		this.id = nextId++;
	}


	public String getName() {
		return name;
	}



	public String getType() {
		return type;
	}



	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getWear() {
		return wear;
	}


	public void setWear(int wear) {
		this.wear = wear;
	}

	public int getId() {
		return id;
	}

	



	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", wear=" + wear + "]";
	}
	
	
	
}
