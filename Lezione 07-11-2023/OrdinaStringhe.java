/*
  Francesco Di Lena
  Esercizio di laboratorio 03-2 - Fondamenti di informatica 
  07-11-2023
 */

import java.util.Scanner;

public class OrdinaStringhe{
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        String[] strings = new String[3];
        System.out.print("Benvenuto nel programma. ");
        for(int i = 0; i < strings.length; i++){
            System.out.printf("Inserisci la stringa n. %d: ", i + 1);
            strings[i] = console.nextLine();
        }
        
    }
}