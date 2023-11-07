/*
  Francesco Di Lena
  Esercizio di laboratorio - Fondamenti di informatica 
  07-11-2023
 */
 
 public class Esercizio2{
 	public static void main(String[] args){
 		int somma = 0;
 		long prodotto = 1;
 		for(int i = 1; i <= 20; i++){
 			if(i % 2 == 0){
 				somma+=i;
 				prodotto*= (long)i;
 			}
 		}
 		System.out.printf("Benvenuto nel programma (esercizio 2)\nLa somma e'%d, mentre il prodotto e' %d\n", somma, prodotto);
 	}
 }
