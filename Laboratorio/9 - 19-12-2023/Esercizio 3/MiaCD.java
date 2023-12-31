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

    private final int ARRAY_DIM = 1;

    public MiaCD()
    {
        v = new Object[ARRAY_DIM];
        makeEmpty();
    }

    public boolean isEmpty()
    {
        return size == 0;
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

    /*
     * I seguenti due metodi servono rispettivamente per incrementare e decrementare un indice (first o last a seconda dei casi)
     * attraverso l'uso dell'algebra modulare
    */

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
        if(size == v.length) resizeAndFixOrder();

        if(isEmpty()) last = increment(last); //incremento last perché altrimenti esso rimarrebbe sulla cella dove si troverà il primo elemento

        v[first] = x;
        first = decrement(first);
        size++;
    }

    public void addLast(Object x)
    {
        if(size == v.length) resizeAndFixOrder();

        if(isEmpty()) first = decrement(first); //decremento last perché altrimenti esso rimarrebbe sulla cella dove si troverà il primo elemento

        v[last] = x;
        last = increment(last);
        size++;
    }

    /*
     * Il seguente metodo permette di raddoppiare le dimensioni della doppia coda e inserire gli elementi nell'array secondo 
     * l'ordine corretto (il primo alla prima cella (indice 0), il secondo nella seconda cella (indice 1), ecc...)
    */ 

    private void resizeAndFixOrder() 
    {
        Object[] tmp = new Object[v.length * 2];
        for(int i = 0; i < size; i++)
        {
            first = increment(first);
            tmp[i] = v[first];
        }
        first = tmp.length - 1;
        last = v.length;
        v = tmp;
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

        if(--size == 0) last = decrement(last);

        return temp;
    }

    public Object removeLast() throws EmptyCDException
    {
        Object temp = getLast();
        last = decrement(last);
        v[last] = null;

        if(--size == 0) first = increment(first);

        return temp;
    }

    //Metodo di resize inizialmente inserito ma non più necessario
    private static Object[] resize(Object[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(newLength <= oldArray.length) throw new IllegalArgumentException();

        Object[] newArray = new Object[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }
}