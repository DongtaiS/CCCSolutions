package cccpractice;
import java.util.*;
import java.io.*;

public class M2020_2
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String arr = br.readLine();
        StringBuffer result = new StringBuffer(n);
        result.append(arr.charAt(0));
        for (int i = 1; i < n; i++)
        {
            int counter = 1;
            for (int d = i+1; d < n; d++)
            {
                if (arr.charAt(i) == arr.charAt(d))
                {
                    counter++;
                }
                else
                {
                    break;
                }
            }
            char temp = arr.charAt(i);
            String add = Character.toString(temp).repeat(counter);
            if (result.codePointAt(0) >= temp)
            {
                result.insert(0, add);
            }
            else
            {
                result.append(add);
            }
            i += counter-1;
        }
        System.out.println(result);
    }
}
