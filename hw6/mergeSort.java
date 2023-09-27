import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class mergeSort
{
	private LinkedHashMap<String, info> map;
	private myMap originalMap;
	private myMap sortedMap;

	public mergeSort(myMap mapObject)
	{
		originalMap = mapObject;
		map = mapObject.getMap( );

		sortedMap = new myMap( );
		sortedMap.setStr(originalMap.getStr( ));
		sortedMap.setMapSize(originalMap.getMapSize( ));
		mergeSortMap();
	}
	/**
	 * @return Returns the sortedMap 
	 */
	public myMap getSortedMap( )
	{
		return sortedMap;
	}
	/**
	 *@return Returns originalMap;
	 */ 
	public myMap getUnsortedMap( )
	{
		return originalMap;
	}

	/**
	 *@return Returns the sorted LinkedHashMap
	 */
	public LinkedHashMap<String, info> getSortedLinkedHash( )
	{
		return sortedMap.getMap( );
	}

	/**
	 * It finds the index of the key in the order that we have used to build myMap 
	 * @param entry An entry whose index is going to be found in the unsorted map
	 * 
	 */
	public int getIndex(Map.Entry<String, info> entry)
	{
		int index = 0;
		
		for (Map.Entry<String, info> mapEntry : map.entrySet( )) 
		{
        	if( mapEntry.getKey( ) == entry.getKey( ))
        		return index;
        	
        	index++;
        }

		return -1;
	}
	/**
	 * This method control which entry has seen first according to the input order.
	 * @param entry1 The first entry that is taken as a Left entry from sort method.
	 * @param entry2 The second entry that is taken as a Right entry from sort method.
	 * @return Returns true if entry1's key has seen first, false otherwise.
	 */
	public boolean hasSeenBefore(Map.Entry<String,info> entry1, Map.Entry<String,info> entry2)
	{
		int size = 0;
		boolean value = false;
		int counter = 0;
		
		String [] words1 = entry1.getValue( ).getWords( );
		String [] words2 = entry2.getValue( ).getWords( );
		int size1 = words1.length;
		int size2 = words2.length;

		if(size1 <= size2)
			size = size1;
		else 
			size = size2;

		for(int i = 0 ; i < size; i++)
		{
			if(words1[i].equals(words2[i]))
			{
				counter++;
			}
			else 
			{
				int indexOfword1 = getIndex(entry1);
				int indexOfword2 = getIndex(entry2);
				if(indexOfword1 < indexOfword2)	
					value = true;
				
				else 
					value = false;
				
				
				break;
			}
		}
		if(counter == size)
			value = true;
		
		return value;
	}
	/**
	 * This method will invoke mergeSort method to sort myMap, it will give 0 as starting index of ArrayList
	 * and size( ) - 1 as an ending of ArrayList.
	 * After that it will create another map with sorted entries
	 */
	
	public void mergeSortMap( )
	{	
		ArrayList<Map.Entry<String,info>> elements = new ArrayList<>(map.entrySet( ));

		mergeSort(elements, 0, elements.size( ) - 1);
		
		
		LinkedHashMap<String, info> sortedHash = new LinkedHashMap<String, info>( );

		for (Map.Entry<String, info> mapEntry : elements) 
		{
   	    	sortedHash.put(mapEntry.getKey(), mapEntry.getValue());
        }
		
		sortedMap.setMap(sortedHash);
	}
	/**
	 * A recurive method to sort map, it will put the sorted entries into ArrayList, In each recursive
	 * call the middle point will be calculated and two recursive call will follow it. The first recursive call
	 * will search leftsub array and the other one will search right subarray. In the end, obtained subarrays will be sorted by
	 * calling sort method and we will obtain sorted map entries 
	 * @param elements An ArrayList that contains the entries of map
	 * @param left index of left part of the data container which is the leftmost index.
	 * @param right index of the right part of the data container which is the rightmost index.
	 * 
	 */
	public void mergeSort(ArrayList<Map.Entry<String,info>> elements, int left, int right)
	{
		if(left < right)
		{
			int middle = (left + right)/ 2;
		
			mergeSort(elements, left, middle);
			mergeSort(elements, middle + 1, right);
			sort(elements, left, middle, right);
		}
		
	}
	/**
	 * This method will sort the obtained sub array. We will obtain string 2 elements sub-strings, from
	 * recursive mergeSort and first it will sort these 2 element sub-arrays. After that it will sort one level higher(3 elements)
	 * sub-arrays and will go like this. At the end it will merge the sorted strings and find the sorted map.
	 * This method will sort map according to the count values of entries, in case of count values are the same, it will consider the input order of
	 * key and give the priority the key that has been taken first
	 * @param elements An ArrayList that contains the entries of map
	 * @param left left index of left part of the data container which is the leftmost index.
	 * @param middle middle index of the array
	 * @param right index of the right part of the data container which is the rightmost index.
	 */

	public void sort(ArrayList<Map.Entry<String,info>> elements, int left, int middle, int right)
	{

		int size1 = (middle - left) + 1; /* Size to represent left subarray*/
		int size2 = (right - middle);    /* Size to represent right subarray */
		ArrayList<Map.Entry<String,info>> LeftPart = new ArrayList<Map.Entry<String,info>>();
		ArrayList<Map.Entry<String,info>> RightPart = new ArrayList<Map.Entry<String,info>>();
		/* Fill the ArrayLists */
		for(int i = 0; i < size1; i++)
			LeftPart.add(elements.get(left + i));

		for(int i = 0; i < size2; i++)
			RightPart.add(elements.get(middle + i +1));

		int left_counter = 0;
		int right_counter = 0;
		int index;
		for(index = left; left_counter < LeftPart.size( ) && right_counter < RightPart.size( ); index ++)
		{
			
			info leftElement = LeftPart.get(left_counter).getValue( );
			info rightElement = RightPart.get(right_counter).getValue( );
			 

			if(leftElement.getCount( ) < rightElement.getCount( ))
			{
				Map.Entry<String, info> LeftEntry = LeftPart.get(left_counter);
				elements.set(index, LeftEntry);
				left_counter++;			
			}
			else if(leftElement.getCount( ) > rightElement.getCount( )) 
			{
				Map.Entry<String, info> rightEntry = RightPart.get(right_counter);
				elements.set(index, rightEntry);
				right_counter++;
			}
			else
			{
				if(hasSeenBefore(LeftPart.get(left_counter), RightPart.get(right_counter)) == true)
				{
					
					Map.Entry<String, info> LeftEntry = LeftPart.get(left_counter);
					elements.set(index, LeftEntry);
					left_counter++;	
				}
				else 
				{	
					Map.Entry<String, info> rightEntry = RightPart.get(right_counter);
					elements.set(index, rightEntry);
					right_counter++;
				}
			}
		}

		/* Put the rest(unsorted) of the elements to the array */
		while(left_counter < LeftPart.size( ))
		{
			elements.set(index, LeftPart.get(left_counter));
			index++;
			left_counter++;
		}

		while(right < RightPart.size( ))
		{
			elements.set(index, RightPart.get(right_counter));
			index++;
			right_counter++;
		}
	}






}