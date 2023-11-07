/*
  Francesco Di Lena
  Esercizio di laboratorio - Fondamenti di informatica 
  07-11-2023
 */
 
 import java.util.Scanner;
 
 public class Esercizio3{
 	public static void main(String[] args){
 		Scanner console = new Scanner(System.in);
 		System.out.println("Benvenuto nel programma. Inserisci un numero maggiore di 10:");
 		int numero = console.nextInt();
 		if(numero < 10){
 			System.exit(1);
 		}
 		System.out.println("Ecco i numeri tra 10 e " + numero + ":"); 
 		for(int i = 10; i <= numero; i++){
 			System.out.print(i);
 			if(i < numero)
 				System.out.print(", ");
 		}
 		System.out.println();
 		console.close();
 	}
 }
