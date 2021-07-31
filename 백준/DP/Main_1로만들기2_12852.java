package 백준.DP;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Main_1로만들기2_12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[3000001];
        int[] tracking = new int[3000001];
        int[] visit = new int[3000001];
        for (int i = 1; i <= n - 1; i++) {
            if (visit[i * 2] == 0) {
                dp[i * 2] = dp[i] + 1;
                visit[i * 2] = 1;
                tracking[i * 2] = i;
            } else {
                if (dp[i] + 1 < dp[i * 2]) {
                    tracking[i * 2] = i;
                    dp[i * 2] = dp[i] + 1;
                }
            }

            if (visit[i * 3] == 0) {
                dp[i * 3] = dp[i] + 1;
                visit[i * 3] = 1;
                tracking[i * 3] = i;
            } else {
                if (dp[i] + 1 < dp[i * 3]) {
                    tracking[i * 3] = i;
                    dp[i * 3] = dp[i] + 1;
                }
            }

            if (visit[i + 1] == 0) {
                dp[i + 1] = dp[i] + 1;
                visit[i + 1] = 1;
                tracking[i + 1] = i;
            } else {
                if (dp[i] + 1 < dp[i + 1]) {
                    tracking[i + 1] = i;
                    dp[i + 1] = dp[i] + 1;
                }
            }

        }
        System.out.println(dp[n]);
        for (int i = n; i >= 1;) {
            System.out.printf("%d ", i);
            i = tracking[i];
        }
    }
}
