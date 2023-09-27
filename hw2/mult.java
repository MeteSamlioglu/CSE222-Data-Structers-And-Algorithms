public class mult
{



	public static int findMinimumMultiplication(int [] A, int[] B) 
	{
		int n = A.length;
		int m = B.length;
		int minA = A[0];
		int minB = B[0];
		
		int maxPositiveA = A[0];
		int maxPositiveB = B[0];


		for(int i = 0 ; i < n; i++)
		{
			if(A[i] <= minA)
				minA = A[i];
			if(A[i] >= maxPositiveA)
				maxPositiveA = A[i];
		}
		
		for(int i = 0; i < m ; i++)
		{
			if(B[i] <= minB)
				minB = B[i];
			if(B[i] >= maxPositiveB)
				maxPositiveB = B[i];
		}
	
		int minPositive = minA*minB;
		int minNegative1 = minA*maxPositiveB;
		int minNegative2 = maxPositiveA*minB;

		if( (minPositive <= minNegative1) && (minPositive <= minNegative2))
			return minPositive;
		else if( (minNegative1 <= minPositive) && (minNegative1 <= minNegative2))
			return minNegative1;
		else if((minNegative2 <= minPositive) && (minNegative2 <= minNegative1))
			return minNegative2;

		return 0;
	}

	public static void main(String [] args){
		int [] A = {-5, -4, 3, 4, 5};
		int [] B = {-6, -3 ,6 ,7 ,8};
		System.out.printf("The minimum multiplication is %d\n",findMinimumMultiplication(A,B));

	}


}