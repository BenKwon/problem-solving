package programmers.카카오인턴십;

import java.util.Arrays;

public class 경주로건설2 {
    static int[][] tmp_visit;
    static int[][][] dp;
    static int[][][] visit;
    static int[] row_move = new int[]{-1, 1, 0, 0};
    static int[] col_move = new int[]{0, 0, -1, 1};
    static int n, m;

    static public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        m = board[0].length;
        dp = new int[n][m][4];
        visit = new int[n][m][4];
        tmp_visit = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], 100000000);
            }
        }

        dp[n - 2][m - 1][1] = 100;
        visit[n - 2][m - 1][1] = 1;

        dp[n - 1][m - 2][3] = 100;
        visit[n - 1][m - 2][3] = 1;

        dfs(0, 0, board);
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            answer = Integer.min(answer, dp[0][0][i]);
//            System.out.printf("%d ", dp[0][0][i]);
        }
        return answer;

    }

    static public int dfs(int row, int col, int[][] board) {

        for (int i = 0; i < 4; i++) {
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];


            if (n_row < 0 || n_row >= n || n_col < 0 || n_col >= m || board[n_row][n_col] == 1) {
                visit[row][col][i] = 1;
                continue;
            }
            if (visit[row][col][i] == 1) continue;
            if (tmp_visit[n_row][n_col] == 1) continue;
            if (Arrays.stream(visit[n_row][n_col]).sum() < 4) {
                tmp_visit[row][col] = 1;
                dfs(n_row, n_col, board);
            }
            //dp[n_row][n_col] 이 완성되 어 있을 것임 단 지금까지 지나온 경로에 대해서는 재방문 하지 않음
            int min = 100000000;
            int jump = 0;
            if (i == 0) jump = 1;
            else if (i == 1) jump = 0;
            else if (i == 2) jump = 3;
            else jump = 2;
            int corner_extra = 0;
//            System.out.printf("row : %d , col : %d , n_row : %d , n_col : %d \n", row, col, n_row, n_col);
//            System.out.println("================================");
            for (int q = 0; q < 4; q++) {
//                System.out.printf("dp[%d][%d] : %d \n", n_row,n_col,q);
//                System.out.printf("dp[%d][%d][%d] : %d \n",n_row,n_col,q,dp[n_row][n_col][q]);
                corner_extra = 0;
                if (q == jump) continue;
                if (i == 0 && (q == 2 || q == 3)) corner_extra = 500;
                else if (i == 1 && (q == 2 || q == 3)) corner_extra = 500;
                else if (i == 2 && (q == 0 || q == 1)) corner_extra = 500;
                else if (i == 3 && (q == 0 || q == 1)) corner_extra = 500;
                min = Math.min(min, dp[n_row][n_col][q] + corner_extra);
            }
//            System.out.printf("min : %d \n", min);
            tmp_visit[row][col] = 0;
            dp[row][col][i] = min + 100;
//            System.out.printf("dp[%d][%d][%d] : %d\n",row,col,i, dp[row][col][i]);
            visit[row][col][i] = 1;
        }
        return 1;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] board2 = new int[][]{
                {0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}
        };
        int[][] board3 = new int[][]{
                {0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}
        };
        solution(board2);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            min = Integer.min(min, dp[0][0][i]);
            System.out.printf("%d ", dp[0][0][i]);
        }
        System.out.println();
        System.out.println(min);
    }

}
