/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 09-01-2024
*/

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.IOException;

// -------------- classe EncoderTester: da completare -----------------
public class EncoderTester
{   
    public static void main(String[] args)
    {
        System.out.println("Benvenuto nel programma.");
        Scanner console = new Scanner(System.in);
        InvertibleMap firstEncoder = new Encoder();
        
        if(args.length != 1)
        {
            System.out.println("Non hai inserito un numero corretto di argomenti, riprova...");
            System.exit(0);
        }
        try
        {
            FileReader reader = new FileReader(args[0]);
            Scanner file = new Scanner(reader);
            file.useDelimiter(" ");
            while(file.hasNextLine())
            {
                Scanner row = new Scanner(file.nextLine());
                firstEncoder.insert(row.next(), row.next());
                row.close();
            }
            reader.close();
            file.close();
        }
        catch(IOException io)
        {
            System.out.println("L'apertura del file non e` avvenuta correttamente: controlla che il percorso si corretto...");
            System.exit(1);
        }
        catch(NoSuchElementException nse)
        {
            System.out.println("Il file inserito non presenta un formato corretto, riprova con un altro file...");
            System.exit(1);
        }
        System.out.println("Acquisizione delle corrispondenze completato. Inserisci ora il messaggio da codificare:");
        String m1 = console.nextLine();
        String m2 = encode(firstEncoder, m1);
        System.out.printf("\nEcco il messaggio codificato:\n%s\n", m2);
        InvertibleMap secondEncoder = firstEncoder.invert();
        String m3 = encode(secondEncoder, m2);
        System.out.printf("\nEcco il messaggio decodificato:\n%s\n", m3);
    }

    private static String encode(InvertibleMap encoder, String clearMessage)
    {
        String encodedMessage = "";
        Scanner messageAnalyzer = new Scanner(clearMessage);
        while(messageAnalyzer.hasNextLine())
        {
            String delimiter = " ";
            Scanner row = new Scanner(messageAnalyzer.nextLine());
            row.useDelimiter(delimiter);
            while(row.hasNext())
            {
                String next = row.next();
                try
                {
                    String word = (String) encoder.find((Comparable) next);
                    encodedMessage += word + delimiter;
                }
                catch(MapItemNotFoundException minf)
                {
                    encodedMessage += next + delimiter;
                }
            }
        }
        return encodedMessage;
    }
}


// -------------------- classe Encoder: da completare -------------------

class Encoder implements InvertibleMap
{
    private StringPair[] words;
    private int wordsSize;
    private static final int ARRAY_DIM = 100;

    public Encoder()
    {
        wordsSize = 0;
        words = new StringPair[ARRAY_DIM];
    }

    public boolean isEmpty() // true: contenitore vuoto; false: contenitore non vuoto
    {
        return (wordsSize == 0);
    }

    public int size()       // restituisce il n. di elementi presenti nel contenitore
    {
        return wordsSize;
    }

    /* 
        L'inserimento va sempre a buon fine; se la chiave non esiste, la coppia 
        key/value viene aggiunta alla mappa; se la chiave esiste gia`, il 
        valore ad essa associato viene sovrascritto con il nuovo valore; se key
        e` null viene lanciata IllegalArgumentException.
    */
    public void insert(Comparable key, Comparable value) throws IllegalArgumentException
    {
        if(key == null) throw new IllegalArgumentException();

        int researchResult = binarySearch(0, wordsSize - 1, key);
        if(researchResult == -1)
        {
            if(wordsSize == words.length) words = resize(words, words.length * 2);

            words[wordsSize++] = new StringPair((String) key, (String) value);
            insertionSort(wordsSize - 1);
        }
        else
        {
            words[researchResult] = new StringPair((String) key, (String) value);
            insertionSort(researchResult);
        }
    }

    /* 
        La rimozione della chiave rimuove anche la corrispondente coppia.
        Lancia MapItemNotFoundException se la chiave non esiste
    */
    public void remove(Comparable key) throws MapItemNotFoundException
    {
        int researchResult = binarySearch(0, wordsSize - 1, key);

        if(researchResult == -1) throw new MapItemNotFoundException();

        for(int i = researchResult; i < wordsSize - 1; i++)
        {
            words[i] = words[i + 1];
        }
        words[--wordsSize] = null;
    }

    /* 
        La ricerca per chiave restituisce soltanto il valore ad essa associato
        nella mappa. Lancia MapItemNotFoundException se la chiave non esiste.
    */
    public Comparable find(Comparable key) throws MapItemNotFoundException
    {
        int researchResult = binarySearch(0, wordsSize - 1, key);

        if(researchResult == -1) throw new MapItemNotFoundException();

        return words[researchResult].getCode();
    }

