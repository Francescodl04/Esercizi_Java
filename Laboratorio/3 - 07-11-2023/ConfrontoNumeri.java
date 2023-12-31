/*
  Francesco Di Lena
  Esercizio di laboratorio 03-3 - Fondamenti di informatica 
  07-11-2023
 */

import java.util.Scanner;

public class ConfrontoNumeri{
    public static void main(String[] args){
        final double EPSILON = Math.pow(10, -2);
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma. Inserisci il primo numero in virgola mobile:");
        double n1 = console.nextDouble();
        System.out.println("Inserisci ora il secondo numero in virgola mobile:");
        double n2 = console.nextDouble();
        console.close();
        if(Math.abs(n1 - n2) <= EPSILON * 0.01){
            System.out.println("\nRisultato: i due numeri inseriti sono approsimativamente uguali.");
            System.exit(0);
        }
        System.out.println("\nRisultato: i due numeri inseriti NON sono approsimativamente uguali.");
    }
}