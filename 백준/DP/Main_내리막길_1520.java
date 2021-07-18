package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_내리막길_1520 {
    public static int[][] graph;
    public static int n, m; // m = row , n = col
    public static int[][] visit;
    public static int[][] dp;
    public static int sol = 0;
    public static int[] row_move = new int[]{-1, 1, 0, 0};
    public static int[] col_move = new int[]{0, 0, -1, 1};

    public static int dfs(int x, int y) {
        if(x == m- 1 && y == n-1){
            return 1;
        }
//        if (x == m - 2 && y == n-1) {
//            return dp[m - 2][n-1];
//        }
//        if (x == m -1&& y == n - 2) {
//            return dp[m-1][n-2];
//        }
        int value = 0;
        visit[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int mx = x + row_move[i];
            int my = y + col_move[i];
            if(mx < 0 || mx >= m || my < 0|| my >=n) continue;
            if(graph[mx][my] >= graph[x][y]) continue;
            if(visit[mx][my] == 1) {
                value += dp[mx][my];
                continue;
            }
            value += dfs(mx, my);
        }
        return dp[x][y] = value;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[m][n];
        visit = new int[m][n];
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(m == 1 && n ==1){
            System.out.println(1);
            return;
        }
//        if(m == 1){
//            if (graph[m-1][n - 2] > graph[m-1][n-1])
//                dp[m-1][n - 2] = 1;
//            visit[m-1][n-2] = 1;
//        }
//        else if(n == 1){
//            if (graph[m - 2][n-1] > graph[m-1][n-1])
//                dp[m - 2][n-1] = 1;
//            visit[m-2][n-1] = 1;
//        }else{
//            if (graph[m-1][n - 2] > graph[m-1][n-1])
//                dp[m-1][n - 2] = 1;
//            visit[m-1][n-2] = 1;
//            if (graph[m - 2][n-1] > graph[m-1][n-1])
//                dp[m - 2][n-1] = 1;
//            visit[m-2][n-1] = 1;
//        }




        System.out.println(dfs(0, 0));
//        for(int i = 0 ; i < m ; i++){
//            for(int j = 0 ; j < n ; j++){
//                System.out.printf("%d ", dp[i][j]);
//            }
//            System.out.println();
//        }
    }
}
