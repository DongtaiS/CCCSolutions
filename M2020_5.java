package cccpractice;
import java.util.*;
import java.io.*;
import java.io.IOException;

public class M2020_5
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sta = new StringTokenizer(br.readLine());
        int aLength = Integer.parseInt(sta.nextToken());
        int aCount = Integer.parseInt(sta.nextToken());

        StringTokenizer stb = new StringTokenizer(br.readLine());
        int bLength = Integer.parseInt(stb.nextToken());
        int bCount = Integer.parseInt(stb.nextToken());

        int m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> tubes = new ArrayList<>(m);
        for (int i = 0; i < m; i++)
        {
            tubes.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(tubes, Collections.reverseOrder());
        int result = 0;
        for (int i = 0; i < m; i++)
        {
            int aMax = Math.min(tubes.get(i) / aLength, aCount);
            int bMax = Math.min(tubes.get(i) / bLength, bCount);
//            if (aMax == bMax)
//            {
//                result += bMax;
//                bCount -= bMax;
//            }
//            else
//            {
                int temp = Integer.MAX_VALUE;
                int aCounter = 0;
                int bCounter = 0;
                for (int k = aMax; k >= 0; k--)
                {
                    int rem = tubes.get(i) - (aLength * k);
                    int bees = Math.min(rem / bLength, bCount);
                    if (rem - (bees * bLength) <= temp)
                    {
                        temp = rem - (bees * bLength);
                        aCounter = k;
                        bCounter = bees;
                    }
                }
                result += aCounter + bCounter;
                aCount -= aCounter;
                bCount -= bCounter;
//            }
        }
        System.out.println(result);
    }
}
