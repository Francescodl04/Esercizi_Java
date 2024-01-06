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
        LinkedList[] rows = new LinkedList[1];
        int index = 0;
        do
        {
            if(index == rows.length) rows = resize(rows, rows.length + 1);

            rows[index] = new LinkedList();
            Scanner row = new Scanner(in.nextLine());
            while(row.hasNext())
            {
                String tmp = row.next();
                rows[index].addLast(tmp);
            }
            if(index == 0)
            {
                index++;
                continue;
            }
            index = insertionSort(rows, index);
        }
        while(in.hasNextLine());
        System.out.println("\nEcco le righe ordinate secondo l'ordine crescente prestabilito:");
        for(int i = 0; i < index; i++)
        {
            System.out.printf("%d)%s\n", i + 1, rows[i]);
        }
    }

    private static int insertionSort(LinkedList[] rows, int currentRowIndex)
    {
        if(findDuplicate(rows, rows[currentRowIndex])) return currentRowIndex;

        //non faccio il ciclo superiore perché deve ordinare solamente la nuova riga inserita
        for(int j = currentRowIndex; j > 0; j--)
        {
            if(rows[j].compareTo(rows[j - 1]) < 0)
            {
                LinkedList tmp = rows[j];
                rows[j] = rows[j - 1];
                rows[j - 1] = tmp;
            }
        }
        return currentRowIndex + 1;
    }

    private static boolean findDuplicate(LinkedList[] rows, LinkedList row)
    {
        for(int i = 0; i < rows.length - 1; i++) //non considero l'ultima riga, che chiaramente sarà uguale a quella di cui si vuole accertare l'esistenza o meno del duplicato
        {
            if(rows[i].equals(row)) return true;
        }
        return false;
    }

    private static LinkedList[] resize(LinkedList[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(oldArray.length >= newLength) throw new IllegalArgumentException();

        LinkedList[] newArray = new LinkedList[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }
}