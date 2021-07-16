package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class 제곱수의합_1699_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        ArrayList<Integer> pow = new ArrayList<>();
        for(int i = 1 ; Math.pow(i,2) <= n; i++){
            double pow1 = Math.pow(i, 2);
            pow.add((int) pow1);
            dp[(int) pow1] = 1;
        }
        int checkpoint = 1;
        for(int i = 2 ; i <= n; i++){
            int pow_idx = 0;
            int min = 1000001;
            for(int j = pow.get(pow_idx); j <= i; j = pow.get(pow_idx)){
                int min1 = dp[i - j] + dp[j];
                if(min1 < min){
                    min = min1;
                }
                if(++pow_idx >= pow.size()) break;
            }
            dp[i] = min;
        }
        System.out.println(dp[n]);
    }
}
