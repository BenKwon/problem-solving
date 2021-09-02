package 백준.DP;

import java.io.*;

public class Main_2688_줄어들지않아 {
    public static int n;
    public static long[][] dp;
    public static long dfs(int level, int cur) {
        if (level == n) {
            return 1;
        }

        if(dp[level][cur] != 0) return dp[level][cur];
        for (int i = cur; i <= 9; i++) {
            dp[level][cur] += dfs(level + 1, i);
        }
        return dp[level][cur];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            n = Integer.parseInt(br.readLine());
            dp = new long[n+1][10];
            dfs(0, 0);
            bw.write(dp[0][0] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
