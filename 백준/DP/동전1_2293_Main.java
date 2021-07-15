package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * ★
 */
public class 동전1_2293_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[k+1];
//        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> coin_weight = new ArrayList<>();
        for(int i = 0 ; i < n ; i ++){
            coin_weight.add(Integer.parseInt(br.readLine()));
//            map.put(coin_weight.get(i), 1);
        }
        Collections.sort(coin_weight);
        dp[0] = 1;
        for(int i = 0; i < n ; i++){
            for(int c = coin_weight.get(i)  ; c <= k; c++){
//                if(c - coin_weight.get(i)>= 0){
                    dp[c] += dp[c - coin_weight.get(i)];
//                }
            }
        }
        System.out.println(dp[k]);
    }
}
