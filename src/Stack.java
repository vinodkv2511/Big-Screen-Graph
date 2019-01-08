/*
    This interface provides a basic skeleton for implementing stack
 */
public interface Stack<type> {

    boolean isEmpty();
    boolean isFull();
    void push(type x) throws StackOverflowError;
    type pop() throws StackUnderflowError;

}
