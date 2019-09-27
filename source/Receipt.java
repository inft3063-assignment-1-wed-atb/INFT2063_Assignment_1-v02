import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tin Nguyen
 *
 */
public class Receipt {

	private String cashier;
	//The variable in which the receipt will start from
	private String reciept = "";
	//An array of all the items for the transaction
	private ArrayList<String> items;
	//An array for the item price of the transactions
	private ArrayList<Double> itemPrice;
	//An array to store the quantity of the items
	private ArrayList<Integer> quantity;
	//An array to store the subtotal 
	private ArrayList<Double> subtotal;
	//Stores the total price of the items
	private double totalPrice;
	// date of receipt
	private Date today = Calendar.getInstance().getTime();
	public double getTotalPrice() {
		return totalPrice;
	}

	private String space = " ";
	//Stores the amount of cash that was given
	private double cashGiven;

	/**
	 * Constructor for the Receipt class. Initialises the required variables needed
	 * such as the items, item price, quantity, and the sub total.
	 */
	public Receipt() {
		items = new ArrayList<String>();
		itemPrice = new ArrayList<Double>();
		quantity = new ArrayList<Integer>();
		subtotal = new ArrayList<Double>();
	}

	/**
	 * Adds the item, item price, and quantity of the item into an array to be
	 * processed later when creating the receipt.
	 * 
	 * @param _itemName  The name of the item
	 * @param _itemPrice The price of the item
	 * @param qty        The quantity of the item
	 */
	public void itemDetails(String _itemName, double _itemPrice, int qty) {

		items.add(_itemName);
		itemPrice.add(_itemPrice);
		quantity.add(qty);
		// Updates the total price of all the items
		totalPrice = totalPrice + (_itemPrice * qty);
		// Adds the subtotal so far to the subtotal array
		subtotal.add(totalPrice);

	}

	public ArrayList<Integer> getQuantity() {
		return quantity;
	}

	/**
	 * This method is purely used for the format and structure of the receipt. It
	 * adds empty spaces between each column in the receipt.
	 * 
	 * @param amount The amount of spaces
	 * @return Returns a string with a number of empty spaces
	 */
	private String spaces(int amount) {
		space = " ";
		for (int i = 0; i < amount; i++) {
			space = space + " ";
		}
		return space;
	}

	/**
	 * Stores the amount of cash that was given by the user
	 * 
	 * @param cashGiven The amount of cash tendered
	 */
	public void cash(double cashGiven) {
		this.cashGiven = cashGiven;
	}

	/**
	 * This method prints out the receipt to display to the user.
	 * 
	 * @return The receipt
	 */
	public String printReciept() {

		// Information about the grocery story and who the cashier is.
		reciept = "\nTAX RECIEPT\n" + "UniSA Groceries Pty Ltd\n" + "Shop 3A, 40 Main Street, Mawson Lakes, SA\n"
				+ "ABN 23 234 680 230\nCashier: "+ cashier + "\n" + "\n";

		// Adding in the title for each required information such as: item, quantity,
		// price,
		// and the subtotal
		reciept = reciept + "Item" + spaces(20) + "QTY" + spaces(2) + "Price" + spaces(2) + "Subtotal\n";

		reciept = reciept + "------------------------------------------------\n";

		//Looping through each different array and adding the required information to each column
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
		
		// for rounding
		DecimalFormat value = new DecimalFormat("#.#");
		// gst info
		reciept+= "\n\nTOTAL includes GST " + spaces(19) + "$" + value.format(totalPrice * 0.10);


		reciept = reciept + "\n\n\n        Thank you, please come again.\n\n";

		//The manufactured barcode
		reciept = reciept + "    || |||| ||||||| |||||| ||||| ||||||||\n";
		reciept = reciept + "    || |||| ||||||| |||||| ||||| ||||||||\n\n";

		reciept = reciept + "        " + today + "\n";

		//The below block of code creates a new text file and adds the receipt to it.
		//Used to allow the user to be able to create a copy to print a hard copy of
		//the receipt.
		PrintWriter outputStream = null;

		try {
			outputStream = new PrintWriter(new FileOutputStream("receipt.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		outputStream.println(reciept);

		outputStream.close();

		return reciept;

	}

	public ArrayList<String> getItems() {
		return items;
	}

	public String getCashier() {
		return cashier;
	}

	public Date getToday() {
		return today;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
}

