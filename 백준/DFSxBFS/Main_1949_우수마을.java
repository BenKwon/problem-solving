package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 입력
 * 7
 * 1000 3000 4000 1000 2000 2000 7000
 * 1 2
 * 2 3
 * 4 3
 * 4 5
 * 6 2
 * 6 7
 *
 * 출력
 * 14000
 * */
public class Main_1949_우수마을 {
    static int n;
    static ArrayList<Integer> leaves = new ArrayList<>();
    static int[] people;
    static int[] visit;
    static int[][] dp;
    static ArrayList<Integer>[] connect_info;
    public static void find_leaf(int node){
        ArrayList<Integer> connects = connect_info[node];
        visit[node] = 1;
        boolean leaf = true;
        for (int connect : connects) {
            if(visit[connect] == 1) continue;
            leaf = false;
            find_leaf(connect);
        }
        if(leaf){
            leaves.add(node);
        }
    }
    public static int[] find(int node){
//        System.out.printf("node : %d \n",node);
        if(visit[node] == 2) return dp[node];
        ArrayList<Integer> connects = connect_info[node];
        visit[node]  = 1;
        int sum_a = 0;
        int sum_b = 0;
        for (int connect : connects) {
            if(visit[connect] == 1) continue;
//            System.out.printf("[%d]->>>>>>>>> next : %d, visit: %d\n",node,connect,visit[connect]);
            int[] result = find(connect);
            sum_a +=result[1];
            sum_b += Math.max(result[0],result[1]);
        }

//        System.out.println("sum a : " + sum_a);
//        System.out.println("sum b : " + sum_b);

        //현재 노드를 골랐을때
        dp[node][0] = sum_a + people[node];
        //현재 노드를 안골랐을때
        dp[node][1] = sum_b;
//        System.out.printf("dp[%d] : [%d, %d]\n",node, dp[node][0], dp[node][1]);
        return dp[node];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        people = new int[n + 1];
        visit = new int[n + 1];
        dp = new int[n + 1][2];
        connect_info = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            connect_info[i + 1] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            people[i + 1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            connect_info[node1].add(node2);
            connect_info[node2].add(node1);
        }

        find_leaf(1);
        visit = new int[n + 1];
        for (int leaf : leaves) {
            visit[leaf] = 2;
            dp[leaf][0] = people[leaf];
        }
        find(1);
        System.out.println(Math.max(dp[1][0],dp[1][1]));
    }
}
