/*
 * Francesco Di Lena
 * Esercizio 3 - laboratorio di fondamenti di informatica
 * 19-12-2023
 * Classe main
*/

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Main
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        MiaCD uno = new MiaCD(), due = new MiaCD(), tre = new MiaCD();
        System.out.println("Benvenuto. Inserisci di seguito, uno per riga, un numero intero (termina inserendo CTRL + Z):");
        do
        {
            try
            {
                uno.addLast(Integer.parseInt(console.nextLine()));
            }
            catch(NumberFormatException e)
            {
                System.out.println("Non hai inserito un numero intero, riprova...");
                continue;
            }
            catch(NoSuchElementException e) //Se l'utente non inserisce nessun numero, allora esco direttamente dal programma
            {
                System.out.println("Non e' possibile proseguire perche' non hai inserito alcun numero...");
                System.exit(0);
            }
        }
        while(console.hasNextLine());
        
        while(!uno.isEmpty())
        {
            due.addFirst(uno.removeLast());
        }

        while(!due.isEmpty())
        {
            tre.addLast(due.removeFirst());
        }

        System.out.println("Bene, hai terminato l'inserimento. Ora ti mostro il contenuto di cio' che hai inserito nella coda:");

        while(!tre.isEmpty())
        {
            System.out.println(tre.removeFirst());
        }
    }
}