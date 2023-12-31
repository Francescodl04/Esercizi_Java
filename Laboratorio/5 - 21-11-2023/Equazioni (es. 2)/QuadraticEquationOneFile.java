/*
 * Francesco Di Lena
 * Esercizio 2 - Laboratorio di fondamenti di informatica
 * 21-11-2023
 * Classe di test e fabbrica di oggetti in un unico file
*/

import java.util.Scanner;

public class QuadraticEquationOneFile
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

class QuadraticEquation
{
	private double a, b, c;
	
	public QuadraticEquation()
	{
		a = 0;
		b = 0;
		c = 0;
	}
	
	public QuadraticEquation(double acoeff, double bcoeff, double ccoeff)
	{
		this.a = acoeff;
		this.b = bcoeff;
		this.c = ccoeff;
	}
	
	// Metodi di accesso ai parametri dell'equazione
	
	public double getACoeff()
	{
		return a;
	}
	
	public double getBCoeff()
	{
		return b;
	}
	
	public double getCCoeff()
	{
		return c;
	}
	
	// Metodi che eseguono operazioni sui parametri
	
	public double getDelta()
	{
		return Math.pow(b, 2) - 4 * a * c;
	}
	
	// 	I due metodi seguenti permettono di ottenere le soluzione dell'equazione : in caso di delta = 0, restituiranno entrambi lo stesso risultato
	
	public double getSolution1()
	{
		return ((- b) - Math.sqrt(getDelta())) / (2 * a);
	}
	
	public double getSolution2()
	{
		return ((- b) + Math.sqrt(getDelta())) / (2 * a);
	}
	
	public boolean hasSolutions() 
	{
		if(getDelta() < 0)
		{
			return false;
		}
		return true;
	}
	
	public int hasSolutionsParams() //Versione alternativa del precedente metodo per la valutazione delle soluzioni in base ai parametri a,b,c
	{
    		if( a == b & b == 0 & c != 0) //nessuna soluzione
		{
			return 1;
		}
		else if (a == 0 && b != 0) //una soluzione
		{
			return 2;
		}
		else if(a == b & b == c & c == 0) //infinite soluzioni
		{
			return 3;
		}
		return 0; //due soluzioni
	}
	
	//Metodo per la creazione di una rappresentazione algebrica dell'equazione
	
	public String toString()
	{
		String acoeff = String.valueOf(a), bcoeff = String.valueOf(b), ccoeff = String.valueOf(c); //Numeri con segno (+ o -) da concatenare con la stringa dell'equazione
		// Aggiungo il segno piu' nel caso non lo possiedano per effettuare la somma algebrica
		if(b >= 0)
		{
			bcoeff = "+ " + bcoeff;
		}
		if(c >= 0)
		{
			ccoeff = "+ " + ccoeff;
		}
		return acoeff + "x^2 " + bcoeff + "x " + ccoeff;
	}
}
