package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2666_벽장문의이동 {
    static int n, k;
    static int[] seq;
    static int[][][] dp;
    static int[][][] visit;
    public static int dfs(int level, int c1, int c2) {
        if(level == k) return 0;
        if(visit[level][c1][c2] == 1) {
//        System.out.printf("result : %d\n", result);
            return dp[level][c1][c2];
        }
        int next = seq[level + 1];
        int result = next_pos(c1, c2, next);
        int tmp = 0;
//        System.out.printf("level : %d ,next: %d, [%d, %d] \n", level,next, c1, c2);
//        System.out.printf("result : %d\n", result);
        if (result == 0) {
            tmp = dfs(level + 1, c1, c2);
        } else if (result == 1) {
            tmp = Math.min(dfs(level + 1, c1, next) + Math.abs(c2 - next), dfs(level + 1, next, c2) + Math.abs(c1 - next));
        } else if (result == 2) {
            tmp = dfs(level + 1, next, c2) + Math.abs(c1 - next);
        } else if (result == 3) {
            tmp = dfs(level + 1, c1, next) + Math.abs(c2 - next);
        }

        dp[level][c1][c2] = tmp;
        visit[level][c1][c2] = 1;

        return dp[level][c1][c2];
    }

    public static int next_pos(int c1, int c2, int next) {
        if (c1 == next || c2 == next) return 0; //안움직여도댐
        else if (c1 < next && next < c2) return 1; //중간에 있음
        else if (next < c1) return 2; //c1왼쪽에 있음
        else if (next > c2) return 3; //c2 왼쪽에 있음
        else return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] c = new int[2];
        c[0] = Integer.parseInt(st.nextToken());
        c[1] = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        seq = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            seq[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[k + 1][n + 1][n + 1];
        visit = new int[k + 1][n + 1][n + 1];
        Arrays.sort(c);
        System.out.println(dfs(0, c[0], c[1]));
    }
}
