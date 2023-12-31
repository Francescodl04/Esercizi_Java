  /*
  * Classe che applica uno sconto (discount) di 50 unita' a un 
  * importo (amount) pari a 100 milioni di miliardi di unita' (1e17)
  */

public class DiscountTester
{
   public static void main(String[] args)
   {
      final double AMOUNT = 1.0e+17;
      final int DISCOUNT = 50;

      double doubleResult = AMOUNT - DISCOUNT;      //calcolo in formato double
      long longResult = ((long) AMOUNT) - DISCOUNT; //calcolo in formato long

      // Aritmetica dei numeri in formato IEEE754 doppia precisione (double)
      System.out.println("Calcolo in formato double di  AMOUNT-DISCOUNT: ");
      System.out.println(AMOUNT + " - "+  DISCOUNT + " = " + doubleResult);
      System.out.println("!!!!!!!!!!!! Mancano due unita`!!!!!!!!!!!!!");

      // Aritmetica dei numeri interi a 64 bit (long)
      System.out.println();
      System.out.println("Calcolo in formato long di  AMOUNT-DISCOUNT: ");
      System.out.println((long)AMOUNT + " - " + DISCOUNT + " = " + longResult);
      System.out.println("........... questa volta e` giusto .........");

   }
}

/*
  Perche` il calcolo in formato double produce un errore di due unita`?
  Suggerimento: abbiamo visto che per sommare due numeri double in base due
  bisogna prima convertirli allo stesso esponente.
  - Trovate la rappresentazione in base due (mantissa ed esponente) di AMOUNT 
  - Trovate la rappresentazione in base due (mantissa ed esponente) di DISCOUNT
  - Cosa succede quando si converte DISCOUNT allo stesso esponente di AMOUNT?
  Provate a dare valori piu` alti a DISCOUNT (ad esempio 500, 5000, 50000)?
  Ci sono ancora errori nella sottrazione AMOUNT-DISCOUNT? Perche`?
*/