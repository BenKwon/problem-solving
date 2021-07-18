package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class 평범한배낭_12865_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] items = new int[n][2];
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[i] = new int[]{weight, value};
        }
        for(int j = 0 ; j <= k;j++){
            if(j >= items[0][0]){
                dp[0][j] = items[0][1];
            }
        }
        for(int i = 1; i < n ;i++){
            int[] item = items[i];
            for(int j = 1; j <= k ; j++){
                if(j < item[0]){ // 이번 아이템이 담을수 있는 무게보다 무거울때
                    dp[i][j] = dp[i - 1][j];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item[0]] + item[1]);
                }
            }
        }
        System.out.println(dp[n-1][k]);
    }
}
