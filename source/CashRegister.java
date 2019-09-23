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

		System.out.println(".  ..___.    __ .__..  ..___\n" + 
				"|  |[__ |   /  `|  ||\\/|[__ \n" + 
				"|/\\|[___|___\\__.|__||  |[___\n" + 
				"                            \n");
		while (!validation) {
			try {
				System.out.print("Sicela ufake iflothi lerejista yemali:");
				s = in.nextLine();
				balance = Double.parseDouble(s);
				validation = true;
			
			} catch(Exception e) {
				System.out.println("Okokufaka okungalungile, sicela ufake inani elifanele.");
			}
		}
		validation = false;

		while (!endProgram) {
			
			nextItem = true;

			System.out.println("Ungathanda ukuqhubeka nokuthengiselana? (y/n)");
			proceed = in.nextLine();

			if (proceed.equalsIgnoreCase("y")) {
				while (nextItem == true) {
					System.out.print("Sicela ufake igama laleyo nto:");
					s = in.nextLine();

					while (!validation) {
						try {
							System.out.print("Sicela ufake izindleko zento:");
							c = in.nextLine();
							receipt.itemDetails(s, Double.parseDouble(c), 1);
							validation = true;
					
						} catch(Exception e) {
							System.out.println("Okokufaka okungalungile, sicela ufake inani elifanele.");
						}
					}
					validation = false;

					Transaction trans = new Transaction(s, Double.parseDouble(c));

					totalCost += trans.getCost();
					System.out.println("Ufake zonke izinto? (y/n)");
					s = in.nextLine();
					while (!(s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n"))) {
						System.out.println("Sicela ufake y/n kuphela");
						System.out.println("Ufake zonke izinto? (y/n)");
						s = in.nextLine();
					}
					if (s.equalsIgnoreCase("y")) {
						nextItem = false;
					}
				}
				
				while (!validation) {
					try {
						System.out.print("Sicela ufake inani lemali elikhokhelwe:");
						s = in.nextLine();
						receipt.cash(Double.parseDouble(s));
						validation = true;
					
					} catch(Exception e) {
						System.out.println("Okokufaka okungalungile, sicela ufake inani elifanele.");
					}
				}
				validation = false;
						
				c = Double.toString(Double.parseDouble(s) - totalCost);

				System.out.println("Inani loshintsho oludingekayo = " + dollar_symbol + c);

				c = Double.toString(balance + totalCost);
				
				while (!recieptEnd) {

					System.out.println("Ungathanda ikhophi ye-reciept yakho? (y/n)");
					proceed = in.nextLine();
					
					if (proceed.equalsIgnoreCase("y")) {
						System.out.println(receipt.printReciept());
						recieptEnd = true;
					}
					else if(proceed.equalsIgnoreCase("n")) {
						recieptEnd=true;
					}
					else {
						System.out.println("Okokufaka okungalungile. Ngicela uzame futhi");
					}
				}
				

			}

			else if (proceed.equalsIgnoreCase("n")) {
				System.out.println("Ibhalansi Yerejista Yemali: " + dollar_symbol + c);
				endProgram = true;
			}

			else {
				System.out.println("Okokufaka okungalungile. Ngicela uzame futhi");
			}

		}

	}
}

