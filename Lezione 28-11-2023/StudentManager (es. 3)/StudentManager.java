/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica
 * 28-11-2023
 * Classe di test della classe Student
*/

import java.util.Scanner;

public class StudentManager
{
	public static void main(String[] args)
	{
		final int N_PARAMS = 3;
	
		Scanner console = new Scanner(System.in);
		Student[] students = new Student[0];
		String input = "";
		int counter = 0;
		do
		{
			System.out.println("Inserisci il cognome, il voto scritto e il voto orale di uno studente separati da un SOLO spazio:");
			String input = console.nextLine();
			if(input.equals(" ") break;
			Scanner inputScan = new Scanner(input);
			inputScan.useDelimiter(" ");
			Student student = new Student(inputScan.next(), inputScan.next(), inputScan.next());
			students = resize(students, ++counter);
			student[counter - 1] = student;
		}
		while(true);
	}
	
	public static Student[] resize(Student[] old, int newLength)
	{
		Student[] newArray = new Student[newLength];
		System.arraycopy(old, 0, newArray, 0, old.length);
		return newArray;
	}
}
