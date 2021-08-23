package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2482_색상환 {
    public static int n, k;
    public static int dp[][][] = new int[1001][1000][2];
    public static int visit[][] = new int[1001][1000];
    public static int mod = 1000000003;
    public static int[] result = new int[2];
    public static void dfs(int level, int color) {
        if (level == k) {
            if (color == n - 1) {
                result[0] = 0;
                result[1] = 1;
                dp[level][color][0] = result[0];
                dp[level][color][1] = result[1];
                return;
            }else{
                result[0] = 1;
                result[1] = 0;
                dp[level][color][0] = result[0];
                dp[level][color][1] = result[1];
                return;
            }
        }
        if(visit[level][color] == 1) {
            result[0] = dp[level][color][0];
            result[1] = dp[level][color][1];
            return;
        }

        int[] sub = new int[2];
        for (int i = color + 2; i <= n - 1; i++) {
            dfs(level + 1, i);
//            System.out.printf("rseult : [%d, %d]\n",result[0],result[1]);
            sub[0] += result[0];
            sub[1] += result[1];
            sub[0] %= mod;
            sub[1] %= mod;
        }
        visit[level][color] = 1;
        result[0] = sub[0];
        result[1] = sub[1];
        dp[level][color][0] = result[0];
        dp[level][color][1] = result[1];

        return;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            dfs(1, i);
        }
        int sol = 0;
        for (int i = 0; i < n; i++) {
            if(i == 0){
                sol += dp[1][i][0] % mod;
            }else{
                sol += (dp[1][i][0] + dp[1][i][1])%mod;
            }
            sol %= mod;
        }
        System.out.println(sol%mod);
    }
}
