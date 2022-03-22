package programmers;

import java.util.*;

// MST 크루스칼 알고리즘 //
public class 섬연결하기 {
    static ArrayList<int[]> edges = new ArrayList<>();
    static int[] parent;

    static int getParent(int node) {
        if (parent[node] == node) return node;
        return parent[node] = getParent(parent[node]);
    }

    static void union(int node1, int node2) {
        node1 = getParent(node1);
        node2 = getParent(node2);
        if (node1 < node2) {
            parent[node2] = node1;
        } else {
            parent[node1] = node2;
        }
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < costs.length; i++) {
            int[] cost = costs[i];
            edges.add(cost);
        }
        Collections.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < edges.size(); i++) {
            int[] cost = edges.get(i);
            int node1 = cost[0];
            int node2 = cost[1];
            if (getParent(node1) == getParent(node2)) continue;
            answer += cost[2];
            union(node1, node2);
        }

        return answer;
    }
}
