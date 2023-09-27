public class TestQuickSort
{


	public static void main(String[] args)
	{

		
		try
		{
		/* Selection Sort Tests */
            String worstCase =   "a ab abc abcd abcde";             /* The worst-case scenario reverse order */
            String bestCase =    "edcba dcba cba ba a";            /* The best-case scenario ordered array */
            String averageCase = "abc abd ace bcd bc";            /* The average case scenario */
           
            myMap testMap = new myMap(averageCase);
            System.out.printf("The original (unsorted) map:\n");
			testMap.printMap( );

			long start = System.currentTimeMillis(); /* Start clock */
			QuickSort quickSortObj = new QuickSort(testMap);
			myMap quickSortedMap = quickSortObj.getSortedMap( );
			System.out.printf("Quick sorted map:\n");
			quickSortedMap.printMap( );

			System.out.printf("\n\n\n");
			long end = System.currentTimeMillis();
            double executionTime = (end - start) / 1000.0;
            System.out.println("Quick Sort Execution time: " + String.format("%.4f",executionTime) + " seconds"); 
			
            System.out.printf("\n\n\n");
			/* Input Ordering control */
            String input = "'Hush, hush!' whispered the rushing wind.";;
            myMap hashmap = new myMap(input);	
			System.out.printf("The original (unsorted) map:\n");
			hashmap.printMap( );
            System.out.printf("\n\n Quick Sorted Map\n");
            QuickSort QuickSortHash = new QuickSort(hashmap);
         	myMap sortedHash = QuickSortHash.getSortedMap( );
         	sortedHash.printMap( );
		}
		catch(IllegalArgumentException exception)
		{
			System.out.printf("Error: An exception occured %s",exception.getMessage( ));
		}


	}

}