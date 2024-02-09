import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<OrderItem> items;
	private String cardNumber;
	private List<Double> prices;
	private double totalPrice;
	
	public Order(String inputFile) throws IOException {
		items = new ArrayList<OrderItem>();
		loadOrder(inputFile);
	}
	
	private void loadOrder(String inputFile) throws IOException {
		File file = new File(inputFile);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String row = "";
		String[] info;	// [0] -> Item, [1] -> Quantity, [2] -> CardNumber(first row only)
		
		br.readLine();	// skip header row
		
		// first row is different, it has item, quantity, and card number
		if ((row = br.readLine()) != null) {
			info = row.split(",");
			OrderItem orderItem = new OrderItem(info[0], Integer.valueOf(info[1]));
			items.add(orderItem);
			setCardNumber(info[2]);
		}
		
		while ((row = br.readLine()) != null) {
			info = row.split(",");
			OrderItem orderItem = new OrderItem(info[0], Integer.valueOf(info[1]));
			items.add(orderItem);
		}
		br.close();
	}
	
	public List<OrderItem> getItems() {
		return this.items;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardNumber() {
		return this.cardNumber;
	}
	
	public void setPrices(List<Double> prices) {
		this.prices = prices;
	}
	
	public List<Double> getPrices() {
		return this.prices;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}
}