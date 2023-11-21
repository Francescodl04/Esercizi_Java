/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica
 * 21-11-2023
*/

import java.util.Scanner;

public class Esercizio3
{
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		System.out.println("Benvenuto nel programma. Inserisci il valore massimo di generazione dei numeri:");
		int max = Integer.parseInt(console.nextLine()); 
		while(max <= 1)
		{
			System.out.println("Devi inserire un numero maggiore di 1, riprova:");
			max = Integer.parseInt(console.nextLine());
		}
		boolean[] numbers = new boolean[max];
		for(int i = 0; i < numbers.length; i++)
		{
			numbers[i] = false; //tutti i numeri sono primi (condizione iniziale richiesta)
		} 
		for(int i = 2; i < numbers.length; i++)
		{
			if(!numbers[i])
			{
				for(int j = i * 2; j < numbers.length ; j += i)
				{
					numbers[j] = true;        
				}
			}
		}
		System.out.printf("Ecco i numeri primi fino a %d (non compreso):\n", max); 
		for(int i = 1; i < numbers.length; i++)
		{
			if(!numbers[i])
			{
				System.out.printf("%d ", i);
			}
			if(i == numbers.length - 1)
			{
				System.out.println();
			}
		}
	}
}
