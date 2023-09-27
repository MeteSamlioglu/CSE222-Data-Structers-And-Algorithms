import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class myMap
{

	/* Data fields */
	private LinkedHashMap<String, info> map; /*It uses string as key, class info as value */
	private int mapSize; /* size of the map */
	private String str;  /* A string that has been taken by user */

	/**
	 * A default constructor that takes an input string, process it, builds a map and in the end sorts the entries 
	 * @throws IllegalArgumentException It throws exception, whenever method preprocessString or buildMapStructer throws exception.
	 */ 
	public myMap(String input) throws IllegalArgumentException
	{	
		/* Take input from user */
		map = new LinkedHashMap<String, info>( );		
		System.out.printf("Original String: %s\n",input);
		preprocessString(input);
		System.out.printf("Preprocessed String: %s\n\n\n",str);
		buildMapStructer(str);
	}
	
	public myMap( )
	{
		str = "";
		mapSize = 0;

	}
	/**
	 * @return Returns the current LinkedHashMap
	 */
	public LinkedHashMap<String, info> getMap( )
	{
		return map;
	}
	/**
	 * Sets the LinkedHashMap 
	 */
	public void setMap(LinkedHashMap<String, info> _map)
	{
		map = _map;
	}
	/**
	 * Sets the mapSize
	 */
	public void setMapSize(int val)
	{
		mapSize = val;
	}
	/**
	 * @return Returns the mapSize
	 */
	public final int getMapSize()
	{
		return mapSize;
	}
	/**
	 *@return Returns str
	 */
	public final String getStr( )
	{
		return str;
	
	}
	/**
	 * Sets the STR
	 */
	public void setStr(String _str)
	{
		str = _str;
	}

	/**
	 * A method to process string before pushing it to class Object
	 * @param str A string that is going to be preprocessed
	 * @throws IllegalArgumentException Throws IllegalArgumentException whenever given input string is null or empty 
	 */
	public void preprocessString(String text) throws IllegalArgumentException
	{
		if(text == "" || text == null)
			throw new IllegalArgumentException("The input string can not be null or empty.\n");

		text = text.toLowerCase( );
		String editedStr = text.replaceAll("[^a-z\\s]","");
		str = editedStr; 
	}
	/**
	 *  This methods uses the preprocessed string to build a custom map structure(named myMap).
	 *  It basically keepS the number of occurrences of each letter within the string along with the words, that it was seen.
	 *  @param text A string that contains preprocessed words 
	 *  @throws IllegalArgumentException Throws IllegalArgumentException whenever key is either null or empty, 
	 *  and also if key is not a single characer it throws an exception. 
	 */
	public void buildMapStructer(String text) throws IllegalArgumentException
	{
		String[] words = text.split(" ");

		for(String word : words) /* foreach loop for string array */
		{
			char ch;
			for(int index = 0; index < word.length( ); index++)
			{
				ch = word.charAt(index);
				String key = "" + ch;
				if(map.containsKey(key) != true)
				{
					if(key == null || key == "")
						throw new IllegalArgumentException("The key can not be null or empty.\n");
					if(key.length() > 1)
						throw new IllegalArgumentException("The key must be a character not a string.\n");

					info value = new info( );
					map.put(key, value);
					mapSize++;
				}
				map.get(key).push(word); /* Get the value(object) and push the related word */
			    
			}
		}

	}
	/**
	 * This method displays the the LinkedHashMap with the Key Count and the words information.
	 */
	public void printMap( )
	{

		for(String key : map.keySet( ))
		{
			info value = map.get(key);
			System.out.printf("Letter: %s - Count: %d - ",key,value.getCount( ));
		    String [] listWords = value.getWords( );
			System.out.printf("Words: [");
			for(int i = 0 ; i < listWords.length; i++)
			{
				if(i == listWords.length - 1)
					System.out.printf("%s]\n",listWords[i]);	
				else
					System.out.printf("%s, ",listWords[i]);
			
			}
		}
	}
	


}