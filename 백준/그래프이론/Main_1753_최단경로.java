package 백준.그래프이론;

import java.io.*;
import java.util.*;

public class Main_1753_최단경로 {
    static class Node {
        int idx;
        int sum;

        public Node(int idx, int sum) {
            this.idx = idx;
            this.sum = sum;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<int[]>> connectInfo = new ArrayList<>();
        ArrayList<Integer> accumWeight = new ArrayList<>();

        connectInfo.add(new ArrayList<>());
        accumWeight.add(0);
        for (int i = 0; i < v; i++) {
            connectInfo.add(new ArrayList<>());
            accumWeight.add(0);
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int _u = Integer.parseInt(st.nextToken());
            int _v = Integer.parseInt(st.nextToken());
            int _w = Integer.parseInt(st.nextToken());
            connectInfo.get(_u).add(new int[]{_v, _w});
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.sum - o2.sum;
            }
        });
        int[] nodes = new int[v + 1];
        int[] visit = new int[v + 1];
        Arrays.fill(nodes, 1000000000);
        nodes[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            System.out.println("=========================");
            Node poll = pq.poll();
            visit[poll.idx] = 1;
            ArrayList<int[]> ints = connectInfo.get(poll.idx);
            for (int[] anInt : ints) {
                if (visit[anInt[0]] != 1) {
                    if (nodes[anInt[0]] > anInt[1] + nodes[poll.idx]) {
                        nodes[anInt[0]] = anInt[1] + nodes[poll.idx];
                        pq.add(new Node(anInt[0], nodes[anInt[0]]));
                    }
                }
            }
            for (int i = 1; i < nodes.length; i++) {
                if (nodes[i] != 1000000000)
                    System.out.println(nodes[i]);
                else
                    System.out.println("INF");
            }
        }

//        for (int i = 1; i < nodes.length; i++) {
//            if (nodes[i] != 1000000000)
//                bw.write(nodes[i] + "\n");
//            else
//                bw.write("INF\n");
//        }

        bw.flush();
        bw.close();
        br.close();
    }
}
