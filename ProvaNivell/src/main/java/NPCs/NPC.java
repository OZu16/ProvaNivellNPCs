package NPCs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import provaNivell.Item;

public abstract class NPC {
	
	private String name;
	private String city;
	private double coins;
	private int id;
	private static int nextId = 0;
	protected List<Item> inventory;
	
	
	public NPC(String name, String city, double coins) {
		this.name = name;
		this.city = city;
		this.coins = coins;
		this.id = nextId++;
		this.inventory = new ArrayList<Item>();
	}


	public String getName() {
		return name;
	}


	public String getCity() {
		return city;
	}

	public int getId() {
		return id;
	}
	
	
	public double getCoins() {
		return coins;
	}


	public void setCoins(double coins) {
		this.coins = coins;
	}


	public List<Item> getInventory() {
		return inventory;
	}
	
	
	public abstract void addItem(List<Item> itemList) throws FullInventoryException;
	
	public abstract Item showLowerPriceItem();
	
	public abstract void showInventory();
	
	
	

}	
