
import java.util.Scanner;

public class LetturaInput1{

    public static void main (String[] args){

        final int MAX_CIFRE = 5;

        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma (v1). Inserisci un numero intero positivo da massimo cinque cifre:");
        int numero = console.nextInt();
        while (numero < 0 || numero > 99999 )
        {
            System.out.println("Hai inserito un numero negativo oppure con un numero di cifre maggiore di cinque. Riprova:");
            numero = console.nextInt();
        }
        System.out.println("Queste sono le singole cifre del numero inserito:");
        int quoziente = numero;
        String risultato = "";
        for(int i=0; i < MAX_CIFRE; i++)
        {
            risultato = quoziente % 10 + " " + risultato;
            quoziente /= 10;
        }
        System.out.print(risultato);
    }
}