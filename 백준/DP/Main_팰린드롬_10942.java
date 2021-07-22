package 백준.DP;

import java.io.*;
import java.util.StringTokenizer;

public class Main_팰린드롬_10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[n];
        int[][] dp = new int[n][n];

        for(int i = 0 ; i < n ; i++){
            seq[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = 1;
        }
        for(int i = 0; i < n-1 ;i++){
            if(seq[i] == seq[i+1]) dp[i][i+1] = 1;
        }

        for(int i = 2 ; i < n ; i++){
            int row = 0;
            for(int j = i ; j < n ;j++){
//                dp[row][j] 를 구해야한다
                if(seq[row] == seq[j]){
                    if(dp[row+1][j-1] == 1){
                        dp[row][j] = 1;
                    }
                }
                row++;
            }
        }

        int m = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            bw.write(dp[Integer.parseInt(st.nextToken()) -1][Integer.parseInt(st.nextToken()) -1]
             +  "\n");

        }
        bw.flush();
        bw.close();
        br.close();


    }
}
