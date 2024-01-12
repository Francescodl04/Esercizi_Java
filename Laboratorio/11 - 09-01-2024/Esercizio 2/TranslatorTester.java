/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 9-01-2024
*/

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.IOException;


// -------------- classe TranslatorTester: da completare -----------------
public class TranslatorTester
{   
    public static void main(String[] args)
    {
        
    }
}


// -------------------- classe Translator: da completare -------------------


/*
    Classe che implementa l'interfaccia StringMap
    La classe contiene coppie di tipo "parola, traduzioni": una possibile 
    realizzazione di queste coppie e` data dalla classe WordPair, classe 
    interna di Translator. In ogni caso il campo valore delle coppie inserite 
    in contenitori di tipo Translator e` di tipo String[] (che e` comunque
    una sottoclasse di Object ...)
    Si presti particolare attenzione al metodo findStartsWith.
    - SUGGERIMENTO 1: studiando la documentazione della classe String e` 
        possibile trovare un modo per verificare se una stringa ha un'altra
        stringa come prefisso.
    - SUGGERIMENTO 2: una volta capito come verificare se una chiave ne ha
        un'altra come prefisso, e` abbastanza facile scrivere una realizzazione
        non-ottima del metodo findStartsWith (ovvero una realizzazione corretta
        con prestazioni O(n) ). Invece scrivere una realizzazione ottima (con 
        prestazioni O(log n) ) e` nettamente piu` complicato: si consiglia di
        provarci solo dopo avere completato una realizzazione non-ottima.
*/


class Translator implements StringMap
{
    private WordPair[] translations;
    private int translationsSize;
    private static final int ARRAY_DIM = 100;


    public Translator(Scanner file)
    {
        translations = new WordPair[ARRAY_DIM];
        translationsSize = 0;
        while(file.hasNextLine())
        {
            if(translationsSize == translations.length) translations = resize(translations, translations.length * 2);

            Scanner row = new Scanner(file.nextLine());
            row.useDelimiter(" : ");
            String englishWord = row.next();
            String[] wordTranslations = row.next().split(", ");
            insert(englishWord, wordTranslations);
        }
    }
    
    public boolean isEmpty() // true: contenitore vuoto; false: contenitore non vuoto
    {
        return (translationsSize == 0);
    }

    public int size()       // restituisce il n. di elementi presenti nel contenitore
    {
        return translationsSize;
    }

    // L'inserimento va sempre a buon fine; se la chiave non esiste, la coppia 
    // key/value viene aggiunta alla mappa; se la chiave esiste gia`, il valore
    // ad essa associato viene sovrascritto con il nuovo valore.
    public void insert(String key, Object value)
    {
        int researchResult = binarySearch(0, translationsSize - 1, key);

        if(researchResult == -1)
        {
            if(translationsSize == translations.length) translations = resize(translations, translations.length * 2);

            translations[translationsSize++] = new WordPair(key, (String[]) value);
            insertionSort(translationsSize - 1);
        }
        else
        {
            translations[researchResult] = new WordPair(key, (String[]) value);
            insertionSort(researchResult);
        }
    }

    // La rimozione della chiave rimuove anche la corrispondente coppia.
    // Lancia MapItemNotFoundException se la chiave non esiste.
    public void remove(String key) throws MapItemNotFoundException
    {
        int researchResult = binarySearch(0, translationsSize - 1, key);

        if(researchResult == -1) throw new MapItemNotFoundException();

        for(int i = researchResult; i < translationsSize; i++)
        {
            translations[i] = translations[i + 1];
        }
        translations[--translationsSize] = null;
    }

    // La ricerca per chiave restituisce soltanto il valore ad essa associato
    // nella mappa. Lancia MapItemNotFoundException se la chiave non esiste.
    public Object find(String key) throws MapItemNotFoundException
    {
        int researchResult = binarySearch(0, translationsSize - 1, key);

        if(researchResult == -1) throw new MapItemNotFoundException();

        return translations[researchResult].getTranslations();
    }

