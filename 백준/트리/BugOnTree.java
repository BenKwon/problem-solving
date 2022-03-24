package 백준.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BugOnTree {

    static int n;
    static int[] fruits;
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] fruit = br.readLine().split(" ");
        tree = new ArrayList[n + 1];
        fruits = new int[n + 1];

        // fruits init
        for (int i = 1; i < n + 1; i++) {
            fruits[i] = Integer.parseInt(fruit[i - 1]);
        }
        for (int i = 0; i < n + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        // vertex init
        for (int i = 0; i < n - 1; i++) {
            String[] vertex = br.readLine().split(" ");
            int x = Integer.parseInt(vertex[0]);
            int y = Integer.parseInt(vertex[1]);
            tree[x].add(y);
            tree[y].add(x);
        }

        // start from 1
        StringBuilder sb = new StringBuilder();
        int[] maxNode = maximumFruits(1);
        int[] result = maximumFruits(maxNode[1]);
        sb.append(result[0]).append(' ').append(Math.min(maxNode[1], result[1]));
        System.out.println(sb);
    }

    public static int[] maximumFruits(int start) {
        int maxFruits = fruits[start]; // bug can eat maximum cnt
        int maxNode = start;
        int[] visit = new int[n + 1];
        Arrays.fill(visit, -1); // 모두 0 일수 있으므로
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        visit[start] = maxFruits;
        dq.offerLast(start);

        while (!dq.isEmpty()) {
            int now = dq.removeFirst();
            for (int next : tree[now]) {
                if (visit[next] == -1) {
                    visit[next] = visit[now] + fruits[next];
                    dq.offerLast(next);
                    if (maxFruits < visit[next]) {
                        maxFruits = visit[next];
                        maxNode = next;
                    } else if (maxFruits == visit[next] && next < maxNode) {
                        maxFruits = visit[next];
                        maxNode = next;
                    }
                }
            }
        }
        return new int[]{maxFruits, maxNode};
    }
}