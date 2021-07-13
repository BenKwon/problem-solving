package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰증가부분수열_11055_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];
        int[][] dp = new int[n][2];
        String seq_str = br.readLine();
        StringTokenizer st = new StringTokenizer(seq_str);
        for(int i = 0 ; i < n ; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }
        dp[0][0] = 1;
        dp[0][1] = seq[0];
        int max = seq[0];
        for(int i = 1 ; i < n ; i++){
            boolean flag = true;
            int tmp_max = 0;
            for(int j = i - 1 ; j >= 0 ;j--){
                if(seq[j] < seq[i]){
                    if(tmp_max < dp[j][1]){
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1] + seq[i];
                        tmp_max = dp[j][1];
                    }
                    flag = false;
                }
            }
            if(flag){
                dp[i][0] = 1;
                dp[i][1] = seq[i];
            }
            if(max < dp[i][1]) max = dp[i][1];
        }
        System.out.println(max);
    }
}
