package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1563_개근상 {
    static int n;
    static int[][][] dp;
    static int[][][] visit;
    public static int dfs(int day,int o, int l,int aBeforeOne, int aBeforeTwo){
        if(day == n) return 1;
        if(visit[day][l][aBeforeOne + aBeforeTwo] == 1) return dp[day][l][aBeforeOne + aBeforeTwo];
        int count = 0;
        // 출석
        count += dfs(day + 1, o + 1, l, 0, 0)% 1000000;
        count %= 1000000;
        // 지각
        if(l + 1 < 2){
            count += dfs(day + 1, o, l + 1, 0, 0)% 1000000;
            count %= 1000000;

        }
        // 결석
        if(aBeforeOne == 1 && aBeforeTwo == 1){
            //do nothing
        }else if(aBeforeOne == 1){
            count += dfs(day + 1, o, l , 1, 1)% 1000000;
            count %= 1000000;

        }else{
            count += dfs(day + 1, o, l , 1, 0)% 1000000;
            count %= 1000000;

        }
        dp[day][l][aBeforeOne + aBeforeTwo] = count % 1000000;
        visit[day][l][aBeforeOne + aBeforeTwo] = 1;
        return count;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[1001][2][3];
        visit = new int[1001][2][3];
        System.out.println(dfs(0, 0, 0, 0, 0)% 1000000000);
    }
}
