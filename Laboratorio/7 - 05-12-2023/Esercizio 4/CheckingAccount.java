/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica
 * 05-12-2023
 * Sottoclasse CheckingAccount fabbrica di oggetti
 * Caratteristiche: contocorrente che non matura interessi, consente 3 prelievi senza 
 *                  commissione e addebita una commissione (FEE) di 2 euro per ogni 
 *                  ulteriore prelievo.
*/

public class CheckingAccount extends BankAccount
{
    public CheckingAccount()
    {
        super();               // costruttore della superclasse
        transactionCount = 0;  // azzera conteggio transaz.
    }

    public CheckingAccount(double initialBalance)
    {
        super(initialBalance); // costruttore della superclasse
        transactionCount = 0;  // azzera conteggio transaz.
    }

    public CheckingAccount(double initialBalance, int initialTransCount)
    {
        super(initialBalance); // costruttore della superclasse
        transactionCount = initialTransCount; // transazioni iniziali
    }

    public void deposit(double amount)  //SOVRASCRITTO
    {
        super.deposit(amount); // aggiungi amount al saldo
        transactionCount++;        
    }

    public void withdraw(double amount) //SOVRASCRITTO
    {
        super.withdraw(amount); // sottrai amount dal saldo
        transactionCount++;
    } 

    //applica le commissioni sulle operazioni in eccedenza
    public void deductFees()  //NUOVO METODO
    {
        if (transactionCount > FREE_TRANSACTIONS)
        {
            double fees = TRANSACTION_FEE *
                            (transactionCount - FREE_TRANSACTIONS);
            super.withdraw(fees);
        }
        transactionCount = 0;
    }


    // ------- metodi di accesso --------

    public int getTransactionCount()
    {
        return transactionCount;
    }



    // ------ metodi di Object sovrascritti ---------
    // ........... toString, equals .................

    public String toString()
    {
        return "balance: " + super.getBalance() + " transactionCount: " + transactionCount;
    }

    public boolean equals(CheckingAccount c)
    {
        return (super.getBalance() == c.getBalance() && this.transactionCount == c.transactionCount);
    }

    //-------- nuovi campi di esemplare e variabili statiche ----------------
    private int transactionCount;
    public static final int FREE_TRANSACTIONS = 3;
    public static final double TRANSACTION_FEE = 2.0;

}