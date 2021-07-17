import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S2018_1
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());
        long[] villages = new long[n];
        for (int i = 0; i < n; i++)
        {
            int temp = Integer.parseInt(br.readLine());
            villages[i] = temp;
        }
        Arrays.sort(villages);

        double lastMid = 0, nextMid = 0;
        double result = Double.MAX_VALUE;
        for (int i = 1; i < n-1; i++)
        {
            if (i == 1)
            {
                lastMid = (villages[i-1] + villages[i]) / (double)2;
            }
            else
            {
                lastMid = nextMid;
            }
            nextMid = (villages[i] + villages[i+1]) / (double)2;
            result = Math.min(result, Math.abs(lastMid - nextMid));
        }
        System.out.printf("%1.1f", result);
    }
}
