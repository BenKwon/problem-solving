package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_동전2_2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];
        int[] dp = new int[10001];
        for(int i = 1;  i<= k ;i++){
            dp[i] = -1;
        }
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            coin[i] = Integer.parseInt(st.nextToken());
            if(coin[i] <= 10000){
                dp[coin[i]] = 1;

            }
        }

        for (int i = 1; i <= k; i++) {
            int min = 1000000;
//            System.out.println("------------------------");
            for (int j = 0; j < n; j++) {
//                System.out.printf("i : %d , coin[%d] : %d\n",i,j,coin[j]);
                if(i - coin[j] >= 0){
                    if(dp[i - coin[j]] == -1){
                        continue;
                    }
//                    System.out.println("hit");
                    min = Math.min(min, 1 + dp[i - coin[j]]);
                }
            }
            if(min == 1000000) dp[i]  = -1;
            else dp[i] = min;
        }
//        for (int i : dp) {
//            System.out.println("i = " + i);
//
//        }
        System.out.println(dp[k]);
    }
}
