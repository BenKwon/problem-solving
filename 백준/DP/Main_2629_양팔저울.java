package 백준.DP;

import java.util.*;
import java.io.*;
public class Main_2629_양팔저울 {
    static int n, m;
    static ArrayList<Integer> weights, marbles;
    static HashSet<Integer> possible = new HashSet<>();
    static int[][] dp;
    public static void dfs(int idx, int compute){
        possible.add(Math.abs(compute));
        if(idx == n){
            return;
        }
        if(dp[idx][Math.abs(compute)] == 1) return;
        dp[idx][Math.abs(compute)] = 1;
        int weight = weights.get(idx);
        dfs(idx + 1 , compute);
        dfs(idx+ 1 , compute + weight);
        dfs(idx + 1, compute - weight);

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n][15001];
        weights = new ArrayList<>();
        marbles = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weights.add(Integer.parseInt(st.nextToken()));
        }


        m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            marbles.add(Integer.parseInt(st.nextToken()));
        }

        dfs(1, 0);
        dfs(1, weights.get(0));
        dfs(1, -weights.get(0));

        for(int o : marbles){
            if(possible.contains(o)){
                System.out.printf("Y ");
            }else{
                System.out.printf("N ");
            }
        }
    }
}
