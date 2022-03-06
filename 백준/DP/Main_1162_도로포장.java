package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

public class Main_1162_도로포장 {
    static int n, m, k;
    static long[][] visit;
    static long INF =Long.MAX_VALUE;
    static ArrayList<ArrayList<int[]>> connectInfo = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visit = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(visit[i], INF);
            connectInfo.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            connectInfo.get(city1).add(new int[]{city2, time});
            connectInfo.get(city2).add(new int[]{city1, time});
        }

        Arrays.fill(visit[1], 0);
        solution();
    }

    static void solution() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, k, 0));
        long min = INF;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            int cityId = poll.cityId;
            int k = poll.k;
            long sum = poll.sum;
            if(cityId == n){
                min = Math.min(min, sum);
                continue;
            }
            if (visit[cityId][k] < sum) continue;
            ArrayList<int[]> connects = connectInfo.get(cityId);
            for (int[] next : connects) {
                int nextCityId = next[0];
                int takeTime = next[1];
                //k 사용
                if (k > 0 && sum < visit[nextCityId][k - 1]) {
                    visit[nextCityId][k - 1] = sum;
                    q.add(new Node(nextCityId, k - 1, sum));
                }
                //k 사용x
                if (sum + takeTime < visit[nextCityId][k]){
                    visit[nextCityId][k] = sum + takeTime;
                    q.add(new Node(nextCityId, k, sum + takeTime));
                }
            }
        }
        System.out.println(min);
    }

    static class Node implements Comparable<Node> {
        int cityId;
        int k;
        long sum;

        public Node(int cityId, int k, long sum) {
            this.cityId = cityId;
            this.k = k;
            this.sum = sum;
        }

        @Override
        public int compareTo(Node node){
            return Long.compare(this.sum, node.sum);
        }

    }
}
