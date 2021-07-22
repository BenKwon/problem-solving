package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_내려가기_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][3];
        int[][] min_dp = new int[n][3];
        int[][] max_dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }
        min_dp[n - 1][0] = graph[n-1][0];
        min_dp[n - 1][1] = graph[n-1][1];
        min_dp[n - 1][2] = graph[n-1][2];

        max_dp[n - 1][0] = graph[n - 1][0];
        max_dp[n - 1][1] = graph[n - 1][1];
        max_dp[n - 1][2] = graph[n - 1][2];
        for(int i = n - 2; i >= 0 ; i--){
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < 3; j++) {
//                min_dp[i][j] max_dp[i][j]
                if(j==0){
                    min_dp[i][j] = Math.min(min_dp[i + 1][j], min_dp[i + 1][j + 1]) + graph[i][j];
                    max_dp[i][j] = Math.max(max_dp[i + 1][j], max_dp[i + 1][j + 1]) + graph[i][j];

                }else if(j==1){
                    min_dp[i][j] = Math.min(Math.min(min_dp[i + 1][j], min_dp[i + 1][j + 1]),min_dp[i+1][j-1]) + graph[i][j];
                    max_dp[i][j] = Math.max(Math.max(max_dp[i + 1][j], max_dp[i + 1][j + 1]),max_dp[i+1][j-1]) + graph[i][j];
                }else if(j==2){
                    min_dp[i][j] = Math.min(min_dp[i + 1][j-1], min_dp[i + 1][j]) + graph[i][j];
                    max_dp[i][j] = Math.max(max_dp[i + 1][j-1], max_dp[i + 1][j]) + graph[i][j];
                }
            }
        }

        int min_sol = Math.min(min_dp[0][2],Math.min(min_dp[0][0], min_dp[0][1]));
        int max_sol = Math.max(max_dp[0][2],Math.max(max_dp[0][0], max_dp[0][1]));
        System.out.printf("%d %d", max_sol,min_sol);
    }
}
