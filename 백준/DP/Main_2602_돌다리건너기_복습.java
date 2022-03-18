package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2602_돌다리건너기_복습 {
    static String map;
    static int n,m;
    static String[] bridge = new String[2]; // [0] : devil , [1] : angel
    static int[][][] dp;
    static int[][][] visit;
    static int dfs(int type, int curIndex, int mapIdx){
        if(mapIdx == m - 1) return 1;
        if(visit[type][curIndex][mapIdx] == 1) return dp[type][curIndex][mapIdx];
        int nextType = type == 0 ? 1 : 0;
        char nextDest = map.charAt(mapIdx + 1);
        int total = 0;
        for (int i = curIndex + 1; i < n; i++) {
            if(bridge[nextType].charAt(i) == nextDest){
                total += dfs(nextType, i, mapIdx + 1);
            }
        }
        dp[type][curIndex][mapIdx] = total;
        visit[type][curIndex][mapIdx] = 1;
        return total;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = br.readLine();
        bridge[0] = br.readLine();
        bridge[1] = br.readLine();
        n = bridge[0].length();
        m = map.length();
        dp = new int[2][n][m];
        visit = new int[2][n][m];
        int answer = 0;
        //악마 부터 시작
        for (int i = 0; i < n; i++) {
            if(bridge[0].charAt(i) == map.charAt(0)){
                answer += dfs(0, i, 0);
            }
        }
        //천사부터 시작
        for (int i = 0; i < n; i++) {
            if(bridge[1].charAt(i) == map.charAt(0)){
                answer += dfs(1, i, 0);
            }
        }
        
        System.out.println(answer);
    }
}
