
public interface OrderProcessor {
	
	public void processOrder(Order order);
	public void setSuccessor(OrderProcessor successor);
}
