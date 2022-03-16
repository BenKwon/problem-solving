package 백준.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1967_트리의지름 {
    static int n ;
    static ArrayList<ArrayList<int[]>> connectInfo = new ArrayList<>();
    static int[] visit;
    static int solution = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visit = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            connectInfo.get(parent).add(new int[]{child, w});
        }
        dfs(1);
        System.out.println(solution);
    }
    static int dfs(int node){
        visit[node] = 1;
        ArrayList<int[]> children = connectInfo.get(node);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int[] child: children){
            int next = child[0];
            int w = child[1];
            if(visit[next] == 1) continue;
            pq.add(dfs(next) + w);
        }
        if(pq.size() == 0) return 0;
        int one = pq.poll();
        int two = 0;
        if(pq.size() != 0) two = pq.poll();
        solution = Math.max(solution, one + two);
        return one;
    }
}
