package cccpractice;
import java.util.*;
import java.io.*;
public class S2020_2
{
    public static Map<Integer, List<int[]>> grid;
    public static int m = 0;
    public static int n = 0;
    static boolean isPossible = false;
    static boolean[][] visited;
    static LinkedList<Integer> queue = new LinkedList<>();

    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        visited = new boolean[m][n];
        grid = new HashMap<>(m * n);
        for (int r = 0; r < m; r++)
        {
            Arrays.fill(visited[r], false);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++)
            {
                int temp = Integer.parseInt(st.nextToken());
                if (!grid.containsKey(temp))
                {
                    grid.put(temp, new ArrayList<>());
                }
                grid.get(temp).add(new int[]{r, c});
            }
        }
        if (grid.containsKey(m * n))
        {
            queue.add(m*n);
            check();
        }
        if (isPossible)
        {
            System.out.println("yes");
        }
        else
        {
            System.out.println("no");
        }
    }
    static void check()
    {
        while (queue.size() > 0)
        {
            if (grid.containsKey(queue.getFirst()))
            {
                for (int i = 0; i < grid.get(queue.getFirst()).size(); i++)
                {
                    int r = grid.get(queue.getFirst()).get(i)[0];
                    int c = grid.get(queue.getFirst()).get(i)[1];
                    if (!visited[r][c])
                    {
                        if (r == 0 && c == 0)
                        {
                            isPossible = true;
                            return;
                        }
                        else
                        {
                            visited[r][c] = true;
                            queue.add((r + 1) * (c + 1));
                        }
                    }
                }
            }
            queue.removeFirst();
        }
    }
}
