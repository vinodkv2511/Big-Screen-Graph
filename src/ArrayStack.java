/*
    This is a implementation of an integer stack using Array

    @author Vinod Krishna Vellampalli

 */
public class ArrayStack<type> implements Stack<type>{

    private int top;
    private int size;
    private type[] stackArray;

    public ArrayStack(int size)
    {
        this.stackArray = (type[])new Object[size];
        this.size = size;
        this.top = -1;
    }

    @Override
    public boolean isEmpty() {
        if(this.top == -1){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public boolean isFull() {
        if(this.top == this.size - 1 ){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public void push(type x) throws StackOverflowError {
        if(this.isFull()){
            System.out.println("Overflow");
            throw new StackOverflowError("Stack is full! Can't push any more");
        }

        this.top += 1;
        stackArray[top] = x;
//        printStack();

    }

    @Override
    public type pop() throws StackUnderflowError {
        if(this.isEmpty()){
            System.out.println("Underflow");
            throw new StackUnderflowError("Stack is empty! Can't pop.");
        }

        type x = stackArray[this.top];
        this.top -= 1;
//        printStack();
        return x;

    }


//    private void printStack(){
//        for(int i=0 ; i<=top; i++){
//            System.out.printf(stackArray[i]+ " ");
//        }
//        System.out.println(" ");
//    }
}