    /* 
        Metodo che crea la "mappa inversa", ovvero un nuovo oggetto di tipo 
        InvertibleMap che, per ogni coppia key/value della mappa originale 
        (parametro implicito), contiene una corrispondente "coppia inversa" 
        value/key.
        Notare che l'operazione e` possibile perche` key e value sono entrambi
        oggetti di tipo Comparable: quindi e` possibile usare i valori value 
        come chiavi della mappa inversa.
        Se nella mappa originale (parametro implicito) sono presenti piu`
        coppie con lo stesso campo value, nella mappa inversa verra` inserita 
        una qualsiasi delle coppie inverse.
    */
    public InvertibleMap invert()
    {
        InvertibleMap reversedMap = new Encoder();
        for(int i = 0; i < wordsSize; i++)
        {
            String reversedWord = words[i].getCode(), reversedCode = words[i].getWord();
            reversedMap.insert(reversedWord, reversedCode);
        }
        return reversedMap;
    }

    
    public String toString()
    {
        String stringFormat = "";
        for(int i = 0; i < wordsSize; i++)
        {
            stringFormat += words[i].toString() + "\n";
        }
        return stringFormat;
    }          

    //metodi di utilità

    private int binarySearch(int startIndex, int endIndex, Comparable key)
    {
        while(startIndex <= endIndex)
        {
            int mid = (startIndex + endIndex) / 2;
            int comparisonResult = key.compareTo(words[mid].getWord());
            if(comparisonResult > 0) //continuo la ricerca a destra
            {
                startIndex = mid + 1;
            }
            else if(comparisonResult < 0) //continuo la ricerca a sinistra
            {
                endIndex = mid - 1;
            }
            else //se sono uguali
            {
                return mid;
            }
        }
        return -1; //se non trovo alcuna corrispondenza
    }

    private void insertionSort(int index)
    {
        for(int i = index; i > 0; i--)
        {
            if(words[i].getWord().compareTo(words[i - 1].getWord()) < 0)
            {
                StringPair tmp = words[i];
                words[i] = words[i - 1];
                words[i - 1] = tmp;
            }
        }
    }
    
    private StringPair[] resize(StringPair[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(oldArray.length >= newLength) throw new IllegalArgumentException();

        StringPair[] newArray = new StringPair[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }
    
    /* --------- classe interna privata StringPair: non modificare!! ---------

        Un oggetto StringPair contiene una coppia "parola codice" entrambi di
        tipo String. Il primo campo e` una parola da cifrare, il secondo e` il 
        codice corrispondente alla parola. 
    */
    private class StringPair
    {   public StringPair(String word, String code)
        {   this.word = word; 
            this.code = code;
        }      
        // metodi (pubblici) di accesso
        public String getWord() 
        { return word; }
        public String getCode() 
        { return code; }
        //metodo toString sovrascritto
        public String toString() 
        {   return word + " " + code;
        }
        //campi di esemplare (privati)
        private String word;  //parola da cifrare
        private String code;  //codice associato alla parola
    }
}


/* -------------- Interfaccia InvertibleMap: non modificare !!---------------
  
    Questo tipo di dato astratto definisce un contenitore di coppie 
    "chiave valore", che hanno l'usuale significato.
    Si tratta di un tipo di dato astratto "mappa", con la particolarita` che 
    entrambi i campi sono di tipo Comparable, e con la proprieta` aggiuntiva
    che e` possibile creare la "mappa inversa" (si vedano piu` sotto i commenti
    al metodo invert).
*/

interface InvertibleMap   //non modificare!!
{
    boolean isEmpty(); // true: contenitore vuoto; false: contenitore non vuoto

    int size();       // restituisce il n. di elementi presenti nel contenitore

    /* 
        L'inserimento va sempre a buon fine; se la chiave non esiste, la coppia 
        key/value viene aggiunta alla mappa; se la chiave esiste gia`, il 
        valore ad essa associato viene sovrascritto con il nuovo valore; se key
        e` null viene lanciata IllegalArgumentException.
    */
    void insert(Comparable key, Comparable value);

    /* 
        La rimozione della chiave rimuove anche la corrispondente coppia.
        Lancia MapItemNotFoundException se la chiave non esiste
    */
    void remove(Comparable key);

    /* 
        La ricerca per chiave restituisce soltanto il valore ad essa associato
        nella mappa. Lancia MapItemNotFoundException se la chiave non esiste.
    */
    Comparable find(Comparable key);

    /* 
        Metodo che crea la "mappa inversa", ovvero un nuovo oggetto di tipo 
        InvertibleMap che, per ogni coppia key/value della mappa originale 
        (parametro implicito), contiene una corrispondente "coppia inversa" 
        value/key.
        Notare che l'operazione e` possibile perche` key e value sono entrambi
        oggetti di tipo Comparable: quindi e` possibile usare i valori value 
        come chiavi della mappa inversa.
        Se nella mappa originale (parametro implicito) sono presenti piu`
        coppie con lo stesso campo value, nella mappa inversa verra` inserita 
        una qualsiasi delle coppie inverse.
    */
    InvertibleMap invert();
}

class MapItemNotFoundException extends RuntimeException  {}