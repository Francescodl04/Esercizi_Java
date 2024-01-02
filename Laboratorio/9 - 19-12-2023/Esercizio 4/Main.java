/*
 * Francesco Di Lena
 * Esercizio 4 - laboratorio di fondamenti di informatica
 * 19-12-2023
*/

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        LinkedList rows = new LinkedList[100];
        int nRows = 0;
        do
        {
            rows[nRows] = new LinkedList();
            Scanner row = new Scanner(in.nextLine());
            while(row.hasNext())
            {
                String tmp = row.next();
                rows[nRows].addLast(tmp);
            }
            if(nRows == 0)
            {
                nRows++;
                continue;
            }
            nRows = insertionSort(rows, nRows);
        }
        while(in.hasNextLine());
        System.out.println("\nEcco le righe ordinate secondo l'ordine crescente prestabilito:");
        for(int i = 0; i < nRows; i++)
        {
            System.out.printf("%d)%s\n", i + 1, rows[i]);
        }
    }

    public static int insertionSort(LinkedList[] rows, int currentRowIndex)
    {
        for(int i = currentRowIndex; i > 0; i--)
        {
            int comparisonResult = rows[currentRowIndex - 1].compareTo(rows[currentRowIndex]);
            if(comparisonResult == 0) return currentRowIndex;
            else if(comparisonResult > 0)
            {
                LinkedList tmp = rows[currentRowIndex];
                rows[currentRowIndex] = rows[currentRowIndex - 1];
                rows[currentRowIndex - 1] = tmp;
            }
        }
        return currentRowIndex + 1;
    }
}