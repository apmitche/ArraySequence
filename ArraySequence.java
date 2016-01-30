package cs2321;

import java.util.Iterator;
import net.datastructures.*;
import cs2321.*;

/**
 * A sequence that is implemented as an array.
 * If the capacity is exceeded the capacity will be doubled. 
 * 
 * 
 * The following functions are O(n) time complexity:
 * 		add(int aIndex, E aElement);  remove( int aIndex); 
 *		addAfter(Position<E> aPosition, E aElementToAdd); addBefore(Position<E> aPosition, E aElementToAdd);
 *		remove( Position<E> aPosition ); addFirst( E aElementToAdd ); removeFirst(); positions();
 *     
 * The following functions are O(1) time complexity:
 *		atIndex(int aIndex); indexOf (Position<E> aPosition); isEmpty(); size(); get(int aIndex);
 *		set(int aIndex, E aElement); first(); last(); next(Position<E> aPosition);
 *		set( Position<E> aPosition, E aElement ); addLast( E aElementToAdd );
 *		getFirst(); getLast(); removeLast();
 * 
 * Course: CS2321 Section 1
 * Assignment: #1
 * @author Alicia Mitchell
 */ 
/* Do not alter the definition of the class.
 * ie. Do not extend LinkedSequence.
 */
@SpaceComplexity ("2n")
public class ArraySequence<E> implements Sequence<E>
{
	/*# Complete the implementation of all required methods.
	 *  Be sure to update this file to include:
	 *      
	 *      Annotations (@TimeComplexity, @SpaceComplexity, @TimeComplexityAmmortized, @TimeComplexityExpected)
	 *      that indicate the time and space complexities.
	 *      
	 *      Appropriate comments justifying time and space claims (TCJ and SCJ) 
	 */

	protected int size;
	protected int capacity = 10;
	private Node<E>[] mainArray = (Node<E>[]) new Node[capacity];

	public ArraySequence(){

		Node<E>[] mainArray = (Node<E>[]) new Node[capacity];
		size = 0;
	}

