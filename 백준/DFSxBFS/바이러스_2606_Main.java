package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 바이러스_2606_Main {
    public static int n,m;
    public static ArrayList<ArrayList<Integer>> connected_info = new ArrayList<>();
    public static Queue<Integer> q = new LinkedList<>();
    public static int[] visited;
    public static int count = 0;
    private static int bfs(int start) {
         q.offer(start);
         visited[start] = 1;
         while (!q.isEmpty()){
             Integer node = q.poll();
             ArrayList<Integer> connect = connected_info.get(node);
             for(int i = 0 ; i < connect.size() ;i++){
                 Integer node2 = connect.get(i);
                 if(visited[node2] == 0){
                    visited[node2] = 1;
                     count++;
                     q.offer(node2);
                 }else continue;
             }
         }
         return count;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        connected_info.add(new ArrayList<>());
        visited = new int[n + 1];
        for(int i = 0 ; i < n ; i++){
            connected_info.add(i + 1, new ArrayList<>());
        }
        for(int i = 0 ; i < m ; i++){
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            connected_info.get(node1).add(node2);
            connected_info.get(node2).add(node1);
        }

        System.out.println(bfs(1));

    }


}
