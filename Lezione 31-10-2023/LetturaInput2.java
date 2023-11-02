/*
* Francesco Di Lena
* A.A. 2023-20224 - Fondamenti di informatica
* Esercizio di laboratorio 02-2 - versione 2
*/

import java.util.Scanner;

public class LetturaInput2{
    public static void main(String[] args){

        final int MAX_CIFRE = 5;
        final int MIN_NUMERO = 0;

        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma (v2). Inserisci un numero intero positivo da massimo cinque cifre:");
        String numero = console.next();
        String numeroFormattato = numero;
        while(numero.length() > MAX_CIFRE || numero.length() == 0 || Integer.parseInt(numero) < MIN_NUMERO)
        {
            System.out.println("Non hai inserito un numero, hai superato le cifre consentite oppure hai inserito un numero negativo. Riprova:");
            numero = console.next();
        }
        for(int i = 0; i < MAX_CIFRE - numero.length(); i++)
        {
            numeroFormattato = "0" + numeroFormattato;
        }
        System.out.println("Ecco le singole cifre del numero inserito: ");
        for(int i = 0; i < numeroFormattato.length(); i++)
        {
            System.out.print(numeroFormattato.substring(i, i + 1) + " ");
        }
        console.close();
    }
}