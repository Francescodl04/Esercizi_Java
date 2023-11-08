/*
  Francesco Di Lena
  Esercizio di laboratorio esempio 2- Fondamenti di informatica 
  07-11-2023
 */

import java.util.Scanner;

public class CalcolaAngoli{
	public static void main (String[] args){
		Scanner console = new Scanner(System.in);
		System.out.println("Benvenuto nel programma. Inserisci un angolo in gradi:");
		double angolo = console.nextDouble();
		double angoloRadianti = Math.toRadians(angolo);
		System.out.printf("Angolo = %.3f * \u220F rad\n", angoloRadianti / Math.PI);
		System.out.printf("sin(%.3f*\u220F) = %.3f\n", angoloRadianti / Math.PI, Math.sin(angoloRadianti));
		System.out.printf("cos(%.3f*\u220F) = %.3f\n", angoloRadianti / Math.PI, Math.cos(angoloRadianti));
		System.out.printf("tan(%.3f*\u220F) = %.3f\n", angoloRadianti / Math.PI, Math.tan(angoloRadianti));
		console.close();
	}
}
