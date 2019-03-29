/**
 * This program will make use of and implement a LinkedList with methods for
 * adding data to the list, remove an item by position, remove an item by value,
 * clear all elements from a list, get an item based on the position, and output
 * the contents of a list.
 */
package linkedlistworkup;

/**
 *
 * @author Jacquelyn Johnson
 * Course: COSC 2336-2803
 * Date: March 27, 2019
 * @param <T> generic type to be made into a linked list
 */ 
public class LinkedListWorkup<T> {
    private Node first;
    private Node last;
    
    /**
     * constructor for the class
     */
    public LinkedListWorkup(){
        first = null;
        last = null;
        
    }
    /**
     * checks if list is empty
     * @return the result of isEmpty test of the list
     */
    public Boolean isEmpty(){
        return first == null;
    }
    
    /**
     * this method will return the amount of elements in a linked list
     * @return the integer describing the size of the linked list
     */
    public int size(){
        int count = 0;
        Node p = first;
        while(p!= null){
            //there exists an element at p
            count ++;
            p = p.next;
        }return count;
    }
    
    /**
     * this method will add an element to the end of the list
     * @param e the list on which to add a link
     */
    public void addLast(T e){
        if(isEmpty()){              //checks for empty
            first = new Node(e);    //if empty, adds a new node
            last = first;           //assigns new node to the end
        }else{                          
            last.next = new Node(e);    //if contains something, creates a new
            last = last.next;           //node on the end and reassigns to last
        }
    }
    
    public void add(int i, T e){
        if(i<0 || i>size()){
            throw new IndexOutOfBoundsException();
        }
        //index is at least 0
        if(i == 0){
            first = new Node(e, first);
            if(last == null) last = first;
            return;
        }
        Node pred = first;
        for(int k=1; k<= i-1; k++){
            pred = pred.next;
        }
        pred.next = new Node(e, pred.next);
        if(pred.next.next == null) last = pred.next;
    }
    
    public T remove(int i){
        if(i<0 || i>=size()){
            throw new IndexOutOfBoundsException();
        }
        T element;
        if(i == 0){
            element = first.element;
            first = first.next;
            if(first == null) last = null;
            return element;
        }
        Node pred = first;                      //find the predecessor
        for(int k = 1; k<= i-1; k++){
            pred = pred.next;
        }
        element = pred.next.element;            //store element to return
        pred.next = pred.next.next;             //reroute
        if(pred.next == null) last = pred;      //check
        return element;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private class Node{
        T element;
        Node next;
        Node(T el, Node n){
            element = el;
            next = n;
            
        }
        Node(T el){
            element = el;
            next = null;
        }
    
}
    
}