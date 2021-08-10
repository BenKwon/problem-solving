package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_알파벳_1987 {
    public static int n, m;
    public static int[][] graph;
    public static int[] row_move = new int[]{-1, 1, 0, 0};
    public static int[] col_move = new int[]{0, 0, -1, 1};
    public static int max = Integer.MIN_VALUE;
    public static int dfs(int row, int col, int visit, int count) {

        for (int i = 0; i < 4; i++) {
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];
            if(n_row < 0 || n_row >= n || n_col < 0 || n_col >= m) continue;
            if(((1 << graph[n_row][n_col]) & visit) != 0) continue;
            dfs(n_row, n_col, visit | (1 << graph[n_row][n_col]), count + 1);
        }
        max = Math.max(max, count);
        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j) - 65;
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println();
//            for (int j = 0; j < m; j++) {
//                System.out.printf("%d ", graph[i][j]);
//            }
//        }
        dfs(0, 0, 1 << graph[0][0], 1);
        System.out.println(max);
//        System.out.println("a = " + a);

    }

}
