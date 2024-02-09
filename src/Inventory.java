import java.util.Map;
import java.util.HashMap;

public class Inventory {
	private static Inventory instance;
	private Map<String, Item> inventory;
	private int maxEssentials;
	private int maxLuxury;
	private int maxMisc;
	
	private Inventory() {
		inventory = new HashMap<String, Item>();
		loadInventory();
		maxEssentials = 12;
		maxLuxury = 7;
		maxMisc = 10;
	}
	
	public static synchronized Inventory getInstance() {
		if (instance == null) {
			instance = new Inventory();
		}
		return instance;
	}
	
	public Item getItem(String itemName) {
		return inventory.get(itemName.toLowerCase());
	}
	
	private void loadInventory() {
		Item clothes = new Item("clothes", "Essentials", 100, 20);
		inventory.put(clothes.getName(), clothes);
		
		Item soap = new Item("soap", "Essentials", 200, 5);
		inventory.put(soap.getName(), soap);
		
		Item shampoo = new Item("shampoo", "Essentials", 200, 10);
		inventory.put(shampoo.getName(), shampoo);
		
		Item milk = new Item("milk", "Essentials", 100, 5);
		inventory.put(milk.getName(), milk);
		
		Item perfume = new Item("perfume", "Luxury", 50, 50);
		inventory.put(perfume.getName(), perfume);
		
		Item chocolates = new Item("chocolates", "Luxury", 300, 3);
		inventory.put(chocolates.getName(), chocolates);
		
		Item handbag = new Item("handbag", "Luxury", 75, 150);
		inventory.put(handbag.getName(), handbag);
		
		Item wallet = new Item("wallet", "Luxury", 100, 100);
		inventory.put(wallet.getName(), wallet);
		
		Item bedsheet = new Item("bedsheet", "Misc", 150, 75);
		inventory.put(bedsheet.getName(), bedsheet);
		
		Item footwear = new Item("footware", "Misc", 200, 25);
		inventory.put(footwear.getName(), footwear);
		
		Item homeDecorPiece = new Item("homedecorpiece", "Misc", 100, 40);
		inventory.put(homeDecorPiece.getName(), homeDecorPiece);
		
		Item pen = new Item("pen", "Misc", 400, 3);
		inventory.put(pen.getName(), pen);
		
		Item pencil = new Item("pencil", "Misc", 400, 3);
		inventory.put(pencil.getName(), pencil);
	}
	
	public int getMaxEssentials() {
		return this.maxEssentials;
	}
	
	public int getMaxLuxury() {
		return this.maxLuxury;
	}
	
	public int getMaxMisc() {
		return this.maxMisc;
	}
}
