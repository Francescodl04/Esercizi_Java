/*
 * Francesco Di Lena
 * Esercizio 5 - Laboratorio di fondamenti di informatica
 * 14-11-2023
 * Classe fabbrica di oggetti
*/


public class Triangolo
{
    private double la /*lato AB*/, lb /*lato BC*/, lc /*lato CA*/;

    /*
        Construttore della classe Triangolo
    */
    public Triangolo (double la, double lb, double lc)
    {
        this.la = la;
        this.lb = lb;
        this.lc = lc;
    }

    /*
        restituisce informazioni sul triangolo. le informazioni sono relative
        ai lati:     equilatero,  isoscele,    scaleno.
        agli angoli: acutangolo,  rettangolo,  ottusangolo.
        Esempio: per il triangolo di lati 3, 4, 5 restituisce la stringa
        "scaleno rettangolo".
    */
    public String info()
    {
        String tipoTriangoloLati = "", tipoTriangoloAngoli = "";
	    
        // Analisi lati

        if(la == lb && lb == lc)
        {
            tipoTriangoloLati = "equilatero";
        }
        else if (la == lb | lb == lc | la == lc)
        {
            tipoTriangoloLati = "isoscele";
        }
        else
        {
            tipoTriangoloLati = "scaleno";
        }

        // Analisi angoli (con aggiunta condizione triangolo equiangolo)

        double alpha = Math.toDegrees(Math.acos((Math.pow(la, 2) - Math.pow(lb, 2) - Math.pow(lc, 2)) / ((-2) * lb * lc)));
        double beta = Math.toDegrees(Math.acos((Math.pow(lb, 2) - Math.pow(la, 2) - Math.pow(lc, 2)) / ((-2) * la * lc)));
        double gamma = Math.toDegrees(Math.acos((Math.pow(lc, 2) - Math.pow(la, 2) - Math.pow(lb, 2)) / ((-2) * la * lb)));

        if(alpha > 90 | beta > 90 | gamma > 90)
        {
            tipoTriangoloAngoli = "ottusangolo";
        }
        else if(alpha == 90 | beta == 90 | gamma == 90)
        {
            tipoTriangoloAngoli = "rettangolo";
        }
        else if(alpha == beta && beta == gamma)
        {
            tipoTriangoloAngoli = "equiangolo";
        }
        else if(alpha < 90 & beta < 90 & gamma < 90)
        {
            tipoTriangoloAngoli = "acutangolo";
        }

        return tipoTriangoloLati + " " + tipoTriangoloAngoli;
    }

    /*
        restituisce una stringa contenente una descrizione testuale dell'oggetto
        nel formato T(a, b, c)
        Esempio "T(3, 4, 5)"
    */
    public String toString ()
    {
        return "T ( " + la + ", " + lb + ", " + lc + " )";
    }

    /*
       calcola e restituisce l'area del triangolo.
       Usa la formula di Erone:
            area * area = p * (p - a) * (p - b) * (p - c)
       dove p e` il semiperimetro, ovvero p = (a + b + c) / 2
    */
    public double area()
    {
        double p = (la + lb + lc) / 2;
        return Math.sqrt(p * (p - la) * (p - lb) * (p - lc));
    }

    /*
       calcola e restituisce l'altezze del triangolo relativa al lato maggiore:
    */
    public double h()
    {
        if(la > lb && la > lc)
        {
            return area() * 2 / la;
        }
        else if(lb > la && lb > lc)
        {
            return area() * 2 / lb;
        }
        else
        {
            return area() * 2 / lc;
        }
    }

}