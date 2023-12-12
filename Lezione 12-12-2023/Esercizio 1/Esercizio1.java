/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 12-12-2023
 */
 
 import java.util.Scanner;
 import java.util.NoSuchElementException;
 
 public class Esercizio1
 {
 	public static void main(String[] args)
 	{
 		LinkedList list = new LinkedList();
   		list.addFirst("A");
   		list.addLast("B");
   		list.addFirst("C");
   		ListIterator iter = list.getIterator();
 		iter.next();
   		iter.add("I");
   		while (iter.hasNext())
   		{
     			iter.next();
   		}
  		iter.remove();
   		list.addLast("O");
   		iter = list.getIterator();
   		while (iter.hasNext())
   		{
      			System.out.print(iter.next());
		}
 	}
 }
 
 interface ListIterator
 {
 	Object next();
 	
 	boolean hasNext();
 	
 	void add(Object obj);
 	
 	void remove();
 }
 
 interface Container
 {
 	boolean isEmpty();
 	
 	void makeEmpty();
 }
 
 interface List extends Container
 {
 	ListIterator getIterator();
 }
 
 class EmptyLinkedListException extends RuntimeException
 {}
 
 class LinkedList implements List
 {
 	private ListNode head, tail;
 	
 	public LinkedList()
 	{
 		makeEmpty();
 	}
 	
 	public boolean isEmpty()
 	{
 		return (head == tail);
 	}
 	
 	public void makeEmpty()
 	{
 		head = tail = new ListNode();
 	}
 	
 	public Object getFirst()
 	{
 		if(isEmpty()) throw new EmptyLinkedListException();
 		
 		return head.getNext().getElement();
 	}
 	
 	public Object getLast()
 	{
 		if(isEmpty()) throw new EmptyLinkedListException();
 		
 		return tail.getElement();
 	}
 	
 	public ListIterator getIterator()
 	{
 		return new ListIterator(head);
 	}
 	
 	public void addFirst(Object obj)
 	{
 		ListNode newNode = new ListNode(obj, head.getNext());
 		head.setNext(newNode);
 	}
 	
 	public void addLast(Object obj)
 	{
 		ListNode newNode = new ListNode(obj, null);
 		tail.setNext(newNode);
 		tail = newNode;
 	}
 	
 	public Object removeFirst()
 	{
 		//Controllo se possiedo il primo elemento ed eventualmente con il metodo getFirst lancio un'eccezione
 		Object first = getFirst();
 		
 		head = head.getNext();
 		head.setElement(null);
 		return first;
 		
 	}
 	
 	public Object removeLast()
 	{
 		//Controllo se possiedo l'ultimo elemento ed eventualmente con il metodo getLast lancio un'eccezione
 		Object last = getLast();
 		
 		ListNode temp = head;
 		while(temp.getNext() != tail)
 		{
 			temp = temp.getNext();
 		}
 		temp.setNext(null);
 		tail = temp;
 		return last;
 	}
 	
 	private class ListNode
 	{
 		private Object element;
 		private ListNode next;
 	
 		public ListNode(Object element, ListNode next)
 		{
 			this.element = element;
 			this.next = next;
 		}
 	
 		public ListNode()
 		{
 			this.element = null;
 			this.next = null;
 		}
 	
 		public Object getElement()
 		{
 			return element;
 		}
 	
 		public ListNode getNext()
 		{
 			return next;
 		}
 	
 		public void setElement(Object obj)
 		{
 			element = obj;
 		}
 	
 		public void setNext(ListNode ln)
 		{
 			next = ln;
 		}
 	}
 	
 	private class LinkedListIterator implements ListIterator
 	{
 		private ListNode current;
 		 
 		public LinkedListIterator(ListNode head)
 		{
 			current = head;
 		}
 		
 		public Object next()
 		{
 			if(!hasNext()) throw new IllegalStateException();
 			
 			current = current.getNext();
 			return current.getElement();
 		}
 	
 		public boolean hasNext()
 		{
 			return current.getNext() != null;
 		}
 	
 		public void add(Object obj)
 		{
 			ListNode newNode = new ListNode(obj, current.getNext());
 			current = current.getNext();
 			if(!hasNext()) LinkedList.this.tail = current;
 		}
 	
 		public void remove()
 		{
 			current.setNext(current.getNext());
 		}
 	}
 }
