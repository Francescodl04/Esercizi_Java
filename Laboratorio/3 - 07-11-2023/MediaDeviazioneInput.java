/*
  Francesco Di Lena
  Esercizio di laboratorio 03-6 - Fondamenti di informatica 
  07-11-2023
*/

import java.util.Scanner;
import java.util.Locale;

public class MediaDeviazioneInput{
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        console = console.useLocale(Locale.ITALY);
        int nValues = 0 /*numero di valori inseriti*/;
        double sum = 0 /*somma*/, squareSum = 0 /*somma dei quadrati*/;
        System.out.print("Benvenuto nel programma. ");
        do{
            System.out.printf("Inserisci il %d' numero (inserire q per terminare):\n", nValues + 1);
            String inputValue = console.next();
            if(inputValue.equals("q")){
                break;
            }
            //In entrambe le prossime istruzioni se il numero decimale viene inserito nella notazione italiana, viene sostituito con quella inglese, altrimenti darebbe errore.
            sum += Double.parseDouble(inputValue.replace(',', '.')); 
            squareSum += Math.pow(Double.parseDouble(inputValue.replace(',', '.')), 2);
            nValues++;
        } while (true);
        console.close();
        if(nValues == 0){
            System.out.println("\nNon hai inserito alcun numero, percio' nessuna operazione puo' essere eseguita.");
            System.exit(1);
        }
        System.out.printf("\nIl valore medio della sequenza di numeri inseriti e': %.2f\n", sum / nValues);
        if(nValues == 1){
            System.out.println("La deviazione standard vale ZERO perche' un solo valore numerico e' stato inserito.");
        }
        else{
            double standardDeviation = Math.sqrt((squareSum - sum * sum / nValues) / (nValues - 1)); //deviazione standard
            System.out.printf("La deviazione standard dei numeri inseriti e': %.2f\n", standardDeviation);
        }
    }
}