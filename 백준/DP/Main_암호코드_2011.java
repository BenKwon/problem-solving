package 백준.DP;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.io.*;

public class Main_암호코드_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String crypto = br.readLine();
        char[] seq = crypto.toCharArray();
        int n = seq.length;
        int[] dp = new int[n];
        dp[0] = 1;

        int start_idx = 0;
//        while(true){
//            if (seq[start_idx + 1] != '0') {
//                start_idx++;
//                break;
//            }
//            start_idx++;
//        }
        if(seq[start_idx] == '0'){
            System.out.println(0);
            return;
        }
        if(n ==1){
            System.out.println(1);
            return;
        }
        if(seq[start_idx + 1] == '0'){
            if(seq[start_idx] >= '3'){
                System.out.println(0);
                return;
            }
        }
        dp[start_idx] = 1;
        if (seq[start_idx] == '1') {
            if(seq[start_idx+1] == '0') dp[start_idx + 1] = 1;
            else dp[start_idx + 1] = 2;
        } else if (seq[start_idx] == '2') {
            if (seq[start_idx+1] <= '6') dp[start_idx + 1] = 2;
            else dp[start_idx + 1] = 1;
            if(seq[start_idx+1] == '0') dp[start_idx + 1] = 1;

        }else{
            dp[start_idx + 1] = 1;
        }

//        System.out.println("START INDEX : " +start_idx);

        for (int i = start_idx + 2; i < n; i++) {
            if(seq[i] == '0'){
                if(seq[i-1] >= '3' || seq[i-1] == '0'){
                    System.out.println(0);
                    return;
                }
            }
            if (seq[i - 1] == '1') {
                if(seq[i] == '0'){
                    dp[i] = dp[i - 2]%1000000 ;
                }else{
                    dp[i] = dp[i - 1]%1000000+ dp[i - 2]%1000000 ;
                }
            } else if (seq[i - 1] == '2') {
                if(seq[i] == '0'){
                    dp[i] = dp[i - 2]%1000000 ;
                }
                else{
                    if (seq[i] > '6') dp[i] = dp[i - 1];
                    else dp[i] = dp[i - 1]%1000000 + dp[i - 2]%1000000;
                }
            }  else {
                dp[i] = dp[i - 1]%1000000;
            }
            /**
             * else if(seq[i-1] == '0') {
             *                 dp[i] = dp[i - 1]%1000000;
             *             }
             */
        }
//        System.out.println();
//        for(int i = 0 ; i < n ; i++){
//            System.out.printf("%d " , dp[i]);
//        }
//        System.out.println();

        System.out.println(dp[n-1]%1000000);

    }
}
