/*
 * Francesco Di Lena
 * Esercizio 5 e 6 - Laboratorio di fondamenti di informatica 
 * 12-12-2023
 * Classe fabbrica di oggetti
*/

public class StringArrayAlgs
{
    public static void main(String[] args)
    {
        iterativeBinarySearch(new String[10]);
    }

    public static boolean iterativeBinarySearch(String[] array, String searchedValue)
    {
        int from = 0, to = array.length - 1, arrayLength = array.length, midValuePosition = (int) (arrayLength / 2);
        String midValue = array[midValuePosition];

        while(from < to)
        {
            midValuePosition = (int) (arrayLength / 2) + from;
            
            int comparisonResult = midValue.compareTo(searchedValue);

            if(comparisonResult == 0) return true;
            else if (comparisonResult < 0)
            {
                from = midValuePosition + 1;
                arrayLength = midValuePosition;
            }
            else 
            {
                to = midValuePosition;
                arrayLength = arrayLength - (midValuePosition + 1);
            }
        }
        return false; //valore di ritorno predefinito in caso il valore cercato non sia stato trovato
    }

    public static String[] bubbleSort(String[] array)
    {
        return null;
    }
}