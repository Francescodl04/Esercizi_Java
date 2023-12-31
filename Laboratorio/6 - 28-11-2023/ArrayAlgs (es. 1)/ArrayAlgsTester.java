/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 28-11-2023
 * Classe di test di ArrayAlgs
*/

import java.util.Scanner;
import java.util.Random;

public class ArrayAlgsTester
{
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		System.out.println("Benvenuto nel programma. Inserisci la lunghezza dell'array che desideri generare:");
		int length = Integer.parseInt(console.nextLine());
		System.out.println("Inserisci il limite massimo di generazione:");
		int n = Integer.parseInt(console.nextLine());
		ArrayAlgs algs = new ArrayAlgs();
		int[] values = algs.randomIntArray(length, n);
		if(values == null) 
		{
			System.out.println("C'e' stato un errore nella creazione dell'array. Riavvia il programma e riprova...");
			System.exit(1);
		}
		
		System.out.println("L'array e' stato creato correttamente. Ora scegli una delle seguenti opzioni:");
		System.out.print("- S per ordinare con selection sort\n- I per ordinare con insertion sort\n- M per ordinare con merge sort\n- Q per passare alla prossima funzione\n");
		boolean inputIsWrong = false;
		do
		{
			switch(console.nextLine().toUpperCase())
			{
				case "S":
					algs.selectionSort(values, length);
					System.out.printf("Ecco l'array ordinato:\n %s\n", algs.printArray(values, length));
					break;
				case "I":
					algs.insertionSort(values, length);
					System.out.printf("Ecco l'array ordinato:\n %s\n", algs.printArray(values, length));
					break;
				case "M":
					algs.mergeSort(values, length);
					System.out.printf("Ecco l'array ordinato:\n %s\n", algs.printArray(values, length));
					break;
				case "Q":
					break;
				default:
					System.out.println("Non hai inserito una valore corretto, riprova:");
					inputIsWrong = true;
					break;
			}
		}
		while(inputIsWrong);
		
		System.out.println("Inserisci ora un numero intero positivo da ricercare:");
		int numberToBeSearched = Integer.parseInt(console.nextLine());
		while(numberToBeSearched <= 0)
		{
			System.out.println("Non hai inserito un numero corretto, riprova:");
			numberToBeSearched = Integer.parseInt(console.nextLine());	
		}
		System.out.println("Per ricercare il valore inserito scegli una delle seguenti opzioni:");
		System.out.print("- L per eseguire una ricerca lineare\n- B per eseguire una ricerca binaria\n- Q per uscire dal programma\n");
		inputIsWrong = false;
		int firstResultIndex = 0;
		do
		{
			switch(console.nextLine().toUpperCase())
			{
				case "L":
					firstResultIndex = algs.linearSearch(values, length, numberToBeSearched);
					break;
				case "B":
					firstResultIndex = algs.binarySearch(values, length, numberToBeSearched);
					break;
				case "Q":
					console.close();
					System.exit(0);
					break;
				default:
					System.out.println("Non hai inserito una valore corretto, riprova:");
					inputIsWrong = true;
					break;
			}
		}
		while(inputIsWrong);
		console.close();
		if(firstResultIndex == -1) 
		{
			System.out.println("Non e' stato trovato nessun valore corrispondente a quello ricercato");
			System.exit(0);
		}
		System.out.printf("Il valore \"%d\" ricercato e' stato trovato all'indice \"%d\" dell'array.\n", numberToBeSearched, firstResultIndex);
	}
}
