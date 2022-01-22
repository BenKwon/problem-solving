package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class Main_11570_환상의듀엣 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][n + 1];
        int[] score = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            score[i + 1] = Integer.parseInt(st.nextToken());
        }
        dp[1][0] = 0;
//        dp[2][0] = Math.abs(score[2] - score[1]);
//        dp[2][1] = 0;

        for (int i = 2; i <= n; i++) {
            int curHeight = score[i];
            int beforeHeight = score[i - 1];
            for (int j = 0; j < i - 1; j++) {
                dp[i][j] = dp[i - 1][j] + Math.abs(curHeight - beforeHeight);
            }
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < i - 1; j++) {
                int tmp;
                if (j == 0)
                    tmp = dp[i - 1][j];
                else
                    tmp = dp[i - 1][j] + Math.abs(curHeight - score[j]);
                min = Math.min(tmp, min);
            }
            dp[i][i - 1] = min;
        }
        int solution = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            solution = Math.min(solution, dp[n][i]);
        }
        System.out.println(solution);

    }
}
