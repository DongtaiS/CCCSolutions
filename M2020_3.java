package cccpractice;
import java.util.*;
import java.io.*;

import java.io.IOException;

public class M2020_3
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < n+1; i++)
        {
            graph.put(i+1, new ArrayList<>());
        }
        for (int i = 0; i < m; i++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(sta.nextToken())).add(new int[]{Integer.parseInt(sta.nextToken()),Integer.parseInt(sta.nextToken())});
        }
        for (int i = 0; i < q; i++)
        {
            int lowest = Integer.MAX_VALUE;
            StringTokenizer sta = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(sta.nextToken()), b = Integer.parseInt(sta.nextToken());
            for (int k = 0; k < graph.get(a).size(); k++)
            {
                if (graph.get(a).get(k)[0] == b)
                {
                    lowest = 0;
                    break;
                }
            }
            if (lowest == Integer.MAX_VALUE)
            {
                HashSet<Integer> contains = new HashSet<>(n);
                LinkedList<int[]> queue = new LinkedList<>();
                for (int k = 0; k < graph.get(a).size(); k++)
                {
                    queue.add(graph.get(a).get(k));
                }
                while (queue.size() > 0)
                {
                    int[] temp = queue.pollFirst();
                    for (int k = 0; k < graph.get(temp[0]).size(); k++)
                    {
                        if (graph.get(temp[0]).get(k)[0] > temp[0])
                        {
                            queue.add(new int[]{graph.get(temp[0]).get(k)[0], temp[1] | graph.get(temp[0]).get(k)[1]});
                        }
                    }
                }
            }
            System.out.println(lowest);
        }
    }
}
