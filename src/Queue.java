import java.util.LinkedList;

public class Queue<type>{
    private LinkedListNode<type> head, tail;

    Queue(){
        this.head = null;
        this.tail = null;
    }

    public void enqueue(type key){

        LinkedListNode<type> newNode = new LinkedListNode<>(key);

        //If queue is empty
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            this.tail.next = newNode;
            this.tail = newNode;
        }

    }

    public LinkedListNode<type> dequeue(){

        if(this.head == null){
            System.out.println("Queue is empty!; Nothing to Dequeue");
            return null;
        }

        LinkedListNode<type> tempNode = this.head;

        this.head = this.head.next;

        if(this.head == null){
            this.tail = null;
        }

        return tempNode;


    }


    public boolean isEmpty(){
        return this.head == null;
    }

}
