package 백준.DP;

import java.util.Scanner;

public class Main_2xn타일링_11726 {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        int[][] dp = new int[3][n+1];
        dp[2][1] = 1;
        if(n == 1){
            System.out.println(dp[2][n]);
            return;
        }
        dp[1][2] = 1;
        dp[2][2] = 2;
        if(n == 2){
            System.out.println(dp[2][n]);
            return;
        }
        for(int i = 3; i <= n;i++){
            dp[2][i] = (dp[2][i-2] * dp[1][2])%10007;
            dp[2][i] += (dp[2][i - 1])%10007;
        }
        System.out.println(dp[2][n]%10007);
    }
}
