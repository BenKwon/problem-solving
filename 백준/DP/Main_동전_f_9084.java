package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_동전_f_9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] coin = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            int goal = Integer.parseInt(br.readLine());
            int[][] dp = new int[n + 1][goal + 1];
            for (int i = 1; i <= n; i++) {
                dp[i][0] = 1;
                int c = coin[i];
                for (int j = 1; j <= goal; j++) {
                    if(c == j){
                        dp[i][j] = dp[i - 1][j] + 1;
                    }else if(c < j){
                        dp[i][j] = dp[i - 1][j] + dp[i][j - c];
                    }else{
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            System.out.println(dp[n][goal]);
        }
    }
}
