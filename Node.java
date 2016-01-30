import net.datastructures.Position;

public class Node<E> implements Position<E>{

	E element;
	int index;
	
	public Node(int newIndex, E newElement){
		
		element = newElement;
		index = newIndex;		
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public E element() {

		return element;
	}
	
}


