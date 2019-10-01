import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Report {
	private ArrayList<Receipt> receipt = new ArrayList<Receipt>();
	// String version of the report
	private String report = "";
	private double totalSales = 0;
	private int totalItem = 0;
	// Starting cash register balance set 0;
	private String balanceCashRegister = "0";
	private double startBalance = 0;

	/**
	 * Set the starting cash register balance
	 * 
	 * @param startBalance starting balance
	 */
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * get the balance of the cash register
	 * 
	 * @return cash register balance
	 */
	public String getBalanceCashRegister() {
		return balanceCashRegister;
	}

	/**
	 * Set the balance of the cash register
	 * 
	 * @param c new cash register balance
	 */
	public void setBalanceCashRegister(String c) {
		this.balanceCashRegister = c;
	}

	/**
	 * Return the string representation of the report
	 * 
	 * @return string representation of the report
	 */
	public String printReport() {
		// Company Information
		report += "\n" + spaces(55, "*") + "\n____ ____ ___  ____ ____ ___ \n" + "|__/ |___ |__] |  | |__/  |  \n"
				+ "|  \\ |___ |    |__| |  \\  |  \n" + "                             \nUniSA Groceries Pty Ltd\n"
				+ "Shop 3A, 40 Main Street, Mawson Lakes, SA\n" + "ABN 23 234 680 230\n\n" + spaces(55, "*") + "\n";
		// No receipt available, no sales
		if (receipt == null) {
			report += "\n\nNO SALES INFORMATION AVAILABLE";
			// receipt found print report
		} else {
			report += "\n" + spaces(43, "<") + "\nStarting balance of the Cash Register: " + "$" + startBalance + "\n"
					+ spaces(43, ">") + "\n";
			// add sales information
			report += "\n+++SALES+++\n" + spaces(55, "-");
			report += "\nDate & Time" + spaces(19, " ") + "|  Sales       | Cashier\n";
			report += spaces(55, "-") + "\n";
			// Loop over list of receipt, print receipt date, total price
			for (Receipt r : receipt) {
				report += (r.getToday() + spaces(30 - r.getToday().toString().length(), " ") + "|  " + r.getTotalPrice()
						+ spaces(11 - Double.toString(r.getTotalPrice()).length(), " ") + "|  " + r.getCashier()
						+ "\n");
				// Add total sales for the system uptime
				totalSales += r.getTotalPrice();
			}
			report += (spaces(55, "-") + "\n");
			report += ("Total Sales" + spaces(22, " ") + totalSales) + "\n";
			report += (spaces(55, "-"));

			// Print Inventory report
			report += ("\n\n+++INVENTORY+++\n" + spaces(35, "-") + "\n");
			report += ("Item Name" + spaces(13, " ") + "|  Quantity\n");
			report += spaces(35, "-") + "\n";
			// Loop over receipt, get items and quantity from receipt
			for (Receipt r : receipt) {
				ArrayList<String> items = r.getItems();
				ArrayList<Integer> qnty = r.getQuantity();
				// Loop over item, get item name and its quantity
				for (int i = 0; i < items.size(); i++) {
					// Text length more than 20 abbrebrate, space between two colum change based on
					// text length
					report += abbreviateString(items.get(i), 20)
							+ spaces(22 - abbreviateString(items.get(i), 20).length(), " ") + "|  " + qnty.get(i)
							+ "\n";
					totalItem += qnty.get(i);
				}
			}
			report += spaces(35, "-") + "\n";
			report += "Total Item" + spaces(15, " ") + totalItem + "\n";
			report += spaces(35, "-");
			// Adding ending cash register balance and thank you note
			report += "\n\n" + spaces(36, "<") + "\nBalance of the Cash Register: " + "$" + balanceCashRegister + "\n"
					+ spaces(36, ">") + "\n";
			report += "\n\n +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+\n" + " |T| |h| |a| |n| |k|   |Y| |o| |u|\n"
					+ " +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+\n" + "";
		}
		// Outputing the report in text file
		PrintWriter outputStream = null;
		try {
			// Date formating for report file name
			String fileName = new SimpleDateFormat("yyyy-MM-dd-hhmm'.txt'").format(new Date());
			fileName = "Report_" + fileName;
			// Creating export folder if not exist
			new File("Reports").mkdir();
			File file = new File("./Reports/" + fileName);

			// Output name of the report.
			outputStream = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		outputStream.println(report);

		outputStream.close();
		return report;

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
	private String abbreviateString(String input, int maxLength) {
		// string length less than maximum, return string otherwise truncate add ..
		if (input.length() <= maxLength)
			return input;
		else
			return input.substring(0, maxLength - 2) + "..";
	}

}
