/*
  Francesco Di Lena
  Esercizio di laboratorio 03-1-5 - Fondamenti di informatica 
  07-11-2023
 */
 
 public class Esercizio5{
 	public static void main (String[] args){
 		int MIN_X = 1, MIN_Y = 1, MAX_X = 4, MAX_Y = 5;
 		for(int i = MIN_Y; i <= MAX_Y; i++){
			System.out.printf("Ora stampo le potenze con esponente %d dal numero %d al numero %d:\n", i, MIN_X, MAX_X);
 			for(int j = MIN_X; j <= MAX_X; j++){
 				System.out.print(Math.pow(j,i) + " ");
 			}
 			System.out.println();
 		}
 	}
 }
