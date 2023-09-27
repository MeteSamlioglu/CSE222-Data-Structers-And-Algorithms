
package homework4;
import java.util.Stack;
public class password1
{
	private String password1;


	public password1(String _password)
	{
		password1 = _password;

	}

	public password1( )
	{
		password1 = "";
	} 
	 
	/**
	 * @return Returns password1( )
	 * 
	 */
	public final String getPassword1( )
	{
		return password1;
	}
	/**
	 * Sets password1 to some value
	 * @param password A password that is going to be setted
	 */
	public void setPassword1(String password)
	{
		password1 = password;
	}
	 /**
	 * A stack function that checks if paranthesis are balanced or not.It also checks if 
	 * string's length is more than 8, and if it includes at least two brackets.
	 * @param password A string password that contains at least two pair paranthesis.
	 * @return True if paranthesis in password1 are balanced, password lengt is greater or equal 8 and includes at least 2 brackets.False otherwise.
	 */
	public boolean isBalancedPassword(String password1)
	{
		Stack<Character> myStack = new Stack<Character>( ); 
		String password = ""; /* A string that contains no character other than {([])} */
		char ch, topElement;
		boolean isBalanced = true;
		int brackets_count = 0;

		/* Control length of the password */
		if(password1.length( ) < 8)
		{
			System.out.printf("The password1 is invalid, the minimum length of password1 string must be 8. Try again.\n");
			return false;
		}
		for(int i = 0 ; i < password1.length( ); i++)
		{
			ch = password1.charAt(i);
			if(ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch== '{'  || ch == '}')
			{	
				password = password + ch;
				brackets_count++;
			}
		}
		
		if(brackets_count < 2) /* Brackets can not be less than 2 */
		{
			System.out.printf("The password1 is invalid, it must include at least 2 brackets. Try again.\n");
			return false;
		}

		for(int index = 0; index < password.length( ) && isBalanced != false; index ++)
		{
			ch = password.charAt(index); 
			if(ch == '{' || ch == '(' || ch == '[')
				myStack.push(ch);
			else if(ch == '}' || ch == ')' || ch == ']')
			{
				if(myStack.empty( ) == false)
					topElement = myStack.pop( );
				else
				{
					isBalanced = false;
					break;
				} 
				switch(topElement)
				{
					case '{':
						if(ch != '}')
							isBalanced = false;
						break;
					case '[':
				 		if(ch != ']')
							isBalanced = false;
						break;
					case '(':
						 if(ch != ')')
							isBalanced = false;
						break;
					default:
							isBalanced = false;
							break;
				}
			}
		}
		
		if(isBalanced == false)
		
			System.out.printf("The password1 is invalid, the brackets are not balanced. Try again.\n");

		return isBalanced;
	}
	/**
	 *  In the given string sequence, the function considers if it is possible to obtain a palindrome by rearranging the letters 
	 *  in the string.
	 *  @param password1 A string whose letters going to be checked for producing a palindrome.
	 *  @return Returns true if it is possible to obtain a palindrome out of string(password1), false otherwise.
	 */
	boolean isPalindromePossible(String password1)
	{
		/* Remove brackets from string */	
		char ch;
		String removeBrackets = "";
		int letters[] = new int[52]; /* An array for the letters a-z  A-Z , from 0 to 25 it will containt a-z after that A-Z */
		
		for(int i = 0; i < 52 ; i++) /* Initialize array with all zeros */
			letters[i] = 0;

		for(int i = 0 ; i < password1.length( ); i++ )
		{
			ch = password1.charAt(i);
			if( ch != '{' && ch !='(' && ch != '[' && ch != ')' && ch !=']' && ch != '}')
				removeBrackets = removeBrackets + ch;
		}
		

		return	isPalindromePossible(removeBrackets, 0, removeBrackets.length( ), letters);
	}
	/**
	 * A recursive function that checks the frequency of the occurence in strig.
	 * @param password1 a password string that is going to be checked
	 * @param length length of the string 
	 * @param lettersCount an array that will store the frequency of each letter(from a-z  A-Z ) in string
	 * @return Returns true, if a string can be rearranged to be palindrome, false otherwise 
	 * */
	boolean isPalindromePossible(String password1, int index, int length, int [] lettersCount)
	{
		/* Base case: All the elements in string has been searched,and number of letter occurences in string are added to lettersCount array*/
		if(index == length)
		{	
			int OddOccurence = 0;
			boolean isPalindrome = true;
			for(int i = 0; i < 52 ; i++) /* A loop that will check the freqeuncy of letters in string */
			{
				if( lettersCount[i]!=0 && lettersCount[i] % 2 != 0)
					OddOccurence++;		
				if( (password1.length( ) % 2 == 0)  && OddOccurence > 0) /* If length of the string is even, odd occurence can not be exist */
				{
					System.out.printf("The password1 is invalid due to the palindrome not possible. Try again.\n");
					isPalindrome = false;
					break;
				}
				else if((password1.length( ) % 2 != 0) && OddOccurence > 1) /* If length of the string is even, only one odd occurence can be exist */
				{ 
					System.out.printf("The password1 is invalid due to the palindrome not possible. Try again.\n");
					isPalindrome = false;
					break;
				}
			}

			return isPalindrome;
		}
		else
		{
			char ch = password1.charAt(index);
			int index_of_letter; 
			
			if(ch > 90) /* If the letter inbetween a-z */
				index_of_letter = (int)ch - 97; 
			else /* A-Z  */
				index_of_letter = (int)(ch - 65) + 26; /* The uppercase letters will start right after where lower case letters are ended in the array */
			
			lettersCount[index_of_letter] = lettersCount[index_of_letter] + 1; /* Increase the number of occurences for that letter in string */
			return isPalindromePossible(password1, index + 1, length, lettersCount);
		}
	}



}