/*
 * Francesco Di Lena
 * Esercizio 3 - laboratorio di fondamenti di informatica
 * 19-12-2023
 * Classe coda doppia
*/


interface Container
{
    boolean isEmpty();
    void makeEmpty();
}

interface CD extends Container
{
    int size();
    void addFirst(Object x);
    void addLast(Object x);
    Object getFirst() throws EmptyCDException;
    Object getLast() throws EmptyCDException;
    Object removeFirst(Object x) throws EmptyCDException;
    Object removeLast(Object x) throws EmptyCDException;
}

class MiaCD implements CD
{

    public MiaCD()
    {

    }

    public boolean isEmpty()
    {
        return false;
    }

    public void makeEmpty()
    {

    }

    public int size()
    {
        return 0;
    }

    public void addFirst(Object x)
    {

    }

    public void addLast(Object x)
    {

    }

    public Object getFirst() throws EmptyCDException
    {
        return null;
    }

    public Object getLast() throws EmptyCDException
    {
        return null;
    }

    public Object removeFirst(Object x) throws EmptyCDException
    {
        return null;
    }

    public Object removeLast(Object x) throws EmptyCDException
    {
        return null;
    }
}