package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14728_벼락치기 {
    static int n,t;
    static int[][] stages;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        stages = new int[n + 1][2];
        dp = new int[n + 1][10001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            stages[i] = new int[]{k, s};
        }
        for (int i = n - 1; i >= 0; i--) {
            int k = stages[i][0];
            int s = stages[i][1];
            for (int j = t; j >= 0; j--) {
//                System.out.printf("j : %d, t-k : %d\n",j,t-k);
                if(j > t - k ){
                    dp[i][j] = dp[i + 1][j];
                }else{
                    dp[i][j] =
                            Math.max(
                              dp[i + 1][j],
                              s + dp[i+1][j + k]
                            );
                }
            }
        }
        int answer = 0;
        for (int i = 0; i <= t; i++) {
            answer = Math.max(answer, dp[0][i]);
        }
        System.out.println(answer);
    }
}
