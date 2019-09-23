import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Receipt {

	//test
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

		reciept = "\nIRISIDI LENTELA\n" + "I-UniSa Groceries Pty Ltd\n" + "Shop 3A, 40 Main Street, Mawson Lakes, SA\n"
				+ "ABN 23 234 680 230\nIndishi: UJesu\n" + "\n";
		
		reciept = reciept + "Into" + spaces(20) + "Inani" + spaces(2) + "Intengo" + spaces(2) + "Okuncane\n";

		reciept = reciept + "----------------------------------------------------\n";
		
		for (int i = 0; i < items.size(); i++) {
			reciept = reciept + items.get(i);
			reciept = reciept + spaces(24 - items.get(i).length()) + quantity.get(i);
			reciept = reciept + spaces(7 - String.valueOf(quantity.get(i)).length()) + itemPrice.get(i);
			reciept = reciept + spaces(9 - String.valueOf(itemPrice.get(i)).length()) + subtotal.get(i) + "\n";
		}

		reciept = reciept + "\n----------------------------------------------------";

		reciept = reciept + "\nKONKE";
		reciept = reciept + spaces(37) + "$" + totalPrice;

		reciept = reciept + "\nIMALI";
		reciept = reciept + spaces(37) + "$" + cashGiven;

		reciept = reciept + "\nSHINTSHA NGENXA";
		reciept = reciept + spaces(27) + "$" + (cashGiven - totalPrice);

		Date today = Calendar.getInstance().getTime();
	
		
		reciept = reciept + "\n\n\n            Ngiyabonga, ngicela ubuye.\n\n";
		
		reciept = reciept + "      || |||| ||||||| |||||| ||||| ||||||||\n";
		reciept = reciept + "      || |||| ||||||| |||||| ||||| ||||||||\n\n";
		
		reciept = reciept + "        " + today +"\n";
		
		return reciept;

	}

}

