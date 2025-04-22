/**
 * A generic singly linked list implementation.
 * @param <E> the type of elements in the list
 */
public class LinkedList<E> {
    private int length;
    private Node<E> head;
    private Node<E> tail;

    /**
     * Constructor
     */
    LinkedList() {
        this.length = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Get the length of the list
     * @return the length of the list
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Insert an element at the end of the list
     * @param element the element to insert
     * @param index the index to insert the element at
     */
    public void insert(E element, int index) {
        if (index < 0 || index > this.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<E> newNode = new Node<E>(element);

        // damn that's a lot of edge cases

        // empty list
        if (this.length == 0) {
            this.head = newNode;
            this.tail = newNode;
        }

        // insert at end
        else if (index == this.length) {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }

        // insert at beginning
        else if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
        }

        // insert anywhere else
        else {
            Node<E> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }

        this.length++;
    }

    /**
     * Remove an element from the list
     * @param index the index to remove the element from
     * @return the element that was removed
     */
    public E remove(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        E element = null;

        // remove from beginning
        if (index == 0) {
            element = this.head.getElement();
            this.head = this.head.getNext();
        }

        // remove from end
        else if (index == this.length - 1) {
            Node<E> current = this.head;
            for (int i = 0; i < this.length - 2; i++) {
                current = current.getNext();
            }
            element = current.getNext().getElement();
            current.setNext(null);
            this.tail = current;
        }

        // remove from anywhere else
        else {
            Node<E> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            element = current.getNext().getElement();
            current.setNext(current.getNext().getNext());
        }

        this.length--;
        return element;
    }

    /**
     * Get an element from the list
     * @param index the index of the element to get
     * @return the element at the index
     */
    public E getElement(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<E> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getElement();
    }

    /**
     * Set an element in the list
     * @param element the element to set
     * @param index the index to set the element at
     */
    public void setElement(E element, int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<E> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        current.setElement(element);
    }

    /**
     * Get a string representation of the list
     * "[element1] -> ... -> [elementN] -> null"
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = this.head;
        while (current != null) {
            sb.append(current.getElement().toString()).append(" -> ");
            current = current.getNext();
        }
        sb.append("null");
        return sb.toString();
    }
}
