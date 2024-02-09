import java.util.Scanner;
import java.io.IOException;

public class Billing {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the input file path: ");
		String filePath = scan.nextLine();
		scan.close();
		
		try {
			Order order = new Order(filePath);

			OrderProcessor orderProcessor1 = new ValidateQuantity();
			OrderProcessor orderProcessor2 = new ValidateCategoryCap();
			OrderProcessor orderProcessor3 = new CalculatePrice();
			OrderProcessor orderProcessor4 = new CheckCardNumber();
			OrderProcessor orderProcessor5 = new GenerateOutputCSV();
			
			orderProcessor1.setSuccessor(orderProcessor2);
			orderProcessor2.setSuccessor(orderProcessor3);
			orderProcessor3.setSuccessor(orderProcessor4);
			orderProcessor4.setSuccessor(orderProcessor5);
			
			orderProcessor1.processOrder(order);
		} catch (IOException e) {
			String retryMessage = "Make sure you have entered the correct path and try again.";
			System.out.println("Could not find file " + filePath + ". " + retryMessage);
			System.exit(1);
		}
	}
}
