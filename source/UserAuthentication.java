import java.util.Scanner;

/**
 * @author Anthony N.
 *
 */

public class UserAuthentication 
{
	Scanner input = new Scanner(System.in);
	String user_input;
	boolean validation = false;
	boolean flag = false;
	double robot_check = 0;
	String username;
	String password;
	private String current_user;
	String current_password;
	profile[] profileList = new profile[0];

	/**
	 * Constructor for the UserAuthentication class.
	 * 
	 */
	public void UserAuthentication()
	{
		while (validation == false)
		{
			// Program begins with the following prompts
			System.out.println("");
			System.out.println("==========================================================");
			System.out.println("Warning, the following cash register is protected.");
			System.out.println("Please select one of the following options from the menu: ");
			System.out.println("L - Login / S - Sign_up / G - Guest / E - Exit");
			
			user_input = input.nextLine();
			
			// When user selects "L", program will proceed to login operations.
			if (user_input.equalsIgnoreCase("L"))
			{
				login();
				if (flag == true)
				{
					validation = true;
				}
			}
			
			// When user selects "S", program will proceed to signing up operations.
			if (user_input.equalsIgnoreCase("S"))
			{
				System.out.println("Lets create a new user login for you");
				signUp();
				System.out.println("Congratulations, you have successfully become a new user of this cash register");
				System.out.println("Please login with your new username and password");
				continue;
			}
			
			// When user selects "G", program will proceed to allocate user with guest username.
			if (user_input.equalsIgnoreCase("G"))
			{
				setCurrent_user("Guest");
				System.out.println("Logging on as Guest");
				validation = true;
			}
			
			// When user selects "E", program will close.
			if (user_input.equalsIgnoreCase("E"))
			{
				exit();
			}
			
			// Too many failed attempts will result in a program shutdown.
			if (robot_check >= 3)
			{
				System.out.println("Too many failed attempts. You are suspected of being a robot.");
				System.out.println("You will now be locked out of the system.");
				robot_check = 0;
				exit();
			}
			
			// Used for testing purposes. Displays existing users in the array.
			if (user_input.equalsIgnoreCase("Admin"))
			{
				System.out.println(toString());
				continue;
			}
			
			if (validation == false)
			{
				robotCheck();
			}
			
		}
		System.out.println("==========================================================");
		System.out.println("");
	}
	
	/**
	 * Method that exits the program.
	 * 
	 */
	public void exit()
	{
		System.out.println("We hope to see you next time, bye for now!");
		System.exit(0);
	}
	
	/**
	 * Method that validates the current user and associated password.
	 * 
	 */
	public void login() 
	{
		boolean found_user = false;
		String correct_password = "123";
		System.out.println("Please enter username: ");
		this.current_user = input.nextLine();
		int next;
		for (next = 0; next < profileList.length; next++)
		{
			if (profileList[next].getUsername().equals(current_user))
			{
				System.out.println("");
				System.out.println("User found in system");
				System.out.println("");
				correct_password = profileList[next].getPassword();
				found_user = true;
			}
		}
		if (found_user == false)
		{
			System.out.println("User not found, suspected robot");
		}
		
		if (found_user == true)
		{
			System.out.println("Please enter password: ");
			this.current_password = input.nextLine();
			if (current_password.equals(correct_password))
			{
				System.out.println("Login Successful");
				flag = true;
			}
			else
			{
				System.out.println("Invalid Password");
			}
		}
	}
	
	/**
	 * Method that adds the new username and password to the list. 
	 * 
	 */
	public void signUp()
	{
		System.out.println("Please enter new username: ");
		this.username = input.nextLine();
		System.out.println("Please enter new password: ");
		this.password = input.nextLine();
		profile newProfile = new profile(username, password);
		userFabrication(newProfile);
		System.out.println(newProfile.toString());
	}
	
	/**
	 * Method that keeps count of the number of failed attempts.
	 * 
	 */
	public void robotCheck()
	{
		System.out.println("Invalid Input. Please try again.");
		robot_check += 1;
		System.out.println("Failed Attempts: " + robot_check);
	}
	
	/**
	 * This method adds the new profile to the list.
	 * 
	 * @param newprofile to be added to the list.
	 */
	public void userFabrication(profile newprofile)
	{
		profile[] newProfileList = new profile[profileList.length + 1];
		int next;
		for (next = 0; next < profileList.length; next++)
			{
				newProfileList[next] = profileList[next]; 
			}
		profileList = newProfileList;
		profileList[profileList.length - 1] = newprofile;
	}
	
	/**
	 * A modified toString method that prints out the profile list.
	 * 
	 * @return The string of the current list
	 */
	public String toString() 
	{
		String value = "";
		for (int i = 0; i < profileList.length; i++) 
		{
			value += profileList[i].toString()+"\n";
		}
		return value;
	}
	public String getCurrent_user() 
	{
		return current_user;
	}
	
	/**
	 * Set the current user.
	 * 
	 * @param current_user
	 */
	public void setCurrent_user(String current_user)
	{
		this.current_user = current_user;
	}

}

class profile
{
	String username;
	String password;
	
	/**
	 * Constructor for the profile class. Initialises the username and
	 * password for the current profile.
	 * @param username
	 * @param password
	 */
	
	public profile(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	
	public String toString() 
	{
		return "New User Profile [name = " + username + ", password = " + password + "]";
	}
}
