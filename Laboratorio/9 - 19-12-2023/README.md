# Laboratorio e lezione del 19-12-2023

## Esercizio 1
Scrivere un metodo statico che riceve una pila di oggetti e la modifica in modo che al suo interno gli oggetti siano ordinati, con l'elemento minore in cima alla pila (si supponga, ovviamente, che tutti gli oggetti siano esemplari di una stessa classe che realizza l'interfaccia Comparable).

Il metodo non deve usare array o altre strutture dati, ma soltanto pile.
Si suggerisce di prendere spunto dall'algoritmo di ordinamento per selezione.
Si tenga presente che sono sufficienti altre due pile, oltre a quella ricevuta come argomento e che dovrà contenere il risultato dell'elaborazione.

Se non si riesce ad individuare un algoritmo per la soluzione del problema, si realizzi questo.

Per collaudare il corretto funzionamento del metodo, scrivere un programma che

- legge dallo standard input un insieme (di dimensione non predeterminata) di numeri interi, in formato libero (cioè anche più numeri su una stessa riga, separati da spazi), inserendoli in una pila
- invoca il metodo appena progettato
- visualizza sullo standard output i numeri dell'insieme in ordine crescente, uno per riga. estraendoli dalla pila

Modificare ora l'algoritmo in modo da svolgere la stessa elaborazione usando una sola pila aggiuntiva.


## Esercizio 2
Scrivere un metodo statico che riceve una coda di oggetti e vi elimina eventuali oggetti duplicati (cioè oggetti dello stesso tipo aventi le stesse informazioni di stato).
Il metodo non deve usare alcuna struttura dati aggiuntiva oltre a quella ricevuta come parametro esplicito.
Si noti che il metodo non deve restituire una nuova coda, ma deve modificare il contenuto della coda ricevuta come parametro esplicito.

Inserire il metodo in una classe eseguibile il cui metodo main:

- legge dallo standard input un insieme (di dimensione non predeterminata) di numeri interi, in formato libero (cioè anche più numeri su una stessa riga, separati da spazi), inserendoli in una coda
- invoca il metodo appena progettato
- visualizza sullo standard output i numeri dell'insieme, uno per riga. estraendoli dalla coda


Si noti che le prestazioni asintotiche di questo algoritmo che opera sulla coda sono identiche a quelle dell'esercizio precedente, ma l'esercizio precedente non può essere risolto usando e modificando soltanto la pila ricevuta come parametro.


## Esercizio 3
Coda doppia
Il tipo di dato astratto CD coda doppia è definito dalla seguente interfaccia Java:

public interface CD
{ int size();
boolean isEmpty();
void addFirst (Object x); // aggiunge all'inizio della coda
void addLast (Object x); // aggiunge alla fine della coda
Object removeFirst () throws EmptyCDException; // toglie dall'inizio
Object removeLast () throws EmptyCDException; // toglie dalla fine
Object getFirst () throws EmptyCDException; // elemento all'inizio
Object getLast () throws EmptyCDException; // elemento alla fine
}

Scrivere la classe MiaCD che realizza l'interfaccia CD in modo che tutte le primitive richiedano un tempo O(1) oppure un tempo O(1) in termini di analisi ammortizzata.

Scrivere una classe Main di prova che:
- crei tre esemplari della classe MiaCD di nome uno, due e tre
- legga dall'ingresso standard una sequenza di numeri interi (uno per riga), li inserisca alla fine della coda uno, svuoti la coda uno dalla fine (con removeLast) trasferendone il contenuto all'inizio della coda due, svuoti la coda due dall'inizio (con removeFirst) inserendo i dati alla fine della coda tre, svuoti infine la coda tre dall'inizio scrivendo i dati sull'uscita standard, uno per riga. I dati in uscita dovranno essere nello stesso ordine dei dati in ingresso.


## Esercizio 4


Realizzare un programma che:
non scriva alcun dato sui flussi standard, tranne quanto esplicitamente indicato in queste specifiche

- legga il flusso di ingresso riga per riga, fino alla sua chiusura

- visualizzi sull'uscita standard le singole righe del testo, ordinate in base al numero crescente di parole contenute (cioè per prime vengono visualizzate le righe prive di parole, poi le righe con una sola parola, poi quelle con due parole, e così via)

- a parità di numero di parole, vanno visualizzate per prime le righe aventi un numero minore di caratteri; in caso di parità anche nel numero di caratteri, vanno visualizzate per prime le righe che precedono le altre nell'ordine lessicografico

- eventuali righe identiche vanno visualizzate una volta sola (ad esempio, è probabile che la visualizzazione inizi con una riga vuota, se nel testo c'è almeno una riga priva di caratteri...)

- termina la propria esecuzione