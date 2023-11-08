/*
  Francesco Di Lena
  Esercizio di laboratorio 03-1-6 (versione 1 in ordine crescente con while) - Fondamenti di informatica 
  07-11-2023
 */

public class Esercizio6_v1{
    public static void main (String[] args){
        System.out.println("Benvenuto nel programma (v1). Ora mostrero' i multipli di 5 da 10 a 100:");
        int number = 0, i = 2;
        while(number < 100){
            number = 5 * i;
            System.out.println(number);
            i++;
        }
    }
}