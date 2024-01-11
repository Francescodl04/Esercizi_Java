/*
 * Francesco Di Lena
 * Laboratorio di fondamenti di informatica
 * Esercizio 2 - 27-12-2023
*/

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.IOException;


// --------------- classe FavoriteListTester: da completare ------------------

public class FavoriteListTester
{
    public static void main(String[] args)
    {   
        System.out.println("Benvenuto nel programma.");
        ListaDownload downloads = new ListaDownload();
        Scanner console = new Scanner(System.in);
        try
        {
            FileReader reader = new FileReader("downloads.txt");
            Scanner file = new Scanner(reader);
            while(file.hasNextLine())
            {
                downloads.access(file.nextLine());
            }
            file.close();
        }
        catch(IOException e)
        {
            System.out.println("Non e` stato possibile leggere il file: controllare che esista nella cartella corrente.");
            console.close();
            System.exit(1);
        }
        do
        {
            if(downloads.isEmpty()) break;

            String[] topTen = downloads.top(10);
            System.out.println("Ecco la top ten dei file piu` scaricati:\n");
            for(String element : topTen)
            {
                System.out.println(element);
            }
            System.out.print("\nInserisci ora quale file vuoi eliminare dalla lista (inserisci Q per terminare): ");
            try
            {
                String fileToBeDeleted = console.nextLine();
                
                if(fileToBeDeleted.equalsIgnoreCase("Q")) break;

                downloads.remove(fileToBeDeleted);
                System.out.println("Eliminazione effettuata con successo, premere un tasto qualsiasi per proseguire...");
                console.nextLine();
            }
            catch(FLItemNotFoundException e)
            {
                System.out.println("Il file inserito non esiste nella lista, premi un tasto qualsiasi per continuare...");
                console.nextLine();
            }
        }
        while(true);
        console.close();
    }
}

// ------------------- classe ListaDowload: da completare --------------------

/*
    Classe che implementa l'interfaccia FavoriteList e contiene coppie di tipo
    "file accessi" appartenenti alla classe Coppia (realizzata come classe 
    interna a ListaDownload).
    La classe interna Coppia contribuisce a realizzare il tipo di dato astratto
    FavoriteList (cfr. commenti all'interfaccia FavoriteList). In particolare 
    la variabile intera "accessi" di ciascuna coppia rappresenta il contatore
    degli accessi di ciascun oggetto (un file, in questo caso) presente nella
    lista dei preferiti.
*/
class ListaDownload implements FavoriteList
{
    private Coppia[] downloads;
    private int downloadsSize;
    private static final int ARRAY_DIM = 100;

    public ListaDownload()
    {
        makeEmpty();
    }

    /*
     Metodo isEmpty
     Restituisce true se la lista dei preferiti e` vuota, false altrimenti
    */
    public boolean isEmpty()
    {
        return (downloadsSize == 0);
    } 

    /*
     Metodo makeEmpty
     Non presente nell'interfaccia, permette di eliminare il contenuto della lista
    */
    public void makeEmpty()
    {
        downloads = new Coppia[ARRAY_DIM];
        downloadsSize = 0;
    } 

    /*
     Metodo size
     Restituisce il numero di elementi presenti nella lista dei preferiti
    */
    public int size()
    {
        return downloadsSize;
    }

    /*
     Metodo access
     Effettua un accesso all'oggetto obj (parametro esplicito del metodo).
     1) Se obj non e` gia` presente nella lista dei preferiti (ovvero se e` 
        la prima volta che si fa un accesso a obj), lo aggiunge assegnando il
        valore 1 al contatore dei suoi accessi.
     2) Se obj e` gia` presente (ovvero se in precedenza sono gia` stati 
        fatti accessi a obj), incrementa di 1 il contatore dei suoi accessi. 
     Lancia IllegalArgumentException se obj e` null. 
    */
    public void access(Object obj) throws IllegalArgumentException
    {
        if(obj == null) throw new IllegalArgumentException();

        int researchResult = linearSearch(obj);
        if(researchResult == -1) 
        {
            if(downloadsSize == downloads.length) downloads = resize(downloads, downloads.length * 2);
            downloads[downloadsSize++] = new Coppia((String) obj); //vale anche nel caso di primo inserimento (downloadsSize = 0)
        }
        else
        {
            downloads[researchResult].setAccessi(downloads[researchResult].getAccessi() + 1); //accedo al numero di accessi e lo incremento di uno
            decrescentSort(researchResult);
        }
    }

    /*
     Metodo remove
     Rimuove l'oggetto obj (e il contatore dei suoi accessi) dalla lista dei 
     preferiti. Lancia FLItemNotFoundException se obj non e` presente
    */
    public void remove(Object obj) throws FLItemNotFoundException
    {
        int researchResult = linearSearch(obj);

        if(researchResult == -1) throw new FLItemNotFoundException();

        for(int i = researchResult; i < downloadsSize - 1; i++)
        {
            downloads[i] = downloads[i + 1];
        }
        downloads[--downloadsSize] = null;
    }

