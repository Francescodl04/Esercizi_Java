/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica
 * 14-11-2023
*/

import java.util.Scanner;

public class Esercizio3
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        int n, m;
        System.out.println("Benvenuto nel programma. Inserisci il primo numero:");
        do
        {
            n  = console.nextInt();
        }
        while(n <= 0);
        System.out.println("Inserisci il secondo numero (maggiore del primo):");
        do
        {
            m  = console.nextInt();
        }
        while(m <= 0 | m < n);
        while( m % n != 0)
        {
            int tmp = m;
            m = n;
            n = tmp % m;
        }
        System.out.printf("\nIl MCD dei due numeri inseriti e' \"%d\"\n", n);
    }
}