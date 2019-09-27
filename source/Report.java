import java.util.ArrayList;

public class Report {
	private ArrayList<Receipt> receipt = new ArrayList<Receipt>();

	private double totalSales = 0;
	private int totalItem = 0;

	public static void main(String[] args) {
		Report r = new Report();
		r.receipt.add(new Receipt());
		r.receipt.get(0).itemDetails("Noodle box", 100, 2);
		r.receipt.get(0).setCashier("DIck");
		r.receipt.get(0).itemDetails("ff", 10000, 1);
		r.receipt.add(new Receipt());
		r.receipt.get(1).itemDetails("ff", 200, 2);
		r.receipt.get(1).setCashier("Rick");
		;

		r.printReport();
	}

	public void printReport() {
		// Company Information
		System.out.println("\n____ ____ ___  ____ ____ ___ \n" + "|__/ |___ |__] |  | |__/  |  \n"
				+ "|  \\ |___ |    |__| |  \\  |  \n" + "                             \nUniSA Groceries Pty Ltd\n"
				+ "Shop 3A, 40 Main Street, Mawson Lakes, SA\n" + "ABN 23 234 680 230\n" + spaces(55, "*") + "\n");
		// No receipt available, no sales
		if (receipt == null) {
			System.out.println("\n\nNO SALES INFORMATION AVAILABLE");
			// receipt found print report
		} else {
			// print sales information
			System.out.println("+++SALES+++\n" + spaces(55, "-"));
			System.out.println("Date & Time" + spaces(19, " ") + "|  Price       | Cashier");
			System.out.println(spaces(55, "-"));
			// Loop over list of receipt, print receipt date, total price
			for (Receipt r : receipt) {
				System.out.println(r.getToday() + "  |  " + r.getTotalPrice()
				+ spaces(11 - Double.toString(r.getTotalPrice()).length(), " ") + "|  " + r.getCashier());
				// Add total sales for the system uptime
				totalSales += r.getTotalPrice();
			}
			System.out.println(spaces(55, "-"));
			System.out.println("Total Sales" + spaces(22, " ") + totalSales);
			System.out.println(spaces(55, "-"));

			// Print Inventory report
			System.out.println("\n\n+++INVENTORY+++\n" + spaces(35, "-"));
			System.out.println("Item Name" + spaces(13, " ") + "|  Quantity");
			System.out.println(spaces(35, "-"));
			// Loop over receipt, get items and quantity from receipt
			for (Receipt r : receipt) {
				ArrayList<String> items = r.getItems();
				ArrayList<Integer> qnty = r.getQuantity();
				// Loop over item, get item name and its quantity
				for (int i = 0; i < items.size(); i++) {
					// Text length more than 20 abbrebrate
					System.out.println(abbreviateString(items.get(i), 20)
							+ spaces(22 - abbreviateString(items.get(i), 20).length(), " ") + "|  " + qnty.get(i));
					totalItem += qnty.get(i);
				}
			}
			System.out.println(spaces(35, "-"));
			System.out.println("Total Item" + spaces(15, " ") + totalItem);
			System.out.println(spaces(35, "-"));

			System.out.println("\n\n +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+\n" + 
					" |T| |h| |a| |n| |k|   |Y| |o| |u|\n" + 
					" +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+\n" + 
					"");
		}

	}

	/**
	 * Get the list of receipt from the report.
	 * 
	 * @return ArrayList of receipt
	 */
	public ArrayList<Receipt> getReceipt() {
		return receipt;
	}

	/**
	 * Set new receipt list as receipt for the report
	 * 
	 * @param receipt Arraylist of receipt
	 */
	public void setReceipt(ArrayList<Receipt> receipt) {
		this.receipt = receipt;
	}

	/**
	 * This method is purely used for the format and structure of the report. It
	 * adds number of string in the parameter.
	 * 
	 * @param amount The amount of string
	 * @param s      the string to be output
	 * @return Returns a string with a number of string
	 */
	private String spaces(int amount, String s) {
		// Enter string iteratively
		while (amount > 0) {
			return s + spaces(amount - 1, s);
		}
		return s;
	}

	/**
	 * If the string length is more than maximum length , truncate with .. at the
	 * end of the string
	 * 
	 * @param input     string which need to be checked
	 * @param maxLength allowed maximum length of the string
	 * @return string with limited characters
	 */
	public static String abbreviateString(String input, int maxLength) {
		// string length less than maximum, return string otherwise truncate add ..
		if (input.length() <= maxLength)
			return input;
		else
			return input.substring(0, maxLength - 2) + "..";
	}

}
