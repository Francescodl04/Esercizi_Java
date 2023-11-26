/*
 * Francesco Di Lena
 * Esercizio 4 - Laboratorio di fondamenti di informatica
 * 21-11-2023
 * Test per la classe Stringa
*/

public class Sol5_4
{ 
    public static void main(String[] args)
    { 
        Stringa s = new Stringa("Un esercizio davvero facilissimo!");
        Stringa sa = new Stringa("UUnn ");
        Stringa sb = sa.substring(sa.indexOf(new Stringa("U")), sa.lastIndexOf(new Stringa("U")));
        Stringa s2 = new Stringa("");
        s2 = s2.concat(sb);
        s2 = s2.concat(sa.substring(2, 3));
        s2 = s2.concat(sa.substring(sa.length()-1));
        sb = new Stringa("esercizio ");

        if (sa.endsWith(new Stringa(" "))) s2 = s2.concat(sb);
        else s2 = s2.concat(sa);

        s2 = s2.concat(s.substring(s.indexOf(new Stringa("d"))));

        for(int i = 0; i< s.length(); i++) System.out.print(s.charAt(i));
        System.out.println();
        for(int i = 0; i< s2.length(); i++) System.out.print(s2.charAt(i));
        System.out.println();

        if (s.compareTo(s2) == 0) System.out.println("Collaudo effettuato con successo");
        else System.out.println("Collaudo fallito");
    }
}

