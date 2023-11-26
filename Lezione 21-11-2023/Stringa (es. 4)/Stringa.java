/*
 * Francesco Di Lena
 * Esercizio 4 - Laboratorio di fondamenti di informatica
 * 21-11-2023
 * Classe fabbrica di oggetti
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

    public char charAt(int index)
    {
        return this.string[index];
    }

    public int compareTo(Stringa s) throws NullPointerException
    {
        if(s == null) throw new NullPointerException();

        for(int i = 0; i < this.length() && i < s.length(); i++)
        {
            if(this.charAt(i) == s.charAt(i))
            {
                if(i == this.length() - 1 && i == s.length() - 1) return 0;
                continue;
            }
            return this.charAt(i) - s.charAt(i);
        }

        return this.length() - s.length(); //valore ritornato nel caso in cui la stringa s sia di lunghezza 0
    }

    public Stringa concat(Stringa s) throws NullPointerException
    {
        if(s == null) throw new NullPointerException();

        int newSize = this.length() + s.length();
        Stringa newString = new Stringa("");

        if (newSize == this.length())
        {
            newString.string = this.string;
            return newString;
        }

        char [] tmp = new char[newSize];
        for(int i = 0; i < newSize; i++)
        {
            if(i < this.length()) tmp[i] = this.charAt(i);
            else 
            {
                for(int j = 0; j < s.length(); j++) 
                {
                    tmp[i] = s.charAt(j);
                    i++;
                }
            }
        }

        newString.string = tmp;
        return newString;
    }

    public boolean endsWith(Stringa s) throws NullPointerException
    {
        if(s == null) throw new NullPointerException();

        if(s.length() > this.length()) return false;
        int j = s.length() - 1;
        for(int i = this.length() - 1; i > this.length() - s.length() - 1; i--)
        {
            if(this.charAt(i) != s.charAt(j))
            {
                return false;
            }
            j--;
        }
        return true;
    }

    public int indexOf(Stringa s) throws NullPointerException, IndexOutOfBoundsException
    {
        if(s == null) throw new NullPointerException();
        if(s.length() > this.length()) return -1;

        int indexStart = -1;
        for(int i = 0; i < this.length(); i++)
        {
            if(this.charAt(i) == s.charAt(0))
            {
                indexStart = i;
                for(int j = 1; j < s.length(); j++)
                {
                    i++;
                    try
                    {
                        if(this.charAt(i) != s.charAt(j)) 
                        {
                            i = indexStart;
                            indexStart = -1;
                            break;
                        }
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        indexStart = -1;
                        break;
                    }
                }
                if(indexStart != -1) break;
            }
        }
        return indexStart;
    }

    public int indexOf(Stringa s, int fromIndex) throws NullPointerException, IndexOutOfBoundsException
    {
        if(s == null) throw new NullPointerException();
        if(s.length() > this.length()) return -1;

        int indexStart = -1;
        for(int i = fromIndex; i < this.length(); i++)
        {
            if(this.charAt(i) == s.charAt(0))
            {
                indexStart = i;
                for(int j = 1; j < s.length(); j++)
                {
                    i++;
                    try
                    {
                        if(this.charAt(i) != s.charAt(j)) 
                        {
                            i = indexStart;
                            indexStart = -1;
                            break;
                        }
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        indexStart = -1;
                        break;
                    }
                }
                if(indexStart != -1) break;
            }
        }
        return indexStart;
    }

    public int lastIndexOf(Stringa s) throws NullPointerException
    {
        if(s == null) throw new NullPointerException();

        if(s.length() == 0) return this.length();

        int indexStart = -1;
        for(int i = 0; i < this.length(); i++)
        {
            if(this.charAt(i) == s.charAt(0))
            {
                indexStart = i;
                for(int j = 1; j < s.length(); j++)
                {
                    i++;
                    try
                    {
                        if(this.charAt(i) != s.charAt(j)) 
                        {
                            i = indexStart;
                            indexStart = -1;
                            break;
                        }
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        indexStart = -1;
                        break;
                    }
                }
            }
        }
        return indexStart;
    }

    public int lastIndexOf(Stringa s, int fromIndex) throws NullPointerException
    {
        if(s == null) throw new NullPointerException();

        if(s.length() == 0) return this.length();

        int indexStart = -1;
        for(int i = fromIndex; i < this.length(); i++)
        {
            if(this.charAt(i) == s.charAt(0))
            {
                indexStart = i;
                for(int j = 1; j < s.length(); j++)
                {
                    i++;
                    try
                    {
                        if(this.charAt(i) != s.charAt(j)) 
                        {
                            i = indexStart;
                            indexStart = -1;
                            break;
                        }
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        indexStart = -1;
                        break;
                    }
                }
            }
        }
        return indexStart;
    }

    public int length()
    {
        return this.string.length;
    }

    Stringa substring(int beginIndex)
    {
        int size = this.length() - beginIndex;
        char [] tmp = new char[size];
        for(int i = beginIndex, j = 0; i < this.length() && j < size; i++, j++)
        {
            tmp[j] = this.charAt(i);
        }
        Stringa newSubstring = new Stringa("");
        newSubstring.string = tmp;
        return newSubstring;
    }

    Stringa substring(int beginIndex, int endIndex)
    {
        int size = endIndex - beginIndex + 1;
        char [] tmp = new char[size];
        for(int i = beginIndex, j = 0; i < endIndex && j < size; i++, j++)
        {
            tmp[j] = this.charAt(i);
        }
        Stringa newSubstring = new Stringa("");
        newSubstring.string = tmp;
        return newSubstring;
    }
}
