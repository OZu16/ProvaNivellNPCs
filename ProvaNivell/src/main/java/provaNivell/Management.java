package provaNivell;

import NPCs.NPC;
import NPCs.Peasant;
import NPCs.Merchant;
import NPCs.Thief;
import NPCs.Buyer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Management {

	private static Management instance = null;
	private static Scanner sc = new Scanner(System.in);


	private Management() {
	}


	public static Management getInstance() {
		if(instance == null) {
			instance = new Management();
		}
		return instance;
	}



	public int menu() {

		int scMenu;

		System.out.println( "App NPCs!\n"
				+ "1 - Crear un vendedor\r\n"
				+ "2 - Añadir un item al inventario de un vendedor.*\r\n"
				+ "3 - Mostrar el ítem más barato de todos los vendedores de una ciudad**\r\n"
				+ "4 - Consultar los ítems de un vendedor.**\r\n"
				+ "5 - Crear un comprador\r\n"
				+ "6 - Realizar la compra de un ítem.\r\n"
				+ "7 - Consultar los ítems de un Comprador.**\r\n"
				+ "8 - Consultar todos los vendedores que hay en una ciudad.**\r\n"
				+ "9 - Consultar todos los compradores que hay en una ciudad.**\r\n"
				+ "10 - Mostrar todos los ítems de un determinado tipo ordenados por precio (asc). **" );

		
		scMenu = sc.nextInt();

		return scMenu;
	}
	
	

	public void insertCities (List<City> cityList) {

		cityList.add(new City("Lumbridge"));
		cityList.add(new City("Varrock"));		

	}

	public void insertItems (List<Item> itemList) {

		itemList.add(new Item("Cooked meat", "food", 9.99, 0));
		itemList.add(new Item("Fishing rod", "tool", 15.66, 0));		
		itemList.add(new Item("Iron pickaxe", "tool", 15.33, 0));		
		itemList.add(new Item("Rune pickaxe", "tool", 149.15, 78));		
		itemList.add(new Item("Iron", "ore", 10.11, 0));		
		itemList.add(new Item("Rune Sword", "weapon", 222.22, 0));		
		itemList.add(new Item("Bow", "weapon", 12.33, 0));		
		itemList.add(new Item("Shears", "tool", 5.45, 0));		
		itemList.add(new Item("Staff of air", "weapon", 32.12, 0));		
		itemList.add(new Item("Blue Robes", "armour", 41.23, 0));
		itemList.add(new Item("Adamant helm", "armour", 80.24, 0));
		itemList.add(new Item("Bread", "food", 2.10, 0));
		itemList.add(new Item("Swordfish", "food", 30.50, 0));
		itemList.add(new Item("Leather gloves", "armour", 13.24, 99));
	}
	
	public void insertSellers (List<City> cityList) {

		cityList.get(0).getSellerList().add(new Thief("Pepe", "Lumbridge", 993.32));
		cityList.get(0).getSellerList().add(new Merchant("Paca", "Lumbridge", 1001.55));		
		cityList.get(1).getSellerList().add(new Peasant("Antonia", "Varrock", 32.9));
		cityList.get(1).getSellerList().add(new Thief("Oscar", "Varrock", 146.33));
	}
	
	public void insertBuyers (List<City> cityList) {

		cityList.get(0).getBuyerList().add(new Buyer("Raul", "Lumbridge", 8052.32));
		cityList.get(1).getBuyerList().add(new Buyer("Gerard", "Varrock", 111.12));
	}
	
	public void insertItemsOnSellers (List<City> cityList, List<Item> itemList) {
		
		cityList.get(0).getSellerList().get(0).getInventory().add(itemList.get(0));
		cityList.get(0).getSellerList().get(0).getInventory().add(itemList.get(4));
		cityList.get(0).getSellerList().get(1).getInventory().add(itemList.get(5));
		cityList.get(0).getSellerList().get(1).getInventory().add(itemList.get(6));
		cityList.get(1).getSellerList().get(0).getInventory().add(itemList.get(11));
		cityList.get(1).getSellerList().get(0).getInventory().add(itemList.get(1));
		cityList.get(1).getSellerList().get(1).getInventory().add(itemList.get(12));
		cityList.get(1).getSellerList().get(1).getInventory().add(itemList.get(9));
	}
	
	public City selectCity(List<City> cityList) throws IndexOutOfBoundsException, NoSuchElementException{

		int useCity;

		System.out.println("\nIntroduce el id de la ciudad:");
		cityList.forEach(city -> System.out.println((city.getId() + "- " + city.getName())));
		useCity = sc.nextInt();


		return cityList.get(useCity);
	}


	
	public NPC selectSeller(City city) throws NoSuchElementException{
		
		int useSeller;

		System.out.println("\nIntroduce el id del vendedor:");
		
		city.getSellerList().forEach(seller -> System.out.println((seller.getId() + "- " + seller.getName())));
		
		useSeller = sc.nextInt();
		
		NPC sellerSelected = city.getSellerList().stream()
				.filter(s -> s.getId() == useSeller)
				.findFirst()
				.get();
		
		return sellerSelected;
	}
	

	public Buyer selectBuyer(City city){
		
		int useBuyer;

		System.out.println("\nIntroduce el id del comprador:");
		
		city.getBuyerList().forEach(buyer -> System.out.println((buyer.getId() + "- " + buyer.getNameBuyer())));
		
		useBuyer = sc.nextInt();
		
		Buyer buyerSelected = city.getBuyerList().stream()
				.filter(s -> s.getId() == useBuyer)
				.findFirst()
				.get();
		
		return buyerSelected;
	}
	
	public void itemByTypeList(List<Item> itemList) {

		int indexMenu;

		System.out.println("Filtrar objetos por:"
				+ "\n1- Herramientas"
				+ "\n2- Comidas"
				+ "\n3- Armaduras"
				+ "\n4- Armas");
		
		indexMenu = sc.nextInt();

		switch(indexMenu) {

		case 1:
			List<Item> toolItems = itemList.stream()
			.filter(a -> a.getType().equals("tool"))
			.collect(Collectors.toList());

			Collections.sort(toolItems, (o1, o2) ->
			Double.compare(o2.getPrice(),o1.getPrice()));

			toolItems.forEach(item -> System.out.println(item.toString()));
			
			break;
		case 2:

			List<Item> foodItems = itemList.stream()
			.filter(a -> a.getType().equals("food"))
			.collect(Collectors.toList());

			Collections.sort(foodItems, (o1, o2) ->
			Double.compare(o2.getPrice(),o1.getPrice()));

			foodItems.forEach(item -> System.out.println(item.toString()));

			break;
		case 3:
			
			List<Item> armourItems = itemList.stream()
			.filter(a -> a.getType().equals("armour"))
			.collect(Collectors.toList());

			Collections.sort(armourItems, (o1, o2) ->
			Double.compare(o2.getPrice(),o1.getPrice()));

			armourItems.forEach(item -> System.out.println(item.toString()));
			
			break;
		case 4:


			List<Item> weaponItems = itemList.stream()
			.filter(a -> a.getType().equals("weapon"))
			.collect(Collectors.toList());

			Collections.sort(weaponItems, (o1, o2) ->
			Double.compare(o2.getPrice(),o1.getPrice()));

			weaponItems.forEach(item -> System.out.println(item.toString()));
			break;
		default:
			System.out.println("selecciona un numero entre 1 y 4");
		}
	}			
}
