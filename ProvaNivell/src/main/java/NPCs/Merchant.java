package NPCs;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import provaNivell.Item;

public class Merchant extends NPC {

	private static Scanner sc = new Scanner(System.in);
	
	private final int MAXITEMS = 7;
	private final double TAX = 4;

	public Merchant(String name, String city, double coins) {
		super(name, city, coins);
	}
	
	public double getTAX() {
		return TAX;
	}
	
	public void addItem(List<Item> itemList) throws FullInventoryException {

		int useItem;

		System.out.println("Selecciona el id del item que quieres añadir:");
		itemList.forEach(item -> System.out.println((item.toString())));

		useItem = sc.nextInt();

		if(inventory.size() == MAXITEMS) {
			throw new FullInventoryException();
		}else {
			inventory.add(itemList.get(useItem));
			System.out.println("Item añadido al inventario!\n");
		}

	}
	
	public Item showLowerPriceItem() {

		Item lowerPriceItem = inventory.stream()
	            .min(Comparator.comparing(Item::getPrice))
	            .orElse(null);

		return lowerPriceItem;
	}
	
	public void showInventory() {
		
		inventory.forEach(item -> System.out.println((item.toString())));
	}
	
	@Override
	public String toString() {
		return "Peasant [id =" + getId() + ", Name=" + getName() + "]";
	}

}
