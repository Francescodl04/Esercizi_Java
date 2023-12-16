/*
 * Francesco Di Lena
 * Esercizio 2 - Laboratorio di fondamenti di informatica
 * 12-12-2023
 */


 //Classi di utilità generale
 import java.util.Scanner;
 import java.util.regex.Pattern;

 //Classi per la gestione dei file
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.io.IOException;
 
 public class Esercizio2
 {
 	public static void main(String[] args) throws IOException
 	{
 		Scanner input = null, console = new Scanner(System.in);
		FileReader reader = null;
 		
 		System.out.println("Benvenuto. Inserisci il percorso del primo file in lettura:");
		try
		{
 			reader = new FileReader(console.nextLine());
 			input = new Scanner(reader);
		}
		catch(IOException e)
		{
			System.out.println("Il nome del file inserito non e' corretto o e' inacessibile. Riprovare...");
			System.exit(1);
		}
 		System.out.println("Inserisci ora il percorso del secondo file in scrittura:");
 		FileWriter writer = new FileWriter(console.nextLine());
 		PrintWriter output = new PrintWriter(writer);
 		
 		String contentToPrint = "";
		int rows = 0;
 		
 		while(input.hasNextLine()) //uso il delimitatore di default (spazio)
 		{
			Scanner inputRow = new Scanner(input.nextLine());
			Pattern textPattern = Pattern.compile("\s|'"); //creo il pattern per la delimitazione delle stringhe (spazio vuoto o apostrofo)
			inputRow.useDelimiter(textPattern);
			while(inputRow.hasNext())
			{
 				String word = inputRow.next();
 				word = Character.toUpperCase(word.charAt(0)) + word.substring(1, word.length()).toLowerCase();
				contentToPrint += word;
 				if(inputRow.hasNext()) contentToPrint += " ";
			}
			rows++;
			if(input.hasNextLine()) contentToPrint += "\n";
		}
 		output.print(contentToPrint);
 		System.out.printf("Operazione di scrittura eseguita correttamente: righe lette %d\n", rows);
		
		console.close();
		input.close();
 		output.close();
 	}
 }
