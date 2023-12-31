/*
 * Francesco Di Lena
 * Esercizio 2 - Laboratorio di fondamenti di informatica
 * 21-11-2023
 * Classe di test
*/

import java.util.Scanner;

public class QuadraticEquationTester
{
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		System.out.println("Benvenuto nel programma. Inserisci il parametro a:");
		double a = Double.parseDouble(console.nextLine());
		System.out.println("Inserisci il parametro b:");
		double b = Double.parseDouble(console.nextLine());
		System.out.println("Inserisci il parametro c:");
		double c = Double.parseDouble(console.nextLine());
		console.close();
		QuadraticEquation quadraticEquation = new QuadraticEquation(a, b, c);
		if(!quadraticEquation.hasSolutions())
		{
			System.out.printf("L'equazione %s non possiede soluzioni\n", quadraticEquation);
			System.exit(0);
		}
		if(quadraticEquation.hasSolutionsParams() == 3)
		{
			System.out.printf("L'equazione %s possiede infinite soluzioni\n", quadraticEquation);
			System.exit(0);
		}
		if(quadraticEquation.getDelta() == 0)
		{
			System.out.printf("L'unica soluzione dell'equazione %s e' %.2f\n", quadraticEquation, quadraticEquation.getSolution1());
			System.exit(0);
		}
		System.out.printf("L'equazione %s ha soluzioni:\n1) %.2f\n2) %.2f\n", quadraticEquation, quadraticEquation.getSolution1(), quadraticEquation.getSolution2());
	}
}
