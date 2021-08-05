package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_계단수_1562 {
    static int n;
    static long[][][] dp; // [현재길이][맨앞][맨뒤]
    static int sol = 0;
    static long[][][] visit;

    static long dfs(int level, int cur, int bitmask) {
        if (visit[level][cur][bitmask] == 1) return dp[level][cur][bitmask]%1000000000;
        if (level == n) {
            int possible = bitmask ^ ((1 << 10) - 1); // 결과가 0이면 정답
            visit[level][cur][bitmask] = 1;
            if (possible == 0) {
                return dp[level][cur][bitmask] = 1;
            } else {
                return dp[level][cur][bitmask] = 0;
            }
        }



        int sum = 0;
        if (cur == 0) {
            sum += dfs(level + 1, cur + 1, bitmask | (1 << (cur + 1))) % 1000000000;
        } else if (cur == 9) {
            sum += dfs(level + 1, cur - 1, bitmask | (1 << (cur - 1))) % 1000000000;
        } else {
            sum += dfs(level + 1, cur + 1, bitmask | (1 << (cur + 1))) % 1000000000;
            sum %= 1000000000;
            sum += dfs(level + 1, cur - 1, bitmask | (1 << (cur - 1))) % 1000000000;
        }
        visit[level][cur][bitmask] = 1;
        sum %= 1000000000;
        return dp[level][cur][bitmask] = sum % 1000000000;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        if (n < 10) {
            System.out.println(0);
            return;
        }
        dp = new long[101][10][(1 << 10)];
        int sol = 0;
        visit = new long[101][10][(1 << 10)];
//        sol += dfs(1, 9, 1 << 9);

        for (int i = 1; i <= 9; i++) {
            long k = dfs(1, i, 1 << i);
//            System.out.printf("i : %d , sol : %d \n", i ,k);
            sol +=  k%1000000000;
            sol %= 1000000000;
        }
        System.out.println(sol);
//        for (n = 10; n <= 40; n++) {
//            dp = new int[n + 1][10][10];
//            sol = 0;
//            dfs(10,9,0);
//            sum += sol;
//        }
    }
}