    // La ricerca per "prefisso" cerca nella mappa tutte le chiavi che iniziano
    // con la stringa prefix, e restituisce tali chiavi sotto forma di un array  
    // di stringhe (pieno).
    // Lancia MapItemNotFoundException se la mappa non contiene nessuna chiave 
    // che inizia con la stringa prefix.
    public String[] findStartsWith(String prefix) throws MapItemNotFoundException
    {
        int prefixLength = prefix.length();
        
        return null;
    }

    
    public String toString()
    {
        String stringFormat = "";
        for(int i = 0; i < translationsSize; i++)
        {
            stringFormat += translations[i].toString() + "\n";
        }
        return stringFormat;
    }

    // metodi di utilità

    private WordPair[] resize(WordPair[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(oldArray.length >= newLength) throw new IllegalArgumentException();

        WordPair[] newArray = new WordPair[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    private int binarySearch(int startIndex, int endIndex, Comparable word)
    {
        while(startIndex <= endIndex)
        {
            int mid = (startIndex + endIndex) / 2;
            int comparisonResult = word.compareTo(translations[mid].getWord());
            if(comparisonResult > 0) //continuo la ricerca a destra
            {
                startIndex += 1;
            }
            else if (comparisonResult < 0) //continuo la ricerca a sinistra
            {
                endIndex -= 1;
            }
            else //se sono uguali
            {
                return mid;
            }
        }
        return -1;
    }

    private void insertionSort(int index)
    {
        for(int i = index; i > 0; i--)
        {
            if(translations[i].getWord().compareTo(translations[i - 1].getWord()) < 0)
            {
                WordPair tmp = translations[i];
                translations[i] = translations[i - 1];
                translations[i - 1] = tmp;
            }
        }
    }

    // --------- classe interna privata WordPair: non modificare!! ---------
    private class WordPair
    {   public WordPair(String word, String[] translations)
        {   this.word = word; 
            this.translations = translations;
        }
        public String getWord() 
        { return word; }
        public String[] getTranslations() 
        { return translations; }

        //  Restituisce una stringa nel formato
        //      word : traduzione1, traduzione2, traduzione3, ecc.
        public String toString() 
        {   String retString = word + " :";
            for (int i = 0; i < translations.length; i++)
	            retString += " " + translations[i] + ",";
            return retString.substring(0,retString.length()-1);
        }
        //campi di esemplare
        private String word;           // parola inglese
        private String[] translations; // array contenente una o piu` possibili
                                       // traduzioni in italiano
    }
}



// -------------- Interfaccia StringMap: non modificare !!---------------

interface StringMap // Definisce una mappa le cui chiavi sono stringhe
{
    boolean isEmpty(); // true: contenitore vuoto; false: contenitore non vuoto

    int size();       // restituisce il n. di elementi presenti nel contenitore

    // L'inserimento va sempre a buon fine; se la chiave non esiste, la coppia 
    // key/value viene aggiunta alla mappa; se la chiave esiste gia`, il valore
    // ad essa associato viene sovrascritto con il nuovo valore.
    void insert(String key, Object value);

    // La rimozione della chiave rimuove anche la corrispondente coppia.
    // Lancia MapItemNotFoundException se la chiave non esiste.
    void remove(String key);

    // La ricerca per chiave restituisce soltanto il valore ad essa associato
    // nella mappa. Lancia MapItemNotFoundException se la chiave non esiste.
    Object find(String key);

    // La ricerca per "prefisso" cerca nella mappa tutte le chiavi che iniziano
    // con la stringa prefix, e restituisce tali chiavi sotto forma di un array  
    // di stringhe (pieno).
    // Lancia MapItemNotFoundException se la mappa non contiene nessuna chiave 
    // che inizia con la stringa prefix.
    String[] findStartsWith(String prefix);

}

//Eccezione che segnala il mancato ritrovamento di una chiave
class MapItemNotFoundException extends RuntimeException {  }
