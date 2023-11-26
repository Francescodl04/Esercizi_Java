/* 
 Costruzione di un file di numeri casuali da fornire come ingresso 
       agli algoritmi di ordinamento per effettuare prove sui tempi 
*/

import java.util.Scanner;
import java.util.Random;

public class GeneraCasuali{
	public static void main(String []arg){
	Scanner in = new Scanner(System.in);
	

/* Il programma legge i dati da Generacasuali.txt che contiene i valori per 
	le variabili    intervallo   e   dimensione  :
*/

// intervallo: un numero naturale, limite superiore dei valori casuali
	int intervallo = in.nextInt();

// dimensione dell'array da costruire
	int dimensione = in.nextInt();

	int [] a = new int [dimensione]; 
		Random generatore = new Random();
		for (int i = 0; i < a.length; i++) 
			a[i] = generatore.nextInt(intervallo);

/*Inserimento nel file casuali.dati usando la ridirezione: non si 
 introducono commenti nel file casuali.txt, perche' sara'
  l'ingresso per il successivo programma di ordinamento */

	System.out.println(dimensione);
	for(int i=0; i<a.length;i++){
		System.out.print(a[i] + "\t");
		if ((i+1) % 6 == 0 ) 
		     	System.out.println();

        }//fine for	
   }//fine main
}


/* Dare il comando

   java Generacasuali <Generacasuali.txt >casuali.txt

*/