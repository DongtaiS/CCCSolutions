import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2019_3
{
    public static int ONEBILANDONE = 1000000001;
    public static int[][] square = new int[3][3];
    public static int[] hXCounts = new int[3];
    public static int[] vXCounts = new int[3];
    public static void main(String args[]) throws IOException
    {
        Arrays.fill(hXCounts, 0);
        Arrays.fill(vXCounts, 0);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int d = 0; d < 3; d++)
            {
                String str = st.nextToken();
                int temp;
                if (str.equals("X"))
                {
                    hXCounts[i] += 1;
                    vXCounts[d] += 1;
                    temp = ONEBILANDONE;
                }
                else
                {
                    temp = Integer.parseInt(str);
                }
                square[i][d] = temp;
            }
        }
        if (!recursive(square, hXCounts, vXCounts))
        {
            rowFill();
            if (!isSolved(square))
            {
                floodFill();
            }
        }
        printSquare(square);
    }
    public static void rowFill()
    {
        for (int i = 0; i < 3; i++)
        {
            if (hXCounts[i] == 0)
            {
                for (int k = 0; k < 3; k++)
                {
                    for (int d = 0; d < 3; d++)
                    {
                        square[k][d] = square[i][d];
                    }
                }
            }
            if (vXCounts[i] == 0)
            {
                for (int k = 0; k < 3; k++)
                {
                    for (int d = 0; d < 3; d++)
                    {
                        square[d][k] = square[d][i];
                    }
                }
            }
        }
    }
    public static void floodFill()
    {
        int only = ONEBILANDONE;
        for (int i = 0; i < 3; i++)
        {
            for (int d = 0; d < 3; d++)
            {
                if (square[i][d] != ONEBILANDONE)
                {
                    only = square[i][d];
                    break;
                }
            }
        }
        if (only == ONEBILANDONE)
        {
            only = 1;
        }
        for (int i = 0; i < 3; i++)
        {
            for (int d = 0; d < 3; d++)
            {
                square[i][d] = only;
            }
        }
    }
    public static int checkSingles(int[][] sqr, int[] hcount, int[] vcount)
    {
        int result = 0;
        for (int i = 0; i < 3; i++)
        {
            if (hcount[i] == 1)
            {
                for (int d = 0; d < 3; d++)
                {
                    if (sqr[i][d] == ONEBILANDONE)
                    {
                        sqr[i][d] = solve(sqr,true, i, d);
                        hcount[i] -= 1;
                        vcount[d] -= 1;
                        result = 1;
                    }
                }
            }
            if (vcount[i] == 1)
            {
                for (int d = 0; d < 3; d++)
                {
                    if (sqr[d][i] == ONEBILANDONE)
                    {
                        sqr[d][i] = solve(sqr,false, i, d);
                        hcount[d] -= 1;
                        vcount[i] -= 1;
                        result = 1;
                    }
                }
            }
        }
        if (checkValid(sqr))
        {
            return result;
        }
        else
        {
            return -1;
        }
    }
    public static void printSquare(int[][] sqr)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int d = 0; d < 3; d++)
            {
                System.out.print(sqr[i][d] + " ");
            }
            System.out.println();
        }
    }
    public static boolean checkValid(int[][] sqr)
    {
        for (int i = 0; i < 3; i++)
        {
            if (sqr[i][0] != ONEBILANDONE && sqr[i][1] != ONEBILANDONE  && sqr[i][2] != ONEBILANDONE)
            {
                if (sqr[i][2] - sqr[i][1] != sqr[i][1] - sqr[i][0])
                {
                    return false;
                }
            }
            if (sqr[0][i] != ONEBILANDONE && sqr[1][i] != ONEBILANDONE && sqr[2][i] != ONEBILANDONE)
            {
                if (sqr[2][i] - sqr[1][i] != sqr[1][i] - sqr[0][i])
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean recursive(int[][] sqr, int[]hXCount, int[] vXCount)
    {
        while (true)
        {
            int res = checkSingles(sqr, hXCount, vXCount);
            if (res == -1)
            {
                return false;
            }
            else if (res == 0)
            {
                break;
            }
        }
        if (isSolved(sqr) && checkValid(sqr))
        {
            square = sqr;
            return true;
        }
        int counter = 0;
        for (int i = 0; i < 3; i++)
        {
            if (hXCount[i] == 2)
            {
                int onlyIndex = getOnlyIntIndex(sqr,true, i);
                int integerInRow = sqr[i][onlyIndex];
                int[] indices = indicesExcept(onlyIndex);
                for (int d = 0; d < 2; d++)
                {
                    if (vXCount[indices[d]] == 2)
                    {
                        while (true)
                        {
                            int addamount = 0 + counter;
                            int[][] newSqr = new int[3][3];
                            for (int k = 0; k < 3; k++)
                            {
                                newSqr[k] = Arrays.copyOf(sqr[k], 3);
                            }
                            int[] newhCount = Arrays.copyOf(hXCount, 3);
                            int[] newvCount = Arrays.copyOf(vXCount, 3);
                            newSqr[i][indices[d]] = integerInRow + addamount;
                            newvCount[indices[d]] -= 1;
                            newhCount[i] -= 1;
                            if (!recursive(newSqr, newhCount, newvCount))
                            {
                                counter++;
                            }
                            else
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public static int getOnlyIntIndex(int[][] sqr, boolean horizontal, int index)
    {
        for (int i = 0; i < 3; i++)
        {
            if (horizontal)
            {
                if (sqr[index][i] != ONEBILANDONE)
                {
                    return i;
                }
            }
            else
            {
                if (sqr[i][index] != ONEBILANDONE)
                {
                    return i;
                }
            }
        }
        return ONEBILANDONE;
    }
    public static int solve(int[][] sqr, boolean horizontal, int index, int pos)
    {
        if (horizontal)
        {
            switch (pos)
            {
                case 0:
                    return sqr[index][pos+1] - (sqr[index][pos+2] - sqr[index][pos+1]);
                case 1:
                    return sqr[index][pos-1] + (sqr[index][pos+1] - sqr[index][pos-1])/2;
                case 2:
                    return sqr[index][pos-1] + sqr[index][pos-1] - sqr[index][pos-2];
            }
        }
        else
        {
            switch (pos)
            {
                case 0:
                    return sqr[pos+1][index] - (sqr[pos+2][index] - sqr[pos+1][index]);
                case 1:
                    return sqr[pos-1][index] + (sqr[pos+1][index] - sqr[pos-1][index])/2;
                case 2:
                    return sqr[pos-1][index] + sqr[pos-1][index] - sqr[pos-2][index];
            }
        }
        return 0;
    }
    public static boolean isSolved(int[][] sqr)
    {
        boolean solved = true;
        for (int i = 0; i < 3; i++)
        {
            for (int d = 0; d < 3; d++)
            {
                if (sqr[i][d] == ONEBILANDONE)
                {
                    solved = false;
                    break;
                }
            }
        }
        return solved;
    }
    public static int[] indicesExcept(int exception)
    {
        int[] result = new int[2];
        int counter = 0;
        for (int i = 0; i < 3; i++)
        {
            if (i != exception)
            {
                result[counter] = i;
                counter++;
            }
        }
        return result;
    }
}
