package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1311_할일정하기1 {
    static int n;
    static int[][] connectInfo;
    static int[][] dp;
    static int solution = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        connectInfo = new int[n][n];
        dp = new int[n][1<<n];
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                connectInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.fill(dp[n-1],Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            dp[0][1 << i] = connectInfo[0][i];
        }
        int[] tmp = new int[n];
        for (int i = 1; i < n; i++) { //사람
            for (int j = 0; j < n; j++) { // 일
                tmp = new int[n];
                tmp[j] = 1;
                recur(j, i + 1,1, tmp);
                tmp[j] = 0;
            }
//            System.out.println("--------------------------");
        }

        solution = Arrays.stream(dp[n - 1]).min().getAsInt();
        System.out.println(solution);
    }
    static void recur(int idx,int tot, int count, int[] tmp){
        if(count == tot){
            int mask = 0;
            for (int i = 0; i < tmp.length; i++) {
                if(tmp[i] == 1){
                    mask |= 1 << i;
                }
            }
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < tmp.length; i++) {
                if(tmp[i] == 1){
                    int newMask = mask ^ (1 << i);
//                    System.out.println("newMask = " + newMask);
//                    System.out.printf("%d \n",dp[tot - 2][newMask] + connectInfo[tot - 1][i]);
                    ans = Math.min(ans, dp[tot - 2][newMask] + connectInfo[tot - 1][i]);
                }
            }
            dp[tot - 1][mask] = ans;
            if(tot == n){
                solution = Math.min(ans, solution);
            }
//            System.out.println("ans = " + ans);
//            System.out.println();
            return;
        }

        for (int j = idx + 1; j < n; j++) {
            if(tmp[j] == 1) continue;
            tmp[j] = 1;
            recur(j , tot,count + 1,tmp);
            tmp[j] = 0;
        }


    }



}
