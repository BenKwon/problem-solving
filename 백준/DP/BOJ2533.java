package 백준.DP;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  No.2533: 사회망 서비스(SNS)
 *  URL: https://www.acmicpc.net/problem/2533
 *  Hint: 자신이 얼리어답터일때, 아닐때를 구분하여 최솟값을 구함
 */

public class BOJ2533 {
    static int n;
    static int[][] dp;
    static int k  = 0;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        dp(1, -1);
        System.out.println("k = " + k);

        bw.write(String.valueOf(Math.min(dp[1][0], dp[1][1])));
        bw.close();
        br.close();
    }

    static void dp(int cur, int parent) {
        System.out.println("k : " + (++k));

        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for (int next : list.get(cur)) {
            // 사이클이 존재하지 않고, 부모가 유일하므로
            // next와 parent가 같으면 단말노드라고 생각할 수 있다.
            if (next != parent) {
                dp(next, cur);
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}