package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS와BFS_1260_Main {
    public static ArrayList<ArrayList<Integer>> connect_info = new ArrayList<ArrayList<Integer>>();
    public static int[] visted;
    public static Stack<Integer> stack = new Stack<>();
    public static void dfs(int start){
        stack.push(start);
        visted[start] = 1;
        System.out.printf("%d ",start);
        ArrayList<Integer> connect = connect_info.get(start);
        for(int i = 0; i < connect.size(); i++){
            Integer node1 = connect.get(i);
            if(visted[node1] == 0){
//                visted[node1] = 1;
                dfs(node1);
            }
        }
    }

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visted[start] = 1;
        System.out.printf("%d ",start);
        while(!q.isEmpty()){
            Integer node = q.poll();
            ArrayList<Integer> connect = connect_info.get(node);
            for(int i = 0; i < connect.size(); i++){
                Integer node1 = connect.get(i);
                if(visted[node1] == 0){//첫 방문
                    visted[node1] = 1;
                    System.out.printf("%d ",node1);
                    q.offer(node1);
                }else{//방문한적이 있음
                    continue;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        visted = new int[n + 1];
        connect_info.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            connect_info.add(i + 1, new ArrayList<>());
        }
        for(int i = 0 ; i < m ;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            connect_info.get(node1).add(node2);
            connect_info.get(node2).add(node1);

        }
        for (int i = 0; i < n; i++) {
            Collections.sort(connect_info.get(i+1));
        }
        //bfs
        dfs(v);
        System.out.printf("\n");
        //
        visted = new int[n + 1]; //방문 이력 초기화
        bfs(v);

    }
}
