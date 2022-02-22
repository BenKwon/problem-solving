package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2228_구간나누기 {
    static int n, m;
    static int[] arr;
    static int INF = -32768101;
    static int[][] rdp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[][][] dp = new int[100][101][2];
        rdp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(rdp[i], INF);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[n - 1][1][0] = INF;
        dp[n - 1][1][1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i][1][0] = Math.max(dp[i + 1][1][0], dp[i + 1][1][1]);
            dp[i][1][1] = Math.max(dp[i + 1][1][1] + arr[i], arr[i]);
        }

        for (int k = 2; k <= m; k++) {
            for (int i = n - 3; i >= 0; i--) {
                int max = Integer.MIN_VALUE;
                for (int j = i; j + 2 < n; j++) {
                    int cur = rangeMax(i, j);
                    int next = Math.max(dp[j + 2][k - 1][0], dp[j + 2][k - 1][1]);
                    max = Math.max(max, cur + next);
                }
                dp[i][k][0] = max;
                dp[i][k][1] = max;
            }
        }

        int solution = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            solution = Math.max(solution, Math.max(dp[i][m][0], dp[i][m][1]));
        }
        System.out.println(solution);
    }

    static int rangeMax(int start, int end) {
        if(rdp[start][end] != INF) return rdp[start][end];
        if(rdp[start + 1][end] != INF){
            rdp[start][end] = Math.max(arr[start], arr[start] + rdp[start + 1][end]);
            return rdp[start][end];
        }
        int[][] tmp = new int[n][2];
        tmp[1] = tmp[start];
        // rdp 초기화
        int max =  arr[start];
        for (int i = start + 1; i <= end; i++) {
            tmp[i][0] = Math.max(tmp[i - 1][0], tmp[i - 1][1]);
            tmp[i][1] = Math.max(tmp[i - 1][1] + arr[i], arr[i]);
            max = Math.max(max, Math.max(tmp[i][0], tmp[i][1]));
        }
        return rdp[start][end] = max;
    }

}
