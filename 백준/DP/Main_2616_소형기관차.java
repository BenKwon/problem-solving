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
//    static int[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        trains = new int[n + 1];
        sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            trains[i + 1] = Integer.parseInt(st.nextToken());
            sum[i + 1] = sum[i] + trains[i + 1];
        }
        k = Integer.parseInt(br.readLine());
        dp = new int[n + 1][4];
        dp[k][1] = sum[k];
        for (int i = k + 1; i <= n; i++) {
            for (int j = 1; j <= 3; j++) {
                if(i < j * k) break;
                else if(j == 1){
                    int newValue = sum[i] - sum[i - k];
                    dp[i][j] = Math.max(dp[i - 1][j], newValue);
                }else { //else if (j == 2 || j == 3)
                    int newValue = (sum[i] - sum[i - k]) + dp[i - k][j - 1];
                    dp[i][j] = Math.max(dp[i - 1][j], newValue);
                }
//                System.out.printf("dp[%d][%d] : %d\n",i,j,dp[i][j]);
            }
        }
        System.out.println(dp[n][3]);

    }
}
