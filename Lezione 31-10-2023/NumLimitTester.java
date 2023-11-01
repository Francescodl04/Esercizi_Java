 /*
  * Classe che invia a standard output i limiti massimi e minimi delle
  * rappresentazioni dei tipi fondamentali di dati in Java.
  *
  */

public class NumLimitTester
{
   public static void main(String[] args)
   {
      // tipo di dati byte
      System.out.println("\ntipo byte:\nmin = " + Byte.MIN_VALUE
                                 + "\nmax = " + Byte.MAX_VALUE);
      // tipo di dati char
      // i piu` interessati a capire cosa succede in questo caso possono 
      // cercare di reperire il significato dei codici Unicode 0000 e FFFF
      // (si vedano le "code charts" su www.unicode.org)
      System.out.println("\ntipo char:\nmin = " + Character.MIN_VALUE
                        + "\nmax = " + Character.MAX_VALUE
                        + "\n  NB:\n  il carattere '\\u0000' non e' stampabile"
                        + "\n  il carattere '\\uFFFF' non e' definito!");
		    
      // tipo di dati short
      System.out.println("\ntipo short:\nmin = " + Short.MIN_VALUE
                                 + "\nmax = " + Short.MAX_VALUE);

      // tipo di dati int
      System.out.println("\ntipo int:\nmin = " + Integer.MIN_VALUE
                                 + "\nmax = " + Integer.MAX_VALUE);

      // tipo di dati long
      System.out.println("\ntipo long:\nmin = " + Long.MIN_VALUE
                                 + "\nmax = " + Long.MAX_VALUE);

      // tipo di dati float
      System.out.println("\ntipo float:\nmin = " + Float.MIN_VALUE
                                 + "\nmax = " + Float.MAX_VALUE);
		    
      // tipo di dati double
      System.out.println("\ntipo double:\nmin = " + Double.MIN_VALUE
                                 + "\nmax = " + Double.MAX_VALUE);
   }
}		    