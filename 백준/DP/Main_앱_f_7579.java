package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_앱_f_7579 {
    public static int n, m;
    public static int[] task_m, task_c;
    public static int[] dp;
    public static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        task_m = new int[n];
        task_c = new int[n];
        dp = new int[10001];
        Arrays.fill(dp,-1);
        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            task_m[i] = Integer.parseInt(st.nextToken());
            task_c[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int cost = task_c[i];
            for(int j = 10000 ; j >= cost;j--){
                if(dp[j- cost] != - 1){
                    if(dp[j-cost] + task_m[i] > dp[j]){
                        dp[j] = dp[j - cost] + task_m[i];
                    }
                }
            }

            if(dp[cost] < task_m[i]) dp[cost] = task_m[i];
        }
        for (int i = 0; i < 10001; i++) {
            if(dp[i] >= m) {
                System.out.println(i);
                return;
            }
        }

    }

}
