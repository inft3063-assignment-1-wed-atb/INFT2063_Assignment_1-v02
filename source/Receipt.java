import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Receipt {

	private String reciept = "";
	private ArrayList<String> items;
	private ArrayList<Double> itemPrice;
	private ArrayList<Integer> quantity;
	private ArrayList<Double> subtotal;
	private double totalPrice;
	private String space = " ";
	private double cashGiven;

	public Receipt() {
		items = new ArrayList<String>();
		itemPrice = new ArrayList<Double>();
		quantity = new ArrayList<Integer>();
		subtotal = new ArrayList<Double>();
	}

	public void itemDetails(String _itemName, double _itemPrice, int qty) {

		items.add(_itemName);
		itemPrice.add(_itemPrice);
		quantity.add(qty);
		totalPrice = totalPrice + (_itemPrice * qty);
		subtotal.add(totalPrice);

	}

	private String spaces(int amount) {
		space = " ";
		for (int i = 0; i < amount; i++) {
			space = space + " ";
		}
		return space;
	}

	public void cash(double cashGiven) {
		this.cashGiven = cashGiven;
	}

	public String printReciept() {

		reciept = "\nTAX RECIEPT\n" + "UniSA Groceries Pty Ltd\n" + "Shop 3A, 40 Main Street, Mawson Lakes, SA\n"
				+ "ABN 23 234 680 230\nCashier: Jeusus\n" + "\n";
		
		reciept = reciept + "Item" + spaces(20) + "QTY" + spaces(2) + "Price" + spaces(2) + "Subtotal\n";

		reciept = reciept + "------------------------------------------------\n";
		
		for (int i = 0; i < items.size(); i++) {
			reciept = reciept + items.get(i);
			reciept = reciept + spaces(24 - items.get(i).length()) + quantity.get(i);
			reciept = reciept + spaces(5 - String.valueOf(quantity.get(i)).length()) + itemPrice.get(i);
			reciept = reciept + spaces(7 - String.valueOf(itemPrice.get(i)).length()) + subtotal.get(i) + "\n";
		}

		reciept = reciept + "\n------------------------------------------------";

		reciept = reciept + "\nTOTAL";
		reciept = reciept + spaces(33) + "$" + totalPrice;

		reciept = reciept + "\nCASH";
		reciept = reciept + spaces(34) + "$" + cashGiven;

		reciept = reciept + "\nCHANGE DUE";
		reciept = reciept + spaces(28) + "$" + (cashGiven - totalPrice);

		Date today = Calendar.getInstance().getTime();
	
		
		reciept = reciept + "\n\n\n        Thank you, please come again.\n\n";
		
		reciept = reciept + "    || |||| ||||||| |||||| ||||| ||||||||\n";
		reciept = reciept + "    || |||| ||||||| |||||| ||||| ||||||||\n\n";
		
		reciept = reciept + "        " + today +"\n";
		
		return reciept;

	}

}
