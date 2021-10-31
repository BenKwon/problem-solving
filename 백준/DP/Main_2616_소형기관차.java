package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2616_소형기관차 {
    static int n,k;
    static int[] trains,sum;
    static int[][] dp;
    static int[][] visit;
    public static int dfs(int cur,int level){
        if(visit[level][cur] == 1) return dp[level][cur];
        if(level == 3) return sum[cur + k - 1] - sum[cur - 1];
        int result = 0;
        int start = cur + k - 1;
        for (int i = start; i + k <= n; i++) {
            result = Math.max(result, dfs(i, level + 1));
        }

        dp[level][cur] = result + (sum[cur + k - 1] - sum[cur - 1]);
        visit[level][cur] = 1;
        return dp[level][cur];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        trains = new int[n + 1];
        sum = new int[n + 1];
        visit = new int[4][n + 1];
        dp = new int[4][n+1];
        StringTokenizer st= new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            trains[i] = Integer.parseInt(st.nextToken());
            sum[i] = trains[i] + sum[i- 1];
        }
        k = Integer.parseInt(br.readLine());

        for(int i = 1 ; i + k <= n; i++){
            dfs(i, 1);
        }
        int sol = Integer.MIN_VALUE;
        for(int i = 1 ; i + k <= n; i++){
            sol = Math.max(sol, dp[1][i]);
        }
        System.out.println(sol);

    }
}
