/*
 * Francesco Di Lena
 * Esercizio in aula - Fondamenti di informatica
 * 23-11-2023
*/

import java.util.*;

public class SortedArray
{
    private final int V_INITIAL_SIZE = 10;

    private int[] v;
    private int vSize;

    /*  Crea un oggetto SortedArray vuoto     */
    public SortedArray()
    { 
        v = new int[V_INITIAL_SIZE];
        vSize = 0;
    }

    /*  Verifica se l'array e` vuoto. Prestazioni O(1)    */
    public boolean isEmpty()
    {
        return (vSize == 0);
    }

    /*  Aggiunge il valore value all'array ordinato, conservando l'ordinamento.
        Prestazioni O(n) (prima dell'inserimento l'array e' ordinato!)
    */
    public void add(int value)
    { 
        if(vSize == v.length)
        {
            v = resize(v, 2 * vSize);
        }
        // v[vSize++] = value; //uso il vecchio valore di vSize per l'inserimento del nuovo valore, poi effettuo l'incremento
        v[vSize] = value;
        int j;
        for(j = vSize - 1; j >= 0 && v[j] > value; j--)
        {
            v[j + 1] = v[j];
        }
        v[j + 1] = value;
        vSize++;
    }

    /* Cancella il valore massimo dall'array, e lo restituisce.
        Prestazioni O(1). Lancia NoSuchElementException se l'array e' vuoto
    */
    public int removeMax() throws NoSuchElementException
    { 
        if(isEmpty()) throw new NoSuchElementException();
        return v[--vSize];
    }

    /*  Restituisce la media (average) dei valori dell'array
        Prestazioni O(n)
    */
    public double avg()
    {
        double sum = 0;
        for(int i = 0; i < vSize; i++)
        {
            sum += v[i];
        }
        return sum / vSize; 
    }

    public static int[] resize(int[] old, int newLength)
    {
        int[] newV = new int[newSize];
        //da completare
    }
}