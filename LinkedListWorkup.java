/**
 * This program will make use of and implement a LinkedList with methods for
 * adding data to the list, remove an item by position, remove an item by value,
 * clear all elements from a list, get an item based on the position, and output
 * the contents of a list.
 */
package linkedlistworkup;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.util.ArrayList;

/**
 *
 * @author Jacquelyn Johnson
 * Course: COSC 2336-2803
 * Date: March 27, 2019
 * @param <E> generic type to be made into a linked list
 */ 
public class LinkedListWorkup<E> {
    private Node first;
    private Node last;
    private Node temp;
    
    
    /**
     * constructor for the class
     */
    public LinkedListWorkup(){
        first = null;
        last = null;
        temp = null;
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
    public void add(E e){
        if(isEmpty()){              //checks for empty
            first = new Node(e);    //if empty, adds a new node
            last = first;           //assigns new node to the end
        }else{                          
            last.next = new Node(e);    //if contains something, creates a new
            last = last.next;           //node on the end and reassigns to last
        }
    }
    
    /**
     * this method will add an element at specific index
     * @param i the index at which to insert the element
     * @param e the item to insert in the list
     */
    public void add(int i, E e){
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
    
    /**
     * this method will remove an element from a list
     * @param i
     * @return 
     */
    public E remove(int i){
        if(i<0 || i>=size()){
            throw new IndexOutOfBoundsException();
        }
        E element;
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
    
    public E get(int index){
        //forces int to be valid
        assert(index >= 0 && index < size());
        
        temp = first;
        for(int i = 0; i<index; i++){
            temp = temp.next;
        }return temp.element;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        LinkedListWorkup ll = new LinkedListWorkup();
        
        InputStream is = new FileInputStream("/home/owner/NetBeansProjects/linkedListWorkup/src/linkedlistworkup/names.dat");;
        DataInputStream dis = new DataInputStream(is);
        
        while(dis.available()>0){
            String k = dis.readUTF();
            ll.add(k);
        }
        System.out.println(ll.size());
        System.out.println(ll.isEmpty());
        System.out.println(ll.get(0));
    }
    
    /**
     * private inner class creating nodes
     */
    private class Node{
        E element;              
        Node next;
        Node(E el, Node n){
            element = el;
            next = n;
            
        }
        Node(E el){
            element = el;
            next = null;
        }
    
}
    
}
