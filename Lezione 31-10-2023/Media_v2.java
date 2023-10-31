
public class Media_v2{
    public static void main (String[] args){
        int[] numeri = {3,5,10,11};
        double somma = 0;
        for (int i=0; i<numeri.length; i++)
        {
            somma+=numeri[i];
        }
        double media = somma/numeri.length;
        System.out.println("La media dei numeri inseriti e': " + media);
    }
}