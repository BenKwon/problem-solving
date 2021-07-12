package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 포도주시식_2156_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] juice = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            juice[i] = Integer.parseInt(br.readLine());
        }
        if(n == 1) {
            System.out.println(juice[0]);
            return;
        }
        if(n==2){
            System.out.println(juice[0] + juice[1]);
            return;
        }
        dp[0] = juice[0];
        dp[1] = juice[0] + juice[1];
        dp[2] = Math.max(dp[0] + juice[2], juice[1] + juice[2]);
        dp[2] = Math.max(dp[2], juice[0] + juice[1]);
        for(int i = 3 ; i < n ; i++){
            dp[i] = Math.max(dp[i - 1], Math.max(juice[i] + dp[i - 2],
                    juice[i] + juice[i - 1] + dp[i - 3]));
        }
        System.out.println(dp[dp.length-1]);


    }
}
