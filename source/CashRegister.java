import java.util.Calendar;
import java.util.Scanner;

public class CashRegister {
	public static void main(String[] args) {
		String primary_input = null;
		String auxiliary_input = "0";
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
		//quantiy of items
		String quantity ="0";
		// Transacation number
		String transNo = "0000";
		// reports
		Report report = new Report();
		Receipt receipt = new Receipt();
		String userNo;
		loyaltyProgram loyalty = new loyaltyProgram();
		String startTrans = "Would you like to proceed with the transaction? (y/n)";

		UserAuthentication userAuthentication = new UserAuthentication();
		userAuthentication.UserAuthentication();
		
		Scanner input = new Scanner(System.in);

		System.out.println(".  ..___.    __ .__..  ..___\n" + "|  |[__ |   /  `|  ||\\/|[__ \n"
				+ "|/\\|[___|___\\__.|__||  |[___\n" + "                            \n");
		while (!validation) {
			try {
				System.out.print("Please enter starting cash register balance:");
				primary_input = input.nextLine();
				balance = Double.parseDouble(primary_input);
				// validate amount not less than 0
				if(balance <0) {
					System.out.println("Cash register can not have less than $0");
				}
				// validate amount less than a million dollar
				else if(primary_input.length() > 6 ) {
					System.out.println("Cash register amount should be less than a million dollar.");
				}
				else {
				report.setStartBalance(balance);
				
				validation = true;
				}

			} catch (Exception e) {
				System.out.println("Invalid input, please enter a appropriate value.");
			}
		}
		validation = false;

		while (!endProgram) {
			System.out.println(startTrans);
			proceed = input.nextLine();

			if (proceed.equalsIgnoreCase("y")) {
				// new transactions set every validation to default
				receipt = new Receipt();
				;
				receipt.setCashier(userAuthentication.getCurrent_user());
				paymentComplete = false;
				totalCost = 0;
				nextItem = true;
				recieptEnd = false;
				// until the cart is empty/has next item
				while (nextItem == true) {
					System.out.print("Please enter the item's name: ");
					primary_input = input.nextLine();
					while (primary_input.length() > 100) {
						System.out.println("Item name can not have more than 100 characters.");
						System.out.print("Please enter the item's name: ");
						primary_input = input.nextLine();
					}
					// check for input other than int
					while (!validation) {
						try {
							System.out.print("Please enter the " + primary_input + "'s cost: ");
							auxiliary_input = input.nextLine();
							// validate item cost not less than 0
							if (Double.parseDouble(auxiliary_input) < 0) {
								System.out.println("Item cost can not be less than 0");
							}
							// validate itemcost less than a million dollar
							else if (auxiliary_input.length() > 6) {
								System.out.println("Item cost should be less than a million dollar.");
							} else {
								validation = true;
							}
						} catch (Exception e) {
							System.out.println("Invalid input, please enter a appropriate value.");
						}
					}
					validation = false;
					
					//Asking for quantity of items 
					while (!validation) {
						try {
							System.out.print("Please enter quantity of the " + primary_input + ":");
							quantity = input.nextLine();
							// validate quantity not less than 0
							if (Double.parseDouble(quantity) < 0) {
								System.out.println("Qunatity can not be less than 0");
							}
							// validate qunatity less than a 100.
							else if (quantity.length() > 3) {
								System.out.println("Quantity should be less than 1000.");
							} else {
								// Adds the name, cost, and quantiy of item to the receiept class to
								// generate the receipt
								receipt.itemDetails(primary_input, Double.parseDouble(auxiliary_input), Integer.parseInt(quantity));
								validation = true;
							}
						}
						catch (Exception e) {
							System.out.println("Invalid input, please enter a appropriate value.");
						}
					}
					
					validation = false;

					Transaction trans = new Transaction(primary_input, Double.parseDouble(auxiliary_input));

					totalCost += trans.getCost()*Integer.parseInt(quantity);
					
					System.out.println("Have you entered all items? (y/n)");
					primary_input = input.nextLine();
					while (!(primary_input.equalsIgnoreCase("y") || primary_input.equalsIgnoreCase("n"))) {
						System.out.println("Please Enter y/n only");
						System.out.println("Have you entered all items? (y/n)");
						primary_input = input.nextLine();
					}
					if (primary_input.equalsIgnoreCase("y")) {
						//updates the transaction number for the receipt 
						int transNumber = Integer.parseInt(transNo) +1;
						transNo = String.format("%04d", transNumber); 
						receipt.transNo(transNo);
						nextItem = false;
					}
				}

				userNo = loyalty.loyaltyPrompy(totalCost);
				if (userNo == null) {
					receipt.getUserInfo(null);
				} else {
					receipt.getUserInfo(loyalty.getUserNumber(userNo));
				}

				// Validation for cash tendered
				while (!validation) {
					try {
						// payment unsuccessful or not completed
						while (!paymentComplete) {
							// List of payment options
							System.out.println(
									"Please select the type of payment \n" + "1. Cash \n2. Bank Card \n3. Gift Card");
							String paymentType = input.nextLine();
							switch (paymentType) {
							// payment type is cash
							case "1": {
								// Ask for cash tendered, pass into receipt
								System.out.print("Please enter the cash amount tendered:");
								primary_input = input.nextLine();
								while (Double.parseDouble(primary_input) < totalCost) {
									System.out.println("<<* Cash tendered is less than total cost *>>\n");
									System.out.print("Please enter the cash amount tendered:");
									primary_input = input.nextLine();
								}
								receipt.cash(Double.parseDouble(primary_input));
								auxiliary_input = Double.toString(Double.parseDouble(primary_input) - totalCost);

								System.out.println("Amount of change required = " + dollar_symbol + auxiliary_input);

								auxiliary_input = Double.toString(balance + totalCost);
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
								auxiliary_input = Double.toString(balance + totalCost);
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
					proceed = input.nextLine();

					if (proceed.equalsIgnoreCase("y")) {
						System.out.println(receipt.printReciept());
						recieptEnd = true;
					} else if (proceed.equalsIgnoreCase("n")) {
						recieptEnd = true;
					} else {
						System.out.println("Invalid input. Please try again");
					}
				}
			}

			else if (proceed.equalsIgnoreCase("n")) {
				report.setBalanceCashRegister(Double.toString(totalSale + balance));

				System.out.println(report.printReport());
				endProgram = true;
			}

			else {
				System.out.println("Invalid input. Please try again");
			}

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
		System.out.println("\n +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+\n"
				+ " |P| |A| |Y| |M| |E| |N| |T|   |S| |U| |C| |C| |E| |S| |S| |F| |U| |L|\n"
				+ " +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+ +-+\n");
	}
}




