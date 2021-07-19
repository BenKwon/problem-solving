package 백준.DP;

import java.util.Scanner;

public class Main_합분해_2225 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[][] dp = new long[k + 1][n + 1];

        for(int j  = 0 ; j <= n ; j++){
            dp[1][j] = 1;
        }

        for (int i = 2; i <= k; i++) {
            dp[i][0] = 1;
            for(int j = 1 ; j <= n ; j++){
                long sum = 0;
                for(int m = 0 ; m <= j ; m++){
                    sum += (dp[i-1][j-m])%1000000000;
                }
                dp[i][j] = sum%1000000000;
            }
        }
        System.out.println(dp[k][n]%1000000000);
    }
}
