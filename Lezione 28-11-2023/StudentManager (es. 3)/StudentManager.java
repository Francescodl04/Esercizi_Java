/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica
 * 28-11-2023
 * Classe di test della classe Student
*/

import java.util.*;

public class StudentManager
{
	public static void main(String[] args)
	{
		final int N_PARAMS = 3;
	
		Scanner console = new Scanner(System.in);
		Student[] students = new Student[0];
		String input = "";
		int counter = 0;
		System.out.println("Benvenuto nel programma");
		do
		{
			System.out.printf("Inserisci il cognome, il voto scritto e il voto orale del %d\' studente separati da un SOLO spazio:\n", counter + 1);
			input = console.nextLine();
			if(input.equals(""))
			{
				System.out.println("INSERIMENTO STUDENTI TERMINATO\n");
				break;
			}
			Scanner inputScan = new Scanner(input);
			inputScan.useDelimiter(" ");
			try
			{
				Student student = new Student(inputScan.next(), Double.parseDouble(inputScan.next()), Double.parseDouble(inputScan.next()));
				students = resize(students, ++counter);
				students[counter - 1] = student;
			}
			catch(NumberFormatException e)
			{
				System.out.println("ERRORE: non hai inserito correttamente uno o entrambi i parametri voto, per cui ripeti l'inserimento di tutte le informazioni dello studente.");
				continue;
			}
			catch(NoSuchElementException n)
			{
				System.out.println("ERRORE: devi inserire tre parametri per ogni studente, non di meno, per cui ripeti l'inserimento di tutte le informazioni dello studente.");
				continue;
			}
		}
		while(true);
		System.out.println("Bene, ora che hai inserito tutti gli studenti scegli una delle seguenti opzioni:");
		do
		{
			System.out.print("- S per accedere alla funzionalita' di ricerca studenti\n- Q per uscire dal programma\n");
			boolean inputIsWrong = false;
			do
			{
				switch(console.nextLine().toUpperCase())
				{
					case "S":
						inputIsWrong = false;
						System.out.println("Inserisci il cognome dello studente che desideri ricercare:");
						String searchingKey = console.nextLine();
						int resultIndex = linearSearch(students, searchingKey);
						if(resultIndex == -1)
						{
							System.out.printf("\nNon e' stato trovato nessuno studente con cognome \"%s\", riprovare con un altro cognome...\n\n", searchingKey);
							break;
						}
						System.out.printf("\nEcco lo studente cercato:\n%s\n\n", toString(students, resultIndex));
						break;
					case "Q":
						inputIsWrong = false;
						System.exit(0);
						break;
					default:
						System.out.println("Non hai inserito un valore corretto, riprova:");
						inputIsWrong = true;
						break;
				}
			}
			while(inputIsWrong);
		}
		while(true);

	}

	public static int linearSearch(Student[] students, String key)
	{
		for(int i = 0; i < students.length; i++)
		{
			if(students[i].getSurname().equals(key)) return i;
		}
		return -1;
	}

	public static Student[] resize(Student[] old, int newLength)
	{
		Student[] newArray = new Student[newLength];
		System.arraycopy(old, 0, newArray, 0, old.length);
		return newArray;
	}

	public static String toString(Student[] students, int index)
	{
		return "Cognome: " + students[index].getSurname() + "\n" + "Voto scritto: " + students[index].getWrittenTest() + "\n" + "Voto orale: " + students[index].getOralTest();
	}
}
