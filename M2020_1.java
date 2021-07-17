package cccpractice;
import java.util.*;
import java.io.*;

public class M2020_1
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>(n);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
        {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);
        for (int i = 0; i < n-1; i++)
        {
            System.out.print(arr.get(i) + " ");
        }
        System.out.print(arr.get(arr.size()-1));
    }
}
