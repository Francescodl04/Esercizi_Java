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
    Object removeFirst() throws EmptyCDException;
    Object removeLast() throws EmptyCDException;
}

class MiaCD implements CD
{
    private Object[] v;
    private int size, first, last;

    private final int ARRAY_DIM = 100;

    public MiaCD()
    {
        v = new Object[ARRAY_DIM];
        makeEmpty();
    }

    public boolean isEmpty()
    {
        return first == last;
    }

    public void makeEmpty()
    {
        size = 0;
        first = 0;
        last = 0;
    }

    public int size()
    {
        return size;
    }

    private int increment(int index)
    {
        return (index + 1) % v.length;
    }

    private int decrement(int index)
    {
        return (index - 1 + v.length) % v.length;
    }

    public void addFirst(Object x)
    {
        if(size == v.length)
        {
            int oldLength = v.length;
            v = resize(v, v.length * 2);
            if(increment(first) > decrement(last))
            {
                Object[] firstPart = new Object[oldLength - first];
                Object[] secondPart = new Object[last];
                System.arraycopy(v, 0, secondPart, 0, last);
                System.arraycopy(v, first, firstPart, 0, oldLength);
                System.arraycopy(firstPart, 0, v, 0, firstPart.length);
                System.arraycopy(secondPart, 0, v, firstPart.length - 1, secondPart.length);
            }
            first = v.length - 1;
            last = oldLength;
        }
        v[first] = x;
        first = decrement(first);
        size++;
    }

    public void addLast(Object x)
    {
        if(size == v.length)
        {
            v = resize(v, v.length * 2);
        }
        v[last] = x;
        last = increment(last);
        size++;
    }

    public Object getFirst() throws EmptyCDException
    {
        if(isEmpty()) throw new EmptyCDException();

        return v[increment(first)];
    }

    public Object getLast() throws EmptyCDException
    {
        if(isEmpty()) throw new EmptyCDException();

        return v[decrement(last)];
    }

    public Object removeFirst() throws EmptyCDException
    {
        Object temp = getFirst();
        first = increment(first);
        v[first] = null;
        size--;
        return temp;
    }

    public Object removeLast() throws EmptyCDException
    {
        Object temp = getLast();
        last = decrement(last);
        v[last] = null;
        size--;
        return temp;
    }

    private static Object[] resize(Object[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(newLength <= oldArray.length) throw new IllegalArgumentException();

        Object[] newArray = new Object[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }
}