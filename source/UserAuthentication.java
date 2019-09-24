import java.util.Scanner;

public class UserAuthentication 
{
	
	Scanner input = new Scanner(System.in);
	String user_input;
	boolean validation = false;
	double robot_check = 0;

	public void UserAuthentication()
	{
	
		System.out.println("Warning, the following cash register is protected.");
		System.out.println("Please select one of the following options from the menu: ");
		System.out.println("L - Login / S - Sign_up / E - Exit");
		while (!validation)
		{
			user_input = input.nextLine();
			if (user_input.equalsIgnoreCase("E"))
			{
				exit();
				validation = false;
			}
			if (robot_check >= 3)
			{
				System.out.println("Too many failed attempts. You are suspected of being a robot");
				System.out.println("You will now be locked out of the system.");
				robot_check = 0;
				exit();
				validation = false;
			}
			else 
			{
				System.out.println("Invalid Input. Please try again");
				robot_check += 1;
				System.out.println("Failed Attempts: " + robot_check);
			}
		}
	}
	public void exit()
	{
		System.out.println("We hope to see you next time, bye for now");
		System.exit(0);
		
	}
	public void userFabrication()
	{
		
	}
}
