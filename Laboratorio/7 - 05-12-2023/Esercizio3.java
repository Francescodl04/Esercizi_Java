/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica
 * 05-12-2023
*/

import java.util.Scanner;

public class Esercizio3
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto. Inserisci il numero per cui utilizzare Fibonacci:");
        int n = Integer.parseInt(console.nextLine());

        // Prima esecuzione con l'algoritmo iterativo
        
        long iterativeTime = System.currentTimeMillis();
        long iterativeResult = iterativeFib(n);
        iterativeTime = System.currentTimeMillis() - iterativeTime;
        System.out.printf("Il risultato con algoritmo iterativo e' \"%d\", realizzato in un tempo di %d millisecondi.\n", iterativeResult, iterativeTime);

        // Seconda esecuzione con l'algoritmo ricorsivo
        
        long recursiveTime = System.currentTimeMillis();
        long recursiveResult = recursiveFib(n);
        recursiveTime = System.currentTimeMillis() - recursiveTime;
        System.out.printf("Il risultato con algoritmo ricorsivo e' \"%d\", realizzato in un tempo di %d millisecondi.\n", recursiveResult, recursiveTime);
    }

    public static long iterativeFib(int n) throws IllegalArgumentException
    {
        if(n < 0) throw new IllegalArgumentException();

        if(n == 0) return (long) 0;

        if(n == 1 || n == 2) return (long) 1;

        long previous = 1; //numero che si trova prima di quello che consideriamo ora
        long current = 1; //numero che stiamo considerando ora
        for(int i = 3; i <= n; i++)
        {
            long tmp = previous; //uso la variaible temporanea per permettere di salvare il valore attuale di previous prima di aggiornarlo
            previous = current;
            current += tmp;
        }
        
        return current;
    }

    public static long recursiveFib(int n) throws IllegalArgumentException
    {
        if(n < 0) throw new IllegalArgumentException();
        
        if(n == 0) return (long) 0;

        if(n == 1) return (long) 1;

        long previous = recursiveFib(n - 2);
        long current = recursiveFib(n - 1);
        
        return current + previous;
    }
}