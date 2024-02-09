
public class Item {
	private String name;
	private String category;
	private int quantity;
	private double price;
	
	public Item(String name, String category, int quantity, double price) {
		this.name = name;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
	}
	
	public void decreaseQuantity(int amount) {
		this.quantity = this.quantity - amount;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
