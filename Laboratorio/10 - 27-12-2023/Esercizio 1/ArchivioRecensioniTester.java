// Francesco Di Lena
// Laboratorio di fondamenti di informatica - 27-12-2023


import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.IOException;

// -------------- classe ArchivioRecensioniTester: da completare --------------
public class ArchivioRecensioniTester
{
    public static void main(String[] args)
    {
   	System.out.println("Benvenuto nel programma.");
      	if(args.length != 1)
      	{
      		System.out.println("Non hai inserito un numero di argomenti corretto, riprova...");
      		System.exit(0);
      	}
      	
      	ArchivioRecensioni archive = new ArchivioRecensioni();
      	
      	try
      	{
      		Scanner input = new Scanner(new FileReader(args[0]));
      		while(input.hasNextLine())
      		{
      			Scanner row = new Scanner(input.nextLine());
      			row.useDelimiter(" :: ");
      			try
      			{
      				Comparable key = row.next();
      				Recensione review = new Recensione(Integer.parseInt(row.next()), row.next());
      				archive.insert(key, review);
      			}
      			catch(NoSuchElementException e)
      			{
      				continue;
      			}	
      		}
      		input.close();
      	}
      	catch(IOException e)
      	{
      		System.out.println("Si e' verificato un errore nell'apertura del file: il file inserito potrebbe non esistere, riprovare con un file diverso");
      		System.exit(1);
      	}
      	Scanner console = new Scanner(System.in);
      	do
      	{
      		System.out.print("\nInserisci un comando (termina con Q): ");
            try
            {
      		    switch(console.nextLine().toUpperCase())
      		    {
      			    case "P":
      				    System.out.println("\nEcco le recensioni contenute in archivio:");
      				    System.out.println(archive.toString());
      				    break;
      			    case "FA":
      				    System.out.print("Inserisci il nome del film: ");
                        try
                        {
      				        Object[] reviews = archive.findAll(console.nextLine());
                            System.out.printf("\nLa ricerca ha prodotto %d risultati:\n\n", reviews.length);
                            for(Object review : reviews)
                            {
                                System.out.println(review);
                            }
                        }
                        catch(MultiMapItemNotFoundException e)
                        {
                            System.out.println("Chiave ricercata non trovata, riprova con un altro termine di ricerca...");
                        }
      				    break;
      			    case "R":
                        System.out.print("Inserisci la recensione che vuoi eliminare (formato titolo film :: voto :: commento): ");
                        Scanner userText = new Scanner(console.nextLine());
                        userText.useDelimiter(" :: ");
                        String[] reviewToBeDeleted = new String[3];
                        int i = 0;
                        while(userText.hasNext())
                        {
                            reviewToBeDeleted[i] = userText.next();
                            i++;
                        }
                        if(i < 3) 
                        {
                            System.out.println("Non hai inserito un numero di parametri corretti oppure non hai usato il delimitatore \" :: \", riprova...");
                            continue;
                        }
                        try
                        {
                            archive.remove(reviewToBeDeleted[0], new Recensione(Integer.parseInt(reviewToBeDeleted[1]), reviewToBeDeleted[2]));
                            System.out.println("Eliminazione effettuata con successo!");
                        }
                        catch(NumberFormatException ne)
                        {
                            System.out.println("Il voto non e' stato inserito in forma numerica, riprova...");
                            continue;
                        }
                        catch(MultiMapItemNotFoundException me)
                        {
                            System.out.println("Elemento non trovato, riprova con termini diversi...");
                            continue;
                        }
      				    break;
      			    case "Q":
      				    console.close();
      				    System.out.println("\nUscita dal programma in corso...");
      				    System.exit(0);
      				    break;
      			    default:
      				    System.out.println("\nNon hai inserito un comando corretto, riprova...");
      				    continue;
      		    }
            }
            catch(NoSuchElementException e)
            {
                break;
            }
      	}
      	while(true);
    }
}

// -------------------- classe Recensioni: da completare -------------------

/*
    Classe che implementa l'interfaccia MultiMap e gestisce coppie di tipo
    "voto commento" appartenenti alla classe Coppia (realizzata come classe 
    interna a Recensioni. Sovrascrive toString in modo che restituisca una
    stringa con il seguente formato: 
    (1) i dati di ogni coppia vengono scritti su una riga diversa;
    (2) in ogni riga i dati vengono scritti con il formato del metodo toString 
        della classe Coppia.
*/
class ArchivioRecensioni implements MultiMap
{   
    //costruttori e metodi pubblici di MultiMap
    
