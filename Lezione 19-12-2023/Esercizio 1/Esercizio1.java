/*
 * Francesco Di Lena
 * Esercizio 1 - laboratorio di fondamenti di informatica
 * 19-12-2023
 * Nota: prima versione senza approfondimento (due pile temporanee)
*/

import java.util.Scanner;

public class Esercizio1
{
	
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		Stack integerStack = new ArrayStack();
		
		System.out.println("Benvenuto. Inserisci i numeri che desideri (inserisci Q per terminare l'inserimento):");
		do
		{
			String inputText = console.nextLine();
			
			if(inputText.equalsIgnoreCase("Q")) break;
			
			Scanner line = new Scanner(inputText);
			
			try
			{
				while(line.hasNext())
				{
					String token = line.next();
					integerStack.push(Integer.parseInt(token));
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Non hai inserito un numero, riprova...");
				continue;
			}
		}
		while(true);
		integerStack = selectionSort(integerStack);
		System.out.println("Ecco ora gli elementi della pila ordinati in modo crescente:");
		while(!integerStack.isEmpty())
		{
			System.out.println(integerStack.pop());
		}
		console.close();
	}
	
	public static Stack selectionSort(Stack originalStack)
	{
		Stack orderedStack = new ArrayStack();
		
		while(!originalStack.isEmpty())
		{
			Stack remainingsStack = new ArrayStack();
			Integer min = (Integer) originalStack.pop();
			while(!originalStack.isEmpty())
			{
				Integer current = (Integer) originalStack.pop();
				if(current.compareTo(min) < 0)
				{
					remainingsStack.push(min);
					min = current;
				}
				else remainingsStack.push(current);
			}
			orderedStack.push(min);
			originalStack = remainingsStack;
		}
		while(!orderedStack.isEmpty())
		{
			originalStack.push(orderedStack.pop());
		}
		return originalStack;
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
	
	public void push(Object obj) throws IllegalArgumentException
	{
		if(obj == null) throw new IllegalArgumentException();
		
		v[vSize++] = obj;
	}
	
	public Object top() throws EmptyStackException
	{
		if(isEmpty()) throw new EmptyStackException();
		
		return v[vSize - 1];
	}
	
	public Object pop() throws EmptyStackException
	{
		Object temp = top();
		
		v[--vSize] = null;
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

class EmptyStackException extends RuntimeException
{
}
