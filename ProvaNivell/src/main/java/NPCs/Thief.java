package NPCs;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import provaNivell.Item;

public class Thief extends NPC {
	
	private static Scanner sc = new Scanner(System.in);
	
	private final int MAXITEMS = 3;
	private final int ADDWEAR = 25;

	public Thief(String name, String city, double coins) {
		super(name, city, coins);
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
			inventory.get(inventory.size() -1).setWear(inventory.get(inventory.size() -1).getWear() + ADDWEAR);

			if(inventory.get(inventory.size() -1).getWear() >= 100) {
				inventory.remove(inventory.size() -1);
				System.out.println("Item roto por desgaste!\n");
			}else {
				System.out.println("Item añadido al inventario!\n");
			}
		}
	}
	
	public Item showLowerPriceItem() {

		Item lowerPriceItem = inventory.stream()
	            .min(Comparator.comparing(Item::getPrice))
	            .orElse(null);

		return lowerPriceItem;
	}
	
	public void showInventory() {
		
		System.out.println(getName() + " Inventory:");
		inventory.forEach(item -> System.out.println((item.toString())));
	}
	
	@Override
	public String toString() {
		return "Peasant [id =" + getId() + ", Name=" + getName() + "]";
	}
}
