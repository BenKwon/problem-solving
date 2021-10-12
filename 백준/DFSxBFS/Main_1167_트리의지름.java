package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main_1167_트리의지름 {
    static ArrayList<node>[] connect_info;
    static int[] visit;
    static int sol = Integer.MIN_VALUE;
    static class node{
        int value;
        int weight;

        public node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }
    public static int dfs(int value ){
        int max = 0;
        visit[value] = 1;
        PriorityQueue<Integer> store = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<node> nodes = connect_info[value];
        for (int i = 0; i < nodes.size(); i++) {
            node next  = nodes.get(i);
            if(visit[next.value] == 1) continue;
            int tmp = next.weight + dfs(next.value);
            store.add(tmp);
            if(tmp > max) max = tmp;
        }

        if(store.size() >= 2){
            int value1 = store.poll();
            int value2 = store.poll();
            if((value1+ value2) > sol) sol = value1 + value2;
        }

        if(sol < max) sol = max;

        return max;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());
        connect_info = new ArrayList[v + 1];
        visit = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            connect_info[i] = new ArrayList<>();
        }
        for (int i = 1; i <= v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start_node = Integer.parseInt(st.nextToken());
            while(true){
                int next = Integer.parseInt(st.nextToken());
                if(next == -1)break;
                int weight = Integer.parseInt(st.nextToken());
                connect_info[start_node].add(new node(next,weight));
            }
        }
        dfs(1);
        System.out.println(sol);



    }
}
