package programmers.카카오인턴십;

import java.util.Arrays;

public class 경주로건설 {
    static int n, m;
    static int[][][] visit; // 0 : 상 , 1 : 하 , 2 : 좌 , 3 : 우
    static int[][][] dp;
    static int[] row_move = new int[]{-1, 1, 0, 0};
    static int[] col_move = new int[]{0, 0, -1, 1};

    public static int dfs(int row, int col, int p_row, int p_col, int[][] board) {

        int result = Integer.MAX_VALUE;
//        int corner_weight = 0;
//        if (corner_check(p_row, p_col, n_row, n_col) == 1) {
//            corner_weight = 500;
//        }
        Arrays.fill(visit[row][col],2);

        for (int i = 0; i < 4; i++) {
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];

            if (n_row < 0 || n_row >= n || n_col < 0 || n_col >= m) {
                visit[row][col][i] = 1;
                continue;
            }
            if (visit[row][col][i] == 1) continue;

            if (n_row == p_row && n_col == p_col) continue;
            if (Arrays.stream(visit[n_row][n_col]).sum() < 4) {
                visit[row][col][i] = 1;
                dfs(n_row, n_col, row, col, board);
            }

            int min = Integer.MAX_VALUE;
            int jump = 0;
            if (i == 0) jump = 1;
            else if (i == 1) jump = 0;
            else if (i == 2) jump = 3;
            else jump = 2;
            int corner_extra = 0;
            for (int q = 0; q < 4; q++) {
                corner_extra = 0;
                if (q == jump) {
                    continue;
                }
                if (i == 0 && q != 0) {
                    corner_extra = 500;
                }
                min = Math.min(min, dp[n_row][n_col][i] + corner_extra);
            }
            dp[row][col][i] = min + 100;

            visit[row][col][i] = 1;
        }
        return 1;
    }

    static int corner_check(int row, int col, int p_row, int p_col) {
        if (row == p_row || col == p_col) return 0;
        return 1;
    }

    static public int solution(int[][] board) {

        n = board.length;
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], 100);
        }
//        board[n - 1][m - 1] = -1;
        dp = new int[n][m][4];
        visit = new int[n][m][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);

            }
        }
        dp[n - 2][m-1][1] = 100;
        dp[n-1][m - 2][3] = 100;
        Arrays.fill(visit[n - 2][m-1], 1);
        Arrays.fill(visit[n-1][m - 2], 1);
        dfs(0, 0, -1, -1, board);
        for (int i = 0; i < 4; i++) {
            System.out.printf("%d ", dp[0][0][i]);
        }
        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };
        solution(board);
    }
}
