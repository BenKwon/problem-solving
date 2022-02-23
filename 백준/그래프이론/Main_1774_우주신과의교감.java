package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main_1774_우주신과의교감 {
    static int n, m;
    static int[] parent;
    static int[] nums;
    static pos[] gods;
    static double solution;
    static HashSet<Integer> set = new HashSet<>();
    static PriorityQueue<Distance> pq = new PriorityQueue<>(new Comparator<Distance>() {
        @Override
        public int compare(Distance o1, Distance o2) {
            return Double.compare(o1.value, o2.value);
        }
    });

    static class pos {
        int x;
        int y;

        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Distance {
        double value;
        int point1;
        int point2;

        public Distance(double value, int point1, int point2) {
            this.value = value;
            this.point1 = point1;
            this.point2 = point2;
        }

        @Override
        public String toString() {
            return "Distance{" +
                    "value=" + value +
                    ", point1=" + point1 +
                    ", point2=" + point2 +
                    '}';
        }
    }

    static int getParent(int node) {
        if (parent[node] == node) return node;
        return parent[node] = getParent(parent[node]);
    }

    static void union(int node1, int node2) {
        node1 = getParent(node1);
        node2 = getParent(node2);
        if(node1 == node2) return;
        if (node1 < node2) {
            parent[node2] = node1;
            nums[node1] += nums[node2];
        } else {
            parent[node1] = node2;
            nums[node2] += nums[node1];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gods = new pos[n];
        parent = new int[n];
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gods[i] = new pos(x, y);
        }

        visit = new int[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            set.add(node1);
            set.add(node2);
            visit[node1][node2] = 1;
            visit[node2][node1] = 1;
            union(node1, node2);
        }
        solution();

    }

    static int[][] visit;

    static void solution() {
        PriorityQueue<Integer> solPq = new PriorityQueue();
//        int count = set.size();
        solPq.add(0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j] == 1) {
                    continue;
                }
                double diff = diffNode(gods[i], gods[j]);
                pq.add(new Distance(diff, i, j));
            }
        }
        while (!pq.isEmpty()) {
            Distance poll = pq.poll();
            int node1 = poll.point1;
            int node2 = poll.point2;
            if (getParent(node1) == getParent(node2)) continue;
            System.out.println(poll.toString());
            union(node1, node2);
            solution += poll.value;
//            if(++count == n )break;
            if(nums[0] == n) break;
        }
        System.out.printf("%.2f",solution);
    }

    static double diffNode(pos node1, pos node2) {
        double xDiff = Math.pow((double) (node1.x - node2.x), 2);
        double yDiff = Math.pow((double) (node1.y - node2.y), 2);
        return Math.sqrt(xDiff + yDiff);
    }
}
