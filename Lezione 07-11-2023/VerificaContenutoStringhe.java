/*
  Francesco Di Lena
  Esercizio di laboratorio 03-4 - Fondamenti di informatica 
  07-11-2023
*/

import java.util.Scanner;

public class VerificaContenutoStringhe{
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma. Inserisci la prima stringa:");
        String string1 = console.nextLine();
        System.out.println("Inserisci ora la seconda stringa:");
        String string2 = console.nextLine();
        while(string2 == "" || string2.length() > string1.length()){
            System.out.println("Non hai inserito una stringa valida. Inserisci di nuovo la seconda stringa:");
            string2 = console.nextLine();
        } 
        console.close();
        // Inizializzo i valori che mi indicheranno gli indici che comprendono la seconda stringa all'interno della prima stringa.
        int startIndex = 0, endIndex = 0;
        // Inizializzo la variabile che mi permetterà di capire se sono riuscito a individuare che la seconda stringa sia una sottostringa della prima stringa.
        boolean substringCheck = false;
        for(int i = 0; i < string1.length(); i++){
            for(int j = 0; j < string2.length(); j++){
                if(string2.charAt(j) == string1.charAt(i)){
                    if(substringCheck == false){
                        substringCheck = true;
                        startIndex = i;
                        endIndex = i;
                    }
                    else{
                        endIndex++;
                    }
                    i++; //Incremento la i per poter proseguire il confronto con i caratteri successivi della prima stringa.
                }
                else{
                    /* 
                     * Realizzo il seguente codice perché da consegna non è possibile l'uso del metodo string.substring()
                     * che mi permette di confrontare se il risultato che ho ottenuto è effettivamente la seconda stringa.
                    */
                   if(substringCheck == true){
                        String tmp = ""; //Variabile temporanea per permettere il confronto.
                        for(int k = startIndex; k <= endIndex; k++){
                            tmp += string1.charAt(k);
                        }
                        if(tmp.equals(string2) == false){
                            substringCheck = false;
                            /* 
                             * Diminuisco la variabile i perché nel caso in cui la stringa che esce non sia uguale alla seconda stringa, allora riporto l'analisi del 
                             * for iniziale all'indice corretto.
                            */
                            i--; 
                        }
                    }
                    break;
                }
            }
        }
        if(substringCheck == false){
            System.out.println("\nRISULTATO: la seconda stringa NON e' contenuta nella prima.");
            System.exit(1);
        }
        System.out.println("\n *** NOTA BENE: LA PRIMA POSIZIONE CHE SI CONSIDERA DI UNA STRINGA E' LO ZERO! ***");
        System.out.printf(" RISULTATO: la seconda stringa e' contenuta nella prima dalla posizione %d alla %d.\n", startIndex, endIndex);        
    }
}