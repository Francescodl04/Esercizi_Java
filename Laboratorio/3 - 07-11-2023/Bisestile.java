/*
  Francesco Di Lena
  Esercizio di laboratorio 03-5 - Fondamenti di informatica 
  07-11-2023
*/

import java.util.Scanner;

public class Bisestile{
    public static void main(String[] args){
        final int GREGORIAN_CALENDAR_START = 1582;
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma. Inserisci l'anno:");
        int year = console.nextInt();
        while(year < 0){
            System.out.println("ERRORE: non si e' inserito un anno corretto, riprova:");
            year = console.nextInt();
        }
        console.close();
        if(year % 4 != 0 | ((year % 100 == 0 && year % 400 != 0) && year >= GREGORIAN_CALENDAR_START)){
            System.out.println("L'anno inserito NON E' bisestile.");
            System.exit(1);
        }
        System.out.println("L'anno inserito E' bisestile.");
    }
}