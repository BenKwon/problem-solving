package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1947_선물전달 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];
        long[] dp2 = new long[n + 1];
        long INF = 1000000000;
        if (n >= 2) {
            dp[2] = 1;
            dp2[2] = 1;
        }
        dp2[1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = (((i - 1) * ((dp[i - 2] % INF) + (((i - 2) * (dp2[i - 2] % INF)) % INF)))) % INF;
            dp2[i] = ((dp[i - 1] % INF) + (((i - 1) * (dp2[i - 1] % INF)) % INF)) % INF;
        }

        System.out.println(dp[n] % INF);


    }
}
