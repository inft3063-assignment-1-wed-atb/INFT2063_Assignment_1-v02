import java.util.Calendar;
import java.util.Scanner;

public class CashRegister {
	public static void main(String[] args) {
		String s = null;
		String c = "0";
		String proceed;
		double balance = 0;
		String dollar_symbol = "$";
		boolean endProgram = false;
		boolean validation = false;
		boolean paymentComplete = false;
		boolean recieptEnd = false;
		double totalSale = 0;
		boolean nextItem = true;
		double totalCost = 0;
		// reports
		Report report = new Report();
		Receipt receipt = new Receipt();
		
		UserAuthentication userAuthentication = new UserAuthentication();
		userAuthentication.UserAuthentication();
		
		Scanner in = new Scanner(System.in);

		System.out.println(".  ..___.    __ .__..  ..___\n" + 
				"|  |[__ |   /  `|  ||\\/|[__ \n" + 
				"|/\\|[___|___\\__.|__||  |[___\n" + 
				"                            \n");
		while (!validation) {
			try {
				System.out.print("Please enter cash register's float:");
				s = in.nextLine();
				balance = Double.parseDouble(s);
				report.setStartBalance(balance);
				
				validation = true;
			
			} catch(Exception e) {
				System.out.println("Invalid input, please enter a appropriate value.");
			}
		}
		validation = false;

		while (!endProgram) {
			System.out.println("Would you like to proceed with the transaction? (y/n)");
			proceed = in.nextLine();

			if (proceed.equalsIgnoreCase("y")) {
				// new transactions set every validation to default
				receipt = new Receipt();;
				receipt.setCashier(userAuthentication.getCurrent_user());
				paymentComplete = false;
				totalCost =0;
				nextItem = true;
				recieptEnd = false;
				// until the cart is empty/has next item
				while (nextItem == true) {
					System.out.print("Please enter the item's name:");
					s = in.nextLine();
					// check for input other than int
					while (!validation) {
						try {
							System.out.print("Please enter the item's cost:");
							c = in.nextLine();
							receipt.itemDetails(s, Double.parseDouble(c), 1);
							validation = true;
					
						} catch(Exception e) {
							System.out.println("Invalid input, please enter a appropriate value.");
						}
					}
					validation = false;

					Transaction trans = new Transaction(s, Double.parseDouble(c));

					totalCost += trans.getCost();
					System.out.println("Have you entered all items? (y/n)");
					s = in.nextLine();
					while (!(s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n"))) {
						System.out.println("Please Enter y/n only");
						System.out.println("Have you entered all items? (y/n)");
						s = in.nextLine();
					}
					if (s.equalsIgnoreCase("y")) {
						nextItem = false;
					}
				}
				// Validation for cash tendered
				while (!validation) {
					try {
						// payment unsuccessful or not completed
						while (!paymentComplete) {
							// List of payment options
							System.out.println(
									"Please Choose the type of payment \n" + "1. Cash \n2. Bank Card \n3. Gift Card");
							String paymentType = in.nextLine();
							switch (paymentType) {
							// payment type is cash
							case "1": {
								// Ask for cash tendered, pass into receipt
								System.out.print("Please enter the cash amount tendered:");
								s = in.nextLine();
								while(Double.parseDouble(s) < totalCost) {
									System.out.println("<<* Cash tendered is less than total cost *>>\n");
									System.out.print("Please enter the cash amount tendered:");
									s = in.nextLine();
								}
								receipt.cash(Double.parseDouble(s));
								c = Double.toString(Double.parseDouble(s) - totalCost);

								System.out.println("Amount of change required = " + dollar_symbol + c);

								c = Double.toString(balance + totalCost);
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
								c = Double.toString(balance + totalCost);
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
								c = Double.toString(balance + totalCost);
								totalSale += totalCost;
								validation = true;
								paymentComplete = true;
								break;
							}
							// user type other than 1,2 and 3 which are options for different payment type
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
					System.out.println("Would you like a copy of your reciept? (y/n)");
					proceed = in.nextLine();
					
					if (proceed.equalsIgnoreCase("y")) {
						System.out.println(receipt.printReciept());
						recieptEnd = true;
					}
					else if(proceed.equalsIgnoreCase("n")) {
						recieptEnd=true;
					}
					else {
						System.out.println("Invalid input. Please try again");
					}
				}
			}

			else if (proceed.equalsIgnoreCase("n")) {
				report.setBalanceCashRegister(Double.toString(totalSale+ balance));
				
				System.out.println(report.printReport());
				endProgram = true;
			}

			else {
				System.out.println("Invalid input. Please try again");
			}

		}
		// Closing scanner
		in.close();

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
		System.out.println("\n +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+\n"
				+ " |P| |A| |Y| |M| |E| |N| |T|   |S| |U| |C| |C| |E| |S| |S| |F| |U| |L|\n"
				+ " +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+\n");
	}
}

