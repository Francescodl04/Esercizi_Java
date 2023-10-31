
import java.util.Scanner;

public class LetturaInput1{
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma. Inserisci un numero intero positivo da massimo cinque cifre:");
        String numero = "";
        do{
            try{
                numero = console.next();
            }
            catch(NumberFormatException exception){
                System.out.println("Non hai inserito un numero oppure hai superato le cifre consentite. Riprova:");
            }
        } while(numero.length()>5 || numero.length()==0 || Integer.parseInt(numero) < 0);
        System.out.println("Ecco le singole cifre del numero inserito: ");
        for(int i=0; i<numero.length(); i++)
        {
            System.out.print(numero.substring(i, i+1) + " ");
        }
    }
}