/*
 * Francesco Di Lena
 * Esercizio 4 - Laboratorio di fondamenti di informatica 
 * 12-12-2023
 * Nota: prima soluzione con prestazioni minori (doppia pila temporanea)
*/

import java.util.Scanner;

public class Esercizio4_v1
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        Stack integerStack = new ArrayStack();

        System.out.println("Benvenuto. Inserisci i numeri che desideri (termina l'inserimento con Q nella riga successiva):");
        do
        {
            String inputText = console.nextLine();

            if(inputText.equalsIgnoreCase("Q")) break;

            Scanner line = new Scanner(inputText);
            while(line.hasNext())
            {
                try
                {
                    integerStack.push(Integer.parseInt(line.next()));
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Non hai inserito un numero, riprova...");
                    continue;
                }
            }
        }
        while(true);
        
        if(integerStack.isEmpty()) System.exit(0);

        deleteDuplicates(integerStack);
        System.out.println("Questa e' la pila senza doppioni:");
        while(!integerStack.isEmpty())
        {
            System.out.println(integerStack.pop());
        }
    }

    public static void deleteDuplicates(Stack originalStack)
    {
        Stack temp1 = new ArrayStack();

        int elements = 0; //numero di elementi della pila
        while(!originalStack.isEmpty())
        {
            temp1.push(originalStack.pop());
            elements++;
        }
        
        for(int i = 0; i < elements; i++)
        {
            //inserisco il primo elemento della pila (che sono sicuro non essere un duplicato) nella pila originale
            Object current = temp1.pop();
            originalStack.push(current);
            int nDuplicates = 0;

            //determino se esistono o meno dei doppioni da qui in poi
            Stack temp2 = new ArrayStack();
            while(!temp1.isEmpty())
            {
                if(!current.equals(temp1.top())) //se non è un doppione, allora lo inserisco nella pila senza doppioni
                {
                    temp2.push(temp1.pop());
                }
                else //se è un doppione lo elimino senza salvarlo
                {
                    temp1.pop();
                    nDuplicates++;
                }
            }
            elements -= nDuplicates;
            while(!temp2.isEmpty()) temp1.push(temp2.pop());
        }
    }
}

interface Container
{
    boolean isEmpty();
    void makeEmpty();
}

interface Stack extends Container
{
    void push(Object obj);
    Object top();
    Object pop();
}

class ArrayStack implements Stack
{
    private Object[] v;
    private int vSize;

    private final int ARRAY_DIM = 100;

    public ArrayStack()
    {
        v = new Object[ARRAY_DIM];
        makeEmpty();
    }

    public boolean isEmpty()
    {
        return vSize == 0;
    }

    public void makeEmpty()
    {
        vSize = 0;
    }

    public void push(Object obj)
    {
        if(vSize >= v.length) v = resize(v, v.length * 2);

        v[vSize++] = obj;
    }

    public Object top() throws EmptyStackException
    {
        if(isEmpty()) throw new EmptyStackException();

        return v[vSize - 1];
    }

    public Object pop() throws EmptyStackException
    {
        if(isEmpty()) throw new EmptyStackException();

        Object deleted = v[vSize - 1];
        v[vSize - 1] =  null;
        vSize--;
        return deleted;
    }

    public Object[] resize(Object[] oldArray, int newLength)
    {
        if(oldArray.length >= newLength) throw new IllegalArgumentException();

        Object[] newArray = new Object[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }
}

class EmptyStackException extends RuntimeException
{}