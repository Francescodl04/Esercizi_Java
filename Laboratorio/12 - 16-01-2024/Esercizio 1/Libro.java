/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 16-01-2024
 * Classe fabbrica di oggetti Libro
*/

public class Libro 
{
    private String titolo; 
    private int numero;

    public Libro (String t, int n)
    {
        titolo = t;
        numero= n;
    }

    public int numcopie () 
    { 
        return numero; 
    }

    public String titololib () 
    {  
        return titolo; 
    }

    public void setquantita(int q)
    { 
        numero = q; 
    }

    public String toString () 
    { 
   	    return "del libro " + titolo + " ci sono " + numero + " copie"; 
    }

}//fine Libro