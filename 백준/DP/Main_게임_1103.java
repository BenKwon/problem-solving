package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_게임_1103 {
    static int n, m;
    static int[][] graph;
    static int[][] check;
    static int[][] visit;
    static boolean infinite = false;
    static int[][] dp;
    static int[] row_move = new int[]{-1, 1, 0, 0};
    static int[] col_move = new int[]{0, 0, -1, 1};

    public static int dfs(int row, int col) {
        int jump = graph[row][col];
        int n_row;
        int n_col;
        int max = 0;
        boolean lastNode = true;
        if (visit[row][col] == 1) return dp[row][col];
        for (int i = 0; i < 4; i++) { // 상 하 좌 우 방문
            n_row = row + (row_move[i] * jump);
            n_col = col + (col_move[i] * jump);
            if (validationCheck(n_row, n_col)) {
                lastNode = false;
                if (check[n_row][n_col] == 0) {
                    check[n_row][n_col] = 1;
                    max = Integer.max(max, dfs(n_row, n_col) + 1);
                    check[n_row][n_col] = 0;
                } else { // 순환이 된다
                    infinite = true;
                    return -1;
                }
            }
        }
        dp[row][col] = max;
        visit[row][col] = 1;
        if (lastNode) {
            return 0;
        }
        return dp[row][col];
    }

    public static boolean validationCheck(int row, int col) {
        if ((row < 0 || row >= n || col < 0 || col >= m) || graph[row][col] == -1) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visit = new int[n][m];
        check = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                if (Character.isDigit(str.charAt(j))) {
                    graph[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                } else {
                    graph[i][j] = -1; // H == 구멍
                }
            }
        }
        check[0][0] = 1;
        dfs(0,0);
        if(infinite){
            System.out.println(-1);
        }else{
            System.out.println(dp[0][0] + 1);
        }
    }
}
