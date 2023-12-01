package provaNivell;

import NPCs.NPC;
import NPCs.Peasant;
import NPCs.Merchant;
import NPCs.Thief;
import NPCs.Buyer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class City {
	
	private static Scanner sc = new Scanner(System.in);
	
	private String name;
	private int id;
	private static int nextId = 0;
	List<NPC> sellerList = new ArrayList<NPC>();
	List<Buyer> buyerList = new ArrayList<Buyer>();
	
	
	public City(String name) {
		this.id = nextId++;
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<NPC> getSellerList() {
		return sellerList;
	}


	public List<Buyer> getBuyerList() {
		return buyerList;
	}
	
	public int getId() {
		return id;
	}
	
	
	
	public void createSeller() throws NoSuchElementException{
		
		String nameSeller;
		int type;
		double coins;
		
		System.out.println("Creación vendedor:"
				+ "\nNombre:" );
		nameSeller = sc.nextLine();
		System.out.println("Tipo:"
				+ "\n1- Campesino"
				+ "\n2- Mercader"
				+ "\n3- Ladrón");
		type = sc.nextInt();
		sc.nextLine();
		
		
		switch(type) {
		case 1:
			System.out.println("coins:");
			coins = sc.nextDouble();
			sellerList.add(new Peasant(nameSeller, this.name, coins));
			System.out.println("Campesino creado!\n");
			break;
		case 2:
			System.out.println("coins:");
			coins = sc.nextDouble();
			sellerList.add(new Merchant(nameSeller, this.name, coins));
			System.out.println("Mercader creado!\n");
			break;
		case 3:
			System.out.println("coins:");
			coins = sc.nextDouble();
			sellerList.add(new Thief(nameSeller, this.name, coins));
			System.out.println("Ladron creado!\n");
			break;
		default:
			System.out.println("error! selecciona entre 1 y 3(campesino, mrecader o ladrón)");
		};
		
	}
	
	
	public void createBuyer() {

		String nameBuyer;
		double coins;

		System.out.println("Creación comprador:"
				+ "\nNombre:" );
		nameBuyer = sc.nextLine();
		System.out.println("Coins:");
		coins = sc.nextDouble();


		buyerList.add(new Buyer(nameBuyer, this.name, coins));
		System.out.println("Comprador creado!\n");


	}
	
	public Item cityLowerPriceItem() {
		
		List<Item> lowerPriceItemList = new ArrayList<Item>();
		
		getSellerList().forEach(seller -> lowerPriceItemList.add(seller.showLowerPriceItem()));
		
		Item lowerPriceItem = lowerPriceItemList.stream()
	            .min(Comparator.comparing(Item::getPrice))
	            .orElse(null);

		return lowerPriceItem;
	}
	
	public void showBuyers() {
		
		buyerList.forEach(buyer -> System.out.println((buyer.toString())));
		
	}
	
	public void showSellers() {
		
		sellerList.forEach(seller -> System.out.println((seller.toString())));
		
	}

}
