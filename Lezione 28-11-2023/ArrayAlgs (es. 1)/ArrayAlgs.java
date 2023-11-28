import java.util.*;

public class ArrayAlgs
{
    /*  NOTA
        Se questo fosse codice professionale, tutti i metodi seguenti
        conterrebbero un controllo delle pre-condizioni e lancerebbero
        eccezioni nel caso le pre-condizioni non fossero verificate.
        In questo caso abbiamo evitato di appesantire il codice con il
        controllo delle pre-condizioni, perche` ci interessa soprattutto
        studiare il funzionamento dei vari algoritmi di inserimento,
        rimozione, ricerca.
    */


    // ---------------- semplici algoritmi su array ---------------------------

    //rimuove dall'array v l'elemento v[index] e lo sostituisce con l'ultimo
    //elemento valido di v
    public static void remove(int[] v, int vSize, int index)
    {
        v[index] = v[vSize - 1];
    }

    //rimuove dall'array v l'elemento v[index] mantenendo l'ordine degli
    //elementi di indici piu` alti
    public static void removeSorted(int[] v, int vSize, int index)
    {
        for (int i = index; i < vSize - 1; i++)
            v[i] = v[i + 1];
    }

    //inserisce nell'array v il valore value, nella posizione index
    public static int[] insert(int[] v, int vSize, int index, int value)
    {
        if (vSize == v.length)
            v = resize(v, 2*v.length);
        for (int i = vSize; i > index; i--)
            v[i] = v[i - 1];
        v[index] = value;
        return v;
    }

    //cerca e restituisce il valore minimo tra quelli presenti nell'array v
    public static int findMin(int[] v, int vSize)
    {
        int min = v[0];
        for (int i = 1; i < vSize; i++)
            if (v[i] < min)
                min = v[i];
        return min;
    }

    //cerca e restituisce il valore massimo tra quelli presenti nell'array v
    public static int findMax(int[] v, int vSize)
    {
        int max = v[0];
        for (int i = 1; i < vSize; i++)
            if (v[i] > max)
                max = v[i];
        return max;
    }


    // ---------------------- algoritmi di ordinamento su Array ----------------

    // ordina l'array v usando l'algoritmo di "ordinamento per selezione"
    public static void selectionSort(int[] v, int vSize)
    {
        for (int i = 0; i < vSize - 1; i++)
        {   int minPos = findMinPos(v, i, vSize-1);
            if (minPos != i) swap(v, minPos, i);
        }
    } //metodo ausiliario per lo scambio (swap) tra due valori
    private static void swap(int[] v, int i, int j)
    {   int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    } //metodo ausiliario per la ricerca dell'indice contenente il val. minimo 
    private static int findMinPos(int[] v, int from, int to)
    {   int pos = from;
        int min = v[from];
        for (int i = from + 1; i <= to; i++)
            if (v[i] < min)
            {   pos = i;      
                min = v[i];   }
        return pos;
    }


    // ordina l'array v usando l'algoritmo di "ordinamento per fusione"
    public static void mergeSort(int[] v, int vSize)
    {
        if (vSize < 2) return; // caso base
        int mid = vSize / 2; //dividiamo circa a meta`
        int[] left = new int[mid];
        int[] right = new int[vSize - mid];
        System.arraycopy(v, 0, left, 0, mid);
        System.arraycopy(v, mid, right, 0, vSize-mid);
        // passi ricorsivi: ricorsione multipla (doppia)
        mergeSort(left, mid);
        mergeSort(right, vSize-mid);
        // fusione (metodo ausiliario)
        merge(v, left, right);
    } //metodo ausiliario per la fusione di due array ordinati v1 e v2
    private static void merge(int[] v, int[] v1, int[] v2)
    {  
        int i = 0, i1 = 0, i2 = 0;
        while (i1 < v1.length && i2 < v2.length)
            if (v1[i1] < v2[i2])
                // prima si usa i, poi lo si incrementa...
                v[i++] = v1[i1++];
            else
                v[i++] = v2[i2++];
        while (i1 < v1.length)
            v[i++] = v1[i1++];
        while (i2 < v2.length)
            v[i++] = v2[i2++];
    }



    // ordina l'array v usando l'algoritmo di "ordinamento per inserimento"
    public static void insertionSort(int[] v, int vSize)
    {   // il ciclo inizia da 1 perché il primo
        // elemento non richiede attenzione
        for (int i = 1; i < vSize; i++)
        {
            int temp = v[i]; // nuovo elemento da inserire
            // j va definita fuori dal ciclo perche'
            // il suo valore finale viene usato in seguito
            int j;
            // sposta a destra di un posto tutti gli el. a
            // sin. di temp e > di temp, partendo da destra
            for (j = i; j > 0 && temp < v[j-1]; j--)
                v[j] = v[j-1];
                v[j] = temp; // inserisci temp in posizione
        }
    }



    // --------------------- algoritmi di ricerca su Array ------------------


    //cerca il valore value nell'array v tramite l'algoritmo di "ricerca 
    //lineare". Se trovato, restituisce l'indice corrispondente, altrimenti 
    //restituisce -1
    public static int linearSearch(int[] v, int vSize, int value)
    {
        for (int i = 0; i < vSize; i++)
            if (v[i] == value) return i; // trovato valore
        return -1;  // valore non trovato
    }


    //cerca il valore value nell'array v tramite l'algoritmo di "ricerca 
    //binaria". Se trovato, restituisce l'indice corrispondente, altrimenti 
    //restituisce -1
    public static int binarySearch(int[] v, int vSize, int value)
    {
        return binSearch(v, 0, vSize-1, value);
    } // metodo ausiliario per consentire chiamate ricorsive
    private static int binSearch(int[] v, int from, int to, int value)
    {  
        if (from > to) return -1;// el. non trovato
        int mid = (from + to) / 2; // circa in mezzo
        int middle = v[mid];
        if (middle == value)
            return mid; // elemento trovato
        else if (middle < value)  //cerca a destra
            return binSearch(v, mid + 1, to, value);
        else // cerca a sinistra
            return binSearch(v, from, mid - 1, value);
   }    //ATTENZIONE: e` un algoritmo con ricorsione SEMPLICE



    // ----------------------- altri metodi di utilita` ---------------------

    // Ridimensiona l'array oldv attribuendogli la lunghezza newLength
    public static int[] resize(int[] oldv, int newLength)
    {
        if (newLength < 0 || oldv == null)
            throw new IllegalArgumentException();
        int[] newv = new int[newLength];
        int count = oldv.length;
        if (newLength < count) 
            count = newLength;
        for (int i = 0; i < count; i++)
            newv[i] = oldv[i];
        return newv;
    }

    // Costruisce un array contenente valori casuali compresi tra 0 e n-1
    public static int[] randomIntArray(int length, int n)
    {
        int[] v = new int[length];
        Random gen = new Random();
        for (int i = 0; i < v.length; i++)
            v[i] = gen.nextInt(n);  //cfr. documentazione di Random
        return v;
    }

    //Stampa tutti gli elementi di un array.
    public static String printArray(int[] v, int vSize)
    {
        String s = "[";
        for (int i = 0; i<vSize; i++)
            s = s + v[i] + " ";
        s = s + "\b]";
        return s;
    }
}

