public class TestClassInsertion
{

	public static void main(String[] args)
	{
		try 
		{

            /* SelectionSort End */
            /* ---------------------------------------------------------------------------- */

            /* Selection Sort Tests */
            String insertionSort_worstCase =   "a ab abc abcd abcde";             /* The worst-case scenario reverse order */
            String insertionSort_bestCase =    "edcba dcba cba ba a";            /* The best-case scenario ordered array */
            String insertionSort_averageCase = "abc abd ace bcd bc";            /* The average case scenario */
           
            myMap testInsertion = new myMap(insertionSort_averageCase);
            System.out.printf("The original (unsorted) map:\n");
			testInsertion.printMap( );

			long insertionStart = System.currentTimeMillis(); /* Start clock */
			InsertionSort insertionSort = new InsertionSort(testInsertion);
			myMap insertionSortedMap = insertionSort.getSortedMap( );
			System.out.printf("Insertion sorted map:\n");
			insertionSortedMap.printMap( );

			System.out.printf("\n\n\n");
			long insertionEnd = System.currentTimeMillis();
            double executionTimeSelection = (insertionEnd - insertionStart) / 1000.0;
            System.out.println("InsertionSort Execution time: " + String.format("%.4f",executionTimeSelection) + " seconds"); 
			
			/* Input Ordering control */
            String input = "'Hush, hush!' whispered the rushing wind.";;
            myMap hashmap = new myMap(input);	
			System.out.printf("The original (unsorted) map:\n");
			hashmap.printMap( );
            System.out.printf("\n\n\nInsertion Sorted Map\n");
            InsertionSort InsertionSortHash = new InsertionSort(hashmap);
         	myMap sortedHash = InsertionSortHash.getSortedMap( );
         	sortedHash.printMap( );

		}

		catch(IllegalArgumentException exception)
		{
			System.out.printf("Error: An exception occured %s",exception.getMessage( ));
		}

	}
}

