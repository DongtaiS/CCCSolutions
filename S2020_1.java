package cccpractice;
import java.util.*;
public class S2020_1
{
    public static void main (String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        double topSpeed = 0;
        List<double[]> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
        {
            arr.add(new double[]{s.nextDouble(), s.nextDouble()});
        }
        Collections.sort(arr, Comparator.comparingDouble(o -> o[0]));
        double lastTime = arr.get(0)[0];
        double lastDist = arr.get(0)[1];
        for (int i = 1; i < n; i++)
        {
            double c = Math.abs(arr.get(i)[1] - lastDist) / Math.abs(arr.get(i)[0] - lastTime);
            if (c > topSpeed)
            {
                topSpeed = c;
            }
            lastTime = arr.get(i)[0];
            lastDist = arr.get(i)[1];
        }
        System.out.println(topSpeed);
    }
}
