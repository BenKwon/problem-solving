package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_행렬곱셈순서_11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] d = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d[i] = Integer.parseInt(st.nextToken());
            d[i + 1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            int row = 1;
            for (int j = i + 1; j <= n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = row; k < j; k++) {
                    min = Math.min(dp[row][k] + dp[k + 1][j] + (d[row - 1] * d[k] * d[j]), min);
                }
                dp[row][j] = min;
                row++;
            }
        }
        System.out.println(dp[1][n]);

    }
}