	/**
	 * Returns the position containing the element at the given index.
	 * 
	 * @param aIndex The index of the element to retrieve
	 * 
	 * @return The position containing the element at the given index.
	 * 
	 * @throws BoundaryViolationException
	 * 
	 * @see net.datastructures.Sequence#atIndex(int)
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public Position<E> atIndex( int aIndex ) throws BoundaryViolationException
	{
		if ( ( aIndex < 0 ) || ( aIndex >= size ) )
		{
			throw new BoundaryViolationException( "Index is invalid" );
		}

		return mainArray[aIndex];
	}

	/**
	 * Returns the index of the element stored at the given position.
	 * 
	 * @param aPosition The position of the element to get the index of
	 * 
	 * @return The index of the element at the given position
	 * 
	 * @throws InvalidPositionException
	 * 
	 * @see net.datastructures.Sequence#indexOf(net.datastructures.Position)
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public int indexOf( Position<E> aPosition ) throws InvalidPositionException
	{

		return ((Node<E>) aPosition).getIndex();
	}

	/**
	 * Returns true if the array is empty, otherwise returns false.
	 * 
	 * @return True if the array is empty, otherwise false.
	 * 
	 * @see net.datastructures.Deque#isEmpty()
	 * 
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public boolean isEmpty()
	{
		if (size == 0){
			return true;
		}
		else return false;
	}

	/**
	 * Returns the size, or elements currently contained, in the array.
	 * 
	 * @return how many elements are in the array
	 * 
	 * @see net.datastructures.Deque#size()
	 * 
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public int size()
	{
		return size;
	}

	/**
	 * Inserts an element aElement to be at index aIndex, shifting all elements after this
	 * 
	 * @param aIndex The index to insert the element at
	 * @param aElement The element to insert at the given index
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 * 
	 * @see net.datastructures.IndexList#add(int, java.lang.Object)
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public void add( int aIndex, E aElement ) throws IndexOutOfBoundsException
	{
		if ( ( aIndex < 0 ) || ( aIndex > this.size() ) )
		{
			throw new IndexOutOfBoundsException( "Index is invalid" );
		}

		if (size + 1 == capacity){

			capacity = (capacity * 2);
			Node<E>[] temp = (Node<E>[]) new Node[capacity];
			for(int i = 0; i < mainArray.length; i++){
				temp[i] = mainArray[i];
			}

			mainArray = temp;
		}

		for(int i = size + 1; i > aIndex; i--){
			mainArray[i] = mainArray[i - 1];
		}

		Node<E> temp = new Node<E>(aIndex, aElement);
		mainArray[aIndex] = temp;

		this.size = this.size+1;
	}

	/**
	 * Returns the element at index aIndex without removing it
	 * 
	 * @param aIndex The index to get the element from
	 * 
	 * @return The element at the given index
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 * 
	 * @see net.datastructures.IndexList#get(int)
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public E get( int aIndex ) throws IndexOutOfBoundsException
	{
		return mainArray[aIndex].getElement();
	}

	/**
	 * Removes and returns the element at index aIndex, shifting the elements after this.
	 * 
	 * @param aIndex The index to remove the element at
	 * 
	 * @return The element removed from the list
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 * 
	 * @see net.datastructures.IndexList#remove(int)
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public E remove( int aIndex ) throws IndexOutOfBoundsException
	{
		if ( ( aIndex < 0 ) || ( aIndex >= size ) )
		{
			throw new IndexOutOfBoundsException( "Index is invalid" );
		}

		Node<E> temp = mainArray[aIndex];

		for(int i = aIndex; i < this.size(); i ++){
			mainArray[i] = mainArray[i+1];
		}

		this.size = this.size() - 1;

		for(int i = aIndex; i < this.size(); i++){
			mainArray[i].index = mainArray[i].index - 1;
		}

		return temp.getElement();
	}

	/**
	 * Replaces the element at index aIndex with aElement, returning the previous element at aIndex.
	 * 
	 * @param aIndex The index to replace the element at
	 * @param aElement The element to replace the element at the given index
	 * 
	 * @return The element that was replaced at the given index
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 * 
	 * @see net.datastructures.IndexList#set(int, java.lang.Object)
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public E set( int aIndex, E aElement ) throws IndexOutOfBoundsException
	{
		if ( ( aIndex < 0 ) || ( aIndex >= size ) )
		{
			throw new IndexOutOfBoundsException( "Index is invalid" );
		}

		Node<E> newNode = new Node<E>(aIndex, aElement);
		Node<E> old = mainArray[aIndex];

		mainArray[aIndex] = newNode;
		return old.getElement();
	}

	/**
	 * Adds an element (aElementToAdd) after the position passed (aPosition).
	 * 
	 * @param aPosition The position of the element to insert after
	 * @param aElementToAdd The element you want to add
	 * 
	 * @throws InvalidPositionException if the given index is invalid
	 * 
	 * @see net.datastructures.PositionList#addAfter(net.datastructures.Position, java.lang.Object)
	 * 
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public void addAfter( Position<E> aPosition, E aElementToAdd )
			throws InvalidPositionException
			{
		if (this.isEmpty()){
			throw new InvalidPositionException("Array is empty.");
		}

		int index = indexOf(aPosition);

		add(index + 1, aElementToAdd);

		for(int i = index + 2; i < this.size(); i++){
			mainArray[i].index = mainArray[i].index + 1;
		}
			}

	/**
	 * Adds an element (aElementToAdd) before the position passed (aPosition).
	 * 
	 * @param aPosition The position of the element to insert before
	 * @param aElementToAdd The element you want to add
	 * 
	 * @throws InvalidPositionException if the given index is invalid
	 * 
	 * @see net.datastructures.PositionList#addBefore(net.datastructures.Position, java.lang.Object)
	 * 
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public void addBefore( Position<E> aPosition, E aElementToAdd )
			throws InvalidPositionException
			{
		if (this.isEmpty()){
			throw new InvalidPositionException("Array is empty.");
		}

		int index = indexOf(aPosition);

		add(index, aElementToAdd);

		for(int i = index + 1; i < this.size(); i++){
			mainArray[i].index = mainArray[i].index + 1;
		}
			}

	/**
	 * Returns the first element.
	 * 
	 * @return The first element in the deque
	 * 
	 * @see net.datastructures.PositionList#first()
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> first()
	{
		if (this.isEmpty()){
			return null;
		}

		Node<E> temp = mainArray[0];
		return (Position<E>) temp.getElement();
	}

	/**
	 * Returns the last element.
	 * 
	 * @return The last element in the deque
	 * 
	 * @see net.datastructures.PositionList#last()
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> last()
	{
		if (mainArray.length == 0){
			return null;
		}

		Node<E> temp = mainArray[size + 1];
		return (Position<E>) temp.getElement();
	}

	/**
	 * Gets the next element after the position (aPosition) that is passed.
	 * 
	 * @param aPosition The position you want to get the next of
	 * 
	 * @throws InvalidPositionException if the given index is invalid
	 * @throws BoundaryViolationException if the given index is invalid
	 * 
	 * @see net.datastructures.PositionList#next(net.datastructures.Position)
	 * 
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> next( Position<E> aPosition )
			throws InvalidPositionException, BoundaryViolationException
			{
		Node<E> theNode = (Node<E>) aPosition;

		if(theNode.getIndex() == mainArray.length){
			throw new BoundaryViolationException("Position is invalid.");
		}

		return mainArray[theNode.getIndex() + 1];
			}

	/**
	 * Gets the next element after the position (aPosition) that is passed.
	 * 
	 * @param aPosition The position you want to get the previous of
	 * 
	 * @throws InvalidPositionException if the given index is the beginning
	 * @throws BoundaryViolationException if the given index is invalid
	 * 
	 * @see net.datastructures.PositionList#prev(net.datastructures.Position)
	 * 
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> prev( Position<E> aPosition )
			throws InvalidPositionException, BoundaryViolationException
			{

		Node<E> theNode = (Node<E>) aPosition;

		if( theNode.getIndex() == 0 ){
			throw new InvalidPositionException("Position is invalid.");
		}

		if( theNode.getIndex() > mainArray.length){
			throw new BoundaryViolationException("Position is invalid.");
		}

		return mainArray[theNode.getIndex() - 1];
			}

	/**
	 * Removes and returns the element at position aPosition, shifting the elements after this.
	 * 
	 * @param aPosition The index to remove the element at
	 * 
	 * @return The element removed from the list
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 * 
	 * @see net.datastructures.PositionList#remove(net.datastructures.Position)
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public E remove( Position<E> aPosition ) throws InvalidPositionException
	{

		Node<E> temp = (Node<E>) aPosition; 

		if(temp.index > this.size + 1 || temp.index < 0){
			throw new InvalidPositionException();
		}

		for(int i = temp.getIndex(); i < this.size(); i ++){
			mainArray[i] = mainArray[i+1];
		}

		this.size = this.size - 1;

		for(int i = temp.getIndex(); i < this.size(); i++){
			mainArray[i].index = mainArray[i].index - 1;
		}
		return temp.getElement();
	}

	/**
	 * Replaces the element at position aPosition with aElement, returning the previous element at aPosition.
	 * 
	 * @param aPosition The index to replace the element at
	 * @param aElement The element to replace the element at the given index
	 * 
	 * @return The element that was replaced at the given index
	 * 
	 * @throws IndexPositionException if the given index is invalid
	 * 
	 * @see net.datastructures.IndexList#PositionList(net.datastructures.Position, java.lang.Object)
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public E set( Position<E> aPosition, E aElement )
			throws InvalidPositionException
			{

		Node<E> newNode = (Node<E>) aPosition;
		Node<E> old = mainArray[newNode.getIndex()];

		if(newNode.index > this.size + 1 || newNode.index < 0){
			throw new InvalidPositionException();
		}

		newNode.setElement(aElement);
		mainArray[newNode.getIndex()] = newNode;
		return old.getElement();
			}

	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public Iterator<E> iterator()
	{

		//Iterator<E> theIt = new Iterator<E>();

		return null;
	}

	/**
	 * Inserts an element to be the first in the deque.
	 * 
	 * @param aElementToAdd the element added to the front of the array
	 * 
	 * @see net.datastructures.Deque#addFirst()
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public void addFirst( E aElementToAdd )
	{

		if (size + 1 == capacity){

			capacity = (capacity * 2);
			Node<E>[] temp = (Node<E>[]) new Node[capacity];
			for(int i = 0; i < mainArray.length; i++){
				temp[i] = mainArray[i];
			}

			mainArray = temp;
		}

		add(0, aElementToAdd);

		for(int i = 0 + 1; i < this.size(); i++){
			mainArray[i].index = mainArray[i].index + 1;
		}


	}

	/**
	 * Inserts an element to be the last in the deque.
	 * 
	 * @param aElementToAdd the element added to the back of the array
	 * 
	 * @see net.datastructures.Deque#addLast()
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	public void addLast( E aElementToAdd )
	{

		if (size == capacity){

			capacity = (capacity * 2);
			Node<E>[] temp = (Node<E>[]) new Node[capacity];
			for(int i = 0; i < mainArray.length; i++){
				temp[i] = mainArray[i];
			}

			mainArray = temp;
		}

		Node<E> last = new Node<E>(this.size()+1, aElementToAdd);

		mainArray[this.size()] = last;
		last.index = this.size();
		this.size = this.size + 1;
	}

	/**
	 * Returns the first element; an exception is thrown if deque is empty.
	 * 
	 * @return The first element in the deque
	 * 
	 * @throws EmptyDequeException if the deque is empty
	 * 
	 * @see net.datastructures.Deque#getFirst()
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public E getFirst() throws EmptyDequeException
	{
		if(this.isEmpty()){
			throw new EmptyDequeException("The array is empty.");
		}

		Node<E> first = mainArray[0];
		return first.getElement();
	}

	/**
	 * Returns the last element; an exception is thrown if deque is empty.
	 * 
	 * @return The last element in the deque
	 * 
	 * @throws EmptyDequeException if the deque is empty
	 * 
	 * @see net.datastructures.Deque#getLast()
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public E getLast() throws EmptyDequeException
	{
		if(this.isEmpty()){
			throw new EmptyDequeException("The array is empty.");
		}

		Node<E> last = mainArray[size+1];
		return last.getElement();
	}

	/** 
	 * Removes the first element; an exception is thrown if deque is empty.
	 * 
	 * @throws EmptyDequeException if they array is empty.
	 * 
	 * @return The element that was removed.
	 * 
	 * @see net.datastructures.Deque#removeFirst()
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public E removeFirst() throws EmptyDequeException
	{
		if (this.isEmpty()){
			throw new EmptyDequeException("Empty Deque");
		}

		Node<E> first = mainArray[0];

		for(int i = 0; i < this.size(); i++){
			mainArray[i] = mainArray[i+1];
		}

		this.size = this.size - 1;

		for(int i = 0; i < this.size(); i++){
			mainArray[i].index = mainArray[i].index - 1;
		}
		return first.getElement();
	}

	/** 
	 * Removes the last element; an exception is thrown if deque is empty.
	 * 
	 * @throws EmptyDequeException if they array is empty.
	 * 
	 * @return The element that was removed.
	 * 
	 * @see net.datastructures.Deque#removeLast()
	 */
	@Override
	@TimeComplexity("O(1)")
	@TimeComplexityExpected("O(1)")
	public E removeLast() throws EmptyDequeException
	{
		if (this.isEmpty()){
			throw new EmptyDequeException("Empty Deque");
		}

		Node<E> last = mainArray[this.size() - 1];

		mainArray[this.size() - 1] = null;

		this.size = this.size - 1;	    
		return last.getElement();
	}

