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
        String s1 = console.nextLine();
        String s2 = "";
        do{
            System.out.println("Inserisci ora la seconda stringa:");
            String s2 = console.nextLine();
        } while (s2 == "");
        for(int i = 0; i < s1.length())
    }
}