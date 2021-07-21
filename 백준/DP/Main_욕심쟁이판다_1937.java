package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_욕심쟁이판다_1937 {
    public static int n;
    public static int[][] dp;
    public static int[][] graph;
    public static int[] xmove = new int[]{-1, 1, 0, 0};
    public static int[] ymove = new int[]{0, 0, -1, 1};
    public static int[][] visit;

    public static int dfs(int row, int col){
        int max = 0;
        if(visit[row][col] == 1){
            return dp[row][col];
        }
        for(int i = 0 ; i < 4 ; i++){
            int nrow = row + xmove[i];
            int ncol = col + ymove[i];
            if(nrow < 0 || nrow >=n || ncol < 0 || ncol >= n) continue;
            if(graph[nrow][ncol] <= graph[row][col]) continue;
//            System.out.printf("row : %d , col : %d\n", row,col);
//            System.out.printf("nrow : %d , ncol : %d\n", nrow,ncol);
//            System.out.printf("visit[%d][%d] : %d\n",nrow,ncol, visit[nrow][ncol]);
            if(visit[nrow][ncol] == 1){
                max = Math.max(max, dp[nrow][ncol]);
            }else{
                max = Math.max(max, dfs(nrow, ncol));
            }
        }
        visit[row][col] = 1;
        return dp[row][col] += max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        dp = new int[n][n];

        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n ; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] =1 ;
            }
        }

        visit = new int[n][n];

        int max = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++){
                max = Math.max(max,dfs(i, j));
            }
        }
//        for(int i = 0 ; i < n ; i++) {
//            System.out.println();
//            for(int j = 0 ; j < n ; j++){
//                System.out.printf("%d ",dp[i][j]);
//            }
//        }
        System.out.println(max);
    }
}
