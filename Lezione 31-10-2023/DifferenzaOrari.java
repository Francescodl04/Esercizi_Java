
/*
* Francesco Di Lena
* A.A. 2023-2024 - Fondamenti di informatica
* Esercizio di laboratorio 02-5
*/

import java.util.Scanner;

public class DifferenzaOrari{
    public static void main (String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma (v1). Inserisci il primo orario nel formato 24 ore con ore e minuti senza spazi (es. 2350):");
        int orario1 = console.nextInt();
        System.out.println("Inserisci ora il secondo orario nello stesso formato del precedente:");
        int orario2 = console.nextInt();
        int ora1 = orario1/100, ora2 = orario2/100, minuti1 = orario1%100, minuti2 = orario2%100;
        System.out.println("Il primo orario e' " + FormattaOra(ora1, minuti1) + "\nIl secondo orario e' " + FormattaOra(ora2, minuti2));
        int oreTrascorse = 0, minutiTrascorsi = ContaMinuti(minuti1, minuti2);
        //Scelgo se eseguire il calcolo di ore in due giorni diversi (primo caso), oppure due orari nello stesso giorno (tutti gli altri casi)
        if(ora1 > ora2){            
            oreTrascorse = ((24 * 60 - (ora1 * 60 + minuti1)) + (ora2 * 60 + minuti2)) / 60;
        }
        else{
            oreTrascorse = ((ora2 * 60 + minuti2) - (ora1 * 60 + minuti1)) / 60;
        }
        System.out.println("\nLa differenza tra i due orari e': " + oreTrascorse + " ore e " + minutiTrascorsi + " minuti.");

        console.close();
    }

    /*
    * La funzione seguente permette di calcolare la differenza dei minuti passati come argomento
    */

    public static int ContaMinuti(int minuti1, int minuti2){
        int minutiTrascorsi;
        if(minuti1 > minuti2){
            minutiTrascorsi = 60 - minuti1 + minuti2;
        }
        else{
            minutiTrascorsi = minuti2 - minuti1;
        }
        return minutiTrascorsi;
    }

    /*
    * La funzione seguente permette di scrivere l'ora nel formato hh:mm
    */

    public static String FormattaOra(int ora, int minuto){
        String oraString = "" + ora;
        String minutoString = "" + minuto;
        if(oraString.length() < 2)
        {
            oraString = "0" + oraString;
        }
        if(minutoString.length() < 2)
        {
            minutoString = "0" + minutoString;
        }
        return oraString + ":" + minutoString;
    }
}