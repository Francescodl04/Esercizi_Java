/*
  Francesco Di Lena
  Esercizio di laboratorio 03-1-6 (versione 2 in ordine decrescente con for) - Fondamenti di informatica 
  07-11-2023
 */

public class Esercizio6_v2{
    public static void main (String[] args){
        System.out.println("Benvenuto nel programma (v2). Ora mostrero' i multipli di 5 da 100 a 10:");
        int number = 100;
        for(int i = 0; number > 10; i++){
            number = 100 - 5 * i;
            System.out.println(number);
        }
    }
}