    public ArchivioRecensioni()
    {
    	makeEmpty();
    }
    
    public boolean isEmpty()
    {
    	return vSize == 0;
    }
    
    public void makeEmpty()
    {
    	vSize = 0;
    	movies = new Coppia[ARRAY_DIM];
    }

    public int size()
    {
    	return vSize;
    }
    
    public void insert(Comparable key, Object value) throws IllegalArgumentException
    {
    	if(key == null) throw new IllegalArgumentException();
    	
    	if(vSize + 1 > movies.length) resize(movies, movies.length * 2);
    	
    	movies[vSize++] = new Coppia((String) key, (Recensione) value);
    	
    	//realizzo l'ordinamento con insertion sort, che qui ha prestazioni O(n)
    	//non eseguo il ciclo completo perche' c'e' da ordinare solamente l'ultimo valore inserito
    	for(int i = vSize - 1; i > 0; i--) 
    	{
    		if(movies[i].getKey().compareTo(movies[i - 1].getKey()) < 0)
    		{
    			Coppia temp = movies[i];
    			movies[i] = movies[i - 1];
    			movies[i - 1] = temp;
    		}
    	}
    }

    
    public void remove(Comparable key, Object value) throws MultiMapItemNotFoundException
    {
    	int first = findFirst(key), last = findLast(key);
        for(int i = first; i <= last; i++)
        {
            if(value.equals(movies[i].getValue()))
            {
                for(int j = i; j < vSize - 1; j++)
                {
                    movies[j] = movies[j + 1];
                }
                movies[--vSize] = null;
                last--;
            }
        }
    }

    public Object find(Comparable key) throws MultiMapItemNotFoundException
    {
    	int index = binarySearch(0, vSize - 1, key);
    	
    	if (index == -1) throw new MultiMapItemNotFoundException(); // nel caso in cui non trovi l'elemento lancia l'eccezione
    	
    	return movies[index].getValue();
    }
    
    public Object[] findAll(Comparable key) throws MultiMapItemNotFoundException
    {
        int first = findFirst(key), last = findLast(key);
        Recensione[] reviews = new Recensione[last - first + 1];
        for(int i = first, j = 0; i <= last && j < reviews.length; i++, j++)
        {
            reviews[j] = movies[i].getValue();
        }
    	return reviews;
    }
    
    private int binarySearch(int startIndex, int endIndex, Comparable key)
    {
    	while(startIndex <= endIndex)
    	{
    		int mid = ((endIndex + startIndex) / 2);
            int comparisonResult = key.compareTo(movies[mid].getKey());
    		if(comparisonResult > 0) //continuo la ricerca a destra
    		{
    			startIndex = mid + 1;
    		}
    		else if(comparisonResult < 0) //continuo la ricerca a sinistra
    		{
    			endIndex = mid - 1;
    		}
    		else //nel caso siano uguali
    		{
    			return mid;
    		}
    	}
    	return -1;
    }

    private int findFirst(Comparable key) throws MultiMapItemNotFoundException
    {
        int first = binarySearch(0, vSize - 1, key);
        
        if(first == -1) throw new MultiMapItemNotFoundException();

        while(first > 0 && movies[first - 1].getKey().equals(key)) 
        {
            first--;
        }
        return first;
    }

    private int findLast(Comparable key) throws MultiMapItemNotFoundException
    {
        int last = binarySearch(0, vSize - 1, key);

        if(last == -1) throw new MultiMapItemNotFoundException();

        while(last < vSize - 1 && movies[last + 1].getKey().equals(key))
        {
            last++;
        }
        return last;
    }
    
    private Object[] resize(Object[] oldArray, int newLength) throws IllegalArgumentException
    {
    	if(oldArray.length >= newLength) throw new IllegalArgumentException();
    	
    	Object[] newArray = new Object[newLength];
    	System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
    	return newArray;
    }

    //metodo toString 
    public String toString()
    {
    	String outputFormat = "";
    	for(int i = 0; i < vSize; i++)
    	{
    		outputFormat += movies[i].toString() + "\n";
    	}
        return outputFormat;
    }          

    //campi di esemplare
    
