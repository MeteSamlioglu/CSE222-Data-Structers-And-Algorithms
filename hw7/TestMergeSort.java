

public class TestMergeSort
{

		public static void main(String[] args)
		{

			try{
				/* Selection Sort Tests */
	            String worstCase =   "a ab abc abcd abcde";             /* The worst-case scenario reverse order */
	            String bestCase =    "edcba dcba cba ba a";            /* The best-case scenario ordered array */
	            String averageCase = "abc abd ace bcd bc";            /* The average case scenario */
	           
	            myMap testMap = new myMap(averageCase);
	            System.out.printf("The original (unsorted) map:\n");
				testMap.printMap( );

				long start = System.currentTimeMillis(); /* Start clock */
				MergeSort mergeSortObj = new MergeSort(testMap);
				myMap mergeSortedMap = mergeSortObj.getSortedMap( );
				System.out.printf("Insertion sorted map:\n");
				mergeSortedMap.printMap( );

				System.out.printf("\n\n\n");
				long end = System.currentTimeMillis();
	            double executionTime = (end - start) / 1000.0;
	            System.out.println("MergeSort Execution time: " + String.format("%.4f",executionTime) + " seconds"); 
				
	            System.out.printf("\n\n\n");
				/* Input Ordering control */
	            String input = "'Hush, hush!' whispered the rushing wind.";;
	            myMap hashmap = new myMap(input);	
				System.out.printf("The original (unsorted) map:\n");
				hashmap.printMap( );
	            System.out.printf("\n\nMerge Sorted Map\n");
	            MergeSort mergeSortHash = new MergeSort(hashmap);
	         	myMap sortedHash = mergeSortHash.getSortedMap( );
	         	sortedHash.printMap( );
			}
		catch(IllegalArgumentException exception)
		{
			System.out.printf("Error: An exception occured %s",exception.getMessage( ));
		}
	} 




}