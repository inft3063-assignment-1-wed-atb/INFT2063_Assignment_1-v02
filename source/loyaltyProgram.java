import java.util.ArrayList;
import java.util.Scanner;

public class loyaltyProgram {

	private ArrayList<String[]> users = new ArrayList<String[]>();
	private String confirmation;
	private String loyaltyNumber;
	private Scanner in = new Scanner(System.in);
	private boolean validation = false;
	private String custName;

	public loyaltyProgram() {

		String[] infoTin = { "000000", "Tin Nguyen", "1340" };
		String[] infoAnthony = { "000001", "Anthony Nguyen", "29480" };
		String[] infoBijay = { "000002", "Bijay Chamlagain", "10" };

		users.add(infoTin);
		users.add(infoAnthony);
		users.add(infoBijay);

	}

	public String loyaltyPrompy(double totalPrice) {

		validation = false;
		System.out.println("Do you a UniSA Loyalty Card? (Y - Yes, C - Create, Enter - Skip)");
		while (!validation) {

			confirmation = in.nextLine();

			if (confirmation.equalsIgnoreCase("y")) {
				validation = true;
				return yesLoyalty(totalPrice);
			} else if (confirmation.equalsIgnoreCase("c")) {
				validation = true;
				return createUser(totalPrice);
			} else if (confirmation.equals("")) {
				return null;
			} else {
				System.out.println("Invalid input, please enter an appropiate value.");
			}
		}

		return null;
	}

	public String yesLoyalty(double totalPrice) {

		validation = false;
		while (!validation) {

			try {
				System.out.println("Please enter Loyalty Card Number:");
				loyaltyNumber = in.nextLine();
				validation = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please enter an appropiate value.");
				in.next();
			}
		}

		if (checkUser(loyaltyNumber)) {
			updatePointBalance(loyaltyNumber, totalPrice);
			return getUserAccount(loyaltyNumber);
		} else if (checkUser(loyaltyNumber) == false) {
			
			System.out.println("That account does not exist."
					+ " Would you like to create a new loyatly account? (Y - Yes, Enter - Skip)");
			
			validation = false;
			while (!validation) {

					confirmation = in.nextLine();
					
					if (confirmation.equalsIgnoreCase("y")) {
						validation = true;
						return createUser(totalPrice);
					}
					
					else if (confirmation.equalsIgnoreCase("")) {
						return null;
					}
					
					else {
						System.out.println("Invalid inpit, please enter an appropiate value.");
					}
					
				} 
			}

		return null;
	}

	public void updatePointBalance(String userNo, double totalPrice) {
		String[] userInfo = getUserNumber(userNo);

		String points = userInfo[2];
		double newPoints = Integer.parseInt(points) + Math.floor(totalPrice / 10);
		userInfo[2] = Double.toString(newPoints);

		System.out.println("Customer: " + getUserNumber(userNo)[1]);
		System.out.println("Loyalty points have been added. Balance: " + getUserNumber(userNo)[2]);

	}

	public String getPointBalance(String userNo) {
		String[] userInfo = getUserNumber(userNo);

		return userInfo[2];
	}

	public String[] getUserNumber(String userNo) {

		for (int i = 0; i < users.size(); i++) {

			String[] userInfo = users.get(i);

			if (userInfo[0].equalsIgnoreCase(userNo)) {
				return userInfo;
			}

		}

		System.out.println("Not found");
		return null;
	}

	public String getUserAccount(String userNo) {
		for (int i = 0; i < users.size(); i++) {

			String[] userInfo = users.get(i);

			if (userInfo[0].equalsIgnoreCase(userNo)) {
				return userInfo[0];
			}

		}

		return null;
	}

	private boolean checkUser(String userNo) {

		for (int i = 0; i < users.size(); i++) {

			String[] userInfo = users.get(i);

			if (userInfo[0].equalsIgnoreCase(userNo)) {
				return true;
			}
		}
		return false;

	}

	public String createUser(double totalPrice) {

		validation = false;
		while (!validation) {

			try {
				System.out.println("Please enter the customer's name: ");
				custName = in.nextLine();
				validation = true;

			} catch (Exception e) {
				System.out.println("Invalid inpit, please enter an appropiate value.");
				in.next();
			}

		}

		String[] userInfo = users.get(users.size() - 1);

		String newLoyaltyNumber = userInfo[0];

		int loyaltyNumberInt = Integer.parseInt(newLoyaltyNumber) + 1;

		String[] newUser = { String.format("%06d", loyaltyNumberInt), custName, "0" };

		users.add(newUser);

		System.out.println("New account has been created");

		updatePointBalance(newUser[0], totalPrice);

		return newUser[0];
	}

}
