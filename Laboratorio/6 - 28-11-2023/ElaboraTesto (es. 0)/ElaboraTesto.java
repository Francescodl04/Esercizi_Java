/**
Algoritmo che acquisisce un testo da un file e costruisce un 
 nuovotesto in cui sono state sostituite tutte le occorrenze di una 
  parola con un'altra; la parola da sostituire e la nuova parola 
   vengono inserite dalla riga di comando (utilizzo del vettore args), 
     il file di ingresso e il file di uscita sono collegati con la ridirezione
*/

import java.util.Scanner;

public class ElaboraTesto {/*la parola da sostituire e' args[0], la nuova
					parola e' args[1]
			   */   
	public static void main(String args []){
   	Scanner in = new Scanner(System.in);

	String line;
	String parola;
	Scanner t;

	int i = 0;

/* Quando si incontra la fine del file il metodo hasNextLine restituisce
    false 
*/
	while(in.hasNextLine()) {
		line= in.nextLine();
		t = new Scanner(line);
		while(t.hasNext()) {
			parola = t.next();  
     			if (args[0].equals(parola)) {
				System.out.print(args[1] + " ");
	/**si contano le (eventuali) sostituzioni effettuate*/
				i++;}
			else System.out.print(parola + " ");
      		}
		System.out.println();
	}
         
      System.out.println("\nsono state effettuate " + i + " sostituzioni "+
		"di " + args[0] + " con " + args[1]);
      
   }//fine main
   
}//fine classe


/** Per la prova utilizzare il file      testo.txt

-  eseguendo il comando
 
	java ElaboraTesto<testo.txt>nuovotesto.txt mio tuo

 o anche 

	java ElaboraTesto mio tuo <testo.txt>nuovotesto.txt 

-  provare anche con una parola non presente nel testo 	

*/

