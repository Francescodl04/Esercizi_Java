
import java.util.Scanner;

public class LetturaInput2{
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma (v2). Inserisci un numero intero positivo da massimo cinque cifre:");
        String numero = console.next();
        String numeroFormattato = "";
        while(numero.length() > 5 || numero.length() == 0 || Integer.parseInt(numero) < 0)
        {
            System.out.println("Non hai inserito un numero, hai superato le cifre consentite oppure hai inserito un numero negativo. Riprova:");
            numero = console.next();
        }
        for(int i=0; i<5-numero.length(); i++)
        {
            if(i == 0)
            {
                numeroFormattato = numero;
            }
            numeroFormattato = "0" + numeroFormattato;
        }
        System.out.println("Ecco le singole cifre del numero inserito: ");
        for(int i=0; i<numeroFormattato.length(); i++)
        {
            System.out.print(numeroFormattato.substring(i, i+1) + " ");
        }
    }
}