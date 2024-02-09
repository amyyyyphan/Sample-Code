import java.util.Set;
import java.util.HashSet;

public class Cards {
	private static Cards instance;
	private Set<String> cards;
	
	private Cards() {
		cards = new HashSet<String>();
		cards.add("5.41E+15");
		cards.add("4.12E+12");
		cards.add("3.41E+14");
		cards.add("6.01E+15");
	}
	
	public static synchronized Cards getInstance() {
		if (instance == null) {
			instance = new Cards();
		}
		return instance;
	}
	
	public boolean contains(String cardNumber) {
		return cards.contains(cardNumber);
	}
	
	public boolean add(String cardNumber) {
		return cards.add(cardNumber);
	}
}
