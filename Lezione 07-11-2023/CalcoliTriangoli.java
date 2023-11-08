/*
  Francesco Di Lena
  Esercizio di laboratorio esempio 1 - Fondamenti di informatica 
  07-11-2023
 */


import java.util.Scanner;

public class CalcoliTriangoli{
	public static void main(String[] args){
		double alpha = 90.00 , beta, gamma;
		Scanner console = new Scanner(System.in);
		System.out.println("Benvenuto nel programma. Inserisci il valore del primo cateto in cm:");
		double a = console.nextDouble(); //cateto1 
		System.out.println("Inserisci il valore del secondo cateto in cm:");
		double b = console.nextDouble(); //cateto2
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)); //ipotenusa
		double perimetro = a + b + c;
		double p = perimetro / 2;
		double area = Math.sqrt(p * (p - a) * (p - b) * (p - c)); //calcolo l'area usando la formula di Erone 
		beta = Math.toDegrees(Math.asin(b/c));
		gamma = 180 - alpha - beta;
		System.out.println("Ecco i risultati dei calcoli sui numeri inseriti:");
		System.out.printf("Ipotenusa: %.2f cm\n", a);
		System.out.printf("Perimetro: %.2f cm\n", perimetro);
		System.out.printf("Area: %.2f cm^2\n", area);
		System.out.printf("Angolo beta: %.2f\u00B0\n", beta);
		System.out.printf("Angolo gamma: %.2f\u00B0\n", gamma);
		console.close();
	}
}
