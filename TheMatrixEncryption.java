import java.util.*;
import java.io.*;

public class TheMatrixEncryption
{	
	public static void main(String [] args) throws Exception
	{
		Scanner fromFile = new Scanner(new File("DATA_TheMatrix.txt"));
		String keyword = ((fromFile.nextLine()).replace(" ", "")).toUpperCase();
		String message = ((fromFile.nextLine()).replace(" ", "")).toUpperCase();
		
		int tableWidth = keyword.length();
		int tableHeight = (int) (Math.ceil((message.length())/(double)tableWidth)) + 1;
		
		char[][] columnarTransposition = new char[tableHeight][tableWidth];
		int count = 0;
		int countY = 0;
		
		while(count < (tableHeight * tableWidth))
		{
			if((count) % tableWidth == 0 && count != 0)
			{
				countY++;
			}
			
			if(count < keyword.length())
			{
				columnarTransposition[countY][count % tableWidth] = keyword.charAt(count);
			}
			else if(count >= keyword.length() && count < (keyword.length() + message.length()))
			{
				columnarTransposition[countY][count % tableWidth] = message.charAt(count - keyword.length());
				//System.out.println(countY + " " + count % tableWidth + " " + message.substring(count - keyword.length(), count - keyword.length() + 1));
			}
			else
			{
				columnarTransposition[countY][count % tableWidth] = 'X';
			}
			count++;
		}
		//System.out.println(keyword.length() + " " + message.length() + " " + tableHeight);
        TheMatrixEncryption.sort(columnarTransposition, 0, tableWidth - 1);
        printEncrypted(columnarTransposition);
	}
	
	public static void merge(char arr[][], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        char array1[][] = new char[arr.length][n1]; 
        char array2[][] = new char[arr.length][n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
        {
        	for(int count = 0; count < arr.length; count++)
        	{
            array1[count][i] = arr[count][l + i];
        	}
        }
        for (int j=0; j<n2; ++j) 
        {
        	for(int count = 0; count < arr.length; count++)
        	{
            array2[count][j] = arr[count][m + 1+ j]; 
        	}
        }
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (array1[0][i] <= array2[0][j]) 
            { 
            	for(int count = 0; count < arr.length; count++)
            	{
                arr[count][k] = array1[count][i]; 
            	}
                i++; 
            } 
            else
            { 
            	for(int count = 0; count < arr.length; count++)
            	{
                arr[count][k] = array2[count][j]; 
            	}
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
        	for(int count = 0; count < arr.length; count++)
        	{
            arr[count][k] = array1[count][i]; 
        	}
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
        	for(int count = 0; count < arr.length; count++)
        	{
            arr[count][k] = array2[count][j]; 
        	} 
            j++; 
            k++; 
        } 
    } 
  
    public static void sort(char arr[][], int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            sort(arr, l, m); 
            sort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    } 
    
    public static void printEncrypted(char arr[][])
    {
    	int height = arr.length;
    	int width = arr[0].length;
    	
    	for(int count = 0; count < width; count++)
    	{
    		for(int count2 = 1; count2 < height; count2++)
    		{
    			System.out.print(arr[count2][count]);
    			
    			if(count2 % 5 == 0)
    			{
    				System.out.print(" ");
    			}
    		}
    	}
    }
}
