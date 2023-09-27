package homework4;

import java.util.Stack;
public class username
{

	private String username; 


	public username(String _name)
	{
		username = _name;
	}
	public username( )
	{
		username = "";
	}

	/**
	 * This method sets username;
	 *@param name_ A string name
	 */
	public void setUsername(String _name)
	{
		username = _name;
	}

	/**
	 * @return Returns username
	 */
	public String getUsername( )
	{
		return username;
	}
	 
	 /**
	  * This method only checks the length of the string, and after that calling checkIfValidUsernameHelper
	  * to check only letter condition 
	  * @param username A string username.
	  * @return It makes a recursive call to its helper method checkIfValidUsername and return
	  */
	 public boolean checkIfValidUsername(String username)
	 {
	 	if(username.length( ) < 1)
	 	{	
	 		System.out.printf("The username is invalid, the minimum username length is 1. Try again.\n");
	 		return false;
	 	}
	 	
	 	return checkIfValidUsernameHelper(username);
	 }

	 /**
	 * A recursive function which checks if it contains only letters, and the minimum length is 1. 
	 * @param username  A username of the employee that is going to be check whether it is valid or not
	 * @return Return true if username satisfies the conditions, false otherwise. 
	 */
	public boolean checkIfValidUsernameHelper(String username)
	{
		/* Base case: Is string null or length < 1*/
		if(username == null || username.length( ) < 1)
			return false;
		/* Second base/end case : length == 1 */
		else if(username.length( ) == 1)
		{
			char lastLetter = username.charAt(0);
				/* Check if the ASCII code of letter is inbetween a-z or A-Z */
				if((lastLetter>=65 && lastLetter <= 90) || (lastLetter<=122 && lastLetter>=97))
					return true;
				else
				{ 
					System.out.printf("The username is invalid, the username must contain only letters. Try again.\n");
					return false;
				}
		}
		else
		{
			char checkLetter = username.charAt(0);
			/* Check if the ASCII code of letter is inbetween a-z or A-Z */
			if((checkLetter>=65 && checkLetter<= 90) || (checkLetter<=122 && checkLetter>=97))
				return checkIfValidUsername(username.substring(1));  /*Remove the first element and call the function again */ 
			else
			{ 
				System.out.printf("The username is invalid, the username must contain only letters. Try again.\n");
				return false;
			}
		}
	}
	
	/**
	 * A function which checks if the string password contains at least one letter of the username.
	 * @param username  A string that contains the username of employee
	 * @param password A string password that contains brackets and letters 
	 * @return True if the string password contains at least one letter of the username, false otherwise
	 * */
	public	boolean containsUserNameSpirit(String username, String password1)
	{
		Stack<Character> myStack = new Stack<Character>( );
		char ch;
		boolean isExist = false;
		
		for(int i = 0 ; i < password1.length( ); i++)
		{
			ch = password1.charAt(i);
			if(ch != '(' || ch != ')' || ch != '[' || ch != ']' || ch != '{'  || ch != '}')
			myStack.push(ch);
		}
		for(int index = 0 ; index < username.length( ); index++)
		{
			ch = username.charAt(index);
			if( myStack.search(ch) > 0)
			{
				isExist = true;
				break;
			}
		}
		/* Error print */
		if(isExist == false)
			System.out.printf("The username is invalid, username must include at least one letter of username. Try again.\n");

		return isExist;
	}

}