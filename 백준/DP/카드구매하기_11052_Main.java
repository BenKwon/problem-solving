package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드구매하기_11052_Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] p = new int[n+1];
        int[] dp = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= n ; i++){
            p[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = p[1];

        for(int i = 2 ; i <= n ;i++){
            int max = 0;
            for(int j = 1; j <= i/2; j++){
                int tmp = dp[i - j] + dp[j];
                max = Math.max(max, tmp);
            }
            max = Math.max(max, p[i]);
            dp[i] = max;
        }

        System.out.println(dp[n]);
    }
}
