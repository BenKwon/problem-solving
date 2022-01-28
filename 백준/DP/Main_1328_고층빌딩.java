package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1328_고층빌딩 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[][][] dp = new int[n + 1][n + 1][n + 1];
        dp[1][1][1] = 1;

        for (int i = 2; i <= n; i++) {
//            System.out.println("---------------------");
//            System.out.println("i = " + i);
            for (int left = 1; left <= n; left++) {
                for (int right = 1; right <= n; right++) {
                    if (dp[i - 1][left][right] == 0) continue;
//                    System.out.printf("dp[%d][%d][%d]\n",i-1,left,right);
                    dp[i][left + 1][right] += dp[i - 1][left][right]%1000000007;
                    dp[i][left + 1][right] %= 1000000007;
                    dp[i][left][right + 1] += dp[i - 1][left][right]%1000000007;
                    dp[i][left][right + 1] %= 1000000007;
                    for (int j = 0; j < i - 2; j++) {
                        dp[i][left][right] += dp[i - 1][left][right]%1000000007;
                        dp[i][left][right] %= 1000000007;
                    }
                }
            }
        }
        System.out.println(dp[n][l][r]%1000000007);


    }
}
