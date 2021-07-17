import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S2017_3
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] counts = new int[2003];
        int[] sums = new int[4003];
        Arrays.fill(counts, 0);
        Arrays.fill(sums, 0);
        for (int i = 0; i < n; i++)
        {
            int temp = Integer.parseInt(st.nextToken());
            counts[temp]++;
        }
        for (int i = 1; i <= 2000; i++)
        {
            if (counts[i] > 0)
            {
                for (int k = i; k <= 2000; k++)
                {
                    if (i == k)
                    {
                        sums[i + k] += counts[i]/2;
                    }
                    else if (counts[k] > 0)
                    {
                        sums[i + k] += Math.min(counts[i], counts[k]);
                    }
                }
            }
        }
        int len = 0;
        int num = 0;
        for (int i = 0; i < 4000; i++)
        {
            if (sums[i] > len)
            {
                len = sums[i];
                num = 1;
            }
            else if (sums[i] == len)
            {
                num++;
            }
        }
        System.out.println(len + " " + num);
    }
}
