/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 16-01-2024
*/

import java.util.NoSuchElementException;

public class ArchivioLibri
{
    private Coppia [] archivio; 
    private int dim;

    private final int ARRAY_DIM = 1;

    public ArchivioLibri()
    {
        archivio = new Coppia[ARRAY_DIM];
        dim = 0;
    }

    public void aggiungi(String codice, String titolo, int copie)
    {
        if(dim == archivio.length) archivio = resize(archivio, archivio.length * 2);

        archivio[dim++] = new Coppia(codice, new Libro(titolo, copie));
        insertionSort(dim - 1); //metto l'elemento inserito nel posto giusto
    }

    public Libro ricerca(String codice) throws NoSuchElementException
    {
        int researchResult = binarySearch(0, dim - 1, codice);

        if(researchResult == -1) throw new NoSuchElementException();

        return archivio[researchResult].getAttributo();
    }

    public void cancella (String codice) throws NoSuchElementException
    {
        int researchResult = binarySearch(0, dim - 1, codice);

        if(researchResult == -1) throw new NoSuchElementException();

        for(int i = researchResult; i < dim - 1; i++)
        {
            archivio[i] = archivio[i + 1];
        }
        archivio[--dim] = null;
    }

    public void modifica(String codice, int quantita) throws NoSuchElementException
    {
        int researchResult = binarySearch(0, dim - 1, codice);

        if(researchResult == -1) throw new NoSuchElementException();

        archivio[researchResult].getAttributo().setquantita(quantita);
    }

    public String toString () 
    {
        String stringFormat = "";
        for(int i = 0; i < dim; i++)
        {
            stringFormat += archivio[i].toString() + "\n";
        }
        return stringFormat;
    }

    // metodi di utilità

    private Coppia[] resize(Coppia[] oldArray, int newLength) throws IllegalArgumentException
    {
        if(oldArray.length >= newLength) throw new IllegalArgumentException();

        Coppia[] newArray = new Coppia[newLength];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray; 
    }

    private void insertionSort(int index)
    {
        for(int i = index; i > 0; i--)
        {
            if(archivio[i].compareTo(archivio[i - 1]) < 0)
            {
                Coppia tmp = archivio[i];
                archivio[i] = archivio[i - 1];
                archivio[i - 1] = tmp;
            }
        }
    }

    private int binarySearch(int startIndex, int endIndex, String key)
    {
        while(startIndex <= endIndex)
        {
            int mid = (startIndex + endIndex) / 2;
            int comparisonResult = key.compareTo(archivio[mid].getChiave());
            if(comparisonResult > 0) //continuo la ricerca a destra
            {
                startIndex = mid + 1;
            }
            else if(comparisonResult < 0) //continuo la ricerca a sinistra
            {
                endIndex = mid - 1;
            }
            else //se sono uguali
            {
                return mid;
            }
        }
        return -1;
    }

    class Coppia implements Comparable <Coppia>
    {
    	private String chiave; 
        private Libro attributo;

        public Coppia(String chiave, Libro attributo)
        {
            this.chiave = chiave;
            this.attributo = attributo;
        }

        public String getChiave()
        {
            return chiave;
        }

        public Libro getAttributo()
        {
            return attributo;
        }

        public void setChiave(String chiave)
        {
            this.chiave = chiave;
        }

        public void setAttributo(Libro attributo)
        {
            this.attributo = attributo;
        }

        public int compareTo(Coppia coppiaInput)
        {
            //Prima fase: faccio un confronto sulla chiave, se sono diverse ritorna il valore del confronto della chiave
            int comparisonResult = this.getChiave().compareTo(coppiaInput.getChiave());
            if(comparisonResult != 0) return comparisonResult;
            else
            {
                //Seconda fase: faccio un confronto sul titolo del libro, se sono diversi ritorna il valore del confronto del titolo
                comparisonResult = this.getAttributo().titololib().compareTo(coppiaInput.getAttributo().titololib());
                if(comparisonResult != 0) return comparisonResult;
                else
                {
                    //Terza fase: faccio un confronto sul numero di copie e ritorna in ogni caso il valore di questo ultimo confronto
                    //Converto entrambi i valori di copie da int a integer in modo da poter usare il metodo compareTo della classe Integer
                    Integer thisNumCopie = this.getAttributo().numcopie(), coppiaInputNumCopie = coppiaInput.getAttributo().numcopie();
                    return thisNumCopie.compareTo(coppiaInputNumCopie);
                }
            }
        }

        public String toString()
        {
            return "Chiave \"" + chiave + "\": " + attributo.toString();
        }

    }//fine Coppia  

}//fine classe ArchivioLibri