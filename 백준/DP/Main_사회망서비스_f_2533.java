package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 접근 하는 방법은 얼추 맞았으나 dp 문제인데 dp에 관해서 너무 생각을 안했다.
 *
 */
public class Main_사회망서비스_f_2533 {
    public static int n;
    public static ArrayList<Integer>[] con_info;
    public static int k= 0;
    public static int[][] dp;
    public static int dfs(int cur, int parent) {
        dp[cur][1] = 1;
        dp[cur][0] = 0;
        Iterator<Integer> iterator = con_info[cur].iterator();
        while (iterator.hasNext()) {
            int next = iterator.next();
            if(next == parent) continue;
            dfs(next, cur);
            dp[cur][1] += Math.min(dp[next][1], dp[next][0]);
            dp[cur][0] += dp[next][1];
        }
        return Math.min(dp[cur][1], dp[cur][0]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        con_info = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        for (int i = 0; i < n + 1; i++) {
            con_info[i] = new ArrayList<>();
        }
        con_info[0].add(1);

        for (int i = 1; i <= n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            con_info[node1].add(node2);
            con_info[node2].add(node1);
        }
        System.out.println(dfs(1, -1));
    }
}

