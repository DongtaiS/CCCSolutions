import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2017_1
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] swifts = new int[n];
        int[] sems = new int[n];
        StringTokenizer s1 = new StringTokenizer(br.readLine());
        StringTokenizer s2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
        {
            swifts[i] = Integer.parseInt(s1.nextToken());
            sems[i] = Integer.parseInt(s2.nextToken());
        }
        int c1 = 0, c2 = 0, index = 0;
        for (int i = 0; i < n; i++)
        {
            c1 += swifts[i];
            c2 += sems[i];
            if (c1 == c2)
            {
                index = i+1;
            }
        }
        System.out.println(index);
    }
}
