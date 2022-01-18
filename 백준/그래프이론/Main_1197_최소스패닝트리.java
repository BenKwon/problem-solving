package 백준.그래프이론;

import java.io.*;
import java.util.*;

public class Main_1197_최소스패닝트리 {
    static int[] parent;
    static class Edge{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static int getParent(int node1) {
        if(parent[node1] == node1) return node1;
        return parent[node1] = getParent(parent[node1]);
    }

    static void union(int node1, int node2) {
        node1 = getParent(node1);
        node2 = getParent(node2);
        if(node1 < node2){
            parent[node2] = node1;
        }else{
            parent[node1] = node2;
        }
    }

    static boolean sameParent(int node1, int node2) {
        return getParent(node1) == getParent(node2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
//        ArrayList<ArrayList<Integer>> connectedInfo = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();
        parent = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
        Collections.sort(edges, new Comparator<Edge>(){
            @Override
            public int compare(Edge o1, Edge o2){
                return o1.weight - o2.weight;
            }
        });
        int visitNodeNum = 0;
        int mstWeight = 0;
        for (int i = 0; i < E; i++) {
            Edge edge = edges.get(i);
            int startNode=  edge.start;
            int endNode = edge.end;
            if(sameParent(startNode,endNode)) continue;
            mstWeight += edge.weight;
            visitNodeNum++;
            union(startNode, endNode);
            if(visitNodeNum == V) break;
        }
        System.out.println(mstWeight);
    }
}
