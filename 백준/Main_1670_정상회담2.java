package 백준;

import 백준.DP.가장긴바이토닉부분수열_11054_Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1670_정상회담2 {
    static int n;
    static long[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[10001];
        dp[2] = 1;
        dp[0] = 1;
        dp[4] = 2;
        for (int i = 6; i <= n; i += 2) {
            int start = 1;
            int end = 2;
            boolean stop = false;
            int count = 0;
//            System.out.println("i = " + i);
            while(!stop){
                if(end == i) stop = true;
//                System.out.printf("dp[%d] * dp[%d] \n",(end-start) -1,i-((end-start)-1)-2);
//                System.out.printf("%d * %d \n",dp[(end-start) -1],dp[i-((end-start)-1)-2]);
                dp[i] += (dp[(end-start) -1]%987654321) * (dp[i-((end-start)-1)-2]%987654321);
//                System.out.println("count = " + count);
                dp[i] %= 987654321;
                end = (end + 2);
            }
        }
        System.out.println(dp[n]);
    }
}
