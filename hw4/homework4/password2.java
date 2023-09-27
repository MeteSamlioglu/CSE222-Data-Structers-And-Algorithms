package homework4;

public class password2
{

	private int password2;


	public password2(int _password2 )
	{

		password2 = _password2;
	}
	/**
	 * @return Returns password2
	 */
	public final int getPassword2( )
	{

		return password2;
	} 
	/**
	 * Sets password2
	 * @param _password an integer password that is going to be setted 
	 */
	public void setPassword2(int _password)
	{
		password2 = _password;
	
	}
		/**
	 * This method checks if password2 satisfies the range rule first, after that provekes the overloaded method 
	 * and checks if we can obtain a password2 by summing and multiplying coeffs with arbitary integers	  
	 *@param password2 A integer password which is inbetween [10, 1000]
	 *@param denominations A set of integer which is going to multiply with arbitary coeffs.
	 *@return This function makes a recursive call to overloaded recursive isExactDivision function, and it returns the result
	 */
	public boolean isExactDivision(int password2, int[] denominations)
	{
		if( !(password2 > 10 && password2 < 10000))
		{
			System.out.printf("The password2 is invalid, password2 must be in between (10, 10000). Try again\n");
			return false;
		}
		else 
		{
			boolean isDividible = isExactDivision(password2, denominations, 0);
			
			if(isDividible == false)
				System.out.printf("The password2 is invalid, it is not possible to obtain the password2 by the summation of denominations(with arbitrary coeffs). Try again\n");
		
			return isDividible;
		}	
	}

	/**
	 * The function determines if it is possible to obtain 
	 * the password by the summation of denominations along with arbitrary coefficients, which are non-negative integers
	 * @param password2 An integer password that is inbetween [10, 1000]
	 * @param denominations An integer array that contains denominations for given password2.
	 * @return true, if we can get the integer password2 by multiplying denominatons with different coeffs and summing afterwards, false otherwise.
	 */
	public boolean isExactDivision(int password2, int[] denominations, int index) 
	{
	    
	    boolean first_recursion_result;
	    /* Base case : If the sum of multiplication denominations with arbitary coeffs equals to password this if block will be activated and retunr true*/
	    if (password2 == 0) 
	    {    
	        return true;  
	    }
	    /* Base case: It means that search is not successfull for particular denominator*/
	   
	    if (password2 < 0 || index >= denominations.length ) 
	    {
	   
	        return false;  /* Solution is not exist */
	   
	    }
	    /* The very first case is to check, if we can find the solution subtracting the very first denomination in array */
	   
	    int substracted_password = password2 - denominations[index];
	   
	    first_recursion_result = isExactDivision(substracted_password, denominations, index);
	   
	    if (first_recursion_result == true) /* If the first recursion returns true which means that by subtracting the coeff we found the solution */
	    {
	        return true;
		}
	    
		return isExactDivision(password2, denominations, index + 1);
	}


}