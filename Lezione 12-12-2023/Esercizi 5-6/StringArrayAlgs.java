/*
 * Francesco Di Lena
 * Esercizio 5 e 6 - Laboratorio di fondamenti di informatica 
 * 12-12-2023
 * Classe fabbrica di oggetti
*/

public class StringArrayAlgs
{
    // Parte esercizio 5: metodo per la ricerca binaria
    	
    public static int iterativeBinarySearch(String[] array, String searchedValue)
    {
        int from = 0, to = array.length - 1; //indici ricerca
        int arrayLength = array.length, midValuePosition;
        String midValue;

        while(from < to)
        {
            midValuePosition = (int) (arrayLength / 2) + from; //6
            midValue = array[midValuePosition]; //gamba
            
            int comparisonResult = midValue.compareTo(searchedValue); //gamba.compareTo(elevatore) > 0
            if(comparisonResult == 0) return midValuePosition;
            else if (comparisonResult < 0) 
            {
            	from = midValuePosition + 1;
            	arrayLength = to - from + 1;
            }
            else 
            {
            	to = midValuePosition;
            	arrayLength = to - from;
            }
        }
        return -1; //valore di ritorno predefinito in caso il valore cercato non sia stato trovato
    }
    
    //Parte esercizio 6: metodo per eseguire l'ordinamento

    public static String[] bubbleSort(String[] array)
    {
    	for(int i = array.length - 1; i > 0; i--)
    	{
    		for(int j = i - 1; j >= 0 ; j--)
    		{
    			if(array[i].compareTo(array[j]) < 0)
    			{
    				String temp = array[i];
    				array[i] = array[j];
    				array[j] = temp;
    			}
    		}
    	}
        return array;
    }
}
