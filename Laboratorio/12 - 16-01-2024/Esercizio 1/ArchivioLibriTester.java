/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 16-01-2024
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
        }
        catch(IOException io)
        {
            System.out.println("Non e' stato possibile accedere al file, riprova con una nuova esecuzione...");
            System.exit(1);
        }
        catch(NoSuchElementException nse)
        {
            System.out.println("Il file inserito come argomento non presenta un formato corretto, riprova con una nuova esecuzione...");
            System.exit(1);
        }
    }
}