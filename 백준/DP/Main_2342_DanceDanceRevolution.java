package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2342_DanceDanceRevolution {
    static ArrayList<Integer> seq;
    static int n;
    static int[][][] dp;
    static int[][][] visit;
    static int calculateScore(int start, int dest){
        if(start == 0)return 2;
        if(start == dest) return 1;
        if(start == 1 && (dest == 2 || dest ==4)) return 3;
        if(start == 2 && (dest == 1 || dest ==3)) return 3;
        if(start == 3 && (dest == 2 || dest ==4)) return 3;
        if(start == 4 && (dest == 1 || dest ==3)) return 3;
        return 4;
    }
    public static int dfs(int left, int right, int level){
        if(level == n - 1) return 0;
        if(visit[left][right][level] == 1) return dp[left][right][level];
        if(visit[right][left][level] == 1) return dp[right][left][level];
        int next = seq.get(level + 1);
        int min = Integer.MAX_VALUE;
        if(left == next || right == next) {
            min = Math.min(dfs(left, right, level + 1) + 1,min);
        }else{
            //왼발을 옮겼을때
            min = Math.min(min, dfs(next, right, level + 1) + calculateScore(left,next));
            //오른발을 옮겼을때
            min = Math.min(min, dfs(left, next, level + 1) + calculateScore(right, next));
        }

        visit[left][right][level] = 1;
        visit[right][left][level] = 1;
        dp[right][left][level] = min;
        return dp[left][right][level] = min;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seq = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        seq.add(0);
        while (st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());
            if(next == 0 )break;
            seq.add(next);
        }
        n = seq.size();
        dp = new int[5][5][100001];
        visit = new int[5][5][100001];
        System.out.println(dfs(0, 0, 0));

    }
}
