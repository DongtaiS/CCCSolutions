/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cccpractice;

/**
 *
 * @author manscare
 */
import java.util.*;
public class S2017_4
{
    
    private static ArrayList<ArrayList<Integer>> b = new ArrayList<>();
    private static ArrayList<Boolean> bcheck = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> blist = new ArrayList<>();
    
    private static int m, n ,d;    
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        String input = s.nextLine();
        ArrayList<Integer> inputs = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (i == input.length()-1) {
                inputs.add(Integer.parseInt(input.substring(count)));
            }
            else if (input.substring(i,i+1).equals(" ")) {
                inputs.add(Integer.parseInt(input.substring(count,i)));
                count = i+1;
            }
        }
        m = inputs.get(1);
        n = inputs.get(0);
        d = inputs.get(2);
        
        for (int i = 0; i < m; i++) {
            b.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            count = 0;
            String in = s.nextLine();
            for (int u = 0; u < in.length(); u++) {
                if (u == in.length()-1) {
                    b.get(i).add(Integer.parseInt(in.substring(count)));
                }
                else if (in.substring(u, u+1).equals(" ")) {
                    b.get(i).add(Integer.parseInt(in.substring(count, u)));
                    count = u+1;
                }
            }
        }
        for (int i = 0; i < b.size(); i++)
        {
            bcheck.add(true);
        }
        
        for (int i = 0; i < b.size(); i++)
        {
            boolean g = true;
            int dex1,dex2;
            dex1 = i;
            dex2 = 0;
            if (b.get(i).get(0) == 1) {
                dex2 = 1;
                blist.add(new ArrayList<>());
            }
            else if (b.get(i).get(1) == 1) {
                dex2 = 0;
                blist.add(new ArrayList<>());
            }
            else {
                g = false;
            }
            while (g) {
                bcheck.set(dex1, false);
                blist.get(blist.size() - 1).add(dex1);          
                int[] intsearch = search(b.get(dex1).get(dex2), dex2);
                if (intsearch[0] != -1)
                {
                    System.out.println(intsearch[0] + " " + intsearch[1]);
                    dex1 = intsearch[0];
                    dex2 = intsearch[1];
                }
                else
                {
                    g = false;
                }
            }
        }
        for (int i = 0; i < blist.size(); i++)
        {
            for (int k = 0; k < blist.get(i).size(); k++)
            {
                if (k == blist.get(i).size()-1) 
                {
                    System.out.println(blist.get(i).get(k));
                }
                else 
                {
                    System.out.print(blist.get(i).get(k));
                }
            }
        }
        
    }
    
    public static int[] search(int in, int in2)
    {
        for (int i = 0; i < b.size(); i++)
        {
            if (bcheck.get(i) == true) {
                if (b.get(i).get(0) == in && in2 == 1) 
                {
                    int[] ret = {i,1};
                    return ret;
                }
                else if (b.get(i).get(1) == in && in2 == 0) 
                {
                    int[] ret = {i,0};
                    return ret;
                }
            }
        }
        int[] ret = {-1,-1};
        return ret;
    }
    
}
