/*
 * Francesco Di Lena
 * Esercizio 4 - laboratorio di fondamenti di informatica
 * 19-12-2023
*/

interface Container
{
    boolean isEmpty();
    void makeEmpty();
}

interface Comparable <List>
{
    int compareTo(List list2);
}

public interface List extends Container, Comparable <List>
{
    void addFirst(Object obj);
    void addLast(Object obj);
    Object getFirst() throws EmptyLinkedListException;
    Object getLast() throws EmptyLinkedListException;
    Object removeFirst() throws EmptyLinkedListException;
    Object removeLast() throws EmptyLinkedListException;
    ListIterator getIterator();
}