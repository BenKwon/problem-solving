package programmers;

import java.io.*;
import java.util.*;

public class test {
    static class Node implements Comparable<Node>{
        int idx;
        int sum;

        public Node(int idx, int sum) {
            this.idx = idx;
            this.sum = sum;
        }

        @Override
        public int compareTo(Node o) {
            return this.sum;
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

        ArrayList<int[]> ints = connectInfo.get(start);
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.sum - o2.sum;
            }
        });
        Node[] nodes = new Node[v + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node(i, Integer.MAX_VALUE);
            pq.add(nodes[i]);
        }
        nodes[start].sum = 0;
//        for (int[] anInt : ints) {
//            int index = anInt[0];
//            int weight = anInt[1];
//            nodes[index].sum = weight;
//        }

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            System.out.println("==========================");
            System.out.printf("idx : %d\n",poll.idx);
            ArrayList<int[]> connect = connectInfo.get(poll.idx);
            if(poll.sum == Integer.MAX_VALUE) break;
            for (int[] next : connect) {
                if(nodes[next[0]].sum > nodes[poll.idx].sum + next[1]){
                    nodes[next[0]].sum = nodes[poll.idx].sum + next[1];
                    System.out.printf("next idx :%d \n",next[0]);
                }
            }
            for(int i=1 ; i < nodes.length;i++){
                if(nodes[i].sum == Integer.MAX_VALUE) System.out.println("INF" );
                else System.out.println(nodes[i].sum );
            }

        }



//        for(int i=1 ; i < nodes.length;i++){
//            if(nodes[i].sum == Integer.MAX_VALUE) bw.write("INF" + "\n");
//            else bw.write(nodes[i].sum + "\n");
//        }

    }
}
