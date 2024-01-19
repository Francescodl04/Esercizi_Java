/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 16-01-2024
 * Classe di test
*/

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.IOException;

public class ArchivioLibriTester
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        ArchivioLibri archive = new ArchivioLibri();

        System.out.println("Benvenuto nel programma.");
        if(args.length != 1)
        {
            System.out.println("Non hai inserito un corretto numero di argomenti, riprova con una nuova esecuzione...");
            System.exit(0);
        }
        try
        {
            FileReader reader = new FileReader(args[0]);
            Scanner inputFile = new Scanner(reader);
            while(inputFile.hasNextLine())
            {
                Scanner row = new Scanner(inputFile.nextLine());
                archive.aggiungi(row.next(), row.next(), Integer.parseInt(row.next()));
            }
        }
        catch(IOException io)
        {
            System.out.println("Non e' stato possibile accedere al file, riprova con una nuova esecuzione...");
            System.exit(1);
        }
        catch(NoSuchElementException | NumberFormatException e)
        {
            System.out.println("Il file inserito come argomento non presenta un formato corretto, riprova con una nuova esecuzione...");
            System.exit(1);
        }
        System.out.print("L'archivio e' stato inserito. ");
        do
        {
            System.out.println("Inserisci ora l'aggiornamento che desideri attuare (\"chiave\" \"cambio quantita'\"): ");
            try
            {
                String input = console.nextLine();
                String[] changesToBeApplied = input.split(" ");
                if(changesToBeApplied.length != 1 && changesToBeApplied.length != 2)
                {
                    System.out.println("Non hai inserito dei parametri corretti, riprova...");
                }

                Libro bookToBeChanged = null;
                try
                {
                    bookToBeChanged = archive.ricerca(changesToBeApplied[0]);
                }
                catch(NoSuchElementException nse1) //Questa eccezione e' diversa da quella che permette di uscire dal programma
                {
                    System.out.printf("Il libro con codice \"%s\" non e' stato trovato, riprova con un altro nome...\n", changesToBeApplied[0]);
                    continue;
                }

                int bookAvailableQuantity = bookToBeChanged.numcopie();
                if(changesToBeApplied.length == 1) //se viene inserito solamente il codice...
                {
                    archive.cancella(changesToBeApplied[0]);
                    System.out.println("Eliminazione eseguita con successo!");
                }
                else //se viene inserito sia codice che quantità da aggiornare...
                {
                    int newQuantity = bookAvailableQuantity + Integer.parseInt(changesToBeApplied[1]);
                    if(newQuantity >= 0)
                    {
                        archive.modifica(changesToBeApplied[0], newQuantity);
                        System.out.printf("Modifica eseguita con successo: %s\n", bookToBeChanged.toString());
                    }
                    else
                    {
                        System.out.println("Hai inserito una quantita' maggiore di quella effettivamente disponibile, riprova...");
                        continue;
                    }
                }
            }
            catch(NumberFormatException nf)
            {
                System.out.println("Non hai inserito un numero come quantita', riprova...");
                continue;
            }
            catch(NoSuchElementException nse0) //Inserendo una riga vuota si termina il programma
            {
                break;
            }
        }
        while(true);
    }
}