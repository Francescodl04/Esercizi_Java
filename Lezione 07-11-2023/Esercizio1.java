/*
  Francesco Di Lena
  Esercizio di laboratorio - Fondamenti di informatica 
  07-11-2023
 */
 
 public class Esercizio1{
 	public static void main(String[] args){
 		int somma = 0, prodotto = 1;
 		for(int i = 1; i < 11; i++){
 			somma+=i;
 			prodotto*=i;
 		}
 		System.out.printf("Benvenuto nel programma (esercizio 1)\nLa somma e'%d, mentre il prodotto e' %d\n", somma, prodotto);
 	}
 }