	/** Returns an iterable collection of all the nodes in the list. 
	 * 
	 * @return the list that can be iterated through
	 * 
	 * @see net.datastructures.IndexList#PositionList()
	 */
	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public Iterable<Position<E>> positions() {

		PositionList<Position<E>> theList = new ArraySequence<Position<E>>();

		if(!isEmpty()){
			Position<E> p = first();
			while(true){
				theList.addLast(p);

				if(p == last()){
					break;
				}

				p = next(p);
			}
		}

		return theList;


	}

	public static void main(String[] args){


		//----------------------------------------------------------//
		//  						Test 1:							//
		//   ArraySequence using the get method time measurement	//
		//----------------------------------------------------------//
		/*
		int sampleSize = 5000;
		int elements = 100;
		ArraySequence<Integer>[] sequences = new ArraySequence[sampleSize];

		for ( int i = 0; i < sampleSize; i++ ){
			sequences[i] = new ArraySequence<Integer>();
		}
		//Fill each sequence
		for ( int i = 0; i < elements; i++ ){

			for ( int j = 0; j < sampleSize; j++ ){

				sequences[j].addFirst( i );
			}
		}


		for (int i = 0; i < sampleSize; i++){

			long startTime = System.nanoTime();
			for(int j = 0; j < elements; j++){

				sequences[i].get(j);
			}

			long stopTime = System.nanoTime();

			System.out.printf( "%4d , %9.5f %n", i + 1, ( stopTime - startTime )
					/ ( double )sampleSize );
		} */


		//--------------------------------------------------------------//
		//  						Test 2:								//
		//   ArraySequence using the addFirst method time measurement	//
		//--------------------------------------------------------------//

		int sampleSize2 = 5000;

		ArraySequence<Integer>[] sequences2 = new ArraySequence[sampleSize2];

		for ( int i = 0; i < sampleSize2; i++ ){

			long startTime = System.nanoTime();
			sequences2[i] = new ArraySequence<Integer>();

			int N = i;
			while(N > 0){
				sequences2[i].addFirst(N);
				N--;
			}

			long stopTime = System.nanoTime();

			System.out.println(( stopTime - startTime )
					/ ( double )sampleSize2 );
		} 



		/* Old Tests:
		 ArraySequence<Integer> theArray = new ArraySequence<Integer>();

		 System.out.println( "Test empty array: " + theArray.size() );
		 System.out.println( "Test isEmpty: " + theArray.isEmpty() );

		 theArray.add(0, 0);
		 theArray.add(1, 1);
		 theArray.add(2, 2);
		 theArray.add(3, 3);
		 theArray.add(4, 4);
		 theArray.add(5, 5);
		 theArray.add(6, 6);
		 theArray.add(7, 7);
		 theArray.add(8, 8);
		 theArray.add(9, 9);
		 theArray.add(10, 10);
		 theArray.add(11, 11);

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }
		 System.out.println("");
		 System.out.println("Test if it's now not empty: " + theArray.isEmpty());
		 System.out.println( "The array size after adding elements: " + theArray.size() );

		 theArray.remove(0);
		 System.out.print("The array after remove method at 0: ");
		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 System.out.println("");
		 System.out.println( "The array size after removing one: " + theArray.size() );

		 theArray.removeFirst();
		 System.out.println( "The array after using removeFirst: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 System.out.println("");
		 theArray.remove(2);
		 System.out.print("The array after remove method at 2: ");
		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }
		 System.out.println("");
		 System.out.println( "The array size after removing elements: " + theArray.size() );


		 theArray.removeLast();
		 System.out.println( "The array after using removeLast: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 theArray.addAfter(theArray.atIndex(0), 50);
		 System.out.println("");
		 System.out.println( "The array after adding one after index 0. Size: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 theArray.addAfter(theArray.atIndex(8), 50);
		 System.out.println("");
		 System.out.println( "The array after adding one after index 8. Size: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 theArray.set(0, 89);
		 System.out.println("");
		 System.out.println( "The array after setting index 0 to 89. Size: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 theArray.addBefore(theArray.atIndex(4), 200);
		 System.out.println("");
		 System.out.println( "The array after setting index 0 to 89. Size: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 int remIndex = theArray.size() - 1;
		 while(remIndex >= 0){
			 theArray.remove(remIndex);
			 remIndex--;
		 }
		 System.out.println("");
		 System.out.println( "The array after deleting them all. Size: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 theArray.addFirst( 2 ) ;
		 System.out.println("");
		 System.out.println( "The array after using addFirst. Size: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 theArray.addFirst( 1 ) ;
		 System.out.println("");
		 System.out.println( "The array after using addFirst. Size: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");
		 }

		 theArray.addLast( 3 );
		 System.out.println("");
		 System.out.println( "The array after using addLast. Size: " + theArray.size() );

		 for (int i = 0; i < theArray.size(); i++){
			 System.out.print(theArray.get(i) + " ");

		 */
	} 
}
