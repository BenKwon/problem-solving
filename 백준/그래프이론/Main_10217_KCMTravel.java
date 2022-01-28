package 백준.그래프이론;

import java.util.*;
import java.io.*;

public class Main_10217_KCMTravel {
    static int n, m, k;
    static ArrayList<ArrayList<Node>> connectInfo;

    static class Node {
        int num;
        int cost;
        int distance;

        public Node(int num, int cost, int distance) {
            this.num = num;
            this.cost = cost;
            this.distance = distance;
        }


    }

    private static final int INF = 100 * 1_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            connectInfo = new ArrayList<>();
            for (int i = 0; i <= k; i++) {
                connectInfo.add(new ArrayList<>());
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                connectInfo.get(u).add(new Node(v, c, d));
            }
            /**init 완료 **/
            /**테스트 케이스 정답 find */
            int sol = find();

            if (sol == INF) {
                bw.write("Poor KCM\n");
            } else {
                bw.write(sol + "\n");

            }
        }
        bw.flush();
    }

    static int find() {
        int[][] dp = new int[n + 1][m + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // a = {1,2,3} 1-> 현재노드 2->지금까지 쓴비용, 3->거리
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[1][0] = 0;
        pq.add(new int[]{1, 0, 0});
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int node = poll[0];
            int cost = poll[1];
            int distance = poll[2];
            if (node == n) break;
            if (dp[node][cost] < distance) continue;
            dp[node][cost] = distance;
            for (Node next : connectInfo.get(node)) {
                int nextCost = cost + next.cost;
                int nextDistance = distance + next.distance;
                if (nextCost > m) continue;
                if(dp[next.num][nextCost] > nextDistance){
                    for (int i = nextCost; i <= m; i++) {
                        if (dp[next.num][i] > nextDistance) dp[next.num][i] = nextDistance;
                    }
                    pq.add(new int[]{next.num, nextCost, nextDistance});
                }

            }
        }
        int result = Integer.MAX_VALUE;

        for (int i = 1; i <= m; i++)
            result = Math.min(result, dp[n][i]);
        return result;
    }
}
