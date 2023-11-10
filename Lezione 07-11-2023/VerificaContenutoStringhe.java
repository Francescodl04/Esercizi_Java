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
        while(string2 == ""){
            System.out.println("Non hai inserito una stringa valida. Inserisci di nuovo la seconda stringa:");
            string2 = console.nextLine();
        } 
        int startIndex = 0, endIndex = 0;
        boolean substringCheck = false;
        for(int i = 0; i < string1.length(); i++){
            if(i > string2.length()){
                break;
            }
            for(int j = 0; j < string2.length(); j++){
                if(string2.charAt(j) == string1.charAt(i)){
                    if(substringCheck == false){
                        startIndex = i;
                        endIndex = i;
                        substringCheck = true;
                    }
                    else{
                        endIndex++;
                    }
                }
                else{
                    substringCheck == false;
                    break;
                }
            }
        }
        if(substringCheck == false){
            System.out.println("La seconda stringa NON e' contenuta nella prima.");
            System.exit(1);
        }
        System.out.printf("La seconda stringa e' contenuta nella prima dalla posizione %d alla %d.", startIndex, endIndex);        
    }
}