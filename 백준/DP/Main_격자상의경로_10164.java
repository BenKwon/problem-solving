package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main_격자상의경로_10164 {
    public static int n, m, k;
    public static int k_row, k_col;
    public static int[][] dp;
    public static int[] row_move = new int[]{1, 0};
    public static int[] col_move = new int[]{0, 1};

    public static int dfs1(int row, int col) {
        if (row == k_row && col == k_col) {
            return 1;
        }
        if (dp[row][col] != 0) return dp[row][col];

        int sum = 0;
        for (int i = 0; i < 2; i++) {
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];
            if (n_row > k_row || n_col > k_col || n_row < 0 || n_col < 0) continue;
            sum += dfs1(n_row, n_col);
        }
        return dp[row][col] = sum;

    }

    public static int dfs2(int row, int col) {
        if (row == n - 1 && col == m - 1) return 1;

        if (dp[row][col] != 0) return dp[row][col];

        int sum = 0;
        for (int i = 0; i < 2; i++) {
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];
            if (n_row >= n || n_col >= m || n_row < 0 || n_col < 0) continue;
            sum += dfs2(n_row, n_col);
        }
        return dp[row][col] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            if ((m * i) + 1 <= k && k < (m * (i + 1) + 1)) {
                k_row = i;
                k_col = k - ((m * i) + 1);
            }
        }

        int sol1 = dfs1(0, 0);
        int sol2 = dfs2(k_row, k_col);
        System.out.println(sol1 * sol2);
    }
}
