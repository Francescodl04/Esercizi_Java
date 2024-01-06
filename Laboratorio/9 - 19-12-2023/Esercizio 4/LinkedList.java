/*
 * Francesco Di Lena
 * Esercizio 4 - laboratorio di fondamenti di informatica
 * 19-12-2023
 * Classe fabbrica di oggetti LinkedList, modificata oppurtamente per permettere il confronto tra stringhe come indicato nella consegna di esercizio
*/

import java.util.NoSuchElementException;

public class LinkedList implements List
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

    public int compareTo(List list2)
    {
        ListIterator iterator1 = this.getIterator();
        ListIterator iterator2 = list2.getIterator();
        int nWords1 = 0, nWords2 = 0;

        //Prima fase: memorizzo quante parole hanno le due righe e le confronto

        while(iterator1.hasNext())
        {
            iterator1.next();
            nWords1++;
        }
        while(iterator2.hasNext())
        {
            iterator2.next();
            nWords2++;
        }

        if(nWords1 == nWords2)
        {

            //Seconda fase: memorizzo i caratteri delle due righe e ne confronto il numero
            char[] row1Characters = this.toString().toCharArray();
            char[] row2Characters = list2.toString().toCharArray();

            if(row1Characters.length == row2Characters.length)
            {
                //Terza fase: faccio un semplice confronto lessicografico delle due righe (intese come stringhe) con il metodo compareTo
                return this.toString().compareTo(list2.toString());
            }
            else
            {
                if(row1Characters.length > row2Characters.length) return 1;

                return -1;
            }
        }
        else
        {
            if(nWords1 > nWords2) return 1;

            return -1;
        }
    }

    public boolean equals(LinkedList list2)
    {
        return this.toString().equals(list2.toString());
    }

    public String toString()
    {
        ListIterator iterator = getIterator();
        String tmp = "";
        while(iterator.hasNext())
        {
            tmp += " " + iterator.next();
        }
        return tmp;
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