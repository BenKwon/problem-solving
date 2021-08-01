package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_로봇조종하기_2169 {
    static int graph[][];
    static int dp[][][][];
    static int visit[][][];
    static int[] row_move = new int[]{1, 0, 0};
    static int[] col_move = new int[]{0, 1, -1};
    static int n, m;
    public static void dfs(int row, int col, int parent_row, int parent_col) {
        if (row == n && col == m) {
            return;
        }
        for (int i = 0; i < 3; i++) { // i = 0 아래쪽 1 : 오른쪽 2; 왼쪽
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];
            if (n_row < 1 || n_row > n || n_col < 1 || n_col > m) {
                visit[row][col][i] = 1;
                continue;
            }
            if(visit[row][col][i] ==1) continue;

            if (n_row == parent_row && n_col == parent_col) continue;

            if (Arrays.stream(visit[n_row][n_col]).sum() < 3) {
                dfs(n_row, n_col, row, col);
            }

            int target_value = -1000000001;
            for (int t = 0; t < 3; t++) {
                if (dp[n_row][n_col][t][1] == row && dp[n_row][n_col][t][2] == col) continue;
                if (dp[n_row][n_col][t][0] > target_value) {
                    target_value = dp[n_row][n_col][t][0];
                }
            }
            dp[row][col][i][0] = target_value + graph[row][col];
            dp[row][col][i][1] = n_row; dp[row][col][i][2] = n_col; visit[row][col][i] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1][3][3];
        visit = new int[n + 1][m + 1][3]; // 0 : 아래 , 1: 오른쪽 2 : 왼쪽

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k][0] = -1000000001;
                }
            }
        }

        dp[n][m][0] = new int[]{graph[n][m], -1, -1};
        dfs(1, 1, 0, 0);
        int max = -1000000001;
        for (int k = 0; k < 3; k++) {
            max = Math.max(max, dp[1][1][k][0]);
        }
        System.out.println(max);


    }
}
