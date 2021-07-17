package cccpractice;
import java.util.*;
import java.io.*;

public class M2020_4
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1)
        {
            System.out.println(0);
            return;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> allNums = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
        {
            allNums.add(Integer.parseInt(st.nextToken()));
        }
        long result = Long.MAX_VALUE;
        for (int i = 1; i <= n-1; i++)
        {
            if (n % i == 0)
            {
                long tempRes = 0;
                for (int k = 0; k < i; k++)
                {
                    long[] tempArr = new long[n/i];
                    for (int d = 0; d < n/i; d++)
                    {
                        tempArr[d] = allNums.get(d*i + k);
                    }
                    Arrays.sort(tempArr);
                    long temp = tempArr[n/(i*2)];
                    for (int d = 0; d < n/i; d++)
                    {
                        tempRes += Math.abs(temp - tempArr[d]);
                    }
                }
                result = Math.min(tempRes, result);
            }
        }
        System.out.println(result);
    }
}
