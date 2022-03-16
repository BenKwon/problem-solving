package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7579_앱_repeat {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] dp = new int[10001];
        int[][] apps = new int[n][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            apps[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            apps[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int[] app = apps[i];
            int mem = app[0];
            int cost = app[1];
            for (int j = 10000; j >= 0; j--) {
                if(j- cost < 0 || dp[j-cost] == 0) continue;
                if(dp[j] < dp[j - cost] + mem){
                    dp[j] = dp[j - cost] + mem;
                }
            }
            if(dp[cost] < mem) dp[cost] = mem;

        }
        int ans =0 ;
        for (int j = 0; j <= 10000; j++) {
            if(dp[j] >= m ){
                ans = j;
                break;
            }
        }
        System.out.println(ans);
    }
}
