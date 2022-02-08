package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main_1238_파티{
    static int n,m,x;
    static int[][] weights;
    static ArrayList<Integer>[] connectInfo;
    static int solution = -1;
    static int INF = 10000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        connectInfo = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            connectInfo[i] = new ArrayList<>();
        }
        weights = new int[n + 1][m + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end =Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            weights[start][end] = weight;
            connectInfo[start].add(end);
        }
        for (int i = 1; i <= n; i++) {
            int time = dijkstra(i) + dijkstra2(i);
            solution = Math.max(time, solution);
        }
        System.out.println(solution);
    }
    static class node{
        int num;
        int path;

        public node(int num, int path) {
            this.num = num;
            this.path = path;
        }
    }
    static int dijkstra(int start){
        int[] visit = new int[n + 1];
        int[] arr = new int[n + 1];
        Arrays.fill(arr, INF);
        PriorityQueue<node> pq = new PriorityQueue<>((a, b) -> a.path - b.path);
        pq.add(new node(start, 0));
        arr[start] =  0;
        while (!pq.isEmpty()) {
            node poll = pq.poll();
            int num = poll.num;
            int path = poll.path;
            if(visit[num] == 1) continue;
            visit[num] = 1;
//            System.out.printf("poll : [%d, %d] \n", num , path);
            for (int next : connectInfo[num]) {
//                System.out.println("next = " + next);
                int weight = weights[num][next];
                if(visit[next] == 1) continue;
                if(path + weight < arr[next]){
                    arr[next] = path + weight;
                    pq.add(new node(next, arr[next]));
                }
            }
        }
//        for (int i = 1; i <= n; i++) {
//            System.out.printf("%d -> %d : %d \n",start,i,arr[i]);
//        }
        return arr[x];
    }

    static int dijkstra2(int end){
        int[] visit = new int[n + 1];
        int[] arr = new int[n + 1];
        Arrays.fill(arr, INF);
        PriorityQueue<node> pq = new PriorityQueue<>((a, b) -> a.path - b.path);
        pq.add(new node(x, 0));
        arr[x] =  0;
        while (!pq.isEmpty()) {
            node poll = pq.poll();
            int num = poll.num;
            int path = poll.path;
            if(visit[num] == 1) continue;
            visit[num] = 1;
            for (int next : connectInfo[num]) {
                int weight = weights[num][next];
                if(visit[next] == 1) continue;
                if(path + weight < arr[next]){
                    arr[next] = path + weight;
                    pq.add(new node(next, arr[next]));
                }
            }
        }
        return arr[end];
    }
}
