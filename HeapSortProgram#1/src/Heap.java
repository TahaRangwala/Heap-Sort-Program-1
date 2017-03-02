/*Name: Taha Rangwala
 * Date: February 15, 2017
 * Purpose: The purpose of the database class is to hold the methods needed for this program to work.
 * This includes the heap sort methods needed for the user to perform his or her task, and then this
 * class relays all that information back to the gui so the user can see it.
 */

//Heap class header
public class Heap <T extends Comparable<T>>{

	//Declaring private instance variables
	private T [] heapArray;//heap sort array
	private int Count;//Number of numbers in the array
	
	//Constructor method to intialize private instance variables
	public Heap(){
		heapArray = (T[])(new Comparable[10]);
		Count = -1;
	}
	
	/*Purpose: This method adds numbers to the heap array
	 * @param Number This is the number being added to the array
	 */
	public void addNumber(T Element){
		Count++;
		if(Count == heapArray.length)
			doubleSize();
		heapArray[Count] = Element;
	}
	
	//This method doubles the size of the heap array when there is no longer any space
	private void doubleSize(){
		T [] subArray = (T[])(new Comparable[heapArray.length*2]);
		for(int i = 0; i <= Count-1; i++){
			subArray[i] = heapArray[i];
		}
		heapArray = subArray;
	}
	
	/*Purpose: This method makes the actual heap by heaping from the bottom to the top
	 */
	public void HeapifyUp(){
		boolean change = false;
		for(int i = Count; i >= 0; i--){
			if((i % 2 != 0) && (((i-1)/2) >= 0) && heapArray[i].compareTo(heapArray[(i-1)/2]) > 0){
				swapNumbers(i,(i-1)/2);
				change = true;
			}
			else if(i % 2 == 0 && (((i-2)/2) >= 0) && heapArray[i].compareTo(heapArray[(i-2)/2]) > 0){
				swapNumbers(i,(i-2)/2);
				change = true;
				if(heapArray[i].compareTo(heapArray[i-1]) > 0)
					swapNumbers(i,i-1);
			}
			if(i == 0 && change){
				i = Count + 1;
				change = false;
			}
		}
	}
	
	/*Purpose: This method makes the actual heap and swaps numbers when needed to make max heaps
	 * @param currentCount This is the count the method uses as a way of determining what the end of the heap is
	 */
	private void HeapifyDown(int currentCount){
		boolean change = false;
		for(int i = 0; i <= currentCount; i++){
			if(((2* i + 1) <= currentCount) && heapArray[i].compareTo(heapArray[2 * i + 1]) < 0){
				swapNumbers(i,2* i + 1);
				change = true;
			}
			if(((2* i + 2) <= currentCount) && heapArray[i].compareTo(heapArray[2 * i + 2]) < 0){
				swapNumbers(i,2* i + 2);
				change = true;
			}
			if(i == currentCount && change){
				i = -1;
				change = false;
			}
		}
	}
	
	/*Purpose:This method switches numbers in the array when the heap is being sorted in order from largest to smallest
	 * @return Returns a string value of all the swaps and heaps that are present before the final heap
	 */
	public String switchNumbers(){
		String Output = "\n\nInitial Heap:\n" + toString() + "\n\n";
		if(Count < 0)
			throw new IllegalArgumentException("There Are No Numbers In The Heap!");
		else{
			for(int i = Count; i >= 1; i--){
				swapNumbers(0, i);
				Output += "Swap:\n" + toString() + "\n\n";
				HeapifyDown(i-1);
			}
		}
		return Output;
	}
	
	/*Purpose: This method swaps the positions of two numbers in the heap array
	 * @param Num1 Position of first number
	 * @param Num2 Position of second number
	 */
	private void swapNumbers(int Num1, int Num2){
		T subNumber = heapArray[Num1];
		heapArray[Num1] = heapArray[Num2];
		heapArray[Num2] = subNumber;
	}
	
	/*Purpose: This method outputs everything in the heap array and sorts the numbers by level
	 * @return Returns a string value of all the numbers in the array sorted by level
	 */
	public String toString(){
		int Number = 0, numInLevel = 1, currentLevel = 0;
		String Output = "Level " + currentLevel + ": ";
		for(int i = 0; i <= Count; i++){
			Output += heapArray[i] + " ";
			Number++;
			if(Number == numInLevel && i != Count){
				numInLevel*=2;
				currentLevel++;
				Output += "\nLevel " + currentLevel + ": ";
				Number = 0;
			}
		}
		return Output;
	}
	
}
