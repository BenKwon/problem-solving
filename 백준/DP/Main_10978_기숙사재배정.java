package 백준.DP;

import java.io.*;
import java.util.*;

public class Main_10978_기숙사재배정 {
    static int n;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            n = Integer.parseInt(br.readLine());
            dp = new long[1 << n];
            Arrays.fill(dp, -1);
            long answer = dfs(0, 0);
            bw.write(answer + "\n");
        }
        bw.flush();
    }

    static long dfs(int index, int mask) {
        if (index == n) return 1;
        if (dp[mask] > 0) return dp[mask];
        long answer = 0;
        for (int i = 0; i < n; i++) {
            if (i == index) continue;
            int newMask = mask | 1 << i;
            if ((mask & (1 << i)) > 0) continue;
            answer += dfs(index + 1, newMask);
        }
        return dp[mask] = answer;
    }
}
