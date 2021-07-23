package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * %
 */
public class 최장공통부분수열_9251_f_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s2.length;
        int m = s1.length;
        int[][] dp = new int[n][m];

        int max = 0;
        for(int i = 0 ; i < m ; i++){
            if(s2[0] == s1[i]){
                dp[0][i] = 1;
                max = 1;
            }
            if(max > 0) dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            char target = s2[i];
            boolean check = false;
            for (int j = 0; j < m; j++) {
                if(j == 0){
                    dp[i][j] = dp[i - 1][j];
                    if(target == s1[j]) {
                        if(dp[i][j] > 0) dp[i][j] = 1;
                    }
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    if(target == s1[j]) {
                        dp[i][j]++;
                    }
                }

            }
        }
    }
}
