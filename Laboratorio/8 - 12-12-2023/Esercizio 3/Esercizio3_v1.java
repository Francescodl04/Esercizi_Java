/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica 
 * 12-12-2023
 * Nota: prima soluzione con prestazioni minori (doppia pila temporanea)
*/

import java.util.Scanner;

public class Esercizio3_v1
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        Stack integerStack = new ArrayStack();
        
        System.out.println("Benvenuto. Inserisci quanti numeri interi desideri (inserisci Q per terminare l'inserimento):");
        do
        {
            try
            {
                String inputText = console.nextLine();
                if(inputText.equalsIgnoreCase("Q")) break;

                Scanner line = new Scanner(inputText);
                while(line.hasNext())
                {
                    integerStack.push(Integer.parseInt(line.next()));
                }
                line.close();
            }
            catch(NumberFormatException e)
            {
                System.out.println("Non hai inserito un numero. Riprova:");
                continue;
            }
        }
        while(true);
        console.close();
        reverseStack(integerStack);
        System.out.println("Hai realizzato la seguente pila (invertita):");
        while(!integerStack.isEmpty())
        {
            System.out.println(integerStack.pop());
        }
    }

    public static void reverseStack(Stack oldStack)
    {
        Stack reversedStack = new ArrayStack();
        Stack doubleReversedStack = new ArrayStack();
        while(!oldStack.isEmpty())
        {
            reversedStack.push(oldStack.pop());
        }
        while(!reversedStack.isEmpty())
        {
            doubleReversedStack.push(reversedStack.pop());
        }
        while(!doubleReversedStack.isEmpty())
        {
            oldStack.push(doubleReversedStack.pop());
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

        Object deleted = top();
        v[vSize - 1] = null;
        vSize--;
        return deleted;
    }

    private Object[] resize(Object[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(oldArray.length >= newLength) throw new IllegalArgumentException();

        Object[] newArray = new Object[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }
}

class EmptyStackException extends RuntimeException
{}