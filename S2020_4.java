package cccpractice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class S2020_4
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        StringBuffer str = new StringBuffer(br.readLine());
        for (int i = 0; i < str.length(); i++)
        {
            switch (str.charAt(i))
            {
                case 'A':
                    aCount++;
                    break;
                case 'B':
                    bCount++;
                    break;
                case 'C':
                    cCount++;
                    break;
            }
        }
        int total = 0;
        int[][] counts = new int[3][3];
        List<int[][]> data = new ArrayList<>();
        Arrays.fill(counts[0], 0);
        Arrays.fill(counts[1], 0);
        Arrays.fill(counts[2], 0);
        for (int k = 0; k < aCount; k++)
        {
            counts[0][str.codePointAt(k)-65]++;
        }
        for (int k = aCount; k < aCount+bCount; k++)
        {
            counts[1][str.codePointAt(k)-65]++;
        }
        for (int k = aCount; k < aCount+cCount; k++)
        {
            counts[2][str.codePointAt(k)-65]++;
        }
        int tempBest;
        int best = Math.min(aCount - counts[0][0] + bCount - counts[1][1] - Math.min(counts[0][1],counts[1][0]),
                aCount - counts[0][0] + cCount - counts[2][2] - Math.min(counts[0][2],counts[2][0]));
        for (int k = 0; k < str.length(); k++)
        {
            counts[0][str.codePointAt(k)-65]--;
            counts[0][str.codePointAt(checkOver(k + aCount, str.length()))-65]++;
            counts[1][str.codePointAt(checkOver(k + aCount, str.length()))-65]--;
            counts[1][str.codePointAt(checkOver(k + aCount + bCount, str.length()))-65]++;
            counts[2][str.codePointAt(checkOver(k + aCount, str.length()))-65]--;
            counts[2][str.codePointAt(checkOver(k + aCount + cCount, str.length()))-65]++;
            tempBest = Math.min(aCount - counts[0][0] + bCount - counts[1][1] - Math.min(counts[0][1],counts[1][0]),
                    aCount - counts[0][0] + cCount - counts[2][2] - Math.min(counts[0][2],counts[2][0]));
            best = Math.min(tempBest, best);
            int temp = Math.max(1, (best - tempBest) / 2);
            if (temp > 1)
            {
                for (int i = 1; i < temp; i++)
                {
                    counts[0][str.codePointAt(k+i)-65]--;
                    counts[0][str.codePointAt(checkOver(k + aCount + i, str.length()))-65]++;
                    counts[1][str.codePointAt(checkOver(k + aCount + i,str.length()))-65]--;
                    counts[1][str.codePointAt(checkOver(k + aCount + bCount + i, str.length()))-65]++;
                }
                k += temp;
            }
        }
        System.out.println(best);
    }
    static int checkOver(int in, int limit)
    {
        if (in >= limit)
        {
            return in - limit;
        }
        else if (in < 0)
        {
            return in + limit;
        }
        return in;
    }
}