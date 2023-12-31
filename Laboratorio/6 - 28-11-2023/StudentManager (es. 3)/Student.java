/*
 * Francesco Di Lena
 * Esercizio 3 - Laboratorio di fondamenti di informatica
 * 28-11-2023
 * Classe fabbrica di oggetti
*/

public class Student
{
	private String surname;
	private double writtenTest, oralTest;
	
	public Student(String surname, double writtenTest, double oralTest)
	{
		this.surname = surname;
		this.writtenTest = writtenTest;
		this.oralTest = oralTest;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public double getWrittenTest()
	{
		return writtenTest;
	}
	
	public double getOralTest()
	{
		return oralTest;
	}

	public String toString()
	{
		return "Cognome: " + surname + "\n" + "Voto scritto: " + writtenTest + "\n" + "Voto orale: " + oralTest;
	}
}
