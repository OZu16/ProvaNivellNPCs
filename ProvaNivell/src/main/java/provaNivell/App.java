package provaNivell;

import NPCs.NPC;
import NPCs.Peasant;
import NPCs.Merchant;
import NPCs.Thief;
import NPCs.Buyer;
import NPCs.FullInventoryException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

public class App {
	

	public static void main( String[] args ) {

		List<City> cityList = new ArrayList<City>();
		List<Item> itemList = new ArrayList<Item>();
		
		Management x = Management.getInstance();
		x.insertCities(cityList);
		x.insertItems(itemList);
		x.insertSellers(cityList);
		x.insertBuyers(cityList);
		x.insertItemsOnSellers(cityList, itemList);
		
		int indexMenu;
		do {
			switch(indexMenu = x.menu()) {
			case 1:
				try {
					x.selectCity(cityList).createSeller();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					x.selectSeller(x.selectCity(cityList)).addItem(itemList);
				} catch (FullInventoryException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					System.out.println(x.selectCity(cityList).cityLowerPriceItem().toString());
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				} 
				break;
			case 4:
				try {
					x.selectSeller(x.selectCity(cityList)).showInventory();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				x.selectCity(cityList).createBuyer();
				break;
			case 6:
				x.selectBuyer(x.selectCity(cityList)).buyItem(x.selectSeller(x.selectCity(cityList)));
				break;
			case 7:
				x.selectBuyer(x.selectCity(cityList)).showInventory();
				break;
			case 8:
				x.selectCity(cityList).showSellers();
				break;
			case 9:
				x.selectCity(cityList).showBuyers();
				break;
			case 10:
				x.itemByTypeList(itemList);
				break;
			default:
				break;
			};
			
		}while(indexMenu != 0);



	}
	

}
