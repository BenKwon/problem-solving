package 백준.DP;

import java.util.Arrays;
import java.util.Scanner;

public class Main_동물원_1309 {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        long[][] dp = new long[n + 1][3];
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1; //dp 초기값 설정

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) { //사자가 없음
                    dp[i][j] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2])%9901;
                } else if (j == 1) { //사자가 왼쪽
                    dp[i][j] = (dp[i - 1][0] +  dp[i - 1][2])%9901;
                } else if (j == 2) { //사자가 오른쪽
                    dp[i][j] = (dp[i - 1][0] +  dp[i - 1][1])%9901;
                }
            }
        }
        System.out.println((Arrays.stream(dp[n]).sum())%9901);


    }
}
