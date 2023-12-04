/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 05-12-2023
*/

public class Esercizio1
{
    public static void main(String[] args)
    {
        if(args.length == 1) 
        {
            if(args[0].length() != 0) System.out.printf("Ecco la stringa invertita: %s\n", stringReverse(args[0]));
        }    
    }

    public static String stringReverse(String string) throws IllegalArgumentException
    {
        if(string == null) throw new IllegalArgumentException();
        
        if(string.length() == 1) return string;

        return string.charAt(string.length() - 1) + stringReverse(string.substring(0, string.length() - 1));
    }
}