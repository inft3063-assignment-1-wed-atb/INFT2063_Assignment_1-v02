import java.util.Calendar;
import java.util.Scanner;

public class CashRegister {
	public static void main(String[] args) {
		// String to save user input
		String primary_input = null;
		String auxiliary_input = "0";
		String proceed;
		// initial balance
		double balance = 0;
		String dollar_symbol = "$";
		// validation for program initializing
		boolean endProgram = false;
		boolean validation = false;
		boolean paymentComplete = false;
		boolean recieptEnd = false;
		// Total sale during the system uptime
		double totalSale = 0;
		boolean nextItem = true;
		double totalCost = 0;
		// quantiy of items
		String quantity = "0";
		// Transacation number
		String transNo = "0000";
		// reports
		Report report = new Report();
		Receipt receipt = new Receipt();
		String userNo;
		// Loyalty program for customers
		loyaltyProgram loyalty = new loyaltyProgram();
		// String to start a new transaction
		String startTrans = "Would you like to proceed with the transaction? (y/n)";
		// User authentication for log in
		UserAuthentication userAuthentication = new UserAuthentication();
		userAuthentication.UserAuthentication();
		Scanner input = new Scanner(System.in);

		// Welcome message in ascii
		System.out.println(".  ..___.    __ .__..  ..___\n" + "|  |[__ |   /  `|  ||\\/|[__ \n"
				+ "|/\\|[___|___\\__.|__||  |[___\n" + "                            \n");

		// Loop while validation is false
		while (!validation) {
			try {
				// Ask user for cash register balance, store in a variable and set as balance
				System.out.print("Please enter starting cash register balance:");
				primary_input = input.nextLine();
				balance = Double.parseDouble(primary_input);
				// validate amount not less than 0
				if (balance < 0) {
					System.out.println("Cash register can not have less than $0");
				}
				// validate amount less than a million dollar
				else if (primary_input.length() > 6) {
					System.out.println("Cash register amount should be less than a million dollar.");
				} else {
					// set starting balance of report as balance
					report.setStartBalance(balance);
					validation = true;
				}
			} catch (Exception e) {
				System.out.println("Invalid input, please enter a appropriate value.");
			}
		}
		// Setting validation false
		validation = false;

		// loop while user chose not to end program
		while (!endProgram) {
			// Ask user if they want to start with transaction and save in variable
			System.out.println(startTrans);
			proceed = input.nextLine();

			// user chose to start a transaction
			if (proceed.equalsIgnoreCase("y")) {
				// Creating receipt and user as current user
				receipt = new Receipt();
				receipt.setCashier(userAuthentication.getCurrent_user());
				// new transactions set every validation to default
				paymentComplete = false;
				totalCost = 0;
				nextItem = true;
				recieptEnd = false;
				// until the cart is empty/has next item
				while (nextItem == true) {
					System.out.print("Please enter the item's name: ");
					primary_input = input.nextLine();
					// validate user input for length of text for item name "max 100 characters"
					while (primary_input.length() > 100) {
						System.out.println("Item name can not have more than 100 characters.");
						System.out.print("Please enter the item's name: ");
						primary_input = input.nextLine();
					}

					while (!validation) {
						try {
							// Asking user for item cost
							System.out.print("Please enter the " + primary_input + "'s cost: ");
							auxiliary_input = input.nextLine();

							// validate item cost not less than 0
							if (Double.parseDouble(auxiliary_input) < 0) {
								System.out.println("Item cost can not be less than 0");
							}
							// validate itemcost if more than a million dollar
							else if (auxiliary_input.length() > 6) {
								System.out.println("Item cost should be less than a million dollar.");
								// else set validation true
							} else {
								validation = true;
							}
						} catch (Exception e) {
							System.out.println("Invalid input, please enter a appropriate value.");
						}
					}
					// seting validation false for new task
					validation = false;

					// Asking for quantity of items
					while (!validation) {
						try {
							System.out.print("Please enter quantity of the " + primary_input + ":");
							quantity = input.nextLine();
							// validate quantity: should not less than 0
							if (Double.parseDouble(quantity) < 0) {
								System.out.println("Qunatity can not be less than 0");
							}
							// validate quantity: should be less than 100.
							else if (quantity.length() > 3) {
								System.out.println("Quantity should be less than 1000.");
							} else {
								// Adds the name, cost, and quantiy of item to the receiept class to
								// generate the receipt
								receipt.itemDetails(primary_input, Double.parseDouble(auxiliary_input),
										Integer.parseInt(quantity));
								validation = true;
							}
						} catch (Exception e) {
							System.out.println("Invalid input, please enter a appropriate value.");
						}
					}

					// seting validation false for new task
					validation = false;

					// Creating new transation with item name and cost
					Transaction trans = new Transaction(primary_input, Double.parseDouble(auxiliary_input));

					// getting cost total cost of item : item cost * quantity
					totalCost += trans.getCost() * Integer.parseInt(quantity);

					// Ask user if additional item left on cart to pay for
					System.out.println("Have you entered all items? (y/n)");
					primary_input = input.nextLine();

					// Validate for user input: only y for yes and n for no is allowed
					while (!(primary_input.equalsIgnoreCase("y") || primary_input.equalsIgnoreCase("n"))) {
						System.out.println("Please Enter y/n only");
						System.out.println("Have you entered all items? (y/n)");
						primary_input = input.nextLine();
					}

					// user has entered all items
					if (primary_input.equalsIgnoreCase("y")) {
						// updates the transaction number for the receipt
						int transNumber = Integer.parseInt(transNo) + 1;
						transNo = String.format("%04d", transNumber);
						receipt.transNo(transNo);
						nextItem = false;
					}
				}

				// Creating loyalty object
				userNo = loyalty.loyaltyPrompy(totalCost);
				if (userNo == null) {
					receipt.getUserInfo(null);
				} else {
					receipt.getUserInfo(loyalty.getUserNumber(userNo));
				}

				while (!validation) {
					try {
						// payment unsuccessful or not completed
						while (!paymentComplete) {
							// List of payment options
							System.out.println(
									"Please select the type of payment \n" + "1. Cash \n2. Bank Card \n3. Gift Card");
							String paymentType = input.nextLine();

							// Swith statement for different payment types
							switch (paymentType) {
							// payment type is cash
							case "1": {
								// Ask for cash tendered, pass into receipt
								System.out.print("Please enter the cash amount tendered:");
								primary_input = input.nextLine();
								// Validate cash tendered: should not be less than total item cost
								while (Double.parseDouble(primary_input) < totalCost) {
									System.out.println("<<* Cash tendered is less than total cost *>>\n");
									System.out.print("Please enter the cash amount tendered:");
									primary_input = input.nextLine();
								}
								// Adding cash tendered in receipt
								receipt.cash(Double.parseDouble(primary_input));
								// Calculating change amount: cash tendered - total cost
								auxiliary_input = Double.toString(Double.parseDouble(primary_input) - totalCost);
								System.out.println("Amount of change required = " + dollar_symbol + auxiliary_input);
								// Cash at register calculate
								auxiliary_input = Double.toString(balance + totalCost);
								// Adding total cost to total sale
								totalSale += totalCost;
								paymentComplete = true;
								validation = true;
								break;

							}

							// Payment type is bank card
							case "2": {
								System.out.println("Payment by Bank Card Selected");
								// Processing payment
								CashRegister.processPayment();
								// Cash received is equal to the total price
								receipt.cash(receipt.getTotalPrice());
								// cash at register add
								auxiliary_input = Double.toString(balance + totalCost);
								// Adding total cost to total sale
								totalSale += totalCost;
								paymentComplete = true;
								validation = true;
								break;
							}

							// Payment type is gift card
							case "3": {
								System.out.println("Payment by Gift Card Selected");
								// Processing payment
								CashRegister.processPayment();
								// Cash received is equal to the total price
								receipt.cash(receipt.getTotalPrice());
								auxiliary_input = Double.toString(balance + totalCost);
								// Adding total cost to total sale
								totalSale += totalCost;
								validation = true;
								paymentComplete = true;
								break;
							}
							// input validation: user type other than 1,2 and 3 which are options for
							// different payment type
							default: {
								System.out.println("Please choose 1, 2 or 3 from the following options:");
								break;
							}
							}

						}
					} catch (Exception e) {
						System.out.println("Invalid input, please enter a appropriate value.");
					}
				}
				validation = false;

				while (!recieptEnd) {
					// Changing receipt date to the current date & time
					receipt.setToday(Calendar.getInstance().getTime());
					// Adding receipt to the report
					report.getReceipt().add(receipt);

					// Asking user if they like to get receipt
					System.out.println("Would you like a copy of your reciept? (y/n)");
					proceed = input.nextLine();

					// User wants to print receipt
					if (proceed.equalsIgnoreCase("y")) {
						System.out.println(receipt.printReciept());
						recieptEnd = true;
						// User do not want to print receipt
					} else if (proceed.equalsIgnoreCase("n")) {
						recieptEnd = true;
					} else {
						System.out.println("Invalid input. Please try again");
					}
				}
			}
			// User wants to stop the system
			else if (proceed.equalsIgnoreCase("n")) {
				// setting report ending balance and printing report
				report.setBalanceCashRegister(Double.toString(totalSale + balance));
				System.out.println(report.printReport());
				endProgram = true;
			}

			else {
				System.out.println("Invalid input. Please try again");
			}
			// change in start transaction after first transaction
			startTrans = "Would you like to process another transaction? (y/n)";
		}
		// Closing scanner
		input.close();

	}

	/**
	 * Process the payment by bank card or by gift cards.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public static void processPayment() throws InterruptedException {
		System.out.print("\n>>Processing Payment ");
		// Loop to print .
		for (int x = 0; x < 20; x++) {
			System.out.print(". ");
			// timer between two . print
			Thread.sleep(350);
		}
		// Ascii display on payment success
		System.out.println("\n +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+\n"
				+ " |P| |A| |Y| |M| |E| |N| |T|   |S| |U| |C| |C| |E| |S| |S| |F| |U| |L|\n"
				+ " +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+\n");
	}
}
