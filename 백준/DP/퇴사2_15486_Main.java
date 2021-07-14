package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 퇴사2_15486_Main {
    public static int[] dp;
    public static int[] p;
    public static int[] t;
    public static int n,count;
    public static int find(int x){
        if(x > n) return 0;
        if(dp[x] != -1) return dp[x];
        dp[x] = Math.max(find(x + t[x]) + p[x], find(x + 1));
        return dp[x];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        t = new int[n+1];
        p = new int[n+1];
        dp = new int[n+1];
        for(int i = 1 ; i <=  n ; i ++){
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            if(i + t[i] - 1> n) p[i] =0;
            else p[i] = Integer.parseInt(st.nextToken());
            dp[i] = -1;
        }

        dp[n] = p[n];
        if(n == 1){
            System.out.println(p[1]);
            return;
        }

        find(1);
        System.out.println(dp[1]);



    }
}
