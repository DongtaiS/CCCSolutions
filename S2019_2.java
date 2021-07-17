import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class S2019_2
{
    public static HashSet<Integer> nonPrimes = new HashSet<>();
    public static HashSet<Integer> primes = new HashSet<>();
    public static void main(String args[]) throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++)
        {
            int current = Integer.parseInt(br.readLine());
            if (primes.contains(current))
            {
                System.out.println(current + " " + current);
                continue;
            }
            int counter = 0;
            while (true)
            {
                if (isPrime(current - counter) && isPrime(current + counter))
                {
                    System.out.println((current - counter) + " " + (current + counter));
                    break;
                }
                else
                {
                    counter++;
                }
            }
        }

    }
    public static boolean isPrime(int input)
    {
        if (nonPrimes.contains(input))
        {
            return false;
        }
        if (input % 2 == 0)
        {
            nonPrimes.add(input);
            return false;
        }
        for (int i = 3; i < Math.sqrt(input)+1; i += 2)
        {
            if (nonPrimes.contains(i))
            {
                continue;
            }
            if (input % i == 0)
            {
                nonPrimes.add(input);
                return false;
            }
        }
        primes.add(input);
        return true;
    }

}
