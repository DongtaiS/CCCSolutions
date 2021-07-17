package cccpractice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
public class S2020_3
{
    static String n;
    static String h;
    static Set<Long> perms;
    static int[] letters = new int[26];
    static long CONST = 101;

    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine();
        h = br.readLine();
        br.close();
        for (int i = 0; i < n.length()-2; i++)
        {
            CONST = CONST * 101;
        }
        perms = new HashSet<>(h.length()/2);
        if (n.length() > h.length())
        {
            System.out.println(0);
        }
        else
        {
            Arrays.fill(letters, 0);
            for (int i = 0; i < n.length(); i++)
            {
                letters[n.charAt(i) - 97]++;
            }
            int[] tempArr = new int[26];
            Arrays.fill(tempArr, 0);
            for (int i = 0; i < n.length(); i++)
            {
                tempArr[h.charAt(i) - 97]++;
            }

            long hash = hash(h.substring(0, n.length()));

            if (Arrays.equals(tempArr, letters))
            {
                perms.add(hash);
            }

            for (int i = n.length(); i < h.length(); i++)
            {
                tempArr[h.charAt(i - n.length()) - 97]--;
                tempArr[h.charAt(i) - 97]++;

                hash = reHash(hash, h.codePointAt(i-n.length()), h.codePointAt(i));
                
                if (Arrays.equals(tempArr, letters))
                {
                    perms.add(hash);
                }
            }
            System.out.println(perms.size());
        }
    }

    static long hash(String in)
    {
        long result = 0;
        for (int i = 0; i < in.length(); i++)
        {
            result = result * 101 + in.codePointAt(i);
        }
        return result;
    }

    static long reHash(long old, int out, int in)
    {
        old -= CONST * out;
        old = old * 101 + in;
        return old;
    }
}
