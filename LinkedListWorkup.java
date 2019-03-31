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
    
    private int counter = 0;
    
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
     * @param i the index of the node to remove
     * @return 
     */
    public E remove(int i){
        if(i<0 || i>size()){       //check size against the index provided 
            throw new IndexOutOfBoundsException();  //throw exception if not in size
        }
        E element;
        if(i == 0){
            element = first.element;
            first = first.next;
            if(first == null) last = null;
            return element;
        }
        if(i == size()){
            deleteLast(last);
        }
        
        Node pred = first;                      //find the predecessor
        for(int k = 1; k< i-1; k++){
            pred = pred.next;
        }
        element = pred.next.element;            //store element to return
        pred.next = pred.next.next;             //reroute
        if(pred.next == null) last = pred;      //check
        return element;
    }
    public E deleteLast(Node head){
        if(head == null){
            return head.element;
        }
        Node preToLast = null;
        Node left = head;
        
        while(left.next != null){
            preToLast = left;
            left = left.next;
        }
        return left.element;
    }
    
    /**
     * this method returns the contents of a link
     * @param index the location at which to pull the element from the list
     * @return the element stored in that particular link of the list
     */
    public E get(int index){
        //forces int to be valid
        assert(index >= 0 && index < size());   //forces validity
        
        temp = first;
        for(int i = 0; i<index; i++){
            temp = temp.next;
        }return temp.element;
    }
    /**
     * this method removes an node based on the element contained
     * @param elem  the element to search the list for
     * @return 
     */   
    public E remove(E elem){
        temp = first;       //starts at the beginning of the list
        Node two = null;
        
        if(first.element.equals(elem)){     //checks first element in the list
            first = first.next;
            first.previous = null;
            counter--;
            return elem;
        }
        else if(last.element.equals(elem)){ //checks last element in the list
            last = last.previous;
            last.next = null;
            counter--;
            return elem;
        }
        //while the element has not been found but more nodes remain...traverse
        while(temp != null && !temp.element.equals(elem)){
            two = temp;     //holds reference to elem before one to remove
            temp = temp.next;   //iterates to next in list of nodes
        }
        //if not found, return null
        if(temp == null){
            return null;
        }
        two.next = temp.next;
        E spare = temp.element;     //return element
        temp = null;                //clear temp
        counter--;                  //decrement counter
        return spare;
    }
    
    public void display(){
        temp = first;
        if(isEmpty()){
            System.out.println("\nList is Empty");
        }else
        {
            while(temp !=null){
                System.out.println(temp.element);
                temp = temp.next;
            }
        }
    }
    
    public void removeAll(){
        temp = first;
        if(isEmpty()){
            System.out.println("\nList is Empty");
        }else
        {
            while(temp != null){
                remove(0);
                temp = temp.next;
            }
        }
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
        //System.out.println(ll.isEmpty());
        System.out.println(ll.get(0));
        System.out.println(ll.last.element);
        System.out.println(ll.remove(ll.size()));
        System.out.println(ll.last.element);
        System.out.println(ll.remove(ll.size()));
        System.out.println(ll.get(2));
        ll.add("45");
        //ll.display();
        ll.removeAll();
        
    }
    
    /**
     * private inner class creating nodes
     */
    private class Node{
        E element;              
        Node next, previous;
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
//not ready to part with this yet

////public E remove(int i){
////        assert(i>= 0 && i<size());
////        temp = first;
////        
////        if(i==0){
////            E elem = first.element;
////            first = first.next;
////            counter--;
////            return elem;
////        }else if(i == size()){
////            E elem = last.element;
////            last = last.previous;
////            counter--;
////            return elem;
////        }
////        
////        for(int f=0; f<i-1; f++){
////            temp = temp.next;
////        }
////        Node two = temp.next;
////        temp.next = two.next;
////        E elem = two.element;
////        two = null;     //remove the node
////        counter--;
////        return elem;
////    }