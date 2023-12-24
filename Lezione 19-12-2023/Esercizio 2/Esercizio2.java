/*
 * Francesco Di Lena
 * Esercizio 2 - laboratorio di fondamenti di informatica
 * 19-12-2023
*/

import java.util.Scanner;

public class Esercizio2
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        Queue integerQueue = new CircularArrayQueue();

        System.out.println("Benvenuto. Inserisci quanti numeri desideri (termina l'inserimento con Q):");
        do
        {
            String inputText = console.nextLine();
            if(inputText.equalsIgnoreCase("Q")) break;
            Scanner line = new Scanner(inputText);
            while(line.hasNext())
            {
                try
                {
                    integerQueue.enqueue(Integer.parseInt(line.next()));
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Non hai inserito un numero intero, riprova...");
                    continue;
                }
            }
        }
        while(true);
        deleteDuplicates(integerQueue);
        System.out.println("Ecco ora la coda senza doppioni:");
        while(!integerQueue.isEmpty())
        {
            System.out.println(integerQueue.dequeue()); 
        }
    }

    public static void deleteDuplicates(Queue queue)
    {
        String endFlag = "* QUEUE_END *"; //dichiaro il flag che mi permette di capire quando finisce la coda
        int counter = 0; //numero elementi coda
        queue.enqueue(endFlag);

        if(queue.isEmpty()) return; //non eseguo nessuna operazione se la coda è vuota

        do //conto gli elementi all'interno della coda
        {
            queue.enqueue(queue.dequeue());
            counter++;
        }
        while(!queue.getFront().equals(endFlag));

        queue.dequeue(); //elimino il flag di fine coda

        int examinedObjects = 0; //mi servirà per capire gli oggetti già esaminati nel ciclo successivo e che sicuramente non presenteranno doppioni

        while(counter != examinedObjects) //ciclo per eliminare i doppioni: mi fermo solo quando avrò esaminato tutti gli elementi della coda
        {
            for(int i = 0; i < examinedObjects; i++) //ignoro gli eventuali oggetti già esaminati
            {
                queue.enqueue(queue.dequeue());
            }
            Object current = queue.dequeue(); //oggetto corrente da esaminare
            queue.enqueue(current); //metto l'oggetto corrente alla fine della coda
            examinedObjects++;

            int duplicatesCounter = 0; //numero di duplicati da sottrarre alla variabile counter
            for(int i = examinedObjects; i < counter; i++) //ciclo per trovare i doppioni: considero solamente gli elementi da analizzare e non quelli già analizzati precedentemente
            {
                Object temp = queue.dequeue();
                if(temp.equals(current)) duplicatesCounter++;
                else queue.enqueue(temp);
            }
            counter -= duplicatesCounter; 
        }
    }
}

interface Container
{
    boolean isEmpty();
    void makeEmpty();
}

interface Queue extends Container
{
    void enqueue(Object obj);
    Object getFront();
    Object dequeue();
}

class CircularArrayQueue implements Queue
{
    private int front, back;
    private Object[] v;

    private final int ARRAY_DIM = 100;

    public CircularArrayQueue()
    {
        v = new Object[ARRAY_DIM];
        makeEmpty();
    }

    public boolean isEmpty()
    {
        return (front == back);
    }

    public void makeEmpty()
    {
        front = back = 0;
    }

    protected int increment(int index) //metodo che mi permette di avanzare di indice pur rispettando la circolarità dell'array
    {
        return (index + 1) % v.length;
    }

    public void enqueue(Object obj) throws IllegalArgumentException
    {
        if(obj == null) throw new IllegalArgumentException();

        if(increment(back) == front)
        {
            resize(v, v.length * 2);
            if(front > back) 
            {
                int oldBack = back;
                back += v.length / 2;
                System.arraycopy(v, 0, v, back - oldBack , oldBack);
            }
        }
        v[back] = obj;
        back = increment(back);
    }

    public Object getFront() throws EmptyQueueException
    {
        if(isEmpty()) throw new EmptyQueueException();

        return v[front];
    }

    public Object dequeue() throws EmptyQueueException
    {
        Object temp = getFront();
        v[front] = null;
        front = increment(front);
        return temp;
    }

    protected Object[] resize(Object[] oldArray, int newLength) throws IllegalArgumentException
	{
		if (oldArray.length >= newLength) throw new IllegalArgumentException();

		Object[] newArray = new Object[newLength];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}
}

class EmptyQueueException extends RuntimeException
{}

class FullQueueException extends RuntimeException
{}