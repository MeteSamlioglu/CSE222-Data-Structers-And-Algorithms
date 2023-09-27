
public class info
{
	/* Data fields */
	private int count;	
	private String[] words;
 	/**
 	 * A default constructor that will create an info object with the initial values count = 0, and words[1]
 	 */
 	public info( )
 	{
 		count = 0;
 		words = new String[1];
 	}

	/**
	 * This method will add a new string to the string container words.
	 * @param word A string that is going to be pushed into words array
	 * 
	 **/
	public void push(String word)
	{
		if(count == 0)
		{
			words[count] = word;
			count++;
		}
		else
		{
			String [] copyArray = new String[count + 1];
			for(int i = 0; i < count; i++)
			{
				copyArray[i] = words[i];
			}
			copyArray[count] = word;
			count++;
			words = copyArray;
		}
	}	
	/**
	 * @return Returns the string array that contains the words that has been pushed.
	 */ 
	public String[] getWords( )
	{
		return words;
	}
	/**
	 *@return Returns the number of words that has been pushed to the string array words.
	 */
	public int getCount( )
	{
		return count;
	}
}