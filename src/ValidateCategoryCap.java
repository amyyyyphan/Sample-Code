import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidateCategoryCap implements OrderProcessor{
	private OrderProcessor successor = null;
	
	public void processOrder(Order order) {
		Inventory inventory = Inventory.getInstance();
		
		int essentialItemsCount = 0;
		int luxuryItemsCount = 0;
		int miscItemsCount = 0;
		
		List<String> essentialItems = new ArrayList<String>();
		List<String> luxuryItems = new ArrayList<String>();
		List<String> miscItems = new ArrayList<String>();
		
		for (OrderItem item : order.getItems()) {
			String itemName = item.getName();
			String itemCategory = inventory.getItem(itemName).getCategory();
			
			if (itemCategory.equals("Essentials")) {
				essentialItemsCount += item.getQuantity();
				essentialItems.add(itemName);
			} else if (itemCategory.equals("Luxury")) {
				luxuryItemsCount += item.getQuantity();
				luxuryItems.add(itemName);
			} else if (itemCategory.equals("Misc")) {
				miscItemsCount += item.getQuantity();
				miscItems.add(itemName);
			}
		}

		if ((inventory.getMaxEssentials() < essentialItemsCount) || (inventory.getMaxLuxury() < luxuryItemsCount) || (inventory.getMaxMisc() < miscItemsCount)) {
			try {
				FileWriter outputFile = new FileWriter("../output/output.txt");
				if (inventory.getMaxEssentials() < essentialItemsCount) {
					outputFile.write("This order exceeded the maximum number of essential items allowed.\n");
					outputFile.write("Please correct quantities for: " + String.join(", ", essentialItems) + "\n\n");
				}
				if (inventory.getMaxLuxury() < luxuryItemsCount) {
					outputFile.write("This order exceeded the maximum number of luxury items allowed.\n");
					outputFile.write("Please correct quantities for: " + String.join(", ", luxuryItems) + "\n\n");
				}
				if (inventory.getMaxMisc() < miscItemsCount) {
					outputFile.write("This order exceeded the maximum number of misc items allowed.\n");
					outputFile.write("Please correct quantities for: " + String.join(", ", miscItems) + "\n\n");
				}
				outputFile.close();
				System.out.println("Order exceeded category cap. Please look at output.txt file.");
			} catch (IOException e) {
				System.out.println("Something went wrong while generating the output.txt file. Please check the output file path in ValidateCategoryCap.java on Line 38. The error was:");
				e.printStackTrace();
			}
		} else {
			System.out.println("Order did not exceed category cap.");
			if (successor != null) {
				successor.processOrder(order);
			}
		}
	}

	public void setSuccessor(OrderProcessor successor) {
		this.successor = successor;
	}
}
