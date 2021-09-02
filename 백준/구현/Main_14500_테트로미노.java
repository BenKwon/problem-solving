package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {
    public static int[] row_move = new int[]{-1, 1, 0, 0};
    public static int[] col_move = new int[]{0, 0, -1, 1};
    public static int n, m;
    public static int[][] graph;
    static int sol = Integer.MIN_VALUE;

    public static void dfs(int row, int col, int level, int p_row, int p_col, int count) {
        if (level == 4) {
            sol = Math.max(sol, count);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];
            if (n_row == p_row && n_col == p_col) continue;
            if(n_row < 0 || n_row >= n || n_col < 0 || n_col >= m) continue;
            dfs(n_row, n_col, level + 1, row, col, count + graph[n_row][n_col]);

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i,j,1,-1,-1,graph[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum = graph[i][j];
                //위로
                if ((i - 1) >= 0 && (j - 1) >= 0 && (j + 1) < m) {
                    sum += graph[i - 1][j] + graph[i - 1][j + 1] + graph[i - 1][j - 1];
                }
                sol = Math.max(sol, sum);
                sum = graph[i][j];
                //아래로
                if ((i + 1) < n && (j - 1) >= 0 && (j + 1) < m) {
                    sum += graph[i + 1][j] + graph[i + 1][j + 1] + graph[i + 1][j - 1];

                }
                sol = Math.max(sol, sum);
                sum = graph[i][j];
                //왼쪽으로
                if ((i - 1) >= 0 && (i + 1) < n && (j - 1) >= 0) {
                    sum += graph[i - 1][j - 1] + graph[i][j - 1] + graph[i + 1][j - 1];
                }
                //오른쪽으로
                sol = Math.max(sol, sum);
                sum = graph[i][j];
                if ((i - 1) >= 0 && (i + 1) < n && (j + 1) < m) {
                    sum += graph[i - 1][j + 1] + graph[i][j + 1] + graph[i + 1][j + 1];
                }
                sol = Math.max(sol, sum);
            }
        }

        System.out.println(sol);
    }
}
