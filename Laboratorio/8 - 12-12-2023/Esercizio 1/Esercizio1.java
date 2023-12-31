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

interface Container
{
    boolean isEmpty();
    void makeEmpty();
}

interface List extends Container
{
    void addFirst(Object obj);
    void addLast(Object obj);
    Object getFirst() throws EmptyLinkedListException;
    Object getLast() throws EmptyLinkedListException;
    Object removeFirst() throws EmptyLinkedListException;
    Object removeLast() throws EmptyLinkedListException;
}

interface ListIterator
{
    Object next() throws NoSuchElementException;
    boolean hasNext();
    void add(Object obj);
    void remove() throws IllegalStateException;
}

class LinkedList implements List
{
    private ListNode head, tail;

    public LinkedList()
    {
        makeEmpty();
    }

    public boolean isEmpty()
    {
        return head == tail;
    }

    public void makeEmpty()
    {
        head = tail = new ListNode();
    }
    
    public void addFirst(Object obj)
    {
        head.setElement(obj);
        ListNode newNode = new ListNode(null, head);
        head = newNode;
    }

    public void addLast(Object obj)
    {
        ListNode newNode = new ListNode(obj, null);
        tail.setNext(newNode);
        tail = newNode;
    }

    public Object getFirst() throws EmptyLinkedListException
    {
        if(isEmpty()) throw new EmptyLinkedListException();

        return head.getNext().getElement();
    }

    public Object getLast() throws EmptyLinkedListException
    {
        if(isEmpty()) throw new EmptyLinkedListException();

        return tail.getElement();
    }

    public Object removeFirst() throws EmptyLinkedListException
    {
        Object deleted = getFirst();
        head = head.getNext();
        head.setElement(null);
        return deleted;
    }

    public Object removeLast() throws EmptyLinkedListException
    {
        Object deleted = getLast();
        ListNode lastNode = head;
        while(lastNode.getNext() != tail)
        {
            lastNode = lastNode.getNext();
        }
        tail = lastNode;
        tail.setNext(null);
        return deleted;
    }

    public ListIterator getIterator()
    {
        return new LinkedListIterator(head);
    }
    
    private class LinkedListIterator implements ListIterator
    {
        private ListNode current, previous;

        public LinkedListIterator(ListNode h)
        {
            this.current = h;
            this.previous = null;
        }

        public Object next() throws NoSuchElementException
        {
            if(current.getNext() == null) throw new NoSuchElementException();
            
            previous = current;
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
            current.setNext(newNode);
            previous = current;
            current = previous.getNext();

            if(!hasNext()) LinkedList.this.tail = current;
        }
    
        public void remove() throws IllegalStateException
        {
            if(previous == null) throw new IllegalStateException();

            //da eliminare la posizione corrente
            previous.setNext(current.getNext());
            current = previous;
            previous = null; //questo mi permette di non invocarlo un altra volta senza non fare un'aggiunta
            
            if(!hasNext()) LinkedList.this.tail = current;
        }
    }
}

class ListNode
{
    private Object element;
    private ListNode next;

    public ListNode()
    {
        element = null;
        next = null;
    }

    public ListNode(Object element, ListNode next)
    {
        this.element = element;
        this.next = next;
    }

    public Object getElement()
    {
        return element;
    }

    public ListNode getNext()
    {
        return next;
    }

    public void setElement(Object element)
    {
        this.element = element;
    }

    public void setNext(ListNode next)
    {
        this.next = next;
    }
}

class EmptyLinkedListException extends RuntimeException
{
}
