package 백준.DP;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_게임개발_1516 {
    public static int n;
    public static Queue<Integer> queue = new LinkedList<>();
    public static ArrayList<ArrayList<Integer>> connect_info = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> parent_info = new ArrayList<>();

    public static int[] weight;
    public static int[] dp;
    public static int[] visit;
    public static void bfs() {
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            ArrayList<Integer> connect_nodes = connect_info.get(poll);
            visit[poll] = 1;
            for (int i = 0; i < connect_nodes.size(); i++) {
                int next = connect_nodes.get(i);
                if (dp[next] == 0) {
                    ArrayList<Integer> parents = parent_info.get(next);
                    boolean visit_parent = true;
                    int max = Integer.MIN_VALUE;
                    for (int p = 0; p < parents.size(); p++) {
                        if(visit[parents.get(p)] == 0){
                            visit_parent = false;
                            break;
                        }
                        max = Math.max(max, dp[parents.get(p)]);
                    }
                    if(visit_parent){
                        dp[next] = max + weight[next];
                        queue.offer(next);
                    }
                } else {
                    if (dp[next] > dp[poll] + weight[next]) {
                        dp[next] = dp[poll] + weight[next];
                        queue.offer(next);
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        connect_info.add(new ArrayList<>());
        parent_info.add(new ArrayList<>());
        weight = new int[n + 1];
        dp = new int[n + 1];
        visit = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            connect_info.add(new ArrayList<>());
            parent_info.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            if (next == -1) {
                queue.offer(i);
                dp[i] = weight[i];
            } else {
                connect_info.get(next).add(i);
                parent_info.get(i).add(next);
                while(true){
                    next = Integer.parseInt(st.nextToken());
                    if(next == -1) break;
                    connect_info.get(next).add(i);
                    parent_info.get(i).add(next);


                }
            }
        }
        bfs();
        for (int i = 1; i <= n; i++) {
            if (i != n)
                bw.write(dp[i] + "\n");
            else
                bw.write(dp[i] + "");

        }
        bw.flush();
        bw.close();
        br.close();
    }
}
