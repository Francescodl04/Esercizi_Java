/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica
 * 14-11-2023
*/

import java.util.Scanner;

public class Esercizio4
{
    public static void main(String[] args)
    {
        final char MALE_POSITIVE_SUFFIX = 'o', FEMALE_POSITIVE_SUFFIX = 'a';
        final String MALE_DIMINUTIVE_SUFFIX = "ino", FEMALE_DIMINUTIVE_SUFFIX = "ina", MALE_SUPERLATIVE_SUFFIX = "issimo", FEMALE_SUPERLATIVE_SUFFIX = "issima";
        Scanner console = new Scanner(System.in);
        System.out.println("Benvenuto nel programma. Inserisci un aggettivo singolare di grado positivo (terminante con \'a\' o \'o\')");
        String adjective = console.nextLine().toLowerCase();
        adjective = Character.toUpperCase(adjective.charAt(0)) + adjective.substring(1, adjective.length());
        System.out.printf("Aggettivo inserito: %s\n", adjective);
        String diminutive = "", superlative = "";
        switch(adjective.charAt(adjective.length() - 1))
        {
            case MALE_POSITIVE_SUFFIX :
                diminutive = adjective.substring(0, adjective.length() - 1) + MALE_DIMINUTIVE_SUFFIX;
                superlative = adjective.substring(0, adjective.length() - 1) + MALE_SUPERLATIVE_SUFFIX;
                break;
            case FEMALE_POSITIVE_SUFFIX:
                diminutive = adjective.substring(0, adjective.length() - 1) + FEMALE_DIMINUTIVE_SUFFIX;
                superlative = adjective.substring(0, adjective.length() - 1) + FEMALE_SUPERLATIVE_SUFFIX;
                break;
            default:
                System.out.println("Non hai inserito un aggettivo corretto.");
                System.exit(1);
                break;
        }
        System.out.printf("Forma diminutiva: %s\nSuperlativo assoluto: %s\n", diminutive, superlative);
    }
}