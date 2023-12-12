/*
 * Francesco Di Lena
 * Esercizio 2 - Laboratorio di fondamenti di informatica
 * 12-12-2023
 */
 
 import java.util.Scanner;
 import java.util.regex.Pattern;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.io.IOException;
 
 public class Esercizio2
 {
 	public static void main(String[] args) throws IOException
 	{
 		Scanner console = new Scanner(System.in);
 		
 		System.out.println("Benvenuto. Inserisci il percorso del primo file in lettura:");
 		FileReader reader = new FileReader(console.nextLine());
 		Scanner input = new Scanner(reader);
 		
 		System.out.println("Inserisci ora il percorso del secondo file in scrittura:");
 		FileWriter writer = new FileWriter(console.nextLine(), true);
 		PrintWriter output = new PrintWriter(writer);
 		
 		String firstFileContent = "";
 		
 		Pattern pattern = Pattern.compile(" ");
 		input = input.useDelimiter(pattern);
 		
 		while(input.hasNext()) //uso il delimitatore di default (spazio)
 		{
 			String word = input.next();
 			word = Character.toUpperCase(word.charAt(0)) + word.substring(1, word.length()).toLowerCase();
 			firstFileContent += word + " ";
 		}
 		output.print(firstFileContent);
 		System.out.println("Operazione di scrittura eseguita correttamente.");
 		output.close();
 	}
 }
