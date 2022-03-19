package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_최소스패닝트리_복습 {
    static int v, e;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parent;

    static class Edge{
        int node1;
        int node2;
        int weight;

        public Edge(int node1, int node2, int weight) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }
    }

    static int getParent(int node){
        if(parent[node] == node) return node;
        return parent[node] = getParent(parent[node]);
    }

    static void union(int node1 ,int node2){
        node1 = getParent(node1);
        node2 = getParent(node2);
        if(node1 < node2){
            parent[node2] = node1;
        }else{
            parent[node1] = node2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        parent = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        Collections.sort(edges, (o1,o2)->o1.weight-o2.weight);
        int answer = 0;

        for(int i = 0 ; i < e;i++){
            Edge edge = edges.get(i);
            int node1 = edge.node1;
            int node2 = edge.node2;
            if(getParent(node1) == getParent(node2)) continue;
            answer += edge.weight;
            union(node1,node2);
        }

        System.out.println(answer);
    }
}
