package 백준.DP;

import java.io.*;
import java.util.StringTokenizer;

public class 스티커_9465_Main {
    public static int[][] graph;
    public static int n;
    public static int[][] dp;
    public static int find(int x, int y){
        if(x < 0 || x > 1 || y >= n || y < 0) return 0;
        if(dp[x][y] != 0) return dp[x][y];
        if(x == 0){
            return dp[x][y] = graph[x][y] + Math.max(find(1, y + 1), find(1, y + 2));
        }else if(x == 1){
            return dp[x][y] = graph[x][y] + Math.max(find(0, y + 1), find(0, y + 2));
        }else{
            return 0;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < test; t++){
            n = Integer.parseInt(br.readLine());
            graph = new int[2][n];
            dp = new int[2][n];
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < 2 ; i++){
                String str = br.readLine();
                StringTokenizer st = new StringTokenizer(str);
                for(int j = 0 ; j < n ;  j++){
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp[1][n - 1] = graph[1][n - 1];
            dp[0][n - 1] = graph[0][n - 1];
            int sol = Math.max(find(0, 0), find(1, 0));
            sb.append(sol +"\n");
            System.out.println(sb);
        }
    }
}
