/*
    L'insieme C dei numeri complessi e' l'insieme delle coppie ordinate (x,y) 
    con x e y appartenenti a R (insieme dei numeri reali).
    Dato il numero complesso z = x + i *y,  i numeri reali x e y sono detti
    parte reale e parte immaginaria di z.
    Nei commenti che seguono, facciamo riferimento ai numeri complessi
        z = x + i*y, z1 = x1 + i*y1, z2 = x2 + i*y2
*/


// Attenzione: 
// 1) Ovviamente il file va rinominato nel file MyComplex.java
// 2) notate che, sebbene i corpi dei metodi non siano ancora  
//    stati scritti, questo codice compila correttamente perche` 
//    abbiamo inserito degli enunciati di return. 


public class MyComplex
{
    private double realPart, imagPart;

    // inizializza il numero complesso al valore 0 + i0 (zero complesso)
    public MyComplex()
    {
        realPart = 0;
        imagPart = 0;
    }

    // inizializza il numero complesso al valore re + i*im
    public MyComplex(double realPart, double imagPart)
    {
        this.realPart = realPart;
        this.imagPart = imagPart;
    }

    //Somma a questo numero complesso il numero complesso z
    //somma di due complessi: z = z1+z2 = (x1+x2) + i(y1+y2)
    public MyComplex add(MyComplex z)
    {
        MyComplex somma = new MyComplex(z.getRe() + this.realPart, z.getIm() + this.imagPart);
        return somma;
    }

    //Sottrae a questo numero complesso il numero complesso z
    //sottrazione di due complessi: z = z1-z2 = (x1-x2) +i(y1-y2)
    public MyComplex sub(MyComplex z)
    {
        MyComplex differenza = new MyComplex(this.realPart - z.getRe(), this.imagPart - z.getIm());
        return differenza;
    }

    //Moltiplica questo numero complesso per il numero complesso z
    //prodotto di due complessi: z = z1*z2 = (x1*x2 -y1*y2) + i*(x1*y2 + x2*y1)
    public MyComplex mult(MyComplex z)
    {
        MyComplex prodotto = new MyComplex((this.realPart * z.getRe() - this.imagPart * z.getIm()), (this.realPart * z.getIm() + z.getRe() * this.imagPart ));
        return prodotto;
    }

    //Divide questo numero complesso per il numero complesso z
    //divisione fra due complessi: z1/z2 = z1 *1/z2, per z2 != 0
    public MyComplex div(MyComplex z)
    {return null;  //completare
    }
          
    //Calcola il coniugato di questo numero complesso
    //coniugato del complesso z:  z^= x - i*y
    public MyComplex conj()
    {return null;  //completare
    }
          
    //Calcola l'inverso rispetto al prodotto di un numero complesso
    //inverso del complesso z: 1/z = x/(|z|*|z|) -i*y/(|z|*|z|), per z != 0
    public MyComplex inv()
    {return null;  //completare
    }

    //Calcola il modulo di questo numero complesso
    //modulo del complesso z: |z| = sqrt( x*x + y*y).
    public double mod()
    {return 0;  //completare
    }
           
    //Confronta con tolleranza due numeri complessi.
    public boolean approxEquals(MyComplex z)
    {return false;  //completare
    }

    //Crea una stringa che rappresenta questo numero complesso
    //Formato stringa: parte reale + i*parte immaginaria
    public String toString()
    {
        return this.realPart + " + i * " + this.imagPart; 
    }


    //-------- metodi di accesso ----------
          
    //Restituisce la parte complessa di un numero complesso
    public double getIm()
    {
        return imagPart;  
    }
    //Restituisce la parte reale di un numero complesso
    public double getRe()
    {
        return realPart;  
    }
}