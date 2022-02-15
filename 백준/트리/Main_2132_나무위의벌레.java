package 백준.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2132_나무위의벌레 {
    static int n;
    static ArrayList<ArrayList<Integer>> connectInfo = new ArrayList<>();
    static int[] nodes;
    static int[] visit;
    static HashSet<Integer> noLeaves = new HashSet<>();
    static PriorityQueue<int[]> solution = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o2[0] - o1[0];
            }
        }
    });

    static int[] dfs(int node) {
        visit[node] = 1;
//        System.out.println("node = " + node);
        if (!noLeaves.contains(node)) {//리프 노드일때
//            System.out.printf("%d is end\n",node);
            return new int[]{nodes[node], node};
        }
        ArrayList<Integer> connects = connectInfo.get(node);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        });

        for (int next : connects) {
            if (visit[next] == 1) continue;
            pq.add(dfs(next));
        }

        int[] poll1 = pq.poll();
        if(pq.isEmpty()){
            if (poll1[0] == 0) return new int[]{nodes[node], Math.min(poll1[1], node)};
            return new int[]{nodes[node] + poll1[0], poll1[1]};
        }
        int[] poll2 = pq.poll();
//        System.out.printf("poll1[0] : %d, poll2[0] : %d . node : %d\n",poll1[0],poll2[0],node );
        int tot = poll1[0] + poll2[0] + nodes[node];
        if (poll1[0] == 0 || poll2[0] == 0) {
            solution.add(new int[]{tot, Math.min(node, Math.min(poll1[1], poll2[1]))});
        } else  solution.add(new int[]{tot ,Math.min(poll1[1], poll2[1])});

        if (poll1[0] == 0) return new int[]{nodes[node], Math.min(poll1[1], node)};
        return new int[]{nodes[node] + poll1[0], poll1[1]};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodes = new int[n + 1];
        visit = new int[n + 1];
        connectInfo.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
            nodes[i] = Integer.parseInt(st.nextToken());
            noLeaves.add(i);
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            connectInfo.get(node1).add(node2);
            connectInfo.get(node2).add(node1);
            visit[node1]++;
            visit[node2]++;
        }
        if (n == 1) {
            System.out.printf("%d %d", nodes[1], 1);
            return;
        } else if (n == 2) {
            System.out.printf("%d %d", nodes[1] + nodes[2], 1);
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (visit[i] < 2) noLeaves.remove(i);
        }
        int startNode = 0;
        ArrayList<Integer> starts = new ArrayList<>();
        for (int i : noLeaves) {
            startNode = i;
            break;
        }

        visit = new int[n + 1];
        ArrayList<Integer> connects = connectInfo.get(startNode);

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        });

//        visit[startNode] = 1;
        dfs(startNode);
        int[] poll = solution.poll();
        System.out.printf("%d %d", poll[0],poll[1]);
//
//        for (int next : connects) {
//            if (visit[next] == 1) continue;
//            pq.add(dfs(next));
//        }
//        int[] poll1 = pq.poll();
//        int[] poll2 = pq.poll();
//        int tot = poll1[0] + poll2[0] + nodes[startNode];
//        if (poll1[0] == 0 || poll2[0] == 0) {
//            System.out.printf("%d %d", tot, Math.min(startNode, Math.min(poll1[1], poll2[1])));
//        } else System.out.printf("%d %d", tot, Math.min(poll1[1], poll2[1]));
    }
}
