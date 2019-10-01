import java.util.ArrayList;
import java.util.Scanner;

public class loyaltyProgram {

	//Array list containing the user info (Membership no, name, balance)
	private ArrayList<String[]> users = new ArrayList<String[]>();
	private String confirmation;
	private String loyaltyNumber;
	private Scanner in = new Scanner(System.in);
	private boolean validation = false;
	private String custName;

	/**
	 * Constructor to create 'dummy' accounts
	 */
	public loyaltyProgram() {

		String[] infoTin = { "000000", "Tin Nguyen", "1340" };
		String[] infoAnthony = { "000001", "Anthony Nguyen", "29480" };
		String[] infoBijay = { "000002", "Bijay Chamlagain", "10" };

		users.add(infoTin);
		users.add(infoAnthony);
		users.add(infoBijay);

	}

	/**
	 * The method that starts off the loyalty program prompts
	 * @param totalPrice The total price of the transaction
	 * @return The membership number
	 */
	public String loyaltyPrompy(double totalPrice) {

		validation = false;
		System.out.println("Do you have a UniSA Loyalty Card? (Y - Yes | C - Create | Enter - Skip)");
		while (!validation) {

			confirmation = in.nextLine();

			//If user selects yes, it will go to the yes loyalty method
			//If user selects c, it will go the create user method
			//If user presses enter, the program exits
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

	/**
	 * Method to process the user's loyalty account
	 * @param totalPrice The total price of the transaction
	 * @return the membership number
	 */
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

		//If the loyalty number exists, the balance is updated
		//If not, user will be prompted to create an account 
		if (checkUser(loyaltyNumber)) {
			updatePointBalance(loyaltyNumber, totalPrice);
			return getUserAccount(loyaltyNumber);
		} else if (checkUser(loyaltyNumber) == false) {
			
			System.out.println("That account does not exist."
					+ " Would you like to create a new loyatly account? (Y - Yes | Enter - Skip)");
			
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

	/**
	 * Method to update the user's point balance
	 * @param userNo The membership number
	 * @param totalPrice The total price of the transaction
	 */
	public void updatePointBalance(String userNo, double totalPrice) {
		String[] userInfo = getUserNumber(userNo);

		String points = userInfo[2];
		double newPoints = Integer.parseInt(points) + Math.floor(totalPrice / 10);
		userInfo[2] = Double.toString(newPoints);

		//Prints out the message confirming the user's balance has been updated.
		System.out.println("Customer: " + getUserNumber(userNo)[1]);
		System.out.println(newPoints +" Loyalty points have been added. \nCurren balance: " + getUserNumber(userNo)[2] +"\n");

	}

	/**
	 * @param userNo Membership number
	 * @return The balance of the user
	 */
	public String getPointBalance(String userNo) {
		String[] userInfo = getUserNumber(userNo);

		return userInfo[2];
	}

	/**
	 * @param userNo Membership number
	 * @return The user's loyalty account information
	 */
	public String[] getUserNumber(String userNo) {

		//Loops through the array of users
		for (int i = 0; i < users.size(); i++) {

			String[] userInfo = users.get(i);

			if (userInfo[0].equalsIgnoreCase(userNo)) {
				return userInfo;
			}

		}

		System.out.println("Not found");
		return null;
	}

	
	/**
	 * Returns the user's membership number
	 * @param userNo Number of the user
	 * @return membership number
	 */
	public String getUserAccount(String userNo) {
		for (int i = 0; i < users.size(); i++) {

			String[] userInfo = users.get(i);

			if (userInfo[0].equalsIgnoreCase(userNo)) {
				return userInfo[0];
			}

		}

		return null;
	}

	/**
	 * Checks if user exists or not
	 * @param userNo The membership number of the user
	 * @return a boolean value
	 */
	private boolean checkUser(String userNo) {

		for (int i = 0; i < users.size(); i++) {

			String[] userInfo = users.get(i);

			if (userInfo[0].equalsIgnoreCase(userNo)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Creates a new account for the user
	 * @param totalPrice The total price of the transaction
	 * @return The user's membership number
	 */
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

		//The code below takes the last membership number in the system
		//and then increments it by 1
		int loyaltyNumberInt = Integer.parseInt(newLoyaltyNumber) + 1;

		String[] newUser = { String.format("%06d", loyaltyNumberInt), custName, "0" };

		//adds the new user to the list of users
		users.add(newUser);

		System.out.println("New account has been created");

		updatePointBalance(newUser[0], totalPrice);

		return newUser[0];
	}

}

