package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 쉬운계단수_10844_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] dp = new long[n][10];
        for(int i = 0 ; i < 10 ; i++){
            dp[n-1][i] = 1;
        }

        for(int i = n-2 ; i >= 0 ; i--){
//            System.out.println("==================");
//            System.out.println("i = " + i);
            for(int j = 0 ; j < 10 ; j++){
                if(j == 0) dp[i][j] = dp[i + 1][1];
                else if(j == 9) dp[i][j] = dp[i + 1][8];
                else dp[i][j] = dp[i+1][j-1] + dp[i+1][j+1];
//                System.out.printf("dp[%d][%d] : %d\n",i,j,dp[i][j]);
                dp[i][j] %= 1000000000;

            }
        }

        long sol = 0;
        for(int i = 1; i < 10;i++){
            sol += dp[0][i];
        }

        System.out.println(sol%1000000000);

    }
}
