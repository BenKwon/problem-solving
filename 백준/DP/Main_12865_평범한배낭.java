package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12865_평범한배낭 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][k + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            for (int w = 0; w <= k; w++) dp[i][w] = dp[i - 1][w];
            for (int w = weight; w <= k; w++) {
                int index = w - weight;
                if(dp[i - 1][index] < dp[i - 1][w] + value){
                    dp[i][index] = dp[i - 1][w] + value;
                }else{
                    dp[i][index] = dp[i - 1][index];
                }
                max = Math.max(max, dp[i][index]);
            }
        }

        System.out.println(max);
    }
}
