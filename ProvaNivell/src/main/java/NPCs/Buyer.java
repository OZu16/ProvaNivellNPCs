package NPCs;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import provaNivell.Item;

public class Buyer {

	private static Scanner sc = new Scanner(System.in);
	
	private String nameBuyer;
	private String city;
	private double coins;
	private int id;
	private static int nextId = 0;
	private List<Item> purchasedItems = new ArrayList<Item>();
	
	
	
	public Buyer(String nameBuyer, String city, double purse) {
		this.nameBuyer = nameBuyer;
		this.city = city;
		this.coins = purse;
		this.id = nextId++;
	}



	public String getNameBuyer() {
		return nameBuyer;
	}


	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}

	

	public double getCoins() {
		return coins;
	}


	public void setCoins(double coins) {
		this.coins = coins;
	}

	public int getId() {
		return id;
	}


	public List<Item> getPurchasedItems() {
		return purchasedItems;
	}

	
	public void buyItem(NPC seller) {
		
		int useItem;
		
		seller.showInventory();
		
		useItem = sc.nextInt();
		
		Item itemSelected = seller.getInventory().stream()
				.filter(i -> i.getId() == useItem)
				.findFirst()
				.get();
		
		
		if(seller instanceof Merchant) {
			purchasedItems.add(itemSelected);
			seller.setCoins(seller.getCoins() + itemSelected.getPrice() + ((((Merchant) seller).getTAX() / 100.0) * itemSelected.getPrice()));
			setCoins(this.coins - itemSelected.getPrice());
			System.out.println(itemSelected.getName() + " comprado a mercader: " + seller.getName() + "!\n");
			seller.getInventory().remove(itemSelected);
			

		}else if(seller instanceof Peasant) {
			purchasedItems.add(itemSelected);
			seller.setCoins(seller.getCoins() + itemSelected.getPrice() + ((((Peasant) seller).getTAX() / 100.0) * itemSelected.getPrice()));
			setCoins(this.coins - itemSelected.getPrice());
			System.out.println(itemSelected.getName() + " comprado a campesino: " + seller.getName() + "!\n");
			seller.getInventory().remove(itemSelected);
			

		}else {
			purchasedItems.add(itemSelected);
			seller.setCoins(seller.getCoins() + itemSelected.getPrice());
			setCoins(this.coins - itemSelected.getPrice());
			System.out.println(itemSelected.getName() + " comprado a ladrón: " + seller.getName() + "!\n");
			seller.getInventory().remove(itemSelected);
			

		}
		
	}

	public void showInventory() {

		purchasedItems.forEach(item -> System.out.println((item.toString())));
	}
	
	@Override
	public String toString() {
		return "Peasant [id =" + getId() + ", Name=" + getNameBuyer() + "]";
	}
}
