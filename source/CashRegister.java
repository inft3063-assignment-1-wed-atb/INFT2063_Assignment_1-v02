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

		boolean recieptEnd = false;
		
		boolean nextItem = true;
		double totalCost = 0;
		
		Receipt receipt = new Receipt();
		
		Scanner in = new Scanner(System.in);

		while (!validation) {
			try {
				System.out.print("Please enter cash register's float:");
				s = in.nextLine();
				balance = Double.parseDouble(s);
				validation = true;
			
			} catch(Exception e) {
				System.out.println("Invalid input, please enter a appropriate value.");
			}
		}
		validation = false;

		while (!endProgram) {
			
			nextItem = true;

			System.out.println("Would you like to proceed with the transaction? (y/n)");
			proceed = in.nextLine();

			if (proceed.equalsIgnoreCase("y")) {
				while (nextItem == true) {
					System.out.print("Please enter the item's name:");
					s = in.nextLine();

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
				
				while (!validation) {
					try {
						System.out.print("Please enter the cash amount tendered:");
						s = in.nextLine();
						receipt.cash(Double.parseDouble(s));
						validation = true;
					
					} catch(Exception e) {
						System.out.println("Invalid input, please enter a appropriate value.");
					}
				}
				validation = false;
						
				c = Double.toString(Double.parseDouble(s) - totalCost);

				System.out.println("Amount of change required = " + dollar_symbol + c);

				c = Double.toString(balance + totalCost);
				
				while (!recieptEnd) {

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
				System.out.println("Balance of the Cash Register: " + dollar_symbol + c);
				endProgram = true;
			}

			else {
				System.out.println("Invalid input. Please try again");
			}

		}

	}
}

