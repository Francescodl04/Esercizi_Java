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
            console.close();
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

        Set allStudentsIntersectionSet = allStudentsSet1.intersection(allStudentsSet2), promotedStudentsIntersectionSet = promotedStudentsSet1.intersection(promotedStudentsSet2);
        double promotedPercentageIntersectionSet = ((double) promotedStudentsIntersectionSet.size()) / ((double) allStudentsIntersectionSet.size()) * 100;

        //Punto d. : calcolo la percentuale di studenti promossi in almeno uno dei due corsi

        Set allStudentsUnionSet = allStudentsSet1.union(allStudentsSet2), promotedStudentsUnionSet = promotedStudentsSet1.union(promotedStudentsSet2);
        double promotedPercentageUnionSet = ((double) promotedStudentsUnionSet.size()) / ((double) allStudentsUnionSet.size()) * 100;
        
        /** NOTA BENE: aggiunto per motivo indicato in riga 285 */ 
        Studenti onlyCourseSet1 = (Studenti) allStudentsSet1.subtract(allStudentsSet2);  //trovo gli studenti iscritti solo al primo corso

        //Richiesta finale: stampo il contenuto di tutti gli insiemi e le statistiche 

        //Stampa degli insiemi primari
        System.out.printf("Questi sono tutti gli studenti del primo corso inserito (%d studenti contati):\n%s\n", allStudentsSet1.size(), allStudentsSet1.toString());
        System.out.printf("Questi sono gli studenti promossi del primo corso inserito (%d studenti contati):\n%s\n", promotedStudentsSet1.size(), promotedStudentsSet1.toString());
        System.out.printf("Questi sono tutti gli studenti del secondo corso inserito (%d studenti contati):\n%s\n", allStudentsSet2.size(), allStudentsSet2.toString());
        System.out.printf("Questi sono gli studenti promossi del secondo corso inserito (%d studenti contati):\n%s\n", promotedStudentsSet2.size(), promotedStudentsSet2.toString());
        /** NOTA BENE: aggiunto per motivo indicato in riga 285 */
        System.out.printf("Questi sono gli studenti iscritti solo al primo corso (%d studenti contati):\n%s\n", onlyCourseSet1.size(), onlyCourseSet1.toString());

        //Stampa degli insiemi secondari (ottenuti con operazioni)
        
        //Punto c.
        System.out.printf("Questi sono gli studenti che hanno partecipato a entrambi i corsi (%d studenti contati):\n%s\n", allStudentsIntersectionSet.size(), allStudentsIntersectionSet.toString());
        System.out.printf("Questi sono gli studenti che sono stati promossi a entrambi i corsi (%d studenti contati):\n%s\n", promotedStudentsIntersectionSet.size(), promotedStudentsIntersectionSet.toString());

        //Punto d.
        System.out.printf("Questi sono gli studenti di entrambi i corsi (%d studenti contati):\n%s\n", allStudentsUnionSet.size(), allStudentsUnionSet.toString());
        System.out.printf("Questi sono gli studenti che sono stati promossi ad almeno un esame (%d studenti contati):\n%s\n", promotedStudentsUnionSet.size(), promotedStudentsUnionSet.toString());

        //Stampa risultati statistiche
        System.out.println("Ora i risultati delle statistiche sono i seguenti: ");
        /*Stampa punto a. */ System.out.printf("Percentuale di promossi nel primo corso: %.2f %%\n", promotedPercentageSet1);
        /*Stampa punto b. */ System.out.printf("Percentuale di promossi nel secondo corso: %.2f %%\n", promotedPercentageSet2);
        /*Stampa punto c. */ System.out.printf("Percentuale di promossi in entrambi i corsi: %.2f %%\n", promotedPercentageIntersectionSet);
        /*Stampa punto d. */ System.out.printf("Percentuale di promossi in almeno uno dei due corsi: %.2f %%\n", promotedPercentageUnionSet);
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
        Comparable[] s1 = this.toArray(), s2 = s.toArray();
        for(int i = 0, j = 0; i < s1.length; i++)
        {
            //Eseguo un ciclo per determinare l'indice giusto dal quale partire per fare il confronto
            //evitando così inutili accessi all'array
            while(j < s2.length && s1[i].compareTo(s2[j]) > 0)
            {
                j++;
            }

            //Interrompo qui il ciclo e non attraverso una condizione nel for, perché altrimenti si rischierebbe l'eccezione di indice fuori dall'array
            if(j == s2.length) break; 
            
            if(s1[i].compareTo(s2[j]) == 0)
            {
                intersectionSet.add(s1[i]);
                j++;
            }
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
        Set unionSet = new Studenti();
        Comparable[] s1 = this.toArray(), s2 = s.toArray();

        /*
         * Uso ora lo stesso algoritmo di fusione usato per il merge sort
         * al fine di avere prestazioni O(n log n) e di mantenere l'ordinamento iniziale
        */

        int i = 0, j = 0;
        while(i < s1.length && j < s2.length) //con questo ciclo metto in ordine ciò che è possibile
        {
            int comparisonResult = s1[i].compareTo(s2[j]);
            if(comparisonResult < 0) //il primo elemento è minore del secondo
            {
                unionSet.add(s1[i++]);
            }
            else if(comparisonResult > 0) //il secondo elemento è minore del primo
            {
                unionSet.add(s2[j++]);
            }
            else //sono uguali
            {
                unionSet.add(s1[i++]); //aggiungo l'elemento in s1, non cambierebbe nulla se inserissi l'elemento di s2, purché...
                j++; //... faccia l'incremento anche di j
            }
        }

        //Con questi ultimi due cicli inserisco anche quegli elementi che rimangono nei due insiemi ma che non si possono confrontare con quelli dell'altro
        while(i < this.size())
        {
            unionSet.add(s1[i++]);
        }
        while(j < s.size())
        {
            unionSet.add(s2[j++]);
        }

        return unionSet;
    }

    /**
     * NOTA BENE: il seguente metodo, sebbene non richiesto dalla consegna, è stato aggiunto per scopi didattici
    **/

    public Set subtract(Set s)
    {
        Set subtractionSet = new Studenti();
        Comparable[] s1 = this.toArray(), s2 = s.toArray();

        int i, j; //Dichiaro queste variabili fuori dal ciclo perché dopo mi servirà la "i" (non posso dichiarare la j nel ciclo e la i no)
        
        /**
         * Con questo ciclo faccio una prima parte dell'inserimento nell'insieme sottrazione:
         * inserisco i valori non contenuti nell'insieme passato come argomento (s) finché non arrivo a
         * valutare tutti gli elementi di quest'ultimo
        **/

        for(i = 0, j = 0; i < s1.length; i++)
        {
            //Se i valori dell'insieme 1 sono maggiori di quelli dell'insieme 2, allora "risparmia" calcoli
            while(j < s2.length && s1[i].compareTo(s2[j]) > 0) j++;

            //Evito l'ArrayIndexOutOfBoundsException con questa condizione
            if(j == s2.length) break;

            //Ora se i valori dell'insieme 1 d 2 sono diversi, vengono inseriti nel insieme sottrazione
            if(s1[i].compareTo(s2[j]) != 0) subtractionSet.add(s1[i]);
        }

        /**
         * Eseguo ora il ciclo finale che mi consente di inserire gli elementi rimanenti del primo insieme,
         * che sappiamo con certezza non essere assolutamente contenuti nel secondo insieme
         */

        while(i < s1.length)
        {
            subtractionSet.add(s1[i++]);
        }

        return subtractionSet;
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