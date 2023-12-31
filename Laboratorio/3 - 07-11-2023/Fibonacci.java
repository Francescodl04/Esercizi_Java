/*
  Francesco Di Lena
  Esercizio di laboratorio 03-7 - Fondamenti di informatica 
  07-11-2023
*/

import java.util.Scanner;

public class Fibonacci{
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma. Inserisci il valore \"n\" da elaborare con Fibonacci:");
        int n = console.nextInt();
        while(n <= 0){
            System.out.println("Non hai inserito un valore corretto, riprova:");
            n = console.nextInt();
        }
        console.close();
        int fibonacciValue = 0;
        switch(n){
            case 1:
            case 2:
                fibonacciValue = 1;
                break;
            default:
                int previous = 1; //numero che si trova prima di quello che consideriamo ora
                int current = 1; //numero che stiamo considerando ora
                for(int i = 3; i <= n; i++){
                    int tmp = previous; //uso la variaible temporanea per permettere di salvare il valore attuale di previous prima di aggiornarlo
                    previous = current;
                    current += tmp;
                }
                fibonacciValue = current;
                break;
        }
        System.out.printf("\nIl valore della sequenza con n = %d e' FIB(n) = %d\n", n, fibonacciValue);
    }
}