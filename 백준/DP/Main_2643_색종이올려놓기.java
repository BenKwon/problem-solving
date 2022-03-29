package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.*;
import java.util.StringTokenizer;

public class Main_2643_색종이올려놓기 {
    static int n;
    static ArrayList<int[]> papers = new ArrayList<>();
    static int[] dp;
    static int[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        visit = new int[n + 1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            papers.add(new int[]{a, b});
        }

        Collections.sort(papers,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                int o1Min = Math.min(o1[0], o1[1]);
                int o2Min = Math.min(o2[0], o2[1]);
                if(o1Min == o2Min){
                    return Math.max(o2[0], o2[1]) - Math.max(o1[0], o1[1]);
                }else{
                    return o2Min - o1Min;
                }
            }
        });
//        for(int[] next : papers){
//            System.out.printf("(%d, %d)\n",next[0],next[1]);
//        }
        int answer = dfs(0);
        dp[n -1]= 1;
        visit[n -1] = 1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            dfs(i);
        }
        for (int next : dp) {
            max = Math.max(next, max);
        }
        System.out.println(max);
    }
    static int dfs(int index){
        if(visit[index] == 1)return dp[index];
        int[] curPaper = papers.get(index);
        int curMin = Math.min(curPaper[0], curPaper[1]);
        int curMax = Math.max(curPaper[0], curPaper[1]);
        int max = 0;
        for (int i = index + 1; i < n; i++) {
            int[] nextPaper = papers.get(i);
            int nextMax = Math.max(nextPaper[0], nextPaper[1]);
            int nextMin = Math.min(nextPaper[0], nextPaper[1]);
            if(curMin >= nextMin && curMax >= nextMax){
                max = Math.max(max, dfs(i));
            }
        }
//        System.out.println("max = " + max);
        visit[index] = 1;
//        System.out.printf("dp[%d] : %d\n",index,dp[index]);
        return dp[index] = 1 + max;
    }
}
