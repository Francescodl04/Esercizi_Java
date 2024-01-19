/**
 * Francesco Di Lena
 * Esercizio 2 - laboratorio di fondamenti di informatica
 * 16-01-2024
**/

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.IOException;


// -------------- classe StatPromossiTester: da completare -----------------
public class StatPromossiTester
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        Studenti allStudentsSet1 = new Studenti(), allStudentsSet2 = new Studenti(), promotedStudentsSet1 = new Studenti(), promotedStudentsSet2 = new Studenti();

        try
        {
            System.out.print("Benvenuto nel programma. Inserisci il percorso del primo file: ");
            Scanner first = new Scanner(new FileReader(console.nextLine()));
            System.out.print("Inserisci ora il percorso del secondo file: ");
            Scanner second = new Scanner(new FileReader(console.nextLine()));
            while(first.hasNextLine())
            {
                Scanner row = new Scanner(first.nextLine());
                row.useDelimiter(";");
                int matricola = Integer.parseInt(row.next().strip());
                String cognome = row.next().strip(), nome = row.next().strip();
                allStudentsSet1.add(new Studente(matricola, cognome, nome));
                if(row.hasNext()) //se dovessimo avere anche la "P" di promosso...
                {
                    promotedStudentsSet1.add(new Studente(matricola, cognome, nome));
                }
                row.close();
            }
            while(second.hasNextLine())
            {
                Scanner row = new Scanner(second.nextLine());
                row.useDelimiter(";");
                int matricola = Integer.parseInt(row.next().strip());
                String cognome = row.next().strip(), nome = row.next().strip();
                allStudentsSet2.add(new Studente(matricola, cognome, nome));
                if(row.hasNext()) //se dovessimo avere anche la "P" di promosso...
                {
                    promotedStudentsSet2.add(new Studente(matricola, cognome, nome));
                }
                row.close();
            }
            first.close();
            second.close();
        }
        catch(IOException io)
        {
            System.out.println("Il percorso del file appena inserito non e' corretto oppure non si posseggono i privilegi richiesti, riprova con una nuova esecuzione...");
            System.exit(1);
        }
        catch(NumberFormatException | NoSuchElementException e)
        {
            System.out.println("Uno dei file inseriti non presenta un formato corretto, riprova con degli altri file in una nuova esecuzione...");
            System.exit(2);
        }

        System.out.println("L'inserimento dei file negli insiemi e` andata a buon fine, ora eseguiro` il calcolo di tutte le statistiche...");

        //Punto a. e b. : calcolo la percentuale di promossi nel primo e nel secondo corso

        double promotedPercentageSet1 = ((double) promotedStudentsSet1.size()) / ((double) allStudentsSet1.size()) * 100;
        double promotedPercentageSet2 = ((double) promotedStudentsSet2.size()) / ((double) allStudentsSet2.size()) * 100;

        //Punto c. : calcolo la percentuale di promossi in entrambi i corsi

        Set allStudentsUnionSet = allStudentsSet1.union(allStudentsSet2), promotedStudentsUnionSet = promotedStudentsSet1.union(promotedStudentsSet2);
        double promotedPercentageUnionSet = ((double) promotedStudentsUnionSet.size()) / ((double) allStudentsUnionSet.size()) * 100;

        //Punto d. : calcolo la percentuale di studenti promossi in almeno uno dei due corsi

        Set allStudentsIntersectionSet = allStudentsSet1.intersection(allStudentsSet2), promotedStudentsIntersectionSet = promotedStudentsSet1.intersection(promotedStudentsSet2);
        double promotedPercentageIntersectionSet = ((double) promotedStudentsIntersectionSet.size()) / ((double) allStudentsIntersectionSet.size()) * 100;

        //Richiesta finale: stampo il contenuto di tutti gli insiemi e le statistiche 

        System.out.printf("Questi sono tutti gli studenti del primo corso inserito:\n%s\n", allStudentsSet1.toString());
        System.out.printf("Questi sono gli studenti promossi del primo corso inserito:\n%s\n", promotedStudentsSet1.toString());
        System.out.printf("Questi sono tutti gli studenti del secondo corso inserito:\n%s\n", allStudentsSet2.toString());
        System.out.printf("Questi sono gli studenti promossi del secondo corso inserito:\n%s\n", promotedStudentsSet2.toString());
        System.out.println("Ora i risultati delle statistiche sono i seguenti: ");
        System.out.printf("Percentuale di promossi nel primo corso: %.2f\n", promotedPercentageSet1);
        System.out.printf("Percentuale di promossi nel secondo corso: %.2f\n", promotedPercentageSet2);
        System.out.printf("Percentuale di promossi in entrambi i corsi: %.2f\n", promotedPercentageUnionSet);
        System.out.printf("Percentuale di promossi in almeno uno dei due corsi: %.2f\n", promotedPercentageIntersectionSet);
    }
}


// -------------------- classe Studenti: da completare -------------------
/*
    Classe che implementa l'interfaccia Set.
    La classe realizza un tipo di dato astratto insieme e puo` contenere solo
    oggetti di tipo Studente (classe realizzata piu` sotto).
    La classe sovrascrive toString in modo che restituisca una stringa con il
    seguente formato: 
    (1) ogni oggetto viene scritto su una nuova riga
    (2) in ogni riga i dati sono scritti con il formato del metodo toString 
        della classe Studente
*/


class Studenti implements Set
{   
    private Studente[] students;
    private int studentsSize;
    
    private final int ARRAY_DIM = 1;
    
    public Studenti()
    {
        students = new Studente[ARRAY_DIM];
        studentsSize = 0;
    }

    public boolean isEmpty() // true: contenitore vuoto; false: contenitore non vuoto
    {
        return (studentsSize == 0);
    }

