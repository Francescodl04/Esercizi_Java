/*
  Francesco Di Lena
  Esercizio di laboratorio 03-2 - Fondamenti di informatica 
  07-11-2023
 */

import java.util.Scanner;

public class OrdinaStringhe{
    public static void main(String[] args){
        final int STRINGS_ARRAY_LENGTH = 3;
        Scanner console = new Scanner(System.in);
        String[] strings = new String[STRINGS_ARRAY_LENGTH]; 
        System.out.print("Benvenuto nel programma. ");
        for(int i = 0; i < strings.length; i++){
            System.out.printf("Inserisci la stringa n. %d: ", i + 1);
            strings[i] = console.nextLine();
        }
        for(int i = 0; i < strings.length; i++){
            for(int j = i + 1; j < strings.length; j++){
                if(strings[i].compareTo(strings[j]) > 0){
                    String tmp = strings[i];
                    strings[i] = strings[j];
                    strings[j] = tmp;
                }
            }
        }
        System.out.println("Questo e' l'ordine lessicografico corretto delle stringhe inserite:");
        for(int i = 0; i < strings.length; i++){
            System.out.printf("%d) %s\n", i + 1, strings[i]);
        }
        console.close();
    }
}