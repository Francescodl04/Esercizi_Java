/*
* Francesco Di Lena
* Esercizio in aula - Fondamenti di informatica
* 13-11-2023
*/


public class Product
{
    private String name;
    private double price;

    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public void reducePrice(double rate)
    {
        price -= price * (rate / 100);
    }

}