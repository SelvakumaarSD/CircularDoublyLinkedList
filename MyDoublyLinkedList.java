import java.util.*;

public class MyDoublyLinkedList<E> extends MyAbstractSequentialList<E> implements Cloneable {
	private Node<E> head = new Node<E>(null);

	/** Create a default list */
	public MyDoublyLinkedList() {
		head.next = head;
		head.previous = head;
	}

	private static class Node<E> {
		E element;
		Node<E> previous;
		Node<E> next;

		public Node(E element) {
			this.element = element;
		}
	}

	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head.next;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != head) {
				result.append(", "); // Separate two elements with a comma
			}
		}
		result.append("]"); // Insert the closing ] in the string

		return result.toString();
	}

	private Node<E> getNode(int index) {
		Node<E> current = head;
		if (index < size / 2)
			for (int i = -1; i < index; i++)
				current = current.next;
		else
			for (int i = size; i > index; i--)
				current = current.previous;
		return current;return current;
	}

	@Override
	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node<E> prev = getNode(index - 1);
		Node<E> next = prev.next;
		Node<E> newNode = new Node<E>(e);
		prev.next = newNode;
		next.previous = newNode;
		newNode.previous = prev;
		newNode.next = next;
		size++;
	}

	@Override
	public void clear() {
		head.next = head;
		head.previous = head;
		size = 0;
	}

	@Override
	public boolean contains(E o) {
		for (Node<E> current = head.next; current != head; current = current.next) {
			E e = current.element;
			if (o == null ? e == null : o.equals(e))
				return true;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		
		Node<E> current = head.next;
		for (int i = 0; i < index; i++){
			current = current.next;
		}

		return current.element;
	}

	@Override
	public int indexOf(E e) {
		Node<E> current = head.next;
		int returnIndex = 0;

		for (int i = 0; i < size; i++){
			if(e == null){
				return returnIndex;
			}

			if(current.element.equals(e)){
				returnIndex = i;
			}
			current = current.next;
		}
		return returnIndex;
	}

	@Override
	public int lastIndexOf(E e) {
		Node<E> current = head.next;
		int last = -1;
		
		for (int i = 0; i < size; i++){
			if(current.element.equals(e)){
				last = i;
			}
			current = current.next;
		}

		if (last > -1){
			return last;
		}

		return last;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		else if (index == 0){
			return removeFirst();
		}
		else if (index == size-1){
			return removeLast();
		}
		else {
			Node<E> prev = getNode(index - 1);
			Node<E> current = getNode(index);
			Node<E> next = current.next;
			
			prev.next = next;
			next.previous = prev;
			size--;
			
			return current.element;
		}
	}

	@Override
	public Object set(int index, E e) {
		if (index < 0 || iindex >= size){
			throw new IndexOutOfBoundsException();
		}

		Nde<E> current = head.next;
		for (int i = 0; i < index; i++){
			current = current.next;
		}

		current.element = e;

		return current.element;
	}

	@Override
	public E getFirst() {
		return head.next.element;
	}

	@Override
	public E getLast() {
		return head.previous.element;
	}

	@Override
	public void addFirst(E e) {
		add(0, e);
	}

	@Override
	public void addLast(E e) {
		Node<E> current = head.previous;
		int lastIndex = indexOf(current.element);

		add(lastIndex, e);
	}

	@Override
	public E removeFirst() {
		if (head.next.element == null){
			throw new NoSuchElementException();
		}
		
		Node<E> current = head.next;
		Node<E> next = current.next;
		E e = current.element;

		next.previous = head;
		current = next;
		size--;

		return e;
	}

	@Override
	public E removeLast() {
		if (head.previous.element == null){
			throw new NoSuchElementException();
		}

		Node<E> current = head.previous;
		Node<E> prev = current.previous;
		E e = current.element;

		prev.next = head;
		current = prev;
		size--;

		return e;
	}

	//Clone Method
	public object clone(){
		try{
			MyDoublyLinkedList listClone = (MyDoublyLinkedList)super.clone();
			Node<E> newHead = new Node<E>(null);
			newHead.previous = newHead;
			newHead.next = newHead;

			int newSize = 0;
			listClone.head = newHead;
			listIterator i1 = (MyDoublyLinkedList<E>.listIterator) this.listIterator(0);
			listIterator i2 = (MyDoublyLinkedList<E>.listIterator) listClone.listIterator(0);

			while (i.hasNext()){
				i2.add(i1.current.element);
				newSize++;
				i.next();
			}

			listClone.size = newSize;
			return listClone;
		}
		catch(CloneNotSupportedException ex){
			return null;
		}
	}

	//Equals Method
	public boolean equals(Object o){
		if (this == o){
			return true;
		}
		else if (!(o instanceof MyList)){
			return false;
		}
		else if (((MyList<E>)o).size() != this.size()){
			return false;
		}
		else{
			listIterator iterO = (MyDoublyLinkedList<E>.listIterator) ((MyDoublyLinkedList<E>) o).listIterator(0);
			listIterator iterThis = (MyDoublyLinkedList<E>.listIterator) this.listIterator(0);

			for (int i = 0; i < size; i++){
				if(iterO.current.element != iterThis.current.element)
					return false;
					iterO.next();
					iterThis.next();
			}

			return true;
		}
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new MyDoublyLinkedListIterator(index);
	}

	private static enum ITERATOR_STATE {
		CANNOT_REMOVE, CAN_REMOVE_PREV, CAN_REMOVE_CURRENT
	};

	private class MyDoublyLinkedListIterator implements ListIterator<E> {
		private Node<E> current; // node that holds the next element in the iteration
		private int nextIndex; // index of current
		ITERATOR_STATE iterState = ITERATOR_STATE.CANNOT_REMOVE;

		private MyDoublyLinkedListIterator(int index) {
			if (index < 0 || index > size)
				throw new IndexOutOfBoundsException("iterator index out of bounds");
			current = getNode(index);
			nextIndex = index;
		}

		@Override
		public void add(E arg0) {
			//Creating the new node element for input paramter
			Node<E> newNode = new Node<E>(arg0);

			//Setting up the previous and next nodes of new element
			prev = head.previous;
			if(prev != null){
				prev.next = newNode;
				head.previous = newNode;
				newNode.previous = prev;
				newNode.next = head;
				newNode.element = arg0;
			}

			//Setting the value of Iterator to avoid add() or remove() calls immediately after this
			iterState = ITERATOR_STATE.CANNOT_REMOVE;
			nextIndex++;
			size++;
		}

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public boolean hasPrevious() {
			return nextIndex > 0;
		}

		@Override
		public E next() {
			if (nextIndex >= size)
				throw new NoSuchElementException();
			
			current = current.next;
			E returnVal = current.element;
			nextIndex++;
			iterState = ITERATOR_STATE.CAN_REMOVE_PREV;
			return returnVal;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public E previous() {
			if(nextIndex <= 0)
				throw new NoSuchElementException();
			
			current = current.previous;
			E returnVal = current.element;
			nextIndex--;
			iterState = ITERATOR_STATE.CAN_REMOVE_CURRENT;
			return returnVal;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			switch (iterState) {
			case CANNOT_REMOVE:
				throw new IllegalStateException("Next or Previous Method not called before Remove");

				break;
			case CAN_REMOVE_PREV:
				//Getting the last element from nex() method
				E lastElement = next();
				Node<E> lastNode = new Node<E>(lastElement);
				
				//Resetting the previous and current node reference
				lastNode.previous.next = lastNode.next;
				lastNode.next.previous = lastNode.previous;

				nextIndex--;
				size--;

				//Setting the value of Iterator to avoid add() or remove() calls immediately after this
				iterState = ITERATOR_STATE.CANNOT_REMOVE;

				break;
			case CAN_REMOVE_CURRENT:
				//Getting the previous element
				E prevElement = previous();
				Node<E> prevNode = new Node<E>(prevElement);

				prevNode.previous.next = prevNode.next;
				prevNode.next.previous = prevNode.previous;

				nextIndex--;
				size--;

				//Setting the value of Iterator to avoid add() or remove() calls immediately after this
				iterState = ITERATOR_STATE.CANNOT_REMOVE;

				break;
			}
		}

		@Override
		public void set(E arg0) {
			switch (iterState) {
			case CANNOT_REMOVE:
				throw new IllegalStateException("Next or Previous Method not called before Remove");

				break;
			case CAN_REMOVE_PREV:
				//Getting the last element from next() method
				E lastElement = next();
				Node<E> lastNode = new Node<E>(lastElement);
				
				lastNode.element = arg0;

				break;
			case CAN_REMOVE_CURRENT:
				//Getting the first element from previous() method
				E firstElement = previous();
				Node<E> firstNode = new Node<E>(firstElement);
				
				firstNode.element = arg0;

				break;
			}
		}
	}
}
