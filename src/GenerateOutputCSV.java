import java.io.IOException;
import java.io.PrintWriter;

public class GenerateOutputCSV implements OrderProcessor {
	private OrderProcessor successor = null;
	
	public void processOrder(Order order) {
		try {
			PrintWriter pw = new PrintWriter("../output/output.csv");
			StringBuilder sb = new StringBuilder();
			
			// output csv file header
			sb.append("Item");
			sb.append(",");
			sb.append("Quantity");
			sb.append(",");
			sb.append("Price");
			sb.append(",");
			sb.append("TotalPrice");
			sb.append("\n");
			
			// first row
			sb.append(order.getItems().get(0).getName());
			sb.append(",");
			sb.append(order.getItems().get(0).getQuantity());
			sb.append(",");
			sb.append(order.getPrices().get(0));
			sb.append(",");
			sb.append(order.getTotalPrice());
			sb.append("\n");
			
			for (int i = 1; i < order.getItems().size(); i++) {
				sb.append(order.getItems().get(i).getName());
				sb.append(",");
				sb.append(order.getItems().get(i).getQuantity());
				sb.append(",");
				sb.append(order.getPrices().get(i));
				sb.append("\n");
			}
			
			pw.write(sb.toString());
			pw.close();
			
			System.out.println("Order has been processed. Please look at the output.csv file.");
			if (successor != null) {
				successor.processOrder(order);
			}
		} catch (IOException e) {
			System.out.println("Something went wrong while generating the output.csv file. Please check the output file path in GenerateOutputCSV.java on Line 9. The error was:");
			e.printStackTrace();
		}
	}

	public void setSuccessor(OrderProcessor successor) {
		this.successor = successor;
	}
}
