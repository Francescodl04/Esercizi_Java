
/*
* Francesco Di Lena
* A.A. 2023-20224 - Fondamenti di informatica
* Esercizio di laboratorio 02-1
*/

public class Operazioni{
    public static void main(String[] args){
        double n1, n2, n3, m1, m2, m3;
        n1 = 5;
        n2 = -9;
        n3 = 4;
        m1=n1*n2/n3;
        m2=n2/n3*n1;
        m3=n1/n3*n2;
        System.out.println("m1 = " + m1 + ", m2 = " + m2 + ", m3 = " + m3);
    }
}