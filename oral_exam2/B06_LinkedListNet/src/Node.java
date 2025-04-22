
/**
 * Node class for a singly linked list
 * @param <E> the type of the element in the node
 */
public class Node<E>{
    private Node<E> next;
    private E element;

    /**
     * Constructor
     * @param element the element to store in the node
     */
    public Node(E element) {
        this.element = element;
        this.next = null;
    }

    /**
     * Constructor
     * @param element the element to store in the node
     * @param next the next node in the list
     */
    public Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Get the next node
     * @return the next node
     */
    public Node<E> getNext() {
        return this.next;
    }

    /**
     * Set the next node
     * @param next the next node
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * Get the element
     * @return the element
     */
    public E getElement() {
        return this.element;
    }

    /**
     * Set the element
     * @param element the element
     */
    public void setElement(E element) {
        this.element = element;
    }
}
