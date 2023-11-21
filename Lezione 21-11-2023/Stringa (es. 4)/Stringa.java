/*
 * Francesco Di Lena
 * Esercizio 4 - Laboratorio di fondamenti di informatica
 * 21-11-2023
*/

public class Stringa
{
    private char[] string;

	public Stringa(String stringObj)
    {
        this.string = new char[stringObj.length()];
        for(int i = 0; i < stringObj.length(); i++)
        {
            this.string[i] = stringObj.charAt(i);
        }
    }

    public char[] getCharArray()
    {
        return string;
    }

    public char charAt(int index)
    {
        return this.string[index];
    }

    public int compareTo(Stringa s)
    {
        for(int i = 0; i < this.length(); i++)
        {
            for(int j = 0; j < s.length(); j++)
            {
                if(this.string[i] == s.getCharArray()[j])
                {

                }
            }
        }
    }

    public Stringa concat(Stringa s)
    {
        int size = this.length() + s.length();
        char [] tmp = new char[size];
        
    }

    public boolean endsWith(Stringa s)
    {

    }

    public int indexOf(Stringa s)
    {
        int indexStart = 0, indexEnd = 0;
        boolean hasIndexOf = false;
        if(s.length() < this.length())
        {
            return -1;
        }
        for(int i = 0; i < this.length; i++)
        {
            for(int j = 0; j < s.length; j++)
            {
                if(this.string[i] == s.getCharArray()[j])
                {
                    hasIndexOf = true;
                    indexStart = i;
                    i++;
                }
                else
                {
                    hasIndexOf = false;
                    break;
                }
            }
            if(hasIndexOf)
            {
                
            }
        }
        return indexStart;
    }

    public int indexOf(Stringa s, int fromIndex)
    {
        
    }

    public int lastIndexOf(Stringa s)
    {

    }

    public int lastIndexOf(Stringa s, int fromIndex)
    {

    }

    public int length()
    {
        return this.string.length;
    }

    Stringa substring(int beginIndex)
    {
        int size = this.string.length - beginIndex;
        char [] tmp = new char[size];
        int j = 0;
        for(int i = beginIndex; i < size; i++)
        {
            tmp[j] = this.string[i];
        }
        return tmp;
    }

    Stringa substring(int beginIndex, int endIndex)
    {
        int size = endIndex - beginIndex;
        char [] tmp = new char[size];
        int j = 0;
        for(int i = beginIndex; i < endIndex; i++)
        {
            tmp[j] = this.string[i];
        }
        return tmp;
    }
}
