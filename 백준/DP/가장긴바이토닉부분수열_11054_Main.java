package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장긴바이토닉부분수열_11054_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        int[] left_dp = new int[n];
        int[] right_dp = new int[n];
        //왼쪽에서 오른쪽으로 최장 증가수열 dp 배열 맏늘기
        left_dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for(int j = i - 1 ; j >= 0 ;j --){
                if(seq[i] > seq[j] && left_dp[j] > max){
                    max = left_dp[j];
                }
            }
            left_dp[i] = max + 1;
        }
        //오른쪽에서 왼쪽순으로 최장 증가수열 dp 배열만들기
        right_dp[n-1] = 1;
        for(int i = n-2 ; i >= 0 ; i--){
            int max =0;
            for(int j = i +1 ; j < n; j++){
                if(seq[i] > seq[j] && right_dp[j] > max){
                    max = right_dp[j];
                }
            }
            right_dp[i] = max + 1;
        }

        int sol = 0; //최장 바이토닉 부분수열
        for(int i = 0 ; i  < n ; i++){
            int bitonic = left_dp[i] + right_dp[i] - 1;
            if(sol < bitonic){
                sol = bitonic;
            }
        }
        System.out.println(sol);
    }
}
