package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 첫번째부터 마지막 집까지 색깔을 칠해야한다.
 * 첫번째집과 마지막집도 RGB거리1과는 다르게 색깔이 달라야한다.
 * 첫번째집을 각각 red, green, blue로 따로 따로 놓고 dfs를 돌린다.
 * 다만 dp에 값을 3개씩 저장한다.
 * 첫번재
 */
public class Main_17404_RGB거리2 {
    public static int[][] cost;
    public static int n;
    public static int[][][] dp; // [n][3][3] = [level, 현재 레벨에 들어갈 rgb] = [0 , 1 ,2] 각 rgb가 n-2 레벨에 있을때 최솟값
    public static final int large = 20000000;
    public static int[][] visit;
    public static int[] dfs(int level, int cur_col) {
        if (level == n - 1) {
            int[] result = new int[3];
            if (cur_col == 0) {
                result = new int[]{cost[level][0], large, large};
            } else if (cur_col == 1) {
                result = new int[]{large, cost[level][1], large};
            } else {
                result = new int[]{large, large, cost[level][2]};
            }
            dp[level][cur_col] = result.clone();
            return dp[level][cur_col];
        }
        if(visit[level][cur_col] == 1) return dp[level][cur_col];
        int[] result1;
        int[] result2;
        if (cur_col == 0) {//R
            result1 = dfs(level + 1, 1);
            result2 = dfs(level + 1, 2);
        } else if (cur_col == 1) {//G
            result1 = dfs(level + 1, 0);
            result2 = dfs(level + 1, 2);
        } else{//B (else if cur_col == 2)
            result1 = dfs(level + 1, 0);
            result2 = dfs(level + 1, 1);
        }
        int[] result = new int[3];
        result[0] = Math.min(result1[0], result2[0]) + cost[level][cur_col];
        result[1] = Math.min(result1[1], result2[1]) + cost[level][cur_col];
        result[2] = Math.min(result1[2], result2[2]) + cost[level][cur_col];
        dp[level][cur_col] = result.clone();
        visit[level][cur_col] = 1;
        return dp[level][cur_col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][3];
        dp = new int[n][3][3];
        visit = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], large);
            }
        }
        dfs(0, 0);
        dfs(0, 1);
        dfs(0, 2);
//        for (int[] ints : dp[0]) {
//            System.out.println();
//            for (int anInt : ints) {
//                System.out.println("anInt = " + anInt);
//            }
//        }
        int[] r = dp[0][0];
        int[] g = dp[0][1];
        int[] b = dp[0][2];
        //맨앞을 red로 칠할때
        int pass = 0;
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i != pass) {
                answer = Math.min(answer, r[i]);
            }
        }
        //맨앞을 green으로 칠할때
        pass = 1;
        for (int i = 0; i < 3; i++) {
            if (i != pass) {
                answer = Math.min(answer, g[i]);
            }
        }
        //맨앞을 blue로 칠할때
        pass = 2;
        for (int i = 0; i < 3; i++) {
            if (i != pass) {
                answer = Math.min(answer, b[i]);
            }
        }
        System.out.println(answer);
    }
}
