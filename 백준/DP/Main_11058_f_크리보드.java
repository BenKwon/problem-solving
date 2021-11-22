package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;

public class Main_11058_f_크리보드 {
    static long[] dp;
    static int n;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[n+1];
        for(int i = 1; i <= 6 && i <= n ; i++){
            dp[i] = i ;
        }
        for(int i = 7 ; i <= n ; i++){
           long max = dp[i - 1] + 1;
           for(int j = 3; j < i ; j++){
               max = Math.max(max, dp[i-j]*(j-1));
           }
           dp[i] = max;
        }

        System.out.println(dp[n]);


    }
}
