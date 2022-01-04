package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_알약_4811 {
    static int n;
    static long[][] dp;
    static long[][] visit;

    public static long dfs(int cur, int full, int half) {
        if(full == 0 && half == 0) return 1;
        if(visit[full][half] == 1) return dp[full][half];
        int count  = 0;
        if(full > 0){
            dp[full][half]  += dfs(1, full - 1, half + 1);
        }
        if(half > 0){
            dp[full][half] += dfs(0, full, half - 1);
        }
        visit[full][half] = 1;
        return dp[full][half];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            dp = new long[n + 1][n + 1];
            visit = new long[n + 1][n + 1];
            // cur == 0 반개 cur == 1 1개
            System.out.println(dfs(1, n - 1, 1));
        }

    }
}
