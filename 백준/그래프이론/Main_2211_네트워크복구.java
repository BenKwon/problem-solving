package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;
public class Main_2211_네트워크복구 {
    static int n, m;
    static int[] nodes, visit;
    static int INF = 10001;
    static ArrayList<ArrayList<int[]>> connectInfo = new ArrayList<>();
    static ArrayList<int[]> answer = new ArrayList<>();
    static class Node implements Comparable<Node>{
        int num;
        int sum;
        int ex;
        public Node(int num, int sum, int ex) {
            this.num = num;
            this.sum = sum;
            this.ex= ex;
        }
        @Override
        public int compareTo(Node node){
            return this.sum - node.sum;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new int[n + 1];
        nodes = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            connectInfo.get(a).add(new int[]{b, c});
            connectInfo.get(b).add(new int[]{a, c});
        }
        Arrays.fill(nodes,INF);
        nodes[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, -1));
        while (!pq.isEmpty()) {
            Node poll =pq.poll();
            int num = poll.num;
            int sum = poll.sum;
            if(visit[num] == 1)continue;
            visit[num] = 1;
            if(poll.ex > 0) answer.add(new int[]{num, poll.ex});
            ArrayList<int[]> connects = connectInfo.get(num);
            for(int[] next : connects){
                if(visit[next[0]] == 1) continue;
                if (sum + next[1] < nodes[next[0]]) {
                    nodes[next[0]] = sum + next[1];
                    pq.add(new Node(next[0], nodes[next[0]], num));
                }
            }
        }
        System.out.println(answer.size());
        for(int[] ans : answer){
            System.out.printf("%d %d\n", ans[1], ans[0]);
        }

    }
}
