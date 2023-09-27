public class TestClass
{




	public static void main(String[] args)
	{
		try 
		{
			//String input = "Buzzing bees buzz.";
			String input = "'Hush, hush!' whispered the rushing wind.";
			//String input = "";    /* Empty string Error */
			//String input = null; /* Null string Error */
			
			myMap hashmap = new myMap(input);	
			System.out.printf("The original (unsorted) map:\n");
			hashmap.printMap( );

			mergeSort mergeSortmyMap = new mergeSort(hashmap);
		    myMap sortedMap = mergeSortmyMap.getSortedMap( );	
		    
		    System.out.printf("\n\n");
			System.out.printf("The sorted map:\n");
			sortedMap.printMap( );
		}
		catch(IllegalArgumentException exception)
		{
			System.out.printf("Error: An exception occured %s",exception.getMessage( ));
		}

	}
}

