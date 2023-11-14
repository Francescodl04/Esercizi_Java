/*
 * Francesco Di Lena
 * Esercizio 1 - Laboratorio di fondamenti di informatica
 * 14-11-2023
 * Classe di test per la classe BankAccount
*/

import java.util.Scanner;

public class BankAccountTest
{
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		BankAccount account = new BankAccount(100);
		System.out.println("Benvenuto nel programma. Comandi disponibili:\n- q per uscire\n- b per mostrare il saldo del conto\n- d per effettuare un deposito (inserire parametro)\n- w per effettuare un prelievo (inserire parametro)\n- a per aggiungere un tasso di interesse (inserire parametro)");
		do
		{
			System.out.print("Inserisci il comando che desideri utilizzare:");
			String input = console.nextLine().toLowerCase();
			if(input.length() == 1 || input.charAt(1) == ' ')
			{
				switch(input.charAt(0))
				{
					case 'q':
						System.exit(0);
						break;
					case 'b':
						System.out.printf("Il saldo del conto e' %.2f\n", account.getBalance());
						break;
					case 'd':
						double amount = Double.parseDouble(input.substring(2, input.length()));
						if(amount < 0)
						{
							System.out.println("Hai inserito un numero minore di 0 (non consetito)");
							continue;
						}
						if(account.deposit(amount) == true)
						{
							System.out.println("Il deposito e' stato effettuato con successo.");
						}
						else
						{
							System.out.println("Il deposito non e' riuscito, riprovare in un secondo momento.");
						}
						break;
					case 'w':
						amount = Double.parseDouble(input.substring(2, input.length()));
						if(amount < 0)
						{
							System.out.println("Hai inserito un numero minore di 0 (non consetito)");
							continue;
						}
						if(account.withdraw(amount) == true)
						{
							System.out.println("Il prelievo e' stato effettuato con successo.");
						}
						else
						{
							System.out.println("Il prelievo non e' riuscito, riprovare in un secondo momento.");
						}
						break;
					case 'a':
						double interest = Double.parseDouble(input.substring(2, input.length()));
						if(interest < 0)
						{
							System.out.println("Hai inserito un numero minore di 0 (non consetito)");
							continue;
						}
						if(account.addInterest(interest) == true)
						{
							System.out.println("L'accredito dell'interesse e' stato effettuato con successo.");
						}
						else
						{
							System.out.println("L'accredito dell'interesse non e' riuscito, riprovare in un secondo momento.");
						}
						break;
					default:
						System.out.printf("Non hai inserito un comando corretto, riprova...");
						continue;
				}
			}
			else
			{
				System.out.println("Non hai inserito un comando corretto, riprova...");
				continue;
				
			}
		}
		while(true);
	}
}