    private Coppia[] movies;
    private int vSize;
    private final int ARRAY_DIM = 100;


    
    /* 
        --------- classe privata interna Coppia: non modificare!! ---------
    */
    private class Coppia
    {   public Coppia(String key, Recensione value) //costruttore
        {   setKey(key); 
            setValue(value); 
        }
        //metodi pubblici: toString, metodi modificatori e di accesso
        public String toString()
        {   String s = key + " :: " + value;
            return s;    
        } 
        public String getKey()
        {  return key; }
        public Recensione getValue()
        {  return value; }
        public void setKey(String key)
        {  this.key = key; }
        public void setValue(Recensione value)
        {   this.value = value; }     
        //campi di esemplare
        private String key;       //titolo dell'oggetto recensito 
        private Recensione value; //recensione (voto+commento) data all'oggetto
    }
}


// --------- classe Recensione: non modificare!! ---------
/* 
    Un oggetto di questa classe rappresenta una singola recensione inserita
    da un utente, ed e` composto da due campi di esemplare:
        1) un numero intero compreso tra 0 e 10, che rappresenta il voto dato
           dall'utente
        2) una stringa contenente un commento aggiuntivo scritto dall'utente
    Notare che questa classe sovrascrive il metodo equals di Object. E` quindi
    possibile verificare l'uguaglianza di 2 oggetti di tipo Recensione tramite
    il metodo equals. In particolare, 2 oggetti di tipo Recensione sono uguali
    se sono uguali entrambi i loro campi di esemplare (voto e commento)
*/
class Recensione
{   public Recensione(int voto, String commento) //costruttore
    {   setVoto(voto); 
        setCommento(commento); 
    }
    //metodi pubblici: toString, equals, metodi modificatori e di accesso
    public String toString()
    {   String s = voto + " :: " + commento;
        return s;    
    } 
    public boolean equals(Object o)
    {   Recensione altra = (Recensione) o;
        return (this.voto == altra.voto) && 
                ( (this.commento).equals(altra.commento) );
    }   
    public int getVoto()
    {  return voto; }
    public String getCommento()
    {  return commento; }
    public void setVoto(int voto)
    {   if (voto < 0 || voto > 10) throw new IllegalArgumentException();
        this.voto = voto; }
    public void setCommento(String commento)
    {  this.commento = commento; } 

    //campi di esemplare
    private int voto; // voto (tra 0 e 10) dato all'oggetto recensito
    private String commento; // commento sull'oggetto recensito
}



// ------------------ Interfaccia MultiMap: non modificare !!----------------

interface MultiMap  // non modificare!!
{
    boolean isEmpty(); // true: contenitore vuoto; false: contenitore non vuoto

    int size();       // restituisce il n. di elementi presenti nel contenitore

    /*
     Inserisce nella multimappa una nuova coppia specificata dai valori key e
     value. L'inserimento va sempre a buon fine:
     - se la chiave non e` gia` presente nel contenitore, la coppia viene
       inserita
     - se invece esistono gia` coppie con la chiave key, viene creata una
       nuova coppia con i valori key, value, e le coppie gia` esistenti 
       rimangono presenti nel contenitore senza venire cancellate o 
       sovrascritte
     Se key e` null lancia IllegalArgumentException
    */
    void insert(Comparable key, Object value);

    /*
     Rimuove dalla multimappa la coppia specificata dai valori key e value.
     Se la coppia non esiste, lancia MultiMapItemNotFoundException 
    */
    void remove(Comparable key, Object value);

    /*
     Cerca nella multimappa una coppia specificata dalla chiave key e 
     restituisce soltanto il valore ad essa associato.
     Se esistono piu` coppie aventi la stessa chiave key, restituisce il valore
     della prima coppia trovata (in pratica, restituisce un valore arbitrario
     tra i valori di tutte le coppie la cui chiave e` key).
     Se non esistono coppie con la chiave key, lancia
     MultiMapItemNotFoundException
    */
    Object find(Comparable key);

    /*
     Cerca nella multimappa tutte le coppie specificate dalla chiave key e
     restituisce un array pieno contenente i valori di tutte le coppie trovate.
     Se non esistono coppie con la chiave key, lancia
     MultiMapItemNotFoundException
    */
    Object[] findAll(Comparable key);
}

//Eccezione che segnala il mancato ritrovamento di una chiave o coppia
class MultiMapItemNotFoundException extends RuntimeException  {}

