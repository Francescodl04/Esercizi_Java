/*
 * Francesco Di Lena
 * Esercizio 1 in aula - Fondamenti di informatica 
 * 13-11-2023
*/

import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        System.out.print("Benvenuto nel programma.");
        
        System.out.println("Inserisci il nome del prodotto primo prodotto:");
        String nome = console.next();
        System.out.println("Inserisci il prezzo del primo prodotto:");
        double prezzo = console.nextDouble();
        Product prodotto1 = new Product(nome, prezzo);
        
        System.out.println("Inserisci il nome del prodotto secondo prodotto:");
        nome = console.next();
        System.out.println("Inserisci il prezzo del secondo prodotto:");
        prezzo = console.nextDouble();
        Product prodotto2 = new Product(nome, prezzo);

        if(prodotto1.getPrice() > prodotto2.getPrice())
        {
            System.out.printf("Primo prodotto: %s, %.2f\n", prodotto1.getName(), prodotto1.getPrice());
            System.out.printf("Secondo prodotto: %s, %.2f\n", prodotto2.getName(), prodotto2.getPrice());
            
        }
        else
        {
            System.out.printf("Secondo prodotto: %s, %.2f\n", prodotto2.getName(), prodotto2.getPrice());
            System.out.printf("Primo prodotto: %s, %.2f\n", prodotto1.getName(), prodotto1.getPrice());
            
        }
    }
}