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
 */
public class LinkedListWorkup<T> {
    
    public LinkedListWorkup(){
        
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