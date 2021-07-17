import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class S2019_4
{
    public static ArrayList<Long> attractions = new ArrayList<>();
    public static int n, k, days;
    public static HashMap<Long, Long> comboMap = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 16384);
        StringTokenizer firstInput = new StringTokenizer(br.readLine());
        n = Integer.parseInt(firstInput.nextToken());
        k = Integer.parseInt(firstInput.nextToken());
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
        {
            attractions.add(Long.parseLong(str[i]));
        }
        days = n / k;
        if (n % k != 0)
        {
            days++;
        }
        System.out.println(solve(0, 0, n, 0));
    }
    public static long solve(int currentDay, int index, int remainAttr, long count)
    {
        ArrayList<Long> results = new ArrayList<>();
        for (int i = k; i >= 1; i--)
        {
            long temp = count;
            if (currentDay + Math.ceil((double)remainAttr / k) <= days)
            {
                if (remainAttr <= k)
                {
                    temp += maxScoreForDay(index, remainAttr);
                    return temp;
                }
                else
                {
                    temp += maxScoreForDay(index, i);
                    results.add(solve(currentDay+1, index+i, remainAttr - i, temp));
                }
            }
            else
            {
                break;
            }
        }
        long result = 0;
        for (int i = 0; i < results.size(); i++)
        {
            result = Math.max(results.get(i), result);
        }
        return result;
    }
    public static long maxScoreForDay(int startIndex, int visitedNum)
    {
        long temp = startIndex * k + visitedNum;
        if (comboMap.containsKey(temp))
        {
            return comboMap.get(temp);
        }
        else
        {
            long result = 0;
            for (int i = visitedNum-1; i > 0; i--)
            {
                if (comboMap.containsKey(startIndex*k + i))
                {
                    result = comboMap.get(startIndex*k + i);
                    startIndex += i;
                    visitedNum = i;
                }
            }
            for (int i = startIndex; i < Math.min(startIndex + visitedNum, attractions.size()); i++)
            {
                result = Math.max(result, attractions.get(i));
            }
            comboMap.put(temp, result);
            return result;
        }
    }
}
