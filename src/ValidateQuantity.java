import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class ValidateQuantity implements OrderProcessor {
	private OrderProcessor successor = null;
	
	public void processOrder(Order order) {
		boolean validQuantity = true;
		Inventory inventory = Inventory.getInstance();
		List<String> incorrectQuantities = new ArrayList<String>();
		
		for (OrderItem item : order.getItems()) {
			String itemName = item.getName();
			Integer requestedQuantity = Integer.valueOf(item.getQuantity());
			
			if (requestedQuantity > inventory.getItem(itemName).getQuantity()) {
				incorrectQuantities.add(itemName);
				validQuantity = false;
			}
			inventory.getItem(itemName).decreaseQuantity(requestedQuantity);
		}
		
		if (validQuantity) {
			System.out.println("Order did not exceed the available quantity in the inventory.");
			if (successor != null) {
				successor.processOrder(order);
			}
		} else {
			try {
				FileWriter outputFile = new FileWriter("../output/output.txt");
				outputFile.write("This order exceeded the available quantity in the inventory.\n");
				outputFile.write("Please correct quantities for: " + String.join(", ", incorrectQuantities));
				outputFile.close();
				System.out.println("Order exceeded inventory quantity. Please look at output.txt file.");
			} catch (IOException e) {
				System.out.println("Something went wrong while generating the output.txt file. Please check the output file path in ValidateQuantity.java on Line 32. The error was:");
				e.printStackTrace();
			}
		}
	}

	public void setSuccessor(OrderProcessor successor) {
		this.successor = successor;
	}
}
