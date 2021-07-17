package cccpractice;
import java.io.*;
import java.util.*;

public class S2020_5
{
    static int[] counts = new int[500001];
    static List<Integer> allNums;
    static HashMap<Integer, Double> results = new HashMap<>();

    public static void main (String[] args) throws IOException
    {
        double result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        allNums = new ArrayList<>(n+1);

        StringTokenizer st = new StringTokenizer(br.readLine());

        int counter = 0;
        for (int i = 0; i < n; i++)
        {
            int temp = Integer.parseInt(st.nextToken());
            allNums.add(temp);
            counts[temp]++;
            counter = Math.max(counter, temp);
        }
        int coach = allNums.get(0);
        int last = allNums.get(allNums.size()-1);

        if (coach == last)
        {
            System.out.println("1");
        }
        else
        {
            results.put(coach, 1.0);
            results.put(last, 0.0);
            int subTotal = 0;
            allNums.add(coach);
            int lastIndex = n+1;
            double tempRes = 0.0;
            double sum;
            List<Integer> resList;
            HashSet<Integer> tempList = new HashSet<>(counter+1);
            tempList.add(coach);
            tempList.add(last);
            for (int i = n-2; i > 0; i--)
            {
                if (!tempList.contains(allNums.get(i)))
                {
                    resList = allNums.subList(i+1, lastIndex);
                    subTotal += lastIndex - i-1;
                    sum = tempRes;
                    for (int k : resList)
                    {
                        sum += results.get(k);
                    }
                    resList.clear();
                    tempRes = sum;
                    sum /= subTotal;
                    results.put(allNums.get(i), sum);
                    sum *= counts[allNums.get(i)];
                    result += sum;

                    lastIndex = i+1;
                    tempList.add(allNums.get(i));
                }
                if (tempList.size() == counter+1)
                {
                    break;
                }
            }
            result += counts[coach];
            result /= n;
            System.out.println(result);
        }
    }
}
