
import java.util.Scanner;

public class DiLena2107407
{
    public static void main(String[] args)
    {
        if(args.length == 0) throw new IllegalArgumentException();

        int nPile = Integer.parseInt(args[0]);
        MultiStack p = new MyMultiStack(nPile);
        for(int i = 1; i < args.length; i++)
        {
            p.add(args[i], nPile - 1);
        }
        Scanner console = new Scanner(System.in);
        while(console.hasNextLine())
        {
            Scanner row = new Scanner(console.nextLine());
            while(row.hasNext())
            {
                p.add(row.next());
            }
        }
        p.remove();
        p.remove();
        System.out.println(p);
    }
}

interface Container
{
    /*
        verifica se il contenitore e' vuoto
        restituisce true se il contenitore e' vuoto, false altrimenti
    */
    boolean isEmpty();

    /*
        rende vuoto il contenitore
    */
    void makeEmpty();
}

interface Stack extends Container
{  
    void push(Object obj);
    Object pop();
    Object top();
}

class EmptyStackException extends RuntimeException
{

}

class EmptyMultiStackException extends RuntimeException
{

} 

class ArrayStack implements Stack
{  
    protected Object[] v;
    protected int vSize;

    public ArrayStack()
    {  
        v = new Object[100];
        makeEmpty();
    }
   
    public void makeEmpty()
    {  
        vSize = 0;
    }

    public boolean isEmpty()
    {  
        return (vSize == 0);
    }
   
    public void push(Object obj)
    {  
        if (vSize == v.length) v = resize(v, 2*vSize);

        v[vSize++] = obj;
    }
   
    public Object top()
    {  
        if (isEmpty()) throw new EmptyStackException();
        
        return v[vSize - 1];
    }
    
    public Object pop()
    {  
        Object obj = top();
        vSize--;
        return obj;
    }

    protected Object[] resize(Object[] oldArray, int newLength)
    {  
        if (newLength < oldArray.length) throw new IllegalArgumentException();
        Object[] newArray = new Object[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
   }
}


interface MultiStack extends Container
{
    //restituisce la dimensione della pila di indice i, cioe' il numero di elementi
    //contenuti nella pila di indice i;lancia IllegalArgumentException se il valore  di
    //i non e' corretto;
    int dim(int i);

    //aggiunge x nella pila di indice i della multipila
    //richiede x!= null;
    //lancia IllegalArgumentException se il valore di i non e' corretto;
    void add(Object x, int i);

    //aggiunge x nella pila della multipila che contiene il minor numero di elementi
    //richiede x!= null;
    void add(Object x);

    //viene eliminato il dato dalla pila che contiene il maggior numero di elementi;
    //richiede che la multipila non sia vuota
    void remove();
}

class MyMultiStack implements MultiStack
{
    // questa classe non puo' avere variabili statiche;

    private Stack[] v;

    /*
     * Il costruttore riceve come parametro un numero intero (maggiore o uguale a 2) che rappresenta il numero 
     * di pile contenute nella multipila e crea una multipila vuota.
     * Lancia IllegalArgumentException se il parametro non soddisfa il prerequisito.
    */ 

    public MyMultiStack(int n)
    {
        if(n < 2) throw new IllegalArgumentException();

        v = new ArrayStack[n];
        for(int i = 0; i < n; i++)
        {
            v[i] = new ArrayStack();
        }
    }

    /*
        verifica se il contenitore e' vuoto
        restituisce true se il contenitore e' vuoto, false altrimenti
    */

    public boolean isEmpty()
    {
        for(int i = 0; i < v.length; i++)
        {
            if(!v[i].isEmpty()) return false;
        }
        return true;
    }

    /*
        rende vuoto il contenitore
    */

    public void makeEmpty()
    {
        if(isEmpty()) return;

        for(int i = 0; i < v.length; i++)
        {
            v[i].makeEmpty();
        }
    }

    //restituisce la dimensione della pila di indice i, cioe' il numero di elementi
    //contenuti nella pila di indice i;lancia IllegalArgumentException se il valore  di
    //i non e' corretto;
    public int dim(int i)
    {
        if(i < 0 || i >= v.length) throw new IllegalArgumentException();

        Stack tmp = new ArrayStack();
        int size = 0;

        while(!v[i].isEmpty())
        {
            tmp.push(v[i].pop());
            size++;
        }

        v[i] = tmp; //Faccio questo perché non è richiesto il mantenimento dell'ordinamento
        return size;
    }

    //aggiunge x nella pila di indice i della multipila
    //richiede x!= null;
    //lancia IllegalArgumentException se il valore di i non e' corretto;
    public void add(Object x, int i)
    {
        if(x == null || i < 0 || i >= v.length) throw new IllegalArgumentException();

        v[i].push(x);
    }

    //aggiunge x nella pila della multipila che contiene il minor numero di elementi
    //richiede x!= null;
    public void add(Object x)
    {
        if(x == null) throw new IllegalArgumentException();

        int min = 0;

        for(int i = 1; i < v.length; i++)
        {
            if(this.dim(i) < this.dim(min)) min = i;
        }

        this.add(x, min);
    }

    //viene eliminato il dato dalla pila che contiene il maggior numero di elementi;
    //richiede che la multipila non sia vuota
    public void remove()
    {
        if(v == null) throw new EmptyMultiStackException();

        int max = 0;

        for(int i = 1; i < v.length; i++)
        {
            if(this.dim(i) > this.dim(max)) max = i;
        }

        v[max].pop();
    }

    public String toString()
    {
        String count = "";
        for(int i = 0; i < v.length; i++)
        {
            count += dim(i) + " ";
        }
        return count;
    }
}