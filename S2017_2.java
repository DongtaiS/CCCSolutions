
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2017_2
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int mid = n/2;
        if (n % 2 == 1)
        {
            mid++;
        }
        int[] lows = Arrays.copyOfRange(arr, 0, mid);
        int[] highs = Arrays.copyOfRange(arr, mid, arr.length);
        for (int i = 0; i < n; i++)
        {
            if (i % 2 == 0)
            {
                System.out.print(lows[mid-1-i/2] + " ");
            }
            else
            {
                System.out.print(highs[i/2] + " ");
            }
        }
    }
}
