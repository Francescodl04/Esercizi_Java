/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 14-11-2023
 * Classe fabbrica di oggetti
*/

public class BankAccount
{  
   private double balance;
   
   public BankAccount()
   {
   	balance = 0;
   }
   
   public BankAccount(double initialBalance)
   {
   	balance = initialBalance;
   }
   
   public double getBalance()
   {  
   	return balance;
   }
   
   public boolean deposit(double amount)
   {  
   	if (amount > 0)
      	{  
      		balance += amount;
         	return true;
      	} 
      	return false;
   }
   
   public boolean withdraw(double amount)
   {  
   	if (amount > 0 && amount <= getBalance())
      	{  
      		balance -= amount;
         	return true;
      	}
      	return false;
   }
   
   public boolean addInterest(double interest)
   {
   	if(balance > 0)
   	{
   		interest /= 100;
   		balance += balance * interest;
   		return true;
   	}
   	return false;
   }
}

