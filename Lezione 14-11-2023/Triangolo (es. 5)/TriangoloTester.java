/*
 * Francesco Di Lena
 * Esercizio 5 - Laboratorio di fondamenti di informatica
 * 14-11-2023
 * Classe per il test della classe Triangolo
*/

import java.util.Scanner;
import java.util.Locale;

public class TriangoloTester
{
   public static void main (String[] args)
   {
      Scanner in = new Scanner(System.in);
      in.useLocale(Locale.US);

      // lettura dei dati da Standard Input
      System.out.print("Introduci lati a, b, c in cm (premendo invio dopo ogni valore inserito): ");
      double a = in.nextDouble();
      double b = in.nextDouble();
      double c = in.nextDouble();

      // creazione di una istanza del triangolo
      Triangolo triangolo = new Triangolo(a, b, c);

      // emissione a Standard Output dell'elaborazione
      System.out.println(triangolo +  ": " + triangolo.info());
      System.out.printf("area = %.2f cm^2\n", triangolo.area());
      System.out.printf("h = %.2f cm", triangolo.h());
   }
}