/*
 * Francesco Di Lena
 * Esercizio 5 e 6 - Laboratorio di fondamenti di informatica 
 * 12-12-2023
 * Classe di test di StringArrayAlgs
*/

public class Test
{
	public static void main(String[] args)
	{
		System.out.println("Benvenuto nel test. Generero' un array di stringhe e ne eseguiro' operazioni per testarne i metodi");
		String[] stringArray1 = {"ape", "banana", "casa", "dondolo", "elevatore", "feritoia", "gamba", "hotel", "iteratore", "java", "kappa", "luna"};
		System.out.println("\nRisultati della ricerca sul primo array con relativo indice degli elementi(ordinato):");
		System.out.println("Risultato ricerca \"banana\": " + StringArrayAlgs.iterativeBinarySearch(stringArray1, "banana"));
		System.out.println("Risultato ricerca \"serratura\": " + StringArrayAlgs.iterativeBinarySearch(stringArray1, "serratura"));
		System.out.println("Risultato ricerca \"luna\": " + StringArrayAlgs.iterativeBinarySearch(stringArray1, "luna"));
		System.out.println("Risultato ricerca \"elevatore\": " + StringArrayAlgs.iterativeBinarySearch(stringArray1, "elevatore"));
		System.out.println("Risultato ricerca \"buonasera\": " + StringArrayAlgs.iterativeBinarySearch(stringArray1, "buonasera"));
		System.out.println("Risultato ricerca \"dondol\": " + StringArrayAlgs.iterativeBinarySearch(stringArray1, "dondol"));
		String[] stringArray2 = {"java", "buongiorno", "xilofono", "yogurt", "letto", "pappagallo", "gallina", "faraone", "ala"};
		stringArray2 = StringArrayAlgs.bubbleSort(stringArray2);
		System.out.println("\nRisultato ordinamento secondo array:");
		for(int i = 0; i < stringArray2.length; i++)
		{
			System.out.println(stringArray2[i]);
		}
		
	}
}
