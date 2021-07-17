import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    public static char[][] grid;
    public static int[][] results;
    public static HashSet<Integer> hashSet;
    public static HashSet<Integer> camera = new HashSet<>();
    public static TreeMap<Integer, ArrayDeque<QueueItem>> queue;
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        grid = new char[n][m];
        hashSet = new HashSet<>(n*m);
        int startX = 0, startY = 0;
        results = new int[n][m];
        for (int i = 0; i < results.length; i++)
        {
            Arrays.fill(results[i], -1);
        }
        ArrayList<int[]> empties = new ArrayList<>(n*m);
        for (int i = 0; i < n; i++)
        {
            StringBuffer str = new StringBuffer(br.readLine());
            for (int d = 0; d < m; d++)
            {
                char temp = str.charAt(d);
                if (temp == 'S')
                {
                    startX = d;
                    startY = i;
                }
                else if (temp == '.')
                {
                    empties.add(new int[] {i, d});
                }
                grid[i][d] = temp;
            }
        }

        for (int i = 1; i < n-1; i++)
        {
            for (int d = 1; d < n-1; d++)
            {
                if (grid[i][d] == 'C')
                {
                    grid[i][d] = 'W';
                    boolean[] dirs = new boolean[4];
                    Arrays.fill(dirs, true);
                    for (int k = 1; k < Math.max(n,m); k++)
                    {
                        int x = 0, y = 0;
                        for (int dir = 0; dir < 4; dir++)
                        {
                            if (dirs[dir])
                            {
                                int[] coord = directionToCoordinate(d, i, dir, k);
                                char temp = grid[coord[1]][coord[0]];
                                if (temp == '.')
                                {
                                    ArrayList<Integer> tempList = new ArrayList<>();
                                    tempList.add(coord[0]);
                                    tempList.add(coord[1]);
                                    camera.add(tempList.hashCode());
                                }
                                else if (temp == 'W')
                                {
                                    dirs[dir] = false;
                                }
                                else if (temp == 'S')
                                {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        queue = new TreeMap<Integer, ArrayDeque<QueueItem>>();
        queue.put(0, new ArrayDeque<>());
        queue.get(0).add(new QueueItem(0, new int[] {startX, startY}));
        int current = 0;
        while (queue.size() > 0)
        {
            if (!queue.containsKey(current) || queue.get(current).size() == 0)
            {
                if (current == queue.lastKey())
                {
                    break;
                }
                else
                {
                    current++;
                }
            }
            else
            {
                QueueItem q = queue.get(current).pop();
                checkItem(q.coord[0], q.coord[1], q.count);
            }
        }
        for (int i = 0; i < empties.size(); i++)
        {
            System.out.println(results[empties.get(i)[0]][empties.get(i)[1]]);
        }
    }
    public static void checkItem(int x, int y, int count)
    {
        ArrayList<Integer> tempList = new ArrayList<>();
        tempList.add(x);
        tempList.add(y);
        int hash = tempList.hashCode();
        if (!hashSet.add(hash) || camera.contains(hash))
        {
            return;
        }
        char temp = grid[y][x];
        int[] coord;
        switch (temp)
        {
            case 'W':
                return;
            case 'U':
                coord = directionToCoordinate(x,y,0,1);
                QueueAdd(count, new QueueItem(count, coord));
                break;
            case 'R':
                coord = directionToCoordinate(x,y,1,1);
                QueueAdd(count, new QueueItem(count, coord));
                break;
            case 'D':
                coord = directionToCoordinate(x,y,2,1);
                QueueAdd(count, new QueueItem(count, coord));
                break;
            case 'L':
                coord = directionToCoordinate(x,y,3,1);
                QueueAdd(count, new QueueItem(count, coord));
                break;
            case'.':
                results[y][x] = count;
                for (int i = 0; i < 4; i++)
                {
                    coord = directionToCoordinate(x, y, i, 1);
                    {
                        if (grid[coord[1]][coord[0]] != 'W')
                        {
                            if (grid[coord[1]][coord[0]] == '.')
                            {
                                if (results[coord[1]][coord[0]] == -1 || count < results[coord[1]][coord[0]])
                                {
                                    QueueAdd(count+1, new QueueItem(count+1, coord));
                                }
                            }
                            else
                            {
                                QueueAdd(count+1, new QueueItem(count+1, coord));
                            }
                        }
                    }
                }
                break;
            default:
                for (int i = 0; i < 4; i++)
                {
                    coord = directionToCoordinate(x, y, i, 1);
                    {
                        if (grid[coord[1]][coord[0]] != 'W')
                        {
                            QueueAdd(count+1, new QueueItem(count + 1, coord));
                        }
                    }
                }
                break;
        }
    }
    public static void QueueAdd(int count, QueueItem item)
    {
        if (!queue.containsKey(count))
        {
            queue.put(count, new ArrayDeque<>());
            queue.get(count).add(item);
        }
        else
        {
            queue.get(count).add(item);
        }
    }
    public static int[] directionToCoordinate(int x, int y, int dir, int dist)
    {
        switch(dir)
        {
            case 0:
                y -= dist;
                break;
            case 1:
                x += dist;
                break;
            case 2:
                y += dist;
                break;
            case 3:
                x -= dist;
                break;
        }
        return new int[] {x, y};
    }
    public static class QueueItem
    {
        public int count;
        public int[] coord;
        public QueueItem(int cnt, int[] crd)
        {
            count = cnt;
            coord = crd;
        }
    }
}
