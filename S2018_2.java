import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2018_2
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long grid[][] = new long[n][n];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int d = 0; d < n; d++)
            {
                grid[i][d] = Long.parseLong(st.nextToken());
            }
        }
        boolean horizontal = false, vertical = false;
        for (int i = 1; i < n; i++)
        {
            if (grid[0][i-1] > grid[0][i])
            {
                vertical = true;
                break;
            }
        }
        for (int i = 1; i < n; i++)
        {
            if (grid[i-1][0] > grid[i][0])
            {
                horizontal = true;
                break;
            }
        }
        if (horizontal && vertical)
        {
            for (int i = 0; i < n; i++)
            {
                for (int d = 0; d < n; d++)
                {
                    System.out.print(grid[n-i-1][n-d-1] + " ");
                }
                System.out.println();
            }
        }
        else if (horizontal)
        {
            for (int i = 0; i < n; i++)
            {
                for (int d = 0; d < n; d++)
                {
                    System.out.print(grid[n-d-1][i] + " ");
                }
                System.out.println();
            }
        }
        else if (vertical)
        {
            for (int i = 0; i < n; i++)
            {
                for (int d = 0; d < n; d++)
                {
                    System.out.print(grid[d][n-i-1] + " ");
                }
                System.out.println();
            }
        }
        else
        {
            for (int i = 0; i < n; i++)
            {
                for (int d = 0; d < n; d++)
                {
                    System.out.print(grid[i][d] + " ");
                }
                System.out.println();
            }
        }
    }
}
