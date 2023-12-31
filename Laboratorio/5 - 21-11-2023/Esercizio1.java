/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 21-11-2023
*/

import java.util.Scanner;

public class Esercizio1
{
	public static void main (String[] args)
	{
		Scanner console = new Scanner(System.in);
		System.out.println("Benvenuto nel programma. Inserisci una stringa:");
		String word = console.nextLine();
		while(word == "")
		{
			System.out.println("Non puoi inserire una stringa vuota, riprova...");
			word = console.nextLine();
		}
		console.close();
		String reversed = ""; // Rappresenta la stringa "girata"
		for(int i = word.length() - 1; i >= 0; i--)
		{
			reversed += word.charAt(i);
		}
		if(reversed.equals(word))
		{
			System.out.printf("La stringa \" %s \" e' palindroma\n", word);
			System.exit(0);
		}
		System.out.printf("La stringa \" %s \" NON e' palindroma\n", word);
	}
}

