package homework4;

public class TestClass 
{
	
	public static void test(String _username, String _password1, int _password2, int [] denominators)
	{
		
		//int [] denominators = {4, 17, 29};

		username username = new username(_username);
		password1 password1 = new password1(_password1);
		password2 password2 = new password2(_password2);
		if(username.checkIfValidUsername(username.getUsername( )) == true && username.containsUserNameSpirit(username.getUsername( ), password1.getPassword1( )) == true)
		{
			if(password1.isBalancedPassword(password1.getPassword1( )) == true && password1.isPalindromePossible(password1.getPassword1( )) == true)
			{
				if(password2.isExactDivision(password2.getPassword2( ), denominators) == true)
				{
					System.out.printf("The username and passwords are valid. The door is opening, please wait...\n");
				}

			}
			else 
				return;
			
		}
		else 
			return;
	
	}
	public static void main(String []args)
	{
		
		int [] denominators = {4, 17, 29};

		/* Test 1 */
		String username = "sibelgulmez";
		String password1 = "[rac()ecar]";
		int password2 = 74; 
		test(username, password1, password2, denominators); /* This will open the door succesfully */
		
		/* Test 2 */
		username = "";
		password1 = "[rac()ecar]";
		password2 = 35; 
		test(username, password1, password2, denominators); // Invalid username at least 1 character

		/* Test 3 */
		username = "sibel";
		password1 = "[rac()ecar]";
		password2 = 35; 
		test(username, password1, password2, denominators); // The password2 is invalid. It is not compatible with the denominations

		/* Test 4*/
		username = "sibel1";
		password1 = "[rac()ecar]";
		password2 = 35; 
		test(username, password1, password2, denominators); // Username invalid it must include only letters

		/* Test 5 */
		username = "sibel";
		password1 = "pass[]";
		password2 = 74; 
		test(username, password1, password2, denominators); //The password is invalid. It should have at least 8 characters.

		/* Test 6 */
		username = "sibel";
		password1 = "abcdahed";
		password2 = 74; 
		test(username, password1, password2, denominators); // The password1 is invalid. It should have at least 2 brackets.


		/* Test 7 */
		username = "sibel";
		password1 = "[(){{}}]";
		password2 = 74; 
		test(username, password1, password2, denominators); // The password is invalid. It should have letters too


		/* Test 8 */
		username = "sibel";
		password1 = "(no)(no)";
		password2 = 74; 
		test(username, password1, password2, denominators); // The password1 is invalid. It should have at least 1 character from the username

		/* Test 9 */
		username = "sibel";
		password1 = "[rac()ecar))";
		password2 = 74; 
		test(username, password1, password2, denominators); // The password1 is invalid. The paranthesis should be balanced

		/* Test 10 */
		username = "sibel";
		password1 = "[rac()ecars]";
		password2 = 74; 
		test(username, password1, password2, denominators); // The password1 is invalid. It should be possible to obtain a palindrome from the post


		/* Test 11 */
		username = "sibel";
		password1 = "[rac()ecar]";
		password2 = 5; 
		test(username, password1, password2, denominators); // The password2 is invalid. It should be between 10 and 10,000


		/* Test 11 */
		username = "sibel";
		password1 = "[rac()ecar]";
		password2 = 35; 
		test(username, password1, password2, denominators); // The password2 is invalid. It is not compatible with the denominations.

		/* END */


	}

}