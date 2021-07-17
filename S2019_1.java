import java.io.*;
import java.io.IOException;
import java.util.*;
public class S2019_1
{
    public static void main (String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer str = new StringBuffer(br.readLine());
        int hCount = 0;
        int vCount = 0;
        int[][] arr = { {1, 2}, {3, 4}};
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'H')
            {
                hCount++;
            }
            else if (str.charAt(i) == 'V')
            {
                vCount++;
            }
        }
        hCount = hCount % 2;
        vCount = vCount % 2;
        if (vCount == 1)
        {
            int temp = arr[0][0];
            arr[0][0] = arr[0][1];
            arr[0][1] = temp;
            temp = arr[1][0];
            arr[1][0] = arr[1][1];
            arr[1][1] = temp;
        }
        if (hCount == 1)
        {
            int temp = arr[0][0];
            arr[0][0] = arr[1][0];
            arr[1][0] = temp;
            temp = arr[0][1];
            arr[0][1] = arr[1][1];
            arr[1][1] = temp;
        }
        System.out.println(arr[0][0] + " " + arr[0][1]);
        System.out.println(arr[1][0] + " " + arr[1][1]);
    }
}
