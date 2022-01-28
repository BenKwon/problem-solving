package 백준.그래프이론;

import java.util.*;
import java.io.*;

public class Main_2887_행성터널 {
    static class cord {
        int idx;
        int x;
        int y;
        int z;

        public cord(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class tunnel {
        int cord1;
        int cord2;
        int weight;

        public tunnel(int cord1, int cord2, int weight) {
            this.cord1 = cord1;
            this.cord2 = cord2;
            this.weight = weight;
        }
    }

    static int[] parent;

    static int getParent(int node) {
        if (parent[node] == node) return node;
        return parent[node] = getParent(parent[node]);
    }

    static void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static int n;
    static ArrayList<cord> seq = new ArrayList<>();
    static ArrayList<cord> xseq = new ArrayList<>();
    static ArrayList<cord> yseq = new ArrayList<>();
    static ArrayList<cord> zseq = new ArrayList<>();
    static ArrayList<tunnel> xTunnel = new ArrayList<>();
    static ArrayList<tunnel> yTunnel = new ArrayList<>();
    static ArrayList<tunnel> zTunnel = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            xseq.add(new cord(i, x, y, z));
            yseq.add(new cord(i, x, y, z));
            zseq.add(new cord(i, x, y, z));
        }
        xseq.sort((a, b) -> a.x - b.x);
        yseq.sort((a, b) -> a.y - b.y);
        zseq.sort((a, b) -> a.z - b.z);
        PriorityQueue<tunnel> pq = new PriorityQueue<>(new Comparator<tunnel>() {
            @Override
            public int compare(tunnel o1, tunnel o2) {
                return o1.weight - o2.weight;
            }
        });

        for (int i = 0; i < n - 1; i++) {
            //x
            int diff = Math.abs(xseq.get(i).x - xseq.get(i + 1).x);
            pq.add(new tunnel(xseq.get(i).idx, xseq.get(i + 1).idx, diff));
            //y
            diff = Math.abs(yseq.get(i).y - yseq.get(i + 1).y);
            pq.add(new tunnel(yseq.get(i).idx, yseq.get(i +1).idx, diff));

            //z
            diff = Math.abs(zseq.get(i).z - zseq.get(i + 1).z);
            pq.add(new tunnel(zseq.get(i).idx, zseq.get(i + 1).idx, diff));
        }

        int buildTunnel = 0;
        int cost = 0;

        while (buildTunnel < n - 1) {
            tunnel t = pq.poll();
            if (getParent(t.cord1) != getParent(t.cord2)) {
                union(t.cord1, t.cord2);
//                System.out.printf("union %d ,%d\n", t.cord1, t.cord2);
                cost += t.weight;
                buildTunnel++;
            }
        }
        System.out.println(cost);
    }
}
