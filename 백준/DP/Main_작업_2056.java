package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_작업_2056 {
    public static int n;
    public static ArrayList<ArrayList<Integer>> parent_info = new ArrayList<>(); // i번째를 설계하기위해 선행되어야 하는 노드들
    public static ArrayList<ArrayList<Integer>> child_info = new ArrayList<>();  // i번째를 의존하고 있는 노드들
    public static int[] weight;
    public static Queue<Task> q = new LinkedList<>();
    public static int[] visit;
    public static int[] dp;
    public static int bfs() {
//        int max = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Task poll = q.poll();
            ArrayList<Integer> parents = parent_info.get(poll.index);
            int max = 0;
            for (int p = 0; p < parents.size(); p++) {
                int parent_idx = parents.get(p);
                if(p == 0) max = dp[parent_idx];
                else{
                    max = Math.max(max, dp[parent_idx]);
                }
            }
            dp[poll.index] = weight[poll.index] + max;
            visit[poll.index] = 1;
            ArrayList<Integer> children = child_info.get(poll.index);
            for(int i = 0 ; i < children.size() ; i++){
                int child = children.get(i);
                ArrayList<Integer> parents_of_child = parent_info.get(child);
                boolean possible_flag = true;
                for (int j = 0; j < parents_of_child.size(); j++) {
                    if(visit[parents_of_child.get(j)] == 0){
                        possible_flag = false;
                        break;
                    }
                }
                if(possible_flag){
                    q.offer(new Task(child, poll.index));
                }
            }
        }
        return 0;
    }
    public static class Task{
        int index;
        int parent;

        public Task(int index, int parent) {
            this.index = index;
            this.parent = parent;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        weight = new int[n + 1];
        visit = new int[n + 1];
        dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            child_info.add(new ArrayList<>());
            parent_info.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (d == 0 && !st.hasMoreTokens()) {
                q.offer(new Task(i,0));
                continue;
            }
            for (int j = 0; j < d; j++) {
                int depend = Integer.parseInt(st.nextToken());
                parent_info.get(i).add(depend);
                child_info.get(depend).add(i);
            }
        }
        bfs();
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
