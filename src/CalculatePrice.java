import java.util.ArrayList;
import java.util.List;

public class CalculatePrice implements OrderProcessor {
	private OrderProcessor successor = null;
	
	public void processOrder(Order order) {
		Inventory inventory = Inventory.getInstance();
		List<Double> prices = new ArrayList<Double>();
		double total = 0.0;
		
		for (OrderItem item : order.getItems()) {
			double price = item.getQuantity() * inventory.getItem(item.getName()).getPrice();
			prices.add(price);
			total += price;
		}

		order.setPrices(new ArrayList<Double>(prices));
		order.setTotalPrice(total);
		
		System.out.println("Total Price: " + total);
		if (successor != null) {
			successor.processOrder(order);
		}
	}

	public void setSuccessor(OrderProcessor successor) {
		this.successor = successor;
	}
}
