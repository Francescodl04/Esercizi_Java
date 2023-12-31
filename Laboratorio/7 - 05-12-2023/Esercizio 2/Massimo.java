/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 05-12-2023
*/

import java.util.Scanner;

public class Massimo
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma. Quanti numeri vuoi inserire?");
        int n = Integer.parseInt(console.nextLine());
        double[] numbers = new double[n];
        for(int i = 0; i < n; i++)
        {
            System.out.printf("Inserisci il %d' valore:\n", i + 1);
            numbers[i] = Double.parseDouble(console.nextLine());
        }
        System.out.printf("Il massimo dei numeri inseriti e' %.2f\n", massimo(numbers));
    }

    public static double massimo(double[] numbers)
    {
        if(numbers == null || numbers.length == 0) throw new IllegalArgumentException();

        if(numbers.length == 1) return numbers[0];

        double[] reducedNumbers = new double[numbers.length - 1];
        System.arraycopy(numbers, 0, reducedNumbers, 0, reducedNumbers.length);
        return max(massimo(reducedNumbers), numbers[numbers.length -1]);
    }

    public static double max (double x, double y)
    {
        if(x > y) return x;
        
        return y;
    }
}