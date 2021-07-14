package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 오르막수_11057_Main {
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
               for(int k = j ; k < 10; k++){
                   dp[i][j] += dp[i + 1][k];
               }
                dp[i][j] %= 10007;
            }
        }
        long sol = 0;
        for(int i = 0; i < 10;i++){
            sol += dp[0][i];
        }

        System.out.println(sol%10007);


    }
}
