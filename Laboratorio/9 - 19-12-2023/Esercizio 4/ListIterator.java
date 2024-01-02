/*
 * Francesco Di Lena
 * Esercizio 4 - laboratorio di fondamenti di informatica
 * 19-12-2023
*/

import java.util.NoSuchElementException;

public interface ListIterator
{
    Object next() throws NoSuchElementException;
    boolean hasNext();
    void add(Object obj);
    void remove() throws IllegalStateException;
}