package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 비트 마스킹에 대해서 잘 몰라서 인터넷에서 참고를 많이하였다.
 */
public class Main_외판원순회_f_2098 {
    public static int[][] map;
    public static int[][] dp;
    public static int INF = 16000001;
    public static int n;
    public static int dfs(int node, int visit) {
        if(visit == (1 << n) - 1){
            if(map[node][0] != 0){
                return map[node][0];
            } else return INF;
        }
        if(dp[node][visit] != INF) return dp[node][visit];

        for (int i = 0; i < n; i++) {
            if(map[node][i] == 0 || (visit & (1 << i)) > 0) continue;
            dp[node][visit] = Math.min(dp[node][visit], dfs(i, visit | (1 << i)) + map[node][i]);
        }
        return dp[node][visit];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][(1 << n)];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],INF);
        }

        int sol = dfs(0, 1);
        System.out.println(sol);

    }
}
