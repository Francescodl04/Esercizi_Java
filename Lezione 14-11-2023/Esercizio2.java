/*
 * Francesco Di Lena
 * Esercizio 2 - Laboratorio di fondamenti di informatica
 * 14-11-2023
*/

import java.util.Scanner;

public class Esercizio2
{
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		System.out.println("Benvenuto nel programma. Inserisci il valore massimo per la generazione dei numeri primi:");
		int max = console.nextInt();
		while( max <= 0)
		{
			System.out.println("Non hai inserito un numero corretto, riprova:");
			max = console.nextInt();
		}
		for(int i = 1; i < max; i++)
		{
			int count = 0;
			for(int j = 1; j <= i; j++)
			{
				if(i % j == 0)
				{
					count++;
				}
			}
			if(count == 2 || i == 1)
			{
				if(i != max - 1)
				{
					System.out.printf("%d, ", i);
					continue;
				}
				System.out.printf("%d\n", i);
			}
		}
	}
}