    /*
     Metodo top
     Restituisce un array di lunghezza k (parametro esplicito del metodo). 
     L'array restituito contiene k stringhe che rappresentano i k oggetti 
     "preferiti", ordinati per numero di accessi. Quindi:
     - L'elemento di indice 0 dell'array restituito contiene una stringa
       che rappresenta l'oggetto con il maggior numero di accessi tra tutti 
       gli oggetti presenti nella lista dei preferiti.
     - L'elemento di indice 1 contiene una stringa che rappresenta il
       secondo oggetto per numero di accessi.
     - E cosi` via. 
     - Esempio: l'invocazione top(10) restituisce un array di 10 stringhe
       contenenti la "top-ten" della lista dei preferiti, ovvero i primi 10
       oggetti per numero di accessi, ordinati dal primo al decimo.
     Lancia IllegalArgumentException se il valore di k e` minore di 1 oppure e`
     maggiore del numero degli oggetti presenti nella lista dei preferiti.
    */
    public String[] top(int k) throws IllegalArgumentException
    {
        if(k < 1 | k > downloadsSize) throw new IllegalArgumentException();

        String[] topK = new String[k];
        for(int i = 0; i < k; i++)
        {
            topK[i] = downloads[i].toString();
        }
        return topK;
    }

    //Metodi privati di utilità

    private int linearSearch(Object file)
    {
        for(int i = 0; i < downloadsSize; i++)
        {
            if(downloads[i].getFile().equals(file)) return i;
        }
        return -1;
    }

    private void decrescentSort(int index)
    {
        for(int i = index; i > 0; i--)
        {
            if(downloads[i].getAccessi() > downloads[i - 1].getAccessi())
            {
                Coppia tmp = downloads[i];
                downloads[i] = downloads[i - 1];
                downloads[i - 1] = tmp;
            }
        }
    }

    private Coppia[] resize(Coppia[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(oldArray.length >= newLength) throw new IllegalArgumentException();

        Coppia[] newArray = new Coppia[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    // --------- classe interna privata Coppia: non modificare!! ---------
    protected class Coppia implements Comparable
    {
        // Costruttore: inizializza il campo "file" con il valore del suo
        // parametro esplicito, e assegna il valore 1 al campo "accessi"  
        public Coppia(String file)
        {   setFile(file); 
            setAccessi(1); 
        }

        // Costruttore: inizializza entrambi i campi  
        public Coppia(String file, int accessi)
        {   setFile(file); 
            setAccessi(accessi); 
        }

        //metodi pubblici: toString, compareTo, met. modificatori e di accesso

        public String toString()
        {  return file + " :: " + accessi; } 

        // Compara due oggetti di tipo Coppia secondo i loro numeri di accessi.
        // Una coppia e` "maggiore" di un'altra se ha un numero di accessi piu`
        // alto.
        public int compareTo(Object o)
        {   return (accessi - ( (Coppia) o).accessi);
        }

        public String getFile()
        {  return file; }
        public int getAccessi()
        {  return accessi; }
        public void setFile(String file)
        {  this.file = file; }
        public void setAccessi(int accessi)
        {  this.accessi = accessi; } 

        //campi di esemplare
        private String file; //stringa che rappresenta il nome del file
        private int accessi; //intero che rappresenta il n. di accessi al file
    }
}



// ---------------- Interfaccia FavoriteList: non modificare !!--------------

/*
    Il tipo di dato astratto FavoriteList (lista dei preferiti) rappresenta
    un contenitore di oggetti in cui e` possibile tenere traccia del numero
    di accessi che sono stati fatti a ciascun oggetto.
    Tramite l'informazione sul numero di accessi a ciascun oggetto, e` 
    possibile conoscere quali sono gli oggetti "preferiti" (ovvero quelli che 
    hanno piu` accessi) tra tutti quelli presenti nel contenitore.
*/
interface FavoriteList  // non modificare!!
{
    /*
     Metodo isEmpty
     Restituisce true se la lista dei preferiti e` vuota, false altrimenti
    */
    boolean isEmpty(); 

    /*
     Metodo size
     Restituisce il numero di elementi presenti nella lista dei preferiti
    */
    int size(); 

    /*
     Metodo access
     Effettua un accesso all'oggetto obj (parametro esplicito del metodo).
     1) Se obj non e` gia` presente nella lista dei preferiti (ovvero se e` 
        la prima volta che si fa un accesso a obj), lo aggiunge assegnando il
        valore 1 al contatore dei suoi accessi.
     2) Se obj e` gia` presente (ovvero se in precedenza sono gia` stati 
        fatti accessi a obj), incrementa di 1 il contatore dei suoi accessi. 
     Lancia IllegalArgumentException se obj e` null. 
    */
    void access(Object obj);

    /*
     Metodo remove
     Rimuove l'oggetto obj (e il contatore dei suoi accessi) dalla lista dei 
     preferiti. Lancia FLItemNotFoundException se obj non e` presente
    */
    void remove(Object obj);

    /*
     Metodo top
     Restituisce un array di lunghezza k (parametro esplicito del metodo). 
     L'array restituito contiene k stringhe che rappresentano i k oggetti 
     "preferiti", ordinati per numero di accessi. Quindi:
     - L'elemento di indice 0 dell'array restituito contiene una stringa
       che rappresenta l'oggetto con il maggior numero di accessi tra tutti 
       gli oggetti presenti nella lista dei preferiti.
     - L'elemento di indice 1 contiene una stringa che rappresenta il
       secondo oggetto per numero di accessi.
     - E cosi` via. 
     - Esempio: l'invocazione top(10) restituisce un array di 10 stringhe
       contenenti la "top-ten" della lista dei preferiti, ovvero i primi 10
       oggetti per numero di accessi, ordinati dal primo al decimo.
     Lancia IllegalArgumentException se il valore di k e` minore di 1 oppure e`
     maggiore del numero degli oggetti presenti nella lista dei preferiti.
    */
    String[] top(int k);
}

/*
 Eccezione che segnala il mancato ritrovamento di un oggetto nella lista dei
 preferiti
*/
class FLItemNotFoundException extends RuntimeException {  }