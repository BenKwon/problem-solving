package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1504_특정한최단경로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j ) graph[i][j] = 0;
                else graph[i][j] = 200000001;
            }
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(graph[node1][node2] > weight || graph[node2][node1] > weight){
                graph[node1][node2] = weight;
                graph[node2][node1] = weight;
            }

        }
        st = new StringTokenizer(br.readLine());
        int needToVisitNodeA = Integer.parseInt(st.nextToken());
        int needToVisitNodeB = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++){
                    if(graph[j][i] + graph[i][k] < graph[j][k]){
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }

        int[][] routes = {{1, needToVisitNodeA, needToVisitNodeB, n}, {
                1, needToVisitNodeB, needToVisitNodeA, n},
                {1, n, needToVisitNodeA, needToVisitNodeB, n},
                {1, n, needToVisitNodeB, needToVisitNodeA, n},
                {1, needToVisitNodeA, n, needToVisitNodeB, n},
                {1, needToVisitNodeB, n, needToVisitNodeA, n}};
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 6; i++) {
            int[] route = routes[i];
            int totalWeight = 0;
            for (int j = 0; j < route.length - 1; j++) {
                totalWeight += graph[route[j]][route[j+1]];
            }
            min = Math.min(min, totalWeight);
        }
        if(min > 200000000){
            System.out.println(-1);
        }else{
            System.out.println(min);
        }

    }
}
