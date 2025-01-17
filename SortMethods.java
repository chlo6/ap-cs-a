import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;		

/**
 *	SortMethods - Sorts an ArrayList of Strings in ascending order.
 *
 *	Requires FileUtils class to compile.
 *	Requires file randomWords.txt to execute a test run.
 *
 *	@author	Chloe He
 *	@since	January 10th, 2025
 */
public class SortMethods {
	
	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		List of String objects to sort
	 */
	public void mergeSort(List<String> arr) {
		mergeSortRecurse(arr, 0, arr.size() - 1);
	}
	
	/**
	 *	Recursive mergeSort method.
	 *	@param arr		List of String objects to sort
	 *	@param l		left index - start of sort
	 *	@param r		right index - end of sort
	 */
	public void mergeSortRecurse(List<String> arr, int l, int r) {
		if (l == r) return;
		
		if (r - l == 1) {
			if (arr.get(l).compareTo(arr.get(r)) > 0) swap(arr, l, r);
			return;
		}
		
		int m = (l + r)/2;
		mergeSortRecurse(arr, l, m);
		mergeSortRecurse(arr, m + 1, r);
		merge(arr, l, m, r);
	}

	/**
 	*	swaps value @ index a with value @ index b in a list of strings
  	*	@param ls 	List of strings that you want to be swapped
   	*	@param a 	index #1 u want to swap
   	*	@param b 	index #2 u want to swap
	*/
	
	public void swap(List<String> ls, int a, int b) {
		String c = ls.get(a);
		ls.set(a, ls.get(b));
		ls.set(b, c);
	}
	
	/**
	 *	Merge two lists that are consecutive elements in array.
	 *	@param arr		List of String objects to merge
	 *	@param first	first index of first list
	 *	@param mid		the last index of the first list;
	 *					mid + 1 is first index of second list
	 *	@param last		last index of second list
	 */
	public void merge(List<String> arr, int l1, int m, int r1) {
		int l = l1;
		int r = m + 1;
		List <String> newArr = new ArrayList<String>();
		
		while (l <= m && r <= r1) {
			if (arr.get(l).compareTo(arr.get(r)) < 0) {
				newArr.add(arr.get(l));
				l++;
			} else {
				newArr.add(arr.get(r));
				r++;
			}
		}
		
		while (l <= m) {
			newArr.add(arr.get(l));
			l++;
		}
		while (r <= r1) {
			newArr.add(arr.get(r));
			r++;
		}
		
		for (int a = 0; a < newArr.size(); a++) {
			arr.set(l1 + a, newArr.get(a));
		}
	}

	
	/**
	 *	Print an List of Strings to the screen
	 *	@param arr		the List of Strings
	 */
	public void printArray(List<String> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %-15s", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 5 == 0) System.out.printf(",\n  %-15s", arr.get(a));
			else System.out.printf(", %-15s", arr.get(a));
		}
		System.out.println(" )");
	}
	
	/*************************************************************/
	/********************** Test program *************************/
	/*************************************************************/
	private final String FILE_NAME = "randomWords.txt";
	
	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		List<String> arr = new ArrayList<String>();
		// Fill List with random words from file		
		fillArray(arr);
		
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
	
	// Fill String array with words
	public void fillArray(List<String> arr) {
		Scanner inFile = FileUtils.openToRead(FILE_NAME);
		while (inFile.hasNext())
			arr.add(inFile.next());
		inFile.close();
	}
}
