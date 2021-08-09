package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_말이되고픈원숭이_f_1600 {
    public static int[][] graph;
    public static int[][][] memo; //각 좌표에서 상하좌우로 들어올때 동작수
    static int k, w, h;
    static int[] row_move = new int[]{-1, 1, 0, 0, -2, -1, 1, 2, -2, -1, 1, 2};
    static int[] col_move = new int[]{0, 0, -1, 1, -1, -2, -2, -1, 1, 2, 2, 1};
    static int min = Integer.MAX_VALUE;

    public static int dfs(int row, int col, int k_remain, int count, int from) {
//        System.out.printf("row : %d , col : %d , count :%d \n", row ,col, count);

//        System.out.printf("row :%d , col: %d , count :%d\n",row,col,count);

        memo[row][col][k_remain] = count;

//        System.out.printf("memo[%d][%d][%d]:%d\n",row,col,from,count);
        if (row == h - 1 && col == w - 1) {
//            if (from == 10)
            min = Math.min(min, count);
            return 1;
        }
        for (int i = 0; i < 12; i++) {
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];
            if (n_row < 0 || n_row >= h || n_col < 0 || n_col >= w || graph[n_row][n_col] == 1) {
                continue;
            }
            if (i <= 3) {
//                System.out.printf("n_row : %d , n_col : %d , count :%d \n", n_row ,n_col, count + 1);
                if (memo[n_row][n_col][k_remain] <= count + 1) {
                    continue;
                }
                dfs(n_row, n_col, k_remain, count + 1, i);
            } else if (i >= 4 && k_remain > 0) {
//                System.out.println("k_remain = " + k_remain);
                if (memo[n_row][n_col][k_remain - 1] <= count + 1) {
                    continue;
                }
                dfs(n_row, n_col, k_remain - 1, count + 1, i);
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        graph = new int[h][w];
        memo = new int[h][w][31];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                    Arrays.fill(memo[i][j], 100000000);
            }
        }
        dfs(0, 0, k, 0, 0);
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < 12; i++) {
//            min = Math.min(min, memo[h - 1][w - 1][i]);
//            System.out.printf("%d ", memo[3][2][i]);
//        }
//        System.out.println();
        if (min > 100000000) {
            System.out.println(-1);
            return;
        }

        System.out.println(min);
    }
}