    public int size()       // restituisce il n. di elementi presenti nel contenitore
    {
        return studentsSize;
    }

    /*
     Inserisce l'oggetto obj nell'insieme se non e` gia` presente, altrimenti 
     fallisce silenziosamente.
    */
    public void add(Comparable obj)
    {
        if(this.contains(obj)) return;

        if(studentsSize == students.length) students = resize(students, students.length * 2);

        students[studentsSize++] = (Studente) obj;
        insertionSort(studentsSize - 1);
    }

    /*
     Rimuove l'oggetto obj dall'insieme se e` presente, altrimenti fallisce
     silenziosamente.
    */
    public void remove(Comparable obj)
    {
        int researchResult = binarySearch(0, studentsSize - 1, obj);

        if(researchResult == -1) return;

        for(int i = researchResult; i < studentsSize - 1; i++)
        {
            students[i] = students[i + 1];
        }
        students[--studentsSize] = null;
    }

   /*
    Restituisce true se e solo se l'oggetto comparabile obj appartiene  
    all'insieme. 
    */
    public boolean contains(Comparable obj)
    {
        return (binarySearch(0, studentsSize - 1, obj) != -1);
    }

    /*
     Restituisce un array (pieno) di oggetti comparabili, contenente i 
     riferimenti a tutti gli elementi presenti nell'insieme
    */
    public Comparable[] toArray()
    {
        Comparable[] newArray = new Comparable[studentsSize];
        System.arraycopy(students, 0, newArray, 0, studentsSize);
        return newArray;
    }

    /*
     Riceve un riferimento ad un altro oggetto di Set e restituisce un nuovo
     riferimento Set contenente tutti gli elementi che appartengono alla
     intersezione (in senso insiemistico) dei due insiemi ricevuti come 
     parametro implicito e esplicito (cioe` l'insieme this e l'insieme s)
    */
    public Set intersection(Set s)
    {
        Set intersectionSet = new Studenti();
        Comparable[] s1 = this.toArray(), s2 = intersectionSet.toArray();
        for(int i = 0; i < s1.length && i < s2.length; i++)
        {
            
        }
        return intersectionSet;
    }

    /*
     Riceve un riferimento ad un altro oggetto di Set e restituisce un nuovo
     riferimento Set contenente tutti gli elementi che appartengono alla
     unione (in senso insiemistico) dei due insiemi ricevuti come parametro
     implicito e esplicito (cioe` l'insieme this e l'insieme s)
    */
    public Set union(Set s)
    {
        return null;
    }

    public String toString()
    {
        String stringFormat = "";
        for(int i = 0; i < studentsSize; i++)
        {
            stringFormat += students[i].toString() + "\n";
        }
        return stringFormat;
    }      

    //metodi di utilità

    private void insertionSort(int index)
    {
        for(int i = index; i > 0; i--)
        {
            if(students[i].compareTo(students[i - 1]) < 0)
            {
                Studente tmp = students[i];
                students[i] = students[i - 1];
                students[i - 1] = tmp;
            }
        }
    }

    private int binarySearch(int startIndex, int endIndex, Comparable key)
    {
        while(startIndex <= endIndex)
        {
            int mid = (startIndex + endIndex) / 2;
            int comparisonResult = key.compareTo(students[mid]);
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
        return -1;
    }    

    private Studente[] resize(Studente[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(oldArray.length >= newLength) throw new IllegalArgumentException();

        Studente[] newArray = new Studente[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

}



// ---------------- Classe Studente: non modificare!! ---------------------

class Studente implements Comparable
{
    public Studente(int matricola, String cognome, String nome)
    {   this.matricola = matricola;
        this.cognome = cognome;
        this.nome = nome;
    }

    public String toString()
    {   return matricola + " ; " + cognome + " ; " + nome;
    }

    public boolean equals(Object o)
    {   return this.matricola == ((Studente)o).matricola;
    }

    public int compareTo(Object o)
    {   return this.matricola - ((Studente)o).matricola;
    }

    public final int matricola;
    public final String cognome;
    public final String nome;
}    

// ------------------ Interfaccia Set: non modificare !!----------------

interface Set  // non modificare!!
{
    boolean isEmpty(); // true: contenitore vuoto; false: contenitore non vuoto

    int size();       // restituisce il n. di elementi presenti nel contenitore

    /*
     Inserisce l'oggetto obj nell'insieme se non e` gia` presente, altrimenti 
     fallisce silenziosamente.
    */
    void add(Comparable obj);

    /*
     Rimuove l'oggetto obj dall'insieme se e` presente, altrimenti fallisce
     silenziosamente.
    */
    void remove(Comparable obj);

   /*
    Restituisce true se e solo se l'oggetto comparabile obj appartiene  
    all'insieme. 
    */
    boolean contains(Comparable obj);

    /*
     Restituisce un array (pieno) di oggetti comparabili, contenente i 
     riferimenti a tutti gli elementi presenti nell'insieme
    */
    Comparable[] toArray();

    /*
     Riceve un riferimento ad un altro oggetto di Set e restituisce un nuovo
     riferimento Set contenente tutti gli elementi che appartengono alla
     intersezione (in senso insiemistico) dei due insiemi ricevuti come 
     parametro implicito e esplicito (cioe` l'insieme this e l'insieme s)
    */
    Set intersection(Set s);

    /*
     Riceve un riferimento ad un altro oggetto di Set e restituisce un nuovo
     riferimento Set contenente tutti gli elementi che appartengono alla
     unione (in senso insiemistico) dei due insiemi ricevuti come parametro
     implicito e esplicito (cioe` l'insieme this e l'insieme s)
    */
    Set union(Set s);
}