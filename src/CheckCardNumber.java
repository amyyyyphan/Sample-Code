
public class CheckCardNumber implements OrderProcessor{
	private OrderProcessor successor = null;

	public void processOrder(Order order) {
		Cards cardsDB = Cards.getInstance();
		
		if (!cardsDB.contains(order.getCardNumber())) {
			cardsDB.add(order.getCardNumber());
			System.out.println("Card number is not present in DB, card number added to DB.");
		} else {
			System.out.println("Card number is present in DB, card number not added to DB.");
		}
		
		if (successor != null) {
			successor.processOrder(order);
		}
	}

	public void setSuccessor(OrderProcessor successor) {
		this.successor = successor;
	}
}
