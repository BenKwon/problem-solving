package 백준.DP;

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main_2407_조합{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        BigInteger[] dp = new BigInteger[m + 1];
        dp[1] = BigInteger.valueOf(n);
        for(int i = 2; i <= m ; i++){
            dp[i] = (dp[i-1].multiply(BigInteger.valueOf(n-i+1))).divide(BigInteger.valueOf(i));
        }
        System.out.println(dp[m]);
    }

}