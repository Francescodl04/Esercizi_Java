/*
 * Francesco Di Lena
 * Esercizio 2 - Laboratorio di fondamenti di informatica
 * 28-11-2023
*/

import java.util.Scanner;

public class Esercizio2
{
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		String string1 = "", string2 = "";
		switch(args.length)
		{
			case 0:
				System.out.println("Inserisci la prima stringa:");
				string1 = console.nextLine();
				System.out.println("Inserisci la seconda stringa:");
				string2 = console.nextLine();
				break;
			case 1:
				string1 = args[0];
				System.out.println("Inserisci la seconda stringa:");
				string2 = console.nextLine();
				break;
			case 2:
				string1 = args[0];
				string2 = args[1];
				break;
			default:
			System.out.println("Non hai inserito un numero corretto di argomenti. Uscita dal programma in corso...");
			System.exit(1);
			break;
		}
		if(reverseEquals(toArray(string1), toArray(string2)))
		{
			System.out.printf("Le stringhe \"%s\" e \"%s\" sono una l'inversa dell'altra\n", string1, string2);
			System.exit(0);
		}
		System.out.printf("Le stringhe \"%s\" e \"%s\" NON sono una l'inversa dell'altra\n", string1, string2);
		
	}
	
	public static char[] toArray(String string) throws IllegalArgumentException
	{
		if(string == null) throw new IllegalArgumentException();
		
		char[] stringArray = new char[string.length()];
		for(int i = 0; i < stringArray.length; i++)
		{
			stringArray[i] = string.charAt(i);
		}
		return stringArray;
	}
	
	public static boolean reverseEquals(char[] string1, char[] string2) throws IllegalArgumentException
	{
		if(string1 == null || string2 == null) throw new IllegalArgumentException();
		
		if(string1.length != string2.length) return false;
		
		int j = string1.length - 1;
		for(int i = 0; i < string1.length; i++)
		{
			if(string1[i] != string2[j--]) return false;
		}
		return true;
	}
}
